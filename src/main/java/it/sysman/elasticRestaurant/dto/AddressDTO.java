package it.sysman.elasticRestaurant.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AddressDTO {


	private String amenity;
	private String houseNumber;
	private String road;
	private String neighbourhood;
	private String suburb;
	private String borough;
	private String city;
	@JsonAlias(value = "ISO3166-2-lvl4")
	private String ISO3166;
	private String postcode;
	private String country;
	private String countryCode;
}
