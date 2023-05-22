package ru.oreshnikova.marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class RuMarketPlaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuMarketPlaceApplication.class, args);
	}

}
