package it.sysman.elasticRestaurant.repository;

import it.sysman.elasticRestaurant.model.Restaurant;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface RestaurantRepository  {

    String createOrUpdate(Restaurant r) throws IOException;
     Restaurant getById(Long placeId, String city) throws IOException;
     List<Restaurant> getAll(String cityIndex) throws IOException;
     void delete();
     String deleteById(Long rId, String city) throws IOException;

    List<Restaurant> searchByLocation(double lat1, double lon1, double lat2, double lon2,String city) throws IOException;
}
