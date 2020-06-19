package com.example.dataredis;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("customer")
public class Customer {
    @Id
    private Long id;

    private String name;
}
