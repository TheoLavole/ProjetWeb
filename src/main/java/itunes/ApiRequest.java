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

	public static SearchParameters createParams(String[] terms) {
		SearchParameters searchParams = new SearchParameters();
		for (String term : terms) {
			searchParams.addQueryTerm(term);
		}
		searchParams.setMedia(Media.MUSIC);
		searchParams.setLimit(new Limit(200));
		return searchParams;
	}
	
	public static List<String> testSearch(String term) throws Exception {
		String[] mots = term.split(" ");
		SearchResults data = SearchApi.search(createParams(mots));
		
		JSONObject tout = new JSONObject(data);
		
		JSONArray SearchResults = tout.getJSONArray("results");
		
		String[] artists = new String[SearchResults.length()];
		List<String> dates = new ArrayList<String>();
		
		for(int i=0; i< SearchResults.length();i++){
			artists[i] = SearchResults.getJSONObject(i).getString("artistName");
			if (artists[i].compareTo(term)==0) dates.add(SearchResults.getJSONObject(i).getString("releaseDate").substring(0, 10));
		}
		return dates;
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
		}
		double[] result = new double[7];
		for(int i=0;i<7; i++){
			result[i]=0;
		}
		
		List<String> dates = testSearch(r.getValeurWhere());
		
		for(String jour:semaine){
			for(String date : dates){
				if (date.compareTo(jour)==0){
					result[semaine.indexOf(jour)]+=1;
				}
			}
		}

		return result;
	}
}
