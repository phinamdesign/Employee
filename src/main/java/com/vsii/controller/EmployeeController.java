package com.vsii.controller;

import com.vsii.model.Department;
import com.vsii.model.Employee;
import com.vsii.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> listEmployee(){
        List<Employee> employees = (List<Employee>) employeeService.findAll();

        if (employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id){
        Optional<Employee> employee = employeeService.findById(id);

        if (!employee.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee){
        employeeService.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @PathVariable Long id){
        Optional<Employee> employeeUpdate = employeeService.findById(id);

        if (!employeeUpdate.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        employeeUpdate.get().setName(employee.getName());
        employeeUpdate.get().setAddress(employee.getAddress());
        employeeService.save(employeeUpdate.get());
        return new ResponseEntity<>(employeeUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        Optional<Employee> employee = employeeService.findById(id);
        if (!employee.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
