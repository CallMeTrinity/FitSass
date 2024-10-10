package com.fitsass.models;

import com.fitsass.enums.MuscleGroup;
import com.fitsass.enums.WorkoutType;

import java.util.List;
import java.util.Map;

public class Split {
    private String name;
    private String description;
    private int days;
    private WorkoutType workoutType; // WEIGHTLIFTING, CARDIO, etc.
    private Map<Integer, List<MuscleGroup>> muscleGroupsPerDay;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public WorkoutType getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(WorkoutType workoutType) {
        this.workoutType = workoutType;
    }

    public Map<Integer, List<MuscleGroup>> getMuscleGroupsPerDay() {
        return muscleGroupsPerDay;
    }

    public void setMuscleGroupsPerDay(Map<Integer,List<MuscleGroup>> muscleGroupsPerDay) {
        this.muscleGroupsPerDay = muscleGroupsPerDay;
    }
}
