package com.oopecommerce.security;

import java.util.List;
import java.util.UUID;

public class UserData {
    private UUID id;
    private String email;
    private List<String> claims;

    public UserData(UUID id, String email, List<String> claims) {
        this.id = id;
        this.email = email;
        this.claims = claims;
    }

    public UUID getId() { return id; }
    public String getEmail() { return email; }
    public List<String> getClaims() { return claims; }
}
