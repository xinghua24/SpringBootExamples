package com.example.demo;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:customdbsource.yml")
public class CustomDBConfig {
    
    @Autowired
    private Environment env; 
    
    public String getServiceUrl() {
        return env.getProperty("other-service.url");
    }
    
    @Bean(name="customDataSource")
    @Qualifier("customDataSource")
    public DataSource CustomDataSource() {
        return DataSourceBuilder.create()
            .driverClassName(env.getProperty("spring.datasource.customsource.driver-class-name"))
            .url(env.getProperty("spring.datasource.customsource.jdbc-url"))
            .username(env.getProperty("spring.datasource.customsource.username"))
            .password(env.getProperty("spring.datasource.customsource.password")).build();
    }
}
