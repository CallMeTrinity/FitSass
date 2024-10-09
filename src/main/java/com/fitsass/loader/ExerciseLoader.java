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

    private static final String EXERCISES_FILE = "./src/main/resources/exercises.json";


    public List<Exercise> loadExercises() {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(EXERCISES_FILE)) {
            return gson.fromJson(reader, new TypeToken<List<Exercise>>(){}.getType());
        } catch (Exception e) {
            e.getMessage();
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
