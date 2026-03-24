package com.lypu.support_app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;  

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

@Service
public class OpenAIService {

    private final HttpClient client;

    @Value("${openai.api-key}")
    private String apiKey;

    public OpenAIService() {
        this.client = HttpClient.newHttpClient();
    }

    // for testing
    public OpenAIService(HttpClient client) {
        this.client = client;
    }

    public String chat(String message) throws Exception {
        String requestBody = """
        {
          "model": "gpt-4o-mini",
          "input": "%s"
        }
        """.formatted(message);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/responses"))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("RAW RESPONSE: " + response.body());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.body());

        return root
            .path("output")
            .get(0)
            .path("content")
            .get(0)
            .path("text")
            .asText();
    }
}
