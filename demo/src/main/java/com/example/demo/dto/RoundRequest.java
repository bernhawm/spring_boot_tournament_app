package com.example.demo.dto;



import java.time.LocalDateTime;

public class RoundRequest {
    private LocalDateTime startTime;

    // Getters and Setters
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
