package com.interview.platform.mod26_ai_prompt_management;

import com.interview.platform.mod26_ai_prompt_management.repository.AiPromptRepository;
import org.springframework.stereotype.Service;

@Service
public class AiPromptManagementService {
    
    private final AiPromptRepository repository;

    public AiPromptManagementService(AiPromptRepository repository) {
        this.repository = repository;
    }
    
    public int handle() {
        return 200; // Legacy stub for tests
    }

    public java.util.Map<String, Object> createAiPrompt(java.util.Map<String, Object> req) {
        com.interview.platform.mod26_ai_prompt_management.model.AiPromptEntity entity = new com.interview.platform.mod26_ai_prompt_management.model.AiPromptEntity();
        if (req.containsKey("name")) entity.setName((String) req.get("name"));
        entity.setVersion(1);
        entity.setContent("Default Content");
        repository.save(entity);
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("id", entity.getId().toString());
        res.put("name", entity.getName());
        java.util.Map<String, Object> wrapper = new java.util.HashMap<>();
        wrapper.put("data", res);
        return wrapper;
    }
    
    public java.util.Map<String, Object> listAiPrompts(java.util.Map<String, Object> req) {
        java.util.List<com.interview.platform.mod26_ai_prompt_management.model.AiPromptEntity> entities = repository.findAll();
        java.util.List<java.util.Map<String, Object>> mappedList = new java.util.ArrayList<>();
        for (com.interview.platform.mod26_ai_prompt_management.model.AiPromptEntity entity : entities) {
            java.util.Map<String, Object> map = new java.util.HashMap<>();
            map.put("id", entity.getId().toString());
            map.put("name", entity.getName());
            mappedList.add(map);
        }
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("data", mappedList);
        return res;
    }
    
    public java.util.Map<String, Object> updateAiPrompt(java.util.Map<String, Object> req, String id) {
        java.util.Optional<com.interview.platform.mod26_ai_prompt_management.model.AiPromptEntity> opt = repository.findById(java.util.UUID.fromString(id));
        if (opt.isPresent()) {
            com.interview.platform.mod26_ai_prompt_management.model.AiPromptEntity entity = opt.get();
            if (req.containsKey("name")) entity.setName((String) req.get("name"));
            repository.save(entity);
            java.util.Map<String, Object> res = new java.util.HashMap<>();
            res.put("id", entity.getId().toString());
            res.put("name", entity.getName());
            return res;
        }
        return new java.util.HashMap<>();
    }
    
    public java.util.Map<String, Object> deleteAiPrompt(String id) {
        repository.deleteById(java.util.UUID.fromString(id));
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("deleted", true);
        return res;
    }

    public java.util.Map<String, Object> activatePrompt(java.util.Map<String, Object> req) {
        java.util.Map<String, Object> res = new java.util.HashMap<>(req);
        res.put("status", "ACTIVE");
        return res;
    }

}
