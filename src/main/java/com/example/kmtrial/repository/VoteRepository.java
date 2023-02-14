package com.example.kmtrial.repository;

import com.example.kmtrial.model.UserEntity;
import com.example.kmtrial.model.Vote;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VoteRepository extends CrudRepository<Vote, UUID> {
    List<Vote> findAllByRelatedQuoteId(UUID relatedQuoteId);
    Optional<Vote> findByRelatedQuoteIdAndPostedBy(UUID relatedQuoteId, UserEntity postedBy);
}
