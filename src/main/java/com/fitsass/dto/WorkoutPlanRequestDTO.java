package com.fitsass.dto;

import com.fitsass.enums.FitnessGoal;
import com.fitsass.enums.PhysicalLimitations;
import com.fitsass.enums.WorkoutType;
import jakarta.validation.constraints.*;

import java.util.List;

public class WorkoutPlanRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 0, message = "Experience level must be between 0 and 10")
    @Max(value = 10, message = "Experience level must be between 0 and 10")
    private int experienceLevel;

    @Min(value = 1, message = "Weekly workout frequency must be between 1 and 7")
    @Max(value = 7, message = "Weekly workout frequency must be between 1 and 7")
    private int weeklyWorkoutFrequency;

    @NotNull(message = "Fitness goal is required")
    private FitnessGoal goal;

    @NotNull(message = "Exercise preference is required")
    private WorkoutType exercisePreference;

    private List<PhysicalLimitations> physicalLimitations;

    @Min(value = 30, message = "Weight must be between 30 and 300 kg")
    @Max(value = 300, message = "Weight must be between 30 and 300 kg")
    private int currentWeight;

    @DecimalMin(value = "1.2", message = "Height must be between 1.2 and 2.5 meters")
    @DecimalMax(value = "2.5", message = "Height must be between 1.2 and 2.5 meters")
    private double height;

    public WorkoutPlanRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(int experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public int getWeeklyWorkoutFrequency() {
        return weeklyWorkoutFrequency;
    }

    public void setWeeklyWorkoutFrequency(int weeklyWorkoutFrequency) {
        this.weeklyWorkoutFrequency = weeklyWorkoutFrequency;
    }

    public FitnessGoal getGoal() {
        return goal;
    }

    public void setGoal(FitnessGoal goal) {
        this.goal = goal;
    }

    public WorkoutType getExercisePreference() {
        return exercisePreference;
    }

    public void setExercisePreference(WorkoutType exercisePreference) {
        this.exercisePreference = exercisePreference;
    }

    public List<PhysicalLimitations> getPhysicalLimitations() {
        return physicalLimitations;
    }

    public void setPhysicalLimitations(List<PhysicalLimitations> physicalLimitations) {
        this.physicalLimitations = physicalLimitations;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
