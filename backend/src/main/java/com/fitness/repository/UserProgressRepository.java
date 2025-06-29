package com.fitness.repository;

import com.fitness.model.User;
import com.fitness.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for UserProgress entity
 */
@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {

    List<UserProgress> findByUserOrderByRecordDateDesc(User user);

    List<UserProgress> findByUserAndMetricTypeOrderByRecordDateDesc(User user, UserProgress.MetricType metricType);

    @Query("SELECT up FROM UserProgress up WHERE up.user = :user AND up.recordDate BETWEEN :startDate AND :endDate ORDER BY up.recordDate DESC")
    List<UserProgress> findByUserAndDateRange(@Param("user") User user,
                                             @Param("startDate") LocalDate startDate,
                                             @Param("endDate") LocalDate endDate);

    @Query("SELECT up FROM UserProgress up WHERE up.user = :user AND up.metricType = :metricType AND up.recordDate BETWEEN :startDate AND :endDate ORDER BY up.recordDate DESC")
    List<UserProgress> findByUserMetricTypeAndDateRange(@Param("user") User user,
                                                        @Param("metricType") UserProgress.MetricType metricType,
                                                        @Param("startDate") LocalDate startDate,
                                                        @Param("endDate") LocalDate endDate);

    @Query("SELECT up FROM UserProgress up WHERE up.user = :user AND up.metricType = :metricType ORDER BY up.recordDate DESC")
    Optional<UserProgress> findLatestByUserAndMetricType(@Param("user") User user,
                                                        @Param("metricType") UserProgress.MetricType metricType);

    @Query("SELECT up FROM UserProgress up WHERE up.user = :user AND up.exerciseName = :exerciseName ORDER BY up.recordDate DESC")
    List<UserProgress> findByUserAndExercise(@Param("user") User user, @Param("exerciseName") String exerciseName);

    @Query("SELECT DISTINCT up.exerciseName FROM UserProgress up WHERE up.user = :user AND up.exerciseName IS NOT NULL")
    List<String> findExerciseNamesWithProgress(@Param("user") User user);

    @Query("SELECT COUNT(up) FROM UserProgress up WHERE up.user = :user AND up.recordDate >= :since")
    Long countProgressRecordsAfter(@Param("user") User user, @Param("since") LocalDate since);

    @Query("SELECT up FROM UserProgress up WHERE up.user = :user AND up.targetValue IS NOT NULL AND up.value >= up.targetValue")
    List<UserProgress> findAchievedTargets(@Param("user") User user);
} 