package it.sysman.elasticRestaurant.controller;

import it.sysman.elasticRestaurant.dto.RestaurantDTO;
import it.sysman.elasticRestaurant.repository.RestaurantRepositoryIMPL;
import it.sysman.elasticRestaurant.service.RestaurantServiceIMPL;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestaurantController {

	@Autowired
	private RestaurantServiceIMPL restService;
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private RestaurantRepositoryIMPL repo;

	@GetMapping("/getAll/{city}")
	public ResponseEntity<List<RestaurantDTO>>getAllRestaurant(@PathVariable("city") String city){
		try {
			return new ResponseEntity<>(restService.getAllRestaurants(city), HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteById/{id}/{city}")
	public ResponseEntity<String>deleteByID(@PathVariable Long id,@PathVariable String city){
		try {
			return new ResponseEntity<>(restService.deleteByID(id, city), HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/SaveOrUpdate")
	public ResponseEntity<String>saveOrUpdate(@RequestBody RestaurantDTO rdto){
		try {
			return new ResponseEntity<>(restService.saveUpdateRestaurant(rdto), HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getByPlaceid/{id}/{city}")
	public ResponseEntity<RestaurantDTO> getbyID(@RequestParam Long id,@RequestParam String city){
		try{
			return new ResponseEntity<>(restService.getById(id, city), HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/searchByLocation/{lat1}/{lat2}/{lon1}/{lon2}/{city}")
	public ResponseEntity<List<RestaurantDTO>>searchByLocation(@PathVariable double lat1,@PathVariable double lat2,
														@PathVariable double lon1,@PathVariable double lon2,
														@PathVariable String city){
		try{
			return new ResponseEntity<>(restService.searchByLocation(lat1, lat2, lon1, lon2, city), HttpStatus.OK);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}



}
