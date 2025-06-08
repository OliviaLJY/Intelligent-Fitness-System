import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useAuthStore = defineStore('auth', () => {
  const user = ref(null)
  const token = ref(localStorage.getItem('token'))
  const onboardingCompleted = ref(false)

  const isAuthenticated = computed(() => !!token.value)
  const userInitials = computed(() => {
    if (!user.value?.name) return ''
    return user.value.name
      .split(' ')
      .map(word => word[0])
      .join('')
      .toUpperCase()
      .slice(0, 2)
  })

  async function login(credentials) {
    try {
      // TODO: Implement actual API call
      const response = await fetch('/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(credentials)
      })
      
      if (!response.ok) throw new Error('Login failed')
      
      const data = await response.json()
      token.value = data.token
      user.value = data.user
      localStorage.setItem('token', data.token)
      
      return true
    } catch (error) {
      console.error('Login error:', error)
      return false
    }
  }

  async function register(userData) {
    try {
      // TODO: Implement actual API call
      const response = await fetch('/api/auth/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(userData)
      })
      
      if (!response.ok) throw new Error('Registration failed')
      
      const data = await response.json()
      token.value = data.token
      user.value = data.user
      localStorage.setItem('token', data.token)
      
      return true
    } catch (error) {
      console.error('Registration error:', error)
      return false
    }
  }

  async function logout() {
    token.value = null
    user.value = null
    localStorage.removeItem('token')
  }

  async function completeOnboarding(onboardingData) {
    try {
      // TODO: Implement actual API call
      const response = await fetch('/api/auth/onboarding', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token.value}`
        },
        body: JSON.stringify(onboardingData)
      })
      
      if (!response.ok) throw new Error('Onboarding failed')
      
      onboardingCompleted.value = true
      user.value = { ...user.value, ...onboardingData }
      
      return true
    } catch (error) {
      console.error('Onboarding error:', error)
      return false
    }
  }

  async function checkAuth() {
    if (!token.value) return false
    
    try {
      // TODO: Implement actual API call
      const response = await fetch('/api/auth/me', {
        headers: { 'Authorization': `Bearer ${token.value}` }
      })
      
      if (!response.ok) throw new Error('Auth check failed')
      
      const data = await response.json()
      user.value = data.user
      onboardingCompleted.value = data.onboardingCompleted
      
      return true
    } catch (error) {
      console.error('Auth check error:', error)
      logout()
      return false
    }
  }

  return {
    user,
    token,
    onboardingCompleted,
    isAuthenticated,
    userInitials,
    login,
    register,
    logout,
    completeOnboarding,
    checkAuth
  }
}) 