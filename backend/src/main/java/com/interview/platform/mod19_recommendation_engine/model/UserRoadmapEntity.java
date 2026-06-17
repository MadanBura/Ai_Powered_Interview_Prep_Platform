package com.interview.platform.mod19_recommendation_engine.model;

import jakarta.persistence.*;
import java.util.UUID;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "user_roadmaps")
public class UserRoadmapEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "char(36)")
    private UUID id;

    @Column(name = "user_id", columnDefinition = "char(36)", nullable = false)
    private UUID userId;

    @Column(name = "roadmap_template_id", columnDefinition = "char(36)")
    private UUID roadmapTemplateId;

    @Column(name = "role_template_id", columnDefinition = "char(36)")
    private UUID roleTemplateId;

    @Column(nullable = false)
    private String status; // "IN_PROGRESS", "COMPLETED"

    @Column(name = "progress_percentage")
    private Integer progressPercentage = 0;

    @Column(name = "completed_topics", columnDefinition = "json")
    private String completedTopics; // JSON array of topic titles

    @CreationTimestamp
    @Column(name = "started_at", updatable = false)
    private LocalDateTime startedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public UUID getRoadmapTemplateId() { return roadmapTemplateId; }
    public void setRoadmapTemplateId(UUID roadmapTemplateId) { this.roadmapTemplateId = roadmapTemplateId; }
    public UUID getRoleTemplateId() { return roleTemplateId; }
    public void setRoleTemplateId(UUID roleTemplateId) { this.roleTemplateId = roleTemplateId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getProgressPercentage() { return progressPercentage; }
    public void setProgressPercentage(Integer progressPercentage) { this.progressPercentage = progressPercentage; }
    public String getCompletedTopics() { return completedTopics; }
    public void setCompletedTopics(String completedTopics) { this.completedTopics = completedTopics; }
    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }
    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
}
