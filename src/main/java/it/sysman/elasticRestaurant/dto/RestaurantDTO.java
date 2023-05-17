package it.sysman.elasticRestaurant.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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

	@JsonProperty("place_id")
	private Long placeId;
	@JsonProperty("licence")
	private String license;
	@JsonProperty("osm_type")
	private String osmType;
	@JsonProperty("osm_id")
	private Long osmId;
	@JsonProperty("lat")
	private String lat;
	@JsonProperty("lon")
	private String lon;
	@JsonProperty("display_name")
	private String displayName;
	@JsonProperty("type")
	private String type;
	@JsonProperty("importance")
	private double importance;
	@JsonProperty("icon")
	private String icon;
	@JsonProperty("address")
	private AddressDTO address;

	
	@Override
	public String toString() {
		return "ResturantDTO [placeId=" + placeId + ", license=" + license + ", osmType=" + osmType + ", osmId=" + osmId
				+ ", lat=" + lat + ", lon=" + lon + ", displayName=" + displayName + ", type=" + type + ", importance="
				+ importance + ", icon=" + icon + ", address=" + address + "]";
	}
}
