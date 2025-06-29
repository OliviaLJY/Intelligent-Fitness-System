import { getStoredToken } from './api.js'

class RealtimeService {
  constructor() {
    this.socket = null
    this.isConnected = false
    this.reconnectAttempts = 0
    this.maxReconnectAttempts = 5
    this.subscribers = new Map()
  }

  connect() {
    const token = getStoredToken()
    if (!token) {
      console.warn('No auth token available for WebSocket connection')
      return
    }

    const wsUrl = `ws://localhost:8080/ws?token=${token}`
    this.socket = new WebSocket(wsUrl)

    this.socket.onopen = () => {
      console.log('🔗 WebSocket connected')
      this.isConnected = true
      this.reconnectAttempts = 0
      this.emit('connected')
    }

    this.socket.onmessage = (event) => {
      try {
        const data = JSON.parse(event.data)
        this.handleMessage(data)
      } catch (error) {
        console.error('Failed to parse WebSocket message:', error)
      }
    }

    this.socket.onclose = () => {
      console.log('🔌 WebSocket disconnected')
      this.isConnected = false
      this.emit('disconnected')
      this.attemptReconnect()
    }

    this.socket.onerror = (error) => {
      console.error('WebSocket error:', error)
      this.emit('error', error)
    }
  }

  disconnect() {
    if (this.socket) {
      this.socket.close()
      this.socket = null
      this.isConnected = false
    }
  }

  // Live Workout Features
  startWorkout(workoutId) {
    this.send({
      type: 'START_WORKOUT',
      workoutId,
      timestamp: new Date().toISOString()
    })
  }

  updateExerciseProgress(data) {
    this.send({
      type: 'EXERCISE_PROGRESS',
      ...data,
      timestamp: new Date().toISOString()
    })
  }

  recordRep(exerciseId, setNumber, repData) {
    this.send({
      type: 'REP_RECORDED',
      exerciseId,
      setNumber,
      repData,
      timestamp: new Date().toISOString()
    })
  }

  sendHeartRate(heartRate) {
    this.send({
      type: 'HEART_RATE',
      heartRate,
      timestamp: new Date().toISOString()
    })
  }

  requestFormAnalysis(videoFrame) {
    this.send({
      type: 'ANALYZE_FORM',
      frame: videoFrame,
      timestamp: new Date().toISOString()
    })
  }

  // Event handling
  on(event, callback) {
    if (!this.subscribers.has(event)) {
      this.subscribers.set(event, [])
    }
    this.subscribers.get(event).push(callback)
  }

  off(event, callback) {
    if (this.subscribers.has(event)) {
      const callbacks = this.subscribers.get(event)
      const index = callbacks.indexOf(callback)
      if (index > -1) {
        callbacks.splice(index, 1)
      }
    }
  }

  emit(event, data) {
    if (this.subscribers.has(event)) {
      this.subscribers.get(event).forEach(callback => {
        try {
          callback(data)
        } catch (error) {
          console.error(`Error in ${event} callback:`, error)
        }
      })
    }
  }

  send(data) {
    if (this.isConnected && this.socket) {
      this.socket.send(JSON.stringify(data))
    } else {
      console.warn('WebSocket not connected, message not sent:', data)
    }
  }

  handleMessage(data) {
    switch (data.type) {
      case 'FORM_FEEDBACK':
        this.emit('formFeedback', data)
        break
      case 'WORKOUT_SUGGESTION':
        this.emit('workoutSuggestion', data)
        break
      case 'REST_REMINDER':
        this.emit('restReminder', data)
        break
      case 'ACHIEVEMENT_UNLOCKED':
        this.emit('achievement', data)
        break
      case 'HEART_RATE_ALERT':
        this.emit('heartRateAlert', data)
        break
      default:
        console.log('Unknown message type:', data.type)
    }
  }

  attemptReconnect() {
    if (this.reconnectAttempts < this.maxReconnectAttempts) {
      this.reconnectAttempts++
      const delay = Math.pow(2, this.reconnectAttempts) * 1000 // Exponential backoff
      
      console.log(`Attempting to reconnect in ${delay}ms... (${this.reconnectAttempts}/${this.maxReconnectAttempts})`)
      
      setTimeout(() => {
        this.connect()
      }, delay)
    } else {
      console.error('Max reconnection attempts reached')
      this.emit('reconnectFailed')
    }
  }
}

// Export singleton instance
export const realtimeService = new RealtimeService() 