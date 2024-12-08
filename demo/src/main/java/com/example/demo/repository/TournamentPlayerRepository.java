package com.example.demo.repository;

import com.example.demo.entity.TournamentPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentPlayerRepository extends JpaRepository<TournamentPlayer, Long> {
    boolean existsByTournamentIdAndPlayerId(Long tournamentId, Long playerId);
}
