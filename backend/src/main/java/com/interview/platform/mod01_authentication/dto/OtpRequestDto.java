package com.interview.platform.mod01_authentication.dto;

import jakarta.validation.constraints.NotBlank;

public record OtpRequestDto(
    @NotBlank(message = "Email or phone must not be blank")
    String target
) {}
