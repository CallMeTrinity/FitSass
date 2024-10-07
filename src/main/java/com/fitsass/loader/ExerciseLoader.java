package com.fitsass.loader;

import com.fitsass.enums.MuscleGroup;
import com.fitsass.models.Exercise;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ExerciseLoader {

    public List<Exercise> loadExercises(String filePath) {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(filePath)) {
            List<Exercise> exercises = gson.fromJson(reader, new TypeToken<List<Exercise>>(){}.getType());
            return exercises;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Exercise> loadMuscleGroupExercise(List<Exercise> list, MuscleGroup muscleGroup){
        List<Exercise> muscleGroupExercises = new ArrayList<>();
        for (Exercise exercise : list) {
            if (exercise.getMainMuscleGroup().equals(muscleGroup)) {
                muscleGroupExercises.add(exercise);
            }
        }
        return muscleGroupExercises;
    }
}
