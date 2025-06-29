package com.fitness.repository;

import com.fitness.model.WorkoutSession;
import com.fitness.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for WorkoutSession entity
 */
@Repository
public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {

    List<WorkoutSession> findByUserOrderByStartTimeDesc(User user);

    List<WorkoutSession> findByUserAndStatus(User user, WorkoutSession.SessionStatus status);

    @Query("SELECT ws FROM WorkoutSession ws WHERE ws.user = :user AND ws.startTime BETWEEN :startDate AND :endDate ORDER BY ws.startTime DESC")
    List<WorkoutSession> findByUserAndDateRange(@Param("user") User user,
                                               @Param("startDate") LocalDateTime startDate,
                                               @Param("endDate") LocalDateTime endDate);

    @Query("SELECT ws FROM WorkoutSession ws WHERE ws.user = :user AND ws.status = 'IN_PROGRESS'")
    Optional<WorkoutSession> findActiveSessionByUser(@Param("user") User user);

    @Query("SELECT ws FROM WorkoutSession ws WHERE ws.user = :user AND ws.status = 'COMPLETED' ORDER BY ws.endTime DESC")
    List<WorkoutSession> findCompletedSessionsByUser(@Param("user") User user);

    @Query("SELECT ws FROM WorkoutSession ws WHERE ws.isTemplate = true")
    List<WorkoutSession> findAllTemplates();

    @Query("SELECT ws FROM WorkoutSession ws WHERE ws.user = :user AND ws.isTemplate = true")
    List<WorkoutSession> findTemplatesByUser(@Param("user") User user);

    @Query("SELECT ws FROM WorkoutSession ws WHERE ws.workoutType = :type")
    List<WorkoutSession> findByWorkoutType(@Param("type") WorkoutSession.WorkoutType type);

    @Query("SELECT COUNT(ws) FROM WorkoutSession ws WHERE ws.user = :user AND ws.status = 'COMPLETED' AND ws.startTime >= :since")
    Long countCompletedSessionsAfter(@Param("user") User user, @Param("since") LocalDateTime since);

    @Query("SELECT AVG(ws.actualDurationMinutes) FROM WorkoutSession ws WHERE ws.user = :user AND ws.status = 'COMPLETED' AND ws.actualDurationMinutes IS NOT NULL")
    Double getAverageWorkoutDuration(@Param("user") User user);

    @Query("SELECT SUM(ws.totalCaloriesBurned) FROM WorkoutSession ws WHERE ws.user = :user AND ws.status = 'COMPLETED' AND ws.totalCaloriesBurned IS NOT NULL")
    Double getTotalCaloriesBurned(@Param("user") User user);

    @Query("SELECT AVG(ws.overallFormScore) FROM WorkoutSession ws WHERE ws.user = :user AND ws.overallFormScore IS NOT NULL")
    Double getAverageFormScore(@Param("user") User user);
} 