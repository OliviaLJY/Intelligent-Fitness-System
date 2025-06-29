package com.fitness.websocket;

import com.fitness.service.MotionAnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Base64;
import java.util.concurrent.CompletableFuture;

/**
 * WebSocket Controller for Motion Analysis
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class MotionAnalysisWebSocketController {

    private final MotionAnalysisService motionAnalysisService;
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * Handle video frame for motion analysis
     */
    @MessageMapping("/analyze-frame")
    public void analyzeVideoFrame(@Payload FrameAnalysisMessage message) {
        try {
            log.debug("Received frame for analysis: session={}, frame={}", 
                     message.getWorkoutSessionId(), message.getFrameNumber());

            // Decode base64 frame data
            byte[] frameData = Base64.getDecoder().decode(message.getFrameData());

            // Perform motion analysis asynchronously
            CompletableFuture<MotionAnalysisService.AnalysisStats> analysisFuture = 
                motionAnalysisService.analyzeMotion(
                    message.getWorkoutSessionId(),
                    message.getWorkoutExerciseId(),
                    frameData,
                    message.getFrameNumber()
                ).thenApply(result -> {
                    // Send analysis result back to client
                    AnalysisResultMessage resultMessage = new AnalysisResultMessage();
                    resultMessage.setSessionId(message.getWorkoutSessionId());
                    resultMessage.setFrameNumber(message.getFrameNumber());
                    resultMessage.setFormScore(result.getAverageFormScore());
                    resultMessage.setFeedbackMessage("Analysis complete");
                    
                    messagingTemplate.convertAndSendToUser(
                        message.getUserId().toString(),
                        "/queue/analysis-results",
                        resultMessage
                    );
                    
                    return null;
                });

            analysisFuture.exceptionally(throwable -> {
                log.error("Error in motion analysis: {}", throwable.getMessage());
                
                // Send error message to client
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setError("Motion analysis failed");
                errorMessage.setMessage(throwable.getMessage());
                
                messagingTemplate.convertAndSendToUser(
                    message.getUserId().toString(),
                    "/queue/analysis-errors",
                    errorMessage
                );
                
                return null;
            });

        } catch (Exception e) {
            log.error("Error processing frame analysis message: {}", e.getMessage());
        }
    }

    /**
     * Handle workout session status updates
     */
    @MessageMapping("/workout-status")
    @SendTo("/topic/workout-updates")
    public WorkoutStatusMessage handleWorkoutStatus(@Payload WorkoutStatusMessage message) {
        log.debug("Workout status update: {}", message);
        return message;
    }

    /**
     * Send real-time form feedback
     */
    public void sendFormFeedback(Long userId, String feedback, Double formScore) {
        FormFeedbackMessage message = new FormFeedbackMessage();
        message.setFeedback(feedback);
        message.setFormScore(formScore);
        message.setTimestamp(System.currentTimeMillis());

        messagingTemplate.convertAndSendToUser(
            userId.toString(),
            "/queue/form-feedback",
            message
        );
    }

    // Message DTOs
    public static class FrameAnalysisMessage {
        private Long userId;
        private Long workoutSessionId;
        private Long workoutExerciseId;
        private String frameData; // Base64 encoded frame
        private Integer frameNumber;
        private Long timestamp;

        // Getters and setters
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }

        public Long getWorkoutSessionId() { return workoutSessionId; }
        public void setWorkoutSessionId(Long workoutSessionId) { this.workoutSessionId = workoutSessionId; }

        public Long getWorkoutExerciseId() { return workoutExerciseId; }
        public void setWorkoutExerciseId(Long workoutExerciseId) { this.workoutExerciseId = workoutExerciseId; }

        public String getFrameData() { return frameData; }
        public void setFrameData(String frameData) { this.frameData = frameData; }

        public Integer getFrameNumber() { return frameNumber; }
        public void setFrameNumber(Integer frameNumber) { this.frameNumber = frameNumber; }

        public Long getTimestamp() { return timestamp; }
        public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }
    }

    public static class AnalysisResultMessage {
        private Long sessionId;
        private Integer frameNumber;
        private Double formScore;
        private String feedbackMessage;
        private Long timestamp;

        public AnalysisResultMessage() {
            this.timestamp = System.currentTimeMillis();
        }

        // Getters and setters
        public Long getSessionId() { return sessionId; }
        public void setSessionId(Long sessionId) { this.sessionId = sessionId; }

        public Integer getFrameNumber() { return frameNumber; }
        public void setFrameNumber(Integer frameNumber) { this.frameNumber = frameNumber; }

        public Double getFormScore() { return formScore; }
        public void setFormScore(Double formScore) { this.formScore = formScore; }

        public String getFeedbackMessage() { return feedbackMessage; }
        public void setFeedbackMessage(String feedbackMessage) { this.feedbackMessage = feedbackMessage; }

        public Long getTimestamp() { return timestamp; }
        public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }
    }

    public static class WorkoutStatusMessage {
        private Long sessionId;
        private String status;
        private Long timestamp;

        public WorkoutStatusMessage() {
            this.timestamp = System.currentTimeMillis();
        }

        // Getters and setters
        public Long getSessionId() { return sessionId; }
        public void setSessionId(Long sessionId) { this.sessionId = sessionId; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public Long getTimestamp() { return timestamp; }
        public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }

        @Override
        public String toString() {
            return "WorkoutStatusMessage{sessionId=" + sessionId + ", status='" + status + "', timestamp=" + timestamp + "}";
        }
    }

    public static class FormFeedbackMessage {
        private String feedback;
        private Double formScore;
        private Long timestamp;

        // Getters and setters
        public String getFeedback() { return feedback; }
        public void setFeedback(String feedback) { this.feedback = feedback; }

        public Double getFormScore() { return formScore; }
        public void setFormScore(Double formScore) { this.formScore = formScore; }

        public Long getTimestamp() { return timestamp; }
        public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }
    }

    public static class ErrorMessage {
        private String error;
        private String message;
        private Long timestamp;

        public ErrorMessage() {
            this.timestamp = System.currentTimeMillis();
        }

        // Getters and setters
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }

        public Long getTimestamp() { return timestamp; }
        public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }
    }
} 