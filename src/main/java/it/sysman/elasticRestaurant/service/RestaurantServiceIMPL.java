package it.sysman.elasticRestaurant.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import it.sysman.elasticRestaurant.dto.AddressDTO;
import it.sysman.elasticRestaurant.model.Address;
import it.sysman.elasticRestaurant.model.Restaurant;
import it.sysman.elasticRestaurant.repository.RestaurantRepositoryIMPL;
import it.sysman.elasticRestaurant.tools.JsonConverter;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.sysman.elasticRestaurant.dto.RestaurantDTO;
import it.sysman.elasticRestaurant.repository.AddressRepository;
import it.sysman.elasticRestaurant.repository.RestaurantRepository;

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
		List<Restaurant>risto=new ArrayList<>();
		try {
			rest=obj.readValue(new File("C:\\Users\\sarex\\Desktop\\start_restaurants.json"), Restaurant[].class);
			for (Restaurant restaurant : rest) {
				risto.add(restaurant);
			}
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


	public void createIndex(String city) throws IOException {
	        String indexName = "restaurant_" + city.toLowerCase() + "_index";
	        if (!indexExists(indexName)) {
	            CreateIndexRequest request = new CreateIndexRequest(indexName);
	            request.settings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 0));
	            client.indices().create(request, RequestOptions.DEFAULT);
	        }
	    }
	 public void indexRestaurants(String city, RestaurantDTO rest, Long i) throws IOException {
		 String indexName = "restaurant_" + city.toLowerCase() + "_index";

		 rest.setId(Long.parseLong(String.valueOf(i)));
		 IndexRequest request = new IndexRequest(indexName);
		 request.id(String.valueOf(i));
		 request.source(convertObjectToJsonBytes(rest), XContentType.JSON);
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

    }
	
