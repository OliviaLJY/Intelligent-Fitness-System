package com.fitness.repository;

import com.fitness.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Exercise entity
 */
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByNameContainingIgnoreCase(String name);

    List<Exercise> findByCategory(Exercise.ExerciseCategory category);

    List<Exercise> findByDifficultyLevel(Exercise.DifficultyLevel difficultyLevel);

    @Query("SELECT e FROM Exercise e WHERE e.isActive = true")
    List<Exercise> findAllActiveExercises();

    @Query("SELECT e FROM Exercise e WHERE :muscleGroup MEMBER OF e.primaryMuscleGroups")
    List<Exercise> findByPrimaryMuscleGroup(@Param("muscleGroup") Exercise.MuscleGroup muscleGroup);

    @Query("SELECT e FROM Exercise e WHERE :equipment MEMBER OF e.requiredEquipment OR e.requiredEquipment IS EMPTY")
    List<Exercise> findByAvailableEquipment(@Param("equipment") Exercise.Equipment equipment);

    @Query("SELECT e FROM Exercise e WHERE e.motionAnalysisEnabled = true")
    List<Exercise> findMotionAnalysisEnabledExercises();

    @Query("SELECT e FROM Exercise e WHERE e.category = :category AND e.difficultyLevel = :difficulty AND e.isActive = true")
    List<Exercise> findByCategoryAndDifficulty(@Param("category") Exercise.ExerciseCategory category,
                                              @Param("difficulty") Exercise.DifficultyLevel difficulty);

    @Query("SELECT e FROM Exercise e WHERE e.durationMinutes BETWEEN :minDuration AND :maxDuration")
    List<Exercise> findByDurationRange(@Param("minDuration") Integer minDuration, 
                                      @Param("maxDuration") Integer maxDuration);
} 