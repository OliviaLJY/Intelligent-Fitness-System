package com.fitness.model;

import javax.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * WorkoutSession entity representing a user's workout session
 */
@Entity
@Table(name = "workout_sessions", indexes = {
    @Index(name = "idx_session_user", columnList = "user_id"),
    @Index(name = "idx_session_date", columnList = "start_time"),
    @Index(name = "idx_session_status", columnList = "status")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutSession extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference
    @JsonIgnoreProperties({"workoutSessions", "progressRecords", "preferredWorkoutDays", "password", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "authorities"})
    private User user;

    @Column(name = "session_name", length = 100)
    private String sessionName;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "planned_duration_minutes")
    private Integer plannedDurationMinutes;

    @Column(name = "actual_duration_minutes")
    private Integer actualDurationMinutes;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SessionStatus status = SessionStatus.PLANNED;

    @Column(name = "total_calories_burned")
    private Double totalCaloriesBurned;

    @Column(name = "total_exercises")
    private Integer totalExercises;

    @Column(name = "completed_exercises")
    private Integer completedExercises;

    @Column(name = "average_heart_rate")
    private Integer averageHeartRate;

    @Column(name = "max_heart_rate")
    private Integer maxHeartRate;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "difficulty_rating") // 1-10 scale
    private Integer difficultyRating;

    @Column(name = "user_rating") // 1-5 scale
    private Integer userRating;

    @Enumerated(EnumType.STRING)
    @Column(name = "workout_type")
    private WorkoutType workoutType;

    @Column(name = "is_template")
    private Boolean isTemplate = false;

    @Column(name = "template_name")
    private String templateName;

    // AI Analysis Results
    @Column(name = "overall_form_score")
    private Double overallFormScore;

    @Column(name = "ai_feedback", columnDefinition = "TEXT")
    private String aiFeedback;

    @Column(name = "improvement_suggestions", columnDefinition = "TEXT")
    private String improvementSuggestions;

    // Relationships
    @OneToMany(mappedBy = "workoutSession", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<WorkoutExercise> exercises = new ArrayList<>();

    @OneToMany(mappedBy = "workoutSession", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MotionAnalysisResult> motionAnalysisResults = new ArrayList<>();

    // Helper methods
    public boolean isCompleted() {
        return status == SessionStatus.COMPLETED;
    }

    public boolean isActive() {
        return status == SessionStatus.IN_PROGRESS;
    }

    public Double getCompletionPercentage() {
        if (totalExercises == null || totalExercises == 0) return 0.0;
        return (completedExercises != null ? completedExercises : 0) * 100.0 / totalExercises;
    }

    public Integer getActualDuration() {
        if (startTime != null && endTime != null) {
            return (int) java.time.Duration.between(startTime, endTime).toMinutes();
        }
        return actualDurationMinutes;
    }

    // Enums
    public enum SessionStatus {
        PLANNED, IN_PROGRESS, PAUSED, COMPLETED, CANCELLED, SKIPPED
    }

    public enum WorkoutType {
        STRENGTH_TRAINING, CARDIO, HIIT, FLEXIBILITY, POWERLIFTING,
        BODYWEIGHT, CIRCUIT_TRAINING, REHABILITATION, SPORTS_SPECIFIC,
        YOGA, PILATES, CROSSFIT, FUNCTIONAL, MIXED
    }
} 