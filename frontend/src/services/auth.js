import { api, storeToken, clearStoredToken, getStoredToken } from './api.js'

// Real authentication service
export const authService = {
  async login(credentials) {
    try {
      const response = await api.post('/auth/login', {
        usernameOrEmail: credentials.email,
        password: credentials.password
      })

      const { accessToken, refreshToken, user } = response.data
      
      // Store tokens
      storeToken(accessToken, credentials.remember)
      
      return {
        token: accessToken,
        refreshToken,
        user
      }
    } catch (error) {
      throw new Error(error.response?.data?.message || 'Login failed')
    }
  },

  async register(userData) {
    try {
      const response = await api.post('/auth/register', {
        username: userData.name, // or use separate username field
        email: userData.email,
        password: userData.password,
        firstName: userData.firstName || userData.name?.split(' ')[0],
        lastName: userData.lastName || userData.name?.split(' ')[1] || ''
      })

      const { accessToken, refreshToken, user } = response.data
      
      // Store tokens
      storeToken(accessToken, userData.remember)
      
      return {
        token: accessToken,
        refreshToken,
        user: {
          ...user,
          onboardingCompleted: false // New users need onboarding
        }
      }
    } catch (error) {
      throw new Error(error.response?.data?.message || 'Registration failed')
    }
  },

  async checkAuth(token) {
    try {
      // If token is provided, use it; otherwise get from storage
      const authToken = token || getStoredToken()
      if (!authToken) {
        throw new Error('No token found')
      }

      // Verify token with backend
      const response = await api.get('/auth/me', {
        headers: {
          Authorization: `Bearer ${authToken}`
        }
      })

      return {
        user: response.data,
        onboardingCompleted: response.data.onboardingCompleted || false
      }
    } catch (error) {
      // Token is invalid, clear it
      clearStoredToken()
      throw new Error('Invalid token')
    }
  },

  async logout() {
    try {
      // Optional: Call logout endpoint on backend
      await api.post('/auth/logout')
    } catch (error) {
      // Ignore errors on logout
      console.warn('Logout request failed:', error.message)
    } finally {
      // Always clear local tokens
      clearStoredToken()
    }
  },

  async refreshToken() {
    try {
      const response = await api.post('/auth/refresh')
      const { accessToken } = response.data
      
      // Update stored token
      const rememberMe = localStorage.getItem('auth_remember_me') === 'true'
      storeToken(accessToken, rememberMe)
      
      return accessToken
    } catch (error) {
      clearStoredToken()
      throw new Error('Token refresh failed')
    }
  },

  // Legacy methods for backward compatibility
  getStoredToken,
  storeToken,
  clearStoredToken
} 