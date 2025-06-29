import { api } from './api.js'

export const analyticsService = {
  // Performance Analytics
  async getPerformanceMetrics(timeRange = '30d') {
    const response = await api.get(`/analytics/performance?range=${timeRange}`)
    return response.data
  },

  async getStrengthProgression(exerciseId, timeRange = '90d') {
    const response = await api.get(`/analytics/strength/${exerciseId}?range=${timeRange}`)
    return response.data
  },

  async getVolumeAnalysis(timeRange = '30d') {
    const response = await api.get(`/analytics/volume?range=${timeRange}`)
    return response.data
  },

  // Comparative Analytics
  async compareWithSimilarUsers(userCriteria) {
    const response = await api.post('/analytics/compare', userCriteria)
    return response.data
  },

  async getLeaderboard(category = 'volume', timeRange = '7d') {
    const response = await api.get(`/analytics/leaderboard?category=${category}&range=${timeRange}`)
    return response.data
  },

  // Predictive Analytics
  async getPredictedGoalCompletion(goalId) {
    const response = await api.get(`/analytics/predictions/goal/${goalId}`)
    return response.data
  },

  async getInjuryRiskAssessment() {
    const response = await api.get('/analytics/injury-risk')
    return response.data
  },

  async getOptimalRestRecommendations() {
    const response = await api.get('/analytics/rest-recommendations')
    return response.data
  },

  // Detailed Reports
  async generateWorkoutReport(workoutId) {
    const response = await api.get(`/analytics/workout-report/${workoutId}`)
    return response.data
  },

  async getMonthlyReport(year, month) {
    const response = await api.get(`/analytics/monthly-report/${year}/${month}`)
    return response.data
  },

  async exportUserData(format = 'json') {
    const response = await api.get(`/analytics/export?format=${format}`, {
      responseType: 'blob'
    })
    return response.data
  },

  // Real-time Metrics
  async getCurrentWorkoutMetrics(workoutId) {
    const response = await api.get(`/analytics/current/${workoutId}`)
    return response.data
  },

  async getHeartRateZoneAnalysis(workoutId) {
    const response = await api.get(`/analytics/heart-rate-zones/${workoutId}`)
    return response.data
  },

  // Personalized Insights
  async getPersonalizedInsights() {
    const response = await api.get('/analytics/insights')
    return response.data
  },

  async getWorkoutRecommendations() {
    const response = await api.get('/analytics/recommendations')
    return response.data
  },

  // Progress Tracking
  async trackPersonalRecord(exerciseId, recordData) {
    const response = await api.post(`/analytics/personal-records/${exerciseId}`, recordData)
    return response.data
  },

  async getPersonalRecords(exerciseId = null) {
    const url = exerciseId 
      ? `/analytics/personal-records/${exerciseId}`
      : '/analytics/personal-records'
    const response = await api.get(url)
    return response.data
  },

  // Body Composition Analytics
  async getBodyCompositionTrends(timeRange = '180d') {
    const response = await api.get(`/analytics/body-composition?range=${timeRange}`)
    return response.data
  },

  async getMuscleGroupBalance() {
    const response = await api.get('/analytics/muscle-balance')
    return response.data
  }
} 