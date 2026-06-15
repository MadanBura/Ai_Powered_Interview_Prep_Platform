package com.interview.platform.mod19_recommendation_engine.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.platform.mod19_recommendation_engine.model.TopicEntity;
import com.interview.platform.mod19_recommendation_engine.repository.TopicRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class TopicSeederService implements ApplicationRunner {

    private final TopicRepository topicRepository;
    private final ObjectMapper objectMapper;

    public TopicSeederService(TopicRepository topicRepository, ObjectMapper objectMapper) {
        this.topicRepository = topicRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        if (topicRepository.count() == 0) {
            try (InputStream is = new ClassPathResource("topics_seed.json").getInputStream()) {
                List<Map<String, Object>> topics = objectMapper.readValue(is, new TypeReference<List<Map<String, Object>>>() {});
                
                for (Map<String, Object> t : topics) {
                    TopicEntity entity = new TopicEntity();
                    entity.setTech((String) t.get("tech"));
                    entity.setLevel((String) t.get("level"));
                    entity.setTitle((String) t.get("title"));
                    
                    entity.setSubtopics(objectMapper.writeValueAsString(t.get("subtopics")));
                    entity.setCodeSnippet((String) t.get("codeSnippet"));
                    entity.setPros(objectMapper.writeValueAsString(t.get("pros")));
                    entity.setCons(objectMapper.writeValueAsString(t.get("cons")));
                    entity.setUseCases(objectMapper.writeValueAsString(t.get("useCases")));
                    entity.setTags(objectMapper.writeValueAsString(t.get("tags")));
                    
                    topicRepository.save(entity);
                }
                System.out.println("Topic table seeded with " + topics.size() + " entries.");
            } catch (Exception e) {
                System.err.println("Failed to seed topics table: " + e.getMessage());
            }
        }
    }
}
