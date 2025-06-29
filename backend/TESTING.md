# 🧪 Smart Fitness System Backend Testing Guide

This guide will help you test the Smart Fitness System backend thoroughly.

## 📋 Prerequisites

- Java 11+ installed
- curl or Postman for API testing  
- (Optional) jq for JSON formatting
- (Optional) WebSocket client for real-time testing

## 🚀 Quick Start Testing

### 1. Start the Application (Test Mode)

```bash
# Using H2 in-memory database for testing
./gradlew bootRun --args='--spring.profiles.active=test'

# Or with environment variable
SPRING_PROFILES_ACTIVE=test ./gradlew bootRun
```

### 2. Run Automated Test Script

```bash
# Make script executable
chmod +x test-api.sh

# Run all API tests
./test-api.sh

# Test against different URL
./test-api.sh http://localhost:8080
```

## 🔧 Testing Methods

### Method 1: Command Line (curl)

#### Health Check
```bash
curl http://localhost:8080/actuator/health
```

#### User Registration
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com", 
    "password": "password123",
    "firstName": "Test",
    "lastName": "User"
  }'
```

#### User Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123"
  }'
```

#### Create Workout (with auth token)
```bash
TOKEN="your_jwt_token_here"
curl -X POST http://localhost:8080/api/workouts \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "sessionName": "Morning Workout",
    "plannedDurationMinutes": 60,
    "workoutType": "STRENGTH"
  }'
```

### Method 2: Postman

1. Import the collection: `Smart_Fitness_API.postman_collection.json`
2. Set base URL variable to `http://localhost:8080`
3. Run tests in order:
   - Health Check
   - Register User
   - Login User (saves token automatically)
   - Test authenticated endpoints

### Method 3: H2 Database Console

Access the H2 console to inspect data:
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (empty)

## 🧩 Test Scenarios

### Scenario 1: User Registration & Authentication Flow
1. Register new user
2. Login with credentials  
3. Access protected endpoints
4. Test invalid credentials

### Scenario 2: Workout Management
1. Create workout session
2. Start workout
3. Get active workout
4. Complete workout
5. Create template from workout

### Scenario 3: Error Handling
1. Test invalid endpoints (404)
2. Test unauthorized access (401)
3. Test invalid data formats (400)
4. Test missing required fields

### Scenario 4: WebSocket Testing

```javascript
// Connect to WebSocket endpoint
const ws = new WebSocket('ws://localhost:8080/ws/motion-analysis');

// Send test frame data
ws.send(JSON.stringify({
  type: 'FRAME_DATA',
  sessionId: 1,
  frameData: 'base64_encoded_frame_data',
  timestamp: Date.now()
}));
```

## 📊 Expected Responses

### Successful User Registration
```json
{
  "message": "User registered successfully",
  "user": {
    "id": 1,
    "username": "testuser",
    "email": "test@example.com",
    "firstName": "Test",
    "lastName": "User"
  }
}
```

### Successful Login
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": 1,
    "username": "testuser",
    "email": "test@example.com"
  }
}
```

### Health Check Response
```json
{
  "status": "UP",
  "components": {
    "diskSpace": {
      "status": "UP"
    },
    "db": {
      "status": "UP"
    }
  }
}
```

## ⚠️ Common Issues & Solutions

### Issue: Application won't start
**Solution:** Check if port 8080 is available
```bash
lsof -i :8080
# Kill process if needed
kill -9 <PID>
```

### Issue: Database connection errors
**Solution:** Use test profile with H2
```bash
SPRING_PROFILES_ACTIVE=test ./gradlew bootRun
```

### Issue: JWT token errors
**Solution:** Check token format and expiration
- Ensure Bearer prefix in Authorization header
- Check token is not expired (24h default)

### Issue: CORS errors in browser
**Solution:** Check WebSocket configuration
- Verify allowed origins in application-test.yml
- Use proper WebSocket URL format

## 🔍 Debugging Tips

### Enable Debug Logging
Add to application-test.yml:
```yaml
logging:
  level:
    com.fitness: DEBUG
    org.springframework.security: DEBUG
```

### Monitor Database
1. Access H2 console: http://localhost:8080/h2-console
2. Check table creation and data
3. View SQL queries in logs

### Check Application Metrics
```bash
curl http://localhost:8080/actuator/metrics
curl http://localhost:8080/actuator/metrics/http.server.requests
```

## 🚀 Performance Testing

### Load Testing with curl
```bash
# Simple load test
for i in {1..100}; do
  curl -s http://localhost:8080/actuator/health > /dev/null &
done
wait
```

### Memory Usage
```bash
curl http://localhost:8080/actuator/metrics/jvm.memory.used
```

## 📝 Test Checklist

- [ ] ✅ Application starts successfully
- [ ] ✅ Health check returns UP status  
- [ ] ✅ User registration works
- [ ] ✅ User login returns valid JWT
- [ ] ✅ Protected endpoints require authentication
- [ ] ✅ Workout CRUD operations work
- [ ] ✅ WebSocket connections establish
- [ ] ✅ Database operations complete
- [ ] ✅ Error handling works correctly
- [ ] ✅ CORS configuration allows frontend

## 🎯 Next Steps

1. **Production Database**: Replace H2 with PostgreSQL
2. **Integration Tests**: Add comprehensive test suite
3. **Load Testing**: Use JMeter or Artillery for load testing
4. **Monitoring**: Set up application monitoring
5. **CI/CD**: Automate testing in build pipeline

---

For more detailed API documentation, check the OpenAPI specification at:
http://localhost:8080/swagger-ui.html (if Swagger is configured) 