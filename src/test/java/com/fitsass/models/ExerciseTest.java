package com.fitsass.models;

import com.fitsass.loader.ExerciseLoader;
import junit.framework.TestCase;

import java.util.List;

public class ExerciseTest extends TestCase {
    private List<Exercise> exercises;

    @Override
    protected void setUp() throws Exception {
        ExerciseLoader loader = new ExerciseLoader();
       this.exercises = loader.loadExercises();
    }

    public void testGetInfos() {
        assertNotNull(exercises.getFirst().getName());
        assertNotNull(exercises.getFirst().getLimitations());
        assertNotNull(exercises.getFirst().getMainMuscleGroup());
        assertNotNull(exercises.getFirst().getName());
        assertNotNull(exercises.getFirst().getSpecificity());
        assertNotNull(exercises.getFirst().getType());
    }

    public void testSetName() {
        exercises.getFirst().setName("Test");
        assertEquals("Test", exercises.getFirst().getName());
    }
}
