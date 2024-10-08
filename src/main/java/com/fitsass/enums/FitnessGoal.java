package com.fitsass.enums;

public enum FitnessGoal {
    LOSE_WEIGHT("Lose weight"),
    GAIN_MUSCLE("Gain muscle"),
    MAINGAIN("Gain a bit a muscle without gaining fat"),
    FLEXIBILITY("Flexibility");

    private final String description;

    FitnessGoal(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
