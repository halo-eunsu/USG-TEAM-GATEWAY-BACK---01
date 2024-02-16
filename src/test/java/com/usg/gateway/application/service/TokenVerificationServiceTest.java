package com.usg.gateway.application.service;

import com.usg.gateway.domain.port.TokenVerifierPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class TokenVerificationServiceTest {
    private TokenVerifierPort tokenVerifierPort;
    private TokenVerificationService tokenVerificationService;

    @BeforeEach
    void setUp() {

        tokenVerifierPort = Mockito.mock(TokenVerifierPort.class);

        tokenVerificationService = new TokenVerificationService(tokenVerifierPort);
    }

    @Test
    void verifyTokenShouldReturnTrueForValidToken() {

        String validToken = "validToken";
        when(tokenVerifierPort.verifyToken(validToken)).thenReturn(true);


        assertTrue(tokenVerificationService.verifyToken(validToken), "Token should be valid");
    }

    @Test
    void verifyTokenShouldReturnFalseForInvalidToken() {

        String invalidToken = "invalidToken";
        when(tokenVerifierPort.verifyToken(invalidToken)).thenReturn(false);


        assertFalse(tokenVerificationService.verifyToken(invalidToken), "Token should be invalid");
    }
}
