package com.nazim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"com.nazim"})
@EnableMongoRepositories(basePackages = {"com.nazim.model", "com.nazim.repository"})
@EnableAsync
public class CarInfoServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarInfoServiceApplication.class, args);
	}
}
