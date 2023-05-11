package it.sysman.elasticRestaurant.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.sysman.elasticRestaurant.model.Restaurant;
import it.sysman.elasticRestaurant.service.RestaurantServiceIMPL;

@RestController
@RequestMapping("/api")
public class RestaurantController {

	@Autowired
	private RestaurantServiceIMPL restService;
	
	
	@PostMapping("/create")
	public void createIndex(@RequestBody Restaurant res)  {
		restService.createRest(res);
	}
	
}
