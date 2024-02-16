package com.usg.gateway.application.service;

import com.usg.gateway.domain.port.TokenVerifierPort;

public class TokenVerificationService {
    private final TokenVerifierPort tokenVerifierPort;

    public TokenVerificationService(TokenVerifierPort tokenVerifierPort) {
        this.tokenVerifierPort = tokenVerifierPort;
    }

    public boolean verifyToken(String token) {
        return tokenVerifierPort.verifyToken(token);
    }
}
