package com.interview.platform.mod01_authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class JwtTokenManagementController {

    private final JwtTokenManagementService service;

    public JwtTokenManagementController(JwtTokenManagementService service) {
        this.service = service;
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> body) {
        service.refreshToken(body.get("refreshToken"));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> body) {
        service.logout(body.get("refreshToken"));
        return ResponseEntity.ok().build();
    }
}
