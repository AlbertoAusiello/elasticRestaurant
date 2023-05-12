package it.sysman.elasticRestaurant.controller;

import java.io.File;
import java.io.IOException;

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
	
	@PostConstruct
	public void init() throws IOException {
//		String fileName = "/elasticRestaurant/src/main/java/it/sysman/elasticRestaurant/data/start_restaurants.json";
		ObjectMapper objM = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		RestaurantDTO [] rest=null;
		try 
		{
			rest = objM.readValue(JsonConverter.toJava(new File("C:\\Users\\sarex\\Desktop\\start_restaurants.json")), RestaurantDTO[].class);
		}
		catch (IOException e) {e.printStackTrace();}
		Address addr = null;
		int i=0;
		for (RestaurantDTO resturantDTO : rest) {
			i++;
			addr = modelMapper.map(resturantDTO.getAddress(), Address.class);
			restService.createIndex(addr.getCountry().replace(" ", ""));
			restService.indexRestaurants(addr.getCountry().replace(" ", ""), resturantDTO,i);
			
		}
	}

	
}
