#!/bin/bash

# =============================================================================
# 🏋️ INTELLIGENT FITNESS SYSTEM - STARTUP SCRIPT
# =============================================================================
# This script will help you start your fitness application every time!
# 
# WHAT THIS SCRIPT DOES:
# 1. Checks all prerequisites (Java, Node.js, Database)
# 2. Starts the backend (Spring Boot + H2 Database)
# 3. Starts the frontend (Vue.js application)
# 4. Shows you all the URLs to access your app
# 5. Provides helpful status information
#
# HOW TO USE:
# 1. Open Terminal
# 2. Navigate to your project: cd /path/to/Intelligent-Fitness-System
# 3. Run this script: ./run-fitness-system.sh
# 4. Wait for "🎉 SYSTEM READY!" message
# 5. Open browser to http://localhost:5173 (or the port shown)
#
# HOW TO STOP:
# - Press Ctrl+C in this terminal window
# - OR run: pkill -f "bootRun|vite"
# =============================================================================

# Colors for pretty output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
PURPLE='\033[0;35m'
CYAN='\033[0;36m'
WHITE='\033[1;37m'
NC='\033[0m' # No Color

# Function to print colored messages
print_header() { echo -e "${PURPLE}$1${NC}"; }
print_success() { echo -e "${GREEN}✅ $1${NC}"; }
print_warning() { echo -e "${YELLOW}⚠️  $1${NC}"; }
print_error() { echo -e "${RED}❌ $1${NC}"; }
print_info() { echo -e "${BLUE}ℹ️  $1${NC}"; }
print_step() { echo -e "${CYAN}🔧 $1${NC}"; }

# Function to check if port is in use
check_port() {
    lsof -i :$1 > /dev/null 2>&1
}

# Function to wait for service startup
wait_for_service() {
    local port=$1
    local service_name=$2
    local max_wait=$3
    
    echo -e "${YELLOW}⏳ Waiting for $service_name to start on port $port...${NC}"
    
    for i in $(seq 1 $max_wait); do
        if check_port $port; then
            print_success "$service_name is running on port $port!"
            return 0
        fi
        
        if [ $((i % 5)) -eq 0 ]; then
            echo -e "${YELLOW}   Still waiting... ($i/$max_wait seconds)${NC}"
        fi
        
        sleep 1
    done
    
    print_error "$service_name failed to start after $max_wait seconds"
    return 1
}

# Function to cleanup on exit
cleanup() {
    echo ""
    print_header "🛑 STOPPING FITNESS SYSTEM..."
    kill $BACKEND_PID 2>/dev/null || true
    kill $FRONTEND_PID 2>/dev/null || true
    pkill -f "gradle.*bootRun" > /dev/null 2>&1 || true
    pkill -f "vite.*dev" > /dev/null 2>&1 || true
    print_success "All services stopped. Thanks for using the Fitness System!"
    exit 0
}

# Trap Ctrl+C
trap cleanup SIGINT SIGTERM

# =============================================================================
# MAIN SCRIPT START
# =============================================================================

clear
echo -e "${PURPLE}"
echo "🏋️‍♀️🏋️‍♂️🏋️‍♀️🏋️‍♂️🏋️‍♀️🏋️‍♂️🏋️‍♀️🏋️‍♂️🏋️‍♀️🏋️‍♂️🏋️‍♀️🏋️‍♂️🏋️‍♀️🏋️‍♂️🏋️‍♀️🏋️‍♂️"
echo "            INTELLIGENT FITNESS SYSTEM"
echo "              🚀 STARTUP SCRIPT 🚀"
echo "🏋️‍♂️🏋️‍♀️🏋️‍♂️🏋️‍♀️🏋️‍♂️🏋️‍♀️🏋️‍♂️🏋️‍♀️🏋️‍♂️🏋️‍♀️🏋️‍♂️🏋️‍♀️🏋️‍♂️🏋️‍♀️🏋️‍♂️🏋️‍♀️"
echo -e "${NC}"
echo ""

# =============================================================================
# STEP 1: CLEANUP ANY EXISTING PROCESSES
# =============================================================================
print_header "STEP 1: Cleaning up any existing processes..."
pkill -f "gradle.*bootRun" > /dev/null 2>&1 || true
pkill -f "vite.*dev" > /dev/null 2>&1 || true
sleep 2
print_success "Cleanup complete"
echo ""

# =============================================================================
# STEP 2: CHECK PREREQUISITES
# =============================================================================
print_header "STEP 2: Checking prerequisites..."

# Check if we're in the right directory
if [ ! -f "backend/build.gradle" ] || [ ! -f "frontend/package.json" ]; then
    print_error "❌ You're not in the Intelligent-Fitness-System directory!"
    echo ""
    print_info "Please run this script from your project root directory:"
    echo -e "${WHITE}cd /path/to/Intelligent-Fitness-System${NC}"
    echo -e "${WHITE}./run-fitness-system.sh${NC}"
    exit 1
fi

# Check Java
JAVA_VERSION=$(java -version 2>&1 | grep "version" | head -1 | cut -d'"' -f2 | cut -d'.' -f1)
if [ "$JAVA_VERSION" -ge 11 ]; then
    print_success "Java $JAVA_VERSION detected"
else
    print_error "Java 11+ required, found Java $JAVA_VERSION"
    print_info "Please install Java 11 or higher: brew install openjdk@17"
    exit 1
fi

# Check Node.js
if command -v node > /dev/null; then
    NODE_VERSION=$(node --version)
    print_success "Node.js $NODE_VERSION detected"
else
    print_error "Node.js not found"
    print_info "Please install Node.js: brew install node"
    exit 1
fi

# Check if frontend dependencies are installed
if [ ! -d "frontend/node_modules" ]; then
    print_step "Installing frontend dependencies..."
    cd frontend && npm install && cd ..
    print_success "Frontend dependencies installed"
fi

print_success "All prerequisites checked!"
echo ""

# =============================================================================
# STEP 3: START BACKEND
# =============================================================================
print_header "STEP 3: Starting Backend (Spring Boot + H2 Database)..."
print_info "The backend provides:"
print_info "• REST API for fitness data"
print_info "• User authentication & management"
print_info "• Workout and exercise tracking"
print_info "• Real-time WebSocket connections"
print_info "• Health monitoring endpoints"
echo ""

cd backend

# Start backend with proper configuration
print_step "Starting Spring Boot application..."
SPRING_PROFILES_ACTIVE=test \
JAVA_LIBRARY_PATH="/opt/homebrew/lib:/usr/local/lib" \
OPENCV_DISABLED=false \
./gradlew bootRun --console=plain > ../logs/backend.log 2>&1 &

BACKEND_PID=$!
print_info "Backend process started with PID: $BACKEND_PID"

# Wait for backend to start
if wait_for_service 8080 "Backend" 60; then
    # Test backend health
    sleep 3
    HEALTH_RESPONSE=$(curl -s http://localhost:8080/actuator/health 2>/dev/null)
    if echo "$HEALTH_RESPONSE" | grep -q '"status":"UP"'; then
        print_success "Backend health check passed!"
    else
        print_warning "Backend started but health check unclear"
    fi
else
    print_error "Backend failed to start"
    print_info "Check logs: tail -f logs/backend.log"
    exit 1
fi

cd ..
echo ""

# =============================================================================
# STEP 4: START FRONTEND
# =============================================================================
print_header "STEP 4: Starting Frontend (Vue.js Application)..."
print_info "The frontend provides:"
print_info "• Modern web interface for fitness tracking"
print_info "• Workout creation and management"
print_info "• Exercise library and tutorials"
print_info "• Progress tracking and analytics"
print_info "• Real-time updates and notifications"
echo ""

cd frontend

print_step "Starting Vue.js development server..."
npm run dev > ../logs/frontend.log 2>&1 &
FRONTEND_PID=$!
print_info "Frontend process started with PID: $FRONTEND_PID"

# Wait for frontend to start (check multiple possible ports)
FRONTEND_PORT=""
for port in 5173 5174 5175 5176 5177; do
    if wait_for_service $port "Frontend" 30; then
        FRONTEND_PORT=$port
        break
    fi
done

if [ -z "$FRONTEND_PORT" ]; then
    print_error "Frontend failed to start on any port"
    print_info "Check logs: tail -f logs/frontend.log"
    exit 1
fi

cd ..
echo ""

# =============================================================================
# STEP 5: SYSTEM STATUS & ACCESS INFORMATION
# =============================================================================
print_header "STEP 5: System Status & Access Information"

# Test system endpoints
echo ""
print_step "Testing system endpoints..."

# Backend health
HEALTH_STATUS=$(curl -s http://localhost:8080/actuator/health | grep -o '"status":"[^"]*"' | cut -d'"' -f4)
if [ "$HEALTH_STATUS" = "UP" ]; then
    print_success "Backend API: Healthy"
else
    print_warning "Backend API: Status unclear"
fi

# OpenCV status
OPENCV_STATUS=$(curl -s http://localhost:8080/test/opencv 2>/dev/null | grep -o '"success":[^,]*' | cut -d':' -f2)
if [ "$OPENCV_STATUS" = "true" ]; then
    print_success "OpenCV: Motion analysis enabled"
else
    print_warning "OpenCV: Motion analysis disabled (system works fine without it)"
fi

# Frontend connectivity
if curl -s http://localhost:$FRONTEND_PORT > /dev/null 2>&1; then
    print_success "Frontend: Accessible"
else
    print_warning "Frontend: Connection unclear"
fi

echo ""

# =============================================================================
# SUCCESS MESSAGE & INSTRUCTIONS
# =============================================================================
print_header "🎉 FITNESS SYSTEM IS READY! 🎉"
echo ""
echo -e "${WHITE}🌐 ACCESS YOUR APPLICATION:${NC}"
echo -e "${GREEN}   📱 Main App:        http://localhost:$FRONTEND_PORT${NC}"
echo -e "${BLUE}   🔧 Backend API:     http://localhost:8080${NC}"
echo -e "${BLUE}   ❤️  Health Check:    http://localhost:8080/actuator/health${NC}"
echo -e "${BLUE}   📊 System Status:   http://localhost:8080/test/health${NC}"
echo ""

echo -e "${WHITE}🎯 WHAT YOU CAN DO:${NC}"
echo -e "${GREEN}   • Create and manage workout routines${NC}"
echo -e "${GREEN}   • Track exercise progress and performance${NC}"
echo -e "${GREEN}   • Browse comprehensive exercise library${NC}"
echo -e "${GREEN}   • Monitor fitness goals and achievements${NC}"
echo -e "${GREEN}   • View detailed analytics and reports${NC}"
echo ""

echo -e "${WHITE}📝 HELPFUL COMMANDS:${NC}"
echo -e "${CYAN}   • Check logs:        tail -f logs/backend.log${NC}"
echo -e "${CYAN}   • Check frontend:    tail -f logs/frontend.log${NC}"
echo -e "${CYAN}   • Test backend:      curl http://localhost:8080/actuator/health${NC}"
echo -e "${CYAN}   • Stop system:       Press Ctrl+C here${NC}"
echo ""

echo -e "${WHITE}🚀 GETTING STARTED:${NC}"
echo -e "${YELLOW}   1. Open your browser to: http://localhost:$FRONTEND_PORT${NC}"
echo -e "${YELLOW}   2. Create a new account or log in${NC}"
echo -e "${YELLOW}   3. Start creating your first workout!${NC}"
echo ""

print_warning "Press Ctrl+C in this terminal to stop all services"
echo ""

# Create logs directory if it doesn't exist
mkdir -p logs

# =============================================================================
# KEEP SCRIPT RUNNING
# =============================================================================
print_header "📊 LIVE SYSTEM MONITORING"
echo -e "${CYAN}Backend PID: $BACKEND_PID | Frontend PID: $FRONTEND_PID${NC}"
echo -e "${CYAN}Logs: ./logs/backend.log | ./logs/frontend.log${NC}"
echo ""

# Monitor system status
while true; do
    sleep 30
    
    # Check if processes are still running
    if ! kill -0 $BACKEND_PID 2>/dev/null; then
        print_error "Backend process died unexpectedly!"
        print_info "Check logs: tail -f logs/backend.log"
        break
    fi
    
    if ! kill -0 $FRONTEND_PID 2>/dev/null; then
        print_error "Frontend process died unexpectedly!"
        print_info "Check logs: tail -f logs/frontend.log"
        break
    fi
    
    # Quick health check
    if curl -s http://localhost:8080/actuator/health > /dev/null 2>&1; then
        echo -e "${GREEN}$(date '+%H:%M:%S') - System healthy ✅${NC}"
    else
        print_warning "$(date '+%H:%M:%S') - Backend health check failed"
    fi
done

# If we get here, something went wrong
cleanup 