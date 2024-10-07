package com.fitsass;

import com.fitsass.enums.MuscleGroup;
import com.fitsass.enums.WorkoutType;
import com.fitsass.loader.ExerciseLoader;
import com.fitsass.models.Exercise;
import com.fitsass.models.UserPreference;
import com.fitsass.models.WorkoutSession;
import com.fitsass.services.FitnessService;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       // UserPreference userPreference = new UserPreference();
        //userPreference.newUserPreference();

        //FitnessService fitnessService = new FitnessService(userPreference);

        //userPreference.printUserPreference();

        ExerciseLoader loader = new ExerciseLoader();
        List<Exercise> exercises = loader.loadExercises("./src/main/resources/exercices.json");

        assert exercises != null;
        List<Exercise> chestExercise = loader.loadMuscleGroupExercise(exercises, MuscleGroup.CHEST);


        WorkoutSession workoutSession = new WorkoutSession("Chest Day", WorkoutType.WEIGHTLIFTING, chestExercise);
        workoutSession.printWorkoutSession();

    }
}
