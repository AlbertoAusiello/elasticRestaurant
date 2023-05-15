package it.sysman.elasticRestaurant.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import it.sysman.elasticRestaurant.dto.RestaurantDTO;

import it.sysman.elasticRestaurant.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class RestaurantRepositoryIMPL implements RestaurantRepository{

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public String createOrUpdate(Restaurant r) throws IOException {
        String indexName;
        if(r.getAddress().getCity()==null) {
            indexName="restaurant_"+r.getAddress().getTown().toLowerCase()+"index";
        }
        else {
            indexName="restaurant_"+r.getAddress().getCity().toLowerCase()+"_index";
        }

        IndexResponse response = elasticsearchClient.index(
                i -> i.index(indexName)
                        .id(r.getPlaceId().toString())
                        .document(r)
        );
        if(response.result().name().equals("Created")){
            return new StringBuilder("Ristorante document has been created successfully.").toString();
        }else if(response.result().name().equals("Updated")){
            return new StringBuilder("Ristorante document has been updated successfully.").toString();
        }
        return new StringBuilder("Error while performing the operation.").toString();
    }


    public Restaurant getById(Long placeId, String city) throws IOException{
        String id=placeId.toString();
        String indexName;
        indexName="restaurant_"+city.toLowerCase()+"_index";
         indexName= indexName.replace(" ","");
        Restaurant r = null;
        String finalIndexName = indexName;
        GetResponse<Restaurant> response = elasticsearchClient.get(
                g -> g.index(finalIndexName)
                        .id(id),
                Restaurant.class
        );
        if (response.found()) {
            r = response.source();
        } else {
            System.out.println ("ristorante not found");
        }
        return r;
    }
}
