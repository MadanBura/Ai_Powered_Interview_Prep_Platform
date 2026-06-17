package com.interview.platform.mod19_recommendation_engine.dto;

import java.util.List;

public class RoadmapResponseDto {
    private String title;
    private List<RoadmapMilestoneDto> items;

    public RoadmapResponseDto(String title, List<RoadmapMilestoneDto> items) {
        this.title = title;
        this.items = items;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<RoadmapMilestoneDto> getItems() { return items; }
    public void setItems(List<RoadmapMilestoneDto> items) { this.items = items; }
}
