package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Customer;
import com.example.demo.repo.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepo;

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    public Optional<Customer> findById(Long id) {
        return customerRepo.findById(id);
    }

    public List<Customer> findByEmail(String email) {
        return customerRepo.findByEmail(email);
    }

    public List<Customer> findByRegDateRange(LocalDate start, LocalDate end) {
        return customerRepo.findByRegDateRange(start, end);
    }

    @Transactional(rollbackOn = IllegalArgumentException.class)
    public void deleteCustomer(Long customerId) {
        customerRepo.deleteById(customerId);

        if (customerId > 0) {
            throw new IllegalArgumentException();
        }
    }
}
