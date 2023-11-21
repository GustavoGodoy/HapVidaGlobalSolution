package com.fiap.gs.service;

import com.fiap.gs.model.Mood;
import com.fiap.gs.repository.MoodRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoodService {

    @Autowired
    MoodRepository moodRepository;


    public Mood createMood(Mood mood){
        return moodRepository.save(mood);
    }


    public Optional<List<Mood>> findAllBylUser(String user){
        return moodRepository.findAllByUser(user);
    }


    public Optional<Mood> findYesterdayMood(String user){
        Instant now = Instant.now();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, ZoneId.systemDefault());
        LocalDateTime yesterdayLocalDate = localDateTime.minusDays(1)
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(0);

        Instant yesterday = yesterdayLocalDate.atZone(ZoneId.systemDefault()).toInstant();

        return moodRepository.findFirstByUserAndTimestampIsBefore(user, yesterday);
    }

}
