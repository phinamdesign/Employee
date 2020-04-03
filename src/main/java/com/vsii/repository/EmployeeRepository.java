package com.vsii.repository;

import com.vsii.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Iterable<Employee> findEmployeeById(Long id);
}
