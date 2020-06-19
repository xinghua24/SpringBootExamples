
To start redis using Docker
```bash
# Run Redis
docker run -d --rm -p 6379:6379 --name redis-demo redis
```

Need to add `@EnableRedisRepositories` annotation and create beans for connection factory and redisTemplate
```java
@SpringBootApplication
@EnableRedisRepositories
public class DataRedisApplication {

	@Bean
	public LettuceConnectionFactory jedisConnectionFactory() {
		return new LettuceConnectionFactory();
	}

	@Bean
	RedisTemplate<?, ?> redisTemplate() {
		RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DataRedisApplication.class, args);
	}

}
```

Create Entity
```java
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
```

Repository
```java
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByExternalId(String externalId);
}
```



Controller
```java
package com.example.dataredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    // GET localhost:8080/customers/all
    @GetMapping("/all")
    public Iterable<Customer> all() {
        return customerRepository.findAll();
    }

    // POST localhost:8080/cusotmers/save
    // curl -X POST -H 'Content-Type: application/json' localhost:8080/customers/save -d '{"name": "James"}'
    @PostMapping("/save")
    public Customer save(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    // DELETE localhost:8080/cusotmers/{id}
    // curl -X DELETE localhost:8080/customers/-2768644627155708900
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }
}
```