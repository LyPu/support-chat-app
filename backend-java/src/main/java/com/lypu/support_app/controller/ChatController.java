package com.lypu.support_app.controller;

import org.springframework.web.bind.annotation.*;
import com.lypu.support_app.service.OpenAIService;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class ChatController {

    private final OpenAIService openAIService;

    public ChatController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/chat")
    public String chatGet() {
        return "Use POST method to interact with this endpoint.";
    }

    @PostMapping("/chat")
    public Map<String, String> chat(@RequestBody Map<String, String> request) {
        String message = request.get("message");

        try {
            String reply = openAIService.chat(message);
            return Map.of("reply", reply);
        } catch (Exception e) {
            return Map.of("reply", "Error: " + e.getMessage());
        }
    }
}

