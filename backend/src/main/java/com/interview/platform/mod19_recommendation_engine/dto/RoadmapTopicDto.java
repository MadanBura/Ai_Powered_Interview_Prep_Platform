package com.interview.platform.mod19_recommendation_engine.dto;

public class RoadmapTopicDto {
    private String id;
    private String name;
    private boolean isCompleted;

    public RoadmapTopicDto() {}

    public RoadmapTopicDto(String id, String name, boolean isCompleted) {
        this.id = id;
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
