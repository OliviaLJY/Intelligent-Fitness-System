package com.fitness.service;

import com.fitness.model.*;
import com.fitness.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service class for Workout-related operations
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class WorkoutService {

    private final WorkoutSessionRepository workoutSessionRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;

    /**
     * Create a new workout session
     */
    public WorkoutSession createWorkoutSession(Long userId, WorkoutSession session) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Check if user has an active session
        Optional<WorkoutSession> activeSession = workoutSessionRepository.findActiveSessionByUser(user);
        if (activeSession.isPresent()) {
            throw new IllegalStateException("User already has an active workout session");
        }

        session.setUser(user);
        session.setStatus(WorkoutSession.SessionStatus.PLANNED);
        
        if (session.getStartTime() == null) {
            session.setStartTime(LocalDateTime.now());
        }

        WorkoutSession savedSession = workoutSessionRepository.save(session);
        log.info("Created workout session {} for user {}", savedSession.getId(), user.getUsername());
        return savedSession;
    }

    /**
     * Start a workout session
     */
    public WorkoutSession startWorkoutSession(Long sessionId, Long userId) {
        WorkoutSession session = getWorkoutSessionById(sessionId);
        validateUserOwnership(session, userId);

        if (session.getStatus() != WorkoutSession.SessionStatus.PLANNED) {
            throw new IllegalStateException("Can only start planned workout sessions");
        }

        session.setStatus(WorkoutSession.SessionStatus.IN_PROGRESS);
        session.setStartTime(LocalDateTime.now());

        return workoutSessionRepository.save(session);
    }

    /**
     * Complete a workout session
     */
    public WorkoutSession completeWorkoutSession(Long sessionId, Long userId) {
        WorkoutSession session = getWorkoutSessionById(sessionId);
        validateUserOwnership(session, userId);

        if (session.getStatus() != WorkoutSession.SessionStatus.IN_PROGRESS) {
            throw new IllegalStateException("Can only complete in-progress workout sessions");
        }

        session.setStatus(WorkoutSession.SessionStatus.COMPLETED);
        session.setEndTime(LocalDateTime.now());
        session.setActualDurationMinutes(session.getActualDuration());

        // Calculate session statistics
        calculateSessionStatistics(session);

        return workoutSessionRepository.save(session);
    }

    /**
     * Pause a workout session
     */
    public WorkoutSession pauseWorkoutSession(Long sessionId, Long userId) {
        WorkoutSession session = getWorkoutSessionById(sessionId);
        validateUserOwnership(session, userId);

        if (session.getStatus() != WorkoutSession.SessionStatus.IN_PROGRESS) {
            throw new IllegalStateException("Can only pause in-progress workout sessions");
        }

        session.setStatus(WorkoutSession.SessionStatus.PAUSED);
        return workoutSessionRepository.save(session);
    }

    /**
     * Resume a paused workout session
     */
    public WorkoutSession resumeWorkoutSession(Long sessionId, Long userId) {
        WorkoutSession session = getWorkoutSessionById(sessionId);
        validateUserOwnership(session, userId);

        if (session.getStatus() != WorkoutSession.SessionStatus.PAUSED) {
            throw new IllegalStateException("Can only resume paused workout sessions");
        }

        session.setStatus(WorkoutSession.SessionStatus.IN_PROGRESS);
        return workoutSessionRepository.save(session);
    }

    /**
     * Cancel a workout session
     */
    public WorkoutSession cancelWorkoutSession(Long sessionId, Long userId) {
        WorkoutSession session = getWorkoutSessionById(sessionId);
        validateUserOwnership(session, userId);

        session.setStatus(WorkoutSession.SessionStatus.CANCELLED);
        session.setEndTime(LocalDateTime.now());

        return workoutSessionRepository.save(session);
    }

    /**
     * Add exercise to workout session
     */
    public WorkoutExercise addExerciseToSession(Long sessionId, Long exerciseId, Long userId, 
                                               WorkoutExercise workoutExercise) {
        WorkoutSession session = getWorkoutSessionById(sessionId);
        validateUserOwnership(session, userId);

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found"));

        workoutExercise.setWorkoutSession(session);
        workoutExercise.setExercise(exercise);
        
        // Set default order if not provided
        if (workoutExercise.getExerciseOrder() == null) {
            int nextOrder = session.getExercises().size() + 1;
            workoutExercise.setExerciseOrder(nextOrder);
        }

        // Set default values from exercise if not provided
        if (workoutExercise.getPlannedSets() == null) {
            workoutExercise.setPlannedSets(exercise.getDefaultSets());
        }
        if (workoutExercise.getPlannedReps() == null) {
            workoutExercise.setPlannedReps(exercise.getDefaultReps());
        }
        if (workoutExercise.getPlannedWeight() == null) {
            workoutExercise.setPlannedWeight(exercise.getDefaultWeight());
        }
        if (workoutExercise.getRestTimeSeconds() == null) {
            workoutExercise.setRestTimeSeconds(exercise.getRestTimeSeconds());
        }

        session.getExercises().add(workoutExercise);
        session.setTotalExercises(session.getExercises().size());

        workoutSessionRepository.save(session);
        return workoutExercise;
    }

    /**
     * Update exercise in workout session
     */
    public WorkoutExercise updateWorkoutExercise(Long sessionId, Long workoutExerciseId, 
                                                Long userId, WorkoutExercise updates) {
        WorkoutSession session = getWorkoutSessionById(sessionId);
        validateUserOwnership(session, userId);

        WorkoutExercise workoutExercise = session.getExercises().stream()
                .filter(we -> we.getId().equals(workoutExerciseId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Workout exercise not found"));

        // Update fields
        if (updates.getPlannedSets() != null) {
            workoutExercise.setPlannedSets(updates.getPlannedSets());
        }
        if (updates.getPlannedReps() != null) {
            workoutExercise.setPlannedReps(updates.getPlannedReps());
        }
        if (updates.getPlannedWeight() != null) {
            workoutExercise.setPlannedWeight(updates.getPlannedWeight());
        }
        if (updates.getRestTimeSeconds() != null) {
            workoutExercise.setRestTimeSeconds(updates.getRestTimeSeconds());
        }
        if (updates.getNotes() != null) {
            workoutExercise.setNotes(updates.getNotes());
        }

        workoutSessionRepository.save(session);
        return workoutExercise;
    }

    /**
     * Get workout sessions for user
     */
    @Transactional(readOnly = true)
    public List<WorkoutSession> getUserWorkoutSessions(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return workoutSessionRepository.findByUserOrderByStartTimeDesc(user);
    }

    /**
     * Get active workout session for user
     */
    @Transactional(readOnly = true)
    public Optional<WorkoutSession> getActiveWorkoutSession(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return workoutSessionRepository.findActiveSessionByUser(user);
    }

    /**
     * Get workout session by ID
     */
    @Transactional(readOnly = true)
    public WorkoutSession getWorkoutSessionById(Long sessionId) {
        return workoutSessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Workout session not found"));
    }

    /**
     * Get workout templates
     */
    @Transactional(readOnly = true)
    public List<WorkoutSession> getWorkoutTemplates() {
        return workoutSessionRepository.findAllTemplates();
    }

    /**
     * Create workout template from session
     */
    public WorkoutSession createTemplateFromSession(Long sessionId, Long userId, String templateName) {
        WorkoutSession originalSession = getWorkoutSessionById(sessionId);
        validateUserOwnership(originalSession, userId);

        WorkoutSession template = WorkoutSession.builder()
                .user(originalSession.getUser())
                .sessionName(templateName)
                .plannedDurationMinutes(originalSession.getPlannedDurationMinutes())
                .workoutType(originalSession.getWorkoutType())
                .isTemplate(true)
                .templateName(templateName)
                .status(WorkoutSession.SessionStatus.PLANNED)
                .build();

        // Copy exercises
        for (WorkoutExercise originalExercise : originalSession.getExercises()) {
            WorkoutExercise templateExercise = WorkoutExercise.builder()
                    .workoutSession(template)
                    .exercise(originalExercise.getExercise())
                    .exerciseOrder(originalExercise.getExerciseOrder())
                    .plannedSets(originalExercise.getPlannedSets())
                    .plannedReps(originalExercise.getPlannedReps())
                    .plannedWeight(originalExercise.getPlannedWeight())
                    .restTimeSeconds(originalExercise.getRestTimeSeconds())
                    .build();
            
            template.getExercises().add(templateExercise);
        }

        template.setTotalExercises(template.getExercises().size());
        return workoutSessionRepository.save(template);
    }

    /**
     * Calculate session statistics
     */
    private void calculateSessionStatistics(WorkoutSession session) {
        int completedExercises = (int) session.getExercises().stream()
                .filter(WorkoutExercise::isCompleted)
                .count();
        
        session.setCompletedExercises(completedExercises);

        double totalCalories = session.getExercises().stream()
                .filter(we -> we.getCaloriesBurned() != null)
                .mapToDouble(WorkoutExercise::getCaloriesBurned)
                .sum();
        
        session.setTotalCaloriesBurned(totalCalories);

        // Calculate average form score if available
        double avgFormScore = session.getExercises().stream()
                .filter(we -> we.getFormScore() != null)
                .mapToDouble(WorkoutExercise::getFormScore)
                .average()
                .orElse(0.0);
        
        session.setOverallFormScore(avgFormScore);
    }

    /**
     * Validate user ownership of workout session
     */
    private void validateUserOwnership(WorkoutSession session, Long userId) {
        if (!session.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("User does not own this workout session");
        }
    }
} 