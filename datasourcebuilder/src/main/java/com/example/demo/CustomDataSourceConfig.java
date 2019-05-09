package com.example.demo;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CustomDataSourceConfig {

    @Primary
    @Bean(name = "customDataSource")
    @Qualifier("customDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.customsource")
    public DataSource CustomDataSource() {
        return DataSourceBuilder.create().build();
    }
}
