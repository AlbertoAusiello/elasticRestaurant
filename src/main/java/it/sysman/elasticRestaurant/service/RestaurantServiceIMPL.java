package it.sysman.elasticRestaurant.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import it.sysman.elasticRestaurant.model.Restaurant;
import it.sysman.elasticRestaurant.repository.RestaurantRepositoryIMPL;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.sysman.elasticRestaurant.dto.RestaurantDTO;
import it.sysman.elasticRestaurant.repository.AddressRepository;

@Service
public class RestaurantServiceIMPL implements RestaurantService {

	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private RestaurantRepositoryIMPL repo;

	@Autowired
	private RestHighLevelClient client;

	public void saveFile() {
		ObjectMapper obj=new ObjectMapper().configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		Restaurant[]rest;
		try {
			rest=obj.readValue(new File("C:\\Users\\sarex\\Desktop\\start_restaurants.json"), Restaurant[].class);
			List<Restaurant> risto = new ArrayList<>(Arrays.asList(rest));
			for (Restaurant restaurant : risto) {
				if(restaurant.getAddress().getCity()!=null) {
					createIndex(restaurant.getAddress().getCity());
					if(repo.getById(restaurant.getPlaceId(),restaurant.getAddress().getCity())==null) {
						repo.createOrUpdate(restaurant);
					}
				}
				else {
					createIndex(restaurant.getAddress().getTown());
					if(repo.getById(restaurant.getPlaceId(),restaurant.getAddress().getTown())==null) {
						repo.createOrUpdate(restaurant);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	public  String saveUpdateRestaurant(RestaurantDTO r) throws IOException {
		Restaurant restaurant;
		restaurant =  modelMapper.map(r,Restaurant.class);
		if(restaurant.getAddress().getCity()!=null) {
			createIndex(restaurant.getAddress().getCity());
		}
		else {
			createIndex(restaurant.getAddress().getTown());
		}
		return repo.createOrUpdate(restaurant);

	}
	public void createIndex(String city) throws IOException {
	        String indexName = "restaurant_" + city.toLowerCase() + "_index";
	        if (!indexExists(indexName)) {
	            CreateIndexRequest request = new CreateIndexRequest(indexName);
	            request.settings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 0));
	            client.indices().create(request, RequestOptions.DEFAULT);
			}
	}
	 private boolean indexExists(String indexName) throws IOException {
	        GetIndexRequest request = new GetIndexRequest(indexName);
	        return client.indices().exists(request, RequestOptions.DEFAULT);
	    }
		private byte[] convertObjectToJsonBytes(Object object) throws IOException {
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	        return mapper.writeValueAsBytes(object);
	    }


	public List<RestaurantDTO> getAllRestaurants(String city) throws IOException {
		List<Restaurant> restaurants = repo.getAll(city);
		List<RestaurantDTO>restaurantDTOS = new ArrayList<>();
		RestaurantDTO rdto;
		for (Restaurant restaurant: restaurants) {
			rdto =  modelMapper.map(restaurant,RestaurantDTO.class);
			restaurantDTOS.add(rdto);
		}

	return restaurantDTOS;
	}
	public RestaurantDTO getById(Long placeId,String city) throws IOException {
		Restaurant restaurant = repo.getById(placeId,city);
		RestaurantDTO rdto;
		rdto =  modelMapper.map(restaurant,RestaurantDTO.class);
		return rdto;
	}
	public String deleteByID(Long id,String city) throws IOException {
		return repo.deleteById(id,city);
	}

	public List<RestaurantDTO>searchByLocation(double lat1, double lon1, double lat2, double lon2, String city) throws IOException {
		List<Restaurant> restaurants = repo.getAll(city);
		List<RestaurantDTO>restaurantDTOS = new ArrayList<>();
		RestaurantDTO rdto;
		for (Restaurant restaurant: restaurants) {
			rdto =  modelMapper.map(restaurant,RestaurantDTO.class);
			restaurantDTOS.add(rdto);
		}
		return restaurantDTOS;
	}
}

