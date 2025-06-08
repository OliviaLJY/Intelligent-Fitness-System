<template>
  <div class="min-h-screen bg-gray-50 flex flex-col justify-center py-12 sm:px-6 lg:px-8">
    <div class="sm:mx-auto sm:w-full sm:max-w-md">
      <router-link to="/" class="flex justify-center">
        <span class="text-3xl font-bold text-primary-600">IFS</span>
      </router-link>
      <h2 class="mt-6 text-center text-3xl font-extrabold text-gray-900">
        {{ title }}
      </h2>
    </div>

    <div class="mt-8 sm:mx-auto sm:w-full sm:max-w-md">
      <div class="bg-white py-8 px-4 shadow sm:rounded-lg sm:px-10">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()

const title = computed(() => {
  switch (route.name) {
    case 'login':
      return 'Sign in to your account'
    case 'register':
      return 'Create your account'
    default:
      return 'Welcome'
  }
})
</script> 