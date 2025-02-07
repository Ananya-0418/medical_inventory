package com.example.stock.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.stock")
@EnableMongoRepositories(basePackages = "com.example.stock.dao.repository")
public class MedicalApp {
	public static void main(String[] args) {
		SpringApplication.run(MedicalApp.class, args);
	}
}
