package com.interview.platform.mod01_authentication;

import com.interview.platform.mod01_authentication.dto.AuthResponseDto;
import com.interview.platform.mod01_authentication.model.UserEntity;
import com.interview.platform.mod01_authentication.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JwtTokenManagementService {

    private final RefreshTokenRepository refreshTokenRepository;

    public JwtTokenManagementService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public AuthResponseDto generateTokenPair(UserEntity user) {
        // Mock generation
        return new AuthResponseDto(
            "mock-access-token:" + user.getEmail(),
            "mock-refresh-token-" + UUID.randomUUID(),
            900L,
            "Bearer",
            user.getRole()
        );
    }
    
    public void refreshToken(String refreshToken) {
        // Mock token validation
    }
    
    public void logout(String refreshToken) {
        // Mock token invalidation
    }
}
