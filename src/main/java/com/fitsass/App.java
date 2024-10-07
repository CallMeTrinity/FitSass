package com.fitsass;

import com.fitsass.enums.MuscleGroup;
import com.fitsass.enums.WorkoutType;
import com.fitsass.loader.ExerciseLoader;
import com.fitsass.models.Exercise;
import com.fitsass.models.WorkoutSession;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {

        ExerciseLoader loader = new ExerciseLoader();
        List<Exercise> exercises = loader.loadExercises("./src/main/resources/exercices.json");


        WorkoutSession workoutSession = new WorkoutSession("Upper", WorkoutType.WEIGHTLIFTING);
        workoutSession.generateSession(List.of(MuscleGroup.CHEST, MuscleGroup.BACK), 2, exercises);
        workoutSession.printWorkoutSession();
    }
}
