package com.lypu.support_app.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class OpenAIServiceTest {

    @Test
    void testChatReturnsResponse() throws Exception {
        // Mock HttpClient
        HttpClient mockClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);

        String fakeJson = """
        {
          "output": [
            {
              "content": [
                { "text": "Hello from AI" }
              ]
            }
          ]
        }
        """;

        Mockito.when(mockResponse.body()).thenReturn(fakeJson);
        Mockito.when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        OpenAIService service = new OpenAIService(mockClient);

        String result = service.chat("hello");

        assertEquals("Hello from AI", result);
    }
}
