package com.fitsass;

import com.fitsass.models.UserPreference;
import com.fitsass.models.WorkoutPlan;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        UserPreference userPreference = new UserPreference(scanner);
        WorkoutPlan workoutPlan = new WorkoutPlan(userPreference);
        workoutPlan.generateWorkoutPlan();

    }
}
