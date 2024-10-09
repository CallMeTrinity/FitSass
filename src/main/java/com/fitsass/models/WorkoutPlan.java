package com.fitsass.models;

import com.fitsass.enums.MuscleGroup;
import com.fitsass.enums.Split;
import com.fitsass.enums.WorkoutType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkoutPlan {
    private final int frequency;
    private final Split split;
    private final UserPreference userPreference;
    List<WorkoutSession> session;

    public WorkoutPlan(int frequency, UserPreference userPreference) {
        this.frequency = frequency;
        session = new ArrayList<>();
        split = getSplitFromFrequency();
        this.userPreference = userPreference;

    }

    public void printWorkoutPlan() {
        System.out.println("GENERATING WORKOUT PLAN FOR " + userPreference.getName());
        for (WorkoutSession workoutSession : session) {
            workoutSession.printWorkoutSession();
        }
    }

    public void addSession(WorkoutSession workoutSession) {
        session.add(workoutSession);
    }

    public Split getSplitFromFrequency() {
        List<Split> splits = List.of(Split.values());

        List<Split> matchingSplits = new ArrayList<>();
        for (Split split : splits) {
            if (split.getDays() == frequency) {
                matchingSplits.add(split);
            }
        }

        if (matchingSplits.size() == 1) {
            return matchingSplits.getFirst();
        }

        if (matchingSplits.isEmpty()) {
            return null;
        }

        System.out.println("Multiple workout splits are available for the frequency of " + frequency + " days. Please choose one:");

        for (int i = 0; i < matchingSplits.size(); i++) {
            System.out.println((i + 1) + ": " + matchingSplits.get(i).toString() + " " + matchingSplits.get(i).getDescription());
        }

        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice < 1 || choice > matchingSplits.size()) {
            System.out.print("Enter the number of your choice: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next();
            }
        }

        return matchingSplits.get(choice - 1);
    }


    public void generateWorkoutPlan(List<Exercise> exercises) {
        if (split == null) {
            System.out.println("No workout split available for this frequency: " + frequency);
            return;
        }

        for (int i = 1; i <= frequency; i++) {
            List<MuscleGroup> muscleGroupsForDay = split.getMuscleGroupsPerDay().get(i);
            WorkoutSession workoutSession = new WorkoutSession("Day " + i, userPreference.getExercisePreference(), muscleGroupsForDay);
            if (muscleGroupsForDay != null && !muscleGroupsForDay.isEmpty()) {
                workoutSession.generateSession(3, exercises, userPreference);
            } else {
                System.out.println("No muscle groups defined for Day " + i);
            }

            addSession(workoutSession);
        }

        printWorkoutPlan();
    }
}
