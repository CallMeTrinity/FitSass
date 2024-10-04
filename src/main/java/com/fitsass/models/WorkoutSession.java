package com.fitsass.models;

import com.fitsass.enums.WorkoutType;

import java.util.List;

public class WorkoutSession {
    private WorkoutType type;
    private List<Exercise> exercise;

    public WorkoutSession(WorkoutType type, List<Exercise> exercise) {
        this.type = type;
        this.exercise = exercise;
    }

    public void addExercise(Exercise exercise) {
        this.exercise.add(exercise);
    }

    public void printWorkoutSession() {
        System.out.println("Workout session type: " + type);
        for (Exercise exercise : exercise) {
            exercise.printExercise();
        }
    }

    public WorkoutType getType() {
        return type;
    }

    public void setType(WorkoutType type) {
        this.type = type;
    }
}
