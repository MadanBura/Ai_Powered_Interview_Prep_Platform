package com.interview.platform.mod19_recommendation_engine.controller;

import com.interview.platform.mod19_recommendation_engine.dto.UserBadgeDto;
import com.interview.platform.mod19_recommendation_engine.service.BadgeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.interview.platform.mod01_authentication.model.UserEntity;
import com.interview.platform.mod01_authentication.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/badges")
public class BadgeController {

    private final BadgeService badgeService;
    private final UserRepository userRepository;

    public BadgeController(BadgeService badgeService, UserRepository userRepository) {
        this.badgeService = badgeService;
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserBadgeDto>> getUserBadges(Authentication authentication) {
        if (authentication == null) return ResponseEntity.status(401).build();
        String email = authentication.getName();
        UserEntity user = userRepository.findAll().stream().filter(u -> email.equals(u.getEmail())).findFirst().orElseThrow(() -> new RuntimeException("User not found"));
        UUID userId = user.getId();
        return ResponseEntity.ok(badgeService.getUserBadges(userId));
    }

    // Usually awarded internally, but keeping endpoint for testing/admin
    @PostMapping("/award")
    public ResponseEntity<UserBadgeDto> awardBadge(Authentication authentication,
                                                   @RequestParam String badgeName,
                                                   @RequestParam String iconName,
                                                   @RequestParam String description) {
        if (authentication == null) return ResponseEntity.status(401).build();
        String email = authentication.getName();
        UserEntity user = userRepository.findAll().stream().filter(u -> email.equals(u.getEmail())).findFirst().orElseThrow(() -> new RuntimeException("User not found"));
        UUID userId = user.getId();
        return ResponseEntity.ok(badgeService.awardBadge(userId, badgeName, iconName, description));
    }
}
