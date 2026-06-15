package com.interview.platform.mod21_notifications.repository;

import com.interview.platform.mod21_notifications.model.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<NotificationEntity, UUID> {
}
