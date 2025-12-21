package com.anup.receipt_analytics.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    Logger LOGGER = LoggerFactory.getLogger(HealthController.class);

    @GetMapping
    public Map<String, String> health() {

        LOGGER.info("health API called ....");
        return Map.of(
                "status", "UP",
                "service", "receipt-analytics"
        );
    }
}
