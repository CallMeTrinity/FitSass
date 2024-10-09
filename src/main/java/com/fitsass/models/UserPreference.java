package com.fitsass.models;

import com.fitsass.enums.FitnessGoal;
import com.fitsass.enums.PhysicalLimitations;
import com.fitsass.enums.WorkoutType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserPreference {
    private String name;
    private int experienceLevel;

    private int weeklyWorkoutFrequency;
    private FitnessGoal goal;
    private WorkoutType exercisePreference;

    private List<PhysicalLimitations> physicalLimitations;

    private int currentWeight;

    private double height;


    public UserPreference() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter your name: ");
        name = scanner.nextLine();


        experienceLevel = getValidIntInput(scanner, "Enter your experience level in years (0-10): ", 0, 10);


        goal = getValidGoal(scanner);


        weeklyWorkoutFrequency = getValidIntInput(scanner, "Enter your desired weekly workout frequency (1-7): ", 1, 7);


        exercisePreference = getValidExercisePreference(scanner);


        System.out.println("Do you have any physical limitations (Knee pain, Back issues, etc.)? If none, enter 'None': ");
        physicalLimitations = getValidPhysicalLimitations(scanner);


        currentWeight = getValidIntInput(scanner, "Enter your current weight (in kg): ", 30, 300);
        height = getValidDoubleInput(scanner, "Enter your height (in meters): ", 1.2, 2.5);
    }


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


    private WorkoutType getValidExercisePreference(Scanner scanner) {
        WorkoutType preference = null;
        while (preference == null) {
            System.out.println("Enter your preferred type of exercise: [WEIGHTLIFTING(W), CARDIO(C), YOGA(Y), GENERAL(G)]");

            try {
                String input = scanner.nextLine().toUpperCase();
                switch (input) {
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

    public List<PhysicalLimitations> getValidPhysicalLimitations(Scanner scanner) {
        List<PhysicalLimitations> selectedLimitations = new ArrayList<>();
        PhysicalLimitations[] limitations = PhysicalLimitations.values();

        while (true) {
            System.out.println("Select your physical limitations (you can choose multiple). Enter numbers separated by commas (e.g., 1,3,5). Enter 0 if you have no limitations:");

            for (int i = 0; i < limitations.length; i++) {
                System.out.println("[" + (i) + "] " + limitations[i].name() + " - " + limitations[i].getDescription());
            }

            try {
                String input = scanner.nextLine();
                if (input.equals("0")) {
                    System.out.println("No physical limitations selected.");
                    return selectedLimitations;

                }
                String[] inputs = input.split(",");
                for (String limit : inputs) {
                    int limitationIndex = Integer.parseInt(limit.trim());


                    if (limitationIndex >= 0 && limitationIndex < limitations.length) {
                        PhysicalLimitations selected = limitations[limitationIndex];


                        if (!selectedLimitations.contains(selected)) {
                            selectedLimitations.add(selected);
                        }
                    } else {
                        System.out.println("Invalid input: " + limit + ". Please select a valid number.");
                    }
                }

                System.out.println("Selected limitations: ");
                for (PhysicalLimitations lim : selectedLimitations) {
                    System.out.println("- " + lim.getDescription());
                }
                return selectedLimitations;

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers separated by commas.");
            }
        }
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

    public List<PhysicalLimitations> getPhysicalLimitations() {
        return physicalLimitations;
    }

    public void setPhysicalLimitations(List<PhysicalLimitations> physicalLimitations) {
        this.physicalLimitations = physicalLimitations;
    }
}
