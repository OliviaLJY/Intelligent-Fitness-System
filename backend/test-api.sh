#!/bin/bash

# Smart Fitness System Backend API Test Script
# Usage: ./test-api.sh [base_url]

BASE_URL=${1:-"http://localhost:8080"}
TOKEN=""

echo "🚀 Testing Smart Fitness System Backend API"
echo "Base URL: $BASE_URL"
echo "======================================"

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Test function
test_endpoint() {
    local method=$1
    local endpoint=$2
    local data=$3
    local description=$4
    local auth_header=""
    
    if [ ! -z "$TOKEN" ]; then
        auth_header="-H 'Authorization: Bearer $TOKEN'"
    fi
    
    echo -e "\n${BLUE}Testing: $description${NC}"
    echo -e "${YELLOW}$method $endpoint${NC}"
    
    if [ -z "$data" ]; then
        response=$(eval "curl -s -w '\n%{http_code}' -X $method '$BASE_URL$endpoint' $auth_header")
    else
        response=$(eval "curl -s -w '\n%{http_code}' -X $method '$BASE_URL$endpoint' -H 'Content-Type: application/json' -d '$data' $auth_header")
    fi
    
    # Split response and status code
    status_code=$(echo "$response" | tail -n1)
    body=$(echo "$response" | sed '$d')
    
    if [[ $status_code -ge 200 && $status_code -lt 300 ]]; then
        echo -e "${GREEN}✅ SUCCESS ($status_code)${NC}"
        echo "$body" | jq . 2>/dev/null || echo "$body"
    else
        echo -e "${RED}❌ FAILED ($status_code)${NC}"
        echo "$body"
    fi
}

# Check if application is running
echo -e "\n${BLUE}1. Health Check${NC}"
test_endpoint "GET" "/actuator/health" "" "Application Health Check"

echo -e "\n${BLUE}2. Authentication Tests${NC}"

# Test user registration
USER_DATA='{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123",
    "firstName": "Test",
    "lastName": "User"
}'

test_endpoint "POST" "/api/auth/register" "$USER_DATA" "User Registration"

# Test user login
LOGIN_DATA='{
    "username": "testuser",
    "password": "password123"
}'

echo -e "\n${YELLOW}Attempting login...${NC}"
login_response=$(curl -s -X POST "$BASE_URL/api/auth/login" \
    -H "Content-Type: application/json" \
    -d "$LOGIN_DATA")

echo "$login_response"

# Extract token if login successful
TOKEN=$(echo "$login_response" | jq -r '.token' 2>/dev/null)
if [ "$TOKEN" != "null" ] && [ ! -z "$TOKEN" ]; then
    echo -e "${GREEN}✅ Login successful, token extracted${NC}"
else
    echo -e "${YELLOW}⚠️  No token extracted, continuing without authentication${NC}"
    TOKEN=""
fi

echo -e "\n${BLUE}3. Workout API Tests${NC}"

# Test getting user workouts
test_endpoint "GET" "/api/workouts" "" "Get User Workouts"

# Test getting active workout
test_endpoint "GET" "/api/workouts/active" "" "Get Active Workout"

# Test creating workout session
WORKOUT_DATA='{
    "sessionName": "Test Workout",
    "plannedDurationMinutes": 60,
    "workoutType": "STRENGTH"
}'

test_endpoint "POST" "/api/workouts" "$WORKOUT_DATA" "Create Workout Session"

# Test getting workout templates
test_endpoint "GET" "/api/workouts/templates" "" "Get Workout Templates"

echo -e "\n${BLUE}4. WebSocket Tests${NC}"
echo -e "${YELLOW}WebSocket endpoints (manual testing required):${NC}"
echo "- ws://localhost:8080/ws/motion-analysis"
echo "- Connect using WebSocket client and send test frame data"

echo -e "\n${BLUE}5. Public Endpoints${NC}"

# Test public exercise endpoints (if any)
test_endpoint "GET" "/api/exercises/public" "" "Get Public Exercises"

echo -e "\n${GREEN}======================================"
echo "🏁 API Testing Complete!"
echo "======================================${NC}"

# Test summary
echo -e "\n${BLUE}📊 Test Summary:${NC}"
echo "- Health check tested"
echo "- Authentication flow tested"
echo "- Workout API endpoints tested"
echo "- Public endpoints tested"

echo -e "\n${YELLOW}💡 Next Steps:${NC}"
echo "1. Set up PostgreSQL database for full functionality"
echo "2. Test with real workout data"
echo "3. Test motion analysis WebSocket connections"
echo "4. Load test with multiple users" 