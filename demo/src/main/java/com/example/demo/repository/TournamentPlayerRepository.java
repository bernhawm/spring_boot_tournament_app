package com.example.demo.repository;

import com.example.demo.entity.TournamentPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;


@Repository
public interface TournamentPlayerRepository extends JpaRepository<TournamentPlayer, Long> {
    boolean existsByTournamentIdAndPlayerId(Long tournamentId, Long playerId);
    Optional<TournamentPlayer> findByTournamentIdAndPlayerId(Long tournamentId, Long playerId);
    List<TournamentPlayer> findByTournamentId(Long tournamentId);
    Optional<TournamentPlayer> findByPlayerIdAndTournamentId(Long playerId, Long tournamentId);

}
