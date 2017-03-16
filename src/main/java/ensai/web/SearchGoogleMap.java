package ensai.web;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class SearchGoogleMap {
	
	static Double[] getLocation(String city) throws Exception, IOException {
	// récupérer le contenu de la page web
			String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + city;
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.connect();
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			byte[] bytes = new byte[1024];
			int tmp;
			String json = "";
			while ((tmp = bis.read(bytes)) != -1) {
				String chaine = new String(bytes, 0, tmp);
				json += chaine;
			}
			conn.disconnect();
			
			// parser le json
			JSONObject obj = new JSONObject(json);
			org.json.JSONObject results = obj.getJSONArray("results").getJSONObject(0);
			org.json.JSONObject geometry = results.getJSONObject("geometry");
			org.json.JSONObject location = geometry.getJSONObject("location");		
			Double latitude = location.getDouble("lat");
			Double longitude = location.getDouble("lng");

			Double[] loc = {latitude,longitude};
			
			return loc;
	}
	
}
