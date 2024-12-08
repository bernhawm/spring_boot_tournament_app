package com.example.demo.service;

import com.example.demo.entity.Match;
import com.example.demo.entity.Rounds;
import com.example.demo.entity.Tournament;
import com.example.demo.repository.RoundRepository;
import com.example.demo.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RoundService {

    private final RoundRepository roundRepository;
    private final TournamentRepository tournamentRepository;

    public RoundService(RoundRepository roundRepository, TournamentRepository tournamentRepository) {
        this.roundRepository = roundRepository;
        this.tournamentRepository = tournamentRepository;
    }

    // Create a round
    public Rounds createRound(Long tournamentId, LocalDateTime startTime) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
            .orElseThrow(() -> new RuntimeException("Tournament not found."));

        // Calculate the round number based on the existing rounds for the tournament
        long roundNumber = roundRepository.countByTournamentId(tournamentId) + 1;  // Use long here

        Rounds round = new Rounds();
        round.setTournament(tournament); // This should work now.
        round.setRoundNumber((int) roundNumber); // If you need to store it as an int, cast here
        round.setStartTime(startTime);

        return roundRepository.save(round);
    }
}
