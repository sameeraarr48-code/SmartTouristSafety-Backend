package com.smarttourist.safety.service;

import org.springframework.stereotype.Service;

@Service
public class AIService {

    public String classifyIncident(String description) {

        if (description == null || description.isBlank()) {
            return "MEDIUM";
        }

        String text = description.toLowerCase();

        if (text.contains("life-threatening")
                || text.contains("unconscious")
                || text.contains("severe bleeding")
                || text.contains("trapped")
                || text.contains("fire")
                || text.contains("accident")
                || text.contains("heart attack")
                || text.contains("emergency")) {
            return "HIGH";
        }

        if (text.contains("injury")
                || text.contains("lost")
                || text.contains("theft")
                || text.contains("unsafe")
                || text.contains("harassment")
                || text.contains("missing")) {
            return "MEDIUM";
        }

        return "LOW";
    }
}