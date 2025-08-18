# Intelligent Fitness System (IFS)

An AI-powered fitness platform that provides real-time motion analysis, personalized workout recommendations, and adaptive training plans using computer vision technology.

![IFS Demo](docs/demo.gif)

##  Features

### Real-time Motion Analysis
- AI-powered form correction using OpenCV
- Real-time feedback on exercise technique
- Video demonstration library
- Common mistake detection and prevention

### Personalized Workout Experience
- Custom workout creation
- Exercise library with proper form demonstrations
- Workout templates for different fitness goals
- Rest timer with visual countdown
- Exercise history and progression tracking

### Smart Training Features
- Adaptive training plans based on performance
- Progress analytics and statistics
- Personal best tracking
- Form score monitoring
- Workout history visualization

### User Experience
- Modern, responsive interface
- Mobile-friendly design
- Real-time session management
- Comprehensive exercise database
- Intuitive workout creation

## 🛠️ Technology Stack

### Frontend
- Vue.js 3 with Composition API
- Pinia for state management
- TailwindCSS for styling
- WebSocket for real-time communication
- Chart.js for analytics visualization

### Backend
- Java Spring Boot
- PostgreSQL database
- OpenCV for motion analysis
- WebSocket for real-time updates
- JWT authentication

## 🚀 Getting Started

### Prerequisites
- Node.js (v16 or higher)
- Java JDK 17
- PostgreSQL 13+
- OpenCV 4.x

### Frontend Setup
```bash
# Navigate to frontend directory
cd frontend

# Install dependencies
npm install

# Start development server
npm run dev

# Build for production
npm run build
```

### Backend Setup
```bash
# Navigate to backend directory
cd backend

# Build with Gradle
./gradlew build

# Run the application
./gradlew bootRun
```

### Environment Configuration
Create a `.env` file in the frontend directory:
```env
VITE_API_URL=http://localhost:8080
VITE_WS_URL=ws://localhost:8080
```

##  Project Structure
```
intelligent-fitness-system/
├── frontend/                 # Vue.js application
│   ├── src/
│   │   ├── components/      # Reusable Vue components
│   │   │   ├── exercises/   # Exercise-related components
│   │   │   └── workout/     # Workout-related components
│   │   ├── views/          # Page views
│   │   ├── stores/         # Pinia stores
│   │   ├── services/       # API services
│   │   └── utils/          # Utility functions
│   ├── package.json
│   └── vite.config.js
├── backend/                 # Spring Boot application
│   ├── src/main/java/
│   │   └── com/fitness/
│   │       ├── controller/ # REST controllers
│   │       ├── service/    # Business logic
│   │       ├── model/      # Entity models
│   │       ├── repository/ # Data repositories
│   │       └── config/     # Configuration
│   ├── build.gradle
│   └── application.yml
└── opencv-module/          # OpenCV motion analysis
    ├── src/main/java/
    └── native/             # Native libraries
```

##  Key Features in Detail

### Exercise Library
- Comprehensive exercise database
- Video demonstrations
- Step-by-step instructions
- Form tips and common mistakes
- Difficulty levels and categories

### Workout Management
- Create custom workouts
- Save workout templates
- Track exercise progress
- Rest timer with presets
- Session statistics

### Progress Tracking
- Performance analytics
- Personal best records
- Workout history
- Form improvement tracking
- Goal setting and monitoring

##  Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

##  License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

##  Acknowledgments

- OpenCV for computer vision capabilities
- Vue.js team for the amazing framework
- Spring Boot team for the robust backend framework
- All contributors who have helped shape this project

##  Contact

For any questions or suggestions, please open an issue in the repository or contact the maintainers.

---

Built with ❤️ by the Intelligent Fitness System (IFS) Team
