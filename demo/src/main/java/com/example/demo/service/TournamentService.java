package com.example.demo.service;

import com.example.demo.entity.Rounds;
import com.example.demo.entity.Tournament;
import com.example.demo.entity.TournamentPlayer;
import com.example.demo.repository.TournamentPlayerRepository;
import com.example.demo.repository.TournamentRepository;
import com.example.demo.repository.RoundRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentPlayerRepository tournamentPlayerRepository;
    private final RoundRepository roundRepository; // Add this field

    public TournamentService(
        TournamentRepository tournamentRepository,
        TournamentPlayerRepository tournamentPlayerRepository,
        RoundRepository roundRepository // Include it in the constructor
    ) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentPlayerRepository = tournamentPlayerRepository;
        this.roundRepository = roundRepository;
    }

    // Create a new tournament
    public Tournament createTournament(String format, LocalDateTime date, Integer rounds) {
        Tournament tournament = new Tournament();
        tournament.setFormat(format);
        tournament.setDate(date);
        tournament.setRounds(rounds);
        // tournament.setPlayerIds(new ArrayList<>()); // Initialize empty player list
        tournament.setStandings(null); // Start with null standings
        return tournamentRepository.save(tournament);
    }
    
    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }
    public Tournament addPlayerToTournament(Long tournamentId, Long playerId) {
    // Check if the player is already in the tournament
    if (tournamentPlayerRepository.existsByTournamentIdAndPlayerId(tournamentId, playerId)) {
        throw new RuntimeException("Player is already in the tournament.");
    }
        // Fetch the tournament
    Tournament tournament = tournamentRepository.findById(tournamentId)
    .orElseThrow(() -> new RuntimeException("Tournament not found."));

        // Add the player to the tournament
        TournamentPlayer tournamentPlayer = new TournamentPlayer();
        tournamentPlayer.setTournament(tournament); // Set the Tournament object
        tournamentPlayer.setPlayerId(playerId);
        tournamentPlayerRepository.save(tournamentPlayer);
    


        Optional<Tournament> optionalTournament = tournamentRepository.findById(tournamentId);
        if (optionalTournament.isEmpty()) {
            throw new RuntimeException("Tournament not found.");
        }

        return optionalTournament.get();
    }
    public void removePlayerFromTournament(Long tournamentId, Long playerId) {
        // Check if the player exists in the tournament
        TournamentPlayer tournamentPlayer = tournamentPlayerRepository
            .findByTournamentIdAndPlayerId(tournamentId, playerId)
            .orElseThrow(() -> new RuntimeException("Player not found in the tournament."));
    
        // Mark the player as no longer in the tournament
        tournamentPlayer.setIsInTournament(false);
        tournamentPlayerRepository.save(tournamentPlayer);
    }
    
    public Rounds createRound(Long tournamentId, LocalDateTime startTime) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
            .orElseThrow(() -> new RuntimeException("Tournament not found."));
    
        Rounds round = new Rounds();
        round.setTournament(tournament);
        round.setStartTime(startTime);
    
        return roundRepository.save(round);
    }



}
