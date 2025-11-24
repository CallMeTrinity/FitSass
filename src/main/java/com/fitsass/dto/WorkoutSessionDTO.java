package com.fitsass.dto;

import com.fitsass.enums.MuscleGroup;

import java.util.List;

public class WorkoutSessionDTO {
    private String name;
    private List<MuscleGroup> muscleGroups;
    private List<ExerciseDTO> exercises;

    public WorkoutSessionDTO() {
    }

    public WorkoutSessionDTO(String name, List<MuscleGroup> muscleGroups, List<ExerciseDTO> exercises) {
        this.name = name;
        this.muscleGroups = muscleGroups;
        this.exercises = exercises;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MuscleGroup> getMuscleGroups() {
        return muscleGroups;
    }

    public void setMuscleGroups(List<MuscleGroup> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public List<ExerciseDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseDTO> exercises) {
        this.exercises = exercises;
    }
}
