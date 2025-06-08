<template>
  <div class="space-y-6">
    <!-- Header -->
    <div class="flex justify-between items-center">
      <div>
        <h1 class="text-2xl font-bold text-gray-900">Workout History</h1>
        <p class="mt-1 text-sm text-gray-500">Track your progress and review past workouts</p>
      </div>
      <div class="flex space-x-3">
        <button
          type="button"
          class="inline-flex items-center px-4 py-2 border border-gray-300 shadow-sm text-sm font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
        >
          <svg class="-ml-1 mr-2 h-5 w-5 text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 4a1 1 0 011-1h16a1 1 0 011 1v2.586a1 1 0 01-.293.707l-6.414 6.414a1 1 0 00-.293.707V17l-4 4v-6.586a1 1 0 00-.293-.707L3.293 7.293A1 1 0 013 6.586V4z" />
          </svg>
          Filter
        </button>
        <button
          type="button"
          class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md text-white bg-primary-600 hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
        >
          Export Data
        </button>
      </div>
    </div>

    <!-- Stats overview -->
    <div class="grid grid-cols-1 gap-5 sm:grid-cols-2 lg:grid-cols-4">
      <!-- Total workouts -->
      <div class="bg-white overflow-hidden shadow-sm rounded-lg">
        <div class="p-5">
          <div class="flex items-center">
            <div class="flex-shrink-0">
              <svg class="h-6 w-6 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" />
              </svg>
            </div>
            <div class="ml-5 w-0 flex-1">
              <dl>
                <dt class="text-sm font-medium text-gray-500 truncate">Total Workouts</dt>
                <dd class="flex items-baseline">
                  <div class="text-2xl font-semibold text-gray-900">24</div>
                </dd>
              </dl>
            </div>
          </div>
        </div>
      </div>

      <!-- This week -->
      <div class="bg-white overflow-hidden shadow-sm rounded-lg">
        <div class="p-5">
          <div class="flex items-center">
            <div class="flex-shrink-0">
              <svg class="h-6 w-6 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
              </svg>
            </div>
            <div class="ml-5 w-0 flex-1">
              <dl>
                <dt class="text-sm font-medium text-gray-500 truncate">This Week</dt>
                <dd class="flex items-baseline">
                  <div class="text-2xl font-semibold text-gray-900">4</div>
                </dd>
              </dl>
            </div>
          </div>
        </div>
      </div>

      <!-- Average duration -->
      <div class="bg-white overflow-hidden shadow-sm rounded-lg">
        <div class="p-5">
          <div class="flex items-center">
            <div class="flex-shrink-0">
              <svg class="h-6 w-6 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
            </div>
            <div class="ml-5 w-0 flex-1">
              <dl>
                <dt class="text-sm font-medium text-gray-500 truncate">Avg. Duration</dt>
                <dd class="flex items-baseline">
                  <div class="text-2xl font-semibold text-gray-900">45m</div>
                </dd>
              </dl>
            </div>
          </div>
        </div>
      </div>

      <!-- Calories burned -->
      <div class="bg-white overflow-hidden shadow-sm rounded-lg">
        <div class="p-5">
          <div class="flex items-center">
            <div class="flex-shrink-0">
              <svg class="h-6 w-6 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z" />
              </svg>
            </div>
            <div class="ml-5 w-0 flex-1">
              <dl>
                <dt class="text-sm font-medium text-gray-500 truncate">Calories Burned</dt>
                <dd class="flex items-baseline">
                  <div class="text-2xl font-semibold text-gray-900">12.5k</div>
                </dd>
              </dl>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Workout list -->
    <div class="bg-white shadow-sm rounded-lg overflow-hidden">
      <div class="divide-y divide-gray-200">
        <!-- Workout item -->
        <div class="p-6">
          <div class="flex items-center justify-between">
            <div class="flex items-center space-x-4">
              <div class="flex-shrink-0">
                <div class="h-12 w-12 rounded-lg bg-primary-100 flex items-center justify-center">
                  <svg class="h-6 w-6 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
                  </svg>
                </div>
              </div>
              <div>
                <h3 class="text-lg font-medium text-gray-900">Full Body Workout</h3>
                <div class="mt-1 flex items-center space-x-2 text-sm text-gray-500">
                  <span>March 15, 2024</span>
                  <span>•</span>
                  <span>45 minutes</span>
                  <span>•</span>
                  <span>8 exercises</span>
                </div>
              </div>
            </div>
            <div class="flex items-center space-x-4">
              <span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-green-100 text-green-800">
                Completed
              </span>
              <button
                type="button"
                class="inline-flex items-center p-2 border border-gray-300 shadow-sm text-sm font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
              >
                View Details
              </button>
            </div>
          </div>
        </div>

        <!-- More workout items... -->
      </div>
    </div>
  </div>
</template>

<script setup>
// Component logic will be added here
</script> 