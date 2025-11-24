package com.fitsass.models;

import com.fitsass.enums.FitnessGoal;
import com.fitsass.enums.PhysicalLimitations;
import com.fitsass.enums.WorkoutType;

import java.util.List;

public class UserPreferenceData {
    private String name;
    private int experienceLevel;
    private int weeklyWorkoutFrequency;
    private FitnessGoal goal;
    private WorkoutType exercisePreference;
    private List<PhysicalLimitations> physicalLimitations;
    private int currentWeight;
    private double height;

    public UserPreferenceData() {
    }

    public UserPreferenceData(String name, int experienceLevel, int weeklyWorkoutFrequency,
                              FitnessGoal goal, WorkoutType exercisePreference,
                              List<PhysicalLimitations> physicalLimitations,
                              int currentWeight, double height) {
        this.name = name;
        this.experienceLevel = experienceLevel;
        this.weeklyWorkoutFrequency = weeklyWorkoutFrequency;
        this.goal = goal;
        this.exercisePreference = exercisePreference;
        this.physicalLimitations = physicalLimitations;
        this.currentWeight = currentWeight;
        this.height = height;
    }

    public double calculateBMI() {
        if (height > 0) {
            return currentWeight / (height * height);
        }
        return 0;
    }

    public int getAverageExercisePerMuscleGroup() {
        int baseNumber = 2;

        if (experienceLevel > 2) baseNumber += 1;
        if (experienceLevel > 4) baseNumber += 1;

        if (weeklyWorkoutFrequency <= 2) baseNumber += 2;
        else if (weeklyWorkoutFrequency <= 4) baseNumber += 1;

        switch (goal) {
            case GAIN_MUSCLE, LOSE_WEIGHT, MAINGAIN -> baseNumber += 1;
            case FLEXIBILITY, ENDURANCE -> baseNumber -= 1;
        }

        return Math.min(baseNumber, 5);
    }

    // Getters and setters
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
