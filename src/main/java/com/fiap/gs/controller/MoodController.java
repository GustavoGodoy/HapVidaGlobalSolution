package com.fiap.gs.controller;

import com.fiap.gs.model.Mood;
import com.fiap.gs.service.MoodService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/mood")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MoodController {

    @Autowired
    MoodService moodService;

    @PostMapping
    public ResponseEntity createMood(@RequestBody Mood mood){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(moodService.createMood(mood));
    }

    @GetMapping
    public ResponseEntity getAllModByUser(@Param("user") String user){
        return moodService.findAllBylUser(user).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/yesterday")
    public ResponseEntity getYesterdayMood(@Param("user") String user){
        return moodService.findYesterdayMood(user).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

}
