package com.interview.platform.mod01_authentication;

import com.interview.platform.mod01_authentication.model.UserEntity;
import com.interview.platform.mod01_authentication.repository.UserRepository;
import com.interview.platform.mod03_department_management.model.DepartmentEntity;
import com.interview.platform.mod03_department_management.repository.DepartmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    public UserController(UserRepository userRepository, DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getMyProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }
        
        String email = auth.getName();
        Optional<UserEntity> userOpt = userRepository.findAll().stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst();

        if (userOpt.isPresent()) {
            return ResponseEntity.ok(Map.of("success", true, "data", mapToDto(userOpt.get())));
        } else {
            return ResponseEntity.status(404).body(Map.of("success", false, "error", "User not found"));
        }
    }

    @PutMapping("/me")
    public ResponseEntity<Map<String, Object>> updateMyProfile(@RequestBody Map<String, Object> body) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }
        
        String email = auth.getName();
        Optional<UserEntity> userOpt = userRepository.findAll().stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst();

        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            
            if (body.containsKey("name")) {
                user.setName((String) body.get("name"));
            }
            if (body.containsKey("departmentId")) {
                String deptId = (String) body.get("departmentId");
                if (deptId != null) {
                    departmentRepository.findById(UUID.fromString(deptId))
                            .ifPresent(user::setDepartment);
                }
            }
            if (body.containsKey("technologies")) {
                user.setTechnologies((String) body.get("technologies"));
            }
            if (body.containsKey("experienceLevel")) {
                user.setExperienceLevel((String) body.get("experienceLevel"));
            }

            userRepository.save(user);
            return ResponseEntity.ok(Map.of("success", true, "data", mapToDto(user)));
        } else {
            return ResponseEntity.status(404).body(Map.of("success", false, "error", "User not found"));
        }
    }

    private Map<String, Object> mapToDto(UserEntity user) {
        Map<String, Object> dto = new HashMap<>();
        dto.put("id", user.getId() != null ? user.getId().toString() : "");
        dto.put("name", user.getName() != null ? user.getName() : "Unknown");
        dto.put("email", user.getEmail());
        dto.put("role", user.getRole());
        dto.put("status", user.getStatus());
        dto.put("department", user.getDepartment() != null ? user.getDepartment().getName() : null);
        dto.put("departmentId", user.getDepartment() != null && user.getDepartment().getId() != null ? user.getDepartment().getId().toString() : null);
        dto.put("technologies", user.getTechnologies());
        dto.put("experienceLevel", user.getExperienceLevel());
        dto.put("initial", user.getName() != null && !user.getName().isEmpty() ? user.getName().substring(0, 1).toUpperCase() : "U");
        return dto;
    }
}
