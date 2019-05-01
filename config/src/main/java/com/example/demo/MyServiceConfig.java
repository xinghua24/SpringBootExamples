package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "myservice", ignoreUnknownFields = true)
@Data
public class MyServiceConfig {
    private String url;

    private String username;

    private String password;
}
