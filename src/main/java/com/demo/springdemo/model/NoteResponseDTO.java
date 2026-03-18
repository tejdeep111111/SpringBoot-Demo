package com.demo.springdemo.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NoteResponseDTO { //DTO for sending note data back to the client
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
