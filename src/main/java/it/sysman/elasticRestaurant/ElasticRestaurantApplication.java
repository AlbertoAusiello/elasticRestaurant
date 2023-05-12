package it.sysman.elasticRestaurant;

import java.io.File;
import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.sysman.elasticRestaurant.dto.RestaurantDTO;
import it.sysman.elasticRestaurant.model.Address;
import it.sysman.elasticRestaurant.service.RestaurantServiceIMPL;
import it.sysman.elasticRestaurant.tools.JsonConverter;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan("it.sysman.elasticRestaurant")
public class ElasticRestaurantApplication {
	 
	public static void main(String[] args) {
		SpringApplication.run(ElasticRestaurantApplication.class, args);
		
		    }
	
	
}
