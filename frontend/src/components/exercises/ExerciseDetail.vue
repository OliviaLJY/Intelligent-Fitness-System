<template>
  <div class="bg-white shadow rounded-lg overflow-hidden">
    <!-- Exercise Header -->
    <div class="p-6 border-b border-gray-200">
      <div class="flex items-center justify-between">
        <div class="flex items-center space-x-4">
          <div class="h-12 w-12 rounded-full bg-primary-100 flex items-center justify-center">
            <svg class="h-8 w-8 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
            </svg>
          </div>
          <div>
            <h2 class="text-2xl font-bold text-gray-900">{{ exercise.name }}</h2>
            <p class="text-sm text-gray-500">{{ exercise.category }} • {{ exercise.muscleGroup }}</p>
          </div>
        </div>
        <div class="flex space-x-2">
          <button @click="addToWorkout" class="btn-primary">
            Add to Workout
          </button>
          <button @click="toggleFavorite" class="btn-secondary">
            <svg :class="['h-5 w-5', isFavorite ? 'text-red-500' : 'text-gray-400']" fill="currentColor" viewBox="0 0 24 24">
              <path d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z" />
            </svg>
          </button>
        </div>
      </div>
    </div>

    <!-- Exercise Content -->
    <div class="grid grid-cols-1 lg:grid-cols-2 gap-6 p-6">
      <!-- Video Demonstration -->
      <div class="space-y-4">
        <h3 class="text-lg font-medium text-gray-900">Form Demonstration</h3>
        <div class="aspect-w-16 aspect-h-9 bg-gray-100 rounded-lg overflow-hidden">
          <video
            v-if="exercise.videoUrl"
            :src="exercise.videoUrl"
            controls
            class="w-full h-full object-cover"
          ></video>
          <div v-else class="flex items-center justify-center h-full">
            <p class="text-gray-500">Video demonstration coming soon</p>
          </div>
        </div>
        <div class="grid grid-cols-2 gap-4">
          <div class="bg-gray-50 p-4 rounded-lg">
            <h4 class="text-sm font-medium text-gray-900">Difficulty</h4>
            <p class="mt-1 text-sm text-gray-500">{{ exercise.difficulty }}</p>
          </div>
          <div class="bg-gray-50 p-4 rounded-lg">
            <h4 class="text-sm font-medium text-gray-900">Equipment</h4>
            <p class="mt-1 text-sm text-gray-500">{{ exercise.equipment || 'None' }}</p>
          </div>
        </div>
      </div>

      <!-- Instructions and Tips -->
      <div class="space-y-6">
        <div>
          <h3 class="text-lg font-medium text-gray-900">Instructions</h3>
          <ol class="mt-4 space-y-4">
            <li v-for="(step, index) in exercise.instructions" :key="index" class="flex">
              <span class="flex-shrink-0 h-6 w-6 rounded-full bg-primary-100 flex items-center justify-center text-primary-600 font-medium text-sm">
                {{ index + 1 }}
              </span>
              <p class="ml-3 text-sm text-gray-500">{{ step }}</p>
            </li>
          </ol>
        </div>

        <div>
          <h3 class="text-lg font-medium text-gray-900">Form Tips</h3>
          <ul class="mt-4 space-y-3">
            <li v-for="(tip, index) in exercise.formTips" :key="index" class="flex">
              <svg class="h-5 w-5 text-green-500 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
              </svg>
              <p class="ml-3 text-sm text-gray-500">{{ tip }}</p>
            </li>
          </ul>
        </div>

        <div>
          <h3 class="text-lg font-medium text-gray-900">Common Mistakes</h3>
          <ul class="mt-4 space-y-3">
            <li v-for="(mistake, index) in exercise.commonMistakes" :key="index" class="flex">
              <svg class="h-5 w-5 text-red-500 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
              </svg>
              <p class="ml-3 text-sm text-gray-500">{{ mistake }}</p>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const props = defineProps({
  exercise: {
    type: Object,
    required: true,
    default: () => ({
      name: '',
      category: '',
      muscleGroup: '',
      difficulty: '',
      equipment: '',
      videoUrl: '',
      instructions: [],
      formTips: [],
      commonMistakes: []
    })
  }
})

const emit = defineEmits(['add-to-workout', 'toggle-favorite'])

const isFavorite = ref(false)

const addToWorkout = () => {
  emit('add-to-workout', props.exercise)
}

const toggleFavorite = () => {
  isFavorite.value = !isFavorite.value
  emit('toggle-favorite', { exercise: props.exercise, isFavorite: isFavorite.value })
}
</script>

<style scoped>
.btn-primary {
  @apply inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-primary-600 hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500;
}

.btn-secondary {
  @apply inline-flex items-center p-2 border border-gray-300 shadow-sm text-sm font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500;
}
</style> 