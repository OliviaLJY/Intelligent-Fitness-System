package com.fitness.model;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * WorkoutExercise entity representing an exercise within a workout session
 */
@Entity
@Table(name = "workout_exercises", indexes = {
    @Index(name = "idx_workout_exercise_session", columnList = "workout_session_id"),
    @Index(name = "idx_workout_exercise_exercise", columnList = "exercise_id"),
    @Index(name = "idx_workout_exercise_order", columnList = "exercise_order")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutExercise extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_session_id", nullable = false)
    private WorkoutSession workoutSession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(name = "exercise_order", nullable = false)
    private Integer exerciseOrder;

    @Column(name = "planned_sets")
    private Integer plannedSets;

    @Column(name = "completed_sets")
    private Integer completedSets;

    @Column(name = "planned_reps")
    private Integer plannedReps;

    @Column(name = "planned_weight") // in kilograms
    private Double plannedWeight;

    @Column(name = "planned_duration_seconds")
    private Integer plannedDurationSeconds;

    @Column(name = "actual_duration_seconds")
    private Integer actualDurationSeconds;

    @Column(name = "rest_time_seconds")
    private Integer restTimeSeconds;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ExerciseStatus status = ExerciseStatus.PLANNED;

    @Column(name = "calories_burned")
    private Double caloriesBurned;

    @Column(name = "average_heart_rate")
    private Integer averageHeartRate;

    @Column(name = "max_heart_rate")
    private Integer maxHeartRate;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "difficulty_rating") // 1-10 scale
    private Integer difficultyRating;

    @Column(name = "rpe_rating") // Rate of Perceived Exertion (1-10)
    private Integer rpeRating;

    // AI Analysis Results
    @Column(name = "form_score")
    private Double formScore;

    @Column(name = "technique_feedback", columnDefinition = "TEXT")
    private String techniqueFeedback;

    @Column(name = "motion_analysis_enabled")
    private Boolean motionAnalysisEnabled = false;

    // Relationships
    @OneToMany(mappedBy = "workoutExercise", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExerciseSet> sets = new ArrayList<>();

    @OneToMany(mappedBy = "workoutExercise", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MotionAnalysisResult> motionAnalysisResults = new ArrayList<>();

    // Helper methods
    public boolean isCompleted() {
        return status == ExerciseStatus.COMPLETED;
    }

    public boolean isActive() {
        return status == ExerciseStatus.IN_PROGRESS;
    }

    public Double getCompletionPercentage() {
        if (plannedSets == null || plannedSets == 0) return 0.0;
        return (completedSets != null ? completedSets : 0) * 100.0 / plannedSets;
    }

    public Integer getActualDuration() {
        if (startTime != null && endTime != null) {
            return (int) java.time.Duration.between(startTime, endTime).toSeconds();
        }
        return actualDurationSeconds;
    }

    public Double getTotalVolume() {
        return sets.stream()
                .mapToDouble(set -> (set.getActualWeight() != null ? set.getActualWeight() : 0) * 
                            (set.getActualReps() != null ? set.getActualReps() : 0))
                .sum();
    }

    // Enums
    public enum ExerciseStatus {
        PLANNED, IN_PROGRESS, COMPLETED, SKIPPED, PAUSED
    }
} 