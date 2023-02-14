package com.example.kmtrial.repository;

import com.example.kmtrial.model.Quote;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface QuoteRepository extends CrudRepository<Quote, UUID> {
    List<Quote> findAll();
    List<Quote> findFirst10ByOrderByVoteCountAsc();
    List<Quote> findFirst10ByOrderByVoteCountDesc();
}
