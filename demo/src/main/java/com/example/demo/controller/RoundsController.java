package com.example.demo.controller;

import com.example.demo.dto.RoundRequest;
import com.example.demo.entity.Rounds;
import com.example.demo.repository.RoundRepository;
import com.example.demo.service.RoundService;  // Make sure to import the RoundService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rounds")
public class RoundsController {

    @Autowired
    private RoundRepository roundRepository;
    @Autowired
    private RoundService roundService;  // Autowire RoundService
    
    @GetMapping
    public List<Rounds> getAllRounds() {
        return roundRepository.findAll();
    }

    @GetMapping("/{id}")
    public Rounds getRoundById(@PathVariable Long id) {
        return roundRepository.findById(id).orElseThrow(() -> new RuntimeException("Round not found"));
    }

@PostMapping("/{tournamentId}/create-round")
public ResponseEntity<String> createRound(@PathVariable Long tournamentId, @RequestBody RoundRequest roundRequest) {
    Rounds newRound = roundService.createRound(tournamentId, roundRequest.getStartTime());
    return ResponseEntity.ok("Round created with ID: " + newRound.getId());
}

}
