package com.fitsass.enums;

public enum FitnessGoal {
    LOSE_WEIGHT("Lose weight"),
    GAIN_MUSCLE("Gain muscle"),
    MAINTAIN_WEIGHT("Maintain weight"),
    FLEXIBILITY("Flexibility");

    private String description;

    FitnessGoal(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
