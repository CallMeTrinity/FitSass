package com.fitsass.entity;

import com.fitsass.enums.FitnessGoal;
import com.fitsass.enums.PhysicalLimitations;
import com.fitsass.enums.WorkoutType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workout_plans")
public class WorkoutPlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private int experienceLevel;

    @Column(nullable = false)
    private int weeklyWorkoutFrequency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FitnessGoal goal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkoutType exercisePreference;

    @ElementCollection(targetClass = PhysicalLimitations.class)
    @CollectionTable(name = "workout_plan_limitations", joinColumns = @JoinColumn(name = "workout_plan_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "limitation")
    private List<PhysicalLimitations> physicalLimitations = new ArrayList<>();

    @Column(nullable = false)
    private int currentWeight;

    @Column(nullable = false)
    private double height;

    @Column(nullable = false)
    private double bmi;

    @Column(nullable = false)
    private String splitName;

    @OneToMany(mappedBy = "workoutPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutSessionEntity> sessions = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public WorkoutPlanEntity() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(int experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public int getWeeklyWorkoutFrequency() {
        return weeklyWorkoutFrequency;
    }

    public void setWeeklyWorkoutFrequency(int weeklyWorkoutFrequency) {
        this.weeklyWorkoutFrequency = weeklyWorkoutFrequency;
    }

    public FitnessGoal getGoal() {
        return goal;
    }

    public void setGoal(FitnessGoal goal) {
        this.goal = goal;
    }

    public WorkoutType getExercisePreference() {
        return exercisePreference;
    }

    public void setExercisePreference(WorkoutType exercisePreference) {
        this.exercisePreference = exercisePreference;
    }

    public List<PhysicalLimitations> getPhysicalLimitations() {
        return physicalLimitations;
    }

    public void setPhysicalLimitations(List<PhysicalLimitations> physicalLimitations) {
        this.physicalLimitations = physicalLimitations;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public String getSplitName() {
        return splitName;
    }

    public void setSplitName(String splitName) {
        this.splitName = splitName;
    }

    public List<WorkoutSessionEntity> getSessions() {
        return sessions;
    }

    public void setSessions(List<WorkoutSessionEntity> sessions) {
        this.sessions = sessions;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void addSession(WorkoutSessionEntity session) {
        sessions.add(session);
        session.setWorkoutPlan(this);
    }
}
