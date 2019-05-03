package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.MyServiceConfig;

@RestController
public class ConfigController {
    @Autowired
    private MyServiceConfig myServiceConfig;
    
    @Autowired
    private Environment env;
    
    
    @Value("${my.someprop:some default value}")
    private String myProp;
    
    
    // http://localhost:8080/myServiceConfig
    @RequestMapping("/myServiceConfig")
    public MyServiceConfig getConfig() {
        return myServiceConfig;
    }
    
    // http://localhost:8080/app-name
    @RequestMapping("/app-name")
    public String getAppName() {
        return env.getProperty("app.intro");
    }
    
    // http://localhost:8080/myProp
    @RequestMapping("/myProp")
    public String getMyProp() {
        return myProp;
    }
}
