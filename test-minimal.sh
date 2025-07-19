#!/bin/bash

echo "🔍 Minimal Backend Test"
echo "======================"

# Kill any existing processes
pkill -f bootRun > /dev/null 2>&1 || true
sleep 2

echo "📝 Java Version Check:"
java -version

echo ""
echo "📊 Starting backend with minimal profile..."
cd backend

# Start with H2 database and disabled OpenCV for debugging
SPRING_PROFILES_ACTIVE=test \
OPENCV_DISABLED=true \
LOG_LEVEL=DEBUG \
./gradlew bootRun --console=plain --no-daemon --info &

BACKEND_PID=$!
echo "🔧 Backend process started with PID: $BACKEND_PID"

# Wait and check
echo "⏳ Waiting for startup..."
for i in {1..30}; do
    echo "Checking... ($i/30)"
    if netstat -an | grep ":8080.*LISTEN" > /dev/null; then
        echo "✅ Backend is listening on port 8080!"
        curl -s http://localhost:8080/actuator/health || echo "Health check failed"
        break
    fi
    sleep 2
done

echo "🔍 Final status check:"
ps aux | grep bootRun | grep -v grep
netstat -an | grep 8080

echo ""
echo "Press Ctrl+C to stop"
wait $BACKEND_PID 