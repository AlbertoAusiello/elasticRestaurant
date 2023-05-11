package it.sysman.elasticRestaurant.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import it.sysman.elasticRestaurant.model.Restaurant;

@Repository
public interface RestaurantRepository extends ElasticsearchRepository<Restaurant, Long> {

}
