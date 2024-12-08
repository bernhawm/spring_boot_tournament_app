package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "matches")
@Getter
@Setter
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Match ID

    @ManyToOne
    @JoinColumn(name = "round_id", nullable = false)
    private Rounds round; // Associated Round

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament; // Associated Tournament

    @Column(nullable = false)
    private Long player1Id; // Player 1

    @Column(nullable = false)
    private Long player2Id; // Player 2

    @Column(nullable = false)
    private String result; // Result (e.g., "2-1", "0-2")

    @Column(nullable = true)
    private Long winnerId; // ID of the player who won
}
