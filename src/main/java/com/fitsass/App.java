package com.fitsass;

import com.fitsass.enums.WorkoutType;
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

        Exercise Bench = new Exercise("Bench Press", 3, 10, "Intermediate");
        Exercise Squat = new Exercise("Squat", 3, 10, "Intermediate");
        Exercise Deadlift = new Exercise("Deadlift", 3, 10, "Intermediate");

        List<Exercise> exercises = List.of(Bench, Squat, Deadlift);

        WorkoutSession workoutSession = new WorkoutSession(WorkoutType.WEIGHTLIFTING, exercises);
        workoutSession.printWorkoutSession();

    }
}
