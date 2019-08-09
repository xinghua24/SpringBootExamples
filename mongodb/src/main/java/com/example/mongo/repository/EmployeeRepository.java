package com.example.mongo.repository;

import com.example.mongo.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Optional<Employee> findByName(String name);
}
