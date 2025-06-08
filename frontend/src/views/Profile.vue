<template>
  <div class="py-6">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <h1 class="text-2xl font-semibold text-gray-900">Profile</h1>
      
      <div class="mt-6 grid grid-cols-1 gap-6 lg:grid-cols-2">
        <!-- Personal Information -->
        <div class="card">
          <h3 class="text-lg font-medium text-gray-900">Personal Information</h3>
          <form class="mt-4 space-y-4">
            <div>
              <label for="name" class="block text-sm font-medium text-gray-700">Name</label>
              <input type="text" id="name" class="input-field mt-1" placeholder="Your name" />
            </div>
            <div>
              <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
              <input type="email" id="email" class="input-field mt-1" placeholder="your@email.com" />
            </div>
            <div>
              <label for="height" class="block text-sm font-medium text-gray-700">Height (cm)</label>
              <input type="number" id="height" class="input-field mt-1" placeholder="170" />
            </div>
            <div>
              <label for="weight" class="block text-sm font-medium text-gray-700">Weight (kg)</label>
              <input type="number" id="weight" class="input-field mt-1" placeholder="70" />
            </div>
            <button type="submit" class="btn-primary w-full">
              Save Changes
            </button>
          </form>
        </div>

        <!-- Fitness Goals -->
        <div class="card">
          <h3 class="text-lg font-medium text-gray-900">Fitness Goals</h3>
          <form class="mt-4 space-y-4">
            <div>
              <label for="goal" class="block text-sm font-medium text-gray-700">Primary Goal</label>
              <select id="goal" class="input-field mt-1">
                <option value="">Select a goal</option>
                <option value="strength">Build Strength</option>
                <option value="weight-loss">Lose Weight</option>
                <option value="muscle">Gain Muscle</option>
                <option value="fitness">Improve Fitness</option>
              </select>
            </div>
            <div>
              <label for="frequency" class="block text-sm font-medium text-gray-700">Workout Frequency</label>
              <select id="frequency" class="input-field mt-1">
                <option value="">Select frequency</option>
                <option value="2">2 times per week</option>
                <option value="3">3 times per week</option>
                <option value="4">4 times per week</option>
                <option value="5">5 times per week</option>
              </select>
            </div>
            <div>
              <label for="duration" class="block text-sm font-medium text-gray-700">Session Duration</label>
              <select id="duration" class="input-field mt-1">
                <option value="">Select duration</option>
                <option value="30">30 minutes</option>
                <option value="45">45 minutes</option>
                <option value="60">60 minutes</option>
                <option value="90">90 minutes</option>
              </select>
            </div>
            <button type="submit" class="btn-primary w-full">
              Update Goals
            </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// Component logic will be added here
</script> 