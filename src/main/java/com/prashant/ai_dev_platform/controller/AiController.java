package com.prashant.ai_dev_platform.controller;

import com.prashant.ai_dev_platform.service.GeminiService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final GeminiService geminiService;

    public AiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/review")
    public Map<String, String> review(@RequestBody Map<String, String> request) {
        try {
            String input = request.get("input");

            if (input == null || input.isBlank()) {
                return Map.of("result", "Please provide valid code input.");
            }

            String result = geminiService.generateContent(input);
            return Map.of("result", result);

        } catch (Exception e) {
            return Map.of("result", "AI service failed: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/review", method = RequestMethod.OPTIONS)
    public void handleOptions() {
        // Browser preflight support
    }
}