package com.fitsass.models;

import java.util.List;

public class WorkoutPlan {
    List<WorkoutSession> session;
    private String difficulty;

    public WorkoutPlan(List<WorkoutSession> session) {
        this.session = session;
    }

    public void adjustDifficulty(UserPreference userPreference) {
        userPreference.getExperienceLevel();

        if (userPreference.getExperienceLevel() < 1) {
            difficulty = "Beginner";
        } else if (userPreference.getExperienceLevel() < 3) {
            difficulty = "Intermediate";
        } else {
            difficulty = "Advanced";
        }
    }

    public void printWorkoutPlan() {
        for (WorkoutSession workoutSession : session) {
            System.out.println("Workout Plan Difficulty: " + difficulty);
            System.out.println(workoutSession);
        }
    }

    public void addSession(WorkoutSession workoutSession) {
        session.add(workoutSession);
    }
}
