package com.interview.platform.mod19_recommendation_engine.model;

import jakarta.persistence.*;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name = "roadmap_templates")
public class RoadmapTemplateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "char(36)")
    private UUID id;

    @Column(name = "technology_id", columnDefinition = "char(36)")
    private UUID technologyId;

    @Column(name = "experience_level_id", columnDefinition = "char(36)")
    private UUID experienceLevelId;

    private String title;

    @Column(name = "focus_areas", columnDefinition = "json")
    private String focusAreas;

    @Column(columnDefinition = "json")
    private String milestones;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getTechnologyId() { return technologyId; }
    public void setTechnologyId(UUID technologyId) { this.technologyId = technologyId; }
    public UUID getExperienceLevelId() { return experienceLevelId; }
    public void setExperienceLevelId(UUID experienceLevelId) { this.experienceLevelId = experienceLevelId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getFocusAreas() { return focusAreas; }
    public void setFocusAreas(String focusAreas) { this.focusAreas = focusAreas; }
    public String getMilestones() { return milestones; }
    public void setMilestones(String milestones) { this.milestones = milestones; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
