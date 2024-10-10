package com.fitsass.enums;

import java.util.List;

public enum MuscleGroup {
    CHEST("Includes lower chest, upper chest, and inner chest.", List.of(Muscle.UPPER_CHEST, Muscle.LOWER_CHEST, Muscle.INNER_CHEST)),
    BACK("Includes upper back, lower back, and lats.", List.of(Muscle.UPPER_BACK, Muscle.LATS, Muscle.LOWER_BACK)),
    LEGS("Includes quads, hamstrings, and calves.", List.of(Muscle.QUADS, Muscle.HAMSTRINGS, Muscle.CALVES, Muscle.GLUTES, Muscle.ADDUCTORS, Muscle.ABDUCTORS)),
    SHOULDERS("Includes front delts, side delts, and rear delts.", List.of(Muscle.FRONT_DELTS, Muscle.SIDE_DELTS, Muscle.REAR_DELTS)),
    ARMS("Includes biceps, triceps, and forearms.", List.of(Muscle.BICEPS, Muscle.TRICEPS, Muscle.FOREARMS)),
    CORE("Includes upper abs, lower abs, and obliques.", List.of(Muscle.UPPER_ABS, Muscle.LOWER_ABS, Muscle.OBLIQUES)),
    TRAPS("Its just traps.", List.of(Muscle.TRAPS)),
    FULL_BODY("Full body workout.", List.of(Muscle.UPPER_CHEST, Muscle.LOWER_CHEST, Muscle.UPPER_BACK, Muscle.LATS, Muscle.QUADS, Muscle.HAMSTRINGS));

    private final String description;
    private final List<Muscle> muscles;

    MuscleGroup(String description, List<Muscle> muscles) {
        this.description = description;
        this.muscles = muscles;
    }

    public String getDescription() {
        return description;
    }

    public List<Muscle> getMuscles() {
        return muscles;
    }
}
