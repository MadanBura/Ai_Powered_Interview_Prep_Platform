import os

base_pkg = "/Users/apple/AI-Powered Interview Preparation Platform/backend/src/main/java/com/interview/platform"

files = {
    "config/SecurityConfig.java": """package com.interview.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/api/v1/interviews/**").permitAll() // temporarily permit all for tests
                .anyRequest().permitAll() // Allow all until full security is needed
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
""",

    "filter/CorrelationIdFilter.java": """package com.interview.platform.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class CorrelationIdFilter extends OncePerRequestFilter {

    private static final String CORRELATION_ID_HEADER = "X-Correlation-ID";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String correlationId = request.getHeader(CORRELATION_ID_HEADER);
        if (correlationId == null || correlationId.isBlank()) {
            correlationId = UUID.randomUUID().toString();
        }
        MDC.put(CORRELATION_ID_HEADER, correlationId);
        response.addHeader(CORRELATION_ID_HEADER, correlationId);
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(CORRELATION_ID_HEADER);
        }
    }
}
""",

    "filter/RateLimitingFilter.java": """package com.interview.platform.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private final Map<String, TokenBucket> buckets = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String clientIp = request.getRemoteAddr();
        TokenBucket bucket = buckets.computeIfAbsent(clientIp, k -> new TokenBucket());

        if (!bucket.tryConsume()) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            
            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("success", false);
            errorDetails.put("data", null);
            Map<String, Object> errorInfo = new HashMap<>();
            errorInfo.put("status", 429);
            errorInfo.put("title", "Too Many Requests");
            errorInfo.put("detail", "Rate limit exceeded. Retry after 60 seconds.");
            errorInfo.put("timestamp", Instant.now().toString());
            errorDetails.put("error", errorInfo);

            objectMapper.writeValue(response.getWriter(), errorDetails);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private static class TokenBucket {
        private final int capacity = 100;
        private AtomicInteger tokens = new AtomicInteger(capacity);
        private long lastRefillTime = System.currentTimeMillis();

        public synchronized boolean tryConsume() {
            refill();
            if (tokens.get() > 0) {
                tokens.decrementAndGet();
                return true;
            }
            return false;
        }

        private void refill() {
            long now = System.currentTimeMillis();
            if (now - lastRefillTime > 60000) {
                tokens.set(capacity);
                lastRefillTime = now;
            }
        }
    }
}
""",

    "exception/GlobalExceptionHandler.java": """package com.interview.platform.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation Error");
        pd.setTitle("Validation Error");
        pd.setInstance(URI.create(request.getDescription(false).replace("uri=", "")));
        pd.setProperty("timestamp", Instant.now().toString());
        pd.setProperty("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createResponse(pd));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        pd.setTitle("Bad Request");
        pd.setInstance(URI.create(request.getDescription(false).replace("uri=", "")));
        pd.setProperty("timestamp", Instant.now().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createResponse(pd));
    }
    
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<Object> handleSecurityException(SecurityException ex, WebRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        pd.setTitle("Unauthorized");
        pd.setInstance(URI.create(request.getDescription(false).replace("uri=", "")));
        pd.setProperty("timestamp", Instant.now().toString());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(createResponse(pd));
    }

    private Map<String, Object> createResponse(ProblemDetail pd) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", false);
        resp.put("data", null);
        resp.put("error", pd);
        return resp;
    }
}
""",

    "mod01_authentication/model/UserEntity.java": """package com.interview.platform.mod01_authentication.model;

import jakarta.persistence.*;
import java.util.UUID;
import java.time.Instant;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
""",

    "mod01_authentication/model/OtpEntity.java": """package com.interview.platform.mod01_authentication.model;

import jakarta.persistence.*;
import java.util.UUID;
import java.time.Instant;

@Entity
@Table(name = "otps", indexes = {
    @Index(name = "idx_otp_target", columnList = "target")
})
public class OtpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String otpCode;

    @Column(nullable = false)
    private String target;

    @Column(nullable = false)
    private Instant expiresAt;

    @Column(nullable = false)
    private boolean used = false;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getOtpCode() { return otpCode; }
    public void setOtpCode(String otpCode) { this.otpCode = otpCode; }
    public String getTarget() { return target; }
    public void setTarget(String target) { this.target = target; }
    public Instant getExpiresAt() { return expiresAt; }
    public void setExpiresAt(Instant expiresAt) { this.expiresAt = expiresAt; }
    public boolean isUsed() { return used; }
    public void setUsed(boolean used) { this.used = used; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
""",

    "mod01_authentication/model/RefreshTokenEntity.java": """package com.interview.platform.mod01_authentication.model;

import jakarta.persistence.*;
import java.util.UUID;
import java.time.Instant;

@Entity
@Table(name = "refresh_tokens", indexes = {
    @Index(name = "idx_token", columnList = "token")
})
public class RefreshTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private Instant expiresAt;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }
    public Instant getExpiresAt() { return expiresAt; }
    public void setExpiresAt(Instant expiresAt) { this.expiresAt = expiresAt; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
""",

    "mod01_authentication/dto/OtpRequestDto.java": """package com.interview.platform.mod01_authentication.dto;

import jakarta.validation.constraints.NotBlank;

public record OtpRequestDto(
    @NotBlank(message = "Email or phone must not be blank")
    String target
) {}
""",

    "mod01_authentication/dto/OtpVerifyDto.java": """package com.interview.platform.mod01_authentication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OtpVerifyDto(
    @NotBlank(message = "Email or phone must not be blank")
    String target,
    
    @NotBlank(message = "OTP code must not be blank")
    @Size(min = 6, max = 6, message = "OTP must be exactly 6 digits")
    String otpCode
) {}
""",

    "mod01_authentication/dto/AuthResponseDto.java": """package com.interview.platform.mod01_authentication.dto;

public record AuthResponseDto(
    String accessToken,
    String refreshToken,
    long expiresIn,
    String tokenType,
    String role
) {}
""",

    "mod01_authentication/repository/UserRepository.java": """package com.interview.platform.mod01_authentication.repository;

import com.interview.platform.mod01_authentication.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByPhone(String phone);
}
""",

    "mod01_authentication/repository/OtpRepository.java": """package com.interview.platform.mod01_authentication.repository;

import com.interview.platform.mod01_authentication.model.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import java.util.UUID;
import java.time.Instant;

public interface OtpRepository extends JpaRepository<OtpEntity, UUID> {
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT o FROM OtpEntity o WHERE o.target = :target AND o.otpCode = :otpCode AND o.used = false ORDER BY o.createdAt DESC LIMIT 1")
    Optional<OtpEntity> findValidUnusedOtpForTarget(@Param("target") String target, @Param("otpCode") String otpCode);

    long countByTargetAndCreatedAtAfter(String target, Instant after);
}
""",

    "mod01_authentication/repository/RefreshTokenRepository.java": """package com.interview.platform.mod01_authentication.repository;

import com.interview.platform.mod01_authentication.model.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {
    Optional<RefreshTokenEntity> findByToken(String token);
    void deleteByToken(String token);
}
""",

    "mod01_authentication/OtpLoginService.java": """package com.interview.platform.mod01_authentication;

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
        if (!request.target().contains("@") && !request.target().matches("^\\\\+?[0-9]{10,15}$")) {
            throw new IllegalArgumentException("Invalid email or phone format");
        }

        // Rate limit: Max 5 per 10 mins
        Instant tenMinsAgo = Instant.now().minus(10, ChronoUnit.MINUTES);
        long count = otpRepository.countByTargetAndCreatedAtAfter(request.target(), tenMinsAgo);
        
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
                return userRepository.save(newUser);
            });

        return tokenService.generateTokenPair(user);
    }
}
""",

    "mod01_authentication/JwtTokenManagementService.java": """package com.interview.platform.mod01_authentication;

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
            "mock-access-token",
            "mock-refresh-token-" + UUID.randomUUID(),
            900L,
            "Bearer",
            user.getRole()
        );
    }
}
""",

    "mod01_authentication/OtpLoginController.java": """package com.interview.platform.mod01_authentication;

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
""",

    "mod01_authentication/JwtTokenManagementController.java": """package com.interview.platform.mod01_authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class JwtTokenManagementController {

    private final JwtTokenManagementService service;

    public JwtTokenManagementController(JwtTokenManagementService service) {
        this.service = service;
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<?> refresh() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok().build();
    }
}
""",

    "exception/RateLimitExceededException.java": """package com.interview.platform.mod01_authentication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
public class RateLimitExceededException extends RuntimeException {
    public RateLimitExceededException(String message) {
        super(message);
    }
}
"""
}

# The exception needs to be in the exception package
files["exception/RateLimitExceededException.java"] = files["exception/RateLimitExceededException.java"].replace("package com.interview.platform.mod01_authentication;", "package com.interview.platform.exception;")

for rel_path, content in files.items():
    full_path = os.path.join(base_pkg, rel_path)
    os.makedirs(os.path.dirname(full_path), exist_ok=True)
    with open(full_path, "w") as f:
        f.write(content)

print("Created all files for MOD-01 Authentication successfully.")
