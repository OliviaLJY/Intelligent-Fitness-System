<template>
  <div class="min-h-screen bg-gray-50">
    <!-- Navigation -->
    <nav class="bg-white shadow-sm">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex justify-between h-16">
          <!-- Logo and main nav -->
          <div class="flex">
            <router-link to="/" class="flex items-center">
              <span class="text-xl font-bold text-primary-600">IFS</span>
            </router-link>
            <div class="hidden sm:ml-6 sm:flex sm:space-x-8">
              <router-link
                v-for="item in mainNavItems"
                :key="item.name"
                :to="item.to"
                class="inline-flex items-center px-1 pt-1 text-sm font-medium"
                :class="[
                  $route.path.startsWith(item.to)
                    ? 'border-primary-500 text-gray-900'
                    : 'border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700',
                  'border-b-2'
                ]"
              >
                {{ item.name }}
              </router-link>
            </div>
          </div>

          <!-- Right side nav -->
          <div class="flex items-center">
            <div class="flex-shrink-0">
              <router-link
                to="/workout"
                class="relative inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md text-white bg-primary-600 hover:bg-primary-700"
              >
                Start Workout
              </router-link>
            </div>
            <div class="hidden sm:ml-6 sm:flex sm:items-center">
              <!-- Profile dropdown -->
              <div class="ml-3 relative">
                <button
                  @click="isProfileMenuOpen = !isProfileMenuOpen"
                  class="flex text-sm rounded-full focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
                >
                  <span class="sr-only">Open user menu</span>
                  <div class="h-8 w-8 rounded-full bg-primary-600 flex items-center justify-center text-white">
                    {{ userInitials }}
                  </div>
                </button>

                <!-- Dropdown menu -->
                <div
                  v-if="isProfileMenuOpen"
                  class="origin-top-right absolute right-0 mt-2 w-48 rounded-md shadow-lg py-1 bg-white ring-1 ring-black ring-opacity-5 focus:outline-none"
                >
                  <router-link
                    to="/profile"
                    class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  >
                    Your Profile
                  </router-link>
                  <router-link
                    to="/settings"
                    class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  >
                    Settings
                  </router-link>
                  <button
                    @click="logout"
                    class="block w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                  >
                    Sign out
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </nav>

    <!-- Page content -->
    <main class="py-6">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <router-view></router-view>
      </div>
    </main>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const router = useRouter()
const authStore = useAuthStore()
const isProfileMenuOpen = ref(false)

const mainNavItems = [
  { name: 'Dashboard', to: '/dashboard' },
  { name: 'Workouts', to: '/workout' },
  { name: 'Exercises', to: '/exercises' },
  { name: 'Progress', to: '/progress' },
  { name: 'Community', to: '/community' }
]

const userInitials = computed(() => {
  const user = authStore.user
  if (!user || !user.name) return '?'
  return user.name
    .split(' ')
    .map(n => n[0])
    .join('')
    .toUpperCase()
})

const logout = async () => {
  await authStore.logout()
  router.push('/auth/login')
}
</script> 