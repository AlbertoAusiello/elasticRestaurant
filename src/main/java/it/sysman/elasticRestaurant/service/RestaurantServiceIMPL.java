package it.sysman.elasticRestaurant.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.sysman.elasticRestaurant.dto.RestaurantDTO;
import it.sysman.elasticRestaurant.tools.JsonConverter;

@Service
public class RestaurantServiceIMPL implements RestaurantService {

	
	
	public String fillDb(){
		ObjectMapper objM = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		RestaurantDTO [] rest=null;
		try 
		{
			rest = objM.readValue(JsonConverter.toJava(new File("C:\\Users\\sarex\\Desktop\\start_restaurants.json")), RestaurantDTO[].class);
		}
		catch (IOException e) {e.printStackTrace();}
		
		
		return null;
	}
}