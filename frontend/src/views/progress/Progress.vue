<template>
  <div class="space-y-6">
    <!-- Header -->
    <div class="flex justify-between items-center">
      <div>
        <h1 class="text-2xl font-bold text-gray-900">Progress</h1>
        <p class="mt-1 text-sm text-gray-500">Track your fitness journey and achievements</p>
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
                  <div class="ml-2 flex items-baseline text-sm font-semibold text-green-600">
                    <svg class="self-center flex-shrink-0 h-5 w-5 text-green-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
                    </svg>
                    <span class="sr-only">Increased by</span>
                    12%
                  </div>
                </dd>
              </dl>
            </div>
          </div>
        </div>
      </div>

      <!-- Weekly progress -->
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
                  <div class="ml-2 flex items-baseline text-sm font-semibold text-green-600">
                    <svg class="self-center flex-shrink-0 h-5 w-5 text-green-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
                    </svg>
                    <span class="sr-only">Increased by</span>
                    25%
                  </div>
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
                  <div class="ml-2 flex items-baseline text-sm font-semibold text-green-600">
                    <svg class="self-center flex-shrink-0 h-5 w-5 text-green-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
                    </svg>
                    <span class="sr-only">Increased by</span>
                    8%
                  </div>
                </dd>
              </dl>
            </div>
          </div>
        </div>
      </div>

      <!-- Current streak -->
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
                <dt class="text-sm font-medium text-gray-500 truncate">Current Streak</dt>
                <dd class="flex items-baseline">
                  <div class="text-2xl font-semibold text-gray-900">5 days</div>
                  <div class="ml-2 flex items-baseline text-sm font-semibold text-green-600">
                    <svg class="self-center flex-shrink-0 h-5 w-5 text-green-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
                    </svg>
                    <span class="sr-only">Increased by</span>
                    2 days
                  </div>
                </dd>
              </dl>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Charts and trends -->
    <div class="grid grid-cols-1 gap-6 lg:grid-cols-2">
      <!-- Workout frequency -->
      <div class="bg-white shadow-sm rounded-lg overflow-hidden">
        <div class="p-6">
          <h3 class="text-lg font-medium text-gray-900">Workout Frequency</h3>
          <div class="mt-4">
            <!-- Placeholder for chart -->
            <div class="h-64 bg-gray-50 rounded-lg flex items-center justify-center">
              <p class="text-sm text-gray-500">Workout frequency chart will be displayed here</p>
            </div>
          </div>
        </div>
      </div>

      <!-- Calories burned trend -->
      <div class="bg-white shadow-sm rounded-lg overflow-hidden">
        <div class="p-6">
          <h3 class="text-lg font-medium text-gray-900">Calories Burned Trend</h3>
          <div class="mt-4">
            <!-- Placeholder for chart -->
            <div class="h-64 bg-gray-50 rounded-lg flex items-center justify-center">
              <p class="text-sm text-gray-500">Calories burned trend chart will be displayed here</p>
            </div>
          </div>
        </div>
      </div>

      <!-- Exercise distribution -->
      <div class="bg-white shadow-sm rounded-lg overflow-hidden">
        <div class="p-6">
          <h3 class="text-lg font-medium text-gray-900">Exercise Distribution</h3>
          <div class="mt-4">
            <!-- Placeholder for chart -->
            <div class="h-64 bg-gray-50 rounded-lg flex items-center justify-center">
              <p class="text-sm text-gray-500">Exercise distribution chart will be displayed here</p>
            </div>
          </div>
        </div>
      </div>

      <!-- Progress milestones -->
      <div class="bg-white shadow-sm rounded-lg overflow-hidden">
        <div class="p-6">
          <h3 class="text-lg font-medium text-gray-900">Progress Milestones</h3>
          <div class="mt-4 flow-root">
            <ul class="-mb-8">
              <li>
                <div class="relative pb-8">
                  <span class="absolute top-4 left-4 -ml-px h-full w-0.5 bg-gray-200" aria-hidden="true"></span>
                  <div class="relative flex space-x-3">
                    <div>
                      <span class="h-8 w-8 rounded-full bg-green-100 flex items-center justify-center ring-8 ring-white">
                        <svg class="h-5 w-5 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                        </svg>
                      </span>
                    </div>
                    <div class="min-w-0 flex-1 pt-1.5 flex justify-between space-x-4">
                      <div>
                        <p class="text-sm text-gray-500">Reached <span class="font-medium text-gray-900">20 workouts</span> milestone</p>
                      </div>
                      <div class="text-right text-sm whitespace-nowrap text-gray-500">
                        <time datetime="2024-03-15">2 days ago</time>
                      </div>
                    </div>
                  </div>
                </div>
              </li>

              <!-- More milestones... -->
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// Component logic will be added here
// This will include chart initialization and data management
</script> 