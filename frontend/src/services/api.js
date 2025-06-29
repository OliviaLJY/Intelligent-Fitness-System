import axios from 'axios'

// API base configuration
// Use proxy in development, direct URL in production
const API_BASE_URL = import.meta.env.DEV ? '/api' : 'http://localhost:8080/api'

// Create axios instance
const api = axios.create({
  baseURL: API_BASE_URL,
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
})

// Request interceptor to add auth token
api.interceptors.request.use(
  (config) => {
    const token = getStoredToken()
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// Response interceptor to handle errors
api.interceptors.response.use(
  (response) => {
    return response
  },
  (error) => {
    if (error.response?.status === 401) {
      // Token expired or invalid
      clearStoredToken()
      window.location.href = '/login'
    }
    return Promise.reject(error)
  }
)

// Token management functions
const TOKEN_KEY = 'auth_token'
const REMEMBER_ME_KEY = 'auth_remember_me'

function getStoredToken() {
  return localStorage.getItem(TOKEN_KEY) || sessionStorage.getItem(TOKEN_KEY)
}

function storeToken(token, rememberMe = false) {
  if (rememberMe) {
    localStorage.setItem(TOKEN_KEY, token)
    localStorage.setItem(REMEMBER_ME_KEY, 'true')
  } else {
    sessionStorage.setItem(TOKEN_KEY, token)
    localStorage.removeItem(TOKEN_KEY)
    localStorage.removeItem(REMEMBER_ME_KEY)
  }
}

function clearStoredToken() {
  localStorage.removeItem(TOKEN_KEY)
  sessionStorage.removeItem(TOKEN_KEY)
  localStorage.removeItem(REMEMBER_ME_KEY)
}

export { api, storeToken, clearStoredToken, getStoredToken } 