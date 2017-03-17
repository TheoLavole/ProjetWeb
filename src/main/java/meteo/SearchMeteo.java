package meteo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class SearchMeteo {
	public static String getConditions(String city) throws Exception, IOException {
		// récupérer le contenu de la page web
		String url = "http://www.prevision-meteo.ch/services/json/" + city;
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.connect();
		BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
		byte[] bytes = new byte[1024];
		int tmp;
		String json = "";
		while ((tmp = bis.read(bytes)) != -1) {
			String chaine = new String(bytes, 0, tmp);
			json += chaine;
			// System.out.print(chaine);
		}
		conn.disconnect();

		// parser le json
		JSONObject obj = new JSONObject(json);
		String name = obj.getJSONObject("city_info").getString("name");
		String country = obj.getJSONObject("city_info").getString("country");
		String latitude = obj.getJSONObject("city_info").getString("latitude");
		String longitude = obj.getJSONObject("city_info").getString("longitude");
		String elevation = obj.getJSONObject("city_info").getString("elevation");
		String sunrise = obj.getJSONObject("city_info").getString("sunrise");
		String sunset = obj.getJSONObject("city_info").getString("sunset");
		String date = obj.getJSONObject("current_condition").getString("date");
		String hour = obj.getJSONObject("current_condition").getString("hour");
		int temperature = obj.getJSONObject("current_condition").getInt("tmp");
		int wnd_spd = obj.getJSONObject("current_condition").getInt("wnd_spd");
		int wnd_gust = obj.getJSONObject("current_condition").getInt("wnd_gust");
		String wnd_dir = obj.getJSONObject("current_condition").getString("wnd_dir");
		Double pressure = obj.getJSONObject("current_condition").getDouble("pressure");
		int humidity = obj.getJSONObject("current_condition").getInt("humidity");
		String condition = obj.getJSONObject("current_condition").getString("condition");

//		System.out.println("Ville : " + name);
//		System.out.println("Pays : " + country);
//		System.out.println("Latitude : " + latitude);
//		System.out.println("Longitude : " + longitude);
//		System.out.println("Elevation : " + elevation);
//		System.out.println("Lever de soleil : " + sunrise);
//		System.out.println("Coucher de soleil : " + sunset);
//		System.out.println("Date : " + date);
//		System.out.println("Heure : " + hour);
//		System.out.println("Temperature : " + temperature);
//		System.out.println("Vitesse du vent : " + wnd_spd + " km/h");
//		System.out.println("Rafales jusqu'à : " + wnd_gust + " km/h");
//		System.out.println("Direction du vent : " + wnd_dir);
//		System.out.println("Pression ATM : " + pressure);
//		System.out.println("Humidité : " + humidity);
//		System.out.println("Condition : " + condition);
//		System.out.println();
		
		return condition;
	}
}
