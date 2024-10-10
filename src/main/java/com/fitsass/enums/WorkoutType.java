package com.fitsass.enums;

public enum WorkoutType {
    WEIGHTLIFTING("All training with weights/machines"),
    CARDIO("Training that increases your heart rate"),
    YOGA("Training that focuses on flexibility and breathing"),
    STRETCHING("Training that focuses on flexibility"),
    HIIT("High Intensity Interval Training"),
    GENERAL("General workout plan");

    private String description;
    WorkoutType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
