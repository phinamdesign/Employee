package com.vsii.service;

import com.vsii.model.Employee;

import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> findById(Long id);
    Iterable<Employee> findAll();
    Employee save(Employee employee);
    void delete(Long id);
}
