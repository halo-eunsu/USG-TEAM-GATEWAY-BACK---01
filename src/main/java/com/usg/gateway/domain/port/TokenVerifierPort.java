package com.usg.gateway.domain.port;

public interface TokenVerifierPort {
    boolean verifyToken(String token);
}
