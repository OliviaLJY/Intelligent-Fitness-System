<template>
  <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-10">
    <div v-if="isLoading" class="flex justify-center items-center py-20">
      <span class="text-blue-600 text-lg font-bold animate-pulse">Loading dashboard...</span>
    </div>
    <div v-else>
      <div v-if="error" class="mb-6 p-4 bg-red-50 text-red-700 rounded-lg text-center">{{ error }}</div>
      <!-- Header -->
      <div class="flex flex-col sm:flex-row sm:items-center sm:justify-between mb-10">
        <div>
          <h1 class="text-3xl font-extrabold text-blue-900 mb-1">Dashboard</h1>
          <p class="text-lg text-blue-600">Welcome back! Here's your fitness overview.</p>
        </div>
        <div class="mt-6 sm:mt-0">
          <button @click="handleNewWorkout" class="px-6 py-3 rounded-full text-white font-bold bg-gradient-to-r from-blue-500 to-blue-700 shadow hover:from-blue-600 hover:to-blue-800 focus:outline-none focus:ring-2 focus:ring-blue-400 transition">
            <svg class="-ml-1 mr-2 h-5 w-5 inline-block align-middle" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6" />
            </svg>
            New Workout
          </button>
        </div>
      </div>

      <!-- Stats Overview -->
      <div class="grid grid-cols-1 gap-8 sm:grid-cols-2 lg:grid-cols-4 mb-10">
        <div class="bg-white border border-blue-100 rounded-2xl shadow p-6 flex flex-col items-center">
          <div class="bg-blue-100 rounded-full p-3 mb-3">
            <svg class="h-8 w-8 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" />
            </svg>
          </div>
          <div class="text-center">
            <div class="text-2xl font-bold text-blue-900">{{ totalWorkouts }}</div>
            <div class="text-sm text-blue-600">Total Workouts</div>
          </div>
        </div>
        <div class="bg-white border border-blue-100 rounded-2xl shadow p-6 flex flex-col items-center">
          <div class="bg-blue-100 rounded-full p-3 mb-3">
            <svg class="h-8 w-8 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0" />
            </svg>
          </div>
          <div class="text-center">
            <div class="text-2xl font-bold text-blue-900">{{ workoutsThisWeek }}</div>
            <div class="text-sm text-blue-600">This Week</div>
          </div>
        </div>
        <div class="bg-white border border-blue-100 rounded-2xl shadow p-6 flex flex-col items-center">
          <div class="bg-blue-100 rounded-full p-3 mb-3">
            <svg class="h-8 w-8 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m4 0h-1v4h-1m-4 0h-1v-4h-1" />
            </svg>
          </div>
          <div class="text-center">
            <div class="text-2xl font-bold text-blue-900">{{ currentStreak }}</div>
            <div class="text-sm text-blue-600">Current Streak</div>
          </div>
        </div>
        <div class="bg-white border border-blue-100 rounded-2xl shadow p-6 flex flex-col items-center">
          <div class="bg-blue-100 rounded-full p-3 mb-3">
            <svg class="h-8 w-8 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z" />
            </svg>
          </div>
          <div class="text-center">
            <div class="text-2xl font-bold text-blue-900">{{ caloriesBurned }}</div>
            <div class="text-sm text-blue-600">Calories Burned</div>
          </div>
        </div>
      </div>

      <!-- Recent Activity -->
      <div class="bg-white border border-blue-100 rounded-2xl shadow p-8 mb-10">
        <h2 class="text-xl font-bold text-blue-900 mb-4">Recent Activity</h2>
        <ul class="divide-y divide-blue-50">
          <li v-for="workout in recentActivities" :key="workout.id" class="py-4 flex items-center justify-between">
            <div class="flex items-center space-x-3">
              <span class="inline-block bg-blue-100 p-2 rounded-full">
                <svg class="h-5 w-5 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                </svg>
              </span>
              <span class="text-blue-800 font-medium">{{ workout.title || 'Workout Session' }}</span>
            </div>
            <div class="flex items-center space-x-2">
              <span class="text-sm text-blue-400">{{ workout.date || 'Recently' }}</span>
              <button @click="handleViewDetails(workout.id)" class="ml-2 px-3 py-1 rounded bg-blue-50 text-blue-700 hover:bg-blue-100 text-xs font-semibold transition">View Details</button>
            </div>
          </li>
        </ul>
      </div>

      <!-- Next Workout -->
      <div class="bg-gradient-to-r from-blue-100 to-blue-50 border border-blue-100 rounded-2xl shadow p-8 flex flex-col sm:flex-row items-center justify-between">
        <div>
          <h2 class="text-xl font-bold text-blue-900 mb-2">Next Workout</h2>
          <p class="text-blue-700 mb-4">Upper Body Strength · Tomorrow 7:00 AM</p>
          <button @click="handleNewWorkout" class="px-5 py-2 rounded-full text-white font-bold bg-gradient-to-r from-blue-500 to-blue-700 shadow hover:from-blue-600 hover:to-blue-800 focus:outline-none focus:ring-2 focus:ring-blue-400 transition">
            View Details
          </button>
        </div>
        <div class="mt-6 sm:mt-0">
          <svg class="h-12 w-12 text-blue-200" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z" />
          </svg>
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

const totalWorkouts = computed(() => workoutStore.workoutHistory.length)
const workoutsThisWeek = computed(() => progressStore.analytics.weekly?.workouts || 0)
const currentStreak = computed(() => progressStore.currentStreak)
const caloriesBurned = computed(() => progressStore.analytics.weekly?.calories || 0)
const recentActivities = computed(() => workoutStore.recentWorkouts)

function handleNewWorkout() {
  router.push('/workout')
}
function handleViewDetails(workoutId) {
  router.push(`/workout/history?workout=${workoutId}`)
}
</script> 