package com.demo.springdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${openai.api.base-url}")
    private String openaiApiUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(openaiApiUrl)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
