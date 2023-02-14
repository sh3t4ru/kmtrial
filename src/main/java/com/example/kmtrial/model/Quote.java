package com.example.kmtrial.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    @ManyToOne
    private UserEntity postedBy;
    private long voteCount;
}
