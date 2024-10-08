package com.fitsass.models;

import com.fitsass.enums.FitnessGoal;
import com.fitsass.enums.PhysicalLimitations;
import com.fitsass.enums.WorkoutType;

import java.util.Arrays;
import java.util.Scanner;

public class UserPreference {
    private String name;
    private int experienceLevel; // out of 10
    private int weeklyWorkoutFrequency;
    private FitnessGoal goal;
    private WorkoutType exercisePreference; // New: "Weightlifting", "Cardio", etc.
    private PhysicalLimitations physicalLimitations; // New: Physical issues or limitations
    private int currentWeight; // New: User's weight in kg
    private double height; // New: User's height in meters

    public UserPreference() {
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

        // Collecting exercise preference
        exercisePreference = getValidExercisePreference(scanner);

        // Collecting physical limitations
        System.out.println("Do you have any physical limitations (Knee pain, Back issues, etc.)? If none, enter 'None': ");
        physicalLimitations = getValidPhysicalLimitation(scanner);

        // Collecting current weight and height
        currentWeight = getValidIntInput(scanner, "Enter your current weight (in kg): ", 30, 300);
        height = getValidDoubleInput(scanner, "Enter your height (in meters): ", 1.2, 2.5);
    }

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
        System.out.println("Exercise Preference: " + exercisePreference);
        System.out.println("Physical Limitations: " + physicalLimitations);
        System.out.println("Current Weight: " + currentWeight + " kg");
        System.out.println("Height: " + height + " m");
        System.out.println("Calculated BMI: " + calculateBMI());
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
            System.out.println("Enter your preferred type of exercise: [WEIGHTLIFTING(W), CARDIO(C), YOGA(Y), GENERAL(G)]");

            try {
                String input = scanner.nextLine().toUpperCase();
                switch (input){
                    case "W" -> preference = WorkoutType.WEIGHTLIFTING;
                    case "C" -> preference = WorkoutType.CARDIO;
                    case "Y" -> preference = WorkoutType.YOGA;
                    case "G" -> preference = WorkoutType.GENERAL;
                    default -> preference = WorkoutType.valueOf(input);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please enter one of the valid options.");
            }
        }
        return preference;
    }

    public PhysicalLimitations getValidPhysicalLimitation(Scanner scanner) {
        PhysicalLimitations limitation = null;
        PhysicalLimitations[] limitations = PhysicalLimitations.values();

        while (limitation == null) {
            // Afficher toutes les limitations disponibles avec leur numéro correspondant
            System.out.println("Enter your physical limitation: ");
            System.out.println("[0] None");

            // Afficher les options à partir de l'énumération PhysicalLimitations
            for (int i = 0; i < limitations.length; i++) {
                System.out.println("[" + (i + 1) + "] " + limitations[i].name() + " - " + limitations[i].getDescription());
            }

            try {
                // Obtenir l'entrée de l'utilisateur (0 pour "None", sinon la limitation sélectionnée)
                int limitationInput = getValidIntInput(scanner, "Enter the number corresponding to your limitation: ", 0, limitations.length);

                if (limitationInput == 0) {
                    limitation = null; // Aucun problème physique sélectionné
                    System.out.println("No physical limitations selected.");
                } else {
                    limitation = limitations[limitationInput - 1]; // Sélection de la limitation choisie
                    System.out.println("Selected limitation: " + limitation.getDescription());
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return limitation;
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

    public PhysicalLimitations getPhysicalLimitations() {
        return physicalLimitations;
    }

    public void setPhysicalLimitations(PhysicalLimitations physicalLimitations) {
        this.physicalLimitations = physicalLimitations;
    }
}
