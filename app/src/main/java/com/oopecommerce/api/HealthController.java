package com.oopecommerce.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oopecommerce.utils.logging.RequestLogger;

@RestController
public class HealthController {

    @Autowired
    private RequestLogger logger;

    @GetMapping("/checkhealth")
    public Map<String, String> checkHealth() {
        logger.info("Health check endpoint reached");
        return Map.of("status", "ok");
    }
}
