package com.fiap.gs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_mood")
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private int moodLevel;

    @NotNull
    @Size(max = 1000)
    private String notes;

    @NotNull
    public String user;

    private String emotions;

    private int heartRate;
    private int vfc;
    private double sleepHours;
    private int activityLevel;

    private Instant timestamp;

    public Mood(int moodLevel, String notes, String emotions) {
        this.moodLevel = moodLevel;
        this.notes = notes;
        this.emotions = emotions;
    }
}
