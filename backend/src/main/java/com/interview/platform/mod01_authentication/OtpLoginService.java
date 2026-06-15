package com.interview.platform.mod01_authentication;

import com.interview.platform.mod01_authentication.dto.OtpRequestDto;
import com.interview.platform.mod01_authentication.dto.OtpVerifyDto;
import com.interview.platform.mod01_authentication.dto.AuthResponseDto;
import com.interview.platform.mod01_authentication.model.OtpEntity;
import com.interview.platform.mod01_authentication.model.UserEntity;
import com.interview.platform.mod01_authentication.repository.OtpRepository;
import com.interview.platform.mod01_authentication.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.interview.platform.exception.RateLimitExceededException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class OtpLoginService {

    private static final Logger log = LoggerFactory.getLogger(OtpLoginService.class);
    private final OtpRepository otpRepository;
    private final UserRepository userRepository;
    private final JwtTokenManagementService tokenService;

    public OtpLoginService(OtpRepository otpRepository, UserRepository userRepository, JwtTokenManagementService tokenService) {
        this.otpRepository = otpRepository;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Transactional
    public void requestOtp(OtpRequestDto request) {
        // Validate email format
        if (!request.target().contains("@") && !request.target().matches("^\\+?[0-9]{10,15}$")) {
            throw new IllegalArgumentException("Invalid email or phone format");
        }

        // Rate limit: Max 5 per 10 mins
        Instant tenMinsAgo = Instant.now().minus(10, ChronoUnit.MINUTES);
        long count = otpRepository.countByTargetAndCreatedAtAfter(request.target(), tenMinsAgo);

        // Verify user exists in the database
        Optional<UserEntity> userOpt = userRepository.findByEmail(request.target());
        if (userOpt.isEmpty()) {
            UserEntity newUser = new UserEntity();
            newUser.setEmail(request.target());
            newUser.setRole("CANDIDATE");
            newUser.setPasswordHash("otp_user_no_password");
            newUser.setStatus("ACTIVE");
            userRepository.save(newUser);
        }
        
        // This simulates a 429 exception. A custom Exception would map to 429.
        if (count >= 5) {
            throw new RateLimitExceededException("Maximum OTP requests exceeded. Try again later.");
        }

        OtpEntity otp = new OtpEntity();
        otp.setTarget(request.target());
        otp.setOtpCode("123456"); // Fixed for mock purposes
        otp.setExpiresAt(Instant.now().plus(5, ChronoUnit.MINUTES));
        otpRepository.save(otp);
        
        log.info("OTP dispatched to {}", request.target());
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public AuthResponseDto verifyOtp(OtpVerifyDto request) {
        // Pessimistic lock lookup
        Optional<OtpEntity> otpOpt = otpRepository.findValidUnusedOtpForTarget(request.target(), request.otpCode());
        
        if (otpOpt.isEmpty()) {
            throw new SecurityException("Invalid OTP");
        }

        OtpEntity otp = otpOpt.get();
        if (otp.getExpiresAt().isBefore(Instant.now())) {
            throw new SecurityException("Expired OTP");
        }

        otp.setUsed(true);
        otpRepository.save(otp);

        UserEntity user = userRepository.findByEmail(request.target())
            .orElseGet(() -> {
                UserEntity newUser = new UserEntity();
                newUser.setEmail(request.target());
                newUser.setRole("CANDIDATE");
                newUser.setPasswordHash("otp_user_no_password");
                newUser.setStatus("ACTIVE");
                return userRepository.save(newUser);
            });

        return tokenService.generateTokenPair(user);
    }
}
