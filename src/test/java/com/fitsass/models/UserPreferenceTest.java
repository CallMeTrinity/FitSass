package com.fitsass.models;

import com.fitsass.enums.FitnessGoal;
import com.fitsass.enums.PhysicalLimitations;
import com.fitsass.enums.WorkoutType;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class UserPreferenceTest extends TestCase {

    private UserPreference userPreference;

    public void testUserPreferenceCreation() {
        // Simuler l'entrée utilisateur
        String simulatedInput = "John\n2\n1\n4\n0\n5\n75\n1.80\n1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(in);

        UserPreference userPreference = new UserPreference(scanner);

        // Vérifier les valeurs obtenues
        assertEquals("John", userPreference.getName());
        assertEquals(2, userPreference.getExperienceLevel());
        assertEquals(FitnessGoal.GAIN_MUSCLE, userPreference.getGoal());
        assertEquals(4, userPreference.getWeeklyWorkoutFrequency());
        assertEquals(List.of(PhysicalLimitations.WRIST_PAIN), userPreference.getPhysicalLimitations());
        assertEquals(WorkoutType.WEIGHTLIFTING, userPreference.getExercisePreference());
        assertEquals(75, userPreference.getCurrentWeight());
        assertEquals(1.80, userPreference.getHeight());
    }

    public void testGeneratedExercisePerMuscleGroup() {
        String simulatedInput = "John\n2\n1\n4\n0\n5\n75\n1.80\n1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(in);

        UserPreference userPreference = new UserPreference(scanner);

        assertEquals(4, userPreference.getAverageExercisePerMuscleGroup());
    }
}
