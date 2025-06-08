<template>
  <div class="space-y-6">
    <!-- Header -->
    <div class="flex justify-between items-center">
      <div>
        <h1 class="text-2xl font-bold text-gray-900">Settings</h1>
        <p class="mt-1 text-sm text-gray-500">Manage your account settings and preferences</p>
      </div>
    </div>

    <!-- Settings content -->
    <div class="grid grid-cols-1 gap-6 lg:grid-cols-3">
      <!-- Left column - Navigation -->
      <div class="lg:col-span-1">
        <nav class="space-y-1">
          <button
            v-for="item in navigationItems"
            :key="item.name"
            @click="activeSection = item.id"
            :class="[
              activeSection === item.id
                ? 'bg-gray-100 text-gray-900'
                : 'text-gray-600 hover:bg-gray-50 hover:text-gray-900',
              'group flex items-center px-3 py-2 text-sm font-medium rounded-md w-full'
            ]"
          >
            <component
              :is="item.icon"
              :class="[
                activeSection === item.id ? 'text-gray-500' : 'text-gray-400 group-hover:text-gray-500',
                'flex-shrink-0 -ml-1 mr-3 h-6 w-6'
              ]"
            />
            {{ item.name }}
          </button>
        </nav>
      </div>

      <!-- Right column - Settings panels -->
      <div class="lg:col-span-2">
        <!-- Account settings -->
        <div v-if="activeSection === 'account'" class="bg-white shadow-sm rounded-lg">
          <div class="p-6">
            <h2 class="text-lg font-medium text-gray-900">Account Settings</h2>
            <div class="mt-6 space-y-6">
              <!-- Email -->
              <div>
                <label for="email" class="block text-sm font-medium text-gray-700">Email address</label>
                <div class="mt-1 flex rounded-md shadow-sm">
                  <input
                    type="email"
                    name="email"
                    id="email"
                    class="block w-full rounded-md border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500 sm:text-sm"
                    placeholder="you@example.com"
                  />
                </div>
              </div>

              <!-- Password -->
              <div>
                <label for="current-password" class="block text-sm font-medium text-gray-700">Current password</label>
                <div class="mt-1">
                  <input
                    type="password"
                    name="current-password"
                    id="current-password"
                    class="block w-full rounded-md border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500 sm:text-sm"
                  />
                </div>
              </div>

              <div>
                <label for="new-password" class="block text-sm font-medium text-gray-700">New password</label>
                <div class="mt-1">
                  <input
                    type="password"
                    name="new-password"
                    id="new-password"
                    class="block w-full rounded-md border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500 sm:text-sm"
                  />
                </div>
              </div>

              <!-- Delete account -->
              <div class="pt-6">
                <div class="flex items-start">
                  <div class="flex items-center h-5">
                    <input
                      id="delete-account"
                      name="delete-account"
                      type="checkbox"
                      class="h-4 w-4 rounded border-gray-300 text-primary-600 focus:ring-primary-500"
                    />
                  </div>
                  <div class="ml-3 text-sm">
                    <label for="delete-account" class="font-medium text-gray-700">Delete account</label>
                    <p class="text-gray-500">Once you delete your account, there is no going back. Please be certain.</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Preferences -->
        <div v-if="activeSection === 'preferences'" class="bg-white shadow-sm rounded-lg">
          <div class="p-6">
            <h2 class="text-lg font-medium text-gray-900">Preferences</h2>
            <div class="mt-6 space-y-6">
              <!-- Measurement units -->
              <div>
                <label class="block text-sm font-medium text-gray-700">Measurement Units</label>
                <div class="mt-2 space-y-4">
                  <div class="flex items-center">
                    <input
                      id="metric"
                      name="units"
                      type="radio"
                      class="h-4 w-4 border-gray-300 text-primary-600 focus:ring-primary-500"
                    />
                    <label for="metric" class="ml-3 block text-sm font-medium text-gray-700">Metric (kg, cm)</label>
                  </div>
                  <div class="flex items-center">
                    <input
                      id="imperial"
                      name="units"
                      type="radio"
                      class="h-4 w-4 border-gray-300 text-primary-600 focus:ring-primary-500"
                    />
                    <label for="imperial" class="ml-3 block text-sm font-medium text-gray-700">Imperial (lb, in)</label>
                  </div>
                </div>
              </div>

              <!-- Notifications -->
              <div>
                <label class="block text-sm font-medium text-gray-700">Notifications</label>
                <div class="mt-2 space-y-4">
                  <div class="flex items-start">
                    <div class="flex items-center h-5">
                      <input
                        id="email-notifications"
                        name="email-notifications"
                        type="checkbox"
                        class="h-4 w-4 rounded border-gray-300 text-primary-600 focus:ring-primary-500"
                      />
                    </div>
                    <div class="ml-3 text-sm">
                      <label for="email-notifications" class="font-medium text-gray-700">Email notifications</label>
                      <p class="text-gray-500">Get notified about your workout progress and achievements.</p>
                    </div>
                  </div>
                  <div class="flex items-start">
                    <div class="flex items-center h-5">
                      <input
                        id="push-notifications"
                        name="push-notifications"
                        type="checkbox"
                        class="h-4 w-4 rounded border-gray-300 text-primary-600 focus:ring-primary-500"
                      />
                    </div>
                    <div class="ml-3 text-sm">
                      <label for="push-notifications" class="font-medium text-gray-700">Push notifications</label>
                      <p class="text-gray-500">Receive push notifications on your device.</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Privacy -->
        <div v-if="activeSection === 'privacy'" class="bg-white shadow-sm rounded-lg">
          <div class="p-6">
            <h2 class="text-lg font-medium text-gray-900">Privacy Settings</h2>
            <div class="mt-6 space-y-6">
              <!-- Profile visibility -->
              <div>
                <label class="block text-sm font-medium text-gray-700">Profile Visibility</label>
                <div class="mt-2 space-y-4">
                  <div class="flex items-center">
                    <input
                      id="public"
                      name="visibility"
                      type="radio"
                      class="h-4 w-4 border-gray-300 text-primary-600 focus:ring-primary-500"
                    />
                    <label for="public" class="ml-3 block text-sm font-medium text-gray-700">Public</label>
                  </div>
                  <div class="flex items-center">
                    <input
                      id="private"
                      name="visibility"
                      type="radio"
                      class="h-4 w-4 border-gray-300 text-primary-600 focus:ring-primary-500"
                    />
                    <label for="private" class="ml-3 block text-sm font-medium text-gray-700">Private</label>
                  </div>
                </div>
              </div>

              <!-- Data sharing -->
              <div>
                <label class="block text-sm font-medium text-gray-700">Data Sharing</label>
                <div class="mt-2 space-y-4">
                  <div class="flex items-start">
                    <div class="flex items-center h-5">
                      <input
                        id="share-progress"
                        name="share-progress"
                        type="checkbox"
                        class="h-4 w-4 rounded border-gray-300 text-primary-600 focus:ring-primary-500"
                      />
                    </div>
                    <div class="ml-3 text-sm">
                      <label for="share-progress" class="font-medium text-gray-700">Share progress</label>
                      <p class="text-gray-500">Allow others to see your workout progress and achievements.</p>
                    </div>
                  </div>
                  <div class="flex items-start">
                    <div class="flex items-center h-5">
                      <input
                        id="share-stats"
                        name="share-stats"
                        type="checkbox"
                        class="h-4 w-4 rounded border-gray-300 text-primary-600 focus:ring-primary-500"
                      />
                    </div>
                    <div class="ml-3 text-sm">
                      <label for="share-stats" class="font-medium text-gray-700">Share statistics</label>
                      <p class="text-gray-500">Share your workout statistics with the community.</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Save changes button -->
    <div class="flex justify-end">
      <button
        type="button"
        class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md text-white bg-primary-600 hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
      >
        Save changes
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const activeSection = ref('account')

const navigationItems = [
  {
    id: 'account',
    name: 'Account',
    icon: {
      template: `
        <svg class="h-6 w-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
        </svg>
      `
    }
  },
  {
    id: 'preferences',
    name: 'Preferences',
    icon: {
      template: `
        <svg class="h-6 w-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10.325 4.317c.426-1.756 2.924-1.756 3.35 0a1.724 1.724 0 002.573 1.066c1.543-.94 3.31.826 2.37 2.37a1.724 1.724 0 001.065 2.572c1.756.426 1.756 2.924 0 3.35a1.724 1.724 0 00-1.066 2.573c.94 1.543-.826 3.31-2.37 2.37a1.724 1.724 0 00-2.572 1.065c-.426 1.756-2.924 1.756-3.35 0a1.724 1.724 0 00-2.573-1.066c-1.543.94-3.31-.826-2.37-2.37a1.724 1.724 0 00-1.065-2.572c-1.756-.426-1.756-2.924 0-3.35a1.724 1.724 0 001.066-2.573c-.94-1.543.826-3.31 2.37-2.37.996.608 2.296.07 2.572-1.065z" />
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
        </svg>
      `
    }
  },
  {
    id: 'privacy',
    name: 'Privacy',
    icon: {
      template: `
        <svg class="h-6 w-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z" />
        </svg>
      `
    }
  }
]
</script> 