package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchResultRequest {
    private String result; // Match result (e.g., "2-1")
    private Long winnerId; // ID of the winner
}
