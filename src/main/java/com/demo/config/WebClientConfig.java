package com.demo.config;

import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${openai.api.url}")
    private String openaiApiUrl;

    @Bean
    public WebClient openaiWebClient() {
        return WebClient.builder()
                .baseUrl(openaiApiUrl)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
