package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyServiceConfig {
    @Bean
    @ConfigurationProperties(prefix = "myservice", ignoreUnknownFields = true)
    public MyService myService() {
        return new MyService();
    }
}
