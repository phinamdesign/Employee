package com.vsii.service;

import com.vsii.model.Department;

import java.util.Optional;

public interface DepartmentService {
    Optional<Department> findById(Long id);
    Iterable<Department> findAll();
    Department save(Department department);
    void delete(Long id);
}
