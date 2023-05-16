package it.sysman.elasticRestaurant.model;


import org.springframework.data.annotation.Id;
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

public class Address {
	@Id
	private Long id;
	
	@Field
	@JsonProperty("amenity")
	private String amenity;
	
	@Field
	@JsonProperty("road")
	private String road;
	
	@Field
	@JsonProperty("house_number")
	private String houseNumber;
	
	@Field
	@JsonProperty("neighbourhood")
	private String neighbourhood;
	
	@Field
	@JsonProperty("suburb")
	private String suburb;
	
	@Field
	@JsonProperty("city")
	private String city;
	
	@Field
	@JsonProperty("borough")
	private String borough;
	
	@Field
	@JsonProperty("ISO3166-2-lvl4")
	private String ISO3166_14;
	
	@Field
	@JsonProperty("ISO3166-2-lvl6")
	private String ISO3166_16;
	
	@Field
	@JsonProperty("postcode")
	private String postCode;
	
	@Field
	@JsonProperty("region")
	private String region;
	
	@Field
	@JsonProperty("county")
	private String county;
	
	@Field
	@JsonProperty("state")
	private String state;
	
	@Field
	@JsonProperty("country")
	private String country;
	
	@Field
	@JsonProperty("country_code")
	private String countryCode;
	
	@Field
	@JsonProperty("municipality")
	private String municipality;
	
	@Field
	@JsonProperty("town")
	private String town;
	
	@Field
	@JsonProperty("city_district")
	private String cityDistrict;
	
	@Field
	@JsonProperty("quarter")
	private String quarter;
}