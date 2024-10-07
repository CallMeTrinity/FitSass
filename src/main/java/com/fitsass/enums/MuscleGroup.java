package com.fitsass.enums;

public enum MuscleGroup {
    CHEST("Includes lower chest, upper chest, and inner chest."),
    BACK("Includes upper back, lower back, and lats."),
    LEGS("Includes quads, hamstrings, and calves."),
    SHOULDERS("Includes front delts, side delts, and rear delts."),
    ARMS("Includes biceps, triceps, and forearms."),
    ABS("Includes upper abs, lower abs, and obliques."),
    TRAPS("Its just traps."),
    FULL_BODY("Full body workout.");
    private final String description;

    MuscleGroup(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
