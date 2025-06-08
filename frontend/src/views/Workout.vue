<template>
  <div class="py-6 animate-fade-in">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <div class="flex justify-between items-center">
        <h1 class="text-2xl font-semibold text-gray-900">Workout Session</h1>
        <div class="flex space-x-4">
          <button class="btn-secondary flex items-center space-x-2">
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6" />
            </svg>
            <span>Add Exercise</span>
          </button>
          <button class="btn-primary flex items-center space-x-2">
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
            </svg>
            <span>Schedule</span>
          </button>
        </div>
      </div>
      
      <div class="mt-6 grid grid-cols-1 lg:grid-cols-3 gap-6">
        <!-- Video Feed -->
        <div class="lg:col-span-2 space-y-6">
          <div class="card animate-slide-up" style="animation-delay: 0.1s">
            <div class="relative">
              <div class="aspect-w-16 aspect-h-9 bg-gray-100 rounded-lg overflow-hidden">
                <div class="absolute inset-0 flex items-center justify-center">
                  <div class="text-center">
                    <svg class="mx-auto h-12 w-12 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 10l4.553-2.276A1 1 0 0121 8.618v6.764a1 1 0 01-1.447.894L15 14M5 18h8a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v8a2 2 0 002 2z" />
                    </svg>
                    <p class="mt-2 text-sm text-gray-500">Camera feed will appear here</p>
                  </div>
                </div>
                <!-- Motion Analysis Overlay -->
                <div class="absolute inset-0 pointer-events-none">
                  <div class="absolute top-4 left-4 bg-black bg-opacity-75 text-white px-3 py-1 rounded-full text-sm">
                    AI Analysis Active
                  </div>
                </div>
              </div>
              
              <div class="mt-4 flex justify-center space-x-4">
                <button class="btn-primary flex items-center space-x-2">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14.752 11.168l-3.197-2.132A1 1 0 0010 9.87v4.263a1 1 0 001.555.832l3.197-2.132a1 1 0 000-1.664z" />
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                  </svg>
                  <span>Start Session</span>
                </button>
                <button class="btn-secondary flex items-center space-x-2">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                  </svg>
                  <span>End Session</span>
                </button>
              </div>
            </div>
          </div>

          <!-- Exercise List -->
          <div class="card animate-slide-up" style="animation-delay: 0.2s">
            <h3 class="text-lg font-medium text-gray-900 mb-4">Exercise List</h3>
            <div class="space-y-4">
              <div class="flex items-center justify-between p-4 bg-gray-50 rounded-lg">
                <div class="flex items-center space-x-4">
                  <div class="w-12 h-12 bg-primary-100 rounded-lg flex items-center justify-center">
                    <svg class="w-6 h-6 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
                    </svg>
                  </div>
                  <div>
                    <h4 class="text-sm font-medium text-gray-900">Squats</h4>
                    <p class="text-sm text-gray-500">3 sets × 12 reps</p>
                  </div>
                </div>
                <button class="text-primary-600 hover:text-primary-700">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
                  </svg>
                </button>
              </div>

              <div class="flex items-center justify-between p-4 bg-gray-50 rounded-lg">
                <div class="flex items-center space-x-4">
                  <div class="w-12 h-12 bg-primary-100 rounded-lg flex items-center justify-center">
                    <svg class="w-6 h-6 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
                    </svg>
                  </div>
                  <div>
                    <h4 class="text-sm font-medium text-gray-900">Push-ups</h4>
                    <p class="text-sm text-gray-500">3 sets × 15 reps</p>
                  </div>
                </div>
                <button class="text-primary-600 hover:text-primary-700">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
                  </svg>
                </button>
              </div>
            </div>
          </div>
        </div>

        <!-- Exercise Info -->
        <div class="space-y-6">
          <!-- Current Exercise -->
          <div class="card animate-slide-up" style="animation-delay: 0.3s">
            <h3 class="text-lg font-medium text-gray-900 mb-4">Current Exercise</h3>
            <div class="text-center py-8">
              <div class="w-16 h-16 bg-primary-100 rounded-full flex items-center justify-center mx-auto">
                <svg class="w-8 h-8 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
                </svg>
              </div>
              <h4 class="mt-4 text-lg font-medium text-gray-900">No Exercise Selected</h4>
              <p class="mt-2 text-sm text-gray-500">Select an exercise to begin</p>
            </div>
          </div>
          
          <!-- Form Feedback -->
          <div class="card animate-slide-up" style="animation-delay: 0.4s">
            <h3 class="text-lg font-medium text-gray-900 mb-4">Form Feedback</h3>
            <div class="space-y-4">
              <div class="p-4 bg-gray-50 rounded-lg">
                <div class="flex items-center space-x-3">
                  <div class="flex-shrink-0">
                    <svg class="w-5 h-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                    </svg>
                  </div>
                  <p class="text-sm text-gray-500">Waiting for exercise to begin...</p>
                </div>
              </div>
            </div>
          </div>

          <!-- Session Stats -->
          <div class="card animate-slide-up" style="animation-delay: 0.5s">
            <h3 class="text-lg font-medium text-gray-900 mb-4">Session Stats</h3>
            <div class="grid grid-cols-2 gap-4">
              <div class="stat-card">
                <p class="text-sm font-medium text-gray-500">Duration</p>
                <p class="mt-1 text-2xl font-semibold text-gray-900">00:00</p>
              </div>
              <div class="stat-card">
                <p class="text-sm font-medium text-gray-500">Calories</p>
                <p class="mt-1 text-2xl font-semibold text-gray-900">0</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// Component logic will be added here
</script> 