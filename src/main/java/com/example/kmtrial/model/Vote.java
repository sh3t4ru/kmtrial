package com.example.kmtrial.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private boolean upvote;
    private Timestamp createdAt;
    @ManyToOne
    private UserEntity postedBy;
    private UUID relatedQuoteId;
}
