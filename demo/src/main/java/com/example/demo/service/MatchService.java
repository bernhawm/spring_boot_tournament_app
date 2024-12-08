package com.example.demo.service;

import com.example.demo.entity.Match;
import com.example.demo.entity.Rounds;
import com.example.demo.entity.Tournament;
import com.example.demo.repository.MatchRepository;
import com.example.demo.repository.RoundRepository;
import org.springframework.stereotype.Service;
import com.example.demo.repository.TournamentRepository; // Import the TournamentRepository

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final RoundRepository roundRepository;
    private final TournamentRepository tournamentRepository; // Add TournamentRepository field

    // Inject TournamentRepository through the constructor
    public MatchService(MatchRepository matchRepository, RoundRepository roundRepository, TournamentRepository tournamentRepository) {
        this.matchRepository = matchRepository;
        this.roundRepository = roundRepository;
        this.tournamentRepository = tournamentRepository; // Initialize the tournamentRepository
    }

    // Create a match
    public Match createMatch(Long roundId, Long player1Id, Long player2Id, Long tournamentId) {
        // Fetch the round
        Rounds round = roundRepository.findById(roundId)
            .orElseThrow(() -> new RuntimeException("Round not found"));

        // Fetch the tournament
        Tournament tournament = tournamentRepository.findById(tournamentId)
            .orElseThrow(() -> new RuntimeException("Tournament not found"));

        // Create a new match
        Match match = new Match();
        match.setRound(round);
        match.setTournament(tournament); // Set the tournament
        match.setPlayer1Id(player1Id);
        match.setPlayer2Id(player2Id);
        match.setResult("Pending"); // Set initial result as "Pending"
        match.setWinnerId(null);   // No winner yet

        // Save and return the match
        return matchRepository.save(match);
    }

    // Record match result
    public Match recordMatchResult(Long matchId, String result, Long winnerId) {
        Match match = matchRepository.findById(matchId)
            .orElseThrow(() -> new RuntimeException("Match not found."));

        match.setResult(result);
        match.setWinnerId(winnerId);

        return matchRepository.save(match); // Save updated match
    }
}
