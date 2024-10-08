package com.fitsass;

import com.fitsass.loader.ExerciseLoader;
import com.fitsass.models.Exercise;
import com.fitsass.models.UserPreference;
import com.fitsass.models.WorkoutPlan;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {

        UserPreference userPreference = new UserPreference();
        userPreference.newUserPreference();
        System.out.println(userPreference.calculateBMI());

        System.exit(0);

        ExerciseLoader loader = new ExerciseLoader();
        List<Exercise> exercises = loader.loadExercises("./src/main/resources/exercices.json");


        WorkoutPlan workoutPlan = new WorkoutPlan(userPreference.getWeeklyWorkoutFrequency());
        workoutPlan.generateWorkoutPlan(exercises);

    }
}
