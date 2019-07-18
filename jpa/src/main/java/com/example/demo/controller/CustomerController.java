package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // localhost:8080/customers
    @RequestMapping("")
    public List<Customer> getCustomers() {
	return customerService.findAll();
    }

    // localhost:8080/customers?email=alice@example.com
    @RequestMapping(value = "/", params = "email")
    public List<Customer> getCustomerByEmail(@RequestParam("email") String email) {
	return customerService.findByEmail(email);
    }

    // http://localhost:8080/customers/byregdate?start=2019-01-01&end=2019-01-31
    @RequestMapping(value = "/byregdate", params = { "start", "end" })
    public List<Customer> getCustomerByEmail(
	    @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
	    @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
	return customerService.findByRegDateRange(start, end);
    }

    // localhost:8080/customers/testTransaction
    @RequestMapping("/testTransaction")
    public String testTransaction() {
	customerService.deleteCustomer(2L);
	return "delete completed";
    }
}
