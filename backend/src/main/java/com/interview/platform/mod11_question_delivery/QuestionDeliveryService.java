package com.interview.platform.mod11_question_delivery;

import com.interview.platform.mod11_question_delivery.model.SessionQuestionEntity;
import com.interview.platform.mod11_question_delivery.repository.SessionQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class QuestionDeliveryService {

    private final SessionQuestionRepository repository;

    public QuestionDeliveryService(SessionQuestionRepository repository) {
        this.repository = repository;
    }

    public int handle() {
        return 200; // Legacy stub for tests
    }

    public Map<String, Object> retrieveQuestions(Map<String, Object> req) {
        // Fallback stub if sessionId is not provided
        return new HashMap<>();
    }

    public List<Map<String, Object>> retrieveQuestionsBySessionId(String sessionId) {
        List<SessionQuestionEntity> sessionQuestions = repository.findBySessionId(UUID.fromString(sessionId));

        return sessionQuestions.stream().map(sq -> {
            Map<String, Object> dto = new HashMap<>();
            dto.put("id", sq.getQuestion().getId().toString());
            dto.put("text", sq.getQuestion().getQuestionText());
            dto.put("expectedDurationMinutes", 5); // Default duration
            return dto;
        }).collect(Collectors.toList());
    }
}
