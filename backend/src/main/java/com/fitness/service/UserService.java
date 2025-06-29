package com.fitness.service;

import com.fitness.model.User;
import com.fitness.model.UserProgress;
import com.fitness.repository.UserRepository;
import com.fitness.repository.UserProgressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service class for User-related operations
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserProgressRepository userProgressRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Load user by username for Spring Security
     */
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + usernameOrEmail));
    }

    /**
     * Create a new user
     */
    public User createUser(User user) {
        validateNewUser(user);
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(User.Role.USER));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        
        User savedUser = userRepository.save(user);
        log.info("Created new user: {}", savedUser.getUsername());
        return savedUser;
    }

    /**
     * Update user profile
     */
    public User updateUserProfile(Long userId, User userUpdate) {
        User existingUser = getUserById(userId);
        
        // Update allowed fields
        if (userUpdate.getFirstName() != null) {
            existingUser.setFirstName(userUpdate.getFirstName());
        }
        if (userUpdate.getLastName() != null) {
            existingUser.setLastName(userUpdate.getLastName());
        }
        if (userUpdate.getDateOfBirth() != null) {
            existingUser.setDateOfBirth(userUpdate.getDateOfBirth());
        }
        if (userUpdate.getGender() != null) {
            existingUser.setGender(userUpdate.getGender());
        }
        if (userUpdate.getHeight() != null) {
            existingUser.setHeight(userUpdate.getHeight());
        }
        if (userUpdate.getWeight() != null) {
            existingUser.setWeight(userUpdate.getWeight());
            // Record weight progress
            recordWeightProgress(existingUser, userUpdate.getWeight());
        }
        if (userUpdate.getFitnessLevel() != null) {
            existingUser.setFitnessLevel(userUpdate.getFitnessLevel());
        }
        if (userUpdate.getFitnessGoals() != null) {
            existingUser.setFitnessGoals(userUpdate.getFitnessGoals());
        }
        if (userUpdate.getPreferredWorkoutDuration() != null) {
            existingUser.setPreferredWorkoutDuration(userUpdate.getPreferredWorkoutDuration());
        }
        if (userUpdate.getPreferredWorkoutDays() != null) {
            existingUser.setPreferredWorkoutDays(userUpdate.getPreferredWorkoutDays());
        }
        if (userUpdate.getTimezone() != null) {
            existingUser.setTimezone(userUpdate.getTimezone());
        }
        if (userUpdate.getPreferredLanguage() != null) {
            existingUser.setPreferredLanguage(userUpdate.getPreferredLanguage());
        }
        if (userUpdate.getNotificationEnabled() != null) {
            existingUser.setNotificationEnabled(userUpdate.getNotificationEnabled());
        }
        
        return userRepository.save(existingUser);
    }

    /**
     * Change user password
     */
    public void changePassword(Long userId, String currentPassword, String newPassword) {
        User user = getUserById(userId);
        
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }
        
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        log.info("Password changed for user: {}", user.getUsername());
    }

    /**
     * Update last login time
     */
    public void updateLastLogin(String username) {
        userRepository.findByUsername(username).ifPresent(user -> {
            user.setLastLogin(LocalDateTime.now());
            userRepository.save(user);
        });
    }

    /**
     * Find user by username
     */
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Find user by email
     */
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Get user by ID
     */
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    /**
     * Check if username exists
     */
    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    /**
     * Check if email exists
     */
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Get users by fitness level
     */
    @Transactional(readOnly = true)
    public List<User> getUsersByFitnessLevel(User.FitnessLevel level) {
        return userRepository.findByFitnessLevel(level);
    }

    /**
     * Get users by fitness goal
     */
    @Transactional(readOnly = true)
    public List<User> getUsersByFitnessGoal(User.FitnessGoal goal) {
        return userRepository.findByFitnessGoal(goal);
    }

    /**
     * Get user statistics
     */
    @Transactional(readOnly = true)
    public UserStats getUserStats(Long userId) {
        User user = getUserById(userId);
        
        UserStats stats = new UserStats();
        stats.setUserId(userId);
        stats.setUsername(user.getUsername());
        stats.setMemberSince(user.getCreatedAt().toLocalDate());
        stats.setCurrentWeight(user.getWeight());
        stats.setCurrentBMI(user.getBMI());
        
        // Add more statistics as needed
        return stats;
    }

    /**
     * Validate new user data
     */
    private void validateNewUser(User user) {
        if (existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists: " + user.getUsername());
        }
        if (existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + user.getEmail());
        }
    }

    /**
     * Record weight progress automatically
     */
    private void recordWeightProgress(User user, Double weight) {
        UserProgress weightProgress = UserProgress.builder()
                .user(user)
                .recordDate(LocalDate.now())
                .metricType(UserProgress.MetricType.WEIGHT)
                .value(weight)
                .unit("kg")
                .build();
        
        userProgressRepository.save(weightProgress);
    }

    /**
     * User statistics DTO
     */
    public static class UserStats {
        private Long userId;
        private String username;
        private LocalDate memberSince;
        private Double currentWeight;
        private Double currentBMI;
        
        // Getters and setters
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        
        public LocalDate getMemberSince() { return memberSince; }
        public void setMemberSince(LocalDate memberSince) { this.memberSince = memberSince; }
        
        public Double getCurrentWeight() { return currentWeight; }
        public void setCurrentWeight(Double currentWeight) { this.currentWeight = currentWeight; }
        
        public Double getCurrentBMI() { return currentBMI; }
        public void setCurrentBMI(Double currentBMI) { this.currentBMI = currentBMI; }
    }
} 