package it.sysman.elasticRestaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("it.sysman.elasticRestaurant")
public class ElasticRestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticRestaurantApplication.class, args);
	}

}
