package com.fitsass;

import com.fitsass.loader.ExerciseLoader;
import com.fitsass.models.Exercise;
import com.fitsass.models.WorkoutPlan;

import java.util.List;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your workout frequency: ");
        int frequency = scanner.nextInt();
        ExerciseLoader loader = new ExerciseLoader();
        List<Exercise> exercises = loader.loadExercises("./src/main/resources/exercices.json");


        WorkoutPlan workoutPlan = new WorkoutPlan(frequency);
        workoutPlan.generateWorkoutPlan(exercises);

    }
}
