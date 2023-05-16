package it.sysman.elasticRestaurant.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(value={ "boundingbox" })
public class RestaurantDTO {

	private Long id;
	private String placeId;
	private String license;
	private String osmType;
	private String osmId;
	private String lat;
	private String lon;
	private String displayName;
	private String type;
	private String importance;
	private String icon;
	private AddressDTO address;

	
	@Override
	public String toString() {
		return "ResturantDTO [placeId=" + placeId + ", license=" + license + ", osmType=" + osmType + ", osmId=" + osmId
				+ ", lat=" + lat + ", lon=" + lon + ", displayName=" + displayName + ", type=" + type + ", importance="
				+ importance + ", icon=" + icon + ", address=" + address + "]";
	}
}
