<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'
// import Navbar from '@/components/layout/Navbar.vue' (no longer needed)

const route = useRoute()

// Don't show footer on auth pages
const showFooter = computed(() => {
  return !route.path.startsWith('/auth')
})
</script>

<template>
  <div class="min-h-screen bg-gray-50">
    <!-- Navigation (removed to prevent duplication) -->
    
    <!-- Main Content -->
    <main class="py-6">
      <router-view v-slot="{ Component }">
        <transition
          name="fade"
          mode="out-in"
        >
          <component :is="Component" />
        </transition>
      </router-view>
    </main>

    <!-- Footer -->
    <footer v-if="showFooter" class="bg-white border-t border-gray-200">
      <div class="max-w-7xl mx-auto py-6 px-4 sm:px-6 lg:px-8">
        <p class="text-center text-sm text-gray-500">
          © {{ new Date().getFullYear() }} SmartFit. All rights reserved.
        </p>
      </div>
    </footer>
  </div>
</template>

<style>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.15s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
