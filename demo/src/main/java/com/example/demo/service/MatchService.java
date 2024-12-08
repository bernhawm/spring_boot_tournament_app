package com.example.demo.service;

import com.example.demo.entity.Match;
import com.example.demo.entity.Rounds;
import com.example.demo.entity.Tournament;
import com.example.demo.entity.TournamentPlayer;
import com.example.demo.repository.MatchRepository;
import com.example.demo.repository.RoundRepository;
import com.example.demo.repository.TournamentRepository;
import com.example.demo.repository.TournamentPlayerRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final RoundRepository roundRepository;
    private final TournamentRepository tournamentRepository;
    private final TournamentPlayerRepository tournamentPlayerRepository;

    public MatchService(
            MatchRepository matchRepository,
            RoundRepository roundRepository,
            TournamentRepository tournamentRepository,
            TournamentPlayerRepository tournamentPlayerRepository) {
        this.matchRepository = matchRepository;
        this.roundRepository = roundRepository;
        this.tournamentRepository = tournamentRepository;
        this.tournamentPlayerRepository = tournamentPlayerRepository;
    }

    // Create matches for a new round
     // Create matches for a new round
     public void createMatchesForNewRound(Long tournamentId, Long roundId) {
        // Fetch the round
        Rounds round = roundRepository.findById(roundId)
                .orElseThrow(() -> new RuntimeException("Round not found"));

        // Fetch the tournament
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));

        // Fetch all players in the tournament
        List<TournamentPlayer> players = tournamentPlayerRepository.findByTournamentId(tournamentId);

        // Sort players by points in descending order
        players.sort((p1, p2) -> Integer.compare(p2.getPoints(), p1.getPoints()));

        // Pair players and create matches
        for (int i = 0; i < players.size() - 1; i += 2) {
            TournamentPlayer player1 = players.get(i);
            TournamentPlayer player2 = players.get(i + 1);

            Match match = new Match();
            match.setRound(round);
            match.setTournament(tournament);
            match.setPlayer1Id(player1.getPlayerId());
            match.setPlayer2Id(player2.getPlayerId());
            match.setResult("Pending");
            match.setWinnerId(null);

            matchRepository.save(match);  // Save match to the database
        }

        // Handle odd number of players
        if (players.size() % 2 != 0) {
            TournamentPlayer lastPlayer = players.get(players.size() - 1);

            // Create a match for the player with no opponent
            Match byeMatch = new Match();
            byeMatch.setRound(round);
            byeMatch.setTournament(tournament);
            byeMatch.setPlayer1Id(lastPlayer.getPlayerId());
            byeMatch.setPlayer2Id(null);  // No opponent for bye
            byeMatch.setResult("Bye");
            byeMatch.setWinnerId(lastPlayer.getPlayerId());

            // Grant points for the bye match
            lastPlayer.setPoints(lastPlayer.getPoints() + 3); // Award points for the bye
            tournamentPlayerRepository.save(lastPlayer);  // Save updated player points

            matchRepository.save(byeMatch);  // Save bye match
        }
    }
    // Record match result
    public Match recordMatchResult(Long matchId, String result, Long winnerId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found"));
    
        match.setResult(result);
        match.setWinnerId(winnerId);
    
        // Update points for the winner
        TournamentPlayer winner = tournamentPlayerRepository.findByPlayerIdAndTournamentId(winnerId, match.getTournament().getId())
                .orElseThrow(() -> new RuntimeException("Winner not found in tournament"));
    
        // Ensure points is initialized
        if (winner.getPoints() == null) {
            winner.setPoints(0); // Default points value
        }
    
        winner.setPoints(winner.getPoints() + 3); // Example: 3 points for a win
        tournamentPlayerRepository.save(winner);
    
        matchRepository.save(match);
        return match;
    }
    
}
