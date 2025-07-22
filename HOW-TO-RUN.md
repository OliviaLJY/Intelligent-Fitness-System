# 🏋️ How to Run Your Intelligent Fitness System

## 🚀 **QUICK START** (Most people should use this)

1. **Open Terminal** and navigate to your project:
   ```bash
   cd /Users/lijiayu/Desktop/Intelligent-Fitness-System
   ```

2. **Run the startup script**:
   ```bash
   ./run-fitness-system.sh
   ```

3. **Wait for the "🎉 SYSTEM READY!" message**

4. **Open your browser** to the URL shown (usually `http://localhost:5173`)

5. **To stop**: Press `Ctrl+C` in the terminal

---

## 📋 **What You'll See When It Starts**

The script will:
- ✅ Check Java, Node.js, and other requirements
- ✅ Start the backend (Spring Boot server on port 8080)
- ✅ Start the frontend (Vue.js app on port 5173-5177)
- ✅ Show you all the URLs to access your app
- ✅ Monitor the system for problems

---

## 🌐 **Your Application URLs**

| What | URL | Description |
|------|-----|-------------|
| **Main App** | `http://localhost:5173` | Your fitness tracking interface |
| **Backend API** | `http://localhost:8080` | REST API (for developers) |
| **Health Check** | `http://localhost:8080/actuator/health` | System status |

---

## 🛠️ **If Something Goes Wrong**

### **Script won't run?**
```bash
# Make it executable first
chmod +x run-fitness-system.sh
./run-fitness-system.sh
```

### **"Java not found" error?**
```bash
# Install Java (macOS)
brew install openjdk@17
```

### **"Node.js not found" error?**
```bash
# Install Node.js (macOS)
brew install node
```

### **Port already in use?**
```bash
# Stop any existing processes
pkill -f "bootRun|vite"
# Then try again
./run-fitness-system.sh
```

### **Check what's running:**
```bash
# See what's using port 8080 or 5173
lsof -i :8080
lsof -i :5173
```

### **View logs:**
```bash
# Backend logs
tail -f logs/backend.log

# Frontend logs  
tail -f logs/frontend.log
```

---

## 🔧 **Manual Commands** (Advanced Users)

If you prefer to start services manually:

### Start Backend Only:
```bash
cd backend
SPRING_PROFILES_ACTIVE=test ./gradlew bootRun
```

### Start Frontend Only:
```bash
cd frontend
npm run dev
```

### Stop Everything:
```bash
pkill -f "bootRun|vite"
```

---

## 🎯 **Features Available**

✅ **User Management**: Register, login, user profiles  
✅ **Workout Tracking**: Create and log workout sessions  
✅ **Exercise Library**: Browse exercises with instructions  
✅ **Progress Analytics**: Track your fitness progress  
✅ **Real-time Updates**: Live data synchronization  
⚠️ **Motion Analysis**: Currently disabled (OpenCV issue)

---

## 📞 **Need Help?**

1. **Check the logs** first: `tail -f logs/backend.log`
2. **Restart the system**: `Ctrl+C` then `./run-fitness-system.sh`
3. **Check system health**: Visit `http://localhost:8080/actuator/health`

**The fitness system works perfectly for all core features!** 🎉 
