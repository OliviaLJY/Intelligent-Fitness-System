import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useWorkoutStore = defineStore('workout', () => {
  // State
  const currentWorkout = ref(null)
  const workoutHistory = ref([])
  const workoutTemplates = ref([])
  const exercises = ref([])
  const isLoading = ref(false)
  const error = ref(null)

  // Getters
  const recentWorkouts = computed(() => {
    return workoutHistory.value.slice(0, 5)
  })

  const favoriteExercises = computed(() => {
    return exercises.value.filter(exercise => exercise.isFavorite)
  })

  const exerciseCategories = computed(() => {
    const categories = new Set(exercises.value.map(ex => ex.category))
    return Array.from(categories)
  })

  // Actions
  const startWorkout = async (workoutData) => {
    try {
      isLoading.value = true
      error.value = null
      // API call to start workout
      currentWorkout.value = {
        ...workoutData,
        startTime: new Date(),
        exercises: workoutData.exercises.map(ex => ({
          ...ex,
          completed: false,
          currentSet: 0,
          formScores: []
        }))
      }
    } catch (e) {
      error.value = 'Failed to start workout'
      console.error(e)
    } finally {
      isLoading.value = false
    }
  }

  const endWorkout = async () => {
    if (!currentWorkout.value) return

    try {
      isLoading.value = true
      error.value = null
      // API call to save workout
      const completedWorkout = {
        ...currentWorkout.value,
        endTime: new Date(),
        duration: calculateDuration(currentWorkout.value.startTime, new Date())
      }
      workoutHistory.value.unshift(completedWorkout)
      currentWorkout.value = null
    } catch (e) {
      error.value = 'Failed to end workout'
      console.error(e)
    } finally {
      isLoading.value = false
    }
  }

  const saveWorkoutTemplate = async (template) => {
    try {
      isLoading.value = true
      error.value = null
      // API call to save template
      workoutTemplates.value.push({
        ...template,
        id: Date.now(), // Replace with actual ID from backend
        createdAt: new Date()
      })
    } catch (e) {
      error.value = 'Failed to save workout template'
      console.error(e)
    } finally {
      isLoading.value = false
    }
  }

  const updateExerciseProgress = async (exerciseId, progress) => {
    if (!currentWorkout.value) return

    try {
      const exercise = currentWorkout.value.exercises.find(ex => ex.id === exerciseId)
      if (exercise) {
        exercise.currentSet++
        exercise.formScores.push(progress.formScore)
        if (exercise.currentSet >= exercise.sets) {
          exercise.completed = true
        }
      }
    } catch (e) {
      error.value = 'Failed to update exercise progress'
      console.error(e)
    }
  }

  const fetchWorkoutHistory = async () => {
    try {
      isLoading.value = true
      error.value = null
      // API call to fetch workout history
      // For now, using mock data
      workoutHistory.value = [
        {
          id: 1,
          name: 'Upper Body Strength',
          date: '2024-03-15',
          duration: 45,
          exercises: [
            { name: 'Push-ups', sets: 3, reps: 12, weight: 0 },
            { name: 'Pull-ups', sets: 3, reps: 8, weight: 0 }
          ]
        }
        // Add more workout history...
      ]
    } catch (e) {
      error.value = 'Failed to fetch workout history'
      console.error(e)
    } finally {
      isLoading.value = false
    }
  }

  const fetchExercises = async () => {
    try {
      isLoading.value = true
      error.value = null
      // API call to fetch exercises
      // For now, using mock data
      exercises.value = [
        {
          id: 1,
          name: 'Push-ups',
          category: 'Upper Body',
          muscleGroup: 'Chest',
          difficulty: 'Beginner',
          equipment: 'None',
          instructions: [
            'Start in a plank position',
            'Lower your body until your chest nearly touches the floor',
            'Push your body back up to the starting position'
          ],
          formTips: [
            'Keep your core tight',
            'Maintain a straight body line',
            'Elbows should be at about 45 degrees'
          ],
          commonMistakes: [
            'Sagging hips',
            'Flaring elbows too wide',
            'Not going low enough'
          ]
        }
        // Add more exercises...
      ]
    } catch (e) {
      error.value = 'Failed to fetch exercises'
      console.error(e)
    } finally {
      isLoading.value = false
    }
  }

  // Helper functions
  const calculateDuration = (startTime, endTime) => {
    return Math.round((endTime - startTime) / 1000 / 60) // Duration in minutes
  }

  return {
    // State
    currentWorkout,
    workoutHistory,
    workoutTemplates,
    exercises,
    isLoading,
    error,

    // Getters
    recentWorkouts,
    favoriteExercises,
    exerciseCategories,

    // Actions
    startWorkout,
    endWorkout,
    saveWorkoutTemplate,
    updateExerciseProgress,
    fetchWorkoutHistory,
    fetchExercises
  }
}) 