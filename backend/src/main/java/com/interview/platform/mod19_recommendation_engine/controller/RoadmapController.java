package com.interview.platform.mod19_recommendation_engine.controller;

import com.interview.platform.mod19_recommendation_engine.dto.RoadmapResponseDto;
import com.interview.platform.mod19_recommendation_engine.service.RoadmapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/roadmaps")
public class RoadmapController {

    private final RoadmapService roadmapService;

    public RoadmapController(RoadmapService roadmapService) {
        this.roadmapService = roadmapService;
    }

    @GetMapping
    public ResponseEntity<RoadmapResponseDto> getRoadmap(
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String technology,
            @RequestParam(required = false) String experienceLevel) {
        
        RoadmapResponseDto response = roadmapService.getRoadmap(role, technology, experienceLevel);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{templateId}")
    public ResponseEntity<RoadmapResponseDto> getRoadmapByTemplateId(@org.springframework.web.bind.annotation.PathVariable java.util.UUID templateId) {
        RoadmapResponseDto response = roadmapService.getRoadmapByTemplateId(templateId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/available")
    public ResponseEntity<java.util.List<com.interview.platform.mod19_recommendation_engine.dto.AvailableRoadmapDto>> getAvailableRoadmaps() {
        return ResponseEntity.ok(roadmapService.getAvailableRoadmaps());
    }
}
