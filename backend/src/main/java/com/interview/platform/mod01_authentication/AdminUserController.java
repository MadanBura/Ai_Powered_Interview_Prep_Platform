package com.interview.platform.mod01_authentication;

import com.interview.platform.mod01_authentication.model.UserEntity;
import com.interview.platform.mod01_authentication.repository.UserRepository;
import com.interview.platform.mod03_department_management.model.DepartmentEntity;
import com.interview.platform.mod03_department_management.repository.DepartmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api/v1/admin/users")
@CrossOrigin(origins = "*")
public class AdminUserController {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    public AdminUserController(UserRepository userRepository, DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> listUsers() {
        List<UserEntity> users = userRepository.findAll();
        List<Map<String, Object>> userList = users.stream().map(this::mapToDto).collect(Collectors.toList());
        return ResponseEntity.ok(Map.of("success", true, "data", Map.of("users", userList)));
    }

    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object>> getProfile() {
        // Return dummy profile for admin since auth context might not be fully populated with user entity
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth != null ? auth.getName() : "admin@system.local";
        
        // Find user or return default
        UserEntity user = userRepository.findAll().stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst()
                .orElse(null);
                
        Map<String, Object> dto = new HashMap<>();
        if (user != null) {
            dto = mapToDto(user);
        } else {
            dto.put("id", UUID.randomUUID().toString());
            dto.put("name", "System Administrator");
            dto.put("email", email);
            dto.put("role", "SUPER_ADMIN");
            dto.put("status", "ACTIVE");
        }
        
        return ResponseEntity.ok(Map.of("success", true, "data", dto));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody Map<String, String> request) {
        UserEntity user = new UserEntity();
        user.setName(request.get("name"));
        user.setEmail(request.get("email"));
        user.setRole(request.get("role"));
        user.setStatus("ACTIVE");
        user.setPasswordHash("hashed_dummy_password");

        String deptName = request.get("dept");
        if (deptName != null) {
            Optional<DepartmentEntity> deptOpt = departmentRepository.findAll().stream()
                    .filter(d -> d.getName().equalsIgnoreCase(deptName)).findFirst();
            deptOpt.ifPresent(user::setDepartment);
        }

        userRepository.save(user);
        return ResponseEntity.ok(Map.of("success", true, "data", Map.of("user", mapToDto(user))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable UUID id, @RequestBody Map<String, String> request) {
        Optional<UserEntity> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) return ResponseEntity.notFound().build();

        UserEntity user = userOpt.get();
        if (request.containsKey("name")) user.setName(request.get("name"));
        if (request.containsKey("email")) user.setEmail(request.get("email"));
        if (request.containsKey("role")) user.setRole(request.get("role"));

        String deptName = request.get("dept");
        if (deptName != null) {
            Optional<DepartmentEntity> deptOpt = departmentRepository.findAll().stream()
                    .filter(d -> d.getName().equalsIgnoreCase(deptName)).findFirst();
            deptOpt.ifPresent(user::setDepartment);
        }

        userRepository.save(user);
        return ResponseEntity.ok(Map.of("success", true, "data", Map.of("user", mapToDto(user))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable UUID id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("success", true));
    }

    private Map<String, Object> mapToDto(UserEntity user) {
        Map<String, Object> dto = new HashMap<>();
        dto.put("id", user.getId().toString());
        dto.put("name", user.getName() != null ? user.getName() : "Unknown");
        dto.put("email", user.getEmail());
        dto.put("role", user.getRole());
        dto.put("status", user.getStatus());
        dto.put("dept", user.getDepartment() != null ? user.getDepartment().getName() : "Unknown");
        dto.put("initial", user.getName() != null && !user.getName().isEmpty() ? user.getName().substring(0, 1).toUpperCase() : "U");
        return dto;
    }
}
