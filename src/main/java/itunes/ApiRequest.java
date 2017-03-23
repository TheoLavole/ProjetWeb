package itunes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import itunes.*;
import itunes.parameters.SearchParameters;
import itunes.parameters.parameter.Limit;
import itunes.parameters.parameter.Media;
import itunes.result.SearchResults;
import main.Request;
public class ApiRequest {
	private static Logger logger = Logger.getGlobal();

	public static SearchParameters createParams(String terms) {
		SearchParameters searchParams = new SearchParameters();
		searchParams.addQueryTerm(terms);
//		for (String term : terms) {
//			searchParams.addQueryTerm(term);
//		}
		searchParams.setMedia(Media.MUSIC);
		searchParams.setLimit(new Limit(200));
		return searchParams;
	}
	
	public static String[] testSearch( String term) throws Exception {
		SearchResults data = SearchApi.search(createParams(term));
		JSONObject tout = new JSONObject(data.toString());
		JSONArray SearchResults = tout.getJSONArray("results");
		String[] artists = new String[SearchResults.length()];
		for(int i=0; i< SearchResults.length();i++){
			artists[i] = SearchResults.getJSONObject(i).getString("artistName");

		}
//		System.out.println(data.toString());
		return artists;
	}    

	public static double[] artistAction(Request r ) throws Exception{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<String> semaine = new ArrayList<String>();
		calendar.add(Calendar.DATE, -0);
		Date date0 =  calendar.getTime();
		semaine.add(dateFormat.format(date0));
		for(int k = 1; k < 7; k++){
			calendar.add(Calendar.DATE, -1);
			Date date =  calendar.getTime();
			semaine.add(dateFormat.format(date));
//			System.out.println(semaine.get(k));
		}
		double[] result = new double[7];
		for(int i=0;i<7; i++){
			result[i]=0;
		}
		for(String jour:semaine){
			String[] provisoire= testSearch(jour);
			for(int i=0; i<provisoire.length;i++){
				String a = provisoire[i].toLowerCase();
				String b = r.getValeurWhere().toLowerCase();
				if(a.contains(b)){
					result[semaine.indexOf(jour)]+=1;
				}
			}
		}

		return result;
	}
}
