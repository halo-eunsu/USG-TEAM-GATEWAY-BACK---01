package com.usg.gateway.application.config;

import com.usg.gateway.domain.port.TokenVerifierPort;
import com.usg.gateway.application.service.TokenVerificationService;
import com.usg.gateway.infrastructure.security.JwtTokenVerifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public TokenVerifierPort tokenVerifierPort() {
        return new JwtTokenVerifier("secret_key");
    }

    @Bean
    public TokenVerificationService tokenVerificationService(TokenVerifierPort tokenVerifierPort) {
        return new TokenVerificationService(tokenVerifierPort);
    }
}
