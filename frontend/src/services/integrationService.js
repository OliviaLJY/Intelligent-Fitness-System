import { api } from './api.js'

export const integrationService = {
  // Wearable Device Integration
  async connectFitbit() {
    const response = await api.get('/integrations/fitbit/auth-url')
    window.location.href = response.data.authUrl
  },

  async handleFitbitCallback(code) {
    const response = await api.post('/integrations/fitbit/callback', { code })
    return response.data
  },

  async syncFitbitData() {
    const response = await api.post('/integrations/fitbit/sync')
    return response.data
  },

  async disconnectFitbit() {
    const response = await api.delete('/integrations/fitbit')
    return response.data
  },

  // Apple Health Integration
  async connectAppleHealth() {
    if (!window.HealthKit) {
      throw new Error('Apple Health not available on this device')
    }

    // Request permissions for health data
    const permissions = {
      read: [
        'HKQuantityTypeIdentifierHeartRate',
        'HKQuantityTypeIdentifierActiveEnergyBurned',
        'HKQuantityTypeIdentifierStepCount',
        'HKWorkoutTypeIdentifier'
      ]
    }

    try {
      await window.HealthKit.requestAuthorization(permissions)
      const response = await api.post('/integrations/apple-health/connect')
      return response.data
    } catch (error) {
      throw new Error('Failed to connect to Apple Health: ' + error.message)
    }
  },

  async syncAppleHealthData() {
    const response = await api.post('/integrations/apple-health/sync')
    return response.data
  },

  // Google Fit Integration
  async connectGoogleFit() {
    const response = await api.get('/integrations/google-fit/auth-url')
    window.location.href = response.data.authUrl
  },

  async handleGoogleFitCallback(code) {
    const response = await api.post('/integrations/google-fit/callback', { code })
    return response.data
  },

  // Heart Rate Monitor (Bluetooth)
  async connectBluetoothHRM() {
    if (!navigator.bluetooth) {
      throw new Error('Bluetooth not supported on this device')
    }

    try {
      const device = await navigator.bluetooth.requestDevice({
        filters: [
          { services: ['heart_rate'] },
          { name: 'Polar H10' },
          { name: 'Wahoo TICKR' }
        ],
        optionalServices: ['battery_service']
      })

      const server = await device.gatt.connect()
      const service = await server.getPrimaryService('heart_rate')
      const characteristic = await service.getCharacteristic('heart_rate_measurement')

      // Listen for heart rate data
      await characteristic.startNotifications()
      characteristic.addEventListener('characteristicvaluechanged', (event) => {
        const heartRate = this.parseHeartRateData(event.target.value)
        this.sendHeartRateToBackend(heartRate)
      })

      return { device, connected: true }
    } catch (error) {
      throw new Error('Failed to connect to heart rate monitor: ' + error.message)
    }
  },

  parseHeartRateData(data) {
    const flags = data.getUint8(0)
    const format = flags & 0x01
    
    if (format === 0) {
      return data.getUint8(1) // 8-bit heart rate
    } else {
      return data.getUint16(1, true) // 16-bit heart rate
    }
  },

  async sendHeartRateToBackend(heartRate) {
    try {
      await api.post('/integrations/heart-rate', {
        heartRate,
        timestamp: new Date().toISOString()
      })
    } catch (error) {
      console.error('Failed to send heart rate data:', error)
    }
  },

  // Smart Scale Integration
  async connectBluetoothScale() {
    if (!navigator.bluetooth) {
      throw new Error('Bluetooth not supported')
    }

    try {
      const device = await navigator.bluetooth.requestDevice({
        filters: [
          { services: ['weight_scale'] },
          { name: 'Withings' },
          { name: 'Fitbit Aria' }
        ]
      })

      const server = await device.gatt.connect()
      // Implementation for weight data collection
      return { device, connected: true }
    } catch (error) {
      throw new Error('Failed to connect to smart scale: ' + error.message)
    }
  },

  // Nutrition App Integration
  async connectMyFitnessPal() {
    const response = await api.get('/integrations/myfitnesspal/auth-url')
    window.location.href = response.data.authUrl
  },

  async syncNutritionData() {
    const response = await api.post('/integrations/nutrition/sync')
    return response.data
  },

  // Music Integration
  async connectSpotify() {
    const response = await api.get('/integrations/spotify/auth-url')
    window.location.href = response.data.authUrl
  },

  async getWorkoutPlaylists() {
    const response = await api.get('/integrations/spotify/workout-playlists')
    return response.data
  },

  async createWorkoutPlaylist(name, trackIds) {
    const response = await api.post('/integrations/spotify/create-playlist', {
      name,
      trackIds
    })
    return response.data
  },

  // Calendar Integration
  async connectGoogleCalendar() {
    const response = await api.get('/integrations/google-calendar/auth-url')
    window.location.href = response.data.authUrl
  },

  async scheduleWorkout(workoutData) {
    const response = await api.post('/integrations/calendar/schedule', workoutData)
    return response.data
  },

  // Smart Gym Equipment
  async connectPeloton() {
    const response = await api.post('/integrations/peloton/connect', {
      username: prompt('Enter Peloton username:'),
      password: prompt('Enter Peloton password:')
    })
    return response.data
  },

  async syncPelotonWorkouts() {
    const response = await api.post('/integrations/peloton/sync')
    return response.data
  },

  // Data Export/Import
  async exportToHealthApp(platform) {
    const response = await api.post(`/integrations/export/${platform}`)
    return response.data
  },

  async importFromCsv(file) {
    const formData = new FormData()
    formData.append('file', file)
    
    const response = await api.post('/integrations/import/csv', formData, {
      headers: { 'Content-Type': 'multipart/form-data' }
    })
    return response.data
  },

  // Integration Status
  async getConnectedDevices() {
    const response = await api.get('/integrations/status')
    return response.data
  },

  async disconnectDevice(deviceType) {
    const response = await api.delete(`/integrations/${deviceType}`)
    return response.data
  },

  // Real-time Device Data
  async startDeviceDataStream() {
    const response = await api.post('/integrations/stream/start')
    return response.data
  },

  async stopDeviceDataStream() {
    const response = await api.post('/integrations/stream/stop')
    return response.data
  }
} 