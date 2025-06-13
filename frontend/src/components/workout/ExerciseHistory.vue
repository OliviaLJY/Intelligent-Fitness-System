<template>
  <div class="bg-white shadow rounded-lg overflow-hidden">
    <div class="p-6 border-b border-gray-200">
      <div class="flex items-center justify-between">
        <div>
          <h2 class="text-2xl font-bold text-gray-900">Exercise History</h2>
          <p class="mt-1 text-sm text-gray-500">Track your progress over time</p>
        </div>
        <div class="flex space-x-3">
          <select
            v-model="selectedTimeRange"
            class="block w-full pl-3 pr-10 py-2 text-base border-gray-300 focus:outline-none focus:ring-primary-500 focus:border-primary-500 sm:text-sm rounded-md"
          >
            <option value="week">Last Week</option>
            <option value="month">Last Month</option>
            <option value="year">Last Year</option>
            <option value="all">All Time</option>
          </select>
        </div>
      </div>
    </div>

    <div class="p-6 space-y-6">
      <!-- Exercise Selection -->
      <div>
        <label for="exercise-select" class="block text-sm font-medium text-gray-700">Select Exercise</label>
        <select
          id="exercise-select"
          v-model="selectedExercise"
          class="mt-1 block w-full pl-3 pr-10 py-2 text-base border-gray-300 focus:outline-none focus:ring-primary-500 focus:border-primary-500 sm:text-sm rounded-md"
        >
          <option value="">Choose an exercise</option>
          <option v-for="exercise in exercises" :key="exercise.id" :value="exercise.id">
            {{ exercise.name }}
          </option>
        </select>
      </div>

      <!-- Progress Chart -->
      <div v-if="selectedExercise" class="bg-gray-50 rounded-lg p-4">
        <h3 class="text-lg font-medium text-gray-900 mb-4">Progress Chart</h3>
        <div class="h-64">
          <canvas ref="chartCanvas"></canvas>
        </div>
      </div>

      <!-- Stats Overview -->
      <div v-if="selectedExercise" class="grid grid-cols-1 gap-5 sm:grid-cols-3">
        <div class="bg-white overflow-hidden shadow rounded-lg">
          <div class="p-5">
            <div class="flex items-center">
              <div class="flex-shrink-0">
                <svg class="h-6 w-6 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
                </svg>
              </div>
              <div class="ml-5 w-0 flex-1">
                <dl>
                  <dt class="text-sm font-medium text-gray-500 truncate">Personal Best</dt>
                  <dd class="flex items-baseline">
                    <div class="text-2xl font-semibold text-gray-900">{{ personalBest }}</div>
                    <div class="ml-2 flex items-baseline text-sm font-semibold text-green-600">
                      <svg class="self-center flex-shrink-0 h-5 w-5 text-green-500" fill="currentColor" viewBox="0 0 20 20">
                        <path fill-rule="evenodd" d="M5.293 9.707a1 1 0 010-1.414l4-4a1 1 0 011.414 0l4 4a1 1 0 01-1.414 1.414L11 7.414V15a1 1 0 11-2 0V7.414L6.707 9.707a1 1 0 01-1.414 0z" clip-rule="evenodd" />
                      </svg>
                      <span class="sr-only">Increased by</span>
                      {{ improvementPercentage }}%
                    </div>
                  </dd>
                </dl>
              </div>
            </div>
          </div>
        </div>

        <div class="bg-white overflow-hidden shadow rounded-lg">
          <div class="p-5">
            <div class="flex items-center">
              <div class="flex-shrink-0">
                <svg class="h-6 w-6 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
              </div>
              <div class="ml-5 w-0 flex-1">
                <dl>
                  <dt class="text-sm font-medium text-gray-500 truncate">Total Volume</dt>
                  <dd class="text-2xl font-semibold text-gray-900">{{ totalVolume }}</dd>
                </dl>
              </div>
            </div>
          </div>
        </div>

        <div class="bg-white overflow-hidden shadow rounded-lg">
          <div class="p-5">
            <div class="flex items-center">
              <div class="flex-shrink-0">
                <svg class="h-6 w-6 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" />
                </svg>
              </div>
              <div class="ml-5 w-0 flex-1">
                <dl>
                  <dt class="text-sm font-medium text-gray-500 truncate">Total Sessions</dt>
                  <dd class="text-2xl font-semibold text-gray-900">{{ totalSessions }}</dd>
                </dl>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Recent History -->
      <div v-if="selectedExercise" class="bg-white shadow overflow-hidden sm:rounded-md">
        <ul class="divide-y divide-gray-200">
          <li v-for="session in recentSessions" :key="session.id">
            <div class="px-4 py-4 sm:px-6">
              <div class="flex items-center justify-between">
                <div class="flex items-center">
                  <p class="text-sm font-medium text-primary-600 truncate">{{ session.date }}</p>
                  <div class="ml-2 flex-shrink-0 flex">
                    <p class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">
                      {{ session.sets }} sets
                    </p>
                  </div>
                </div>
                <div class="ml-2 flex-shrink-0 flex">
                  <p class="text-sm text-gray-500">{{ session.weight }}kg × {{ session.reps }} reps</p>
                </div>
              </div>
              <div class="mt-2 sm:flex sm:justify-between">
                <div class="sm:flex">
                  <p class="flex items-center text-sm text-gray-500">
                    <svg class="flex-shrink-0 mr-1.5 h-5 w-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
                    </svg>
                    {{ session.duration }} minutes
                  </p>
                </div>
                <div class="mt-2 flex items-center text-sm text-gray-500 sm:mt-0">
                  <svg class="flex-shrink-0 mr-1.5 h-5 w-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                  </svg>
                  <p>
                    Form Score: {{ session.formScore }}%
                  </p>
                </div>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'
import Chart from 'chart.js/auto'

const props = defineProps({
  exercises: {
    type: Array,
    default: () => []
  }
})

const selectedExercise = ref('')
const selectedTimeRange = ref('month')
const chartCanvas = ref(null)
const chart = ref(null)

// Mock data - replace with actual data from your backend
const personalBest = ref('100kg × 8 reps')
const improvementPercentage = ref(12)
const totalVolume = ref('2,450kg')
const totalSessions = ref(24)
const recentSessions = ref([
  {
    id: 1,
    date: '2024-03-15',
    sets: 3,
    weight: 95,
    reps: 8,
    duration: 45,
    formScore: 92
  },
  {
    id: 2,
    date: '2024-03-12',
    sets: 3,
    weight: 90,
    reps: 10,
    duration: 40,
    formScore: 88
  },
  // Add more sessions...
])

// Initialize chart when component is mounted
onMounted(() => {
  if (chartCanvas.value) {
    initializeChart()
  }
})

// Watch for changes in selected exercise or time range
watch([selectedExercise, selectedTimeRange], () => {
  if (selectedExercise.value) {
    updateChart()
  }
})

const initializeChart = () => {
  const ctx = chartCanvas.value.getContext('2d')
  chart.value = new Chart(ctx, {
    type: 'line',
    data: {
      labels: [],
      datasets: [
        {
          label: 'Weight (kg)',
          data: [],
          borderColor: 'rgb(59, 130, 246)',
          tension: 0.1
        },
        {
          label: 'Reps',
          data: [],
          borderColor: 'rgb(16, 185, 129)',
          tension: 0.1
        }
      ]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  })
}

const updateChart = () => {
  // Fetch and update chart data based on selected exercise and time range
  // This is where you would typically make an API call
  const mockData = {
    labels: ['Week 1', 'Week 2', 'Week 3', 'Week 4'],
    weight: [80, 85, 90, 95],
    reps: [8, 8, 10, 8]
  }

  if (chart.value) {
    chart.value.data.labels = mockData.labels
    chart.value.data.datasets[0].data = mockData.weight
    chart.value.data.datasets[1].data = mockData.reps
    chart.value.update()
  }
}
</script> 