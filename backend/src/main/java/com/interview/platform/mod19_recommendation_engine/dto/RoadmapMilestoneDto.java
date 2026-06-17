package com.interview.platform.mod19_recommendation_engine.dto;

public class RoadmapMilestoneDto {
    private String title;
    private String description;
    private String status;
    private float progress;
    private java.util.List<RoadmapTopicDto> topics;

    public RoadmapMilestoneDto(String title, String description, String status, float progress, java.util.List<RoadmapTopicDto> topics) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.progress = progress;
        this.topics = topics;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public float getProgress() { return progress; }
    public void setProgress(float progress) { this.progress = progress; }
    public java.util.List<RoadmapTopicDto> getTopics() { return topics; }
    public void setTopics(java.util.List<RoadmapTopicDto> topics) { this.topics = topics; }
}
