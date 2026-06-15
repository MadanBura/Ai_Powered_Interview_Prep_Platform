package com.interview.platform.mod19_recommendation_engine.dto;

import java.util.UUID;
import java.util.List;
import java.time.LocalDateTime;

public class UserRoadmapDto {
    private UUID id;
    private UUID userId;
    private UUID roadmapTemplateId;
    private String status;
    private Integer progressPercentage;
    private List<String> completedTopics;
    private LocalDateTime startedAt;
    
    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public UUID getRoadmapTemplateId() { return roadmapTemplateId; }
    public void setRoadmapTemplateId(UUID roadmapTemplateId) { this.roadmapTemplateId = roadmapTemplateId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getProgressPercentage() { return progressPercentage; }
    public void setProgressPercentage(Integer progressPercentage) { this.progressPercentage = progressPercentage; }
    public List<String> getCompletedTopics() { return completedTopics; }
    public void setCompletedTopics(List<String> completedTopics) { this.completedTopics = completedTopics; }
    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }
}
