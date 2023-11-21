package com.fiap.gs.repository;

import com.fiap.gs.model.Mood;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface MoodRepository extends JpaRepository<Mood,Long> {

    public Optional<List<Mood>> findAllByUser(String usuario);
    public Optional<Mood> findFirstByUserAndTimestampIsBefore(String user, Instant timestamp);
}
