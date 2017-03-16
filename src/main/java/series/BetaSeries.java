package series;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.json.*;

import series.Request;

public class BetaSeries {

	public static void main(String[] args) throws IOException {

		Request r = new Request();
		r.setValeurWhere("Fear The WALKING Dead");
		double[] res = series(r);
		for(int k = 0; k < res.length; k++){   
			System.out.print(res[k] + " "); 
		} 

	}


	// Méthode qui retourne une matrice des titres de series et date de diffusion, avec les options suivantes (juste type pour l'instant) :
	//  date : Date d'origine (YYYY-MM-JJ — Facultatif, par défaut "now")
	//  before : Nombre de jours avant (Facultatif, par défaut 8)
	//  after : Nombre de jours après (Facultatif, par défaut 8)
	//  type : Type d'épisodes à afficher : "all" ou "premieres" (Facultatif, par défaut "all")
	public static double[] series(Request r) throws IOException{
		String resGet = getResURL("https://api.betaseries.com/planning/general?v=2.4&key=81134c7c1f29&before=8&after=0&type=all");

		JSONObject tout = new JSONObject(resGet);
		JSONArray episodes = tout.getJSONArray("episodes");
		String[] titresSeries = new String[episodes.length()];
		String[] dates = new String[episodes.length()];
		for (int i = 0; i < episodes.length(); i++){
			titresSeries[i] = episodes.getJSONObject(i).getJSONObject("show").getString("title");
			dates[i] = episodes.getJSONObject(i).getString("date");
		}

		String[][] resURL = new String[2][];
		resURL[0] = titresSeries;
		resURL[1] = dates;

		for(int i = 0 ; i < resURL.length; i++ ){  
			for(int j = 0; j< resURL[i].length; j++){   
				System.out.print(resURL[i][j] + " / "); 
			} 
			System.out.println(" "); 
		}

		//récupérer la liste des jours d'intérêt
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<String> semaine = new ArrayList<String>();
		calendar.add(Calendar.DATE, -0);
		Date date0 =  calendar.getTime();
		semaine.add(dateFormat.format(date0));
		//System.out.println(semaine.get(0));
		for(int k = 1; k < 7; k++){
			calendar.add(Calendar.DATE, -1);
			Date date =  calendar.getTime();
			semaine.add(dateFormat.format(date));
			//System.out.println(semaine.get(k));
		}

		double[] res = new double[semaine.size()];
		for(int h = 0; h <semaine.size(); h++){
			res[h] = 0;
			System.out.println(res[h]);
			System.out.println(semaine.get(h));
		}


		for(int j = 0; j< resURL[0].length; j++){  
			//vérifier que c'est pas trop loin dans le calendrier
			if(semaine.contains(resURL[1][j])){
				//verifier qu'on est sur la serie demandé
				//System.out.println(resURL[0][j] + " =? " + r.getValeurWhere() + " le " + resURL[1][j]);
				String a = resURL[0][j].toLowerCase();
				String b = r.getValeurWhere().toLowerCase();
				if(a.contains(b)){
					//incrémenter le compteur à l'index correspondant à la bonne date
					//System.out.println(semaine.indexOf(resURL[1][j]));
					res[semaine.indexOf(resURL[1][j])] = res[semaine.indexOf(resURL[1][j])] + 1;
				}
			}
		} 



		return res;
	}

	public static String getResURL(String url) throws IOException{

		String source ="";
		URL oracle = new URL(url);
		URLConnection yc = oracle.openConnection();
		BufferedReader in = new BufferedReader(
				new InputStreamReader(
						yc.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null)
			source +=inputLine;
		in.close();
		return source;
	}
}
