package it.sysman.elasticRestaurant.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@NoArgsConstructor
@ToString
@Document(indexName = "resturant")
public class Restaurant {
	@Id
	private Long id;
	
	@Field
	@JsonProperty("place_id")
	private Long placeId;
	
	@Field
	@JsonProperty("osm_id")
	private Long osmId;
	
	@Field
	@JsonProperty("licence")
	private String licence;
	
	@Field
	@JsonProperty("osm_type")
	private String osmType;
	
	@Field
	@JsonProperty("lat")
	private String lat;
	
	@Field
	@JsonProperty("lon")
	private String lon;
	
	@Field
	@JsonProperty("display_name")
	private String displayName;
	
	@Field
	@JsonProperty("class")
	private String class1;
	
	@JsonProperty("type")
	private String type;
	
	@Field
	@JsonProperty("icon")
	private String icon;
	
	@Field
	@JsonProperty("importance")
	private double importance;
	
	@Field
	@JsonProperty("address")
	private Address address=new Address();
	
	
}