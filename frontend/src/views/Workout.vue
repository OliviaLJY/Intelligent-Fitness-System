<template>
  <div class="min-h-screen bg-apple-background">
    <!-- Apple-style Navigation Bar -->
    <div class="apple-nav-bar">
      <div class="max-w-7xl mx-auto px-6">
        <div class="flex items-center justify-between h-16">
          <div class="flex items-center space-x-3">
            <div class="apple-icon-container">
              <svg class="w-6 h-6 text-apple-blue" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z" />
              </svg>
            </div>
            <h1 class="apple-title">Workout</h1>
          </div>
          <div class="apple-nav-actions">
            <button
              v-if="!currentWorkout"
              @click="showWorkoutCreator = true"
              class="apple-button-primary"
            >
              <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6" />
              </svg>
              <span>New Workout</span>
            </button>
            <button
              v-if="!currentWorkout"
              @click="showTemplates = true"
              class="apple-button-secondary"
            >
              <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
              </svg>
              <span>Templates</span>
            </button>
            <button
              v-if="currentWorkout"
              @click="endWorkoutSession"
              class="apple-button-destructive"
            >
              <span>End Session</span>
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Main Content -->
    <div class="apple-main-content">
      <div class="max-w-7xl mx-auto px-6">
        <!-- Active Workout Section -->
        <div v-if="currentWorkout" class="space-y-8">
          <!-- Header Info -->
          <div class="apple-section-header">
            <h2 class="apple-section-title">{{ currentWorkout.name }}</h2>
            <p class="apple-section-subtitle">{{ completedExercises }} of {{ totalExercises }} exercises completed</p>
          </div>

          <div class="grid grid-cols-1 lg:grid-cols-3 gap-8">
            <!-- Video and Exercise Section -->
            <div class="lg:col-span-2 space-y-8">
              <!-- Video Feed -->
              <div class="apple-card-large">
                <div class="apple-card-header">
                  <h3 class="apple-card-title">AI Form Analysis</h3>
                  <div v-if="isRecording" class="apple-status-indicator active">
                    <div class="apple-pulse-dot"></div>
                    <span>Recording</span>
                  </div>
                </div>
                
                <div class="apple-video-container">
                  <div v-if="!isRecording" class="apple-video-placeholder">
                    <div class="apple-placeholder-icon">
                      <svg class="w-16 h-16 text-apple-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M15 10l4.553-2.276A1 1 0 0121 8.618v6.764a1 1 0 01-1.447.894L15 14M5 18h8a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v8a2 2 0 002 2z" />
                      </svg>
                    </div>
                    <h4 class="apple-placeholder-title">Ready for AI Analysis</h4>
                    <p class="apple-placeholder-text">Start recording to begin real-time form analysis</p>
                  </div>
                  
                  <video
                    v-else
                    ref="videoElement"
                    class="apple-video-element"
                    autoplay
                    muted
                  ></video>
                  
                  <!-- AI Overlay -->
                  <div v-if="isRecording" class="apple-video-overlay">
                    <div class="apple-ai-badge">
                      <span>Form Score: 85%</span>
                    </div>
                  </div>
                </div>
                
                <div class="apple-video-controls">
                  <button
                    v-if="!isRecording"
                    @click="startRecording"
                    class="apple-button-large apple-button-success"
                  >
                    <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14.752 11.168l-3.197-2.132A1 1 0 0010 9.87v4.263a1 1 0 001.555.832l3.197-2.132a1 1 0 000-1.664z" />
                    </svg>
                    <span>Start Recording</span>
                  </button>
                  <button
                    v-else
                    @click="stopRecording"
                    class="apple-button-large apple-button-destructive"
                  >
                    <div class="w-2 h-2 bg-current rounded-sm"></div>
                    <span>Stop Recording</span>
                  </button>
                </div>
              </div>

              <!-- Exercise List -->
              <div class="apple-card">
                <div class="apple-card-header">
                  <h3 class="apple-card-title">Exercise Queue</h3>
                  <div class="apple-progress-badge">
                    {{ completedExercises }}/{{ totalExercises }}
                  </div>
                </div>
                
                <div class="apple-exercise-list">
                  <div
                    v-for="(exercise, index) in currentWorkout.exercises"
                    :key="exercise.id"
                    class="apple-exercise-item"
                    :class="{
                      'completed': exercise.completed,
                      'active': currentExercise?.id === exercise.id
                    }"
                  >
                    <div class="apple-exercise-number">
                      <span v-if="!exercise.completed">{{ index + 1 }}</span>
                      <svg v-else class="w-5 h-5 text-apple-green" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                      </svg>
                    </div>
                    
                    <div class="apple-exercise-content">
                      <h4 class="apple-exercise-name">{{ exercise.name }}</h4>
                      <div class="apple-exercise-details">
                        <span>{{ exercise.sets }} sets</span>
                        <span class="apple-separator">•</span>
                        <span>{{ exercise.reps }} reps</span>
                        <span v-if="exercise.weight" class="apple-separator">•</span>
                        <span v-if="exercise.weight">{{ exercise.weight }}kg</span>
                      </div>
                      
                      <!-- Progress Bar -->
                      <div class="apple-progress-bar">
                        <div 
                          class="apple-progress-fill"
                          :style="{ width: `${(exercise.currentSet / exercise.sets) * 100}%` }"
                        ></div>
                      </div>
                    </div>
                    
                    <div class="apple-exercise-action">
                      <button
                        v-if="!exercise.completed"
                        @click="startExercise(exercise)"
                        class="apple-button-small"
                        :class="{ 'active': currentExercise?.id === exercise.id }"
                      >
                        {{ currentExercise?.id === exercise.id ? 'Active' : 'Start' }}
                      </button>
                      <div v-else class="apple-completed-badge">
                        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                        </svg>
                        <span>Done</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Sidebar -->
            <div class="space-y-6">
              <!-- Current Exercise -->
              <div v-if="currentExercise" class="apple-card">
                <div class="apple-card-header">
                  <h3 class="apple-card-title">Current Exercise</h3>
                </div>
                
                <div class="apple-current-exercise">
                  <h4 class="apple-current-exercise-name">{{ currentExercise.name }}</h4>
                  <div class="apple-set-indicator">
                    Set {{ currentExercise.currentSet + 1 }} of {{ currentExercise.sets }}
                  </div>
                  
                  <div class="apple-exercise-stats">
                    <div class="apple-stat-item">
                      <div class="apple-stat-icon">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 12l3-3 3 3 4-4M8 21l4-4 4 4M3 4h18M4 4h16v12a1 1 0 01-1 1H5a1 1 0 01-1-1V4z" />
                        </svg>
                      </div>
                      <div class="apple-stat-content">
                        <span class="apple-stat-label">Reps</span>
                        <span class="apple-stat-value">{{ currentExercise.reps }}</span>
                      </div>
                    </div>
                    
                    <div class="apple-stat-item">
                      <div class="apple-stat-icon">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 6l3 1m0 0l-3 9a5.002 5.002 0 006.001 0M6 7l3 9M6 7l6-2m6 2l3-1m-3 1l-3 9a5.002 5.002 0 006.001 0M18 7l3 9m-3-9l-6-2m0-2v2m0 16V5m0 16H9m3 0h3" />
                        </svg>
                      </div>
                      <div class="apple-stat-content">
                        <span class="apple-stat-label">Weight</span>
                        <span class="apple-stat-value">{{ currentExercise.weight || 0 }}kg</span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Rest Timer -->
              <RestTimer
                v-if="showRestTimer"
                :initial-time="restTime"
                @complete="onRestComplete"
                class="apple-card"
              />

              <!-- Form Feedback -->
              <div v-if="currentExercise && isRecording" class="apple-card">
                <div class="apple-card-header">
                  <h3 class="apple-card-title">Form Analysis</h3>
                </div>
                
                <div class="apple-feedback-list">
                  <div
                    v-for="(feedback, index) in formFeedback"
                    :key="index"
                    class="apple-feedback-item"
                    :class="feedback.type"
                  >
                    <div class="apple-feedback-icon">
                      <svg
                        v-if="feedback.type === 'success'"
                        class="w-5 h-5 text-apple-green"
                        fill="none"
                        stroke="currentColor"
                        viewBox="0 0 24 24"
                      >
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                      </svg>
                      <svg
                        v-else
                        class="w-5 h-5 text-apple-red"
                        fill="none"
                        stroke="currentColor"
                        viewBox="0 0 24 24"
                      >
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                      </svg>
                    </div>
                    <p class="apple-feedback-text">{{ feedback.message }}</p>
                  </div>
                  
                  <div v-if="formFeedback.length === 0" class="apple-feedback-empty">
                    <div class="apple-loading-spinner"></div>
                    <p>Analyzing your form...</p>
                  </div>
                </div>
              </div>

              <!-- Session Stats -->
              <div class="apple-card">
                <div class="apple-card-header">
                  <h3 class="apple-card-title">Session Stats</h3>
                </div>
                
                <div class="apple-stats-grid">
                  <div class="apple-stat-card">
                    <div class="apple-stat-icon">
                      <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                      </svg>
                    </div>
                    <div class="apple-stat-content">
                      <span class="apple-stat-label">Duration</span>
                      <span class="apple-stat-value">{{ sessionDuration }}</span>
                    </div>
                  </div>
                  
                  <div class="apple-stat-card">
                    <div class="apple-stat-icon">
                      <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z" />
                      </svg>
                    </div>
                    <div class="apple-stat-content">
                      <span class="apple-stat-label">Progress</span>
                      <span class="apple-stat-value">{{ completedExercises }}/{{ totalExercises }}</span>
                    </div>
                  </div>
                </div>
                
                <!-- Overall Progress -->
                <div class="apple-overall-progress">
                  <div class="flex justify-between items-center mb-2">
                    <span class="apple-progress-label">Overall Progress</span>
                    <span class="apple-progress-percentage">{{ Math.round((completedExercises / totalExercises) * 100) }}%</span>
                  </div>
                  <div class="apple-progress-track">
                    <div 
                      class="apple-progress-fill"
                      :style="{ width: `${(completedExercises / totalExercises) * 100}%` }"
                    ></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Empty State -->
        <div v-else class="apple-empty-state">
          <div class="apple-empty-icon">
            <svg class="w-20 h-20 text-apple-gray-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1" d="M13 10V3L4 14h7v7l9-11h-7z" />
            </svg>
          </div>
          <h3 class="apple-empty-title">Ready to Start Training?</h3>
          <p class="apple-empty-text">Create a new workout or choose from our curated templates to begin your fitness journey.</p>
          
          <div class="apple-empty-actions">
            <button @click="showWorkoutCreator = true" class="apple-button-large apple-button-primary">
              <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6" />
              </svg>
              <span>Create Workout</span>
            </button>
            <button @click="showTemplates = true" class="apple-button-large apple-button-secondary">
              <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
              </svg>
              <span>Browse Templates</span>
            </button>
          </div>
        </div>
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
    <div v-if="showTemplates" class="apple-modal-overlay">
      <div class="apple-modal">
        <div class="apple-modal-header">
          <h3 class="apple-modal-title">Workout Templates</h3>
          <button @click="showTemplates = false" class="apple-close-button">
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
            </svg>
          </button>
        </div>
        
        <div class="apple-modal-content">
          <div class="apple-template-grid">
            <div
              v-for="template in workoutTemplates"
              :key="template.id"
              class="apple-template-card"
              @click="useTemplate(template)"
            >
              <div class="apple-template-icon">
                <svg class="w-8 h-8" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z" />
                </svg>
              </div>
              <h4 class="apple-template-name">{{ template.name }}</h4>
              <p class="apple-template-description">{{ template.description || 'Professional workout template' }}</p>
              <div class="apple-template-meta">
                <span>{{ template.exercises.length }} exercises</span>
                <span>{{ template.duration || '45' }} min</span>
              </div>
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
/* ===== Apple Design System Variables ===== */
:root {
  --apple-blue: #007AFF;
  --apple-blue-light: #5AC8FA;
  --apple-blue-dark: #0051D5;
  --apple-green: #34C759;
  --apple-red: #FF3B30;
  --apple-orange: #FF9500;
  --apple-yellow: #FFCC00;
  --apple-gray-900: #1D1D1F;
  --apple-gray-800: #424245;
  --apple-gray-700: #6E6E73;
  --apple-gray-600: #8E8E93;
  --apple-gray-500: #AEAEB2;
  --apple-gray-400: #C7C7CC;
  --apple-gray-300: #D1D1D6;
  --apple-gray-200: #E5E5EA;
  --apple-gray-100: #F2F2F7;
  --apple-gray-50: #F9F9FB;
  
  --apple-radius-sm: 8px;
  --apple-radius-md: 12px;
  --apple-radius-lg: 16px;
  --apple-radius-xl: 20px;
  --apple-radius-2xl: 24px;
  
  --apple-shadow-sm: 0 1px 3px rgba(0, 0, 0, 0.1);
  --apple-shadow-md: 0 4px 6px rgba(0, 0, 0, 0.07);
  --apple-shadow-lg: 0 10px 15px rgba(0, 0, 0, 0.1);
  --apple-shadow-xl: 0 20px 25px rgba(0, 0, 0, 0.1);
}

/* ===== Base Styles ===== */
.bg-apple-background {
  background: linear-gradient(135deg, #F9F9FB 0%, #F2F2F7 100%);
}

.text-apple-blue { color: var(--apple-blue); }
.text-apple-green { color: var(--apple-green); }
.text-apple-red { color: var(--apple-red); }
.text-apple-gray-900 { color: var(--apple-gray-900); }
.text-apple-gray-800 { color: var(--apple-gray-800); }
.text-apple-gray-700 { color: var(--apple-gray-700); }
.text-apple-gray-600 { color: var(--apple-gray-600); }
.text-apple-gray-500 { color: var(--apple-gray-500); }
.text-apple-gray-400 { color: var(--apple-gray-400); }
.text-apple-gray-300 { color: var(--apple-gray-300); }

/* ===== Navigation Bar ===== */
.apple-nav-bar {
  @apply bg-white backdrop-blur-xl bg-opacity-80 border-b border-gray-200;
  position: sticky;
  top: 0;
  z-index: 50;
}

.apple-icon-container {
  @apply w-8 h-8 bg-blue-50 rounded-lg flex items-center justify-center;
}

.apple-title {
  @apply text-xl font-semibold text-gray-900;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
}

.apple-nav-actions {
  @apply flex items-center space-x-3;
}

/* ===== Buttons ===== */
.apple-button-primary {
  @apply inline-flex items-center space-x-2 px-4 py-2 bg-blue-500 text-white rounded-lg font-medium transition-all duration-200;
  font-size: 14px;
  box-shadow: var(--apple-shadow-sm);
}

.apple-button-primary:hover {
  @apply bg-blue-600 scale-105;
  box-shadow: var(--apple-shadow-md);
}

.apple-button-secondary {
  @apply inline-flex items-center space-x-2 px-4 py-2 bg-gray-100 text-gray-700 rounded-lg font-medium transition-all duration-200;
  font-size: 14px;
  box-shadow: var(--apple-shadow-sm);
}

.apple-button-secondary:hover {
  @apply bg-gray-200 scale-105;
}

.apple-button-destructive {
  @apply inline-flex items-center space-x-2 px-4 py-2 bg-red-500 text-white rounded-lg font-medium transition-all duration-200;
  font-size: 14px;
  box-shadow: var(--apple-shadow-sm);
}

.apple-button-destructive:hover {
  @apply bg-red-600 scale-105;
}

.apple-button-large {
  @apply inline-flex items-center space-x-3 px-6 py-3 rounded-xl font-semibold transition-all duration-200;
  font-size: 16px;
  box-shadow: var(--apple-shadow-md);
}

.apple-button-large.apple-button-primary {
  @apply bg-blue-500 text-white;
}

.apple-button-large.apple-button-primary:hover {
  @apply bg-blue-600 scale-105;
  box-shadow: var(--apple-shadow-lg);
}

.apple-button-large.apple-button-secondary {
  @apply bg-gray-100 text-gray-700;
}

.apple-button-large.apple-button-secondary:hover {
  @apply bg-gray-200 scale-105;
}

.apple-button-large.apple-button-success {
  @apply bg-green-500 text-white;
}

.apple-button-large.apple-button-success:hover {
  @apply bg-green-600 scale-105;
}

.apple-button-large.apple-button-destructive {
  @apply bg-red-500 text-white;
}

.apple-button-large.apple-button-destructive:hover {
  @apply bg-red-600 scale-105;
}

.apple-button-small {
  @apply inline-flex items-center px-3 py-1.5 bg-blue-500 text-white rounded-lg text-sm font-medium transition-all duration-200;
  box-shadow: var(--apple-shadow-sm);
}

.apple-button-small:hover {
  @apply bg-blue-600 scale-105;
}

.apple-button-small.active {
  @apply bg-orange-500;
}

.apple-button-small.active:hover {
  @apply bg-orange-600;
}

/* ===== Main Content ===== */
.apple-main-content {
  @apply pt-8 pb-24;
}

.apple-section-header {
  @apply text-center mb-8;
}

.apple-section-title {
  @apply text-3xl font-bold text-gray-900 mb-2;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
}

.apple-section-subtitle {
  @apply text-lg text-gray-600;
}

/* ===== Cards ===== */
.apple-card {
  @apply bg-white rounded-2xl p-6 shadow-lg border border-gray-100 transition-all duration-300;
  backdrop-filter: blur(20px);
  background-color: rgba(255, 255, 255, 0.95);
}

.apple-card:hover {
  box-shadow: var(--apple-shadow-xl);
  transform: translateY(-2px);
}

.apple-card-large {
  @apply bg-white rounded-3xl p-8 shadow-xl border border-gray-100 transition-all duration-300;
  backdrop-filter: blur(20px);
  background-color: rgba(255, 255, 255, 0.95);
}

.apple-card-large:hover {
  box-shadow: var(--apple-shadow-xl);
  transform: translateY(-2px);
}

.apple-card-header {
  @apply flex items-center justify-between mb-6;
}

.apple-card-title {
  @apply text-xl font-semibold text-gray-900;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
}

/* ===== Video Section ===== */
.apple-video-container {
  @apply relative w-full h-80 bg-gray-50 rounded-2xl overflow-hidden mb-6;
}

.apple-video-placeholder {
  @apply absolute inset-0 flex flex-col items-center justify-center text-center space-y-4;
}

.apple-placeholder-icon {
  @apply w-20 h-20 bg-gray-100 rounded-full flex items-center justify-center;
}

.apple-placeholder-title {
  @apply text-xl font-semibold text-gray-800;
}

.apple-placeholder-text {
  @apply text-gray-600;
}

.apple-video-element {
  @apply w-full h-full object-cover;
}

.apple-video-overlay {
  @apply absolute inset-0 pointer-events-none;
}

.apple-ai-badge {
  @apply absolute bottom-4 right-4 bg-black bg-opacity-60 text-white px-3 py-2 rounded-lg text-sm backdrop-blur-sm;
}

.apple-video-controls {
  @apply flex justify-center;
}

.apple-status-indicator {
  @apply flex items-center space-x-2 px-3 py-1.5 bg-gray-100 rounded-full text-sm;
}

.apple-status-indicator.active {
  @apply bg-green-100 text-green-700;
}

.apple-pulse-dot {
  @apply w-2 h-2 bg-current rounded-full animate-pulse;
}

/* ===== Exercise List ===== */
.apple-progress-badge {
  @apply px-3 py-1 bg-blue-100 text-blue-700 rounded-full text-sm font-medium;
}

.apple-exercise-list {
  @apply space-y-4;
}

.apple-exercise-item {
  @apply flex items-center justify-between p-4 bg-gray-50 rounded-xl transition-all duration-200;
}

.apple-exercise-item:hover {
  @apply bg-gray-100 scale-102;
}

.apple-exercise-item.completed {
  @apply bg-green-50 border border-green-200;
}

.apple-exercise-item.active {
  @apply bg-blue-50 border border-blue-200 shadow-md;
}

.apple-exercise-number {
  @apply w-10 h-10 bg-white rounded-lg flex items-center justify-center font-semibold text-gray-700 shadow-sm;
}

.apple-exercise-content {
  @apply flex-1 ml-4;
}

.apple-exercise-name {
  @apply text-lg font-semibold text-gray-900 mb-1;
}

.apple-exercise-details {
  @apply flex items-center space-x-2 text-sm text-gray-600 mb-2;
}

.apple-separator {
  @apply text-gray-400;
}

.apple-progress-bar {
  @apply w-full h-1.5 bg-gray-200 rounded-full overflow-hidden;
}

.apple-progress-fill {
  @apply h-full bg-blue-500 rounded-full transition-all duration-500;
}

.apple-exercise-action {
  @apply ml-4;
}

.apple-completed-badge {
  @apply flex items-center space-x-2 px-3 py-1.5 bg-green-100 text-green-700 rounded-lg text-sm font-medium;
}

/* ===== Current Exercise ===== */
.apple-current-exercise {
  @apply text-center space-y-4;
}

.apple-current-exercise-name {
  @apply text-2xl font-bold text-gray-900;
}

.apple-set-indicator {
  @apply inline-block px-4 py-2 bg-blue-100 text-blue-700 rounded-full text-sm font-medium;
}

.apple-exercise-stats {
  @apply space-y-4;
}

.apple-stat-item {
  @apply flex items-center space-x-3 p-3 bg-gray-50 rounded-xl;
}

.apple-stat-icon {
  @apply w-10 h-10 bg-white rounded-lg flex items-center justify-center text-gray-600;
}

.apple-stat-content {
  @apply flex-1;
}

.apple-stat-label {
  @apply block text-sm text-gray-600;
}

.apple-stat-value {
  @apply block text-xl font-bold text-gray-900;
}

/* ===== Session Stats ===== */
.apple-stats-grid {
  @apply space-y-4 mb-6;
}

.apple-stat-card {
  @apply flex items-center space-x-4 p-4 bg-gray-50 rounded-xl;
}

.apple-overall-progress {
  @apply space-y-2;
}

.apple-progress-label {
  @apply text-sm font-medium text-gray-700;
}

.apple-progress-percentage {
  @apply text-sm font-semibold text-blue-600;
}

.apple-progress-track {
  @apply w-full h-2 bg-gray-200 rounded-full overflow-hidden;
}

/* ===== Form Feedback ===== */
.apple-feedback-list {
  @apply space-y-3;
}

.apple-feedback-item {
  @apply flex items-start space-x-3 p-3 rounded-xl;
}

.apple-feedback-item.success {
  @apply bg-green-50;
}

.apple-feedback-item.error {
  @apply bg-red-50;
}

.apple-feedback-icon {
  @apply flex-shrink-0 mt-0.5;
}

.apple-feedback-text {
  @apply text-sm text-gray-800 leading-relaxed;
}

.apple-feedback-empty {
  @apply text-center py-8 text-gray-500;
}

.apple-loading-spinner {
  @apply w-6 h-6 border-2 border-gray-300 border-t-blue-500 rounded-full animate-spin mx-auto mb-3;
}

/* ===== Empty State ===== */
.apple-empty-state {
  @apply text-center py-20 max-w-lg mx-auto;
}

.apple-empty-icon {
  @apply w-24 h-24 bg-gray-100 rounded-full flex items-center justify-center mx-auto mb-8;
}

.apple-empty-title {
  @apply text-3xl font-bold text-gray-900 mb-4;
}

.apple-empty-text {
  @apply text-lg text-gray-600 mb-8 leading-relaxed;
}

.apple-empty-actions {
  @apply space-y-4;
}

/* ===== Modal ===== */
.apple-modal-overlay {
  @apply fixed inset-0 bg-black bg-opacity-30 backdrop-blur-sm flex items-center justify-center p-6 z-50;
}

.apple-modal {
  @apply bg-white rounded-3xl max-w-4xl w-full max-h-[85vh] overflow-hidden shadow-2xl;
  animation: apple-modal-enter 0.3s ease-out;
}

.apple-modal-header {
  @apply flex items-center justify-between p-6 border-b border-gray-100;
}

.apple-modal-title {
  @apply text-2xl font-bold text-gray-900;
}

.apple-close-button {
  @apply w-8 h-8 bg-gray-100 rounded-full flex items-center justify-center text-gray-600 hover:bg-gray-200 transition-colors duration-200;
}

.apple-modal-content {
  @apply p-6 overflow-y-auto max-h-[70vh];
}

/* ===== Template Grid ===== */
.apple-template-grid {
  @apply grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6;
}

.apple-template-card {
  @apply bg-gray-50 rounded-2xl p-6 cursor-pointer transition-all duration-200 border border-gray-100;
}

.apple-template-card:hover {
  @apply bg-white shadow-lg border-blue-200 transform scale-105;
}

.apple-template-icon {
  @apply w-12 h-12 bg-blue-100 rounded-xl flex items-center justify-center text-blue-600 mb-4;
}

.apple-template-name {
  @apply text-lg font-semibold text-gray-900 mb-2;
}

.apple-template-description {
  @apply text-sm text-gray-600 mb-4;
}

.apple-template-meta {
  @apply flex items-center space-x-4 text-xs text-gray-500;
}

/* ===== Animations ===== */
@keyframes apple-modal-enter {
  from {
    opacity: 0;
    transform: scale(0.95) translateY(-20px);
  }
  to {
    opacity: 1;
    transform: scale(1) translateY(0);
  }
}

/* ===== Utilities ===== */
.scale-102 {
  transform: scale(1.02);
}

/* ===== Responsive Design ===== */
@media (max-width: 768px) {
  .apple-card,
  .apple-card-large {
    @apply p-4;
  }
  
  .apple-section-title {
    @apply text-2xl;
  }
  
  .apple-button-large {
    @apply px-4 py-2 text-sm;
  }
  
  .apple-nav-actions {
    @apply space-x-2;
  }
  
  .apple-nav-actions button {
    @apply px-3 py-1.5 text-sm;
  }
  
  .apple-empty-state {
    @apply py-12;
  }
  
  .apple-empty-title {
    @apply text-2xl;
  }
  
  .apple-empty-text {
    @apply text-base;
  }
}

/* ===== Accessibility ===== */
@media (prefers-reduced-motion: reduce) {
  * {
    animation-duration: 0.01ms !important;
    animation-iteration-count: 1 !important;
    transition-duration: 0.01ms !important;
  }
}

/* Focus states for accessibility */
button:focus,
.apple-template-card:focus {
  @apply outline-none ring-2 ring-blue-500 ring-offset-2;
}
</style> 