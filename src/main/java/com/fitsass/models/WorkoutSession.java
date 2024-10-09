package com.fitsass.models;

import com.fitsass.enums.Muscle;
import com.fitsass.enums.MuscleGroup;
import com.fitsass.enums.WorkoutType;

import java.util.*;

public class WorkoutSession {
    private final String name;
    private final List<Exercise> exercises;
    private final List<MuscleGroup> muscleGroups;
    private WorkoutType type;
    private int difficulty;

    public WorkoutSession(String name, WorkoutType type, List<MuscleGroup> muscleGroups) {
        this.name = name;
        this.type = type;
        this.exercises = new ArrayList<>();
        this.muscleGroups = muscleGroups;
        this.difficulty = 0;
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public void printWorkoutSession() {
        System.out.println(this.name + " : " + muscleGroups.toString());
        for (Exercise exercise : exercises) {
            exercise.printExercise();
        }
    }

    public WorkoutType getType() {
        return type;
    }

    public void setType(WorkoutType type) {
        this.type = type;
    }

    public void adjustDifficulty(UserPreference userPreference) {
        if (userPreference.getExperienceLevel() <= 1) {
            difficulty = 0;
        } else if (userPreference.getExperienceLevel() <= 3) {
            difficulty = 1;
        } else {
            difficulty = 2;
        }
    }

    public void generateSession(int numberOfExercises, List<Exercise> exercises, UserPreference userPreference) {
        for (MuscleGroup muscleGroup : muscleGroups) {
            Map<Muscle, List<Exercise>> muscleExerciseMap = new HashMap<>();

            for (Muscle muscle : muscleGroup.getMuscles()) {
                muscleExerciseMap.put(muscle, new ArrayList<>());
            }

            adjustDifficulty(userPreference);

            for (Exercise exercise : exercises) {
                if (
                                ((exercise.getMainMuscleGroup().equals(muscleGroup) || muscleGroup.equals(MuscleGroup.FULL_BODY))
                                        && muscleExerciseMap.containsKey(exercise.getSpecificity()))
                                && exercise.getDifficulty() == difficulty
                ) {
                    muscleExerciseMap.get(exercise.getSpecificity()).add(exercise);
                }
            }

            for (List<Exercise> muscleSpecificExercises : muscleExerciseMap.values()) {
                Collections.shuffle(muscleSpecificExercises);
            }

            if (muscleGroup == MuscleGroup.FULL_BODY) {
                for (Muscle muscle : muscleGroup.getMuscles()) {
                    List<Exercise> muscleSpecificExercises = muscleExerciseMap.get(muscle);

                    if (!muscleSpecificExercises.isEmpty()) {
                        Exercise selectedExercise = muscleSpecificExercises.removeFirst();
                        addExercise(selectedExercise);
                    }
                }
            } else {
                int exercisesAdded = 0;
                boolean musclesAvailable = true;

                while (exercisesAdded < numberOfExercises && musclesAvailable) {
                    musclesAvailable = false;

                    for (Muscle muscle : muscleGroup.getMuscles()) {
                        List<Exercise> muscleSpecificExercises = muscleExerciseMap.get(muscle);

                        if (!muscleSpecificExercises.isEmpty() && exercisesAdded < numberOfExercises) {
                            Exercise selectedExercise = muscleSpecificExercises.removeFirst();
                            addExercise(selectedExercise);
                            exercisesAdded++;
                            musclesAvailable = true;
                        }
                    }
                }
            }
        }
    }


    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}

