package com.fitsass.models;

import com.fitsass.loader.ExerciseLoader;
import com.fitsass.loader.SplitLoader;
import junit.framework.TestCase;

import java.util.List;

public class LoaderTest extends TestCase {

    public void testExerciseLoader() {
        ExerciseLoader loader = new ExerciseLoader();
        List<Exercise> exercises = loader.loadExercises();
        assertNotNull(exercises);
    }

    public void testSplitLoader() {
        SplitLoader loader = new SplitLoader();
        List<Split> splits = loader.loadSplits();
        assertNotNull(splits);
    }
}
