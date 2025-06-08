import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { authService } from '@/services/auth'

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
      const { token: newToken, user: userData } = await authService.login(credentials)
      token.value = newToken
      user.value = userData
      onboardingCompleted.value = userData.onboardingCompleted
      localStorage.setItem('token', newToken)
      return true
    } catch (error) {
      console.error('Login error:', error)
      throw error
    }
  }

  async function register(userData) {
    try {
      const { token: newToken, user: registeredUser } = await authService.register(userData)
      token.value = newToken
      user.value = registeredUser
      onboardingCompleted.value = registeredUser.onboardingCompleted
      localStorage.setItem('token', newToken)
      return true
    } catch (error) {
      console.error('Registration error:', error)
      throw error
    }
  }

  async function logout() {
    token.value = null
    user.value = null
    onboardingCompleted.value = false
    localStorage.removeItem('token')
  }

  async function checkAuth() {
    if (!token.value) return false
    
    try {
      const { user: userData, onboardingCompleted: completed } = await authService.checkAuth(token.value)
      user.value = userData
      onboardingCompleted.value = completed
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
    checkAuth
  }
}) 