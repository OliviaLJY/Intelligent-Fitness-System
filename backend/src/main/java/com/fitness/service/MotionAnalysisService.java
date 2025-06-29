package com.fitness.service;

import com.fitness.model.*;
import com.fitness.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * Service class for OpenCV Motion Analysis
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MotionAnalysisService {

    private final MotionAnalysisResultRepository motionAnalysisResultRepository;
    private final WorkoutSessionRepository workoutSessionRepository;

    /**
     * Analyze motion from video frame (simplified version)
     */
    public CompletableFuture<MotionAnalysisResult> analyzeMotion(
            Long workoutSessionId,
            Long workoutExerciseId,
            byte[] frameData,
            int frameNumber) {

        return CompletableFuture.supplyAsync(() -> {
            try {
                WorkoutSession session = workoutSessionRepository.findById(workoutSessionId)
                        .orElseThrow(() -> new IllegalArgumentException("Workout session not found"));

                // Simulate motion analysis
                boolean poseDetected = simulatePoseDetection(frameData);
                double formScore = simulateFormAnalysis();
                String currentPhase = simulatePhaseDetection();

                MotionAnalysisResult result = MotionAnalysisResult.builder()
                        .workoutSession(session)
                        .analysisTimestamp(LocalDateTime.now())
                        .frameNumber(frameNumber)
                        .confidenceScore(poseDetected ? 0.85 : 0.0)
                        .formScore(formScore)
                        .poseDetected(poseDetected)
                        .currentPhase(currentPhase)
                        .symmetryScore(0.85)
                        .stabilityScore(0.80)
                        .tempoScore(0.75)
                        .depthScore(0.82)
                        .feedbackMessage(generateFeedback(formScore))
                        .build();

                return motionAnalysisResultRepository.save(result);

            } catch (Exception e) {
                log.error("Error analyzing motion: {}", e.getMessage());
                throw new RuntimeException("Motion analysis failed", e);
            }
        });
    }

    /**
     * Get analysis statistics for session
     */
    @Transactional(readOnly = true)
    public AnalysisStats getSessionStats(Long sessionId) {
        WorkoutSession session = workoutSessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));

        List<MotionAnalysisResult> results = motionAnalysisResultRepository
                .findByWorkoutSessionOrderByAnalysisTimestampDesc(session);

        return AnalysisStats.builder()
                .sessionId(sessionId)
                .totalFrames(results.size())
                .successfulDetections((int) results.stream().filter(r -> r.getPoseDetected()).count())
                .averageFormScore(results.stream()
                        .filter(r -> r.getFormScore() != null)
                        .mapToDouble(MotionAnalysisResult::getFormScore)
                        .average().orElse(0.0))
                .build();
    }

    // Simulation methods
    private boolean simulatePoseDetection(byte[] frameData) {
        return frameData != null && frameData.length > 1000;
    }

    private double simulateFormAnalysis() {
        return 0.75 + (Math.random() * 0.2);
    }

    private String simulatePhaseDetection() {
        String[] phases = {"concentric", "eccentric", "isometric"};
        return phases[(int) (Math.random() * phases.length)];
    }

    private String generateFeedback(double formScore) {
        if (formScore >= 0.9) return "Excellent form!";
        if (formScore >= 0.8) return "Good form with minor adjustments needed.";
        if (formScore >= 0.7) return "Fair form. Focus on improvement areas.";
        return "Form needs improvement. Review technique.";
    }

    // Inner class for stats
    public static class AnalysisStats {
        private Long sessionId;
        private Integer totalFrames;
        private Integer successfulDetections;
        private Double averageFormScore;

        public static AnalysisStatsBuilder builder() {
            return new AnalysisStatsBuilder();
        }

        // Getters
        public Long getSessionId() { return sessionId; }
        public Integer getTotalFrames() { return totalFrames; }
        public Integer getSuccessfulDetections() { return successfulDetections; }
        public Double getAverageFormScore() { return averageFormScore; }

        // Builder
        public static class AnalysisStatsBuilder {
            private Long sessionId;
            private Integer totalFrames;
            private Integer successfulDetections;
            private Double averageFormScore;

            public AnalysisStatsBuilder sessionId(Long sessionId) {
                this.sessionId = sessionId;
                return this;
            }

            public AnalysisStatsBuilder totalFrames(Integer totalFrames) {
                this.totalFrames = totalFrames;
                return this;
            }

            public AnalysisStatsBuilder successfulDetections(Integer successfulDetections) {
                this.successfulDetections = successfulDetections;
                return this;
            }

            public AnalysisStatsBuilder averageFormScore(Double averageFormScore) {
                this.averageFormScore = averageFormScore;
                return this;
            }

            public AnalysisStats build() {
                AnalysisStats stats = new AnalysisStats();
                stats.sessionId = this.sessionId;
                stats.totalFrames = this.totalFrames;
                stats.successfulDetections = this.successfulDetections;
                stats.averageFormScore = this.averageFormScore;
                return stats;
            }
        }
    }
} 