package com.fitsass.controller;

import com.fitsass.dto.WorkoutPlanRequestDTO;
import com.fitsass.dto.WorkoutPlanResponseDTO;
import com.fitsass.services.WorkoutPlanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-plans")
@CrossOrigin(origins = "*")
public class WorkoutPlanController {

    @Autowired
    private WorkoutPlanService workoutPlanService;

    @PostMapping
    public ResponseEntity<WorkoutPlanResponseDTO> generateWorkoutPlan(@Valid @RequestBody WorkoutPlanRequestDTO request) {
        WorkoutPlanResponseDTO response = workoutPlanService.generateWorkoutPlan(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutPlanResponseDTO> getWorkoutPlanById(@PathVariable Long id) {
        return workoutPlanService.getWorkoutPlanById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<WorkoutPlanResponseDTO>> getAllWorkoutPlans() {
        List<WorkoutPlanResponseDTO> plans = workoutPlanService.getAllWorkoutPlans();
        return ResponseEntity.ok(plans);
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<List<WorkoutPlanResponseDTO>> getWorkoutPlansByUser(@PathVariable String userName) {
        List<WorkoutPlanResponseDTO> plans = workoutPlanService.getWorkoutPlansByUserName(userName);
        return ResponseEntity.ok(plans);
    }
}
