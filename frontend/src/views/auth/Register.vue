<template>
  <div class="apple-auth-container">
    <!-- Background -->
    <div class="apple-auth-background">
      <div class="apple-floating-shapes">
        <div class="apple-shape shape-1"></div>
        <div class="apple-shape shape-2"></div>
        <div class="apple-shape shape-3"></div>
      </div>
    </div>

    <!-- Main Content -->
    <div class="apple-auth-content">
      <div class="apple-auth-card">
        <!-- Header -->
        <div class="apple-auth-header">
          <div class="apple-logo-container">
            <div class="apple-logo">
              <svg class="w-12 h-12 text-blue-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z" />
              </svg>
            </div>
          </div>
          <h1 class="apple-auth-title">Get Started</h1>
          <p class="apple-auth-subtitle">Create your account to begin your fitness journey</p>
        </div>

        <!-- Form -->
        <form class="apple-auth-form" @submit.prevent="handleRegister">
          <!-- Name Field -->
          <div class="apple-form-group">
            <label for="name" class="apple-form-label">Full Name</label>
            <div class="apple-input-container">
              <input
                id="name"
                v-model="name"
                type="text"
                autocomplete="name"
                required
                class="apple-input"
                :class="{ 'error': error && !name }"
                placeholder="Enter your full name"
              />
              <div class="apple-input-icon">
                <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                </svg>
              </div>
            </div>
          </div>

          <!-- Email Field -->
          <div class="apple-form-group">
            <label for="email" class="apple-form-label">Email</label>
            <div class="apple-input-container">
              <input
                id="email"
                v-model="email"
                type="email"
                autocomplete="email"
                required
                class="apple-input"
                :class="{ 'error': error && !email }"
                placeholder="Enter your email"
              />
              <div class="apple-input-icon">
                <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 12a4 4 0 10-8 0 4 4 0 008 0zm0 0v1.5a2.5 2.5 0 005 0V12a9 9 0 10-9 9m4.5-1.206a8.959 8.959 0 01-4.5 1.207" />
                </svg>
              </div>
            </div>
          </div>

          <!-- Password Field -->
          <div class="apple-form-group">
            <label for="password" class="apple-form-label">Password</label>
            <div class="apple-input-container">
              <input
                id="password"
                v-model="password"
                :type="showPassword ? 'text' : 'password'"
                autocomplete="new-password"
                required
                class="apple-input"
                :class="{ 'error': error && !password }"
                placeholder="Enter your password"
              />
              <button
                type="button"
                @click="showPassword = !showPassword"
                class="apple-input-action"
              >
                <svg v-if="!showPassword" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z" />
                </svg>
                <svg v-else class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13.875 18.825A10.05 10.05 0 0112 19c-4.478 0-8.268-2.943-9.543-7a9.97 9.97 0 011.563-3.029m5.858.908a3 3 0 114.243 4.243M9.878 9.878l4.242 4.242M9.878 9.878L3 3m6.878 6.878L21 21" />
                </svg>
              </button>
            </div>
            <!-- Password Strength Indicator -->
            <div class="apple-password-strength">
              <div class="apple-strength-bars">
                <div class="apple-strength-bar" :class="passwordStrength >= 1 ? 'active' : ''"></div>
                <div class="apple-strength-bar" :class="passwordStrength >= 2 ? 'active' : ''"></div>
                <div class="apple-strength-bar" :class="passwordStrength >= 3 ? 'active' : ''"></div>
                <div class="apple-strength-bar" :class="passwordStrength >= 4 ? 'active' : ''"></div>
              </div>
              <span class="apple-strength-text">{{ passwordStrengthText }}</span>
            </div>
          </div>

          <!-- Confirm Password Field -->
          <div class="apple-form-group">
            <label for="password-confirm" class="apple-form-label">Confirm Password</label>
            <div class="apple-input-container">
              <input
                id="password-confirm"
                v-model="passwordConfirm"
                :type="showConfirmPassword ? 'text' : 'password'"
                autocomplete="new-password"
                required
                class="apple-input"
                :class="{ 
                  'error': error && !passwordConfirm,
                  'success': passwordConfirm && password === passwordConfirm,
                  'warning': passwordConfirm && password !== passwordConfirm
                }"
                placeholder="Confirm your password"
              />
              <button
                type="button"
                @click="showConfirmPassword = !showConfirmPassword"
                class="apple-input-action"
              >
                <svg v-if="!showConfirmPassword" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z" />
                </svg>
                <svg v-else class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13.875 18.825A10.05 10.05 0 0112 19c-4.478 0-8.268-2.943-9.543-7a9.97 9.97 0 011.563-3.029m5.858.908a3 3 0 114.243 4.243M9.878 9.878l4.242 4.242M9.878 9.878L3 3m6.878 6.878L21 21" />
                </svg>
              </button>
            </div>
            <div v-if="passwordConfirm && password !== passwordConfirm" class="apple-field-hint error">
              Passwords do not match
            </div>
            <div v-else-if="passwordConfirm && password === passwordConfirm" class="apple-field-hint success">
              Passwords match
            </div>
          </div>

          <!-- Terms Agreement -->
          <div class="apple-form-group">
            <label class="apple-checkbox-container">
              <input
                v-model="acceptTerms"
                type="checkbox"
                required
                class="apple-checkbox"
              />
              <span class="apple-checkbox-mark"></span>
              <span class="apple-checkbox-label">
                I agree to the
                <button type="button" class="apple-inline-link">Terms of Service</button>
                and
                <button type="button" class="apple-inline-link">Privacy Policy</button>
              </span>
            </label>
          </div>

          <!-- Submit Button -->
          <button
            type="submit"
            :disabled="isLoading || !isFormValid"
            class="apple-submit-button"
            :class="{ 'loading': isLoading }"
          >
            <div v-if="isLoading" class="apple-loading-spinner"></div>
            <span>{{ isLoading ? 'Creating Account...' : 'Create Account' }}</span>
          </button>

          <!-- Error Message -->
          <div v-if="error" class="apple-error-message">
            <div class="apple-error-icon">
              <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
            </div>
            <span>{{ error }}</span>
          </div>
        </form>

        <!-- Footer -->
        <div class="apple-auth-footer">
          <p class="apple-footer-text">
            Already have an account?
            <router-link to="/auth/login" class="apple-footer-link">
              Sign in
            </router-link>
          </p>
        </div>
      </div>

      <!-- Alternative Options -->
      <div class="apple-auth-alternatives">
        <div class="apple-divider">
          <span>or continue with</span>
        </div>
        
        <div class="apple-social-buttons">
          <button class="apple-social-button">
            <svg class="w-5 h-5" viewBox="0 0 24 24" fill="currentColor">
              <path d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z"/>
              <path d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z"/>
              <path d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z"/>
              <path d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z"/>
            </svg>
            <span>Google</span>
          </button>
          
          <button class="apple-social-button">
            <svg class="w-5 h-5" viewBox="0 0 24 24" fill="currentColor">
              <path d="M18.71 19.5c-.83 1.24-1.71 2.45-3.05 2.47-1.34.03-1.77-.79-3.29-.79-1.53 0-2 .77-3.27.82-1.31.05-2.3-1.32-3.14-2.53C4.25 17 2.94 12.45 4.7 9.39c.87-1.52 2.43-2.48 4.12-2.51 1.28-.02 2.5.87 3.29.87.78 0 2.26-1.07 3.81-.91.65.03 2.47.26 3.64 1.98-.09.06-2.17 1.28-2.15 3.81.03 3.02 2.65 4.03 2.68 4.04-.03.07-.42 1.44-1.38 2.83M13 3.5c.73-.83 1.94-1.46 2.94-1.5.13 1.17-.34 2.35-1.04 3.19-.69.85-1.83 1.51-2.95 1.42-.15-1.15.41-2.35 1.05-3.11z"/>
            </svg>
            <span>Apple</span>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const router = useRouter()
const authStore = useAuthStore()

const name = ref('')
const email = ref('')
const password = ref('')
const passwordConfirm = ref('')
const acceptTerms = ref(false)
const showPassword = ref(false)
const showConfirmPassword = ref(false)
const isLoading = ref(false)
const error = ref('')

const passwordStrength = computed(() => {
  if (!password.value) return 0
  let strength = 0
  
  // Length check
  if (password.value.length >= 8) strength++
  
  // Contains lowercase and uppercase
  if (/[a-z]/.test(password.value) && /[A-Z]/.test(password.value)) strength++
  
  // Contains numbers
  if (/\d/.test(password.value)) strength++
  
  // Contains special characters
  if (/[!@#$%^&*(),.?":{}|<>]/.test(password.value)) strength++
  
  return strength
})

const passwordStrengthText = computed(() => {
  switch (passwordStrength.value) {
    case 0:
    case 1:
      return 'Weak'
    case 2:
      return 'Fair'
    case 3:
      return 'Good'
    case 4:
      return 'Strong'
    default:
      return ''
  }
})

const isFormValid = computed(() => {
  return (
    name.value.length > 0 &&
    email.value.length > 0 &&
    password.value.length >= 8 &&
    password.value === passwordConfirm.value &&
    acceptTerms.value
  )
})

const handleRegister = async () => {
  if (!isFormValid.value) return

  try {
    isLoading.value = true
    error.value = ''
    
    await authStore.register({
      name: name.value,
      email: email.value,
      password: password.value
    })
    
    router.push('/dashboard')
  } catch (err) {
    error.value = err.message || 'Failed to create account. Please try again.'
  } finally {
    isLoading.value = false
  }
}
</script>

<style scoped>
/* ===== Apple Auth Container (Same as Login) ===== */
.apple-auth-container {
  @apply min-h-screen flex items-center justify-center p-4;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  position: relative;
  overflow: hidden;
}

.apple-auth-background {
  @apply absolute inset-0;
}

.apple-floating-shapes {
  @apply absolute inset-0;
}

.apple-shape {
  @apply absolute rounded-full;
  background: linear-gradient(45deg, rgba(255, 255, 255, 0.1), rgba(255, 255, 255, 0.05));
  animation: float 6s ease-in-out infinite;
}

.apple-shape.shape-1 {
  @apply w-96 h-96 -top-48 -left-48;
  animation-delay: 0s;
}

.apple-shape.shape-2 {
  @apply w-64 h-64 top-1/4 -right-32;
  animation-delay: 2s;
}

.apple-shape.shape-3 {
  @apply w-48 h-48 -bottom-24 left-1/3;
  animation-delay: 4s;
}

.apple-auth-content {
  @apply relative z-10 w-full max-w-md;
}

/* ===== Auth Card ===== */
.apple-auth-card {
  @apply bg-white rounded-3xl shadow-2xl border border-gray-100 overflow-hidden;
  backdrop-filter: blur(20px);
  background-color: rgba(255, 255, 255, 0.95);
}

.apple-auth-header {
  @apply text-center p-8 pb-4;
}

.apple-logo-container {
  @apply mb-6;
}

.apple-logo {
  @apply w-16 h-16 bg-blue-100 rounded-2xl flex items-center justify-center mx-auto;
}

.apple-auth-title {
  @apply text-3xl font-bold text-gray-900 mb-2;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
  letter-spacing: -0.02em;
}

.apple-auth-subtitle {
  @apply text-gray-600;
}

/* ===== Form Styles ===== */
.apple-auth-form {
  @apply px-8 pb-4;
}

.apple-form-group {
  @apply mb-6;
}

.apple-form-label {
  @apply block text-sm font-semibold text-gray-700 mb-2;
}

.apple-input-container {
  @apply relative;
}

.apple-input {
  @apply w-full px-4 py-4 bg-gray-50 border border-gray-200 rounded-2xl text-gray-900 placeholder-gray-500 transition-all duration-200;
  font-size: 16px; /* Prevent zoom on iOS */
}

.apple-input:focus {
  @apply outline-none bg-white border-blue-500 shadow-lg;
  box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.1);
}

.apple-input.error {
  @apply border-red-500 bg-red-50;
}

.apple-input.success {
  @apply border-green-500 bg-green-50;
}

.apple-input.warning {
  @apply border-yellow-500 bg-yellow-50;
}

.apple-input-icon {
  @apply absolute right-4 top-1/2 transform -translate-y-1/2 text-gray-400;
}

.apple-input-action {
  @apply absolute right-4 top-1/2 transform -translate-y-1/2 text-gray-400 hover:text-gray-600 transition-colors duration-200;
}

/* ===== Password Strength ===== */
.apple-password-strength {
  @apply flex items-center justify-between mt-2;
}

.apple-strength-bars {
  @apply flex space-x-1;
}

.apple-strength-bar {
  @apply w-6 h-1 bg-gray-200 rounded-full transition-all duration-300;
}

.apple-strength-bar.active {
  @apply bg-blue-500;
}

.apple-strength-text {
  @apply text-xs text-gray-600 font-medium;
}

/* ===== Field Hints ===== */
.apple-field-hint {
  @apply mt-2 text-xs font-medium;
}

.apple-field-hint.error {
  @apply text-red-600;
}

.apple-field-hint.success {
  @apply text-green-600;
}

/* ===== Checkbox Styles ===== */
.apple-checkbox-container {
  @apply flex items-start cursor-pointer;
}

.apple-checkbox {
  @apply sr-only;
}

.apple-checkbox-mark {
  @apply w-5 h-5 bg-gray-200 rounded border border-gray-300 flex items-center justify-center mr-3 mt-0.5 transition-all duration-200 flex-shrink-0;
}

.apple-checkbox:checked + .apple-checkbox-mark {
  @apply bg-blue-500 border-blue-500;
}

.apple-checkbox:checked + .apple-checkbox-mark::after {
  content: '✓';
  @apply text-white text-sm font-bold;
}

.apple-checkbox-label {
  @apply text-sm text-gray-700 leading-relaxed;
}

.apple-inline-link {
  @apply text-blue-500 hover:text-blue-600 font-medium underline transition-colors duration-200;
}

/* ===== Submit Button ===== */
.apple-submit-button {
  @apply w-full bg-blue-500 text-white py-4 rounded-2xl font-semibold text-lg transition-all duration-300 shadow-lg hover:bg-blue-600 hover:shadow-xl disabled:opacity-50 disabled:cursor-not-allowed mb-4;
  position: relative;
}

.apple-submit-button.loading {
  @apply text-transparent;
}

.apple-loading-spinner {
  @apply absolute inset-0 flex items-center justify-center;
}

.apple-loading-spinner::after {
  content: '';
  @apply w-6 h-6 border-2 border-white border-t-transparent rounded-full;
  animation: spin 1s linear infinite;
}

/* ===== Error Message ===== */
.apple-error-message {
  @apply flex items-center space-x-2 p-4 bg-red-50 border border-red-200 rounded-xl text-red-700 text-sm mb-4;
}

.apple-error-icon {
  @apply flex-shrink-0;
}

/* ===== Footer ===== */
.apple-auth-footer {
  @apply p-8 pt-4 text-center;
}

.apple-footer-text {
  @apply text-gray-600;
}

.apple-footer-link {
  @apply text-blue-500 hover:text-blue-600 font-semibold transition-colors duration-200;
}

/* ===== Alternatives ===== */
.apple-auth-alternatives {
  @apply mt-8;
}

.apple-divider {
  @apply relative text-center mb-6;
}

.apple-divider::before {
  content: '';
  @apply absolute top-1/2 left-0 right-0 h-px bg-gray-300;
}

.apple-divider span {
  @apply inline-block px-4 text-sm text-gray-500 bg-white;
}

.apple-social-buttons {
  @apply grid grid-cols-2 gap-4;
}

.apple-social-button {
  @apply flex items-center justify-center space-x-2 p-4 bg-white border border-gray-200 rounded-2xl hover:bg-gray-50 transition-all duration-200 shadow-sm hover:shadow-md;
}

.apple-social-button svg {
  @apply text-gray-600;
}

.apple-social-button span {
  @apply text-gray-700 font-medium;
}

/* ===== Animations ===== */
@keyframes float {
  0%, 100% {
    transform: translateY(0px);
  }
  50% {
    transform: translateY(-20px);
  }
}

@keyframes spin {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}

/* ===== Responsive Design ===== */
@media (max-width: 480px) {
  .apple-auth-card {
    @apply rounded-2xl;
  }
  
  .apple-auth-header {
    @apply p-6 pb-2;
  }
  
  .apple-auth-form {
    @apply px-6;
  }
  
  .apple-auth-footer {
    @apply p-6 pt-2;
  }
  
  .apple-auth-title {
    @apply text-2xl;
  }
  
  .apple-checkbox-label {
    @apply text-xs;
  }
}

/* ===== Accessibility ===== */
@media (prefers-reduced-motion: reduce) {
  .apple-shape {
    animation: none;
  }
  
  .apple-loading-spinner::after {
    animation: none;
  }
}

/* Focus states for accessibility */
button:focus,
input:focus,
.apple-checkbox-container:focus-within .apple-checkbox-mark {
  @apply outline-none ring-2 ring-blue-500 ring-offset-2;
}
</style> 