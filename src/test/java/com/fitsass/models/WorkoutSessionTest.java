package com.fitsass.models;

import com.fitsass.enums.FitnessGoal;
import com.fitsass.enums.MuscleGroup;
import com.fitsass.enums.WorkoutType;
import com.fitsass.loader.ExerciseLoader;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkoutSessionTest extends TestCase {

    private WorkoutSession workoutSession;
    private UserPreference userPreference;
    private List<Exercise> exercises;

    public void setUp() {
        // Setup a default UserPreference
        String simulatedInput = "John\n2\n1\n4\n0\n5\n75\n1.80\n1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(in);

        userPreference = new UserPreference(scanner);
        userPreference.setExperienceLevel(3);
        userPreference.setGoal(FitnessGoal.GAIN_MUSCLE);
        userPreference.setWeeklyWorkoutFrequency(4);

        // Create a basic workout session
        workoutSession = new WorkoutSession("Day 1", WorkoutType.WEIGHTLIFTING, List.of(MuscleGroup.CHEST, MuscleGroup.BACK));

        ExerciseLoader loader = new ExerciseLoader();
        exercises = loader.loadExercises();
    }
    public void testAddExercise() {
        // Add an exercise and check if it has been added
        Exercise exo = exercises.getFirst();
        workoutSession.addExercise(exo);

        assertEquals(1, workoutSession.getExercises().size());
        assertEquals("Squat", workoutSession.getExercises().getFirst().getName());
    }

    public void testAdjustDifficulty() {
        // Adjust difficulty based on user preferences
        workoutSession.adjustDifficulty(userPreference);
        assertEquals(1, workoutSession.getDifficulty());
    }

    public void testGenerateSession() {
        // Generate a session and ensure exercises are added
        workoutSession.generateSession(1, exercises, userPreference);

        assertEquals(2, workoutSession.getExercises().size());
    }

    public void testFullBodySession() {
        // Setup a Full Body session
        workoutSession = new WorkoutSession("Full Body Day", WorkoutType.WEIGHTLIFTING, List.of(MuscleGroup.FULL_BODY));

        int numberOfExercises = 1;
        workoutSession.generateSession(numberOfExercises, exercises, userPreference);

        int expected = MuscleGroup.FULL_BODY.getMuscles().size() * numberOfExercises;

        assertEquals(expected, workoutSession.getExercises().size());
    }
}
