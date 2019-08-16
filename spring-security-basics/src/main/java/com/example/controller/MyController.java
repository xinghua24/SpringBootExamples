package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    // http://localhost:8080/
    @GetMapping("/")
    public String home() {
        return "Home";
    }

    // http://localhost:8080/user
    @GetMapping("/user")
    public String getUser() {
        return "User";
    }

    // http://localhost:8080/admin
    @GetMapping("/admin")
    public String getAdmin() {
        return "Admin";
    }

    // http://localhost:8080/logoutsuccess
    @GetMapping("/logoutsuccess")
    public String logoutsuccessful() {
        return "Logout is successful";
    }
}
