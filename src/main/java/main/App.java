package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import google.map.SearchGoogleMap;
import meteo.SearchMeteo;
import series.BetaSeries;
import twitter.SearchTwitter;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		recherche("The Walking Dead", "Paris");
		// recherche("The Walking Dead");
	}

	public static void recherche(String recherche, String ville) throws Exception {
		new SearchGoogleMap();
		// Obtenir le latitude et longitude d'une ville (API GoogleMap)
		Double[] location = SearchGoogleMap.getLocation(ville);

		// Obtenir la météo
		String condition = SearchMeteo.getConditions(ville);

		// récupérer la liste des jours d'intérêt
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<String> semaine = new ArrayList<String>();
		calendar.add(Calendar.DATE, -0);
		Date date0 = calendar.getTime();
		semaine.add(dateFormat.format(date0));

		// passé
		int nbTweetsPas = 0;
		int nbTweetsPosPas = 0;
		int nbTweetsNegPas = 0;
		// FIN RAJOUT

		// total
		int nbTweetsPos = 0;
		int nbTweetsNeg = 0;
		int nbTweets = 0;

		// stockage
		int[] tweetsPos = new int[7];
		int[] tweetsTotal = new int[7];
		int[] tweetsNeg = new int[7];

		for (int k = 0; k < 7; k++) {
			Date date = calendar.getTime();

			// tweets de l'instant k à maintenant
			int nbTweetsActuels = new SearchTwitter(recherche + " since:" + dateFormat.format(date), location,
					30.00).nbTweets;
			int nbTweetsPosActuels = new SearchTwitter(recherche + " since:" + dateFormat.format(date) + " :)",
					location, 30.00).nbTweets;
			int nbTweetsNegActuels = new SearchTwitter(recherche + " since:" + dateFormat.format(date) + " :(",
					location, 30.00).nbTweets;

			// tweets du jour k
			nbTweets = nbTweetsActuels - nbTweetsPas;
			nbTweetsPos = nbTweetsPosActuels - nbTweetsPosPas;
			nbTweetsNeg = nbTweetsNegActuels - nbTweetsNegPas;

			// stockage
			tweetsPos[k] = nbTweetsPos;
			tweetsNeg[k] = nbTweetsNeg;
			tweetsTotal[k] = nbTweets;

			// on change la date
			calendar.add(Calendar.DATE, -1);

//			System.out.println(dateFormat.format(date));
//			System.out.println(
//					"passé = " + nbTweetsPas + ", :) passé = " + nbTweetsPosPas + ", :( passé = " + nbTweetsNegPas);
//			System.out.println(
//					"présent = " + nbTweets + ", :) présent = " + nbTweetsPos + ", :( présent = " + nbTweetsNeg);
//			System.out.println(
//					"actuel = " + nbTweetsActuels + ", :) = " + nbTweetsPosActuels + ", :( = " + nbTweetsNegActuels);
//			System.out.println();

			// on change la valeur du passé
			nbTweetsPas += nbTweets;
			nbTweetsPosPas += nbTweetsPos;
			nbTweetsNegPas += nbTweetsNeg;
		}
		System.out.println("Lieu      -> " + ville);
		System.out.println("Météo     -> " + condition);
		System.out.println("**********************************************");
		System.out.println("Résultat de la recherche sur l'API Twitter");
		System.out.println("        :) - :( - total");
		for (int k = 0; k < tweetsTotal.length; k++) {
			System.out.println("Jour " + k + " : " + tweetsPos[k] + " - " + tweetsNeg[k] + " - " + tweetsTotal[k]);
		}
		System.out.println("----------------------------------------------");
		// utilisation de l'API iTunes
		main.Request r = new main.Request();
		r.setValeurWhere("recherche");
		double[] resultat = itunes.ApiRequest.artistAction(r);
		System.out.println("Résultat de la recherche sur l'API iTunes");
		for (int k = 0; k < resultat.length; k++) {
			System.out.println("Jour " + k + " : " + resultat[k]);
		}
		System.out.println("----------------------------------------------");

		// Beta series
		Request r2 = new Request();
		r2.setValeurWhere(recherche);
		double[] res = BetaSeries.series(r2);
		System.out.println("Résultat de la recherche sur l'API BetaSeries");
		for (int k = 0; k < res.length; k++) {
			System.out.println("Jour " + k + " : " + res[k]);
		}
	}

	public static void recherche(String recherche) throws Exception {

		// récupérer la liste des jours d'intérêt
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<String> semaine = new ArrayList<String>();
		calendar.add(Calendar.DATE, -0);
		Date date0 = calendar.getTime();
		semaine.add(dateFormat.format(date0));

		// RAJOUT
		int nbTweetsPas = 0;
		int nbTweetsPosPas = 0;
		int nbTweetsNegPas = 0;
		// FIN RAJOUT

		// utiliser la loc trouvée avant pour les tweets
		int nbTweetsPos;
		int nbTweetsNeg;
		int nbTweets;

		int[] tweetsPos = new int[7];
		int[] tweetsTotal = new int[7];
		int[] tweetsNeg = new int[7];

		for (int k = 0; k < 7; k++) {
			Date date = calendar.getTime();

			// tweets de l'instant k à maintenant
			int nbTweetsActuels = new SearchTwitter(recherche + " since:" + dateFormat.format(date)).nbTweets;
			int nbTweetsPosActuels = new SearchTwitter(
					recherche + " since:" + dateFormat.format(date) + " :)").nbTweets;
			int nbTweetsNegActuels = new SearchTwitter(
					recherche + " since:" + dateFormat.format(date) + " :(").nbTweets;

			// tweets du jour k
			nbTweets = nbTweetsActuels - nbTweetsPas;
			nbTweetsPos = nbTweetsPosActuels - nbTweetsPosPas;
			nbTweetsNeg = nbTweetsNegActuels - nbTweetsNegPas;

			// stockage
			tweetsPos[k] = nbTweetsPos;
			tweetsNeg[k] = nbTweetsNeg;
			tweetsTotal[k] = nbTweets;

			// on change la date
			calendar.add(Calendar.DATE, -1);

//			System.out.println(dateFormat.format(date));
//			System.out.println(
//					"passé = " + nbTweetsPas + ", :) passé = " + nbTweetsPosPas + ", :( passé = " + nbTweetsNegPas);
//			System.out.println(
//					"présent = " + nbTweets + ", :) présent = " + nbTweetsPos + ", :( présent = " + nbTweetsNeg);
//			System.out.println(
//					"actuel = " + nbTweetsActuels + ", :) = " + nbTweetsPosActuels + ", :( = " + nbTweetsNegActuels);

			// on change la valeur du passé
			nbTweetsPas += nbTweets;
			nbTweetsPosPas += nbTweetsPos;
			nbTweetsNegPas += nbTweetsNeg;
		}
		System.out.println("**********************************************");
		System.out.println("Résultat de la recherche sur l'API Twitter");
		System.out.println("        :) - :( - total");
		for (int k = 0; k < tweetsTotal.length; k++) {
			System.out.println("Jour " + k + " : " + tweetsPos[k] + " - " + tweetsNeg[k] + " - " + tweetsTotal[k]);
		}
		System.out.println("----------------------------------------------");
		// utilisation de l'API iTunes
		main.Request r = new main.Request();
		r.setValeurWhere("recherche");
		double[] resultat = itunes.ApiRequest.artistAction(r);
		System.out.println("Résultat de la recherche sur l'API iTunes");
		for (int k = 0; k < resultat.length; k++) {
			System.out.println("Jour " + k + " : " + resultat[k]);
		}
		System.out.println("----------------------------------------------");

		// Beta series
		Request r2 = new Request();
		r2.setValeurWhere(recherche);
		double[] res = BetaSeries.series(r2);
		System.out.println("Résultat de la recherche sur l'API BetaSeries");
		for (int k = 0; k < res.length; k++) {
			System.out.println("Jour " + k + " : " + res[k]);
		}
	}

}
