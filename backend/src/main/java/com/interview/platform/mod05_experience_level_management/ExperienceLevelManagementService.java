package com.interview.platform.mod05_experience_level_management;

import com.interview.platform.mod05_experience_level_management.repository.ExperienceLevelRepository;
import org.springframework.stereotype.Service;

@Service
public class ExperienceLevelManagementService {
    
    private final ExperienceLevelRepository repository;

    public ExperienceLevelManagementService(ExperienceLevelRepository repository) {
        this.repository = repository;
    }
    
    public int handle() {
        return 200; // Legacy stub for tests
    }

    public java.util.Map<String, Object> createExperienceLevel(java.util.Map<String, Object> req) {
        com.interview.platform.mod05_experience_level_management.model.ExperienceLevelEntity entity = new com.interview.platform.mod05_experience_level_management.model.ExperienceLevelEntity();
        if (req.containsKey("name")) entity.setLabel((String) req.get("name")); entity.setRankVal(1);
        if (req.containsKey("status")) 
        repository.save(entity);
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("id", entity.getId().toString());
        res.put("name", entity.getLabel());
        
        java.util.Map<String, Object> wrapper = new java.util.HashMap<>();
        wrapper.put("data", res);
        return wrapper;
    }
    
    public java.util.Map<String, Object> listExperienceLevels(java.util.Map<String, Object> req) {
        java.util.List<com.interview.platform.mod05_experience_level_management.model.ExperienceLevelEntity> entities = repository.findAll();
        java.util.List<java.util.Map<String, Object>> mappedList = new java.util.ArrayList<>();
        for (com.interview.platform.mod05_experience_level_management.model.ExperienceLevelEntity entity : entities) {
            java.util.Map<String, Object> map = new java.util.HashMap<>();
            map.put("id", entity.getId().toString());
            map.put("name", entity.getLabel());
            
            mappedList.add(map);
        }
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("data", mappedList);
        return res;
    }
    
    public java.util.Map<String, Object> updateExperienceLevel(java.util.Map<String, Object> req, String id) {
        java.util.Optional<com.interview.platform.mod05_experience_level_management.model.ExperienceLevelEntity> opt = repository.findById(java.util.UUID.fromString(id));
        if (opt.isPresent()) {
            com.interview.platform.mod05_experience_level_management.model.ExperienceLevelEntity entity = opt.get();
            if (req.containsKey("name")) entity.setLabel((String) req.get("name")); entity.setRankVal(1);
            if (req.containsKey("status")) 
            repository.save(entity);
            java.util.Map<String, Object> res = new java.util.HashMap<>();
            res.put("id", entity.getId().toString());
            res.put("name", entity.getLabel());
            
            return res;
        }
        return new java.util.HashMap<>();
    }
    
    public java.util.Map<String, Object> deleteExperienceLevel(String id) {
        repository.deleteById(java.util.UUID.fromString(id));
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("deleted", true);
        return res;
    }

}
