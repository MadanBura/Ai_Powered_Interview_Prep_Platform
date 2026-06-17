package com.interview.platform.mod01_authentication;

import com.interview.platform.mod01_authentication.dto.OtpRequestDto;
import com.interview.platform.mod01_authentication.dto.OtpVerifyDto;
import com.interview.platform.mod01_authentication.dto.AuthResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth/otp")
public class OtpLoginController {

    private final OtpLoginService service;

    public OtpLoginController(OtpLoginService service) {
        this.service = service;
    }

    @PostMapping("/request")
    public ResponseEntity<?> requestOtp(@Valid @RequestBody OtpRequestDto request) {
        service.requestOtp(request);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", Map.of("message", "OTP dispatched successfully"));
        resp.put("error", null);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@Valid @RequestBody OtpVerifyDto request) {
        AuthResponseDto data = service.verifyOtp(request);
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", true);
        resp.put("data", data);
        resp.put("error", null);
        return ResponseEntity.ok(resp);
    }
}
