package com.fitsass.models;

import com.fitsass.enums.MuscleGroup;
import com.fitsass.enums.WorkoutType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkoutSession {
    private final String name;
    private final List<Exercise> exercises;
    private WorkoutType type;

    public WorkoutSession(String name, WorkoutType type) {
        this.name = name;
        this.type = type;
        this.exercises = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public void printWorkoutSession() {
        System.out.println(this.name + " : " + type);
        for (Exercise exercise : exercises) {
            exercise.printExercise();
        }
    }

    public WorkoutType getType() {
        return type;
    }

    public void setType(WorkoutType type) {
        this.type = type;
    }

    public void generateSession(List<MuscleGroup> muscleGroups, int numberOfExercises, List<Exercise> exercises) {
        for (MuscleGroup muscleGroup : muscleGroups) {
            List<Exercise> filteredExercises = new ArrayList<>();
            for (Exercise exercise : exercises) {
                if (exercise.getMainMuscleGroup().equals(muscleGroup)) {
                    filteredExercises.add(exercise);
                }
            }

            Collections.shuffle(filteredExercises);

            for (int i = 0; i < Math.min(numberOfExercises, filteredExercises.size()); i++) {
                Exercise selectedExercise = filteredExercises.get(i);
                System.out.println("Adding " + selectedExercise.getName() + " to " + this.name);
                addExercise(selectedExercise);
            }
        }
    }

}

