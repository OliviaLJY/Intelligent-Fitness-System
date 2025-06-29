import { api } from './api.js'

class NotificationService {
  constructor() {
    this.permission = 'default'
    this.registration = null
    this.isSupported = 'serviceWorker' in navigator && 'PushManager' in window
  }

  // Initialize push notifications
  async init() {
    if (!this.isSupported) {
      console.warn('Push notifications not supported')
      return false
    }

    // Request permission
    this.permission = await this.requestPermission()
    
    if (this.permission === 'granted') {
      await this.registerServiceWorker()
      await this.subscribeToPush()
      return true
    }
    
    return false
  }

  async requestPermission() {
    if (!('Notification' in window)) {
      return 'denied'
    }

    const permission = await Notification.requestPermission()
    return permission
  }

  async registerServiceWorker() {
    try {
      this.registration = await navigator.serviceWorker.register('/sw.js')
      console.log('Service Worker registered:', this.registration)
    } catch (error) {
      console.error('Service Worker registration failed:', error)
    }
  }

  async subscribeToPush() {
    if (!this.registration) {
      return
    }

    try {
      const subscription = await this.registration.pushManager.subscribe({
        userVisibleOnly: true,
        applicationServerKey: await this.getVapidKey()
      })

      // Send subscription to backend
      await api.post('/notifications/subscribe', {
        subscription: subscription.toJSON()
      })

      console.log('Push subscription successful:', subscription)
    } catch (error) {
      console.error('Push subscription failed:', error)
    }
  }

  async getVapidKey() {
    const response = await api.get('/notifications/vapid-key')
    return response.data.key
  }

  // Notification Management
  async getNotifications(page = 1, limit = 20) {
    const response = await api.get(`/notifications?page=${page}&limit=${limit}`)
    return response.data
  }

  async markAsRead(notificationId) {
    const response = await api.put(`/notifications/${notificationId}/read`)
    return response.data
  }

  async markAllAsRead() {
    const response = await api.put('/notifications/read-all')
    return response.data
  }

  async deleteNotification(notificationId) {
    const response = await api.delete(`/notifications/${notificationId}`)
    return response.data
  }

  // Preferences
  async updatePreferences(preferences) {
    const response = await api.put('/notifications/preferences', preferences)
    return response.data
  }

  async getPreferences() {
    const response = await api.get('/notifications/preferences')
    return response.data
  }

  // Manual notifications
  showLocalNotification(title, options = {}) {
    if (this.permission === 'granted') {
      new Notification(title, {
        icon: '/icon-192x192.png',
        badge: '/badge-72x72.png',
        ...options
      })
    }
  }

  // Smart reminder setup
  async setWorkoutReminders(reminderSettings) {
    const response = await api.post('/notifications/workout-reminders', reminderSettings)
    return response.data
  }

  async setGoalReminders(goalId, reminderSettings) {
    const response = await api.post(`/notifications/goal-reminders/${goalId}`, reminderSettings)
    return response.data
  }

  async setRestDayReminders(settings) {
    const response = await api.post('/notifications/rest-reminders', settings)
    return response.data
  }

  // Achievement notifications
  async getAchievements() {
    const response = await api.get('/notifications/achievements')
    return response.data
  }

  // Real-time notifications via WebSocket
  handleRealtimeNotification(data) {
    switch (data.type) {
      case 'WORKOUT_REMINDER':
        this.showLocalNotification('💪 Workout Time!', {
          body: data.message,
          tag: 'workout-reminder',
          actions: [
            { action: 'start', title: 'Start Workout' },
            { action: 'snooze', title: 'Snooze 15min' }
          ]
        })
        break

      case 'ACHIEVEMENT_UNLOCKED':
        this.showLocalNotification('🏆 Achievement Unlocked!', {
          body: data.message,
          tag: 'achievement',
          icon: '/achievements/' + data.achievement.icon
        })
        break

      case 'FORM_ALERT':
        this.showLocalNotification('⚠️ Form Alert', {
          body: data.message,
          tag: 'form-alert',
          requireInteraction: true
        })
        break

      case 'REST_REMINDER':
        this.showLocalNotification('😴 Rest Day Reminder', {
          body: data.message,
          tag: 'rest-reminder'
        })
        break

      case 'GOAL_PROGRESS':
        this.showLocalNotification('🎯 Goal Progress', {
          body: data.message,
          tag: 'goal-progress'
        })
        break
    }
  }
}

// Predefined notification templates
export const notificationTemplates = {
  workoutReminder: (workoutName) => ({
    title: '💪 Time to Workout!',
    body: `Your ${workoutName} session is scheduled now`,
    actions: [
      { action: 'start', title: 'Start Now' },
      { action: 'delay', title: 'Delay 15min' }
    ]
  }),

  goalDeadline: (goalName, daysLeft) => ({
    title: '🎯 Goal Deadline Approaching',
    body: `${goalName} - ${daysLeft} days remaining`,
    tag: 'goal-deadline'
  }),

  personalRecord: (exerciseName, record) => ({
    title: '🎉 New Personal Record!',
    body: `${exerciseName}: ${record}`,
    tag: 'personal-record',
    requireInteraction: true
  }),

  injuryRisk: (riskLevel) => ({
    title: '⚠️ Injury Risk Alert',
    body: `${riskLevel} risk detected. Consider rest or lighter training.`,
    tag: 'injury-risk',
    requireInteraction: true
  })
}

export const notificationService = new NotificationService() 