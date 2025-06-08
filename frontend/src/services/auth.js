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
    const token = btoa(JSON.stringify({ userId: user.id, email: user.email }))
    
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
    const token = btoa(JSON.stringify({ userId: newUser.id, email: newUser.email }))
    
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
      const { userId } = JSON.parse(atob(token))
      const user = MOCK_USERS.find(u => u.id === userId)
      
      if (!user) {
        throw new Error('Invalid token')
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
  }
} 