package com.fitsass.models;

import com.fitsass.enums.FitnessGoal;
import com.fitsass.enums.MuscleGroup;
import com.fitsass.enums.WorkoutType;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;


public class WorkoutPlanTest extends TestCase {

    private WorkoutPlan workoutPlan;
    private UserPreference userPreference;

    public void setUp() {
        // Simulate user inputs for UserPreference
        String simulatedInput = "John\n2\n1\n2\n0\n5\n75\n1.80\n1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(in);

        userPreference = new UserPreference(scanner);
        workoutPlan = new WorkoutPlan(userPreference);
    }

    public void testGenerateCustomSplit() {
        // Check if the custom split is generated with the correct data
        assertNotNull(workoutPlan.getSplitFromFrequency());
    }

    public void testAddSession() {
        // Create a workout session and add it to the workout plan
        WorkoutSession session = new WorkoutSession("Day 1", WorkoutType.WEIGHTLIFTING, List.of(MuscleGroup.CHEST));
        workoutPlan.addSession(session);

        assertEquals(1, workoutPlan.session.size());
    }

    public void testGenerateWorkoutPlan() {
        // Ensure that a workout plan is generated with the correct number of sessions
        workoutPlan.generateWorkoutPlan();
        assertEquals(2, workoutPlan.session.size()); // 4 days as per the frequency
    }

}
