package com.fitness;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Smart Fitness System - AI-Powered Motion Analysis
 * 
 * A comprehensive intelligent fitness system that provides:
 * - Real-time motion capture and analysis using OpenCV
 * - Personalized workout recommendations based on user performance
 * - Form correction and feedback system
 * - Progress tracking and analytics
 * - Adaptive training plans
 * 
 * @author Smart Fitness Team
 * @version 1.0.0
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableTransactionManagement
@EnableCaching
@EnableAsync
@EnableScheduling
@Slf4j
public class SmartFitnessApplication {

    public static void main(String[] args) {
        // Initialize OpenCV native library (optional for testing)
        initializeOpenCV();
        
        SpringApplication.run(SmartFitnessApplication.class, args);
    }
    
    /**
     * Initialize OpenCV library with graceful error handling
     */
    private static void initializeOpenCV() {
        // Check if we're in test mode - completely skip OpenCV initialization
        String profile = System.getProperty("spring.profiles.active", "");
        String envProfile = System.getenv("SPRING_PROFILES_ACTIVE");
        
        // Check both system property and environment variable
        if ("test".equals(profile) || "test".equals(envProfile)) {
            log.info("Running in test mode - OpenCV initialization skipped");
            return;
        }
        
        // Check if OpenCV should be disabled via property
        String disableOpenCV = System.getProperty("opencv.disabled", "false");
        if ("true".equals(disableOpenCV)) {
            log.info("OpenCV disabled via system property");
            return;
        }
        
        try {
            // Try to load OpenCV only in production mode
            nu.pattern.OpenCV.loadShared();
            log.info("OpenCV library loaded successfully");
            
        } catch (UnsatisfiedLinkError e) {
            log.warn("OpenCV native library not found. Motion analysis features will be disabled. Error: {}", e.getMessage());
            log.info("To enable motion analysis, ensure OpenCV native libraries are properly installed");
        } catch (Exception e) {
            log.error("Unexpected error while loading OpenCV: {}", e.getMessage(), e);
        }
    }
} 