package com.fitsass.models;

import com.fitsass.enums.MuscleGroup;

public class Exercise {
    private String name;
    private int sets;
    private int reps;
    private String duration;
    private String difficulty;
    private MuscleGroup mainMuscleGroup;

    public String getName() {
        return name;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public MuscleGroup getMainMuscleGroup() {
        return mainMuscleGroup;
    }

    public void printExercise() {
        System.out.println("--------------------");
        System.out.println("Exercise:   " + name);
        System.out.println("Sets:       " + sets);
        System.out.println("Reps:       " + reps);
        System.out.println("Duration:   " + duration + " minutes");
        System.out.println("Difficulty: " + difficulty);
        System.out.println("Muscle Group: " + mainMuscleGroup);
    }
}
