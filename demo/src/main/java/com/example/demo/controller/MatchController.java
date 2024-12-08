package com.example.demo.controller;

import com.example.demo.entity.Match;
import com.example.demo.entity.Rounds;
import com.example.demo.entity.Tournament;
import com.example.demo.repository.MatchRepository;
import com.example.demo.repository.RoundRepository; // Add this import
import com.example.demo.repository.TournamentRepository;
import com.example.demo.service.MatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private RoundRepository roundRepository; // Autowire RoundRepository

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @GetMapping("/{id}")
    public Match getMatchById(@PathVariable Long id) {
        return matchRepository.findById(id).orElseThrow(() -> new RuntimeException("Match not found"));
    }

    // POST request with JSON body (if you prefer this method)
    @PostMapping("/create")
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        // Fetch the tournament from the database
        Tournament tournament = tournamentRepository.findById(match.getTournament().getId())
                .orElseThrow(() -> new RuntimeException("Tournament not found"));

        // Fetch the round from the database using match.getRound().getId()
        Rounds round = roundRepository.findById(match.getRound().getId())
                .orElseThrow(() -> new RuntimeException("Round not found"));

        // Call the MatchService to create the match
        Match createdMatch = matchService.createMatch(round.getId(), match.getPlayer1Id(), match.getPlayer2Id(), tournament.getId());

        return ResponseEntity.ok(createdMatch);
    }

    // POST request with request params (if you prefer this method)
    
    // @PostMapping("/create")
    // public ResponseEntity<Match> createMatch(
    //         @RequestParam Long roundId,
    //         @RequestParam Long player1Id,
    //         @RequestParam Long player2Id,
    //         @RequestParam Long tournamentId) {

    //     // Fetch the tournament from the database
    //     Tournament tournament = tournamentRepository.findById(tournamentId)
    //             .orElseThrow(() -> new RuntimeException("Tournament not found"));

    //     // Call the MatchService to create the match
    //     Match match = matchService.createMatch(roundId, player1Id, player2Id, tournamentId);

    //     return ResponseEntity.ok(match);
    // }
    

    @PostMapping("/{matchId}/result")
    public ResponseEntity<Match> recordMatchResult(
            @PathVariable Long matchId,
            @RequestParam String result,
            @RequestParam Long winnerId) {
        Match match = matchService.recordMatchResult(matchId, result, winnerId);
        return ResponseEntity.ok(match);
    }
}
