<template>
  <div class="py-6 animate-fade-in">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <!-- Header -->
      <div class="flex justify-between items-center mb-6">
        <div>
          <h1 class="text-2xl font-semibold text-gray-900">Workout Session</h1>
          <p class="mt-1 text-sm text-gray-500">
            {{ currentWorkout ? currentWorkout.name : 'Start a new workout session' }}
          </p>
        </div>
        <div class="flex space-x-4">
          <button
            v-if="!currentWorkout"
            @click="showWorkoutCreator = true"
            class="btn-primary flex items-center space-x-2"
          >
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6" />
            </svg>
            <span>Create Workout</span>
          </button>
          <button
            v-if="!currentWorkout"
            @click="showTemplates = true"
            class="btn-secondary flex items-center space-x-2"
          >
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
            </svg>
            <span>Use Template</span>
          </button>
          <button
            v-if="currentWorkout"
            @click="endWorkoutSession"
            class="btn-danger flex items-center space-x-2"
          >
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
            </svg>
            <span>End Session</span>
          </button>
        </div>
      </div>

      <!-- Main Content -->
      <div v-if="currentWorkout" class="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <!-- Video Feed and Exercise List -->
        <div class="lg:col-span-2 space-y-6">
          <!-- Video Feed -->
          <div class="card">
            <div class="relative">
              <div class="aspect-w-16 aspect-h-9 bg-gray-100 rounded-lg overflow-hidden">
                <div v-if="!isRecording" class="absolute inset-0 flex items-center justify-center">
                  <div class="text-center">
                    <svg class="mx-auto h-12 w-12 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 10l4.553-2.276A1 1 0 0121 8.618v6.764a1 1 0 01-1.447.894L15 14M5 18h8a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v8a2 2 0 002 2z" />
                    </svg>
                    <p class="mt-2 text-sm text-gray-500">Camera feed will appear here</p>
                  </div>
                </div>
                <video
                  v-else
                  ref="videoElement"
                  class="w-full h-full object-cover"
                  autoplay
                  muted
                ></video>
                <!-- Motion Analysis Overlay -->
                <div v-if="isRecording" class="absolute inset-0 pointer-events-none">
                  <div class="absolute top-4 left-4 bg-black bg-opacity-75 text-white px-3 py-1 rounded-full text-sm">
                    AI Analysis Active
                  </div>
                </div>
              </div>
              
              <div class="mt-4 flex justify-center space-x-4">
                <button
                  v-if="!isRecording"
                  @click="startRecording"
                  class="btn-primary flex items-center space-x-2"
                >
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14.752 11.168l-3.197-2.132A1 1 0 0010 9.87v4.263a1 1 0 001.555.832l3.197-2.132a1 1 0 000-1.664z" />
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                  </svg>
                  <span>Start Session</span>
                </button>
                <button
                  v-else
                  @click="stopRecording"
                  class="btn-danger flex items-center space-x-2"
                >
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                  </svg>
                  <span>Stop Session</span>
                </button>
              </div>
            </div>
          </div>

          <!-- Exercise List -->
          <div class="card">
            <h3 class="text-lg font-medium text-gray-900 mb-4">Exercise List</h3>
            <div class="space-y-4">
              <div
                v-for="exercise in currentWorkout.exercises"
                :key="exercise.id"
                class="flex items-center justify-between p-4 bg-gray-50 rounded-lg"
                :class="{ 'opacity-50': exercise.completed }"
              >
                <div class="flex items-center space-x-4">
                  <div class="w-12 h-12 bg-primary-100 rounded-lg flex items-center justify-center">
                    <svg class="w-6 h-6 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
                    </svg>
                  </div>
                  <div>
                    <h4 class="text-sm font-medium text-gray-900">{{ exercise.name }}</h4>
                    <p class="text-sm text-gray-500">
                      {{ exercise.currentSet }}/{{ exercise.sets }} sets × {{ exercise.reps }} reps
                    </p>
                  </div>
                </div>
                <div class="flex items-center space-x-2">
                  <button
                    v-if="!exercise.completed"
                    @click="startExercise(exercise)"
                    class="btn-primary text-sm"
                  >
                    Start
                  </button>
                  <span
                    v-else
                    class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-green-100 text-green-800"
                  >
                    Completed
                  </span>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Sidebar -->
        <div class="space-y-6">
          <!-- Current Exercise -->
          <div v-if="currentExercise" class="card">
            <h3 class="text-lg font-medium text-gray-900 mb-4">Current Exercise</h3>
            <div class="space-y-4">
              <div class="text-center">
                <h4 class="text-lg font-medium text-gray-900">{{ currentExercise.name }}</h4>
                <p class="text-sm text-gray-500">
                  Set {{ currentExercise.currentSet + 1 }} of {{ currentExercise.sets }}
                </p>
              </div>
              <div class="grid grid-cols-2 gap-4">
                <div class="stat-card">
                  <p class="text-sm font-medium text-gray-500">Reps</p>
                  <p class="mt-1 text-2xl font-semibold text-gray-900">{{ currentExercise.reps }}</p>
                </div>
                <div class="stat-card">
                  <p class="text-sm font-medium text-gray-500">Weight</p>
                  <p class="mt-1 text-2xl font-semibold text-gray-900">{{ currentExercise.weight || 0 }}kg</p>
                </div>
              </div>
            </div>
          </div>

          <!-- Rest Timer -->
          <RestTimer
            v-if="showRestTimer"
            :initial-time="restTime"
            @complete="onRestComplete"
          />

          <!-- Form Feedback -->
          <div v-if="currentExercise && isRecording" class="card">
            <h3 class="text-lg font-medium text-gray-900 mb-4">Form Feedback</h3>
            <div class="space-y-4">
              <div
                v-for="(feedback, index) in formFeedback"
                :key="index"
                class="p-4 bg-gray-50 rounded-lg"
              >
                <div class="flex items-center space-x-3">
                  <svg
                    :class="[
                      'h-5 w-5 flex-shrink-0',
                      feedback.type === 'success' ? 'text-green-500' : 'text-red-500'
                    ]"
                    fill="none"
                    stroke="currentColor"
                    viewBox="0 0 24 24"
                  >
                    <path
                      v-if="feedback.type === 'success'"
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="2"
                      d="M5 13l4 4L19 7"
                    />
                    <path
                      v-else
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      stroke-width="2"
                      d="M6 18L18 6M6 6l12 12"
                    />
                  </svg>
                  <p class="text-sm text-gray-500">{{ feedback.message }}</p>
                </div>
              </div>
            </div>
          </div>

          <!-- Session Stats -->
          <div class="card">
            <h3 class="text-lg font-medium text-gray-900 mb-4">Session Stats</h3>
            <div class="grid grid-cols-2 gap-4">
              <div class="stat-card">
                <p class="text-sm font-medium text-gray-500">Duration</p>
                <p class="mt-1 text-2xl font-semibold text-gray-900">{{ sessionDuration }}</p>
              </div>
              <div class="stat-card">
                <p class="text-sm font-medium text-gray-500">Exercises</p>
                <p class="mt-1 text-2xl font-semibold text-gray-900">
                  {{ completedExercises }}/{{ totalExercises }}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Empty State -->
      <div
        v-else
        class="text-center py-12"
      >
        <svg
          class="mx-auto h-12 w-12 text-gray-400"
          fill="none"
          stroke="currentColor"
          viewBox="0 0 24 24"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2"
          />
        </svg>
        <h3 class="mt-2 text-sm font-medium text-gray-900">No active workout</h3>
        <p class="mt-1 text-sm text-gray-500">Get started by creating a new workout or using a template.</p>
        <div class="mt-6">
          <button
            @click="showWorkoutCreator = true"
            class="btn-primary"
          >
            Create Workout
          </button>
        </div>
      </div>

      <!-- Modals -->
      <WorkoutCreator
        v-if="showWorkoutCreator"
        @close="showWorkoutCreator = false"
        @save="startWorkoutSession"
        @save-template="saveWorkoutTemplate"
      />

      <!-- Templates Modal -->
      <div
        v-if="showTemplates"
        class="fixed inset-0 bg-gray-500 bg-opacity-75 flex items-center justify-center p-4"
      >
        <div class="bg-white rounded-lg max-w-2xl w-full p-6">
          <div class="flex justify-between items-center mb-4">
            <h3 class="text-lg font-medium text-gray-900">Workout Templates</h3>
            <button
              @click="showTemplates = false"
              class="text-gray-400 hover:text-gray-500"
            >
              <svg class="h-6 w-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
              </svg>
            </button>
          </div>
          <div class="space-y-4">
            <div
              v-for="template in workoutTemplates"
              :key="template.id"
              class="flex items-center justify-between p-4 bg-gray-50 rounded-lg"
            >
              <div>
                <h4 class="text-sm font-medium text-gray-900">{{ template.name }}</h4>
                <p class="text-sm text-gray-500">{{ template.exercises.length }} exercises</p>
              </div>
              <button
                @click="useTemplate(template)"
                class="btn-primary text-sm"
              >
                Use Template
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, onUnmounted } from 'vue'
import { useWorkoutStore } from '@/stores/workout'
import WorkoutCreator from '@/components/workout/WorkoutCreator.vue'
import RestTimer from '@/components/workout/RestTimer.vue'

const workoutStore = useWorkoutStore()

// State
const showWorkoutCreator = ref(false)
const showTemplates = ref(false)
const showRestTimer = ref(false)
const isRecording = ref(false)
const currentExercise = ref(null)
const formFeedback = ref([])
const videoElement = ref(null)
const restTime = ref(60)
const sessionStartTime = ref(null)
const sessionTimer = ref(null)

// Computed
const currentWorkout = computed(() => workoutStore.currentWorkout)
const workoutTemplates = computed(() => workoutStore.workoutTemplates)
const sessionDuration = computed(() => {
  if (!sessionStartTime.value) return '00:00'
  const duration = Math.floor((Date.now() - sessionStartTime.value) / 1000)
  const minutes = Math.floor(duration / 60)
  const seconds = duration % 60
  return `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`
})
const completedExercises = computed(() => {
  if (!currentWorkout.value) return 0
  return currentWorkout.value.exercises.filter(ex => ex.completed).length
})
const totalExercises = computed(() => {
  if (!currentWorkout.value) return 0
  return currentWorkout.value.exercises.length
})

// Methods
const startWorkoutSession = async (workoutData) => {
  await workoutStore.startWorkout(workoutData)
  showWorkoutCreator.value = false
  sessionStartTime.value = Date.now()
  startSessionTimer()
}

const endWorkoutSession = async () => {
  if (confirm('Are you sure you want to end this workout session?')) {
    await workoutStore.endWorkout()
    stopSessionTimer()
    sessionStartTime.value = null
    isRecording.value = false
    currentExercise.value = null
    formFeedback.value = []
  }
}

const startExercise = (exercise) => {
  currentExercise.value = exercise
  showRestTimer.value = false
  if (!isRecording.value) {
    startRecording()
  }
}

const startRecording = async () => {
  try {
    const stream = await navigator.mediaDevices.getUserMedia({ video: true })
    if (videoElement.value) {
      videoElement.value.srcObject = stream
    }
    isRecording.value = true
    // Start AI analysis here
  } catch (error) {
    console.error('Error accessing camera:', error)
    alert('Failed to access camera. Please make sure you have granted camera permissions.')
  }
}

const stopRecording = () => {
  if (videoElement.value && videoElement.value.srcObject) {
    const tracks = videoElement.value.srcObject.getTracks()
    tracks.forEach(track => track.stop())
    videoElement.value.srcObject = null
  }
  isRecording.value = false
  // Stop AI analysis here
}

const onRestComplete = () => {
  showRestTimer.value = false
  if (currentExercise.value) {
    // Start next set or exercise
    if (currentExercise.value.currentSet < currentExercise.value.sets - 1) {
      currentExercise.value.currentSet++
    } else {
      currentExercise.value.completed = true
      currentExercise.value = null
    }
  }
}

const useTemplate = (template) => {
  startWorkoutSession(template)
  showTemplates.value = false
}

const saveWorkoutTemplate = async (template) => {
  await workoutStore.saveWorkoutTemplate(template)
  showWorkoutCreator.value = false
}

const startSessionTimer = () => {
  sessionTimer.value = setInterval(() => {
    // Update session duration
  }, 1000)
}

const stopSessionTimer = () => {
  if (sessionTimer.value) {
    clearInterval(sessionTimer.value)
    sessionTimer.value = null
  }
}

// Lifecycle hooks
onMounted(() => {
  workoutStore.fetchExercises()
  workoutStore.fetchWorkoutHistory()
})

onUnmounted(() => {
  stopRecording()
  stopSessionTimer()
})
</script>

<style scoped>
.btn-primary {
  @apply inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-primary-600 hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500;
}

.btn-secondary {
  @apply inline-flex items-center px-4 py-2 border border-gray-300 shadow-sm text-sm font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500;
}

.btn-danger {
  @apply inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-red-600 hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500;
}

.card {
  @apply bg-white shadow rounded-lg overflow-hidden;
}

.stat-card {
  @apply bg-gray-50 p-4 rounded-lg;
}
</style> 