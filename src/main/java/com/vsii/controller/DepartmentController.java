package com.vsii.controller;

import com.vsii.model.Department;
import com.vsii.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?> listDepartment(){
        List<Department> departments = (List<Department>) departmentService.findAll();

        if (departments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable Long id){
        Optional<Department> department = departmentService.findById(id);

        if (!department.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody Department department){
        departmentService.save(department);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(@RequestBody Department department, @PathVariable Long id){
        Optional<Department> departmentUpdate = departmentService.findById(id);

        if (!departmentUpdate.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        departmentUpdate.get().setName(department.getName());
        departmentService.save(departmentUpdate.get());
        return new ResponseEntity<>(departmentUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id){
        Optional<Department> department = departmentService.findById(id);
        if (!department.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        departmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
