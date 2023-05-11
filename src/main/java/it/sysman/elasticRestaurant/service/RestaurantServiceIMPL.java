package it.sysman.elasticRestaurant.service;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.sysman.elasticRestaurant.model.Restaurant;
import it.sysman.elasticRestaurant.repository.AddressRepository;
import it.sysman.elasticRestaurant.repository.RestaurantRepository;
import it.sysman.elasticRestaurant.config.ElasticsearchConfig;

@Service
public class RestaurantServiceIMPL implements RestaurantService {

	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private RestaurantRepository restRepo;
	
	@Autowired 
	private RestHighLevelClient client;
	
//	@Transactional
//	public String fillDb(){
//		ObjectMapper objM = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		RestaurantDTO [] rest=null;
//		try 
//		{
//			rest = objM.readValue(JsonConverter.toJava(new File("C:\\Users\\sarex\\Desktop\\start_restaurants.json")), RestaurantDTO[].class);
////		}
//		catch (IOException e) {e.printStackTrace();}
//		
//		Address addr= null;
//		Restaurant restu=null;
//		for (RestaurantDTO restaurantDTO : rest) {
//			addr=modelMapper.map(restaurantDTO.getAddress(), Address.class);
//			addressRepo.save(addr);
//		}
//		
//		return null;
//	}
//	
	public void indexRestaurant(String city, Restaurant restaurant) throws IOException {
	    String indexName = "restaurant_" + city + "_index";
	    
	    IndexRequest request = new IndexRequest(indexName);
	    //restaurant.setId(1L);
	   // request.id(); // assuming each restaurant has an ID field
	   restaurant.setId( restRepo.count()+1);
	    request.source(convertObjectToJsonBytes(restaurant), XContentType.JSON);
	    
	    IndexResponse response = client.index(request, RequestOptions.DEFAULT);
	    if (response.getResult() == DocWriteResponse.Result.CREATED) {
	        System.out.println("Restaurant indexed successfully");
	    } else if (response.getResult() == DocWriteResponse.Result.UPDATED) {
	        System.out.println("Restaurant updated successfully");
	    }
	}

	private byte[] convertObjectToJsonBytes(Restaurant restaurant) throws JsonProcessingException {
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.writeValueAsBytes(restaurant);
	}
	
	public void createRest(Restaurant r) {
		r.setId( restRepo.count()+1);
		restRepo.save(r);
		
	}


    }
	
