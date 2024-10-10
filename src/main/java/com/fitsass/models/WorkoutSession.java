package com.fitsass.models;

import com.fitsass.enums.Muscle;
import com.fitsass.enums.MuscleGroup;
import com.fitsass.enums.PhysicalLimitations;
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
        int reps = 0;
        int sets = 0;
        double intensity = 0.0;

        switch (userPreference.getGoal()) {
            case LOSE_WEIGHT -> {
                reps = 12;
                sets = 3;
                intensity = 0.6;
            }
            case GAIN_MUSCLE -> {
                reps = 8;
                sets = 4;
                intensity = 0.8;
            }
            case MAINGAIN -> {
                reps = 10;
                sets = 3;
                intensity = 0.7;
            }
            case ENDURANCE -> {
                reps = 15;
                sets = 2;
                intensity = 0.5;
            }
            case RUN_FASTER, RUN_LONGER -> {
                reps = 20;
                sets = 2;
                intensity = 0.4;
            }
            case FLEXIBILITY -> {
                reps = 12;
                sets = 2;
                intensity = 0.5;
            }
        }

        for (MuscleGroup muscleGroup : muscleGroups) {
            Map<Muscle, List<Exercise>> muscleExerciseMap = new HashMap<>();

            for (Muscle muscle : muscleGroup.getMuscles()) {
                muscleExerciseMap.put(muscle, new ArrayList<>());
            }

            adjustDifficulty(userPreference);

            for (Exercise exercise : exercises) {
                if (((exercise.getMainMuscleGroup().equals(muscleGroup) || muscleGroup.equals(MuscleGroup.FULL_BODY)) && muscleExerciseMap.containsKey(exercise.getSpecificity())) && exercise.getDifficulty() <= difficulty && exercise.getType().equals(type)) {
                    boolean isLimited = false;

                    for (PhysicalLimitations userLimitation : userPreference.getPhysicalLimitations()) {
                        if (exercise.getLimitations().contains(userLimitation)) {
                            isLimited = true;
                            break;
                        }
                    }

                    if (!isLimited) {
                        muscleExerciseMap.get(exercise.getSpecificity()).add(exercise);
                    }
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
                            selectedExercise.setReps(reps);
                            selectedExercise.setSets(sets);
                            selectedExercise.setIntensity(intensity);
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

