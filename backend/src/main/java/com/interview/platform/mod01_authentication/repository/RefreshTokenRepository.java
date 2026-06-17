package com.interview.platform.mod01_authentication.repository;

import com.interview.platform.mod01_authentication.model.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {
    Optional<RefreshTokenEntity> findByJti(String jti);
    void deleteByJti(String jti);
}
