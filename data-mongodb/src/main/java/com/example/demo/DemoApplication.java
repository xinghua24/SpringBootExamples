package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableMongoRepositories
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        Product iphone =
                Product.builder().name("iPhone").category("Electronic").price(1000.00).created(new Date()).build();
        Product samsungTV =
                Product.builder().name("Samsung TV").category("Electronic").price(300.00).created(new Date()).build();
        Product pillow =
                Product.builder().name("Pillow").category("Home").price(200.0).created(new Date()).build();

        // Insert
        List<Product> productList = List.of(iphone, samsungTV, pillow);
        productRepository.insert(productList);


        // Query
        List<Product> electronics = productRepository.findByCategory("Electronic");
        System.out.println("----electronics----");
        electronics.forEach(System.out::println);

        // Query by Example
        Optional<Product> iphoneFromMongo =
                productRepository.findOne(Example.of(Product.builder().name("iPhone").build(), ExampleMatcher.matchingAny()));

        // Update
        iphoneFromMongo.ifPresentOrElse(ip -> {
            ip.setPrice(1200);
            productRepository.save(ip);
        }, () -> {
            System.err.println("iPhone not found :-(");
        });

        // Query all
        System.out.println("---findAll----");
        productRepository.findAll().forEach(System.out::println);

        // Delete
        productRepository.deleteAll();
    }
}
