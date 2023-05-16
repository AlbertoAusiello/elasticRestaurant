package it.sysman.elasticRestaurant.controller;

import it.sysman.elasticRestaurant.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.sysman.elasticRestaurant.service.RestaurantServiceIMPL;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestaurantController {

	@Autowired
	private RestaurantServiceIMPL restService;
	
	@Autowired
	private ModelMapper modelMapper;


	@GetMapping("/restaurant/{city}")
	public ResponseEntity<List<Restaurant>>getAllRestaurant(@PathVariable("city") String city){
		try {
			return new ResponseEntity<List<Restaurant>>(restService.getAllRestaurants(city),HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<List<Restaurant>>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/restaurant/{id}/{city}")
	public ResponseEntity<String>deleteByID(@PathVariable Long id,@PathVariable String city){
		try {
			return new ResponseEntity<String>(restService.deleteByID(id,city),HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
