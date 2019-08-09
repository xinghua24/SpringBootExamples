package com.example.mongo.controller;

import com.example.mongo.entity.Employee;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private MongoTemplate mongoTemplate;

    // http://localhost:8080/employees/
    // e.g. curl -X POST  -H 'Content-Type: application/json' http://localhost:8080/employees/ -d '{"name": "Dave", "age": "25"}'
    @PostMapping("/")
    public void save(@RequestBody Employee e) {
        mongoTemplate.save(e, "employees");
    }

    // http://localhost:8080/employees/{id}
    @GetMapping("/{id}")
    public Employee findById(@PathVariable String id) {
        Employee result = mongoTemplate.findById(id, Employee.class);
        return result;
    }

    // http://localhost:8080/employees/
    @GetMapping("/")
    public List<Employee> findAll() {
        List<Employee> result = mongoTemplate.findAll( Employee.class);
        return result;
    }

    // http://localhost:8080/employees/findByName?name=:name
    // e.g. http://localhost:8080/employees/findByName?name=Alice
    @GetMapping("/findByName")
    public Employee findByName(@RequestParam("name") String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        Employee result = mongoTemplate.findOne(query, Employee.class);
        return result;
    }

    // http://localhost:8080/employees/{id}
    // e.g. curl -X DELETE http://localhost:8080/employees/{id}
    @DeleteMapping("/{id}")
    public String remove(@PathVariable String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        DeleteResult result = mongoTemplate.remove(query, Employee.class);
        return result.toString();
    }
}
