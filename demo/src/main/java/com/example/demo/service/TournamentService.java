package com.example.demo.service;

import com.example.demo.entity.Tournament;
import com.example.demo.entity.TournamentPlayer;
import com.example.demo.repository.TournamentPlayerRepository;
import com.example.demo.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentPlayerRepository tournamentPlayerRepository;

    public TournamentService(TournamentRepository tournamentRepository, TournamentPlayerRepository tournamentPlayerRepository) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentPlayerRepository = tournamentPlayerRepository;
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
}
