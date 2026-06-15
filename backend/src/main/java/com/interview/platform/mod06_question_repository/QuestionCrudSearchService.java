package com.interview.platform.mod06_question_repository;

import com.interview.platform.mod06_question_repository.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionCrudSearchService {
    
    private final QuestionRepository repository;

    public QuestionCrudSearchService(QuestionRepository repository) {
        this.repository = repository;
    }
    
    public int handle() {
        return 200; // Legacy stub for tests
    }

    public java.util.Map<String, Object> createQuestion(java.util.Map<String, Object> req) {
        com.interview.platform.mod06_question_repository.model.QuestionEntity entity = new com.interview.platform.mod06_question_repository.model.QuestionEntity();
        if (req.containsKey("title")) entity.setQuestionText((String) req.get("title"));
        if (req.containsKey("category")) entity.setCategory((String) req.get("category"));
        if (req.containsKey("difficulty")) entity.setDifficulty((String) req.get("difficulty"));
        entity.setStatus("ACTIVE");
        
        repository.save(entity);
        
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("id", entity.getId().toString());
        res.put("title", entity.getQuestionText());
        res.put("category", entity.getCategory());
        res.put("difficulty", entity.getDifficulty());
        res.put("status", entity.getStatus());
        
        java.util.Map<String, Object> wrapper = new java.util.HashMap<>();
        wrapper.put("question", res);
        return wrapper;
    }

    public java.util.Map<String, Object> listQuestions(java.util.Map<String, Object> req) {
        java.util.List<com.interview.platform.mod06_question_repository.model.QuestionEntity> entities = repository.findAll();
        java.util.List<java.util.Map<String, Object>> mappedList = new java.util.ArrayList<>();
        
        for (com.interview.platform.mod06_question_repository.model.QuestionEntity entity : entities) {
            java.util.Map<String, Object> map = new java.util.HashMap<>();
            map.put("id", entity.getId().toString());
            map.put("title", entity.getQuestionText());
            map.put("category", entity.getCategory());
            map.put("difficulty", entity.getDifficulty());
            map.put("status", entity.getStatus());
            mappedList.add(map);
        }
        
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("questions", mappedList);
        return res;
    }

    public java.util.Map<String, Object> updateQuestion(java.util.Map<String, Object> req, String id) {
        java.util.Optional<com.interview.platform.mod06_question_repository.model.QuestionEntity> opt = repository.findById(java.util.UUID.fromString(id));
        if (opt.isPresent()) {
            com.interview.platform.mod06_question_repository.model.QuestionEntity entity = opt.get();
            if (req.containsKey("title")) entity.setQuestionText((String) req.get("title"));
            if (req.containsKey("category")) entity.setCategory((String) req.get("category"));
            if (req.containsKey("difficulty")) entity.setDifficulty((String) req.get("difficulty"));
            if (req.containsKey("status")) entity.setStatus((String) req.get("status"));
            
            repository.save(entity);
            
            java.util.Map<String, Object> res = new java.util.HashMap<>();
            res.put("id", entity.getId().toString());
            res.put("title", entity.getQuestionText());
            res.put("category", entity.getCategory());
            res.put("difficulty", entity.getDifficulty());
            res.put("status", entity.getStatus());
            
            java.util.Map<String, Object> wrapper = new java.util.HashMap<>();
            wrapper.put("question", res);
            return wrapper;
        }
        return new java.util.HashMap<>();
    }

    public java.util.Map<String, Object> deleteQuestion(String id) {
        repository.deleteById(java.util.UUID.fromString(id));
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("deleted", true);
        return res;
    }

    public java.util.Map<String, Object> generateQuestions() {
        java.util.List<com.interview.platform.mod06_question_repository.model.QuestionEntity> list = new java.util.ArrayList<>();
        for (int i = 0; i < 10; i++) {
            com.interview.platform.mod06_question_repository.model.QuestionEntity entity = new com.interview.platform.mod06_question_repository.model.QuestionEntity();
            entity.setQuestionText("Auto-generated AI question " + (i + 1));
            entity.setCategory("Technical");
            entity.setDifficulty("Mid");
            entity.setStatus("ACTIVE");
            list.add(entity);
        }
        repository.saveAll(list);
        
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("generated", 10);
        return res;
    }

}
