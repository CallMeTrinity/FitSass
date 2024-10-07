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

        ExerciseLoader loader = new ExerciseLoader();
        List<Exercise> exercises = loader.loadExercises("./src/main/resources/exercices.json");

        assert exercises != null;

        WorkoutSession workoutSession = new WorkoutSession("Chest and Back", WorkoutType.WEIGHTLIFTING);
        workoutSession.generateSession(List.of(MuscleGroup.CHEST, MuscleGroup.BACK), 2, exercises);
    }
}
