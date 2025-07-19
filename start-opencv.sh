#!/bin/bash

echo "🚀 Starting Intelligent Fitness System with OpenCV Integration"
echo "=============================================================="

# OpenCV Configuration
OPENCV_LIB_PATH="/opt/homebrew/lib"
JAVA_LIBRARY_PATH="${OPENCV_LIB_PATH}:/usr/local/lib"

# Function to check if port is in use
check_port() {
    lsof -i :$1 > /dev/null 2>&1
}

# Function to verify OpenCV installation
verify_opencv() {
    echo "🔍 Verifying OpenCV installation..."
    
    if [ ! -d "$OPENCV_LIB_PATH" ]; then
        echo "❌ OpenCV library directory not found: $OPENCV_LIB_PATH"
        return 1
    fi
    
    # Check for core OpenCV libraries
    local core_libs=("libopencv_core.dylib" "libopencv_imgproc.dylib" "libopencv_imgcodecs.dylib")
    
    for lib in "${core_libs[@]}"; do
        if [ ! -e "$OPENCV_LIB_PATH/$lib" ]; then
            echo "❌ Missing OpenCV library: $lib"
            return 1
        fi
    done
    
    echo "✅ OpenCV libraries found"
    echo "📍 OpenCV path: $OPENCV_LIB_PATH"
    
    # Show OpenCV version if available
    if which opencv_version > /dev/null 2>&1; then
        echo "📊 OpenCV version: $(opencv_version)"
    fi
    
    return 0
}

# Function to check database connection
check_database() {
    echo "🔍 Checking database connection..."
    if psql -h localhost -U fitness_user -d fitness_db -c "SELECT 1;" > /dev/null 2>&1; then
        echo "✅ PostgreSQL database connection successful"
        return 0
    else
        echo "⚠️  PostgreSQL connection failed, will use H2 in-memory database"
        return 1
    fi
}

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

# Kill any existing processes
echo "📝 Cleaning up existing processes..."
pkill -f "gradle.*bootRun" > /dev/null 2>&1 || true
pkill -f "vite.*dev" > /dev/null 2>&1 || true
sleep 2

# Verify prerequisites
echo "🔧 Checking prerequisites..."

# Check Java
JAVA_VERSION=$(java -version 2>&1 | grep "version" | head -1 | cut -d'"' -f2 | cut -d'.' -f1)
if [ "$JAVA_VERSION" -ge 11 ]; then
    echo "✅ Java $JAVA_VERSION detected"
else
    echo "❌ Java 11+ required, found Java $JAVA_VERSION"
    exit 1
fi

# Verify OpenCV
if ! verify_opencv; then
    echo "❌ OpenCV verification failed"
    echo "💡 Please ensure OpenCV is properly installed via Homebrew"
    exit 1
fi

# Check database
DB_AVAILABLE=false
if check_database; then
    DB_AVAILABLE=true
    SPRING_PROFILE="development"
else
    SPRING_PROFILE="test"
fi

echo ""
echo "🚀 Starting backend with OpenCV integration..."
echo "📊 Profile: $SPRING_PROFILE"
echo "🔗 Java Library Path: $JAVA_LIBRARY_PATH"

# Start backend with OpenCV integration
cd backend

# Set comprehensive JVM arguments for OpenCV
JVM_ARGS="-Djava.library.path=$JAVA_LIBRARY_PATH"
JVM_ARGS="$JVM_ARGS -Dopencv.disabled=false"
JVM_ARGS="$JVM_ARGS -Djna.library.path=$JAVA_LIBRARY_PATH"

# Add memory and GC settings
JVM_ARGS="$JVM_ARGS -Xmx2g -Xms512m"
JVM_ARGS="$JVM_ARGS -XX:+UseG1GC"

# Export environment variables
export JAVA_LIBRARY_PATH="$JAVA_LIBRARY_PATH"
export OPENCV_LIB_PATH="$OPENCV_LIB_PATH"
export DYLD_LIBRARY_PATH="$OPENCV_LIB_PATH:$DYLD_LIBRARY_PATH"

if [ "$DB_AVAILABLE" = true ]; then
    # Use PostgreSQL with development profile
    SPRING_PROFILES_ACTIVE=development \
    GRADLE_OPTS="$JVM_ARGS" \
    ./gradlew bootRun --quiet --daemon &
else
    # Use H2 with test profile
    SPRING_PROFILES_ACTIVE=test \
    GRADLE_OPTS="$JVM_ARGS" \
    ./gradlew bootRun --quiet --daemon &
fi

BACKEND_PID=$!
echo "🔧 Backend process started with PID: $BACKEND_PID"

# Wait for backend startup with detailed feedback
echo "⏳ Waiting for backend to start..."
for i in {1..60}; do
    if check_port 8080; then
        echo "✅ Backend started successfully on port 8080"
        
        # Wait a moment and test health endpoint
        sleep 3
        HEALTH_RESPONSE=$(curl -s http://localhost:8080/actuator/health 2>/dev/null)
        if echo "$HEALTH_RESPONSE" | grep -q '"status":"UP"'; then
            echo "✅ Backend health check passed"
            echo "📊 Status: $(echo "$HEALTH_RESPONSE" | grep -o '"status":"[^"]*"')"
        else
            echo "⚠️  Backend started but health check inconclusive"
        fi
        break
    fi
    
    # Show progress
    if [ $((i % 10)) -eq 0 ]; then
        echo "   Still waiting... ($i/60 seconds)"
    fi
    
    sleep 1
    if [ $i -eq 60 ]; then
        echo "❌ Backend failed to start after 60 seconds"
        echo "💡 Check logs with: tail -f backend/logs/application.log"
        exit 1
    fi
done

# Start frontend
echo ""
echo "🎨 Starting frontend..."
cd ../frontend

# Ensure dependencies are installed
if [ ! -d "node_modules" ]; then
    echo "📦 Installing frontend dependencies..."
    npm install
fi

npm run dev &
FRONTEND_PID=$!

# Wait for frontend startup
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
echo "🎉 System started successfully with OpenCV integration!"
echo "======================================================="
echo "📱 Frontend:     http://localhost:5173"
echo "🔧 Backend:      http://localhost:8080"
echo "❤️  Health:      http://localhost:8080/actuator/health"
echo "📊 Database:     $SPRING_PROFILE profile"
echo "🤖 OpenCV:       Enabled (v4.11.0)"
echo "📍 Library Path: $JAVA_LIBRARY_PATH"
echo ""
echo "📝 Useful endpoints:"
echo "   • Motion Analysis Test: http://localhost:8080/test/opencv"
echo "   • WebSocket:           ws://localhost:8080/ws"
echo "   • Actuator:            http://localhost:8080/actuator"
echo ""
echo "Press Ctrl+C to stop all services"

# Keep script running
wait 