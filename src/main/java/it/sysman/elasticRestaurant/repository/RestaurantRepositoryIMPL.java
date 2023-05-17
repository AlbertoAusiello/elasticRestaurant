package it.sysman.elasticRestaurant.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;

import co.elastic.clients.elasticsearch.core.search.Hit;
import it.sysman.elasticRestaurant.model.Restaurant;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class RestaurantRepositoryIMPL implements RestaurantRepository{

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public String createOrUpdate(Restaurant r) throws IOException {
        String indexName;
        if(r.getAddress().getCity()==null) {
            indexName="restaurant_"+r.getAddress().getTown().toLowerCase()+"_index";
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
            return "Ristorante document has been created successfully.";
        }else if(response.result().name().equals("Updated")){
            return "Ristorante document has been updated successfully.";
        }
        return "Error while performing the operation.";
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

    @Override
    public List<Restaurant> getAll(String cityIndex) throws IOException {
            String indexName="restaurant_"+cityIndex.toLowerCase()+"_index";
            SearchRequest searchRequest = SearchRequest.of(s -> s.index(indexName).size(10000));
            SearchResponse<Restaurant> searchResponse = elasticsearchClient.search(searchRequest, Restaurant.class);
            List<Hit<Restaurant>> hits = searchResponse.hits().hits();
            List<Restaurant> r = new ArrayList<>();
            for(Hit<Restaurant> object : hits){
                System.out.print(object.source());
                r.add(object.source());
            }
            return r;
        }

    @Override
    public String deleteById(Long rId, String city) throws IOException {
        String id=rId.toString();
        String indexName="restaurant_"+city.toLowerCase()+"_index";
        DeleteRequest request = DeleteRequest.of(d -> d.index(indexName).id(id));

        DeleteResponse deleteResponse = elasticsearchClient.delete(request);
        if (Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals("NotFound")) {
            return "Ristorante with id : " + deleteResponse.id() + " has been deleted successfully !.";
        }
        return "Ristorante with id : " + deleteResponse.id() + " does not exist.";
    }

    @Override
    public List<Restaurant> searchByLocation(double lat1, double lon1, double lat2, double lon2, String city) throws IOException {
        String indexName = "restaurant_" + city.toLowerCase() + "_index";

        SearchRequest searchRequest = SearchRequest.of(s -> s.index(indexName).size(10000));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.boolQuery()
                .must(QueryBuilders.rangeQuery("location.lat").gte(lat1).lte(lat2))
                .must(QueryBuilders.rangeQuery("location.lon").gte(lon1).lte(lon2)));

        SearchResponse<Restaurant> response = elasticsearchClient.search(searchRequest,Restaurant.class);

        List<Hit<Restaurant>> hits = response.hits().hits();
        List<Restaurant> restaurants = new ArrayList<>();
        for(Hit<Restaurant> object : hits){
            System.out.print(object.source());
            restaurants.add(object.source());
        }
        return restaurants;
    }


}
