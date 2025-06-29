package com.fitness.model;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * MotionAnalysisResult entity storing OpenCV motion analysis results
 */
@Entity
@Table(name = "motion_analysis_results", indexes = {
    @Index(name = "idx_analysis_session", columnList = "workout_session_id"),
    @Index(name = "idx_analysis_exercise", columnList = "workout_exercise_id"),
    @Index(name = "idx_analysis_timestamp", columnList = "analysis_timestamp")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MotionAnalysisResult extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_session_id", nullable = false)
    private WorkoutSession workoutSession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_exercise_id")
    private WorkoutExercise workoutExercise;

    @Column(name = "analysis_timestamp", nullable = false)
    private LocalDateTime analysisTimestamp;

    @Column(name = "frame_number")
    private Integer frameNumber;

    @Column(name = "video_timestamp_ms")
    private Long videoTimestampMs;

    @Column(name = "confidence_score")
    private Double confidenceScore;

    @Column(name = "form_score")
    private Double formScore;

    @Column(name = "pose_detected")
    private Boolean poseDetected = false;

    @Column(name = "rep_count")
    private Integer repCount;

    @Column(name = "current_phase")
    private String currentPhase; // e.g., "eccentric", "concentric", "isometric"

    @ElementCollection
    @CollectionTable(name = "pose_landmarks", joinColumns = @JoinColumn(name = "analysis_result_id"))
    @MapKeyColumn(name = "landmark_name")
    @Column(name = "landmark_data", columnDefinition = "TEXT")
    private Map<String, String> poseLandmarks; // JSON data for each landmark

    @ElementCollection
    @CollectionTable(name = "joint_angles", joinColumns = @JoinColumn(name = "analysis_result_id"))
    @MapKeyColumn(name = "joint_name")
    @Column(name = "angle_degrees")
    private Map<String, Double> jointAngles;

    @Column(name = "velocity_data", columnDefinition = "TEXT")
    private String velocityData; // JSON data for movement velocity

    @Column(name = "acceleration_data", columnDefinition = "TEXT")
    private String accelerationData; // JSON data for movement acceleration

    @Column(name = "range_of_motion", columnDefinition = "TEXT")
    private String rangeOfMotion; // JSON data for ROM analysis

    @Column(name = "symmetry_score")
    private Double symmetryScore; // Bilateral movement symmetry

    @Column(name = "stability_score")
    private Double stabilityScore; // Movement stability

    @Column(name = "tempo_score")
    private Double tempoScore; // Movement tempo consistency

    @Column(name = "depth_score")
    private Double depthScore; // Movement depth/range

    @ElementCollection
    @CollectionTable(name = "form_errors", joinColumns = @JoinColumn(name = "analysis_result_id"))
    @Column(name = "error_type")
    private java.util.List<String> formErrors; // Detected form issues

    @Column(name = "feedback_message", columnDefinition = "TEXT")
    private String feedbackMessage;

    @Column(name = "improvement_suggestions", columnDefinition = "TEXT")
    private String improvementSuggestions;

    @Column(name = "analysis_model_version")
    private String analysisModelVersion;

    @Column(name = "processing_time_ms")
    private Long processingTimeMs;

    // Helper methods
    public boolean hasGoodForm() {
        return formScore != null && formScore >= 0.8;
    }

    public boolean isPoseReliable() {
        return confidenceScore != null && confidenceScore >= 0.7;
    }

    public String getAnalysisPhase() {
        return currentPhase != null ? currentPhase : "unknown";
    }

    public Double getAverageFormScore() {
        if (formScore == null) return null;
        // Calculate average form score considering all score components
        double totalScore = 0.0;
        int components = 0;
        
        if (formScore != null) { totalScore += formScore; components++; }
        if (symmetryScore != null) { totalScore += symmetryScore; components++; }
        if (stabilityScore != null) { totalScore += stabilityScore; components++; }
        if (tempoScore != null) { totalScore += tempoScore; components++; }
        if (depthScore != null) { totalScore += depthScore; components++; }
        
        return components > 0 ? totalScore / components : formScore;
    }
} 