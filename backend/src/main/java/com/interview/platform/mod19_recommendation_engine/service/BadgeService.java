package com.interview.platform.mod19_recommendation_engine.service;

import com.interview.platform.mod19_recommendation_engine.dto.UserBadgeDto;
import com.interview.platform.mod19_recommendation_engine.model.UserBadgeEntity;
import com.interview.platform.mod19_recommendation_engine.repository.UserBadgeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BadgeService {

    private final UserBadgeRepository userBadgeRepository;

    public BadgeService(UserBadgeRepository userBadgeRepository) {
        this.userBadgeRepository = userBadgeRepository;
    }

    public List<UserBadgeDto> getUserBadges(UUID userId) {
        return userBadgeRepository.findAllByUserIdOrderByEarnedAtDesc(userId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public UserBadgeDto awardBadge(UUID userId, String badgeName, String iconName, String description) {
        UserBadgeEntity entity = new UserBadgeEntity();
        entity.setUserId(userId);
        entity.setBadgeName(badgeName);
        entity.setIconName(iconName);
        entity.setDescription(description);
        
        UserBadgeEntity saved = userBadgeRepository.save(entity);
        return mapToDto(saved);
    }

    private UserBadgeDto mapToDto(UserBadgeEntity entity) {
        UserBadgeDto dto = new UserBadgeDto();
        dto.setId(entity.getId());
        dto.setBadgeName(entity.getBadgeName());
        dto.setIconName(entity.getIconName());
        dto.setDescription(entity.getDescription());
        dto.setEarnedAt(entity.getEarnedAt());
        return dto;
    }
}
