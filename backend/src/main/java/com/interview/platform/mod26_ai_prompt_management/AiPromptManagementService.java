package com.interview.platform.mod26_ai_prompt_management;

import com.interview.platform.mod26_ai_prompt_management.repository.AiPromptRepository;
import com.interview.platform.mod26_ai_prompt_management.repository.AiPersonaRepository;
import com.interview.platform.mod26_ai_prompt_management.model.AiPersonaEntity;
import com.interview.platform.mod26_ai_prompt_management.model.AiPromptEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

@Service
public class AiPromptManagementService {
    
    private final AiPromptRepository repository;
    private final AiPersonaRepository personaRepository;

    public AiPromptManagementService(AiPromptRepository repository, AiPersonaRepository personaRepository) {
        this.repository = repository;
        this.personaRepository = personaRepository;
    }
    
    public int handle() {
        return 200; // Legacy stub for tests
    }

    private AiPersonaEntity findOrCreatePersona(String name) {
        if (name == null || name.trim().isEmpty()) {
            name = "Executive Interviewer";
        }
        List<AiPersonaEntity> personas = personaRepository.findAll();
        for (AiPersonaEntity p : personas) {
            if (name.equalsIgnoreCase(p.getName())) {
                return p;
            }
        }
        AiPersonaEntity p = new AiPersonaEntity();
        p.setName(name);
        p.setTone("Professional");
        p.setDescription("Auto-created for prompt persona: " + name);
        return personaRepository.save(p);
    }

    public java.util.Map<String, Object> createAiPrompt(java.util.Map<String, Object> req) {
        com.interview.platform.mod26_ai_prompt_management.model.AiPromptEntity entity = new com.interview.platform.mod26_ai_prompt_management.model.AiPromptEntity();
        if (req.containsKey("name")) {
            entity.setName((String) req.get("name"));
        } else {
            entity.setName("Untitled Prompt");
        }
        entity.setVersion(1);
        if (req.containsKey("content")) {
            entity.setContent((String) req.get("content"));
        } else {
            entity.setContent("Default Content");
        }
        if (req.containsKey("persona")) {
            String personaName = (String) req.get("persona");
            entity.setPersona(findOrCreatePersona(personaName));
        } else {
            entity.setPersona(findOrCreatePersona("Executive Interviewer"));
        }
        repository.save(entity);
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("id", entity.getId().toString());
        res.put("name", entity.getName());
        res.put("persona", entity.getPersona() != null ? entity.getPersona().getName() : "Executive Interviewer");
        res.put("status", "Active");
        res.put("version", "v" + entity.getVersion() + ".0");
        res.put("lastModified", "Just now");
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
            map.put("persona", entity.getPersona() != null ? entity.getPersona().getName() : "Executive Interviewer");
            map.put("status", "Active");
            map.put("version", "v" + (entity.getVersion() != null ? entity.getVersion() : 1) + ".0");
            map.put("lastModified", "Just now");
            mappedList.add(map);
        }
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("prompts", mappedList);
        return res;
    }
    
    public java.util.Map<String, Object> updateAiPrompt(java.util.Map<String, Object> req, String id) {
        java.util.Optional<com.interview.platform.mod26_ai_prompt_management.model.AiPromptEntity> opt = repository.findById(java.util.UUID.fromString(id));
        if (opt.isPresent()) {
            com.interview.platform.mod26_ai_prompt_management.model.AiPromptEntity entity = opt.get();
            if (req.containsKey("name")) entity.setName((String) req.get("name"));
            if (req.containsKey("content")) entity.setContent((String) req.get("content"));
            if (req.containsKey("persona")) {
                String personaName = (String) req.get("persona");
                entity.setPersona(findOrCreatePersona(personaName));
            }
            repository.save(entity);
            java.util.Map<String, Object> res = new java.util.HashMap<>();
            res.put("id", entity.getId().toString());
            res.put("name", entity.getName());
            res.put("persona", entity.getPersona() != null ? entity.getPersona().getName() : "Executive Interviewer");
            res.put("status", "Active");
            res.put("version", "v" + entity.getVersion() + ".0");
            res.put("lastModified", "Just now");
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
