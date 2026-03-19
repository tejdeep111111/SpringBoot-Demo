package com.demo.springdemo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
@Getter @Setter
@NoArgsConstructor //JPA requires a no-args constructor
@AllArgsConstructor
@Builder           //lets us do Note.builder().title("...").content("...").build()
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-incrementing ID
    private Long id;

    @Column(nullable = false) //title is required
    private String title;

    @Column(nullable = false) //content is required
    private String content;

    private LocalDateTime createdAt;

    @PrePersist  //called before the entity is saved for the first time
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
