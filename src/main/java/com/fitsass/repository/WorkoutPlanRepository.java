package com.fitsass.repository;

import com.fitsass.entity.WorkoutPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlanEntity, Long> {

    List<WorkoutPlanEntity> findByUserNameOrderByCreatedAtDesc(String userName);

    @Query("SELECT w FROM WorkoutPlanEntity w LEFT JOIN FETCH w.sessions s LEFT JOIN FETCH s.exercises WHERE w.id = :id")
    WorkoutPlanEntity findByIdWithSessions(Long id);
}
