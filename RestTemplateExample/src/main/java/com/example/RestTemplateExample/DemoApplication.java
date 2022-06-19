package com.example.RestTemplateExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private MyService myService;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		myService.simpleGet();
		myService.exchangeWithUriVariables();
		myService.simplePost();
		myService.exchangePost();
		myService.exchangePut();
		myService.simpleDelete();
	}
}
