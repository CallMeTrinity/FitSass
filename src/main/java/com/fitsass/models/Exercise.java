package com.fitsass.models;

import com.fitsass.enums.Muscle;
import com.fitsass.enums.MuscleGroup;
import com.fitsass.enums.PhysicalLimitations;
import com.fitsass.enums.WorkoutType;

import java.util.List;

public class Exercise {
    private String name;
    private int sets;
    private int reps;
    private String duration;
    private int difficulty;
    private MuscleGroup mainMuscleGroup;
    private Muscle specificity;
    private WorkoutType type;
    private List<PhysicalLimitations> limitations;
    private double intensity;
    private int importance;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
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

    public void setMainMuscleGroup(MuscleGroup mainMuscleGroup) {
        this.mainMuscleGroup = mainMuscleGroup;
    }

    public Muscle getSpecificity() {
        return specificity;
    }

    public void setSpecificity(Muscle specificity) {
        this.specificity = specificity;
    }

    public void printExercise() {
        System.out.println("--------------------");
        System.out.println("Exercise:   " + name);
        System.out.println("Muscle:     " + specificity);
        System.out.println("Sets:       " + sets);
        System.out.println("Reps:       " + reps);
        System.out.println("Duration:   " + duration + " minutes");
        System.out.println("Intensity:  " + intensity);
        System.out.println("Difficulty: " + (difficulty == 0 ? "Beginner" : difficulty == 1 ? "Intermediate" : "Advanced"));
    }

    public WorkoutType getType() {
        return type;
    }

    public void setType(WorkoutType type) {
        this.type = type;
    }

    public List<PhysicalLimitations> getLimitations() {
        return limitations;
    }

    public void setLimitations(List<PhysicalLimitations> limitations) {
        this.limitations = limitations;
    }

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
