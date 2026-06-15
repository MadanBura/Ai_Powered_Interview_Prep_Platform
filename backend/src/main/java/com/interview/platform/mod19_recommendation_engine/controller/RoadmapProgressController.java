package com.interview.platform.mod19_recommendation_engine.controller;

import com.interview.platform.mod19_recommendation_engine.dto.UserRoadmapDto;
import com.interview.platform.mod19_recommendation_engine.service.RoadmapProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.interview.platform.mod01_authentication.model.UserEntity;
import com.interview.platform.mod01_authentication.repository.UserRepository;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/roadmaps/user")
public class RoadmapProgressController {

    private final RoadmapProgressService roadmapProgressService;
    private final UserRepository userRepository;
    private final com.interview.platform.mod19_recommendation_engine.repository.TopicRepository topicRepository;
    private final com.fasterxml.jackson.databind.ObjectMapper objectMapper;

    public RoadmapProgressController(RoadmapProgressService roadmapProgressService, UserRepository userRepository,
                                     com.interview.platform.mod19_recommendation_engine.repository.TopicRepository topicRepository,
                                     com.fasterxml.jackson.databind.ObjectMapper objectMapper) {
        this.roadmapProgressService = roadmapProgressService;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/active")
    public ResponseEntity<UserRoadmapDto> getActiveRoadmap(Authentication authentication) {
        if (authentication == null) return ResponseEntity.status(401).build();
        String email = authentication.getName();
        UserEntity user = userRepository.findAll().stream().filter(u -> email.equals(u.getEmail())).findFirst().orElseThrow(() -> new RuntimeException("User not found"));
        UUID userId = user.getId();
        UserRoadmapDto active = roadmapProgressService.getActiveRoadmap(userId);
        if (active == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(active);
    }

    @PostMapping("/start")
    public ResponseEntity<?> startRoadmap(Authentication authentication,
                                                       @RequestParam(required = false) UUID roadmapTemplateId,
                                                       @RequestParam(required = false) String techName) {
        try {
            if (authentication == null) return ResponseEntity.status(401).build();
            String email = authentication.getName();
            UserEntity user = userRepository.findAll().stream().filter(u -> email.equals(u.getEmail())).findFirst().orElseThrow(() -> new RuntimeException("User not found"));
            UUID userId = user.getId();
            return ResponseEntity.ok(roadmapProgressService.startRoadmap(userId, roadmapTemplateId, techName));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage() + "\n" + java.util.Arrays.toString(e.getStackTrace()));
        }
    }

    @PostMapping("/{userRoadmapId}/topics/{topicName}/complete")
    public ResponseEntity<UserRoadmapDto> completeTopic(Authentication authentication,
                                                        @PathVariable UUID userRoadmapId,
                                                        @PathVariable String topicName) {
        if (authentication == null) return ResponseEntity.status(401).build();
        String email = authentication.getName();
        UserEntity user = userRepository.findAll().stream().filter(u -> email.equals(u.getEmail())).findFirst().orElseThrow(() -> new RuntimeException("User not found"));
        UUID userId = user.getId();
        return ResponseEntity.ok(roadmapProgressService.completeTopic(userId, userRoadmapId, topicName));
    }

    @GetMapping("/topics/{title}")
    public ResponseEntity<com.interview.platform.mod19_recommendation_engine.dto.TopicDto> getTopic(@PathVariable String title) {
        return topicRepository.findByTitle(title).map(entity -> {
            com.interview.platform.mod19_recommendation_engine.dto.TopicDto dto = new com.interview.platform.mod19_recommendation_engine.dto.TopicDto();
            dto.setTitle(entity.getTitle());
            dto.setCodeSnippet(entity.getCodeSnippet());
            try {
                if (entity.getSubtopics() != null) dto.setSubtopics(objectMapper.readValue(entity.getSubtopics(), new com.fasterxml.jackson.core.type.TypeReference<java.util.List<String>>(){}));
                if (entity.getPros() != null) dto.setPros(objectMapper.readValue(entity.getPros(), new com.fasterxml.jackson.core.type.TypeReference<java.util.List<String>>(){}));
                if (entity.getCons() != null) dto.setCons(objectMapper.readValue(entity.getCons(), new com.fasterxml.jackson.core.type.TypeReference<java.util.List<String>>(){}));
                if (entity.getUseCases() != null) dto.setUseCases(objectMapper.readValue(entity.getUseCases(), new com.fasterxml.jackson.core.type.TypeReference<java.util.List<String>>(){}));
                if (entity.getTags() != null) dto.setTags(objectMapper.readValue(entity.getTags(), new com.fasterxml.jackson.core.type.TypeReference<java.util.List<String>>(){}));
            } catch (Exception e) {
                // ignore
            }
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
