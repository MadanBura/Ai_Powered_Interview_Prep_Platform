package com.interview.platform.mod03_department_management.model;

import com.interview.platform.mod01_authentication.model.UserEntity;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "departments")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_user_id")
    private UserEntity headUser;

    @Column(nullable = false)
    private String status;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public UserEntity getHeadUser() { return headUser; }
    public void setHeadUser(UserEntity headUser) { this.headUser = headUser; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
