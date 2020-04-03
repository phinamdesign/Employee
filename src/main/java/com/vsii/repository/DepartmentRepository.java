package com.vsii.repository;

import com.vsii.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Iterable<Department> findDepartmentById(Long id);
}
