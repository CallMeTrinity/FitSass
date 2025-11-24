package com.fitsass.models;

import com.fitsass.enums.MuscleGroup;
import com.fitsass.enums.WorkoutType;
import com.fitsass.loader.ExerciseLoader;
import com.fitsass.loader.SplitLoader;

import java.util.*;

public class WorkoutPlan {
    private final int frequency;
    private final Split split;
    private final UserPreferenceData userPreferenceData;
    List<WorkoutSession> session;

    public WorkoutPlan(UserPreference userPreference) {
        this(userPreference.toUserPreferenceData());
    }

    public WorkoutPlan(UserPreferenceData userPreferenceData) {
        this.frequency = userPreferenceData.getWeeklyWorkoutFrequency();
        session = new ArrayList<>();
        this.userPreferenceData = userPreferenceData;
        split = getSplitFromFrequency();
    }

    public void printWorkoutPlan() {
        System.out.println("GENERATING A "+ userPreferenceData.getWeeklyWorkoutFrequency() +" DAY WORKOUT PLAN FOR " + userPreferenceData.getName());
        for (WorkoutSession workoutSession : session) {
            workoutSession.printWorkoutSession();
        }
    }

    public void addSession(WorkoutSession workoutSession) {
        session.add(workoutSession);
    }

    public Split getSplitFromFrequency() {
        List<Split> matchingSplits = new ArrayList<>();
        List<Split> splits = new SplitLoader().loadSplits();
        for (Split split : splits) {
            if (split.getDays() == frequency && split.getWorkoutType().equals(userPreferenceData.getExercisePreference())) {
                matchingSplits.add(split);
            }
        }

        if (matchingSplits.size() == 1) {
            return matchingSplits.getFirst();
        }

        if (matchingSplits.isEmpty()) {
            return generateCustomSplit(userPreferenceData);
        }

        System.out.println("Multiple workout splits are available for the frequency of " + frequency + " days. Please choose one:");

        for (int i = 0; i < matchingSplits.size(); i++) {
            System.out.println((i + 1) + ": " + matchingSplits.get(i).getName() + " " + matchingSplits.get(i).getDescription());
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

    private Split generateCustomSplit(UserPreferenceData userPreferenceData) {
        String name = "Custom Split for " + userPreferenceData.getName();
        String description = "Custom split generated for " + userPreferenceData.getExercisePreference() + " with a frequency of " + frequency + " days.";
        String workoutType = userPreferenceData.getExercisePreference().toString();

        Map<Integer, List<MuscleGroup>> muscleGroupsPerDay = new HashMap<>();
        for (int i = 1; i <= frequency; i++) {
            muscleGroupsPerDay.put(i, List.of(MuscleGroup.FULL_BODY));
        }
        Split generatedCustomSplit = new Split();
        generatedCustomSplit.setName(name);
        generatedCustomSplit.setDescription(description);
        generatedCustomSplit.setDays(frequency);
        generatedCustomSplit.setWorkoutType(WorkoutType.valueOf(workoutType));
        generatedCustomSplit.setMuscleGroupsPerDay(muscleGroupsPerDay);

        return generatedCustomSplit;
    }


    public void generateWorkoutPlan() {
        generateWorkoutPlanWithoutPrint();
        printWorkoutPlan();
    }

    public void generateWorkoutPlanWithoutPrint() {
        ExerciseLoader loader = new ExerciseLoader();
        List<Exercise> exercises = loader.loadExercises();

        for (int i = 1; i <= frequency; i++) {
            List<MuscleGroup> muscleGroupsForDay = split.getMuscleGroupsPerDay().get(i);
            WorkoutSession workoutSession = new WorkoutSession("Day " + i, userPreferenceData.getExercisePreference(), muscleGroupsForDay);

            if (muscleGroupsForDay != null && !muscleGroupsForDay.isEmpty()) {
                workoutSession.generateSession(userPreferenceData.getAverageExercisePerMuscleGroup(), exercises, userPreferenceData);
            } else {
                System.out.println("No muscle groups defined for Day " + i);
            }

            addSession(workoutSession);
        }
    }

    public Split getSplit() {
        return split;
    }

    public UserPreferenceData getUserPreferenceData() {
        return userPreferenceData;
    }
}
