package com.fitness.model;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * ExerciseSet entity representing a single set within a workout exercise
 */
@Entity
@Table(name = "exercise_sets", indexes = {
    @Index(name = "idx_set_workout_exercise", columnList = "workout_exercise_id"),
    @Index(name = "idx_set_order", columnList = "set_order")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseSet extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_exercise_id", nullable = false)
    private WorkoutExercise workoutExercise;

    @Column(name = "set_order", nullable = false)
    private Integer setOrder;

    @Column(name = "planned_reps")
    private Integer plannedReps;

    @Column(name = "actual_reps")
    private Integer actualReps;

    @Column(name = "planned_weight") // in kilograms
    private Double plannedWeight;

    @Column(name = "actual_weight") // in kilograms
    private Double actualWeight;

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
    private SetStatus status = SetStatus.PLANNED;

    @Column(name = "calories_burned")
    private Double caloriesBurned;

    @Column(name = "average_heart_rate")
    private Integer averageHeartRate;

    @Column(name = "max_heart_rate")
    private Integer maxHeartRate;

    @Column(name = "rpe_rating") // Rate of Perceived Exertion (1-10)
    private Integer rpeRating;

    @Column(name = "form_score")
    private Double formScore;

    @Column(name = "notes")
    private String notes;

    @Enumerated(EnumType.STRING)
    @Column(name = "set_type")
    private SetType setType = SetType.NORMAL;

    // Helper methods
    public boolean isCompleted() {
        return status == SetStatus.COMPLETED;
    }

    public Double getVolume() {
        if (actualWeight != null && actualReps != null) {
            return actualWeight * actualReps;
        }
        return 0.0;
    }

    public Integer getActualDuration() {
        if (startTime != null && endTime != null) {
            return (int) java.time.Duration.between(startTime, endTime).toSeconds();
        }
        return actualDurationSeconds;
    }

    // Enums
    public enum SetStatus {
        PLANNED, IN_PROGRESS, COMPLETED, SKIPPED, FAILED
    }

    public enum SetType {
        NORMAL, WARM_UP, DROP_SET, SUPERSET, REST_PAUSE, 
        CLUSTER, TEMPO, ISOMETRIC, NEGATIVE, FAILURE
    }
} 