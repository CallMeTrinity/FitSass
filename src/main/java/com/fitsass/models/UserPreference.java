package com.fitsass.models;

import com.fitsass.enums.FitnessGoal;
import com.fitsass.enums.WorkoutType;

import java.util.Scanner;

public class UserPreference {
    private String name;
    private int experienceLevel; // out of 10
    private int weeklyWorkoutFrequency;
    private FitnessGoal goal;
    private String recoveryLevel; // New: "Low", "Medium", "High"
    private WorkoutType exercisePreference; // New: "Weightlifting", "Cardio", etc.
    private String physicalLimitations; // New: Physical issues or limitations
    private int currentWeight; // New: User's weight in kg
    private double height; // New: User's height in meters

    // New: Method to calculate BMI
    public double calculateBMI() {
        if (height > 0) {
            return currentWeight / (height * height);
        }
        return 0;
    }

    public void printUserPreference() {
        System.out.println("Name: " + name);
        System.out.println("Experience Level: " + experienceLevel);
        System.out.println("Weekly Workout Frequency: " + weeklyWorkoutFrequency);
        System.out.println("Goal: " + goal.getDescription());
        System.out.println("Recovery Level: " + recoveryLevel);
        System.out.println("Exercise Preference: " + exercisePreference);
        System.out.println("Physical Limitations: " + physicalLimitations);
        System.out.println("Current Weight: " + currentWeight + " kg");
        System.out.println("Height: " + height + " m");
        System.out.println("Calculated BMI: " + calculateBMI());
    }

    public void newUserPreference() {
        Scanner scanner = new Scanner(System.in);

        // Collect basic details
        System.out.println("Enter your name: ");
        name = scanner.nextLine();

        // Collecting experience level
        experienceLevel = getValidIntInput(scanner, "Enter your experience level in years (0-10): ", 0, 10);

        // Select fitness goal
        goal = getValidGoal(scanner);

        // Collecting weekly workout frequency
        weeklyWorkoutFrequency = getValidIntInput(scanner, "Enter your desired weekly workout frequency (1-7): ", 1, 7);

        // Collecting recovery level
        System.out.println("How would you rate your recovery level (Low, Medium, High)? ");
        recoveryLevel = scanner.nextLine();

        // Collecting exercise preference
        exercisePreference = getValidExercisePreference(scanner);

        // Collecting physical limitations
        System.out.println("Do you have any physical limitations (Knee pain, Back issues, etc.)? If none, enter 'None': ");
        physicalLimitations = scanner.nextLine();

        // Collecting current weight and height
        currentWeight = getValidIntInput(scanner, "Enter your current weight (in kg): ", 30, 300);
        height = getValidDoubleInput(scanner, "Enter your height (in meters): ", 1.2, 2.5);
    }

    // Helper method to validate integer input
    private int getValidIntInput(Scanner scanner, String prompt, int min, int max) {
        int input;
        while (true) {
            System.out.println(prompt);
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    break;
                } else {
                    System.out.println("Please enter a value between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return input;
    }

    // Helper method to validate double input
    private double getValidDoubleInput(Scanner scanner, String prompt, double min, double max) {
        double input;
        while (true) {
            System.out.println(prompt);
            try {
                input = Double.parseDouble(scanner.nextLine());
                if (input >= min && input <= max) {
                    break;
                } else {
                    System.out.println("Please enter a value between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a decimal number.");
            }
        }
        return input;
    }

    // Method to get a valid fitness goal
    private FitnessGoal getValidGoal(Scanner scanner) {
        FitnessGoal fitnessGoal = null;
        while (fitnessGoal == null) {
            System.out.println("Enter your goal: [0] GAIN_MUSCLE, [1] LOSE_FAT, [2] MAINGAIN, [3] FLEXIBILITY");
            try {
                int goalInput = Integer.parseInt(scanner.nextLine());
                switch (goalInput) {
                    case 0 -> fitnessGoal = FitnessGoal.GAIN_MUSCLE;
                    case 1 -> fitnessGoal = FitnessGoal.LOSE_WEIGHT;
                    case 2 -> fitnessGoal = FitnessGoal.MAINGAIN;
                    case 3 -> fitnessGoal = FitnessGoal.FLEXIBILITY;
                    default -> System.out.println("Please select a valid goal (0-3).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return fitnessGoal;
    }

    // Method to get a valid exercise preference
    private WorkoutType getValidExercisePreference(Scanner scanner) {
        WorkoutType preference = null;
        while (preference == null) {
            System.out.println("Enter your preferred type of exercise: [WEIGHTLIFTING, CARDIO, YOGA, MIX]");
            try {
                String input = scanner.nextLine().toUpperCase();
                preference = WorkoutType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter one of the valid options.");
            }
        }
        return preference;
    }

    // getter and setter methods
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecoveryLevel() {
        return recoveryLevel;
    }

    public void setRecoveryLevel(String recoveryLevel) {
        this.recoveryLevel = recoveryLevel;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public WorkoutType getExercisePreference() {
        return exercisePreference;
    }

    public void setExercisePreference(WorkoutType exercisePreference) {
        this.exercisePreference = exercisePreference;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getPhysicalLimitations() {
        return physicalLimitations;
    }

    public void setPhysicalLimitations(String physicalLimitations) {
        this.physicalLimitations = physicalLimitations;
    }
}
