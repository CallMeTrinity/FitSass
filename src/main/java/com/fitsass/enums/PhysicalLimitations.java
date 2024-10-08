package com.fitsass.enums;

public enum PhysicalLimitations {
    KNEE_PAIN("Knee pain - Avoid high-impact exercises like jumping or running."),
    LOWER_BACK_PAIN("Lower back pain - Focus on core strength, avoid heavy deadlifts."),
    SHOULDER_ISSUES("Shoulder issues - Be careful with overhead pressing exercises."),
    NECK_PAIN("Neck pain - Limit strain on the neck, avoid certain postures."),
    WRIST_PAIN("Wrist pain - Avoid exercises that put pressure on the wrists, like push-ups."),
    HIP_PAIN("Hip pain - Modify lower body exercises to reduce stress on hips."),
    ELBOW_PAIN("Elbow pain - Be cautious with pushing and pulling exercises."),
    ANKLE_INJURY("Ankle injury - Avoid exercises that require balance or ankle mobility."),
    HEART_CONDITIONS("Heart conditions - Avoid high-intensity cardio and monitor heart rate."),
    ASTHMA("Asthma - Avoid prolonged intense cardio exercises."),
    PREGNANCY("Pregnancy - Avoid exercises that put pressure on the abdomen."),
    JOINT_ISSUES("Joint issues - Focus on low-impact exercises to reduce stress on joints."),
    RECENT_SURGERY("Recent surgery - Focus on rehabilitation and low-impact activities."),
    HIGH_BLOOD_PRESSURE("High blood pressure - Avoid intense resistance training and high-intensity cardio.");

    private final String description;

    PhysicalLimitations(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
