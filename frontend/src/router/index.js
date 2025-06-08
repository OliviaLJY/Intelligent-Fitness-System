import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

// Layouts
import DefaultLayout from '@/layouts/DefaultLayout.vue'
import AuthLayout from '@/layouts/AuthLayout.vue'

// Views
import Home from '@/views/Home.vue'
import Login from '@/views/auth/Login.vue'
import Register from '@/views/auth/Register.vue'
import Dashboard from '@/views/Dashboard.vue'
import Workout from '@/views/workout/Workout.vue'
import WorkoutHistory from '@/views/workout/WorkoutHistory.vue'
import ExerciseLibrary from '@/views/exercises/ExerciseLibrary.vue'
import Profile from '@/views/profile/Profile.vue'
import Settings from '@/views/settings/Settings.vue'
import Progress from '@/views/progress/Progress.vue'
import Community from '@/views/community/Community.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: DefaultLayout,
      children: [
        {
          path: '',
          name: 'home',
          component: Home,
          meta: { requiresAuth: false }
        },
        {
          path: 'dashboard',
          name: 'dashboard',
          component: Dashboard,
          meta: { requiresAuth: true }
        },
        {
          path: 'workout',
          name: 'workout',
          component: Workout,
          meta: { requiresAuth: true }
        },
        {
          path: 'workout/history',
          name: 'workout-history',
          component: WorkoutHistory,
          meta: { requiresAuth: true }
        },
        {
          path: 'exercises',
          name: 'exercise-library',
          component: ExerciseLibrary,
          meta: { requiresAuth: true }
        },
        {
          path: 'progress',
          name: 'progress',
          component: Progress,
          meta: { requiresAuth: true }
        },
        {
          path: 'community',
          name: 'community',
          component: Community,
          meta: { requiresAuth: true }
        },
        {
          path: 'profile',
          name: 'profile',
          component: Profile,
          meta: { requiresAuth: true }
        },
        {
          path: 'settings',
          name: 'settings',
          component: Settings,
          meta: { requiresAuth: true }
        }
      ]
    },
    {
      path: '/auth',
      component: AuthLayout,
      children: [
        {
          path: 'login',
          name: 'login',
          component: Login,
          meta: { requiresAuth: false }
        },
        {
          path: 'register',
          name: 'register',
          component: Register,
          meta: { requiresAuth: false }
        }
      ]
    }
  ]
})

// Navigation guard
router.beforeEach((to, from, next) => {
  const authStore = useAuthStore()
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth)

  if (requiresAuth && !authStore.isAuthenticated) {
    next({ name: 'login', query: { redirect: to.fullPath } })
  } else if (authStore.isAuthenticated && (to.name === 'login' || to.name === 'register')) {
    next({ name: 'dashboard' })
  } else {
    next()
  }
})

export default router 