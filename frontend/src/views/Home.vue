<template>
  <div class="bg-white">
    <!-- Hero Section -->
    <div class="relative overflow-hidden bg-gradient-to-br from-primary-50 to-white">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12 md:py-16">
        <div class="grid grid-cols-1 lg:grid-cols-2 gap-8 items-center">
          <div class="text-center lg:text-left">
            <h1 class="text-3xl sm:text-4xl font-extrabold text-gray-900 tracking-tight">
              <span class="block">Transform your fitness with</span>
              <span class="block text-primary-600 mt-2">AI-powered training</span>
            </h1>
            <p class="mt-4 text-base text-gray-600 sm:text-lg max-w-xl mx-auto lg:mx-0">
              Get personalized workout guidance, real-time form correction, and detailed progress tracking. Your AI fitness coach is here to help you achieve your goals.
            </p>
            <div class="mt-6 flex flex-col sm:flex-row gap-4 justify-center lg:justify-start">
              <router-link
                to="/auth/register"
                class="inline-flex items-center justify-center px-6 py-3 border border-transparent text-base font-medium rounded-lg text-white bg-primary-600 hover:bg-primary-700 shadow-sm transition-colors duration-200"
              >
                Get Started
              </router-link>
              <router-link
                to="/auth/login"
                class="inline-flex items-center justify-center px-6 py-3 border border-primary-200 text-base font-medium rounded-lg text-primary-700 bg-white hover:bg-primary-50 shadow-sm transition-colors duration-200"
              >
                Sign In
              </router-link>
            </div>
          </div>
          <div class="relative h-64 sm:h-80 lg:h-96 rounded-2xl overflow-hidden shadow-xl">
            <img
              class="absolute inset-0 w-full h-full object-cover"
              src="https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"
              alt="Person working out"
            />
          </div>
        </div>
      </div>
    </div>

    <!-- Feature Section -->
    <div class="py-12 bg-white">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="text-center">
          <h2 class="text-base font-semibold text-primary-600 tracking-wide uppercase">Features</h2>
          <p class="mt-2 text-3xl font-extrabold text-gray-900 sm:text-4xl">
            A better way to train
          </p>
          <p class="mt-4 max-w-2xl mx-auto text-xl text-gray-500">
            Everything you need to achieve your fitness goals, powered by AI.
          </p>
        </div>

        <div class="mt-12">
          <div class="grid grid-cols-1 gap-8 sm:grid-cols-2 lg:grid-cols-4">
            <!-- Real-time Analysis -->
            <div class="relative bg-white p-6 rounded-xl shadow-sm hover:shadow-md transition-shadow duration-200">
              <div class="flex items-center justify-center h-12 w-12 rounded-lg bg-primary-100 text-primary-600 mb-4">
                <svg class="h-6 w-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 10l4.553-2.276A1 1 0 0121 8.618v6.764a1 1 0 01-1.447.894L15 14M5 18h8a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v8a2 2 0 002 2z" />
                </svg>
              </div>
              <h3 class="text-lg font-medium text-gray-900">Real-time Analysis</h3>
              <p class="mt-2 text-base text-gray-500">
                Get instant feedback on your form using advanced computer vision.
              </p>
            </div>

            <!-- Personalized Plans -->
            <div class="relative bg-white p-6 rounded-xl shadow-sm hover:shadow-md transition-shadow duration-200">
              <div class="flex items-center justify-center h-12 w-12 rounded-lg bg-primary-100 text-primary-600 mb-4">
                <svg class="h-6 w-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" />
                </svg>
              </div>
              <h3 class="text-lg font-medium text-gray-900">Personalized Plans</h3>
              <p class="mt-2 text-base text-gray-500">
                AI-generated workout plans that adapt to your progress and goals.
              </p>
            </div>

            <!-- Progress Tracking -->
            <div class="relative bg-white p-6 rounded-xl shadow-sm hover:shadow-md transition-shadow duration-200">
              <div class="flex items-center justify-center h-12 w-12 rounded-lg bg-primary-100 text-primary-600 mb-4">
                <svg class="h-6 w-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z" />
                </svg>
              </div>
              <h3 class="text-lg font-medium text-gray-900">Progress Tracking</h3>
              <p class="mt-2 text-base text-gray-500">
                Track your progress with detailed analytics and visual reports.
              </p>
            </div>

            <!-- Community Features -->
            <div class="relative bg-white p-6 rounded-xl shadow-sm hover:shadow-md transition-shadow duration-200">
              <div class="flex items-center justify-center h-12 w-12 rounded-lg bg-primary-100 text-primary-600 mb-4">
                <svg class="h-6 w-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
                </svg>
              </div>
              <h3 class="text-lg font-medium text-gray-900">Community</h3>
              <p class="mt-2 text-base text-gray-500">
                Join a community of fitness enthusiasts and participate in challenges.
              </p>
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