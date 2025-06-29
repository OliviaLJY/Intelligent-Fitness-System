package com.fitness.controller;

import com.fitness.model.WorkoutSession;
import com.fitness.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Workout Controller
 */
@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
@Slf4j
public class WorkoutController {

    private final WorkoutService workoutService;

    /**
     * Get user's workout sessions
     */
    @GetMapping
    public ResponseEntity<List<WorkoutSession>> getUserWorkouts(Authentication authentication) {
        Long userId = getUserId(authentication);
        List<WorkoutSession> workouts = workoutService.getUserWorkoutSessions(userId);
        return ResponseEntity.ok(workouts);
    }

    /**
     * Get active workout session
     */
    @GetMapping("/active")
    public ResponseEntity<?> getActiveWorkout(Authentication authentication) {
        Long userId = getUserId(authentication);
        Optional<WorkoutSession> activeSession = workoutService.getActiveWorkoutSession(userId);
        
        if (activeSession.isPresent()) {
            return ResponseEntity.ok(activeSession.get());
        } else {
            return ResponseEntity.ok(Map.of("message", "No active workout session"));
        }
    }

    /**
     * Create new workout session
     */
    @PostMapping
    public ResponseEntity<?> createWorkout(@Valid @RequestBody WorkoutSessionRequest request, 
                                          Authentication authentication) {
        try {
            Long userId = getUserId(authentication);
            
            WorkoutSession session = WorkoutSession.builder()
                    .sessionName(request.getSessionName())
                    .plannedDurationMinutes(request.getPlannedDurationMinutes())
                    .workoutType(request.getWorkoutType())
                    .build();

            WorkoutSession createdSession = workoutService.createWorkoutSession(userId, session);
            return ResponseEntity.ok(createdSession);
            
        } catch (Exception e) {
            log.error("Error creating workout: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Start workout session
     */
    @PostMapping("/{sessionId}/start")
    public ResponseEntity<?> startWorkout(@PathVariable Long sessionId, 
                                         Authentication authentication) {
        try {
            Long userId = getUserId(authentication);
            WorkoutSession session = workoutService.startWorkoutSession(sessionId, userId);
            return ResponseEntity.ok(session);
            
        } catch (Exception e) {
            log.error("Error starting workout: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Complete workout session
     */
    @PostMapping("/{sessionId}/complete")
    public ResponseEntity<?> completeWorkout(@PathVariable Long sessionId, 
                                            Authentication authentication) {
        try {
            Long userId = getUserId(authentication);
            WorkoutSession session = workoutService.completeWorkoutSession(sessionId, userId);
            return ResponseEntity.ok(session);
            
        } catch (Exception e) {
            log.error("Error completing workout: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Pause workout session
     */
    @PostMapping("/{sessionId}/pause")
    public ResponseEntity<?> pauseWorkout(@PathVariable Long sessionId, 
                                         Authentication authentication) {
        try {
            Long userId = getUserId(authentication);
            WorkoutSession session = workoutService.pauseWorkoutSession(sessionId, userId);
            return ResponseEntity.ok(session);
            
        } catch (Exception e) {
            log.error("Error pausing workout: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Resume workout session
     */
    @PostMapping("/{sessionId}/resume")
    public ResponseEntity<?> resumeWorkout(@PathVariable Long sessionId, 
                                          Authentication authentication) {
        try {
            Long userId = getUserId(authentication);
            WorkoutSession session = workoutService.resumeWorkoutSession(sessionId, userId);
            return ResponseEntity.ok(session);
            
        } catch (Exception e) {
            log.error("Error resuming workout: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Cancel workout session
     */
    @PostMapping("/{sessionId}/cancel")
    public ResponseEntity<?> cancelWorkout(@PathVariable Long sessionId, 
                                          Authentication authentication) {
        try {
            Long userId = getUserId(authentication);
            WorkoutSession session = workoutService.cancelWorkoutSession(sessionId, userId);
            return ResponseEntity.ok(session);
            
        } catch (Exception e) {
            log.error("Error cancelling workout: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Get workout templates
     */
    @GetMapping("/templates")
    public ResponseEntity<List<WorkoutSession>> getWorkoutTemplates() {
        List<WorkoutSession> templates = workoutService.getWorkoutTemplates();
        return ResponseEntity.ok(templates);
    }

    /**
     * Create template from workout session
     */
    @PostMapping("/{sessionId}/create-template")
    public ResponseEntity<?> createTemplate(@PathVariable Long sessionId,
                                           @RequestBody CreateTemplateRequest request,
                                           Authentication authentication) {
        try {
            Long userId = getUserId(authentication);
            WorkoutSession template = workoutService.createTemplateFromSession(
                    sessionId, userId, request.getTemplateName());
            return ResponseEntity.ok(template);
            
        } catch (Exception e) {
            log.error("Error creating template: {}", e.getMessage());
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    private Long getUserId(Authentication authentication) {
        // In a real implementation, you would extract user ID from the JWT token
        // For now, we'll use a simple approach
        return 1L; // Placeholder
    }

    // DTOs
    public static class WorkoutSessionRequest {
        private String sessionName;
        private Integer plannedDurationMinutes;
        private WorkoutSession.WorkoutType workoutType;

        // Getters and setters
        public String getSessionName() { return sessionName; }
        public void setSessionName(String sessionName) { this.sessionName = sessionName; }

        public Integer getPlannedDurationMinutes() { return plannedDurationMinutes; }
        public void setPlannedDurationMinutes(Integer plannedDurationMinutes) { 
            this.plannedDurationMinutes = plannedDurationMinutes; 
        }

        public WorkoutSession.WorkoutType getWorkoutType() { return workoutType; }
        public void setWorkoutType(WorkoutSession.WorkoutType workoutType) { 
            this.workoutType = workoutType; 
        }
    }

    public static class CreateTemplateRequest {
        private String templateName;

        // Getters and setters
        public String getTemplateName() { return templateName; }
        public void setTemplateName(String templateName) { this.templateName = templateName; }
    }
} 