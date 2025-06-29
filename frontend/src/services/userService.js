import { api } from './api.js'

export const userService = {
  // Profile Management
  async getProfile() {
    const response = await api.get('/users/profile')
    return response.data
  },

  async updateProfile(profileData) {
    const response = await api.put('/users/profile', profileData)
    return response.data
  },

  async uploadProfilePhoto(file) {
    const formData = new FormData()
    formData.append('photo', file)
    const response = await api.post('/users/profile/photo', formData, {
      headers: { 'Content-Type': 'multipart/form-data' }
    })
    return response.data
  },

  // Body Measurements
  async addBodyMeasurement(measurement) {
    const response = await api.post('/users/measurements', measurement)
    return response.data
  },

  async getMeasurementHistory() {
    const response = await api.get('/users/measurements')
    return response.data
  },

  // Goals Management
  async setGoals(goals) {
    const response = await api.post('/users/goals', goals)
    return response.data
  },

  async getGoals() {
    const response = await api.get('/users/goals')
    return response.data
  },

  // Preferences
  async updatePreferences(preferences) {
    const response = await api.put('/users/preferences', preferences)
    return response.data
  },

  // Statistics
  async getStats() {
    const response = await api.get('/users/stats')
    return response.data
  }
} 