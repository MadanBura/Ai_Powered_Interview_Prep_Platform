package com.interview.platform.mod19_recommendation_engine.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.platform.mod19_recommendation_engine.dto.UserRoadmapDto;
import com.interview.platform.mod19_recommendation_engine.model.RoadmapTemplateEntity;
import com.interview.platform.mod19_recommendation_engine.model.UserRoadmapEntity;
import com.interview.platform.mod19_recommendation_engine.repository.RoadmapTemplateRepository;
import com.interview.platform.mod19_recommendation_engine.repository.UserRoadmapRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoadmapProgressService {

    private final UserRoadmapRepository userRoadmapRepository;
    private final RoadmapTemplateRepository roadmapTemplateRepository;
    private final com.interview.platform.mod19_recommendation_engine.repository.UserBadgeRepository userBadgeRepository;
    private final ObjectMapper objectMapper;

    public RoadmapProgressService(UserRoadmapRepository userRoadmapRepository,
                                  RoadmapTemplateRepository roadmapTemplateRepository,
                                  com.interview.platform.mod19_recommendation_engine.repository.UserBadgeRepository userBadgeRepository,
                                  ObjectMapper objectMapper) {
        this.userRoadmapRepository = userRoadmapRepository;
        this.roadmapTemplateRepository = roadmapTemplateRepository;
        this.userBadgeRepository = userBadgeRepository;
        this.objectMapper = objectMapper;
    }

    public UserRoadmapDto getActiveRoadmap(UUID userId) {
        Optional<UserRoadmapEntity> active = userRoadmapRepository.findByUserIdAndStatus(userId, "IN_PROGRESS");
        return active.map(this::mapToDto).orElse(null);
    }

    public UserRoadmapDto startRoadmap(UUID userId, UUID roadmapTemplateId, String techName) {
        // If one is active, keep it or mark it abandoned? For now, just return existing if present.
        Optional<UserRoadmapEntity> existing = userRoadmapRepository.findByUserIdAndStatus(userId, "IN_PROGRESS");
        if (existing.isPresent()) {
            return mapToDto(existing.get());
        }

        UUID finalTemplateId = roadmapTemplateId;
        if (finalTemplateId == null && techName != null) {
            // Find template by title or just use first available
            Optional<RoadmapTemplateEntity> template = roadmapTemplateRepository.findAll().stream()
                    .filter(t -> t.getTitle() != null && t.getTitle().contains(techName))
                    .findFirst();
            if (template.isPresent()) {
                finalTemplateId = template.get().getId();
            } else {
                finalTemplateId = roadmapTemplateRepository.findAll().stream().findFirst().map(RoadmapTemplateEntity::getId).orElse(null);
            }
        } else if (finalTemplateId == null) {
            finalTemplateId = roadmapTemplateRepository.findAll().stream().findFirst().map(RoadmapTemplateEntity::getId).orElse(null);
        }

        UserRoadmapEntity entity = new UserRoadmapEntity();
        entity.setUserId(userId);
        entity.setRoadmapTemplateId(finalTemplateId);
        entity.setStatus("IN_PROGRESS");
        entity.setProgressPercentage(0);
        entity.setCompletedTopics("[]");

        return mapToDto(userRoadmapRepository.saveAndFlush(entity));
    }

    public UserRoadmapDto completeTopic(UUID userId, UUID userRoadmapId, String topicName) {
        UserRoadmapEntity roadmap = userRoadmapRepository.findById(userRoadmapId)
                .orElseThrow(() -> new RuntimeException("Roadmap not found"));

        if (!roadmap.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        try {
            List<String> topics = objectMapper.readValue(roadmap.getCompletedTopics(), new TypeReference<List<String>>() {});
            if (!topics.contains(topicName)) {
                topics.add(topicName);
                roadmap.setCompletedTopics(objectMapper.writeValueAsString(topics));
            }

            // Calculate progress
            int totalTopics = getTotalTopicsForTemplate(roadmap.getRoadmapTemplateId());
            if (totalTopics > 0) {
                int progress = (int) (((double) topics.size() / totalTopics) * 100);
                roadmap.setProgressPercentage(Math.min(progress, 100));
            }

            if (roadmap.getProgressPercentage() >= 100 && !"COMPLETED".equals(roadmap.getStatus())) {
                roadmap.setStatus("COMPLETED");
                roadmap.setCompletedAt(LocalDateTime.now());
                
                // Award Badge
                com.interview.platform.mod19_recommendation_engine.model.UserBadgeEntity badge = new com.interview.platform.mod19_recommendation_engine.model.UserBadgeEntity();
                badge.setUserId(userId);
                badge.setBadgeName("Roadmap Master");
                badge.setIconName("Star");
                badge.setDescription("Completed all topics in your learning path.");
                userBadgeRepository.save(badge);
            }

            return mapToDto(userRoadmapRepository.save(roadmap));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing topics JSON", e);
        }
    }

    private int getTotalTopicsForTemplate(UUID templateId) {
        if (templateId == null) return 10; // Fallback
        return roadmapTemplateRepository.findById(templateId).map(template -> {
            try {
                // Assuming milestones JSON is a list of objects, we count them as topics
                List<Object> milestones = objectMapper.readValue(template.getMilestones(), new TypeReference<List<Object>>() {});
                return milestones.size();
            } catch (Exception e) {
                return 10; // Fallback
            }
        }).orElse(10);
    }

    private UserRoadmapDto mapToDto(UserRoadmapEntity entity) {
        UserRoadmapDto dto = new UserRoadmapDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setRoadmapTemplateId(entity.getRoadmapTemplateId());
        dto.setStatus(entity.getStatus());
        dto.setProgressPercentage(entity.getProgressPercentage());
        dto.setStartedAt(entity.getStartedAt());
        try {
            dto.setCompletedTopics(objectMapper.readValue(entity.getCompletedTopics(), new TypeReference<List<String>>() {}));
        } catch (Exception e) {
            dto.setCompletedTopics(new ArrayList<>());
        }
        return dto;
    }
}
