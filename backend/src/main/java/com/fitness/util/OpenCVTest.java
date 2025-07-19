package com.fitness.util;

import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.CvType;

/**
 * OpenCV Test Utility
 * Simple test to verify OpenCV is properly loaded and functional
 */
@Slf4j
public class OpenCVTest {
    
    /**
     * Test basic OpenCV functionality
     * @return true if OpenCV is working properly
     */
    public static boolean testOpenCV() {
        try {
            // Test basic matrix operations
            Mat testMat = new Mat(3, 3, CvType.CV_8UC1);
            testMat.put(0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
            
            // Test core functionality
            double[] data = testMat.get(1, 1);
            if (data != null && data[0] == 5) {
                log.info("✅ OpenCV basic matrix operations working");
                
                // Test OpenCV version
                String version = Core.getVersionString();
                log.info("✅ OpenCV version: {}", version);
                
                // Test native library build info
                String buildInfo = Core.getBuildInformation();
                log.debug("OpenCV build info: {}", buildInfo);
                
                return true;
            } else {
                log.error("❌ OpenCV matrix operations failed");
                return false;
            }
            
        } catch (UnsatisfiedLinkError e) {
            log.error("❌ OpenCV native library not found: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("❌ OpenCV test failed: {}", e.getMessage(), e);
            return false;
        }
    }
    
    /**
     * Get OpenCV status information
     * @return status string
     */
    public static String getOpenCVStatus() {
        try {
            if (testOpenCV()) {
                return String.format("OpenCV %s - Ready", Core.getVersionString());
            } else {
                return "OpenCV - Failed";
            }
        } catch (Exception e) {
            return "OpenCV - Not Available: " + e.getMessage();
        }
    }
} 