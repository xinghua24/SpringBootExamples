package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Scheduled(fixedRate = 10000L)
	public void someJob() throws InterruptedException {
		System.out.println("Current Time is " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
	}
}

