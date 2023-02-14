package com.example.kmtrial.service;

import com.example.kmtrial.model.Quote;
import com.example.kmtrial.model.UserEntity;
import com.example.kmtrial.model.Vote;

import java.util.List;
import java.util.UUID;

public interface QuoteService {
    Quote addQuote(Quote quote, UserEntity postedBy);

    Quote getRandomQuote();

    Quote updateQuote(Quote quote);

    void deleteQuote(UUID id);

    List<Quote> getWorstQuotes();

    List<Quote> getTopQuotes();

    Quote vote(Quote quote, UserEntity postedBy, boolean isUpvote);

    List<Vote> getVoteStats(UUID quoteId);
}
