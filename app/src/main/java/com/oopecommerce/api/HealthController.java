package com.oopecommerce.api;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/checkhealth")
    public Map<String, String> checkHealth() {
        return Map.of("status", "ok");
    }
}
