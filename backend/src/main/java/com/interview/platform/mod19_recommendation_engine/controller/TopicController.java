package com.interview.platform.mod19_recommendation_engine.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.interview.platform.mod19_recommendation_engine.repository.TopicRepository;

@RestController
@RequestMapping("/api/v1/roadmaps/topics")
public class TopicController {

    private final TopicRepository topicRepository;
    private final com.fasterxml.jackson.databind.ObjectMapper objectMapper;

    public TopicController(TopicRepository topicRepository, com.fasterxml.jackson.databind.ObjectMapper objectMapper) {
        this.topicRepository = topicRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{title}")
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
