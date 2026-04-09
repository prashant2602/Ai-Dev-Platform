package com.prashant.ai_dev_platform.service;

import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final GeminiService geminiService;

    public AiService(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    public String reviewCode(String code) {
        String prompt = """
                You are a senior software engineer.
                Review the following code and provide:
                1. Code smells
                2. Performance issues
                3. Security risks
                4. Better implementation suggestions
                
                Code:
                """ + code;

        return geminiService.generateContent(prompt);
    }

    public String generateTestCases(String code) {
        String prompt = """
                Generate JUnit 5 + Mockito test cases for the following Java code.
                Include edge cases and validation scenarios.
                
                Code:
                """ + code;

        return geminiService.generateContent(prompt);
    }

    public String recommendAwsArchitecture(String requirement) {
        String prompt = """
                Suggest AWS architecture for the following system.
                Include:
                1. Compute
                2. Database
                3. Storage
                4. Caching
                5. Monitoring
                6. Scaling
                
                Requirement:
                """ + requirement;

        return geminiService.generateContent(prompt);
    }
}