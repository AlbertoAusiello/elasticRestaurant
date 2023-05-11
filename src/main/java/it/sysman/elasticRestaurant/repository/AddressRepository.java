package it.sysman.elasticRestaurant.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import it.sysman.elasticRestaurant.model.Address;

@Repository
public interface AddressRepository extends ElasticsearchRepository<Address,Long> {

}
