package com.prashant.ai_dev_platform.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://generativelanguage.googleapis.com")
            .build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String generateContent(String prompt) {
        try {
            Map<String, Object> requestBody = Map.of(
                    "contents", new Object[]{
                            Map.of(
                                    "parts", new Object[]{
                                            Map.of("text", prompt)
                                    }
                            )
                    }
            );

            String rawResponse = webClient.post()
                    .uri("/v1beta/models/gemini-2.5-flash-lite:generateContent?key=" + apiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .timeout(Duration.ofSeconds(30))
                    .onErrorResume(e ->
                            Mono.just("{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"Gemini timeout or API issue\"}]}}]"))
                    .block();

            JsonNode root = objectMapper.readTree(rawResponse);

            return root.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

        } catch (Exception e) {
            return "AI service failed: " + e.getMessage();
        }
    }
}