package com.prashant.ai_dev_platform.controller;

import com.prashant.ai_dev_platform.dto.AiRequest;
import com.prashant.ai_dev_platform.dto.AiResponse;
import com.prashant.ai_dev_platform.service.AiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "http://localhost:3000")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/review")
    public AiResponse review(@RequestBody AiRequest request) {
        return new AiResponse(aiService.reviewCode(request.getInput()));
    }

    @PostMapping("/testcases")
    public AiResponse testcases(@RequestBody AiRequest request) {
        return new AiResponse(aiService.generateTestCases(request.getInput()));
    }

    @PostMapping("/aws")
    public AiResponse aws(@RequestBody AiRequest request) {
        return new AiResponse(aiService.recommendAwsArchitecture(request.getInput()));
    }
}