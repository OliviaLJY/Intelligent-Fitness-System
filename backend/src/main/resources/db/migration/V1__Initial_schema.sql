-- Initial database schema for Smart Fitness System

-- Users table
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    date_of_birth DATE,
    gender VARCHAR(10),
    height INTEGER,
    weight DECIMAL(5,2),
    fitness_level VARCHAR(20),
    is_enabled BOOLEAN DEFAULT TRUE,
    is_account_non_expired BOOLEAN DEFAULT TRUE,
    is_account_non_locked BOOLEAN DEFAULT TRUE,
    is_credentials_non_expired BOOLEAN DEFAULT TRUE,
    last_login TIMESTAMP,
    profile_image_url VARCHAR(500),
    timezone VARCHAR(50) DEFAULT 'UTC',
    preferred_language VARCHAR(10) DEFAULT 'en',
    preferred_workout_duration INTEGER,
    notification_enabled BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version BIGINT DEFAULT 0
);

-- User roles
CREATE TABLE user_roles (
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    role VARCHAR(20) NOT NULL,
    PRIMARY KEY (user_id, role)
);

-- User fitness goals
CREATE TABLE user_goals (
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    goal VARCHAR(30) NOT NULL,
    PRIMARY KEY (user_id, goal)
);

-- User preferred workout days
CREATE TABLE user_workout_days (
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    day_of_week VARCHAR(10) NOT NULL,
    PRIMARY KEY (user_id, day_of_week)
);

-- Exercises table
CREATE TABLE exercises (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    instructions TEXT,
    category VARCHAR(30) NOT NULL,
    difficulty_level VARCHAR(20) NOT NULL,
    duration_minutes INTEGER,
    calories_per_minute DECIMAL(5,2),
    default_sets INTEGER,
    default_reps INTEGER,
    default_weight DECIMAL(6,2),
    rest_time_seconds INTEGER,
    video_url VARCHAR(500),
    image_url VARCHAR(500),
    is_active BOOLEAN DEFAULT TRUE,
    opencv_model_name VARCHAR(100),
    motion_analysis_enabled BOOLEAN DEFAULT FALSE,
    form_check_threshold DECIMAL(3,2) DEFAULT 0.7,
    -- Motion analysis parameters
    min_detection_confidence DECIMAL(3,2) DEFAULT 0.5,
    min_tracking_confidence DECIMAL(3,2) DEFAULT 0.5,
    pose_smoothing BOOLEAN DEFAULT TRUE,
    enable_segmentation BOOLEAN DEFAULT FALSE,
    frame_rate INTEGER DEFAULT 30,
    analysis_window_size INTEGER DEFAULT 10,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version BIGINT DEFAULT 0
);

-- Exercise muscle groups
CREATE TABLE exercise_muscle_groups (
    exercise_id BIGINT REFERENCES exercises(id) ON DELETE CASCADE,
    muscle_group VARCHAR(30) NOT NULL,
    PRIMARY KEY (exercise_id, muscle_group)
);

-- Exercise secondary muscles
CREATE TABLE exercise_secondary_muscles (
    exercise_id BIGINT REFERENCES exercises(id) ON DELETE CASCADE,
    muscle_group VARCHAR(30) NOT NULL,
    PRIMARY KEY (exercise_id, muscle_group)
);

-- Exercise equipment
CREATE TABLE exercise_equipment (
    exercise_id BIGINT REFERENCES exercises(id) ON DELETE CASCADE,
    equipment VARCHAR(30) NOT NULL,
    PRIMARY KEY (exercise_id, equipment)
);

-- Exercise key points for motion analysis
CREATE TABLE exercise_key_points (
    exercise_id BIGINT REFERENCES exercises(id) ON DELETE CASCADE,
    key_point VARCHAR(50) NOT NULL,
    PRIMARY KEY (exercise_id, key_point)
);

-- Workout sessions
CREATE TABLE workout_sessions (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    session_name VARCHAR(100),
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP,
    planned_duration_minutes INTEGER,
    actual_duration_minutes INTEGER,
    status VARCHAR(20) NOT NULL DEFAULT 'PLANNED',
    total_calories_burned DECIMAL(8,2),
    total_exercises INTEGER,
    completed_exercises INTEGER,
    average_heart_rate INTEGER,
    max_heart_rate INTEGER,
    notes TEXT,
    difficulty_rating INTEGER,
    user_rating INTEGER,
    workout_type VARCHAR(30),
    is_template BOOLEAN DEFAULT FALSE,
    template_name VARCHAR(100),
    overall_form_score DECIMAL(3,2),
    ai_feedback TEXT,
    improvement_suggestions TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version BIGINT DEFAULT 0
);

-- Workout exercises
CREATE TABLE workout_exercises (
    id BIGSERIAL PRIMARY KEY,
    workout_session_id BIGINT NOT NULL REFERENCES workout_sessions(id) ON DELETE CASCADE,
    exercise_id BIGINT NOT NULL REFERENCES exercises(id),
    exercise_order INTEGER NOT NULL,
    planned_sets INTEGER,
    completed_sets INTEGER,
    planned_reps INTEGER,
    planned_weight DECIMAL(6,2),
    planned_duration_seconds INTEGER,
    actual_duration_seconds INTEGER,
    rest_time_seconds INTEGER,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    status VARCHAR(20) NOT NULL DEFAULT 'PLANNED',
    calories_burned DECIMAL(8,2),
    average_heart_rate INTEGER,
    max_heart_rate INTEGER,
    notes TEXT,
    difficulty_rating INTEGER,
    rpe_rating INTEGER,
    form_score DECIMAL(3,2),
    technique_feedback TEXT,
    motion_analysis_enabled BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version BIGINT DEFAULT 0
);

-- Exercise sets
CREATE TABLE exercise_sets (
    id BIGSERIAL PRIMARY KEY,
    workout_exercise_id BIGINT NOT NULL REFERENCES workout_exercises(id) ON DELETE CASCADE,
    set_order INTEGER NOT NULL,
    planned_reps INTEGER,
    actual_reps INTEGER,
    planned_weight DECIMAL(6,2),
    actual_weight DECIMAL(6,2),
    planned_duration_seconds INTEGER,
    actual_duration_seconds INTEGER,
    rest_time_seconds INTEGER,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    status VARCHAR(20) NOT NULL DEFAULT 'PLANNED',
    calories_burned DECIMAL(8,2),
    average_heart_rate INTEGER,
    max_heart_rate INTEGER,
    rpe_rating INTEGER,
    form_score DECIMAL(3,2),
    notes VARCHAR(500),
    set_type VARCHAR(20) DEFAULT 'NORMAL',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version BIGINT DEFAULT 0
);

-- Motion analysis results
CREATE TABLE motion_analysis_results (
    id BIGSERIAL PRIMARY KEY,
    workout_session_id BIGINT NOT NULL REFERENCES workout_sessions(id) ON DELETE CASCADE,
    workout_exercise_id BIGINT REFERENCES workout_exercises(id) ON DELETE CASCADE,
    analysis_timestamp TIMESTAMP NOT NULL,
    frame_number INTEGER,
    video_timestamp_ms BIGINT,
    confidence_score DECIMAL(3,2),
    form_score DECIMAL(3,2),
    pose_detected BOOLEAN DEFAULT FALSE,
    rep_count INTEGER,
    current_phase VARCHAR(20),
    velocity_data TEXT,
    acceleration_data TEXT,
    range_of_motion TEXT,
    symmetry_score DECIMAL(3,2),
    stability_score DECIMAL(3,2),
    tempo_score DECIMAL(3,2),
    depth_score DECIMAL(3,2),
    feedback_message TEXT,
    improvement_suggestions TEXT,
    analysis_model_version VARCHAR(50),
    processing_time_ms BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version BIGINT DEFAULT 0
);

-- Pose landmarks
CREATE TABLE pose_landmarks (
    analysis_result_id BIGINT REFERENCES motion_analysis_results(id) ON DELETE CASCADE,
    landmark_name VARCHAR(50) NOT NULL,
    landmark_data TEXT,
    PRIMARY KEY (analysis_result_id, landmark_name)
);

-- Joint angles
CREATE TABLE joint_angles (
    analysis_result_id BIGINT REFERENCES motion_analysis_results(id) ON DELETE CASCADE,
    joint_name VARCHAR(50) NOT NULL,
    angle_degrees DECIMAL(6,2),
    PRIMARY KEY (analysis_result_id, joint_name)
);

-- Form errors
CREATE TABLE form_errors (
    analysis_result_id BIGINT REFERENCES motion_analysis_results(id) ON DELETE CASCADE,
    error_type VARCHAR(50) NOT NULL,
    PRIMARY KEY (analysis_result_id, error_type)
);

-- User progress
CREATE TABLE user_progress (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    record_date DATE NOT NULL,
    metric_type VARCHAR(50) NOT NULL,
    value DECIMAL(10,3) NOT NULL,
    unit VARCHAR(20),
    exercise_name VARCHAR(100),
    body_part VARCHAR(50),
    notes TEXT,
    target_value DECIMAL(10,3),
    improvement_percentage DECIMAL(5,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version BIGINT DEFAULT 0
);

-- Create indexes for better performance
CREATE INDEX idx_user_email ON users(email);
CREATE INDEX idx_user_username ON users(username);
CREATE INDEX idx_exercise_name ON exercises(name);
CREATE INDEX idx_exercise_category ON exercises(category);
CREATE INDEX idx_exercise_difficulty ON exercises(difficulty_level);
CREATE INDEX idx_session_user ON workout_sessions(user_id);
CREATE INDEX idx_session_date ON workout_sessions(start_time);
CREATE INDEX idx_session_status ON workout_sessions(status);
CREATE INDEX idx_workout_exercise_session ON workout_exercises(workout_session_id);
CREATE INDEX idx_workout_exercise_exercise ON workout_exercises(exercise_id);
CREATE INDEX idx_workout_exercise_order ON workout_exercises(exercise_order);
CREATE INDEX idx_set_workout_exercise ON exercise_sets(workout_exercise_id);
CREATE INDEX idx_set_order ON exercise_sets(set_order);
CREATE INDEX idx_analysis_session ON motion_analysis_results(workout_session_id);
CREATE INDEX idx_analysis_exercise ON motion_analysis_results(workout_exercise_id);
CREATE INDEX idx_analysis_timestamp ON motion_analysis_results(analysis_timestamp);
CREATE INDEX idx_progress_user ON user_progress(user_id);
CREATE INDEX idx_progress_date ON user_progress(record_date);
CREATE INDEX idx_progress_metric ON user_progress(metric_type); 