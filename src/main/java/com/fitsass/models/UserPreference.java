package com.fitsass.models;


import com.fitsass.enums.FitnessGoal;

import java.util.Scanner;

public class UserPreference {
    private String name;
    private int experienceLevel; // out of 10
    private int weeklyWorkoutFrequency;
    private FitnessGoal goal;

    public void printUserPreference() {
        System.out.println("Name: " + name);
        System.out.println("Experience Level: " + experienceLevel);
        System.out.println("Weekly Workout Frequency: " + weeklyWorkoutFrequency);
        System.out.println("Goal: " + goal.getDescription());
    }

    public void newUserPreference() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your your experience level in years: ");
        int experience = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter your goal: [0]GAIN_MUSCLE, [1]LOSE_FAT, [2]MAINTAIN, [3]FLEXIBILITY");

        int goal = -1;
        FitnessGoal fitnessGoal = null;
        while (goal < 0 || goal > 3) {
            goal = Integer.parseInt(scanner.nextLine());
        }
        fitnessGoal = switch (goal) {
            case (0) -> FitnessGoal.GAIN_MUSCLE;
            case (1) -> FitnessGoal.LOSE_WEIGHT;
            case (2) -> FitnessGoal.MAINTAIN_WEIGHT;
            case (3) -> FitnessGoal.FLEXIBILITY;
            default -> fitnessGoal;
        };

        System.out.println("Enter your desired weekly workout frequency: ");
        int weeklyWorkoutFrequency = Integer.parseInt(scanner.nextLine());

        this.name = name;
        this.experienceLevel = experience;
        this.weeklyWorkoutFrequency = weeklyWorkoutFrequency;
        this.goal = fitnessGoal;
    }
// getter and setter methods
    public int getExperienceLevel() {
        return experienceLevel;
    }

    public int getWeeklyWorkoutFrequency() {
        return weeklyWorkoutFrequency;
    }

    public FitnessGoal getGoal() {
        return goal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExperienceLevel(int experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public void setGoal(FitnessGoal goal) {
        this.goal = goal;
    }

    public void setWeeklyWorkoutFrequency(int weeklyWorkoutFrequency) {
        this.weeklyWorkoutFrequency = weeklyWorkoutFrequency;
    }
}
