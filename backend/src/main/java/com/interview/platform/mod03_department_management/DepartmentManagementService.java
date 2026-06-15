package com.interview.platform.mod03_department_management;

import com.interview.platform.mod03_department_management.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentManagementService {
    
    private final DepartmentRepository repository;

    public DepartmentManagementService(DepartmentRepository repository) {
        this.repository = repository;
    }
    
    public int handle() {
        return 200; // Legacy stub for tests
    }

    public java.util.Map<String, Object> createDepartment(java.util.Map<String, Object> req) {
        com.interview.platform.mod03_department_management.model.DepartmentEntity entity = new com.interview.platform.mod03_department_management.model.DepartmentEntity();
        if (req.containsKey("name")) entity.setName((String) req.get("name"));
        entity.setStatus("ACTIVE");
        
        repository.save(entity);
        
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("id", entity.getId().toString());
        res.put("name", entity.getName());
        res.put("status", entity.getStatus());
        
        java.util.Map<String, Object> wrapper = new java.util.HashMap<>();
        wrapper.put("department", res);
        return wrapper;
    }

    public java.util.Map<String, Object> listDepartments(java.util.Map<String, Object> req) {
        java.util.List<com.interview.platform.mod03_department_management.model.DepartmentEntity> entities = repository.findAll();
        java.util.List<java.util.Map<String, Object>> mappedList = new java.util.ArrayList<>();
        
        for (com.interview.platform.mod03_department_management.model.DepartmentEntity entity : entities) {
            java.util.Map<String, Object> map = new java.util.HashMap<>();
            map.put("id", entity.getId().toString());
            map.put("name", entity.getName());
            map.put("status", entity.getStatus());
            mappedList.add(map);
        }
        
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("departments", mappedList);
        return res;
    }

    public java.util.Map<String, Object> updateDepartment(java.util.Map<String, Object> req, String id) {
        java.util.Optional<com.interview.platform.mod03_department_management.model.DepartmentEntity> opt = repository.findById(java.util.UUID.fromString(id));
        if (opt.isPresent()) {
            com.interview.platform.mod03_department_management.model.DepartmentEntity entity = opt.get();
            if (req.containsKey("name")) entity.setName((String) req.get("name"));
            repository.save(entity);
            
            java.util.Map<String, Object> res = new java.util.HashMap<>();
            res.put("id", entity.getId().toString());
            res.put("name", entity.getName());
            res.put("status", entity.getStatus());
            
            java.util.Map<String, Object> wrapper = new java.util.HashMap<>();
            wrapper.put("department", res);
            return wrapper;
        }
        return new java.util.HashMap<>();
    }

    public java.util.Map<String, Object> deleteDepartment(String id) {
        repository.deleteById(java.util.UUID.fromString(id));
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("deleted", true);
        return res;
    }

}
