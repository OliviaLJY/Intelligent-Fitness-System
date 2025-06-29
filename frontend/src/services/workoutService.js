import { api } from './api.js'

export const workoutService = {
  // Workout Sessions
  async getWorkouts() {
    const response = await api.get('/workouts')
    return response.data
  },

  async getWorkout(id) {
    const response = await api.get(`/workouts/${id}`)
    return response.data
  },

  async createWorkout(workoutData) {
    const response = await api.post('/workouts', workoutData)
    return response.data
  },

  async updateWorkout(id, workoutData) {
    const response = await api.put(`/workouts/${id}`, workoutData)
    return response.data
  },

  async deleteWorkout(id) {
    await api.delete(`/workouts/${id}`)
  },

  async startWorkout(id) {
    const response = await api.post(`/workouts/${id}/start`)
    return response.data
  },

  async endWorkout(id) {
    const response = await api.post(`/workouts/${id}/end`)
    return response.data
  },

  // Workout Templates
  async getTemplates() {
    const response = await api.get('/workouts/templates')
    return response.data
  },

  async getActiveWorkout() {
    const response = await api.get('/workouts/active')
    return response.data
  },

  // Exercises
  async getExercises() {
    const response = await api.get('/exercises')
    return response.data
  },

  async getPublicExercises() {
    const response = await api.get('/exercises/public')
    return response.data
  },

  async getExercise(id) {
    const response = await api.get(`/exercises/${id}`)
    return response.data
  },

  async createExercise(exerciseData) {
    const response = await api.post('/exercises', exerciseData)
    return response.data
  },

  // Progress tracking
  async getProgress() {
    const response = await api.get('/progress')
    return response.data
  },

  async recordProgress(progressData) {
    const response = await api.post('/progress', progressData)
    return response.data
  },

  // Motion Analysis
  async getAnalysisResults(sessionId) {
    const response = await api.get(`/analysis/session/${sessionId}`)
    return response.data
  },

  async uploadVideo(formData) {
    const response = await api.post('/analysis/upload', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    })
    return response.data
  }
} 