package com.fitsass.services;

import com.fitsass.dto.*;
import com.fitsass.entity.ExerciseEntity;
import com.fitsass.entity.WorkoutPlanEntity;
import com.fitsass.entity.WorkoutSessionEntity;
import com.fitsass.models.*;
import com.fitsass.repository.WorkoutPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutPlanService {

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    @Transactional
    public WorkoutPlanResponseDTO generateWorkoutPlan(WorkoutPlanRequestDTO request) {
        // Convert DTO to UserPreferenceData
        UserPreferenceData userPreferenceData = new UserPreferenceData(
            request.getName(),
            request.getExperienceLevel(),
            request.getWeeklyWorkoutFrequency(),
            request.getGoal(),
            request.getExercisePreference(),
            request.getPhysicalLimitations() != null ? request.getPhysicalLimitations() : new ArrayList<>(),
            request.getCurrentWeight(),
            request.getHeight()
        );

        // Generate workout plan using existing logic
        WorkoutPlan workoutPlan = new WorkoutPlan(userPreferenceData);
        workoutPlan.generateWorkoutPlanWithoutPrint();

        // Convert to entity and persist
        WorkoutPlanEntity entity = convertToEntity(workoutPlan, userPreferenceData);
        entity = workoutPlanRepository.save(entity);

        // Convert to DTO and return
        return convertToDTO(entity);
    }

    @Transactional(readOnly = true)
    public Optional<WorkoutPlanResponseDTO> getWorkoutPlanById(Long id) {
        WorkoutPlanEntity entity = workoutPlanRepository.findByIdWithSessions(id);
        return Optional.ofNullable(entity).map(this::convertToDTO);
    }

    @Transactional(readOnly = true)
    public List<WorkoutPlanResponseDTO> getWorkoutPlansByUserName(String userName) {
        List<WorkoutPlanEntity> entities = workoutPlanRepository.findByUserNameOrderByCreatedAtDesc(userName);
        return entities.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<WorkoutPlanResponseDTO> getAllWorkoutPlans() {
        List<WorkoutPlanEntity> entities = workoutPlanRepository.findAll();
        return entities.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    private WorkoutPlanEntity convertToEntity(WorkoutPlan workoutPlan, UserPreferenceData userData) {
        WorkoutPlanEntity entity = new WorkoutPlanEntity();
        entity.setUserName(userData.getName());
        entity.setExperienceLevel(userData.getExperienceLevel());
        entity.setWeeklyWorkoutFrequency(userData.getWeeklyWorkoutFrequency());
        entity.setGoal(userData.getGoal());
        entity.setExercisePreference(userData.getExercisePreference());
        entity.setPhysicalLimitations(userData.getPhysicalLimitations());
        entity.setCurrentWeight(userData.getCurrentWeight());
        entity.setHeight(userData.getHeight());
        entity.setBmi(userData.calculateBMI());
        entity.setSplitName(workoutPlan.getSplit().getName());

        for (WorkoutSession session : workoutPlan.session) {
            WorkoutSessionEntity sessionEntity = new WorkoutSessionEntity();
            sessionEntity.setName(session.getName());
            sessionEntity.setMuscleGroups(session.getMuscleGroups());

            for (Exercise exercise : session.getExercises()) {
                ExerciseEntity exerciseEntity = new ExerciseEntity();
                exerciseEntity.setName(exercise.getName());
                exerciseEntity.setSets(exercise.getSets());
                exerciseEntity.setReps(exercise.getReps());
                exerciseEntity.setDuration(exercise.getDuration());
                exerciseEntity.setDifficulty(exercise.getDifficulty());
                exerciseEntity.setSpecificity(exercise.getSpecificity());
                exerciseEntity.setIntensity(exercise.getIntensity());

                sessionEntity.addExercise(exerciseEntity);
            }

            entity.addSession(sessionEntity);
        }

        return entity;
    }

    private WorkoutPlanResponseDTO convertToDTO(WorkoutPlanEntity entity) {
        WorkoutPlanResponseDTO dto = new WorkoutPlanResponseDTO();
        dto.setId(entity.getId());
        dto.setUserName(entity.getUserName());
        dto.setWeeklyWorkoutFrequency(entity.getWeeklyWorkoutFrequency());
        dto.setSplitName(entity.getSplitName());
        dto.setBmi(entity.getBmi());

        List<WorkoutSessionDTO> sessionDTOs = entity.getSessions().stream()
            .map(session -> {
                List<ExerciseDTO> exerciseDTOs = session.getExercises().stream()
                    .map(exercise -> new ExerciseDTO(
                        exercise.getName(),
                        exercise.getSets(),
                        exercise.getReps(),
                        exercise.getDuration(),
                        exercise.getDifficulty(),
                        exercise.getSpecificity(),
                        exercise.getIntensity()
                    ))
                    .collect(Collectors.toList());

                return new WorkoutSessionDTO(
                    session.getName(),
                    session.getMuscleGroups(),
                    exerciseDTOs
                );
            })
            .collect(Collectors.toList());

        dto.setSessions(sessionDTOs);
        return dto;
    }
}
