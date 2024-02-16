package com.usg.gateway.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.usg.gateway.domain.port.TokenVerifierPort;

import java.util.Date;

public class JwtTokenVerifier implements TokenVerifierPort {
    private final JWTVerifier verifier;


    public JwtTokenVerifier(String secret) {
        Algorithm algorithm = Algorithm.HMAC512(secret);
        this.verifier = JWT.require(algorithm).build();
    }

    @Override
    public boolean verifyToken(String token) {
        try {
            DecodedJWT jwt = verifier.verify(token);


            Date expiresAt = jwt.getExpiresAt();
            if (expiresAt.before(new Date())) {
                System.out.println("토큰 만료");
                return false;
            }


            String email = jwt.getClaim("email").asString();
            if (email == null || email.isEmpty()) {
                System.out.println("Email claim is missing");
                return false;
            }


            String role = jwt.getClaim("role").asString();
            if (!"ADMIN".equals(role)) {
                System.out.println("관리자가 아닙니다.");
                return false;
            }


            return true;
        } catch (JWTVerificationException e) {

            System.out.println("토큰검증 실패 : " + e.getMessage());
            return false;
        }
    }

}
