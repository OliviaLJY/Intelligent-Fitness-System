<template>
  <div class="space-y-6">
    <!-- Header -->
    <div class="flex justify-between items-center">
      <div>
        <h1 class="text-2xl font-bold text-gray-900">Profile</h1>
        <p class="mt-1 text-sm text-gray-500">Manage your personal information and fitness goals</p>
      </div>
      <button
        type="button"
        class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md text-white bg-primary-600 hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
      >
        Edit Profile
      </button>
    </div>

    <!-- Profile content -->
    <div class="grid grid-cols-1 gap-6 lg:grid-cols-3">
      <!-- Left column - Personal info -->
      <div class="lg:col-span-1 space-y-6">
        <!-- Profile card -->
        <div class="bg-white shadow-sm rounded-lg overflow-hidden">
          <div class="p-6">
            <div class="flex items-center space-x-4">
              <div class="flex-shrink-0">
                <div class="h-20 w-20 rounded-full bg-primary-100 flex items-center justify-center text-2xl font-semibold text-primary-600">
                  JD
                </div>
              </div>
              <div>
                <h2 class="text-xl font-semibold text-gray-900">John Doe</h2>
                <p class="text-sm text-gray-500">john.doe@example.com</p>
                <p class="mt-1 text-sm text-gray-500">Member since March 2024</p>
              </div>
            </div>
          </div>
          <div class="border-t border-gray-200 px-6 py-4">
            <dl class="grid grid-cols-1 gap-4">
              <div>
                <dt class="text-sm font-medium text-gray-500">Height</dt>
                <dd class="mt-1 text-sm text-gray-900">175 cm</dd>
              </div>
              <div>
                <dt class="text-sm font-medium text-gray-500">Weight</dt>
                <dd class="mt-1 text-sm text-gray-900">70 kg</dd>
              </div>
              <div>
                <dt class="text-sm font-medium text-gray-500">Age</dt>
                <dd class="mt-1 text-sm text-gray-900">28 years</dd>
              </div>
            </dl>
          </div>
        </div>

        <!-- Fitness goals -->
        <div class="bg-white shadow-sm rounded-lg overflow-hidden">
          <div class="p-6">
            <h3 class="text-lg font-medium text-gray-900">Fitness Goals</h3>
            <div class="mt-4 space-y-4">
              <div>
                <div class="flex items-center justify-between">
                  <span class="text-sm font-medium text-gray-900">Weekly Workouts</span>
                  <span class="text-sm text-gray-500">4/5</span>
                </div>
                <div class="mt-2 w-full bg-gray-200 rounded-full h-2">
                  <div class="bg-primary-600 h-2 rounded-full" style="width: 80%"></div>
                </div>
              </div>
              <div>
                <div class="flex items-center justify-between">
                  <span class="text-sm font-medium text-gray-900">Daily Steps</span>
                  <span class="text-sm text-gray-500">8,500/10,000</span>
                </div>
                <div class="mt-2 w-full bg-gray-200 rounded-full h-2">
                  <div class="bg-primary-600 h-2 rounded-full" style="width: 85%"></div>
                </div>
              </div>
              <div>
                <div class="flex items-center justify-between">
                  <span class="text-sm font-medium text-gray-900">Calories Burned</span>
                  <span class="text-sm text-gray-500">2,500/3,000</span>
                </div>
                <div class="mt-2 w-full bg-gray-200 rounded-full h-2">
                  <div class="bg-primary-600 h-2 rounded-full" style="width: 83%"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Right column - Achievements and stats -->
      <div class="lg:col-span-2 space-y-6">
        <!-- Achievements -->
        <div class="bg-white shadow-sm rounded-lg overflow-hidden">
          <div class="p-6">
            <h3 class="text-lg font-medium text-gray-900">Achievements</h3>
            <div class="mt-4 grid grid-cols-2 gap-4 sm:grid-cols-3">
              <!-- Achievement badge -->
              <div class="text-center">
                <div class="mx-auto h-16 w-16 rounded-full bg-primary-100 flex items-center justify-center">
                  <svg class="h-8 w-8 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 3v4M3 5h4M6 17v4m-2-2h4m5-16l2.286 6.857L21 12l-5.714 2.143L13 21l-2.286-6.857L5 12l5.714-2.143L13 3z" />
                  </svg>
                </div>
                <h4 class="mt-2 text-sm font-medium text-gray-900">Early Bird</h4>
                <p class="mt-1 text-xs text-gray-500">Complete 5 morning workouts</p>
              </div>

              <!-- More achievement badges... -->
            </div>
          </div>
        </div>

        <!-- Recent activity -->
        <div class="bg-white shadow-sm rounded-lg overflow-hidden">
          <div class="p-6">
            <h3 class="text-lg font-medium text-gray-900">Recent Activity</h3>
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
                          <p class="text-sm text-gray-500">Completed <span class="font-medium text-gray-900">Full Body Workout</span></p>
                        </div>
                        <div class="text-right text-sm whitespace-nowrap text-gray-500">
                          <time datetime="2024-03-15">2 hours ago</time>
                        </div>
                      </div>
                    </div>
                  </div>
                </li>

                <!-- More activity items... -->
              </ul>
            </div>
          </div>
        </div>

        <!-- Fitness stats -->
        <div class="bg-white shadow-sm rounded-lg overflow-hidden">
          <div class="p-6">
            <h3 class="text-lg font-medium text-gray-900">Fitness Statistics</h3>
            <div class="mt-4 grid grid-cols-1 gap-5 sm:grid-cols-2">
              <div class="bg-gray-50 px-4 py-5 rounded-lg">
                <dt class="text-sm font-medium text-gray-500 truncate">Total Workouts</dt>
                <dd class="mt-1 text-3xl font-semibold text-gray-900">24</dd>
              </div>
              <div class="bg-gray-50 px-4 py-5 rounded-lg">
                <dt class="text-sm font-medium text-gray-500 truncate">Total Duration</dt>
                <dd class="mt-1 text-3xl font-semibold text-gray-900">18h</dd>
              </div>
              <div class="bg-gray-50 px-4 py-5 rounded-lg">
                <dt class="text-sm font-medium text-gray-500 truncate">Calories Burned</dt>
                <dd class="mt-1 text-3xl font-semibold text-gray-900">12.5k</dd>
              </div>
              <div class="bg-gray-50 px-4 py-5 rounded-lg">
                <dt class="text-sm font-medium text-gray-500 truncate">Current Streak</dt>
                <dd class="mt-1 text-3xl font-semibold text-gray-900">5 days</dd>
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