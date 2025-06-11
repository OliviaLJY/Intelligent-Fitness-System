<template>
  <nav class="bg-white shadow-sm sticky top-0 z-50">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <div class="flex justify-between h-16 items-center">
        <!-- Logo -->
        <router-link to="/" class="flex items-center space-x-2">
          <div class="w-8 h-8 bg-blue-600 rounded-full flex items-center justify-center">
            <svg class="w-5 h-5 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
            </svg>
          </div>
          <span class="text-xl font-bold text-blue-800">SmartFit</span>
        </router-link>
        <!-- Navigation Links -->
        <div class="hidden sm:flex space-x-2 ml-8">
          <router-link
            v-for="item in navigationItems"
            :key="item.name"
            :to="item.to"
            :class="[
              isActive(item.to)
                ? 'bg-blue-100 text-blue-800 font-bold'
                : 'text-blue-600 hover:bg-blue-50 hover:text-blue-900',
              'px-4 py-2 rounded-lg text-base transition-colors duration-200'
            ]"
          >
            <div class="flex items-center space-x-2">
              <component :is="item.icon" class="w-5 h-5 text-blue-500" />
              <span>{{ item.name }}</span>
            </div>
          </router-link>
        </div>
        <!-- Profile Menu -->
        <div class="flex items-center space-x-4">
          <template v-if="isAuthenticated">
            <div class="relative">
              <button
                @click="isProfileMenuOpen = !isProfileMenuOpen"
                class="flex items-center space-x-2 bg-white rounded-full focus:outline-none focus:ring-2 focus:ring-blue-500"
              >
                <span class="sr-only">Open user menu</span>
                <div class="h-8 w-8 rounded-full bg-blue-100 flex items-center justify-center">
                  <span class="text-sm font-bold text-blue-700">{{ userInitials }}</span>
                </div>
                <span class="text-sm font-bold text-blue-700">{{ userName }}</span>
              </button>
              <!-- Dropdown -->
              <div
                v-if="isProfileMenuOpen"
                class="origin-top-right absolute right-0 mt-2 w-48 rounded-md shadow-lg py-1 bg-white ring-1 ring-blue-200 focus:outline-none"
                role="menu"
              >
                <router-link
                  to="/profile"
                  class="block px-4 py-2 text-sm text-blue-700 hover:bg-blue-50"
                  role="menuitem"
                >
                  Your Profile
                </router-link>
                <router-link
                  to="/settings"
                  class="block px-4 py-2 text-sm text-blue-700 hover:bg-blue-50"
                  role="menuitem"
                >
                  Settings
                </router-link>
                <button
                  @click="logout"
                  class="block w-full text-left px-4 py-2 text-sm text-blue-700 hover:bg-blue-50"
                  role="menuitem"
                >
                  Sign out
                </button>
              </div>
            </div>
          </template>
          <template v-else>
            <router-link
              to="/login"
              class="btn-primary"
            >
              Sign in
            </router-link>
          </template>
        </div>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const router = useRouter()
const authStore = useAuthStore()
const isProfileMenuOpen = ref(false)

const navigationItems = [
  { name: 'Dashboard', to: '/dashboard', icon: 'HomeIcon' },
  { name: 'Workouts', to: '/workout', icon: 'PlayIcon' },
  { name: 'Exercises', to: '/exercises', icon: 'BookOpenIcon' },
  { name: 'Progress', to: '/progress', icon: 'ChartBarIcon' },
  { name: 'Community', to: '/community', icon: 'UsersIcon' }
]

const isAuthenticated = computed(() => authStore.isAuthenticated)
const userName = computed(() => authStore.user?.name || 'User')
const userInitials = computed(() => {
  const user = authStore.user
  if (!user || !user.name) return '?'
  return user.name
    .split(' ')
    .map(n => n[0])
    .join('')
    .toUpperCase()
})

const isActive = (to) => router.currentRoute.value.path.startsWith(to)

const logout = async () => {
  await authStore.logout()
  router.push('/auth/login')
}

// Icon Components
const HomeIcon = {
  render() {
    return h('svg', {
      class: 'w-5 h-5',
      fill: 'none',
      stroke: 'currentColor',
      viewBox: '0 0 24 24'
    }, [
      h('path', {
        'stroke-linecap': 'round',
        'stroke-linejoin': 'round',
        'stroke-width': '2',
        d: 'M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6'
      })
    ])
  }
}

const PlayIcon = {
  render() {
    return h('svg', {
      class: 'w-5 h-5',
      fill: 'none',
      stroke: 'currentColor',
      viewBox: '0 0 24 24'
    }, [
      h('path', {
        'stroke-linecap': 'round',
        'stroke-linejoin': 'round',
        'stroke-width': '2',
        d: 'M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z'
      })
    ])
  }
}

const BookOpenIcon = {
  render() {
    return h('svg', {
      class: 'w-5 h-5',
      fill: 'none',
      stroke: 'currentColor',
      viewBox: '0 0 24 24'
    }, [
      h('path', {
        'stroke-linecap': 'round',
        'stroke-linejoin': 'round',
        'stroke-width': '2',
        d: 'M12 6.253v13m0-13C10.832 5.473 9.246 5 7.5 5S4.168 5.473 3 6.253v13C4.168 18.473 5.754 18 7.5 18s3.332.473 4.5 1.253z'
      })
    ])
  }
}

const ChartBarIcon = {
  render() {
    return h('svg', {
      class: 'w-5 h-5',
      fill: 'none',
      stroke: 'currentColor',
      viewBox: '0 0 24 24'
    }, [
      h('path', {
        'stroke-linecap': 'round',
        'stroke-linejoin': 'round',
        'stroke-width': '2',
        d: 'M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z'
      })
    ])
  }
}

const UsersIcon = {
  render() {
    return h('svg', {
      class: 'w-5 h-5',
      fill: 'none',
      stroke: 'currentColor',
      viewBox: '0 0 24 24'
    }, [
      h('path', {
        'stroke-linecap': 'round',
        'stroke-linejoin': 'round',
        'stroke-width': '2',
        d: 'M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0h-5M7 20h-5v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.285.356-1.857m0 0C7.126 16.285 7 15.656 7 15v-2c0-2.485.114-4.926.344-7.357A3 3 0 0110.357 3m1.859-2a3 3 0 012.642 1.357M14.823 3c.138.033.275.075.412.126A3 3 0 0119.5 4.5c.986.042 1.852.225 2.554.545M12 10a3 3 0 100-6 3 3 0 000 6z'
      })
    ])
  }
}
</script> 