package com.fitsass.services;


import com.fitsass.models.UserPreference;
import com.fitsass.models.WorkoutPlan;

import java.util.Scanner;

public class FitnessService {

    public FitnessService() {
        Scanner scanner = new Scanner(System.in);
        UserPreference userPreference = new UserPreference(scanner);
        WorkoutPlan workoutPlan = new WorkoutPlan(userPreference);
        workoutPlan.generateWorkoutPlan();
    }


}
