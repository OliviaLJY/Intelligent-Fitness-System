#!/bin/bash

echo "🚀 Starting Intelligent Fitness System (Optimized)"
echo "=============================================="

# Function to check if port is in use
check_port() {
    lsof -i :$1 > /dev/null 2>&1
}

# Kill existing processes
echo "📝 Cleaning up existing processes..."
pkill -f "gradle.*bootRun" > /dev/null 2>&1 || true
pkill -f "vite.*dev" > /dev/null 2>&1 || true

# Wait a moment for processes to stop
sleep 2

# Start backend
echo "🔧 Starting backend (optimized)..."
cd backend
SPRING_PROFILES_ACTIVE=test ./gradlew bootRun --quiet --daemon > /dev/null 2>&1 &
BACKEND_PID=$!

# Wait for backend to start
echo "⏳ Waiting for backend to start..."
for i in {1..30}; do
    if check_port 8080; then
        echo "✅ Backend started successfully on port 8080"
        break
    fi
    sleep 1
    if [ $i -eq 30 ]; then
        echo "❌ Backend failed to start"
        exit 1
    fi
done

# Start frontend
echo "🎨 Starting frontend..."
cd ../frontend
npm run dev > /dev/null 2>&1 &
FRONTEND_PID=$!

# Wait for frontend to start
echo "⏳ Waiting for frontend to start..."
for i in {1..20}; do
    if check_port 5173; then
        echo "✅ Frontend started successfully on port 5173"
        break
    fi
    sleep 1
    if [ $i -eq 20 ]; then
        echo "❌ Frontend failed to start"
        kill $FRONTEND_PID 2>/dev/null || true
        exit 1
    fi
done

echo ""
echo "🎉 System started successfully!"
echo "================================"
echo "📱 Frontend: http://localhost:5173"
echo "🔧 Backend:  http://localhost:8080"
echo "❤️  Health:  http://localhost:8080/actuator/health"
echo ""
echo "Press Ctrl+C to stop all services"

# Function to cleanup on exit
cleanup() {
    echo ""
    echo "🛑 Stopping services..."
    kill $BACKEND_PID 2>/dev/null || true
    kill $FRONTEND_PID 2>/dev/null || true
    pkill -f "gradle.*bootRun" > /dev/null 2>&1 || true
    pkill -f "vite.*dev" > /dev/null 2>&1 || true
    echo "✅ All services stopped"
    exit 0
}

# Trap Ctrl+C
trap cleanup SIGINT SIGTERM

# Keep script running
wait 