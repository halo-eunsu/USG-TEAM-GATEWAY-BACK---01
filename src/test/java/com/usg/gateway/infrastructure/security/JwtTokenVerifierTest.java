package com.usg.gateway.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JwtTokenVerifierTest {
    private JwtTokenVerifier jwtTokenVerifier;
    private String secret = "secret_key";
    private Algorithm algorithm;

    @BeforeEach
    void setUp() {
        algorithm = Algorithm.HMAC512(secret);
        jwtTokenVerifier = new JwtTokenVerifier(secret);
    }

    @Test
    void verifyTokenShouldNotThrowExceptionForValidToken() {

        String token = JWT.create()
                .withSubject("user")
                .withClaim("email", "test@naver.com")
                .withClaim("role", "ADMIN")
                .sign(algorithm);


        assertDoesNotThrow(() -> jwtTokenVerifier.verifyToken(token));
    }

    @Test
    void verifyTokenShouldThrowExceptionForInvalidToken() {

        String invalidToken = "invalidToken";


        assertThrows(JWTVerificationException.class, () -> jwtTokenVerifier.verifyToken(invalidToken));
    }
}
