package com.example.demo.controller;

import com.example.demo.dto.PlayerAddRequest;
import com.example.demo.entity.Tournament;
import com.example.demo.service.TournamentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    // Endpoint to create a new tournament
    // Endpoint to create a new tournament
    @PostMapping("/create")
    public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament) {
        Tournament createdTournament = tournamentService.createTournament(tournament);
        return ResponseEntity.ok(createdTournament);
    }

    // Endpoint to add a player to a tournament
    @PostMapping("/{tournamentId}/addPlayer")
    public ResponseEntity<Tournament> addPlayerToTournament(
            @PathVariable Long tournamentId,
            @RequestBody PlayerAddRequest playerAddRequest) {

        if (!tournamentId.equals(playerAddRequest.getTournamentId())) {
            return ResponseEntity.badRequest().body(null);
        }

        Tournament tournament = tournamentService.addPlayerToTournament(
                playerAddRequest.getTournamentId(), playerAddRequest.getPlayerId());
        return ResponseEntity.ok(tournament);
    }

}
