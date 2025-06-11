<template>
  <div class="bg-white">
    <!-- Hero section -->
    <div class="relative bg-gradient-to-r from-blue-600 to-blue-700">
      <div class="max-w-7xl mx-auto py-16 px-4 sm:py-24 sm:px-6 lg:px-8">
        <div class="text-center">
          <h1 class="text-4xl font-extrabold tracking-tight text-white sm:text-5xl lg:text-6xl">
            Intelligent Fitness System
          </h1>
          <p class="mt-6 max-w-2xl mx-auto text-xl text-blue-100">
            AI-powered motion analysis and personalized workout recommendations to help you achieve your fitness goals.
          </p>
          <div class="mt-10 flex justify-center space-x-4">
            <router-link
              to="/auth/register"
              class="inline-flex items-center px-6 py-3 border border-transparent text-base font-bold rounded-lg text-blue-700 bg-white hover:bg-blue-50"
            >
              Get Started
            </router-link>
            <router-link
              to="/auth/login"
              class="inline-flex items-center px-6 py-3 border border-transparent text-base font-bold rounded-lg text-white bg-blue-800 hover:bg-blue-900"
            >
              Sign In
            </router-link>
          </div>
        </div>
      </div>
    </div>

    <!-- Feature section -->
    <div class="py-16 bg-blue-50">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="text-center">
          <h2 class="text-3xl font-extrabold text-blue-900">
            Smart Features for Better Results
          </h2>
          <p class="mt-4 max-w-2xl mx-auto text-xl text-blue-600">
            Everything you need to track, improve, and achieve your fitness goals.
          </p>
        </div>

        <div class="mt-12 grid grid-cols-1 gap-8 sm:grid-cols-2 lg:grid-cols-4">
          <!-- Real-time Analysis -->
          <div class="bg-white border border-blue-100 rounded-xl shadow-sm">
            <div class="p-6">
              <div class="flex items-center">
                <div class="flex-shrink-0 bg-blue-100 rounded-md p-3">
                  <svg class="h-6 w-6 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 10l4.553-2.276A1 1 0 0121 8.618v6.764a1 1 0 01-1.447.894L15 14M5 18h8a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v8a2 2 0 002 2z" />
                  </svg>
                </div>
                <div class="ml-4">
                  <h3 class="text-lg font-bold text-blue-900">Real-time Analysis</h3>
                  <p class="mt-2 text-sm text-blue-600">
                    Get instant feedback on your form and technique using AI-powered motion analysis.
                  </p>
                </div>
              </div>
            </div>
          </div>

          <!-- Personalized Plans -->
          <div class="bg-white border border-blue-100 rounded-xl shadow-sm">
            <div class="p-6">
              <div class="flex items-center">
                <div class="flex-shrink-0 bg-blue-100 rounded-md p-3">
                  <svg class="h-6 w-6 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" />
                  </svg>
                </div>
                <div class="ml-4">
                  <h3 class="text-lg font-bold text-blue-900">Personalized Plans</h3>
                  <p class="mt-2 text-sm text-blue-600">
                    Custom workout plans that adapt to your progress and goals.
                  </p>
                </div>
              </div>
            </div>
          </div>

          <!-- Progress Tracking -->
          <div class="bg-white border border-blue-100 rounded-xl shadow-sm">
            <div class="p-6">
              <div class="flex items-center">
                <div class="flex-shrink-0 bg-blue-100 rounded-md p-3">
                  <svg class="h-6 w-6 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z" />
                  </svg>
                </div>
                <div class="ml-4">
                  <h3 class="text-lg font-bold text-blue-900">Progress Tracking</h3>
                  <p class="mt-2 text-sm text-blue-600">
                    Detailed analytics and insights to monitor your fitness journey.
                  </p>
                </div>
              </div>
            </div>
          </div>

          <!-- Community -->
          <div class="bg-white border border-blue-100 rounded-xl shadow-sm">
            <div class="p-6">
              <div class="flex items-center">
                <div class="flex-shrink-0 bg-blue-100 rounded-md p-3">
                  <svg class="h-6 w-6 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
                  </svg>
                </div>
                <div class="ml-4">
                  <h3 class="text-lg font-bold text-blue-900">Community</h3>
                  <p class="mt-2 text-sm text-blue-600">
                    Connect with others, share achievements, and stay motivated.
                  </p>
                </div>
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