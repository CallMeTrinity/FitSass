package com.fitsass.models;

import com.fitsass.enums.MuscleGroup;
import com.fitsass.enums.WorkoutType;
import com.fitsass.loader.ExerciseLoader;

import java.util.List;

public class WorkoutSession {
    private String name;
    private WorkoutType type;
    private List<Exercise> exercise;

    public WorkoutSession(String name, WorkoutType type, List<Exercise> exercise) {
        this.name = name;
        this.type = type;
        this.exercise = exercise;
    }

    public void addExercise(Exercise exercise) {
        this.exercise.add(exercise);
    }

    public void printWorkoutSession() {
        System.out.println(this.name + " : " + type);
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

    public void generateSession(List<Exercise> exercises,List<MuscleGroup> muscleGroups, int numberOfExercises) {
        for (MuscleGroup muscleGroup : muscleGroups) {
            for (int i = 0; i < numberOfExercises; i++) {
                for (Exercise exercise : exercises) {
                    if (exercise.getMainMuscleGroup().equals(muscleGroup)) {
                        addExercise(exercise);
                    }
                }
            }
        }
    }
}
