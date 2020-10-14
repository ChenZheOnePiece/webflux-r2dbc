package com.taiji.webfluxcurd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class WebfluxCurdApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxCurdApplication.class, args);
	}

}
