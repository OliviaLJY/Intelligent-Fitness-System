package com.fitness.repository;

import com.fitness.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User entity
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    @Query("SELECT u FROM User u WHERE u.enabled = true")
    List<User> findAllActiveUsers();

    @Query("SELECT u FROM User u WHERE u.lastLogin > :since")
    List<User> findUsersActiveAfter(@Param("since") LocalDateTime since);

    @Query("SELECT u FROM User u WHERE :goal MEMBER OF u.fitnessGoals")
    List<User> findByFitnessGoal(@Param("goal") User.FitnessGoal goal);

    @Query("SELECT u FROM User u WHERE u.fitnessLevel = :level")
    List<User> findByFitnessLevel(@Param("level") User.FitnessLevel level);

    @Query("SELECT u FROM User u WHERE u.preferredWorkoutDuration BETWEEN :minDuration AND :maxDuration")
    List<User> findByPreferredWorkoutDuration(@Param("minDuration") Integer minDuration, 
                                             @Param("maxDuration") Integer maxDuration);

    @Query("SELECT COUNT(u) FROM User u WHERE u.createdAt >= :since")
    Long countNewUsersAfter(@Param("since") LocalDateTime since);
} 