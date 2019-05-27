package com.example.demo.health;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ServicesHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        String service1Health = checkService1Health();
        String service2Health = checkService2Health();

        Map<String, Object> serviceHealthMap = new HashMap<>();
        serviceHealthMap.put("service1", service1Health);
        serviceHealthMap.put("service2", service2Health);

        if ("up".equals(service1Health) && "up".equals(service2Health)) {
            return Health.up().withDetails(serviceHealthMap).build();
        } else {
            return Health.down().withDetails(serviceHealthMap).build();
        }
    }

    public String checkService1Health() {
        return "up";
    }

    public String checkService2Health() {
        return "up";
    }
}
