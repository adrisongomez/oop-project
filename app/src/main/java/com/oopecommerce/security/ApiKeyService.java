package com.oopecommerce.security;

import org.springframework.stereotype.Component;

@Component
public class ApiKeyService {
    private final String apiKey = System.getenv().getOrDefault("API_KEY", "");

    public boolean isValid(String provided) {
        return provided != null && !apiKey.isEmpty() && apiKey.equals(provided);
    }
}
