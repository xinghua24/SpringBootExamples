package com.example.mongo.controller;

import com.example.mongo.entity.Employee;
import com.example.mongo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // http://localhost:8080/employees/
    @GetMapping("/")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // http://localhost:8080/employees/{id}
    @GetMapping("/{id}")
    public Optional<Employee> findById(@PathVariable String id) {
        return employeeService.findById(id);
    }

    // http://localhost:8080/employees/findByName?name=:name
    // e.g. http://localhost:8080/employees/findByName?name=Dave
    @GetMapping("/findByName")
    public Optional<Employee> findByName(@RequestParam("name") String name) {
        return employeeService.findByName(name);
    }

    // http://localhost:8080/employees/
    // e.g. curl -X POST  -H 'Content-Type: application/json' http://localhost:8080/employees/ -d '{"name": "Dave", "age": "25"}'
    @PostMapping("/")
    public Employee save(@RequestBody Employee e) {
        return employeeService.save(e);
    }

    // http://localhost:8080/employees/{id}
    // e.g. curl -X DELETE http://localhost:8080/employees/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        employeeService.delete(id);
    }
}
