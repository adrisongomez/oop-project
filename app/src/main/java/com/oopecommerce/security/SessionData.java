package com.oopecommerce.security;

import java.time.Instant;
import java.util.UUID;

public class SessionData {
    private UUID userId;
    private String ip;
    private Instant issuedAt;

    public SessionData(UUID userId, String ip, Instant issuedAt) {
        this.userId = userId;
        this.ip = ip;
        this.issuedAt = issuedAt;
    }

    public UUID getUserId() { return userId; }
    public String getIp() { return ip; }
    public Instant getIssuedAt() { return issuedAt; }
}
