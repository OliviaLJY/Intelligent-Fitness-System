package com.fitness.model;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * UserProgress entity tracking user's fitness progress over time
 */
@Entity
@Table(name = "user_progress", indexes = {
    @Index(name = "idx_progress_user", columnList = "user_id"),
    @Index(name = "idx_progress_date", columnList = "record_date"),
    @Index(name = "idx_progress_metric", columnList = "metric_type")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProgress extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "metric_type", nullable = false)
    private MetricType metricType;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "unit", length = 20)
    private String unit;

    @Column(name = "exercise_name", length = 100)
    private String exerciseName; // For exercise-specific progress

    @Column(name = "body_part", length = 50)
    private String bodyPart; // For body measurement progress

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "target_value")
    private Double targetValue;

    @Column(name = "improvement_percentage")
    private Double improvementPercentage;

    // Helper methods
    public boolean hasReachedTarget() {
        return targetValue != null && value != null && value >= targetValue;
    }

    public Double getProgressToTarget() {
        if (targetValue == null || targetValue == 0) return null;
        return (value / targetValue) * 100.0;
    }

    // Enums
    public enum MetricType {
        // Body measurements
        WEIGHT, HEIGHT, BODY_FAT_PERCENTAGE, MUSCLE_MASS, BMI,
        WAIST_CIRCUMFERENCE, CHEST_CIRCUMFERENCE, ARM_CIRCUMFERENCE,
        THIGH_CIRCUMFERENCE, NECK_CIRCUMFERENCE,
        
        // Performance metrics
        MAX_WEIGHT, TOTAL_VOLUME, ENDURANCE_TIME, REPS_MAX,
        CARDIO_DISTANCE, CARDIO_SPEED, HEART_RATE_MAX,
        RECOVERY_HEART_RATE, VO2_MAX,
        
        // Flexibility and mobility
        FLEXIBILITY_SCORE, RANGE_OF_MOTION, BALANCE_SCORE,
        
        // Workout metrics
        WORKOUT_FREQUENCY, WORKOUT_DURATION, CALORIES_BURNED,
        FORM_SCORE_AVERAGE, CONSISTENCY_SCORE,
        
        // Health metrics
        BLOOD_PRESSURE_SYSTOLIC, BLOOD_PRESSURE_DIASTOLIC,
        RESTING_HEART_RATE, SLEEP_HOURS, STRESS_LEVEL,
        
        // Goals and milestones
        GOAL_COMPLETION, STREAK_DAYS, PERSONAL_RECORD
    }
} 