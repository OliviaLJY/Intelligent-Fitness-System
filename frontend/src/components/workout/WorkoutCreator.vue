<template>
  <div class="bg-white shadow rounded-lg overflow-hidden">
    <div class="p-6 border-b border-gray-200">
      <h2 class="text-2xl font-bold text-gray-900">Create Workout</h2>
      <p class="mt-1 text-sm text-gray-500">Design your custom workout routine</p>
    </div>

    <div class="p-6 space-y-6">
      <!-- Workout Details -->
      <div class="grid grid-cols-1 gap-6 sm:grid-cols-2">
        <div>
          <label for="workout-name" class="block text-sm font-medium text-gray-700">Workout Name</label>
          <input
            type="text"
            id="workout-name"
            v-model="workout.name"
            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-primary-500 focus:border-primary-500 sm:text-sm"
            placeholder="e.g., Upper Body Strength"
          />
        </div>
        <div>
          <label for="workout-type" class="block text-sm font-medium text-gray-700">Workout Type</label>
          <select
            id="workout-type"
            v-model="workout.type"
            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-primary-500 focus:border-primary-500 sm:text-sm"
          >
            <option value="strength">Strength</option>
            <option value="cardio">Cardio</option>
            <option value="flexibility">Flexibility</option>
            <option value="hiit">HIIT</option>
            <option value="custom">Custom</option>
          </select>
        </div>
      </div>

      <!-- Exercise List -->
      <div>
        <div class="flex justify-between items-center mb-4">
          <h3 class="text-lg font-medium text-gray-900">Exercises</h3>
          <button
            @click="addExercise"
            class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-primary-600 hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
          >
            <svg class="-ml-1 mr-2 h-5 w-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6" />
            </svg>
            Add Exercise
          </button>
        </div>

        <div class="space-y-4">
          <div
            v-for="(exercise, index) in workout.exercises"
            :key="index"
            class="bg-gray-50 rounded-lg p-4"
          >
            <div class="flex items-center justify-between">
              <div class="flex-1">
                <div class="flex items-center space-x-4">
                  <div class="flex-shrink-0">
                    <div class="h-10 w-10 rounded-full bg-primary-100 flex items-center justify-center">
                      <svg class="h-6 w-6 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
                      </svg>
                    </div>
                  </div>
                  <div class="flex-1">
                    <select
                      v-model="exercise.id"
                      class="block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-primary-500 focus:border-primary-500 sm:text-sm"
                    >
                      <option value="">Select Exercise</option>
                      <option v-for="ex in availableExercises" :key="ex.id" :value="ex.id">
                        {{ ex.name }}
                      </option>
                    </select>
                  </div>
                </div>
              </div>
              <button
                @click="removeExercise(index)"
                class="ml-4 text-gray-400 hover:text-gray-500"
              >
                <svg class="h-5 w-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                </svg>
              </button>
            </div>

            <div class="mt-4 grid grid-cols-3 gap-4">
              <div>
                <label class="block text-sm font-medium text-gray-700">Sets</label>
                <input
                  type="number"
                  v-model="exercise.sets"
                  min="1"
                  class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-primary-500 focus:border-primary-500 sm:text-sm"
                />
              </div>
              <div>
                <label class="block text-sm font-medium text-gray-700">Reps</label>
                <input
                  type="number"
                  v-model="exercise.reps"
                  min="1"
                  class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-primary-500 focus:border-primary-500 sm:text-sm"
                />
              </div>
              <div>
                <label class="block text-sm font-medium text-gray-700">Rest (sec)</label>
                <input
                  type="number"
                  v-model="exercise.rest"
                  min="0"
                  step="5"
                  class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-primary-500 focus:border-primary-500 sm:text-sm"
                />
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Workout Notes -->
      <div>
        <label for="workout-notes" class="block text-sm font-medium text-gray-700">Notes</label>
        <textarea
          id="workout-notes"
          v-model="workout.notes"
          rows="3"
          class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-primary-500 focus:border-primary-500 sm:text-sm"
          placeholder="Add any notes or instructions for this workout..."
        ></textarea>
      </div>

      <!-- Action Buttons -->
      <div class="flex justify-end space-x-3">
        <button
          @click="saveAsTemplate"
          class="inline-flex items-center px-4 py-2 border border-gray-300 shadow-sm text-sm font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
        >
          Save as Template
        </button>
        <button
          @click="saveWorkout"
          class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-primary-600 hover:bg-primary-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary-500"
        >
          Save Workout
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'

const workout = reactive({
  name: '',
  type: 'strength',
  exercises: [],
  notes: ''
})

const availableExercises = ref([
  { id: 1, name: 'Push-ups' },
  { id: 2, name: 'Squats' },
  { id: 3, name: 'Pull-ups' },
  // Add more exercises here
])

const addExercise = () => {
  workout.exercises.push({
    id: '',
    sets: 3,
    reps: 12,
    rest: 60
  })
}

const removeExercise = (index) => {
  workout.exercises.splice(index, 1)
}

const saveWorkout = () => {
  // Validate workout
  if (!workout.name || workout.exercises.length === 0) {
    alert('Please fill in all required fields')
    return
  }

  // Emit save event
  emit('save', { ...workout })
}

const saveAsTemplate = () => {
  // Validate workout
  if (!workout.name || workout.exercises.length === 0) {
    alert('Please fill in all required fields')
    return
  }

  // Emit save template event
  emit('save-template', { ...workout })
}

const emit = defineEmits(['save', 'save-template'])
</script> 