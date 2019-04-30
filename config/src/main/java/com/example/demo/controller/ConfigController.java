package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.MyServiceConfig;

@RestController
public class ConfigController {
    @Autowired
    private MyServiceConfig myServiceConfig;
    
    // http://localhost:8080/myServiceConfig
    @RequestMapping("/myServiceConfig")
    public MyServiceConfig getConfig() {
        return myServiceConfig;
    }
}
