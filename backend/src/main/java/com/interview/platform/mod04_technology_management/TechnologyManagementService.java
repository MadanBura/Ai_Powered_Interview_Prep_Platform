package com.interview.platform.mod04_technology_management;

import com.interview.platform.mod04_technology_management.repository.TechnologyRepository;
import org.springframework.stereotype.Service;

@Service
public class TechnologyManagementService {
    
    private final TechnologyRepository repository;

    public TechnologyManagementService(TechnologyRepository repository) {
        this.repository = repository;
    }
    
    public int handle() {
        return 200; // Legacy stub for tests
    }

    public java.util.Map<String, Object> createTechnology(java.util.Map<String, Object> req) {
        com.interview.platform.mod04_technology_management.model.TechnologyEntity entity = new com.interview.platform.mod04_technology_management.model.TechnologyEntity();
        if (req.containsKey("name")) entity.setName((String) req.get("name"));
        if (req.containsKey("status")) entity.setStatus((String) req.get("status"));
        repository.save(entity);
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("id", entity.getId().toString());
        res.put("name", entity.getName());
        res.put("status", entity.getStatus());
        java.util.Map<String, Object> wrapper = new java.util.HashMap<>();
        wrapper.put("data", res);
        return wrapper;
    }
    
    public java.util.Map<String, Object> listTechnologys(java.util.Map<String, Object> req) {
        java.util.List<com.interview.platform.mod04_technology_management.model.TechnologyEntity> entities = repository.findAll();
        java.util.List<java.util.Map<String, Object>> mappedList = new java.util.ArrayList<>();
        for (com.interview.platform.mod04_technology_management.model.TechnologyEntity entity : entities) {
            java.util.Map<String, Object> map = new java.util.HashMap<>();
            map.put("id", entity.getId().toString());
            map.put("name", entity.getName());
            map.put("status", entity.getStatus());
            mappedList.add(map);
        }
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("data", mappedList);
        return res;
    }
    
    public java.util.Map<String, Object> updateTechnology(java.util.Map<String, Object> req, String id) {
        java.util.Optional<com.interview.platform.mod04_technology_management.model.TechnologyEntity> opt = repository.findById(java.util.UUID.fromString(id));
        if (opt.isPresent()) {
            com.interview.platform.mod04_technology_management.model.TechnologyEntity entity = opt.get();
            if (req.containsKey("name")) entity.setName((String) req.get("name"));
            if (req.containsKey("status")) entity.setStatus((String) req.get("status"));
            repository.save(entity);
            java.util.Map<String, Object> res = new java.util.HashMap<>();
            res.put("id", entity.getId().toString());
            res.put("name", entity.getName());
            res.put("status", entity.getStatus());
            return res;
        }
        return new java.util.HashMap<>();
    }
    
    public java.util.Map<String, Object> deleteTechnology(String id) {
        repository.deleteById(java.util.UUID.fromString(id));
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("deleted", true);
        return res;
    }

}
