package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tournaments")
@Getter
@Setter
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Tournament ID

    @Column(nullable = true)
    private Integer rounds;

    // @ElementCollection
    // @CollectionTable(name = "tournament_players", joinColumns = @JoinColumn(name = "tournament_id"))
    // @Column(name = "player_id")
    // private List<Long> playerIds; // Player IDs

    @Lob
    private String standings; // JSON or serialized standings data

    @Column(nullable = false)
    private LocalDateTime date; // Tournament date

    @Column(nullable = false, length = 50)
    private String format; // Tournament format

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRounds() {
        return rounds;
    }

    public void setRounds(Integer rounds) {
        this.rounds = rounds;
    }

    // public List<Long> getPlayerIds() {
    //     return playerIds;
    // }

    // public void setPlayerIds(List<Long> playerIds) {
    //     this.playerIds = playerIds;
    // }

    public String getStandings() {
        return standings;
    }

    public void setStandings(String standings) {
        this.standings = standings;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
