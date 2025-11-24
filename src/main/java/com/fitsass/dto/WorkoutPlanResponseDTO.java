package com.fitsass.dto;

import java.util.List;

public class WorkoutPlanResponseDTO {
    private Long id;
    private String userName;
    private int weeklyWorkoutFrequency;
    private String splitName;
    private double bmi;
    private List<WorkoutSessionDTO> sessions;

    public WorkoutPlanResponseDTO() {
    }

    public WorkoutPlanResponseDTO(Long id, String userName, int weeklyWorkoutFrequency, String splitName, double bmi, List<WorkoutSessionDTO> sessions) {
        this.id = id;
        this.userName = userName;
        this.weeklyWorkoutFrequency = weeklyWorkoutFrequency;
        this.splitName = splitName;
        this.bmi = bmi;
        this.sessions = sessions;
    }

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

    public int getWeeklyWorkoutFrequency() {
        return weeklyWorkoutFrequency;
    }

    public void setWeeklyWorkoutFrequency(int weeklyWorkoutFrequency) {
        this.weeklyWorkoutFrequency = weeklyWorkoutFrequency;
    }

    public String getSplitName() {
        return splitName;
    }

    public void setSplitName(String splitName) {
        this.splitName = splitName;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public List<WorkoutSessionDTO> getSessions() {
        return sessions;
    }

    public void setSessions(List<WorkoutSessionDTO> sessions) {
        this.sessions = sessions;
    }
}
