#!/bin/bash

echo "🚀 Starting Intelligent Fitness System (Enhanced with OpenCV)"
echo "==========================================================="

# Function to check if port is in use
check_port() {
    lsof -i :$1 > /dev/null 2>&1
}

# Function to check Java version
check_java() {
    JAVA_VERSION=$(java -version 2>&1 | grep "version" | head -1 | cut -d'"' -f2 | cut -d'.' -f1)
    if [ "$JAVA_VERSION" -ge 11 ]; then
        echo "✅ Java $JAVA_VERSION detected"
        return 0
    else
        echo "❌ Java 11+ required, found Java $JAVA_VERSION"
        return 1
    fi
}

# Function to check database connection
check_database() {
    echo "🔍 Checking database connection..."
    if psql -h localhost -U fitness_user -d fitness_db -c "SELECT 1;" > /dev/null 2>&1; then
        echo "✅ Database connection successful"
        return 0
    else
        echo "⚠️  Database connection failed, but continuing with H2 in-memory database"
        return 1
    fi
}

# Kill existing processes
echo "📝 Cleaning up existing processes..."
pkill -f "gradle.*bootRun" > /dev/null 2>&1 || true
pkill -f "vite.*dev" > /dev/null 2>&1 || true

# Wait a moment for processes to stop
sleep 2

# Check prerequisites
check_java || exit 1

# Check database (optional - will fall back to H2)
DB_AVAILABLE=false
if check_database; then
    DB_AVAILABLE=true
    SPRING_PROFILE="development"
else
    SPRING_PROFILE="test"
fi

echo "🔧 Starting backend with profile: $SPRING_PROFILE"

# Start backend with appropriate profile
cd backend

if [ "$DB_AVAILABLE" = true ]; then
    # Use PostgreSQL with development profile
    SPRING_PROFILES_ACTIVE=development ./gradlew bootRun --quiet --daemon &
else
    # Use H2 with test profile but enable OpenCV
    SPRING_PROFILES_ACTIVE=test OPENCV_DISABLED=false ./gradlew bootRun --quiet --daemon &
fi

BACKEND_PID=$!

# Wait for backend to start with more detailed feedback
echo "⏳ Waiting for backend to start..."
for i in {1..60}; do
    if check_port 8080; then
        echo "✅ Backend started successfully on port 8080"
        
        # Test health endpoint
        sleep 2
        if curl -s http://localhost:8080/actuator/health > /dev/null 2>&1; then
            echo "✅ Backend health check passed"
        else
            echo "⚠️  Backend started but health check failed"
        fi
        break
    fi
    
    # Show progress dots
    if [ $((i % 5)) -eq 0 ]; then
        echo "   Still waiting... ($i/60 seconds)"
    fi
    
    sleep 1
    if [ $i -eq 60 ]; then
        echo "❌ Backend failed to start after 60 seconds"
        echo "💡 Try checking the logs with: tail -f backend/logs/application.log"
        exit 1
    fi
done

# Start frontend
echo "🎨 Starting frontend..."
cd ../frontend

# Check if node_modules exists
if [ ! -d "node_modules" ]; then
    echo "📦 Installing frontend dependencies..."
    npm install
fi

npm run dev &
FRONTEND_PID=$!

# Wait for frontend to start
echo "⏳ Waiting for frontend to start..."
for i in {1..30}; do
    if check_port 5173; then
        echo "✅ Frontend started successfully on port 5173"
        break
    fi
    sleep 1
    if [ $i -eq 30 ]; then
        echo "❌ Frontend failed to start"
        kill $FRONTEND_PID 2>/dev/null || true
        exit 1
    fi
done

echo ""
echo "🎉 System started successfully!"
echo "================================"
echo "📱 Frontend:    http://localhost:5173"
echo "🔧 Backend:     http://localhost:8080"
echo "❤️  Health:     http://localhost:8080/actuator/health"
echo "📊 Database:    $SPRING_PROFILE profile"
echo "🤖 OpenCV:      ${OPENCV_DISABLED:-enabled}"
echo ""
echo "📝 Useful endpoints:"
echo "   • API Docs:  http://localhost:8080/swagger-ui.html (if enabled)"
echo "   • Actuator:  http://localhost:8080/actuator"
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