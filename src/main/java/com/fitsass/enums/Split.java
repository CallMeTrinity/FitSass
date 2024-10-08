package com.fitsass.enums;

import java.util.List;
import java.util.Map;

public enum Split {

    UPPER_LOWER("""
            4 days: 2 upper body days (chest, back, shoulders, arms) and 2 lower body days (quads, hamstrings, calves).\s
            Day 1: Upper Body\s
            Day 2: Lower Body\s
            Day 3: Rest\s
            Day 4: Upper Body\s
            Day 5: Lower Body\s
            Day 6-7: Rest""",
            4,
            Map.of(
                    1, List.of(MuscleGroup.CHEST, MuscleGroup.BACK, MuscleGroup.SHOULDERS, MuscleGroup.ARMS),
                    2, List.of(MuscleGroup.LEGS),
                    3, List.of(MuscleGroup.CHEST, MuscleGroup.BACK, MuscleGroup.SHOULDERS, MuscleGroup.ARMS),
                    4, List.of(MuscleGroup.LEGS)
            )
    ),
    PHUL("""
            4 days: Power Hypertrophy Upper Lower (PHUL) split for both strength and muscle building.\s
            Day 1: Upper Power\s
            Day 2: Lower Power\s
            Day 3: Rest\s
            Day 4: Upper Hypertrophy\s
            Day 5: Lower Hypertrophy\s
            Day 6-7: Rest""",
            4,
            Map.of(
                    1, List.of(MuscleGroup.CHEST, MuscleGroup.BACK, MuscleGroup.SHOULDERS, MuscleGroup.ARMS),
                    2, List.of(MuscleGroup.LEGS),
                    3, List.of(MuscleGroup.CHEST, MuscleGroup.BACK, MuscleGroup.SHOULDERS, MuscleGroup.ARMS),
                    4, List.of(MuscleGroup.LEGS)
            )
    ),

    PHAT("""
            5 days: Power Hypertrophy Adaptive Training (PHAT), a 5-day split focusing on both strength and hypertrophy.\s
            Day 1: Upper Power\s
            Day 2: Lower Power\s
            Day 3: Rest\s
            Day 4: Back and Shoulders Hypertrophy\s
            Day 5: Legs Hypertrophy\s
            Day 6: Chest and Arms Hypertrophy\s
            Day 7: Rest""",
            5,
            Map.of(
                    1, List.of(MuscleGroup.CHEST, MuscleGroup.BACK, MuscleGroup.SHOULDERS, MuscleGroup.ARMS),
                    2, List.of(MuscleGroup.LEGS),
                    3, List.of(MuscleGroup.BACK, MuscleGroup.SHOULDERS),
                    4, List.of(MuscleGroup.LEGS),
                    5, List.of(MuscleGroup.CHEST, MuscleGroup.ARMS)
            )
    ),

    PUSH_PULL_LEGS_3_DAYS("""
            3 days: A 3-day Push-Pull-Legs split for those with a limited schedule.\s
            Day 1: Push (chest, shoulders, triceps)\s
            Day 2: Pull (back, biceps)\s
            Day 3: Legs (quads, hamstrings, calves)\s
            Day 4-7: Rest""",
            3,
            Map.of(
                    1, List.of(MuscleGroup.CHEST, MuscleGroup.SHOULDERS, MuscleGroup.ARMS),
                    2, List.of(MuscleGroup.BACK, MuscleGroup.ARMS),
                    3, List.of(MuscleGroup.LEGS)
            )
    ),

    PUSH_PULL_LEGS("""
            6 days: 2 push days (chest, shoulders, triceps), 2 pull days (back, biceps), and 2 leg days (quads, hamstrings, calves).\s
            Day 1: Push (chest, shoulders, triceps)\s
            Day 2: Pull (back, biceps)\s
            Day 3: Legs (quads, hamstrings, calves)\s
            Day 4: Push (chest, shoulders, triceps)\s
            Day 5: Pull (back, biceps)\s
            Day 6: Legs (quads, hamstrings, calves)\s
            Day 7: Rest""",
            6,
            Map.of(
                    1, List.of(MuscleGroup.CHEST, MuscleGroup.SHOULDERS, MuscleGroup.ARMS),
                    2, List.of(MuscleGroup.BACK, MuscleGroup.ARMS),
                    3, List.of(MuscleGroup.LEGS),
                    4, List.of(MuscleGroup.CHEST, MuscleGroup.SHOULDERS, MuscleGroup.ARMS),
                    5, List.of(MuscleGroup.BACK, MuscleGroup.ARMS),
                    6, List.of(MuscleGroup.LEGS)
            )
    ),

    FULL_BODY("""
            Between 2 and 4 days: full body workouts on each day, targeting all major muscle groups (chest, back, shoulders, arms, legs).\s
            Day 1: Full Body\s
            Day 2: Rest\s
            Day 3: Full Body\s
            Day 4: Rest\s
            Day 5: Full Body (optional)\s
            Day 6: Rest\s
            Day 7: Full Body (optional)""",
            3,
            Map.of(
                    1, List.of(MuscleGroup.CHEST, MuscleGroup.BACK, MuscleGroup.SHOULDERS, MuscleGroup.ARMS, MuscleGroup.LEGS),
                    2, List.of(MuscleGroup.CHEST, MuscleGroup.BACK, MuscleGroup.SHOULDERS, MuscleGroup.ARMS, MuscleGroup.LEGS),
                    3, List.of(MuscleGroup.CHEST, MuscleGroup.BACK, MuscleGroup.SHOULDERS, MuscleGroup.ARMS, MuscleGroup.LEGS)
            )
    ),

    ARNOLD_SPLIT("""
            6 days: Classic Arnold Schwarzenegger split, alternating chest/back and arms/shoulders, with separate leg days.\s
            Day 1: Chest and Back\s
            Day 2: Shoulders and Arms\s
            Day 3: Legs\s
            Day 4: Chest and Back\s
            Day 5: Shoulders and Arms\s
            Day 6: Legs\s
            Day 7: Rest""",
            6,
            Map.of(
                    1, List.of(MuscleGroup.CHEST, MuscleGroup.BACK),
                    2, List.of(MuscleGroup.SHOULDERS, MuscleGroup.ARMS),
                    3, List.of(MuscleGroup.LEGS),
                    4, List.of(MuscleGroup.CHEST, MuscleGroup.BACK),
                    5, List.of(MuscleGroup.SHOULDERS, MuscleGroup.ARMS),
                    6, List.of(MuscleGroup.LEGS)
            )
    ),

    TENINSON_SPLIT("""
            5 days: Combines chest and back on some days, with legs and arms spread across the week.\s
            Day 1: Chest and Back\s
            Day 2: Arms and Shoulders\s
            Day 3: Legs\s
            Day 4: Rest\s
            Day 5: Chest and Back\s
            Day 6: Legs and Arms\s
            Day 7: Rest as God did""",
            5,
            Map.of(
                    1, List.of(MuscleGroup.CHEST, MuscleGroup.BACK),
                    2, List.of(MuscleGroup.ARMS, MuscleGroup.SHOULDERS),
                    3, List.of(MuscleGroup.LEGS),
                    4, List.of(MuscleGroup.CHEST, MuscleGroup.BACK),
                    5, List.of(MuscleGroup.LEGS, MuscleGroup.ARMS)
            )
    );

    private final String description;
    private final int days;
    private final Map<Integer, List<MuscleGroup>> muscleGroupsPerDay;

    Split(String description, int days, Map<Integer, List<MuscleGroup>> muscleGroupsPerDay) {
        this.description = description;
        this.days = days;
        this.muscleGroupsPerDay = muscleGroupsPerDay;
    }

    public String getDescription() {
        return description;
    }

    public int getDays() {
        return days;
    }

    public Map<Integer, List<MuscleGroup>> getMuscleGroupsPerDay() {
        return muscleGroupsPerDay;
    }
}
