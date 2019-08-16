package com.example.errorhandling.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping("/getError")
    public ResponseEntity<String> getError() {
        throw new RuntimeException("Some Exception is thrown.");
    }
}
