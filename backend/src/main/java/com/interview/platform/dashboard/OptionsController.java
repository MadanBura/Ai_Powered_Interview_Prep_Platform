package com.interview.platform.dashboard;

import com.interview.platform.mod03_department_management.repository.DepartmentRepository;
import com.interview.platform.mod03_department_management.model.DepartmentEntity;
import com.interview.platform.mod04_technology_management.repository.TechnologyRepository;
import com.interview.platform.mod04_technology_management.model.TechnologyEntity;
import com.interview.platform.mod05_experience_level_management.repository.ExperienceLevelRepository;
import com.interview.platform.mod05_experience_level_management.model.ExperienceLevelEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/admin/options")
@CrossOrigin(origins = "*")
public class OptionsController {

    private final DepartmentRepository departmentRepository;
    private final TechnologyRepository technologyRepository;
    private final ExperienceLevelRepository experienceLevelRepository;

    public OptionsController(DepartmentRepository departmentRepository, TechnologyRepository technologyRepository, ExperienceLevelRepository experienceLevelRepository) {
        this.departmentRepository = departmentRepository;
        this.technologyRepository = technologyRepository;
        this.experienceLevelRepository = experienceLevelRepository;
    }

    @GetMapping("/departments")
    public ResponseEntity<Map<String, Object>> getDepartments() {
        List<Map<String, String>> departments = departmentRepository.findAll().stream()
                .map(d -> Map.of("id", d.getId().toString(), "name", d.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(Map.of("success", true, "data", departments));
    }

    @GetMapping("/technologies")
    public ResponseEntity<Map<String, Object>> getTechnologies() {
        List<Map<String, String>> technologies = technologyRepository.findAll().stream()
                .map(t -> Map.of("id", t.getId().toString(), "name", t.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(Map.of("success", true, "data", technologies));
    }

    @GetMapping("/experience-levels")
    public ResponseEntity<Map<String, Object>> getExperienceLevels() {
        List<Map<String, String>> levels = experienceLevelRepository.findAll().stream()
                .map(l -> Map.of("id", l.getId().toString(), "name", l.getLabel()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(Map.of("success", true, "data", levels));
    }
}
