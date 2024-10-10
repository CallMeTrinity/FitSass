package com.fitsass.models;

import com.fitsass.loader.SplitLoader;
import junit.framework.TestCase;

import java.util.List;

public class SplitTest extends TestCase {

    private List<Split> splits;

    @Override
    protected void setUp() throws Exception {
        SplitLoader loader = new SplitLoader();
        this.splits = loader.loadSplits();
    }

    public void testGetInfos() {
        assertNotNull(splits.getFirst().getName());
        assertNotNull(splits.getFirst().getDescription());
        assertNotNull(splits.getFirst().getWorkoutType());
        assertNotNull(splits.getFirst().getMuscleGroupsPerDay());
    }

    public void testSetName() {
        splits.getFirst().setName("Test");
        assertEquals("Test", splits.getFirst().getName());
    }
}
