package it.sysman.elasticRestaurant.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonConverter {

	public static String toJava(File Json) {

		Scanner scan;
		String result="";
		try {
			scan = new Scanner(Json);
			while (scan.hasNextLine()){
				result += scan.nextLine()+"\n";
			}
			result = result.replace("place_id","placeId");
			result = result.replace("licence", "license");
			result = result.replace("osm_type", "osmType");
			result = result.replace("osm_id", "osmId");
			result = result.replace("display_name", "displayName");
			result = result.replace("house_number", "houseNumber");
			result = result.replace("country_code", "countryCode");
//			result = result.replace("country_code", "countryCode");
			JsonArray arr = JsonParser.parseString(result).getAsJsonArray();
			for (int i = 0; i < arr.size(); i++) {
				arr.get(i).getAsJsonObject().get("address").getAsJsonObject().remove("quarter");
				arr.get(i).getAsJsonObject().get("address").getAsJsonObject().remove("state");
				arr.get(i).getAsJsonObject().get("address").getAsJsonObject().remove("city_district");
				arr.get(i).getAsJsonObject().remove("boundingbox");
				arr.get(i).getAsJsonObject().remove("class");
			}
			result = arr.toString();
			scan.close();
			return result;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
