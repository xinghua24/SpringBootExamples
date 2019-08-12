package com.example.mongo.service;

import com.example.mongo.entity.Employee;
import com.example.mongo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repo;

    public List<Employee> findAll() {
        return repo.findAll();
    }

    public Optional<Employee> findById(String id) {
        return repo.findById(id);
    }

    public Optional<Employee> findByName(String name) {
        return repo.findByName(name);
    }

    public Employee save(Employee e) {
        return repo.save(e);
    }

    public void delete(String id) {
        repo.findById(id).ifPresent(e -> repo.delete(e));
    }
}
