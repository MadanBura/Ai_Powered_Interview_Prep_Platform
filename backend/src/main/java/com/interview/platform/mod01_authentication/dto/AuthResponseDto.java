package com.interview.platform.mod01_authentication.dto;

public record AuthResponseDto(
    String accessToken,
    String refreshToken,
    long expiresIn,
    String tokenType,
    String role
) {}
