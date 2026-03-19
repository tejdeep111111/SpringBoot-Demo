package com.demo.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class OpenAiService {
    private final WebClient webClient;

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    public OpenAiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String summarize(String content) {
        Map<String, Object> requestBody = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of("role", "system", "content", "You are a helpful assistant that summarizes text."),
                        Map.of("role", "user", "content", "Summarize this note:\n\n" + content)
                ), "max_tokens", 200
        );

        Map response = webClient.post()
                .uri("chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        List choices = (List) response.get("choices");
        Map choice = (Map) choices.get(0);
        Map message = (Map) choice.get("message");
        return (String) message.get("content");
    }
}
