package com.fitsass.models;

import com.fitsass.enums.MuscleGroup;
import com.fitsass.enums.WorkoutType;
import com.fitsass.loader.ExerciseLoader;
import com.fitsass.loader.SplitLoader;

import java.util.*;

public class WorkoutPlan {
    private final int frequency;
    private final Split split;
    private final UserPreference userPreference;
    List<WorkoutSession> session;

    public WorkoutPlan(UserPreference userPreference) {
        this.frequency = userPreference.getWeeklyWorkoutFrequency();
        session = new ArrayList<>();
        this.userPreference = userPreference;
        split = getSplitFromFrequency();

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
        List<Split> matchingSplits = new ArrayList<>();
        List<Split> splits = new SplitLoader().loadSplits();
        for (Split split : splits) {
            if (split.getDays() == frequency && split.getWorkoutType().equals(userPreference.getExercisePreference())) {
                matchingSplits.add(split);
            }
        }

        if (matchingSplits.size() == 1) {
            return matchingSplits.getFirst();
        }

        if (matchingSplits.isEmpty()) {
            return generateCustomSplit(userPreference);
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

    private Split generateCustomSplit(UserPreference userPreference) {
        String name = "Custom Split for " + userPreference.getName();
        String description = "Custom split generated for " + userPreference.getExercisePreference() + " with a frequency of " + frequency + " days.";
        String workoutType = userPreference.getExercisePreference().toString();

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
        ExerciseLoader loader = new ExerciseLoader();
        List<Exercise> exercises = loader.loadExercises();

        for (int i = 1; i <= frequency; i++) {
            List<MuscleGroup> muscleGroupsForDay = split.getMuscleGroupsPerDay().get(i);
            WorkoutSession workoutSession = new WorkoutSession("Day " + i, userPreference.getExercisePreference(), muscleGroupsForDay);

            if (muscleGroupsForDay != null && !muscleGroupsForDay.isEmpty()) {
                workoutSession.generateSession(userPreference.getAverageExercisePerMuscleGroup(), exercises, userPreference);
            } else {
                System.out.println("No muscle groups defined for Day " + i);
            }

            addSession(workoutSession);
        }

        printWorkoutPlan();
    }
}
