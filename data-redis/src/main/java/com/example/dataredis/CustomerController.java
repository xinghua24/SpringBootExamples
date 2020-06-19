package com.example.dataredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    // GET localhost:8080/customers/all
    @GetMapping("/all")
    public Iterable<Customer> all() {
        return customerRepository.findAll();
    }

    // POST localhost:8080/cusotmers/save
    // curl -X POST -H 'Content-Type: application/json' localhost:8080/customers/save -d '{"name": "James"}'
    @PostMapping("/save")
    public Customer save(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    // DELETE localhost:8080/cusotmers/{id}
    // curl -X DELETE localhost:8080/customers/-2768644627155708900
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }
}
