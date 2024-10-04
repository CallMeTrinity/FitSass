package com.fitsass.models;

public class Exercise {
    private final String name;
    private final int sets;
    private int reps;
    private String duration;
    private final String difficulty;

    public Exercise(String name, int sets, int reps, String difficulty) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.difficulty = difficulty;
    }

    public Exercise(String name, int sets, String duration, String difficulty) {
        this.name = name;
        this.sets = sets;
        this.duration = duration;
        this.difficulty = difficulty;
    }

    public void printExercise() {
        System.out.println("--------------------");
        System.out.println("Exercise:   " + name);
        System.out.println("Sets:       " + sets);
        System.out.println("Reps:       " + reps);
        System.out.println("Duration:   " + duration + " minutes");
        System.out.println("Difficulty: " + difficulty);
    }


}
