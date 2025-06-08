<template>
  <div class="py-6">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <h1 class="text-2xl font-semibold text-gray-900">Settings</h1>
      
      <div class="mt-6 space-y-6">
        <!-- Notification Settings -->
        <div class="card">
          <h3 class="text-lg font-medium text-gray-900">Notification Settings</h3>
          <div class="mt-4 space-y-4">
            <div class="flex items-center justify-between">
              <div>
                <label for="email-notifications" class="text-sm font-medium text-gray-700">Email Notifications</label>
                <p class="text-sm text-gray-500">Receive email updates about your progress</p>
              </div>
              <button type="button" class="relative inline-flex h-6 w-11 flex-shrink-0 cursor-pointer rounded-full border-2 border-transparent bg-gray-200 transition-colors duration-200 ease-in-out focus:outline-none focus:ring-2 focus:ring-primary-500 focus:ring-offset-2">
                <span class="sr-only">Enable email notifications</span>
                <span class="translate-x-0 pointer-events-none relative inline-block h-5 w-5 transform rounded-full bg-white shadow ring-0 transition duration-200 ease-in-out">
                  <span class="opacity-100 duration-200 ease-in absolute inset-0 flex h-full w-full items-center justify-center transition-opacity">
                    <svg class="h-3 w-3 text-gray-400" fill="none" viewBox="0 0 12 12">
                      <path d="M4 8l2-2m0 0l2-2M6 6L4 4m2 2l2 2" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
                    </svg>
                  </span>
                </span>
              </button>
            </div>
            
            <div class="flex items-center justify-between">
              <div>
                <label for="workout-reminders" class="text-sm font-medium text-gray-700">Workout Reminders</label>
                <p class="text-sm text-gray-500">Get reminded about scheduled workouts</p>
              </div>
              <button type="button" class="relative inline-flex h-6 w-11 flex-shrink-0 cursor-pointer rounded-full border-2 border-transparent bg-gray-200 transition-colors duration-200 ease-in-out focus:outline-none focus:ring-2 focus:ring-primary-500 focus:ring-offset-2">
                <span class="sr-only">Enable workout reminders</span>
                <span class="translate-x-0 pointer-events-none relative inline-block h-5 w-5 transform rounded-full bg-white shadow ring-0 transition duration-200 ease-in-out">
                  <span class="opacity-100 duration-200 ease-in absolute inset-0 flex h-full w-full items-center justify-center transition-opacity">
                    <svg class="h-3 w-3 text-gray-400" fill="none" viewBox="0 0 12 12">
                      <path d="M4 8l2-2m0 0l2-2M6 6L4 4m2 2l2 2" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
                    </svg>
                  </span>
                </span>
              </button>
            </div>
          </div>
        </div>

        <!-- Privacy Settings -->
        <div class="card">
          <h3 class="text-lg font-medium text-gray-900">Privacy Settings</h3>
          <div class="mt-4 space-y-4">
            <div class="flex items-center justify-between">
              <div>
                <label for="data-collection" class="text-sm font-medium text-gray-700">Data Collection</label>
                <p class="text-sm text-gray-500">Allow collection of workout data for personalized recommendations</p>
              </div>
              <button type="button" class="relative inline-flex h-6 w-11 flex-shrink-0 cursor-pointer rounded-full border-2 border-transparent bg-gray-200 transition-colors duration-200 ease-in-out focus:outline-none focus:ring-2 focus:ring-primary-500 focus:ring-offset-2">
                <span class="sr-only">Enable data collection</span>
                <span class="translate-x-0 pointer-events-none relative inline-block h-5 w-5 transform rounded-full bg-white shadow ring-0 transition duration-200 ease-in-out">
                  <span class="opacity-100 duration-200 ease-in absolute inset-0 flex h-full w-full items-center justify-center transition-opacity">
                    <svg class="h-3 w-3 text-gray-400" fill="none" viewBox="0 0 12 12">
                      <path d="M4 8l2-2m0 0l2-2M6 6L4 4m2 2l2 2" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
                    </svg>
                  </span>
                </span>
              </button>
            </div>
          </div>
        </div>

        <!-- Account Settings -->
        <div class="card">
          <h3 class="text-lg font-medium text-gray-900">Account Settings</h3>
          <div class="mt-4 space-y-4">
            <button class="btn-secondary w-full">
              Change Password
            </button>
            <button class="btn-secondary w-full text-red-600 hover:bg-red-50">
              Delete Account
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// Component logic will be added here
</script> 