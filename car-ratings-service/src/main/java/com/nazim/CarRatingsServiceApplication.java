package com.nazim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"com.nazim"})
@EnableMongoRepositories(basePackages = {"com.nazim.model", "com.nazim.repository"})
@EnableAsync
public class CarRatingsServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarRatingsServiceApplication.class, args);
	}
}
