<template>
  <div class="bg-apple-background min-h-screen">
    <!-- Apple-style Hero Section -->
    <div class="apple-hero-section">
      <div class="max-w-4xl mx-auto px-6 text-center">
        <!-- Main Title -->
        <div class="apple-hero-content">
          <h1 class="apple-hero-title">
            Intelligent
            <br />
            Fitness System
          </h1>
          <p class="apple-hero-subtitle">
            AI-powered motion analysis and personalized workout recommendations 
            <br />
            to help you achieve your fitness goals with precision and intelligence.
          </p>
          
          <!-- CTA Buttons -->
          <div class="apple-hero-actions">
            <router-link to="/auth/register" class="apple-button-hero-primary">
              <span>Get Started</span>
              <svg class="w-5 h-5 ml-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7l5 5m0 0l-5 5m5-5H6" />
              </svg>
            </router-link>
            <router-link to="/auth/login" class="apple-button-hero-secondary">
              <span>Sign In</span>
            </router-link>
          </div>
        </div>
        
        <!-- Hero Visual -->
        <div class="apple-hero-visual">
          <div class="apple-device-mockup">
            <div class="apple-device-screen">
              <div class="apple-screen-content">
                <div class="apple-ai-indicator">
                  <div class="apple-pulse-ring"></div>
                  <div class="apple-ai-icon">
                    <svg class="w-8 h-8 text-blue-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z" />
                    </svg>
                  </div>
                </div>
                <div class="apple-metrics-display">
                  <div class="apple-metric">
                    <span class="apple-metric-value">85%</span>
                    <span class="apple-metric-label">Form Score</span>
                  </div>
                  <div class="apple-metric">
                    <span class="apple-metric-value">42</span>
                    <span class="apple-metric-label">Reps</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Features Section -->
    <div class="apple-features-section">
      <div class="max-w-6xl mx-auto px-6">
        <!-- Section Header -->
        <div class="apple-section-header">
          <h2 class="apple-section-title">Built for Excellence</h2>
          <p class="apple-section-subtitle">
            Everything you need to transform your fitness journey with intelligent technology.
          </p>
        </div>

        <!-- Features Grid -->
        <div class="apple-features-grid">
          <!-- Real-time Analysis -->
          <div class="apple-feature-card">
            <div class="apple-feature-icon">
              <svg class="w-8 h-8" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M15 10l4.553-2.276A1 1 0 0121 8.618v6.764a1 1 0 01-1.447.894L15 14M5 18h8a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v8a2 2 0 002 2z" />
              </svg>
            </div>
            <h3 class="apple-feature-title">Real-time Analysis</h3>
            <p class="apple-feature-description">
              Get instant feedback on your form and technique using advanced AI-powered motion analysis technology.
            </p>
          </div>

          <!-- Personalized Plans -->
          <div class="apple-feature-card">
            <div class="apple-feature-icon">
              <svg class="w-8 h-8" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" />
              </svg>
            </div>
            <h3 class="apple-feature-title">Personalized Plans</h3>
            <p class="apple-feature-description">
              Custom workout plans that intelligently adapt to your progress, goals, and individual fitness level.
            </p>
          </div>

          <!-- Progress Tracking -->
          <div class="apple-feature-card">
            <div class="apple-feature-icon">
              <svg class="w-8 h-8" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z" />
              </svg>
            </div>
            <h3 class="apple-feature-title">Progress Tracking</h3>
            <p class="apple-feature-description">
              Comprehensive analytics and detailed insights to monitor and optimize your fitness journey.
            </p>
          </div>

          <!-- Community -->
          <div class="apple-feature-card">
            <div class="apple-feature-icon">
              <svg class="w-8 h-8" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
              </svg>
            </div>
            <h3 class="apple-feature-title">Community</h3>
            <p class="apple-feature-description">
              Connect with fitness enthusiasts, share achievements, and stay motivated together.
            </p>
          </div>
        </div>
      </div>
    </div>

    <!-- Technology Section -->
    <div class="apple-tech-section">
      <div class="max-w-6xl mx-auto px-6">
        <div class="apple-tech-content">
          <div class="apple-tech-text">
            <h2 class="apple-tech-title">Powered by Intelligence</h2>
            <p class="apple-tech-description">
              Our advanced AI technology analyzes your movements in real-time, providing precise feedback 
              and personalized recommendations to help you achieve optimal form and maximum results.
            </p>
            <div class="apple-tech-features">
              <div class="apple-tech-feature">
                <svg class="w-6 h-6 text-blue-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                </svg>
                <span>Computer Vision Analysis</span>
              </div>
              <div class="apple-tech-feature">
                <svg class="w-6 h-6 text-blue-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                </svg>
                <span>Machine Learning Insights</span>
              </div>
              <div class="apple-tech-feature">
                <svg class="w-6 h-6 text-blue-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                </svg>
                <span>Adaptive Recommendations</span>
              </div>
            </div>
          </div>
          <div class="apple-tech-visual">
            <div class="apple-floating-card">
              <div class="apple-card-header">
                <div class="apple-card-dots">
                  <div class="apple-dot red"></div>
                  <div class="apple-dot yellow"></div>
                  <div class="apple-dot green"></div>
                </div>
                <span class="apple-card-title">AI Analysis</span>
              </div>
              <div class="apple-card-content">
                <div class="apple-analysis-item">
                  <span class="apple-analysis-label">Squat Depth</span>
                  <div class="apple-progress-bar">
                    <div class="apple-progress-fill" style="width: 85%"></div>
                  </div>
                  <span class="apple-analysis-score">85%</span>
                </div>
                <div class="apple-analysis-item">
                  <span class="apple-analysis-label">Knee Alignment</span>
                  <div class="apple-progress-bar">
                    <div class="apple-progress-fill" style="width: 92%"></div>
                  </div>
                  <span class="apple-analysis-score">92%</span>
                </div>
                <div class="apple-analysis-item">
                  <span class="apple-analysis-label">Back Position</span>
                  <div class="apple-progress-bar">
                    <div class="apple-progress-fill" style="width: 78%"></div>
                  </div>
                  <span class="apple-analysis-score">78%</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- CTA Section -->
    <div class="apple-cta-section">
      <div class="max-w-4xl mx-auto px-6 text-center">
        <h2 class="apple-cta-title">Ready to Transform Your Fitness?</h2>
        <p class="apple-cta-description">
          Join thousands of users who are already achieving their fitness goals with intelligent technology.
        </p>
        <div class="apple-cta-actions">
          <router-link to="/auth/register" class="apple-button-cta-primary">
            Start Your Journey
          </router-link>
          <router-link to="/auth/login" class="apple-button-cta-secondary">
            I Already Have an Account
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// Home page component logic
</script>

<style scoped>
/* ===== Apple Design System Variables ===== */
:root {
  --apple-blue: #007AFF;
  --apple-blue-light: #5AC8FA;
  --apple-blue-dark: #0051D5;
  --apple-green: #34C759;
  --apple-red: #FF3B30;
  --apple-orange: #FF9500;
  --apple-yellow: #FFCC00;
  --apple-gray-900: #1D1D1F;
  --apple-gray-800: #424245;
  --apple-gray-700: #6E6E73;
  --apple-gray-600: #8E8E93;
  --apple-gray-500: #AEAEB2;
  --apple-gray-400: #C7C7CC;
  --apple-gray-300: #D1D1D6;
  --apple-gray-200: #E5E5EA;
  --apple-gray-100: #F2F2F7;
  --apple-gray-50: #F9F9FB;
}

/* ===== Base Styles ===== */
.bg-apple-background {
  background: linear-gradient(135deg, #F9F9FB 0%, #F2F2F7 100%);
}

/* ===== Hero Section ===== */
.apple-hero-section {
  @apply pt-20 pb-32;
  background: linear-gradient(135deg, rgba(0, 122, 255, 0.03) 0%, rgba(255, 255, 255, 0.8) 100%);
}

.apple-hero-content {
  @apply mb-16;
}

.apple-hero-title {
  @apply text-6xl md:text-7xl font-bold text-gray-900 mb-8 leading-tight;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
  letter-spacing: -0.02em;
}

.apple-hero-subtitle {
  @apply text-xl md:text-2xl text-gray-600 mb-12 leading-relaxed;
  font-weight: 400;
}

.apple-hero-actions {
  @apply flex flex-col sm:flex-row items-center justify-center space-y-4 sm:space-y-0 sm:space-x-6;
}

.apple-button-hero-primary {
  @apply inline-flex items-center px-8 py-4 bg-blue-500 text-white rounded-2xl font-semibold text-lg transition-all duration-300 shadow-lg hover:bg-blue-600 hover:scale-105 hover:shadow-xl;
}

.apple-button-hero-secondary {
  @apply inline-flex items-center px-8 py-4 bg-white text-gray-700 rounded-2xl font-semibold text-lg transition-all duration-300 shadow-md border border-gray-200 hover:bg-gray-50 hover:scale-105;
}

/* ===== Hero Visual ===== */
.apple-hero-visual {
  @apply flex justify-center;
}

.apple-device-mockup {
  @apply relative w-80 h-80 bg-white rounded-3xl shadow-2xl border border-gray-200 p-6;
  backdrop-filter: blur(20px);
  background-color: rgba(255, 255, 255, 0.95);
}

.apple-device-screen {
  @apply w-full h-full bg-gray-50 rounded-2xl overflow-hidden;
}

.apple-screen-content {
  @apply p-6 h-full flex flex-col justify-center items-center space-y-8;
}

.apple-ai-indicator {
  @apply relative;
}

.apple-pulse-ring {
  @apply absolute inset-0 w-20 h-20 border-4 border-blue-500 rounded-full;
  animation: apple-pulse 2s infinite;
}

.apple-ai-icon {
  @apply w-20 h-20 bg-blue-100 rounded-full flex items-center justify-center;
}

.apple-metrics-display {
  @apply flex space-x-8;
}

.apple-metric {
  @apply text-center;
}

.apple-metric-value {
  @apply block text-2xl font-bold text-gray-900;
}

.apple-metric-label {
  @apply block text-sm text-gray-600 mt-1;
}

/* ===== Features Section ===== */
.apple-features-section {
  @apply py-24;
}

.apple-section-header {
  @apply text-center mb-20;
}

.apple-section-title {
  @apply text-4xl md:text-5xl font-bold text-gray-900 mb-6;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
  letter-spacing: -0.02em;
}

.apple-section-subtitle {
  @apply text-xl text-gray-600 max-w-3xl mx-auto leading-relaxed;
}

.apple-features-grid {
  @apply grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8;
}

.apple-feature-card {
  @apply bg-white rounded-3xl p-8 shadow-lg border border-gray-100 transition-all duration-300 hover:shadow-xl hover:scale-105;
  backdrop-filter: blur(20px);
  background-color: rgba(255, 255, 255, 0.95);
}

.apple-feature-icon {
  @apply w-16 h-16 bg-blue-100 rounded-2xl flex items-center justify-center text-blue-600 mb-6;
}

.apple-feature-title {
  @apply text-xl font-semibold text-gray-900 mb-4;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
}

.apple-feature-description {
  @apply text-gray-600 leading-relaxed;
}

/* ===== Technology Section ===== */
.apple-tech-section {
  @apply py-24 bg-white;
}

.apple-tech-content {
  @apply grid grid-cols-1 lg:grid-cols-2 gap-16 items-center;
}

.apple-tech-title {
  @apply text-4xl font-bold text-gray-900 mb-6;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
  letter-spacing: -0.02em;
}

.apple-tech-description {
  @apply text-xl text-gray-600 mb-8 leading-relaxed;
}

.apple-tech-features {
  @apply space-y-4;
}

.apple-tech-feature {
  @apply flex items-center space-x-3 text-gray-700;
}

.apple-tech-visual {
  @apply flex justify-center;
}

.apple-floating-card {
  @apply bg-white rounded-3xl shadow-2xl border border-gray-100 p-6 w-full max-w-sm;
  backdrop-filter: blur(20px);
  background-color: rgba(255, 255, 255, 0.95);
}

.apple-card-header {
  @apply flex items-center justify-between mb-6 pb-4 border-b border-gray-100;
}

.apple-card-dots {
  @apply flex space-x-2;
}

.apple-dot {
  @apply w-3 h-3 rounded-full;
}

.apple-dot.red {
  @apply bg-red-500;
}

.apple-dot.yellow {
  @apply bg-yellow-500;
}

.apple-dot.green {
  @apply bg-green-500;
}

.apple-card-title {
  @apply text-sm font-semibold text-gray-700;
}

.apple-card-content {
  @apply space-y-4;
}

.apple-analysis-item {
  @apply flex items-center justify-between;
}

.apple-analysis-label {
  @apply text-sm text-gray-600 w-24;
}

.apple-progress-bar {
  @apply flex-1 h-2 bg-gray-200 rounded-full mx-3 overflow-hidden;
}

.apple-progress-fill {
  @apply h-full bg-blue-500 rounded-full transition-all duration-1000;
}

.apple-analysis-score {
  @apply text-sm font-semibold text-gray-700 w-10 text-right;
}

/* ===== CTA Section ===== */
.apple-cta-section {
  @apply py-24;
}

.apple-cta-title {
  @apply text-4xl md:text-5xl font-bold text-gray-900 mb-6;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
  letter-spacing: -0.02em;
}

.apple-cta-description {
  @apply text-xl text-gray-600 mb-12 leading-relaxed;
}

.apple-cta-actions {
  @apply flex flex-col sm:flex-row items-center justify-center space-y-4 sm:space-y-0 sm:space-x-6;
}

.apple-button-cta-primary {
  @apply inline-flex items-center px-8 py-4 bg-blue-500 text-white rounded-2xl font-semibold text-lg transition-all duration-300 shadow-lg hover:bg-blue-600 hover:scale-105 hover:shadow-xl;
}

.apple-button-cta-secondary {
  @apply inline-flex items-center px-8 py-4 bg-gray-100 text-gray-700 rounded-2xl font-semibold text-lg transition-all duration-300 hover:bg-gray-200 hover:scale-105;
}

/* ===== Animations ===== */
@keyframes apple-pulse {
  0% {
    transform: scale(1);
    opacity: 1;
  }
  50% {
    transform: scale(1.2);
    opacity: 0.5;
  }
  100% {
    transform: scale(1);
    opacity: 1;
  }
}

/* ===== Responsive Design ===== */
@media (max-width: 768px) {
  .apple-hero-title {
    @apply text-4xl;
  }
  
  .apple-hero-subtitle {
    @apply text-lg;
  }
  
  .apple-section-title {
    @apply text-3xl;
  }
  
  .apple-tech-title {
    @apply text-3xl;
  }
  
  .apple-cta-title {
    @apply text-3xl;
  }
  
  .apple-device-mockup {
    @apply w-64 h-64;
  }
}

/* ===== Accessibility ===== */
@media (prefers-reduced-motion: reduce) {
  * {
    animation-duration: 0.01ms !important;
    animation-iteration-count: 1 !important;
    transition-duration: 0.01ms !important;
  }
}

/* Focus states for accessibility */
a:focus,
button:focus {
  @apply outline-none ring-2 ring-blue-500 ring-offset-2;
}
</style> 