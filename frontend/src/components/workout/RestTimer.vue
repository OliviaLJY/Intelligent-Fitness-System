<template>
  <div class="bg-white shadow rounded-lg overflow-hidden">
    <div class="p-6">
      <div class="flex items-center justify-between">
        <div>
          <h3 class="text-lg font-medium text-gray-900">Rest Timer</h3>
          <p class="mt-1 text-sm text-gray-500">Time remaining until next set</p>
        </div>
        <div class="flex items-center space-x-2">
          <button
            v-if="!isRunning"
            @click="startTimer"
            class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-primary-600 hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
          >
            <svg class="-ml-1 mr-2 h-5 w-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14.752 11.168l-3.197-2.132A1 1 0 0010 9.87v4.263a1 1 0 001.555.832l3.197-2.132a1 1 0 000-1.664z" />
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
            </svg>
            Start
          </button>
          <button
            v-else
            @click="pauseTimer"
            class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-yellow-600 hover:bg-yellow-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-yellow-500"
          >
            <svg class="-ml-1 mr-2 h-5 w-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 9v6m4-6v6m7-3a9 9 0 11-18 0 9 9 0 0118 0z" />
            </svg>
            Pause
          </button>
          <button
            @click="resetTimer"
            class="inline-flex items-center px-4 py-2 border border-gray-300 shadow-sm text-sm font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
          >
            <svg class="-ml-1 mr-2 h-5 w-5 text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15" />
            </svg>
            Reset
          </button>
        </div>
      </div>

      <!-- Timer Display -->
      <div class="mt-6 flex justify-center">
        <div class="relative">
          <svg class="w-32 h-32">
            <circle
              class="text-gray-200"
              stroke-width="8"
              stroke="currentColor"
              fill="transparent"
              r="56"
              cx="64"
              cy="64"
            />
            <circle
              class="text-primary-600"
              stroke-width="8"
              :stroke-dasharray="circumference"
              :stroke-dashoffset="dashOffset"
              stroke-linecap="round"
              stroke="currentColor"
              fill="transparent"
              r="56"
              cx="64"
              cy="64"
            />
          </svg>
          <div class="absolute inset-0 flex items-center justify-center">
            <span class="text-4xl font-bold text-gray-900">{{ formattedTime }}</span>
          </div>
        </div>
      </div>

      <!-- Quick Presets -->
      <div class="mt-6">
        <h4 class="text-sm font-medium text-gray-700 mb-3">Quick Presets</h4>
        <div class="grid grid-cols-4 gap-3">
          <button
            v-for="preset in presets"
            :key="preset"
            @click="setPreset(preset)"
            class="inline-flex items-center justify-center px-4 py-2 border border-gray-300 shadow-sm text-sm font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
          >
            {{ preset }}s
          </button>
        </div>
      </div>

      <!-- Custom Time Input -->
      <div class="mt-6">
        <label for="custom-time" class="block text-sm font-medium text-gray-700">Custom Time (seconds)</label>
        <div class="mt-1 flex rounded-md shadow-sm">
          <input
            type="number"
            id="custom-time"
            v-model="customTime"
            min="0"
            step="5"
            class="flex-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-primary-500 focus:border-primary-500 sm:text-sm"
          />
          <button
            @click="setCustomTime"
            class="ml-3 inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-primary-600 hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
          >
            Set
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onUnmounted } from 'vue'

const props = defineProps({
  initialTime: {
    type: Number,
    default: 60
  }
})

const emit = defineEmits(['complete'])

const timeRemaining = ref(props.initialTime)
const isRunning = ref(false)
const customTime = ref('')
const timerInterval = ref(null)

const presets = [30, 45, 60, 90]

// Calculate circle progress
const circumference = computed(() => 2 * Math.PI * 56)
const dashOffset = computed(() => {
  const progress = timeRemaining.value / props.initialTime
  return circumference.value * (1 - progress)
})

// Format time as MM:SS
const formattedTime = computed(() => {
  const minutes = Math.floor(timeRemaining.value / 60)
  const seconds = timeRemaining.value % 60
  return `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`
})

const startTimer = () => {
  if (isRunning.value) return
  isRunning.value = true
  timerInterval.value = setInterval(() => {
    if (timeRemaining.value > 0) {
      timeRemaining.value--
    } else {
      completeTimer()
    }
  }, 1000)
}

const pauseTimer = () => {
  isRunning.value = false
  clearInterval(timerInterval.value)
}

const resetTimer = () => {
  pauseTimer()
  timeRemaining.value = props.initialTime
}

const completeTimer = () => {
  pauseTimer()
  emit('complete')
}

const setPreset = (seconds) => {
  pauseTimer()
  timeRemaining.value = seconds
  props.initialTime = seconds
}

const setCustomTime = () => {
  const seconds = parseInt(customTime.value)
  if (seconds > 0) {
    setPreset(seconds)
  }
}

onUnmounted(() => {
  if (timerInterval.value) {
    clearInterval(timerInterval.value)
  }
})
</script>

<style scoped>
.circle-progress {
  transform: rotate(-90deg);
  transform-origin: 50% 50%;
}
</style> 