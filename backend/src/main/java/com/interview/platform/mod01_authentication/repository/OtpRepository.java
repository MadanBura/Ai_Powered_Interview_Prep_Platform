package com.interview.platform.mod01_authentication.repository;

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
