package it.sysman.elasticRestaurant.service;

import java.io.IOException;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
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
	private RestaurantRepository restRepo;
	
	@Autowired 
	private RestHighLevelClient client;
	

	 public void createIndex(String city) throws IOException {
	        String indexName = "restaurant_" + city.toLowerCase() + "_index";
	        if (!indexExists(indexName)) {
	            CreateIndexRequest request = new CreateIndexRequest(indexName);
	            request.settings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 0));
	            client.indices().create(request, RequestOptions.DEFAULT);
	        }
	    }
	 public void indexRestaurants(String city, RestaurantDTO rest,int i) throws IOException {
	        String indexName = "restaurant_" + city.toLowerCase() + "_index";
	        BulkRequest bulkRequest = new BulkRequest();
	        
	            IndexRequest indexRequest = new IndexRequest(indexName);
	            indexRequest.id(i+"");
	            indexRequest.source(convertObjectToJsonBytes(rest), XContentType.JSON);
	            
	        
	        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
	        if (indexResponse.getResult() == DocWriteResponse.Result.NOOP ) {
	            System.out.println("Failed to index restaurants: " );
	        } else {
	            System.out.println("Successfully indexed restaurants");
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

    }
	
