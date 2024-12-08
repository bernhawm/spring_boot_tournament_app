package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "rounds")
@Getter
@Setter
public class Rounds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Round ID

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;


    @Column(nullable = true)
    private LocalDateTime StartTime; // Starttime date


    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> matches; // Matches in this round



}
