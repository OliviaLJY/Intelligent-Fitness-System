// Mock user data for development
const MOCK_USERS = [
  {
    id: 1,
    name: 'John Doe',
    email: 'john@example.com',
    password: 'password123', // In a real app, this would be hashed
    onboardingCompleted: true
  }
]

// Storage keys
const TOKEN_KEY = 'auth_token'
const REMEMBER_ME_KEY = 'auth_remember_me'

// Mock authentication service
export const authService = {
  async login(credentials) {
    // Simulate API delay
    await new Promise(resolve => setTimeout(resolve, 1000))

    const user = MOCK_USERS.find(u => u.email === credentials.email)
    
    if (!user || user.password !== credentials.password) {
      throw new Error('Invalid email or password')
    }

    // Generate a mock token
    const token = btoa(JSON.stringify({ 
      userId: user.id, 
      email: user.email,
      rememberMe: credentials.remember || false
    }))
    
    // Store remember me preference
    if (credentials.remember) {
      localStorage.setItem(REMEMBER_ME_KEY, 'true')
    } else {
      sessionStorage.setItem(REMEMBER_ME_KEY, 'true')
      localStorage.removeItem(REMEMBER_ME_KEY)
    }
    
    // Return user data without password
    const { password, ...userData } = user
    return {
      token,
      user: userData
    }
  },

  async register(userData) {
    // Simulate API delay
    await new Promise(resolve => setTimeout(resolve, 1000))

    // Check if email already exists
    if (MOCK_USERS.some(u => u.email === userData.email)) {
      throw new Error('Email already registered')
    }

    // Create new user
    const newUser = {
      id: MOCK_USERS.length + 1,
      ...userData,
      onboardingCompleted: false
    }
    MOCK_USERS.push(newUser)

    // Generate a mock token
    const token = btoa(JSON.stringify({ 
      userId: newUser.id, 
      email: newUser.email,
      rememberMe: userData.remember || false
    }))
    
    // Store remember me preference
    if (userData.remember) {
      localStorage.setItem(REMEMBER_ME_KEY, 'true')
    } else {
      sessionStorage.setItem(REMEMBER_ME_KEY, 'true')
      localStorage.removeItem(REMEMBER_ME_KEY)
    }
    
    // Return user data without password
    const { password, ...userDataWithoutPassword } = newUser
    return {
      token,
      user: userDataWithoutPassword
    }
  },

  async checkAuth(token) {
    // Simulate API delay
    await new Promise(resolve => setTimeout(resolve, 500))

    try {
      const { userId, rememberMe } = JSON.parse(atob(token))
      const user = MOCK_USERS.find(u => u.id === userId)
      
      if (!user) {
        throw new Error('Invalid token')
      }

      // Verify remember me preference
      const storedRememberMe = localStorage.getItem(REMEMBER_ME_KEY) === 'true'
      if (rememberMe !== storedRememberMe) {
        throw new Error('Session expired')
      }

      // Return user data without password
      const { password, ...userData } = user
      return {
        user: userData,
        onboardingCompleted: user.onboardingCompleted
      }
    } catch (error) {
      throw new Error('Invalid token')
    }
  },

  getStoredToken() {
    // Check both storage locations
    const token = localStorage.getItem(TOKEN_KEY) || sessionStorage.getItem(TOKEN_KEY)
    if (!token) return null

    try {
      const { rememberMe } = JSON.parse(atob(token))
      const storedRememberMe = localStorage.getItem(REMEMBER_ME_KEY) === 'true'
      
      // If remember me preference doesn't match, clear the token
      if (rememberMe !== storedRememberMe) {
        this.clearStoredToken()
        return null
      }
      
      return token
    } catch {
      this.clearStoredToken()
      return null
    }
  },

  storeToken(token, rememberMe) {
    if (rememberMe) {
      localStorage.setItem(TOKEN_KEY, token)
    } else {
      sessionStorage.setItem(TOKEN_KEY, token)
      localStorage.removeItem(TOKEN_KEY)
    }
  },

  clearStoredToken() {
    localStorage.removeItem(TOKEN_KEY)
    sessionStorage.removeItem(TOKEN_KEY)
    localStorage.removeItem(REMEMBER_ME_KEY)
    sessionStorage.removeItem(REMEMBER_ME_KEY)
  }
} 