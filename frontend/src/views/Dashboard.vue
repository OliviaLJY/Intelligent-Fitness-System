<template>
  <div class="apple-dashboard-container">
    <!-- Loading State -->
    <div v-if="isLoading" class="apple-loading-container">
      <div class="apple-loading-spinner-large"></div>
      <p class="apple-loading-text">Loading your fitness dashboard...</p>
    </div>

    <!-- Error State -->
    <div v-else-if="error" class="apple-error-container">
      <div class="apple-error-icon">
        <svg class="w-12 h-12" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
        </svg>
      </div>
      <h3 class="apple-error-title">Something went wrong</h3>
      <p class="apple-error-message">{{ error }}</p>
      <button @click="handleRetry" class="apple-button-primary">
        Try Again
      </button>
    </div>

    <!-- Main Content -->
    <div v-else class="apple-dashboard-content">
      <!-- Header Section -->
      <div class="apple-dashboard-header">
        <div class="apple-header-content">
          <div class="apple-greeting-section">
            <h1 class="apple-dashboard-title">Good {{ timeOfDay }}</h1>
            <p class="apple-dashboard-subtitle">Ready to continue your fitness journey?</p>
          </div>
          <div class="apple-header-actions">
            <button @click="handleNewWorkout" class="apple-button-hero">
              <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6" />
              </svg>
              <span>Start Workout</span>
            </button>
          </div>
        </div>
      </div>

      <!-- Quick Stats -->
      <div class="apple-stats-section">
        <h2 class="apple-section-title">Your Progress</h2>
        <div class="apple-stats-grid">
          <!-- Total Workouts -->
          <div class="apple-stat-card">
            <div class="apple-stat-icon workouts">
              <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" />
              </svg>
            </div>
            <div class="apple-stat-content">
              <div class="apple-stat-value">{{ totalWorkouts }}</div>
              <div class="apple-stat-label">Total Sessions</div>
            </div>
            <div class="apple-stat-trend positive">
              <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
              </svg>
              <span>+12%</span>
            </div>
          </div>

          <!-- This Week -->
          <div class="apple-stat-card">
            <div class="apple-stat-icon weekly">
              <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
              </svg>
            </div>
            <div class="apple-stat-content">
              <div class="apple-stat-value">{{ workoutsThisWeek }}</div>
              <div class="apple-stat-label">This Week</div>
            </div>
            <div class="apple-stat-trend positive">
              <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
              </svg>
              <span>+3</span>
            </div>
          </div>

          <!-- Current Streak -->
          <div class="apple-stat-card">
            <div class="apple-stat-icon streak">
              <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 18.657A8 8 0 016.343 7.343S7 9 9 10c0-2 .5-5 2.986-7C14 5 16.09 5.777 17.656 7.343A7.975 7.975 0 0120 13a7.975 7.975 0 01-2.343 5.657z" />
              </svg>
            </div>
            <div class="apple-stat-content">
              <div class="apple-stat-value">{{ currentStreak }}</div>
              <div class="apple-stat-label">Day Streak</div>
            </div>
            <div class="apple-stat-trend streak">
              <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 18.657A8 8 0 016.343 7.343S7 9 9 10c0-2 .5-5 2.986-7C14 5 16.09 5.777 17.656 7.343A7.975 7.975 0 0120 13a7.975 7.975 0 01-2.343 5.657z" />
              </svg>
              <span>{{ currentStreak }}d</span>
            </div>
          </div>

          <!-- Calories Burned -->
          <div class="apple-stat-card">
            <div class="apple-stat-icon calories">
              <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z" />
              </svg>
            </div>
            <div class="apple-stat-content">
              <div class="apple-stat-value">{{ formattedCalories }}</div>
              <div class="apple-stat-label">Calories</div>
            </div>
            <div class="apple-stat-trend positive">
              <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
              </svg>
              <span>+8%</span>
            </div>
          </div>
        </div>
      </div>

      <!-- Recent Activity & Next Workout Grid -->
      <div class="apple-content-grid">
        <!-- Recent Activity -->
        <div class="apple-activity-section">
          <div class="apple-section-header">
            <h2 class="apple-section-title">Recent Activity</h2>
            <button class="apple-link-button">View All</button>
          </div>
          
          <div class="apple-activity-list">
            <div
              v-for="workout in recentActivities"
              :key="workout.id"
              class="apple-activity-item"
              @click="handleViewDetails(workout.id)"
            >
              <div class="apple-activity-icon">
                <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                </svg>
              </div>
              <div class="apple-activity-content">
                <h4 class="apple-activity-title">{{ workout.title || 'Workout Session' }}</h4>
                <p class="apple-activity-meta">{{ workout.duration || '45 min' }} • {{ workout.exercises || '8 exercises' }}</p>
              </div>
              <div class="apple-activity-date">
                <span>{{ formatWorkoutDate(workout.date) }}</span>
                <svg class="w-4 h-4 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
                </svg>
              </div>
            </div>
            
            <!-- Empty State -->
            <div v-if="recentActivities.length === 0" class="apple-empty-activity">
              <div class="apple-empty-icon">
                <svg class="w-12 h-12" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1" d="M13 10V3L4 14h7v7l9-11h-7z" />
                </svg>
              </div>
              <h4 class="apple-empty-title">No workouts yet</h4>
              <p class="apple-empty-text">Start your first workout to see your activity here</p>
            </div>
          </div>
        </div>

        <!-- Next Workout -->
        <div class="apple-next-workout-section">
          <div class="apple-next-workout-card">
            <div class="apple-next-workout-header">
              <h3 class="apple-next-workout-title">Next Workout</h3>
              <div class="apple-workout-time">
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
                <span>Tomorrow 7:00 AM</span>
              </div>
            </div>
            
            <div class="apple-workout-info">
              <h4 class="apple-workout-name">Upper Body Strength</h4>
              <div class="apple-workout-details">
                <div class="apple-workout-detail">
                  <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                  </svg>
                  <span>45 min</span>
                </div>
                <div class="apple-workout-detail">
                  <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" />
                  </svg>
                  <span>6 exercises</span>
                </div>
              </div>
            </div>
            
            <div class="apple-workout-actions">
              <button @click="handleViewWorkout" class="apple-button-outline">
                View Details
              </button>
              <button @click="handleStartWorkout" class="apple-button-primary">
                Start Now
              </button>
            </div>
          </div>

          <!-- Weekly Goal Progress -->
          <div class="apple-goal-card">
            <div class="apple-goal-header">
              <h3 class="apple-goal-title">Weekly Goal</h3>
              <span class="apple-goal-percentage">{{ weeklyGoalPercentage }}%</span>
            </div>
            
            <div class="apple-goal-progress">
              <div class="apple-progress-track">
                <div 
                  class="apple-progress-fill"
                  :style="{ width: `${weeklyGoalPercentage}%` }"
                ></div>
              </div>
              <div class="apple-goal-text">
                {{ workoutsThisWeek }} of {{ weeklyGoalTarget }} workouts
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useWorkoutStore } from '@/stores/workout'
import { useProgressStore } from '@/stores/progress'
import { useRouter } from 'vue-router'

const router = useRouter()
const workoutStore = useWorkoutStore()
const progressStore = useProgressStore()

const isLoading = ref(true)
const error = ref('')
const weeklyGoalTarget = ref(5)

onMounted(async () => {
  try {
    isLoading.value = true
    await Promise.all([
      workoutStore.fetchWorkoutHistory(),
      progressStore.fetchProgressData('weekly'),
      progressStore.fetchAchievements()
    ])
  } catch (e) {
    error.value = 'Failed to load dashboard data.'
  } finally {
    isLoading.value = false
  }
})

const timeOfDay = computed(() => {
  const hour = new Date().getHours()
  if (hour < 12) return 'Morning'
  if (hour < 18) return 'Afternoon'
  return 'Evening'
})

const totalWorkouts = computed(() => workoutStore.workoutHistory?.length || 0)
const workoutsThisWeek = computed(() => progressStore.analytics?.weekly?.workouts || 0)
const currentStreak = computed(() => progressStore.currentStreak || 0)
const caloriesBurned = computed(() => progressStore.analytics?.weekly?.calories || 0)
const recentActivities = computed(() => workoutStore.recentWorkouts?.slice(0, 4) || [])

const formattedCalories = computed(() => {
  if (caloriesBurned.value >= 1000) {
    return `${(caloriesBurned.value / 1000).toFixed(1)}k`
  }
  return caloriesBurned.value.toString()
})

const weeklyGoalPercentage = computed(() => {
  return Math.min(100, Math.round((workoutsThisWeek.value / weeklyGoalTarget.value) * 100))
})

function formatWorkoutDate(date) {
  if (!date) return 'Recently'
  const workoutDate = new Date(date)
  const now = new Date()
  const diffTime = Math.abs(now - workoutDate)
  const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24))
  
  if (diffDays === 1) return 'Yesterday'
  if (diffDays <= 7) return `${diffDays} days ago`
  return workoutDate.toLocaleDateString()
}

function handleNewWorkout() {
  router.push('/workout')
}

function handleViewDetails(workoutId) {
  router.push(`/workout/history?workout=${workoutId}`)
}

function handleViewWorkout() {
  router.push('/workout/templates')
}

function handleStartWorkout() {
  router.push('/workout')
}

function handleRetry() {
  window.location.reload()
}
</script>

<style scoped>
/* ===== Dashboard Container ===== */
.apple-dashboard-container {
  @apply min-h-screen bg-apple-background;
  background: linear-gradient(135deg, #F9F9FB 0%, #F2F2F7 100%);
}

/* ===== Loading State ===== */
.apple-loading-container {
  @apply flex flex-col items-center justify-center min-h-screen space-y-6;
}

.apple-loading-spinner-large {
  @apply w-12 h-12 border-4 border-gray-300 border-t-blue-500 rounded-full;
  animation: spin 1s linear infinite;
}

.apple-loading-text {
  @apply text-lg text-gray-600 font-medium;
}

/* ===== Error State ===== */
.apple-error-container {
  @apply flex flex-col items-center justify-center min-h-screen space-y-6 px-6;
}

.apple-error-icon {
  @apply w-20 h-20 bg-red-100 rounded-full flex items-center justify-center text-red-500;
}

.apple-error-title {
  @apply text-2xl font-bold text-gray-900;
}

.apple-error-message {
  @apply text-gray-600 text-center;
}

/* ===== Main Content ===== */
.apple-dashboard-content {
  @apply max-w-7xl mx-auto px-6 py-8 space-y-12;
}

/* ===== Header ===== */
.apple-dashboard-header {
  @apply space-y-8;
}

.apple-header-content {
  @apply flex flex-col lg:flex-row lg:items-center lg:justify-between space-y-6 lg:space-y-0;
}

.apple-greeting-section {
  @apply space-y-2;
}

.apple-dashboard-title {
  @apply text-4xl md:text-5xl font-bold text-gray-900;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
  letter-spacing: -0.02em;
}

.apple-dashboard-subtitle {
  @apply text-xl text-gray-600;
}

.apple-header-actions {
  @apply flex items-center space-x-4;
}

.apple-button-hero {
  @apply inline-flex items-center space-x-3 px-8 py-4 bg-blue-500 text-white rounded-2xl font-semibold text-lg transition-all duration-300 shadow-lg hover:bg-blue-600 hover:scale-105 hover:shadow-xl;
}

/* ===== Stats Section ===== */
.apple-stats-section {
  @apply space-y-6;
}

.apple-section-title {
  @apply text-2xl font-bold text-gray-900 mb-6;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
}

.apple-stats-grid {
  @apply grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6;
}

.apple-stat-card {
  @apply bg-white rounded-3xl p-6 shadow-lg border border-gray-100 transition-all duration-300 hover:shadow-xl hover:scale-105;
  backdrop-filter: blur(20px);
  background-color: rgba(255, 255, 255, 0.95);
}

.apple-stat-icon {
  @apply w-12 h-12 rounded-2xl flex items-center justify-center mb-4;
}

.apple-stat-icon.workouts {
  @apply bg-blue-100 text-blue-600;
}

.apple-stat-icon.weekly {
  @apply bg-green-100 text-green-600;
}

.apple-stat-icon.streak {
  @apply bg-orange-100 text-orange-600;
}

.apple-stat-icon.calories {
  @apply bg-purple-100 text-purple-600;
}

.apple-stat-content {
  @apply mb-4;
}

.apple-stat-value {
  @apply text-3xl font-bold text-gray-900 mb-1;
}

.apple-stat-label {
  @apply text-sm text-gray-600 font-medium;
}

.apple-stat-trend {
  @apply flex items-center space-x-1 text-xs font-semibold;
}

.apple-stat-trend.positive {
  @apply text-green-600;
}

.apple-stat-trend.streak {
  @apply text-orange-600;
}

/* ===== Content Grid ===== */
.apple-content-grid {
  @apply grid grid-cols-1 lg:grid-cols-3 gap-8;
}

/* ===== Activity Section ===== */
.apple-activity-section {
  @apply lg:col-span-2 space-y-6;
}

.apple-section-header {
  @apply flex items-center justify-between;
}

.apple-link-button {
  @apply text-blue-500 hover:text-blue-600 font-semibold text-sm transition-colors duration-200;
}

.apple-activity-list {
  @apply bg-white rounded-3xl shadow-lg border border-gray-100 overflow-hidden;
  backdrop-filter: blur(20px);
  background-color: rgba(255, 255, 255, 0.95);
}

.apple-activity-item {
  @apply flex items-center justify-between p-6 border-b border-gray-100 last:border-b-0 cursor-pointer transition-all duration-200 hover:bg-gray-50;
}

.apple-activity-icon {
  @apply w-10 h-10 bg-green-100 rounded-xl flex items-center justify-center text-green-600 mr-4;
}

.apple-activity-content {
  @apply flex-1;
}

.apple-activity-title {
  @apply text-lg font-semibold text-gray-900 mb-1;
}

.apple-activity-meta {
  @apply text-sm text-gray-600;
}

.apple-activity-date {
  @apply flex items-center space-x-2 text-sm text-gray-500;
}

/* ===== Empty Activity State ===== */
.apple-empty-activity {
  @apply text-center py-12 space-y-4;
}

.apple-empty-icon {
  @apply w-16 h-16 bg-gray-100 rounded-full flex items-center justify-center mx-auto text-gray-400;
}

.apple-empty-title {
  @apply text-lg font-semibold text-gray-900;
}

.apple-empty-text {
  @apply text-gray-600;
}

/* ===== Next Workout Section ===== */
.apple-next-workout-section {
  @apply space-y-6;
}

.apple-next-workout-card {
  @apply bg-white rounded-3xl p-6 shadow-lg border border-gray-100;
  backdrop-filter: blur(20px);
  background-color: rgba(255, 255, 255, 0.95);
}

.apple-next-workout-header {
  @apply flex items-center justify-between mb-4;
}

.apple-next-workout-title {
  @apply text-lg font-semibold text-gray-900;
}

.apple-workout-time {
  @apply flex items-center space-x-1 text-sm text-gray-600;
}

.apple-workout-info {
  @apply mb-6;
}

.apple-workout-name {
  @apply text-xl font-bold text-gray-900 mb-3;
}

.apple-workout-details {
  @apply flex items-center space-x-4;
}

.apple-workout-detail {
  @apply flex items-center space-x-1 text-sm text-gray-600;
}

.apple-workout-actions {
  @apply flex space-x-3;
}

.apple-button-outline {
  @apply flex-1 px-4 py-3 bg-gray-100 text-gray-700 rounded-xl font-semibold transition-all duration-200 hover:bg-gray-200;
}

.apple-button-primary {
  @apply flex-1 px-4 py-3 bg-blue-500 text-white rounded-xl font-semibold transition-all duration-200 hover:bg-blue-600;
}

/* ===== Goal Card ===== */
.apple-goal-card {
  @apply bg-white rounded-3xl p-6 shadow-lg border border-gray-100;
  backdrop-filter: blur(20px);
  background-color: rgba(255, 255, 255, 0.95);
}

.apple-goal-header {
  @apply flex items-center justify-between mb-4;
}

.apple-goal-title {
  @apply text-lg font-semibold text-gray-900;
}

.apple-goal-percentage {
  @apply text-2xl font-bold text-blue-500;
}

.apple-goal-progress {
  @apply space-y-3;
}

.apple-progress-track {
  @apply w-full h-2 bg-gray-200 rounded-full overflow-hidden;
}

.apple-progress-fill {
  @apply h-full bg-blue-500 rounded-full transition-all duration-1000;
}

.apple-goal-text {
  @apply text-sm text-gray-600;
}

/* ===== Animations ===== */
@keyframes spin {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}

/* ===== Responsive Design ===== */
@media (max-width: 768px) {
  .apple-dashboard-title {
    @apply text-3xl;
  }
  
  .apple-stats-grid {
    @apply grid-cols-2;
  }
  
  .apple-content-grid {
    @apply grid-cols-1;
  }
  
  .apple-workout-actions {
    @apply flex-col space-x-0 space-y-3;
  }
}

/* ===== Accessibility ===== */
@media (prefers-reduced-motion: reduce) {
  .apple-loading-spinner-large {
    animation: none;
  }
}

/* Focus states for accessibility */
button:focus,
.apple-activity-item:focus {
  @apply outline-none ring-2 ring-blue-500 ring-offset-2;
}
</style> 