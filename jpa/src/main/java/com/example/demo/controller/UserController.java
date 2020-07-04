package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    // localhost:8080/users
    @RequestMapping("")
    public List<User> getUsers() {
	    return userService.findAll();
    }

    // localhost:8080/users?id=1
    @RequestMapping(value = "/", params = "id")
    public User getUsersById(@RequestParam("id") Long id) {
	return userService.findById(id).orElse(null);
    }

    // http://localhost:8080/users/bydate?start=2019-01-01&end=2019-01-31
    @RequestMapping(value = "/bydate", params = { "start", "end" })
    public List<User> getCustomerByEmail(
	    @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
	    @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
	return userService.findByDateRange(start, end);
    }

    // localhost:8080/users/testTransaction
    @RequestMapping("/testTransaction")
    public String testTransaction() {
        userService.testTransaction();
        return "test transaction complete";
    }
}
