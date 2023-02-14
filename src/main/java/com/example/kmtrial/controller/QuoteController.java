package com.example.kmtrial.controller;

import com.example.kmtrial.model.Quote;
import com.example.kmtrial.model.UserEntity;
import com.example.kmtrial.model.Vote;
import com.example.kmtrial.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    private UserEntity userStub;

    @PostMapping("/quote")
    public ResponseEntity<Quote> addQuote(@RequestBody Quote quote) {
        return ResponseEntity.ok().body(quoteService.addQuote(quote, userStub));
    }

    @GetMapping("/quote/random")
    public ResponseEntity<Quote> getRandomQuote() {
        return ResponseEntity.ok().body(quoteService.getRandomQuote());
    }

    @GetMapping("/quote/best")
    public ResponseEntity<List<Quote>> getTopQuotes() {
        return ResponseEntity.ok().body(quoteService.getTopQuotes());
    }

    @GetMapping("/quote/worst")
    public ResponseEntity<List<Quote>> getWorstQuotes() {
        return ResponseEntity.ok().body(quoteService.getWorstQuotes());
    }

    @PutMapping("/quote")
    public ResponseEntity<Quote> updateQuote(@RequestBody Quote quote) {
        return ResponseEntity.ok().body(quoteService.updateQuote(quote));
    }

    @DeleteMapping("/quote/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable UUID id) {
        quoteService.deleteQuote(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/quote/upvote")
    public ResponseEntity<Quote> upvote(@RequestBody Quote quote) {
        return ResponseEntity.ok().body(quoteService.vote(quote, userStub, true));
    }

    @PostMapping("/quote/downvote")
    public ResponseEntity<Quote> downvote(@RequestBody Quote quote) {
        return ResponseEntity.ok().body(quoteService.vote(quote, userStub, false));
    }

    @GetMapping("/quote/getVoteStats")
    public ResponseEntity<List<Vote>> getVoteStats(@RequestParam UUID quoteId) {
        return ResponseEntity.ok().body(quoteService.getVoteStats(quoteId));
    }
}
