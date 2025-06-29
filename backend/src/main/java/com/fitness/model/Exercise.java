package com.fitness.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Exercise entity representing different workout exercises
 */
@Entity
@Table(name = "exercises", indexes = {
    @Index(name = "idx_exercise_name", columnList = "name"),
    @Index(name = "idx_exercise_category", columnList = "category"),
    @Index(name = "idx_exercise_difficulty", columnList = "difficulty_level")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    @NotBlank(message = "Exercise name is required")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "instructions", columnDefinition = "TEXT")
    private String instructions;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private ExerciseCategory category;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty_level", nullable = false)
    private DifficultyLevel difficultyLevel;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "exercise_muscle_groups", joinColumns = @JoinColumn(name = "exercise_id"))
    @Column(name = "muscle_group")
    private Set<MuscleGroup> primaryMuscleGroups = new HashSet<>();

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "exercise_secondary_muscles", joinColumns = @JoinColumn(name = "exercise_id"))
    @Column(name = "muscle_group")
    private Set<MuscleGroup> secondaryMuscleGroups = new HashSet<>();

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "exercise_equipment", joinColumns = @JoinColumn(name = "exercise_id"))
    @Column(name = "equipment")
    private Set<Equipment> requiredEquipment = new HashSet<>();

    @Column(name = "duration_minutes")
    @Positive(message = "Duration must be positive")
    private Integer durationMinutes;

    @Column(name = "calories_per_minute")
    @Positive(message = "Calories per minute must be positive")
    private Double caloriesPerMinute;

    @Column(name = "default_sets")
    @Positive(message = "Default sets must be positive")
    private Integer defaultSets;

    @Column(name = "default_reps")
    @Positive(message = "Default reps must be positive")
    private Integer defaultReps;

    @Column(name = "default_weight") // in kilograms
    @Positive(message = "Default weight must be positive")
    private Double defaultWeight;

    @Column(name = "rest_time_seconds")
    @Positive(message = "Rest time must be positive")
    private Integer restTimeSeconds;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "opencv_model_name")
    private String openCvModelName; // For motion analysis

    @ElementCollection
    @CollectionTable(name = "exercise_key_points", joinColumns = @JoinColumn(name = "exercise_id"))
    @Column(name = "key_point")
    private List<String> keyPoints = new ArrayList<>(); // Body key points to track

    @Column(name = "motion_analysis_enabled")
    private Boolean motionAnalysisEnabled = false;

    @Column(name = "form_check_threshold")
    private Double formCheckThreshold = 0.7; // Confidence threshold for form checking

    // Motion analysis parameters
    @Embedded
    private MotionAnalysisParams motionAnalysisParams;

    // Relationships
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WorkoutExercise> workoutExercises = new ArrayList<>();

    // Helper methods
    public boolean requiresEquipment() {
        return requiredEquipment != null && !requiredEquipment.isEmpty();
    }

    public boolean isCardio() {
        return category == ExerciseCategory.CARDIO;
    }

    public boolean isStrength() {
        return category == ExerciseCategory.STRENGTH || category == ExerciseCategory.POWERLIFTING;
    }

    // Enums
    public enum ExerciseCategory {
        STRENGTH, CARDIO, FLEXIBILITY, POWERLIFTING, 
        OLYMPIC_LIFTING, BODYWEIGHT, REHABILITATION, 
        SPORTS_SPECIFIC, YOGA, PILATES
    }

    public enum DifficultyLevel {
        BEGINNER, INTERMEDIATE, ADVANCED, EXPERT
    }

    public enum MuscleGroup {
        CHEST, BACK, SHOULDERS, BICEPS, TRICEPS, FOREARMS,
        QUADRICEPS, HAMSTRINGS, GLUTES, CALVES, ABS, OBLIQUES,
        LOWER_BACK, TRAPS, LATS, NECK, FULL_BODY
    }

    public enum Equipment {
        NONE, DUMBBELLS, BARBELL, KETTLEBELL, RESISTANCE_BANDS,
        PULL_UP_BAR, BENCH, SQUAT_RACK, CABLE_MACHINE,
        TREADMILL, ELLIPTICAL, STATIONARY_BIKE, ROWING_MACHINE,
        MEDICINE_BALL, STABILITY_BALL, FOAM_ROLLER, YOGA_MAT,
        SUSPENSION_TRAINER, BATTLE_ROPES, GYMNASTIC_RINGS
    }

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MotionAnalysisParams {
        
        @Column(name = "min_detection_confidence")
        private Double minDetectionConfidence = 0.5;
        
        @Column(name = "min_tracking_confidence")
        private Double minTrackingConfidence = 0.5;
        
        @Column(name = "pose_smoothing")
        private Boolean poseSmoothing = true;
        
        @Column(name = "enable_segmentation")
        private Boolean enableSegmentation = false;
        
        @Column(name = "frame_rate")
        private Integer frameRate = 30;
        
        @Column(name = "analysis_window_size")
        private Integer analysisWindowSize = 10; // Number of frames to analyze
    }
} 