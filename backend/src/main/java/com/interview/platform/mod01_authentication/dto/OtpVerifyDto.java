package com.interview.platform.mod01_authentication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OtpVerifyDto(
    @NotBlank(message = "Email or phone must not be blank")
    String target,
    
    @NotBlank(message = "OTP code must not be blank")
    @Size(min = 6, max = 6, message = "OTP must be exactly 6 digits")
    String otpCode
) {}
