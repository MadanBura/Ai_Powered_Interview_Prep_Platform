package com.interview.platform.mod21_notifications;

import com.interview.platform.mod21_notifications.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationsService {
    
    private final NotificationRepository repository;

    public NotificationsService(NotificationRepository repository) {
        this.repository = repository;
    }
    
    public int handle() {
        return 200; // Legacy stub for tests
    }

    public java.util.Map<String, Object> registerDevice(java.util.Map<String, Object> req) {
        return new java.util.HashMap<>();
    }

}
