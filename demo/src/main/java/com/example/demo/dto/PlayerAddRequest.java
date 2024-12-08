package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PlayerAddRequest {
    private Long tournamentId;
    private Long playerId;

    // Getters and Setters
    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
}
