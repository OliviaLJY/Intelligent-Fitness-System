package com.fitness;

import com.fitness.util.OpenCVTest;
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
    
    // OpenCV status tracking
    public static volatile boolean openCVAvailable = false;
    public static volatile String openCVStatus = "Not initialized";

    public static void main(String[] args) {
        // Start Spring Boot application first
        SpringApplication.run(SmartFitnessApplication.class, args);
        
        // Initialize OpenCV after application startup (async)
        initializeOpenCVAsync();
    }
    
    /**
     * Initialize OpenCV asynchronously after Spring Boot startup
     */
    private static void initializeOpenCVAsync() {
        new Thread(() -> {
            try {
                Thread.sleep(3000); // Wait for app to fully start
                log.info("🔧 Attempting OpenCV initialization...");
                initializeOpenCV();
            } catch (Exception e) {
                log.warn("OpenCV async initialization failed: {}", e.getMessage());
            }
        }).start();
    }
    
    /**
     * Initialize OpenCV library with graceful error handling
     */
    private static void initializeOpenCV() {
        // Check if we're in test mode - completely skip OpenCV initialization
        String profile = System.getProperty("spring.profiles.active", "");
        String envProfile = System.getenv("SPRING_PROFILES_ACTIVE");
        String disableOpenCV = System.getProperty("opencv.disabled", 
                               System.getenv("OPENCV_DISABLED"));
        
        // Check both system property and environment variable
        if ("test".equals(profile) || "test".equals(envProfile)) {
            // In test mode, only skip if explicitly disabled
            if ("true".equals(disableOpenCV)) {
                log.info("Running in test mode - OpenCV initialization skipped");
                return;
            }
        }
        
        // Check if OpenCV should be disabled via property
        if ("true".equals(disableOpenCV)) {
            log.info("OpenCV disabled via system property");
            return;
        }
        
        try {
            // Try to load OpenCV native library
            nu.pattern.OpenCV.loadShared();
            log.info("✅ OpenCV native library loaded successfully");
            
            // Run comprehensive test
            if (OpenCVTest.testOpenCV()) {
                openCVAvailable = true;
                openCVStatus = OpenCVTest.getOpenCVStatus();
                log.info("✅ OpenCV initialization complete - Motion analysis ready");
                log.info("📊 Status: {}", openCVStatus);
            } else {
                openCVAvailable = false;
                openCVStatus = "Loaded but functionality test failed";
                log.warn("⚠️  OpenCV loaded but functionality test failed");
            }
            
        } catch (UnsatisfiedLinkError e) {
            log.warn("❌ OpenCV native library loading failed: {}", e.getMessage());
            
            // Try alternative loading method for system OpenCV
            if (trySystemOpenCV()) {
                openCVAvailable = true;
                openCVStatus = "System OpenCV loaded as fallback";
                log.info("✅ Successfully loaded system OpenCV as fallback");
            } else {
                openCVAvailable = false;
                openCVStatus = "Failed to load: " + e.getMessage();
                log.warn("⚠️  Motion analysis features will be disabled");
                log.info("💡 To enable motion analysis:");
                log.info("   1. Ensure OpenCV Java bindings match system version");
                log.info("   2. System has OpenCV 4.11.0, but Java expects 4.6.0/4.9.0");
                log.info("   3. Application will continue without motion analysis");
            }
        } catch (Exception e) {
            openCVAvailable = false;
            openCVStatus = "Initialization error: " + e.getMessage();
            log.error("❌ Unexpected error while loading OpenCV: {}", e.getMessage(), e);
            log.info("💡 Application will continue without motion analysis features");
        }
    }
    
    /**
     * Try to load system OpenCV as fallback
     */
    private static boolean trySystemOpenCV() {
        try {
            // Try loading system library directly
            System.loadLibrary("opencv_java");
            log.info("✅ System OpenCV library loaded successfully");
            return true;
        } catch (UnsatisfiedLinkError e) {
            try {
                // Try with version-specific name
                System.loadLibrary("opencv_java4");
                log.info("✅ System OpenCV4 library loaded successfully");
                return true;
            } catch (UnsatisfiedLinkError e2) {
                log.debug("Could not load system OpenCV libraries: {} / {}", e.getMessage(), e2.getMessage());
                return false;
            }
        } catch (Exception e) {
            log.debug("Unexpected error loading system OpenCV: {}", e.getMessage());
            return false;
        }
    }
} 