import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useProgressStore = defineStore('progress', () => {
  const achievements = ref([])
  const progressData = ref({
    workouts: [],
    measurements: [],
    goals: []
  })
  const analytics = ref({
    weekly: {},
    monthly: {},
    yearly: {}
  })

  const currentStreak = computed(() => {
    // TODO: Implement streak calculation
    return 0
  })

  const goalProgress = computed(() => {
    return progressData.value.goals.map(goal => ({
      ...goal,
      progress: calculateGoalProgress(goal)
    }))
  })

  const recentAchievements = computed(() => {
    return achievements.value
      .filter(achievement => achievement.unlocked)
      .sort((a, b) => b.unlockedAt - a.unlockedAt)
      .slice(0, 5)
  })

  function calculateGoalProgress(goal) {
    // TODO: Implement goal progress calculation
    return 0
  }

  async function fetchProgressData(timeframe = 'weekly') {
    try {
      // TODO: Implement actual API call
      const response = await fetch(`/api/progress/${timeframe}`, {
        headers: {
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        }
      })

      if (!response.ok) throw new Error('Failed to fetch progress data')
      
      const data = await response.json()
      progressData.value = data.progress
      analytics.value[timeframe] = data.analytics
      
      return true
    } catch (error) {
      console.error('Fetch progress data error:', error)
      return false
    }
  }

  async function fetchAchievements() {
    try {
      // TODO: Implement actual API call
      const response = await fetch('/api/achievements', {
        headers: {
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        }
      })

      if (!response.ok) throw new Error('Failed to fetch achievements')
      
      const data = await response.json()
      achievements.value = data.achievements
      
      return true
    } catch (error) {
      console.error('Fetch achievements error:', error)
      return false
    }
  }

  async function updateGoal(goalId, updates) {
    try {
      // TODO: Implement actual API call
      const response = await fetch(`/api/goals/${goalId}`, {
        method: 'PATCH',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        },
        body: JSON.stringify(updates)
      })

      if (!response.ok) throw new Error('Failed to update goal')
      
      const data = await response.json()
      const goalIndex = progressData.value.goals.findIndex(g => g.id === goalId)
      if (goalIndex !== -1) {
        progressData.value.goals[goalIndex] = data.goal
      }
      
      return true
    } catch (error) {
      console.error('Update goal error:', error)
      return false
    }
  }

  async function addMeasurement(measurement) {
    try {
      // TODO: Implement actual API call
      const response = await fetch('/api/measurements', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        },
        body: JSON.stringify(measurement)
      })

      if (!response.ok) throw new Error('Failed to add measurement')
      
      const data = await response.json()
      progressData.value.measurements.push(data.measurement)
      
      return true
    } catch (error) {
      console.error('Add measurement error:', error)
      return false
    }
  }

  return {
    achievements,
    progressData,
    analytics,
    currentStreak,
    goalProgress,
    recentAchievements,
    fetchProgressData,
    fetchAchievements,
    updateGoal,
    addMeasurement
  }
}) 