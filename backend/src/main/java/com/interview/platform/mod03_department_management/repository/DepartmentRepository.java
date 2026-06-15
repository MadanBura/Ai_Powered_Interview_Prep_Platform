package com.interview.platform.mod03_department_management.repository;

import com.interview.platform.mod03_department_management.model.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, UUID> {
}
