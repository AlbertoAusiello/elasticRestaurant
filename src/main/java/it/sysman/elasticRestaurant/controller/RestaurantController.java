package it.sysman.elasticRestaurant.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.sysman.elasticRestaurant.dto.RestaurantDTO;
import it.sysman.elasticRestaurant.model.Address;
import it.sysman.elasticRestaurant.model.Restaurant;
import it.sysman.elasticRestaurant.service.RestaurantServiceIMPL;
import it.sysman.elasticRestaurant.tools.JsonConverter;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class RestaurantController {

	@Autowired
	private RestaurantServiceIMPL restService;
	
	@Autowired
	private ModelMapper modelMapper;
	


	
}
