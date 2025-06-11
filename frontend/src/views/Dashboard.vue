<template>
  <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-10">
    <!-- Header -->
    <div class="flex flex-col sm:flex-row sm:items-center sm:justify-between mb-10">
      <div>
        <h1 class="text-3xl font-extrabold text-blue-900 mb-1">Dashboard</h1>
        <p class="text-lg text-blue-600">Welcome back! Here's your fitness overview.</p>
      </div>
      <div class="mt-6 sm:mt-0">
        <router-link
          to="/workout"
          class="px-6 py-3 rounded-full text-white font-bold bg-gradient-to-r from-blue-500 to-blue-700 shadow hover:from-blue-600 hover:to-blue-800 focus:outline-none focus:ring-2 focus:ring-blue-400 transition"
        >
          <svg class="-ml-1 mr-2 h-5 w-5 inline-block align-middle" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6" />
          </svg>
          New Workout
        </router-link>
      </div>
    </div>

    <!-- Stats Overview -->
    <div class="grid grid-cols-1 gap-8 sm:grid-cols-2 lg:grid-cols-4 mb-10">
      <div class="bg-white border border-blue-100 rounded-2xl shadow p-6 flex flex-col items-center">
        <div class="bg-blue-100 rounded-full p-3 mb-3">
          <svg class="h-7 w-7 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" />
          </svg>
        </div>
        <div class="text-center">
          <div class="text-2xl font-bold text-blue-900">12</div>
          <div class="text-sm text-blue-600">Total Workouts</div>
        </div>
      </div>
      <div class="bg-white border border-blue-100 rounded-2xl shadow p-6 flex flex-col items-center">
        <div class="bg-blue-100 rounded-full p-3 mb-3">
          <svg class="h-7 w-7 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0" />
          </svg>
        </div>
        <div class="text-center">
          <div class="text-2xl font-bold text-blue-900">4</div>
          <div class="text-sm text-blue-600">This Week</div>
        </div>
      </div>
      <div class="bg-white border border-blue-100 rounded-2xl shadow p-6 flex flex-col items-center">
        <div class="bg-blue-100 rounded-full p-3 mb-3">
          <svg class="h-7 w-7 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m4 0h-1v4h-1m-4 0h-1v-4h-1" />
          </svg>
        </div>
        <div class="text-center">
          <div class="text-2xl font-bold text-blue-900">7</div>
          <div class="text-sm text-blue-600">Current Streak</div>
        </div>
      </div>
      <div class="bg-white border border-blue-100 rounded-2xl shadow p-6 flex flex-col items-center">
        <div class="bg-blue-100 rounded-full p-3 mb-3">
          <svg class="h-7 w-7 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z" />
          </svg>
        </div>
        <div class="text-center">
          <div class="text-2xl font-bold text-blue-900">1,250</div>
          <div class="text-sm text-blue-600">Calories Burned</div>
        </div>
      </div>
    </div>

    <!-- Recent Activity -->
    <div class="bg-white border border-blue-100 rounded-2xl shadow p-8 mb-10">
      <h2 class="text-xl font-bold text-blue-900 mb-4">Recent Activity</h2>
      <ul class="divide-y divide-blue-50">
        <li class="py-4 flex items-center justify-between">
          <div class="flex items-center space-x-3">
            <span class="inline-block bg-blue-100 p-2 rounded-full">
              <svg class="h-5 w-5 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
              </svg>
            </span>
            <span class="text-blue-800 font-medium">Completed Full Body Workout</span>
          </div>
          <span class="text-sm text-blue-400">Today</span>
        </li>
        <li class="py-4 flex items-center justify-between">
          <div class="flex items-center space-x-3">
            <span class="inline-block bg-blue-100 p-2 rounded-full">
              <svg class="h-5 w-5 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6 6 0 10-12 0v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9" />
              </svg>
            </span>
            <span class="text-blue-800 font-medium">Started Cardio Session</span>
          </div>
          <span class="text-sm text-blue-400">Yesterday</span>
        </li>
        <li class="py-4 flex items-center justify-between">
          <div class="flex items-center space-x-3">
            <span class="inline-block bg-blue-100 p-2 rounded-full">
              <svg class="h-5 w-5 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 17l4 4 4-4m-4-5v9" />
              </svg>
            </span>
            <span class="text-blue-800 font-medium">Achieved Weekly Goal</span>
          </div>
          <span class="text-sm text-blue-400">2 days ago</span>
        </li>
      </ul>
    </div>

    <!-- Next Workout -->
    <div class="bg-gradient-to-r from-blue-100 to-blue-50 border border-blue-100 rounded-2xl shadow p-8 flex flex-col sm:flex-row items-center justify-between">
      <div>
        <h2 class="text-xl font-bold text-blue-900 mb-2">Next Workout</h2>
        <p class="text-blue-700 mb-4">Upper Body Strength · Tomorrow 7:00 AM</p>
        <router-link
          to="/workout"
          class="px-5 py-2 rounded-full text-white font-bold bg-gradient-to-r from-blue-500 to-blue-700 shadow hover:from-blue-600 hover:to-blue-800 focus:outline-none focus:ring-2 focus:ring-blue-400 transition"
        >
          View Details
        </router-link>
      </div>
      <div class="mt-6 sm:mt-0">
        <svg class="h-12 w-12 text-blue-200" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z" />
        </svg>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const recentActivities = ref([
  {
    content: 'Completed',
    target: 'Upper Body Workout',
    date: '2 hours ago',
    datetime: '2024-03-20T10:00',
    icon: 'CheckCircleIcon',
    iconBackground: 'bg-green-500'
  },
  {
    content: 'Started',
    target: 'Cardio Session',
    date: 'Yesterday',
    datetime: '2024-03-19T15:30',
    icon: 'PlayIcon',
    iconBackground: 'bg-blue-500'
  },
  {
    content: 'Achieved new personal best in',
    target: 'Squats',
    date: '2 days ago',
    datetime: '2024-03-18T09:15',
    icon: 'TrophyIcon',
    iconBackground: 'bg-yellow-500'
  }
])

// Icon components using template syntax
const CheckCircleIcon = {
  template: `
    <svg class="h-5 w-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
    </svg>
  `
}

const PlayIcon = {
  template: `
    <svg class="h-5 w-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14.752 11.168l-3.197-2.132A1 1 0 0010 9.87v4.263a1 1 0 001.555.832l3.197-2.132a1 1 0 000-1.664z" />
      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
    </svg>
  `
}

const TrophyIcon = {
  template: `
    <svg class="h-5 w-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 3v4M3 5h4M6 17v4m-2-2h4m5-16l2.286 6.857L21 12l-5.714 2.143L13 21l-2.286-6.857L5 12l5.714-2.143L13 3z" />
    </svg>
  `
}
</script> 