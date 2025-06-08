<template>
  <div class="space-y-6">
    <!-- Header -->
    <div class="flex justify-between items-center">
      <div>
        <h1 class="text-2xl font-bold text-gray-900">Community</h1>
        <p class="mt-1 text-sm text-gray-500">Connect with fellow fitness enthusiasts</p>
      </div>
      <button
        type="button"
        class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md text-white bg-primary-600 hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
      >
        <svg class="-ml-1 mr-2 h-5 w-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4" />
        </svg>
        New Post
      </button>
    </div>

    <!-- Main content -->
    <div class="grid grid-cols-1 gap-6 lg:grid-cols-3">
      <!-- Left sidebar -->
      <div class="lg:col-span-1 space-y-6">
        <!-- User stats -->
        <div class="bg-white shadow-sm rounded-lg overflow-hidden">
          <div class="p-6">
            <div class="flex items-center">
              <img
                class="h-12 w-12 rounded-full"
                src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                alt="User avatar"
              />
              <div class="ml-4">
                <h2 class="text-lg font-medium text-gray-900">John Doe</h2>
                <p class="text-sm text-gray-500">Fitness Level: Intermediate</p>
              </div>
            </div>
            <div class="mt-6 grid grid-cols-3 gap-4 text-center">
              <div>
                <p class="text-sm font-medium text-gray-500">Posts</p>
                <p class="mt-1 text-xl font-semibold text-gray-900">12</p>
              </div>
              <div>
                <p class="text-sm font-medium text-gray-500">Followers</p>
                <p class="mt-1 text-xl font-semibold text-gray-900">245</p>
              </div>
              <div>
                <p class="text-sm font-medium text-gray-500">Following</p>
                <p class="mt-1 text-xl font-semibold text-gray-900">189</p>
              </div>
            </div>
          </div>
        </div>

        <!-- Trending topics -->
        <div class="bg-white shadow-sm rounded-lg overflow-hidden">
          <div class="p-6">
            <h3 class="text-lg font-medium text-gray-900">Trending Topics</h3>
            <div class="mt-4 space-y-4">
              <a href="#" class="block hover:bg-gray-50 rounded-lg p-3">
                <div class="flex items-center">
                  <span class="flex-shrink-0 h-8 w-8 rounded-full bg-primary-100 flex items-center justify-center">
                    <svg class="h-5 w-5 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z" />
                    </svg>
                  </span>
                  <div class="ml-3">
                    <p class="text-sm font-medium text-gray-900">#WorkoutMotivation</p>
                    <p class="text-sm text-gray-500">1.2k posts</p>
                  </div>
                </div>
              </a>
              <a href="#" class="block hover:bg-gray-50 rounded-lg p-3">
                <div class="flex items-center">
                  <span class="flex-shrink-0 h-8 w-8 rounded-full bg-green-100 flex items-center justify-center">
                    <svg class="h-5 w-5 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                    </svg>
                  </span>
                  <div class="ml-3">
                    <p class="text-sm font-medium text-gray-900">#FitnessGoals</p>
                    <p class="text-sm text-gray-500">856 posts</p>
                  </div>
                </div>
              </a>
              <a href="#" class="block hover:bg-gray-50 rounded-lg p-3">
                <div class="flex items-center">
                  <span class="flex-shrink-0 h-8 w-8 rounded-full bg-yellow-100 flex items-center justify-center">
                    <svg class="h-5 w-5 text-yellow-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                    </svg>
                  </span>
                  <div class="ml-3">
                    <p class="text-sm font-medium text-gray-900">#MorningWorkout</p>
                    <p class="text-sm text-gray-500">543 posts</p>
                  </div>
                </div>
              </a>
            </div>
          </div>
        </div>
      </div>

      <!-- Main feed -->
      <div class="lg:col-span-2 space-y-6">
        <!-- Post -->
        <div class="bg-white shadow-sm rounded-lg overflow-hidden">
          <div class="p-6">
            <div class="flex items-center">
              <img
                class="h-10 w-10 rounded-full"
                src="https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                alt="User avatar"
              />
              <div class="ml-3">
                <p class="text-sm font-medium text-gray-900">Sarah Wilson</p>
                <p class="text-sm text-gray-500">2 hours ago</p>
              </div>
            </div>
            <div class="mt-4">
              <p class="text-sm text-gray-900">
                Just completed my first 10K run! 🏃‍♀️ The training really paid off. #RunningGoals #FitnessJourney
              </p>
              <div class="mt-4">
                <img
                  class="rounded-lg"
                  src="https://images.unsplash.com/photo-1571008887538-b36bb32f4571?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80"
                  alt="Running achievement"
                />
              </div>
            </div>
            <div class="mt-6 flex items-center justify-between">
              <div class="flex space-x-4">
                <button class="flex items-center text-sm text-gray-500 hover:text-gray-700">
                  <svg class="h-5 w-5 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
                  </svg>
                  245
                </button>
                <button class="flex items-center text-sm text-gray-500 hover:text-gray-700">
                  <svg class="h-5 w-5 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 12h.01M12 12h.01M16 12h.01M21 12c0 4.418-4.03 8-9 8a9.863 9.863 0 01-4.255-.949L3 20l1.395-3.72C3.512 15.042 3 13.574 3 12c0-4.418 4.03-8 9-8s9 3.582 9 8z" />
                  </svg>
                  48
                </button>
              </div>
              <button class="text-sm text-gray-500 hover:text-gray-700">
                Share
              </button>
            </div>
          </div>
        </div>

        <!-- Another post -->
        <div class="bg-white shadow-sm rounded-lg overflow-hidden">
          <div class="p-6">
            <div class="flex items-center">
              <img
                class="h-10 w-10 rounded-full"
                src="https://images.unsplash.com/photo-1500648767791-00dcc994a43e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                alt="User avatar"
              />
              <div class="ml-3">
                <p class="text-sm font-medium text-gray-900">Mike Johnson</p>
                <p class="text-sm text-gray-500">5 hours ago</p>
              </div>
            </div>
            <div class="mt-4">
              <p class="text-sm text-gray-900">
                Looking for workout buddies in the downtown area! Planning to start a morning workout group. Anyone interested? #WorkoutBuddies #MorningWorkout
              </p>
            </div>
            <div class="mt-6 flex items-center justify-between">
              <div class="flex space-x-4">
                <button class="flex items-center text-sm text-gray-500 hover:text-gray-700">
                  <svg class="h-5 w-5 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
                  </svg>
                  89
                </button>
                <button class="flex items-center text-sm text-gray-500 hover:text-gray-700">
                  <svg class="h-5 w-5 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 12h.01M12 12h.01M16 12h.01M21 12c0 4.418-4.03 8-9 8a9.863 9.863 0 01-4.255-.949L3 20l1.395-3.72C3.512 15.042 3 13.574 3 12c0-4.418 4.03-8 9-8s9 3.582 9 8z" />
                  </svg>
                  23
                </button>
              </div>
              <button class="text-sm text-gray-500 hover:text-gray-700">
                Share
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// Component logic will be added here
// This will include post management, interactions, and real-time updates
</script> 