package com.fitsass.dto;

import com.fitsass.enums.Muscle;

public class ExerciseDTO {
    private String name;
    private int sets;
    private int reps;
    private String duration;
    private int difficulty;
    private Muscle specificity;
    private double intensity;

    public ExerciseDTO() {
    }

    public ExerciseDTO(String name, int sets, int reps, String duration, int difficulty, Muscle specificity, double intensity) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.duration = duration;
        this.difficulty = difficulty;
        this.specificity = specificity;
        this.intensity = intensity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public Muscle getSpecificity() {
        return specificity;
    }

    public void setSpecificity(Muscle specificity) {
        this.specificity = specificity;
    }

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }
}
