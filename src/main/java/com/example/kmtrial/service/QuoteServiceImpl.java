package com.example.kmtrial.service;

import com.example.kmtrial.model.Quote;
import com.example.kmtrial.model.UserEntity;
import com.example.kmtrial.model.Vote;
import com.example.kmtrial.repository.QuoteRepository;
import com.example.kmtrial.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final VoteRepository voteRepository;

    @Override
    public Quote addQuote(Quote quote, UserEntity postedBy) {
        quote.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        quote.setPostedBy(postedBy);
        return quoteRepository.save(quote);
    }

    @Override
    public Quote getRandomQuote() {
        List<Quote> quotes = quoteRepository.findAll();
        return quotes.get(new Random().nextInt(0, quotes.size())); // very inefficient but ok as a stub
    }

    @Override
    public Quote updateQuote(Quote quote) {
        Quote persistedQuote = quoteRepository.findById(quote.getId()).orElseThrow();
        persistedQuote.setContent(quote.getContent());
        persistedQuote.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        quoteRepository.save(persistedQuote);
        return persistedQuote;
    }

    @Override
    public void deleteQuote(UUID id) {
        quoteRepository.deleteById(id);
    }

    @Override
    public List<Quote> getWorstQuotes() {
        return quoteRepository.findFirst10ByOrderByVoteCountAsc();
    }

    @Override
    public List<Quote> getTopQuotes() {
        return quoteRepository.findFirst10ByOrderByVoteCountDesc();
    }

    @Override
    public Quote vote(Quote quote, UserEntity postedBy, boolean isUpvote) {
        Quote persistedQuote = quoteRepository.findById(quote.getId()).orElseThrow();

        Vote vote = voteRepository.findByRelatedQuoteIdAndPostedBy(quote.getId(), postedBy).orElse(new Vote());
        if (vote.getCreatedAt() == null) {
            vote.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            vote.setPostedBy(postedBy);
            vote.setRelatedQuoteId(persistedQuote.getId());
            persistedQuote.setVoteCount(persistedQuote.getVoteCount() + (isUpvote ? 1 : -1));
        } else {
            if (isUpvote != vote.isUpvote()) {
                persistedQuote.setVoteCount(persistedQuote.getVoteCount() + (isUpvote ? 2 : -2));
            }
        }
        vote.setUpvote(isUpvote);
        voteRepository.save(vote);

        return quoteRepository.save(persistedQuote);
    }

    @Override
    public List<Vote> getVoteStats(UUID quoteId) {
        return voteRepository.findAllByRelatedQuoteId(quoteId);
    }

}
