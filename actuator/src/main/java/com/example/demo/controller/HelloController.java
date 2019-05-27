package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    public class HelloController {
    
    // http://localhost:8080/hello
    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }
}

