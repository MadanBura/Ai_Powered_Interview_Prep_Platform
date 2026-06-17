package com.interview.platform.mod19_recommendation_engine.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.platform.mod04_technology_management.repository.TechnologyRepository;
import com.interview.platform.mod05_experience_level_management.repository.ExperienceLevelRepository;
import com.interview.platform.mod19_recommendation_engine.dto.RoadmapMilestoneDto;
import com.interview.platform.mod19_recommendation_engine.dto.RoadmapResponseDto;
import com.interview.platform.mod19_recommendation_engine.model.RoadmapRoleTemplateEntity;
import com.interview.platform.mod19_recommendation_engine.model.RoadmapTemplateEntity;
import com.interview.platform.mod19_recommendation_engine.repository.RoadmapRoleTemplateRepository;
import com.interview.platform.mod19_recommendation_engine.repository.RoadmapTemplateRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoadmapService {

    private final RoadmapRoleTemplateRepository roleRepository;
    private final RoadmapTemplateRepository techRepository;
    private final TechnologyRepository technologyRepository;
    private final ExperienceLevelRepository experienceLevelRepository;
    private final ObjectMapper objectMapper;

    public RoadmapService(RoadmapRoleTemplateRepository roleRepository,
                          RoadmapTemplateRepository techRepository,
                          TechnologyRepository technologyRepository,
                          ExperienceLevelRepository experienceLevelRepository,
                          ObjectMapper objectMapper) {
        this.roleRepository = roleRepository;
        this.techRepository = techRepository;
        this.technologyRepository = technologyRepository;
        this.experienceLevelRepository = experienceLevelRepository;
        this.objectMapper = objectMapper;
    }

    public RoadmapResponseDto getRoadmap(String role, String technology, String experienceLevel) {
        List<RoadmapMilestoneDto> items = new ArrayList<>();
        String title = "Your Personalized Learning Path";

        UUID experienceLevelId = null;
        if (experienceLevel != null && !experienceLevel.isEmpty()) {
            experienceLevelId = experienceLevelRepository.findByLabel(experienceLevel)
                    .map(el -> el.getId()).orElse(null);
        }

        UUID technologyId = null;
        if (technology != null && !technology.isEmpty()) {
            technologyId = technologyRepository.findByName(technology)
                    .map(t -> t.getId()).orElse(null);
        }

        boolean foundRole = false;
        if (role != null && !role.isEmpty() && experienceLevelId != null) {
            // Fuzzy match role name since DB might have "Full Stack Developer (Java + React)"
            // Actually, let's just find all and filter
            List<RoadmapRoleTemplateEntity> roles = roleRepository.findAll();
            for (RoadmapRoleTemplateEntity r : roles) {
                if (r.getRoleName().toLowerCase().contains(role.toLowerCase()) && 
                    r.getExperienceLevelId().equals(experienceLevelId)) {
                    title = r.getTitle();
                    parseAndAddMilestones(r.getMilestones(), items, true);
                    foundRole = true;
                    break;
                }
            }
        }

        if (technologyId != null && experienceLevelId != null) {
            Optional<RoadmapTemplateEntity> techEntity = techRepository.findByTechnologyIdAndExperienceLevelId(technologyId, experienceLevelId);
            if (techEntity.isPresent()) {
                if (!foundRole) {
                    title = techEntity.get().getTitle();
                }
                parseAndAddMilestones(techEntity.get().getMilestones(), items, false);
            }
        }

        if (items.isEmpty()) {
            return getFallbackRoadmap(title);
        }

        return new RoadmapResponseDto(title, items);
    }

    public RoadmapResponseDto getRoadmapByTemplateId(UUID templateId) {
        List<RoadmapMilestoneDto> items = new ArrayList<>();
        String title = "Your Personalized Learning Path";

        Optional<RoadmapTemplateEntity> techEntity = techRepository.findById(templateId);
        if (techEntity.isPresent()) {
            title = techEntity.get().getTitle();
            parseAndAddMilestones(techEntity.get().getMilestones(), items, false);
        }

        if (items.isEmpty()) {
            return getFallbackRoadmap(title);
        }
        return new RoadmapResponseDto(title, items);
    }

    private RoadmapResponseDto getFallbackRoadmap(String title) {
        List<RoadmapMilestoneDto> items = new ArrayList<>();
        java.util.List<com.interview.platform.mod19_recommendation_engine.dto.RoadmapTopicDto> topics = new java.util.ArrayList<>();
        topics.add(new com.interview.platform.mod19_recommendation_engine.dto.RoadmapTopicDto(java.util.UUID.randomUUID().toString(), "Introductory Concepts", false));
        topics.add(new com.interview.platform.mod19_recommendation_engine.dto.RoadmapTopicDto(java.util.UUID.randomUUID().toString(), "Practical Application", false));
        items.add(new RoadmapMilestoneDto(
            "Fundamentals of " + title,
            "Get started with the basics.",
            "In Progress",
            0.1f,
            topics
        ));
        return new RoadmapResponseDto(title, items);
    }

    private void parseAndAddMilestones(String json, List<RoadmapMilestoneDto> items, boolean isRole) {
        if (json == null || json.isEmpty()) return;
        try {
            // Try to parse the new structure (List of Maps) first
            List<java.util.Map<String, Object>> milestonesList = objectMapper.readValue(json, new TypeReference<List<java.util.Map<String, Object>>>() {});
            for (int i = 0; i < milestonesList.size(); i++) {
                java.util.Map<String, Object> map = milestonesList.get(i);
                String title = (String) map.get("title");
                String description = (String) map.get("description");
                String status = (i == 0) ? "In Progress" : "Locked";
                float progress = (i == 0) ? 0.3f : 0.0f;
                
                java.util.List<com.interview.platform.mod19_recommendation_engine.dto.RoadmapTopicDto> topicDtos = new java.util.ArrayList<>();
                
                if (map.containsKey("topics")) {
                    List<java.util.Map<String, Object>> topics = (List<java.util.Map<String, Object>>) map.get("topics");
                    for (java.util.Map<String, Object> t : topics) {
                        topicDtos.add(new com.interview.platform.mod19_recommendation_engine.dto.RoadmapTopicDto(
                            java.util.UUID.randomUUID().toString(),
                            (String) t.get("name"),
                            false
                        ));
                    }
                }
                
                items.add(new RoadmapMilestoneDto(
                    title,
                    description,
                    status,
                    progress,
                    topicDtos
                ));
            }
        } catch (Exception e) {
            // Fallback for old simple list format
            try {
                List<String> texts = objectMapper.readValue(json, new TypeReference<List<String>>() {});
                for (int i = 0; i < texts.size(); i++) {
                    String status = (i == 0) ? "In Progress" : "Locked";
                    float progress = (i == 0) ? 0.3f : 0.0f;
                    java.util.List<com.interview.platform.mod19_recommendation_engine.dto.RoadmapTopicDto> topics = new java.util.ArrayList<>();
                    topics.add(new com.interview.platform.mod19_recommendation_engine.dto.RoadmapTopicDto(java.util.UUID.randomUUID().toString(), "Core " + texts.get(i) + " Concepts", false));
                    topics.add(new com.interview.platform.mod19_recommendation_engine.dto.RoadmapTopicDto(java.util.UUID.randomUUID().toString(), "Advanced " + texts.get(i) + " Patterns", false));
                    topics.add(new com.interview.platform.mod19_recommendation_engine.dto.RoadmapTopicDto(java.util.UUID.randomUUID().toString(), "Common Interview Questions", false));

                    items.add(new RoadmapMilestoneDto(
                        "Milestone " + (items.size() + 1),
                        texts.get(i),
                        status,
                        progress,
                        topics
                    ));
                }
            } catch (Exception ex) {
                // Ignore
            }
        }
    }

    public List<com.interview.platform.mod19_recommendation_engine.dto.AvailableRoadmapDto> getAvailableRoadmaps() {
        List<com.interview.platform.mod19_recommendation_engine.dto.AvailableRoadmapDto> available = new ArrayList<>();
        List<RoadmapTemplateEntity> templates = techRepository.findAll();
        for (RoadmapTemplateEntity entity : templates) {
            String description = "Master " + entity.getTitle() + " with this 7-day intensive curriculum.";
            available.add(new com.interview.platform.mod19_recommendation_engine.dto.AvailableRoadmapDto(
                entity.getId(),
                entity.getTitle(),
                description
            ));
        }
        return available;
    }
}
