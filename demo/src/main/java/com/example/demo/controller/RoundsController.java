package com.example.demo.controller;

import com.example.demo.entity.Rounds;
import com.example.demo.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rounds")
public class RoundsController {

    @Autowired
    private RoundRepository roundRepository;

    @GetMapping
    public List<Rounds> getAllRounds() {
        return roundRepository.findAll();
    }

    @GetMapping("/{id}")
    public Rounds getRoundById(@PathVariable Long id) {
        return roundRepository.findById(id).orElseThrow(() -> new RuntimeException("Round not found"));
    }

    @PostMapping
    public Rounds createRound(@RequestBody Rounds round) {
        return roundRepository.save(round);
    }
}
