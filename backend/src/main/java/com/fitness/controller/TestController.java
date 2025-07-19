package com.fitness.controller;

import com.fitness.SmartFitnessApplication;
import com.fitness.util.OpenCVTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Test Controller for system verification
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    
    /**
     * Test OpenCV integration
     */
    @GetMapping("/opencv")
    public ResponseEntity<Map<String, Object>> testOpenCV() {
        log.info("Testing OpenCV integration...");
        
        Map<String, Object> response = new HashMap<>();
        
        // Check application-level OpenCV status first
        if (!SmartFitnessApplication.openCVAvailable) {
            response.put("success", false);
            response.put("status", SmartFitnessApplication.openCVStatus);
            response.put("message", "OpenCV is not available - " + SmartFitnessApplication.openCVStatus);
            response.put("recommendation", "Application started successfully without OpenCV. Motion analysis features are disabled.");
            
            log.info("OpenCV test: NOT AVAILABLE - {}", SmartFitnessApplication.openCVStatus);
            return ResponseEntity.ok(response);
        }
        
        try {
            boolean openCVWorking = OpenCVTest.testOpenCV();
            String status = OpenCVTest.getOpenCVStatus();
            
            response.put("success", openCVWorking);
            response.put("status", status);
            response.put("message", openCVWorking ? 
                "OpenCV is properly integrated and working" : 
                "OpenCV integration failed");
            
            if (openCVWorking) {
                response.put("features", Map.of(
                    "motionAnalysis", true,
                    "poseDetection", true,
                    "videoProcessing", true
                ));
            }
            
            log.info("OpenCV test completed: {}", openCVWorking ? "SUCCESS" : "FAILED");
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            log.error("OpenCV test failed with exception", e);
            
            response.put("success", false);
            response.put("status", "Error: " + e.getMessage());
            response.put("message", "OpenCV test failed with exception");
            
            return ResponseEntity.status(500).body(response);
        }
    }
    
    /**
     * System health check
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> systemHealth() {
        Map<String, Object> health = new HashMap<>();
        
        health.put("application", "Smart Fitness System");
        health.put("version", "1.0.0");
        health.put("opencv", SmartFitnessApplication.openCVAvailable ? "UP" : "DOWN");
        health.put("opencvStatus", SmartFitnessApplication.openCVStatus);
        health.put("timestamp", System.currentTimeMillis());
        
        return ResponseEntity.ok(health);
    }
} 