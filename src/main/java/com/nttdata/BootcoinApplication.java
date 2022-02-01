package com.nttdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class BootcoinApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootcoinApplication.class, args);
	}

}
