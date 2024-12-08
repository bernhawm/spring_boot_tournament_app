package com.example.demo.repository;

import com.example.demo.entity.Rounds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundRepository extends JpaRepository<Rounds, Long> {
}