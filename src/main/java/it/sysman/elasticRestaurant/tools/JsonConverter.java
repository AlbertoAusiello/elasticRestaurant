package it.sysman.elasticRestaurant.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class JsonConverter {

	public static String toJava(File Json) {

		Scanner scan;
		StringBuilder result= new StringBuilder();
		try {
			scan = new Scanner(Json);
			while (scan.hasNextLine()){
				result.append(scan.nextLine()).append("\n");
			}
			result = new StringBuilder(result.toString().replace("place_id", "placeId"));
			result = new StringBuilder(result.toString().replace("licence", "license"));
			result = new StringBuilder(result.toString().replace("osm_type", "osmType"));
			result = new StringBuilder(result.toString().replace("osm_id", "osmId"));
			result = new StringBuilder(result.toString().replace("display_name", "displayName"));
			result = new StringBuilder(result.toString().replace("house_number", "houseNumber"));
			result = new StringBuilder(result.toString().replace("country_code", "countryCode"));
			result = new StringBuilder(result.toString().replace("town", "city"));
//			result = result.replace("town", "city");
			
			JsonArray arr = JsonParser.parseString(result.toString()).getAsJsonArray();
//			for (int i = 0; i < arr.size(); i++) {
//				arr.get(i).getAsJsonObject().get("address").getAsJsonObject().remove("quarter");
//				arr.get(i).getAsJsonObject().get("address").getAsJsonObject().remove("state");
//				arr.get(i).getAsJsonObject().get("address").getAsJsonObject().remove("city_district");
//				arr.get(i).getAsJsonObject().remove("boundingbox");
//				arr.get(i).getAsJsonObject().remove("class");
//			}
			result = new StringBuilder(arr.toString());
			scan.close();
			return result.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
