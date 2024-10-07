package com.fitsass.models;

import com.fitsass.enums.Muscle;
import com.fitsass.enums.MuscleGroup;
import com.fitsass.enums.WorkoutType;

import java.util.*;

public class WorkoutSession {
    private final String name;
    private final List<Exercise> exercises;
    private WorkoutType type;

    public WorkoutSession(String name, WorkoutType type) {
        this.name = name;
        this.type = type;
        this.exercises = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public void printWorkoutSession() {
        System.out.println(this.name + " : " + type);
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

    public void generateSession(List<MuscleGroup> muscleGroups, int numberOfExercises, List<Exercise> exercises) {
        for (MuscleGroup muscleGroup : muscleGroups) {
            Map<Muscle, List<Exercise>> muscleExerciseMap = new HashMap<>();

            // Initialiser la map avec les muscles associés au groupe musculaire
            for (Muscle muscle : muscleGroup.getMuscles()) {
                muscleExerciseMap.put(muscle, new ArrayList<>());
            }

            // Remplir la map avec les exercices correspondants à chaque muscle spécifique
            for (Exercise exercise : exercises) {
                if ((exercise.getMainMuscleGroup().equals(muscleGroup) || muscleGroup.equals(MuscleGroup.FULL_BODY)) && muscleExerciseMap.containsKey(exercise.getSpecificity())) {
                    muscleExerciseMap.get(exercise.getSpecificity()).add(exercise);
                }
            }

            // Mélanger les exercices pour chaque muscle
            for (List<Exercise> muscleSpecificExercises : muscleExerciseMap.values()) {
                Collections.shuffle(muscleSpecificExercises);
            }

            // Si le groupe musculaire est FULL_BODY, on sélectionne un exercice pour chaque muscle
            if (muscleGroup == MuscleGroup.FULL_BODY) {
                // Sélectionner au moins un exercice pour chaque muscle dans FULL_BODY
                for (Muscle muscle : muscleGroup.getMuscles()) {
                    List<Exercise> muscleSpecificExercises = muscleExerciseMap.get(muscle);

                    if (!muscleSpecificExercises.isEmpty()) {
                        Exercise selectedExercise = muscleSpecificExercises.removeFirst();
                        System.out.println("Adding " + selectedExercise.getName() + " to " + this.name + " which works on " + muscle);
                        addExercise(selectedExercise);
                    }
                }
            } else {
                // Logique normale : distribuer les exercices pour chaque muscle (selon le nombre d'exercices)
                int exercisesAdded = 0;
                boolean musclesAvailable = true;

                while (exercisesAdded < numberOfExercises && musclesAvailable) {
                    musclesAvailable = false;

                    for (Muscle muscle : muscleGroup.getMuscles()) {
                        List<Exercise> muscleSpecificExercises = muscleExerciseMap.get(muscle);

                        if (!muscleSpecificExercises.isEmpty() && exercisesAdded < numberOfExercises) {
                            Exercise selectedExercise = muscleSpecificExercises.removeFirst();
                            System.out.println("Adding " + selectedExercise.getName() + " to " + this.name + " which works on " + muscle);
                            addExercise(selectedExercise);
                            exercisesAdded++;
                            musclesAvailable = true;
                        }
                    }
                }
            }
        }
    }


}

