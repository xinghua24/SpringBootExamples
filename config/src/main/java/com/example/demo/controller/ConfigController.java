package com.example.demo.controller;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.MyService;

@RestController
public class ConfigController {
    @Autowired
    private MyService myService;
    
    @Autowired
    @Qualifier("customDataSource")
    private DataSource customDataSource;
    
    @Autowired
    private Environment env;
    
    
    @Value("${my.someprop:some default value}")
    private String myProp;
    
    
    // http://localhost:8080/myServiceConfig
    @RequestMapping("/myServiceConfig")
    public MyService getConfig() {
        return myService;
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
    
    // http://localhost:8080/customDataSource
    @RequestMapping("/customDataSource")
    public String getOtherServiceUrl() throws SQLException {
        return customDataSource.getConnection().toString();
    }
}
