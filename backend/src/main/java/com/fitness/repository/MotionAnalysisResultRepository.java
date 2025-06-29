package com.fitness.repository;

import com.fitness.model.MotionAnalysisResult;
import com.fitness.model.WorkoutSession;
import com.fitness.model.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for MotionAnalysisResult entity
 */
@Repository
public interface MotionAnalysisResultRepository extends JpaRepository<MotionAnalysisResult, Long> {

    List<MotionAnalysisResult> findByWorkoutSessionOrderByAnalysisTimestampDesc(WorkoutSession workoutSession);

    List<MotionAnalysisResult> findByWorkoutExerciseOrderByAnalysisTimestampDesc(WorkoutExercise workoutExercise);

    @Query("SELECT mar FROM MotionAnalysisResult mar WHERE mar.workoutSession = :session AND mar.analysisTimestamp BETWEEN :startTime AND :endTime ORDER BY mar.analysisTimestamp")
    List<MotionAnalysisResult> findBySessionAndTimeRange(@Param("session") WorkoutSession session,
                                                         @Param("startTime") LocalDateTime startTime,
                                                         @Param("endTime") LocalDateTime endTime);

    @Query("SELECT mar FROM MotionAnalysisResult mar WHERE mar.workoutExercise = :exercise AND mar.poseDetected = true ORDER BY mar.analysisTimestamp")
    List<MotionAnalysisResult> findSuccessfulAnalysesByExercise(@Param("exercise") WorkoutExercise exercise);

    @Query("SELECT AVG(mar.formScore) FROM MotionAnalysisResult mar WHERE mar.workoutSession = :session AND mar.formScore IS NOT NULL")
    Double getAverageFormScoreForSession(@Param("session") WorkoutSession session);

    @Query("SELECT AVG(mar.formScore) FROM MotionAnalysisResult mar WHERE mar.workoutExercise = :exercise AND mar.formScore IS NOT NULL")
    Double getAverageFormScoreForExercise(@Param("exercise") WorkoutExercise exercise);

    @Query("SELECT COUNT(mar) FROM MotionAnalysisResult mar WHERE mar.workoutSession = :session AND mar.poseDetected = true")
    Long countSuccessfulDetections(@Param("session") WorkoutSession session);

    @Query("SELECT mar FROM MotionAnalysisResult mar WHERE mar.workoutSession.user.id = :userId AND mar.analysisTimestamp >= :since ORDER BY mar.analysisTimestamp DESC")
    List<MotionAnalysisResult> findRecentAnalysesForUser(@Param("userId") Long userId, @Param("since") LocalDateTime since);

    @Query("SELECT mar FROM MotionAnalysisResult mar WHERE mar.formScore < :threshold AND mar.workoutSession = :session")
    List<MotionAnalysisResult> findPoorFormInstances(@Param("session") WorkoutSession session, @Param("threshold") Double threshold);
} 