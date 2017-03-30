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

public class App {
	public static void main(String[] args) throws Exception {
//		rechercheTwitterBetaSeries("The Walking Dead", "Paris");
		// rechercheTwitterBetaSeries("Fear The Walking Dead");

		rechercheTwitterItunes("David Guetta", "Paris");
		// rechercheTwitterItunes("Lacrim");
	}

	// Retourne le résultat de la recherche sur Twitter et BetaSeries dans une
	// ville
	public static void rechercheTwitterBetaSeries(String recherche, String ville) throws Exception {
		// Obtenir le latitude et longitude d'une ville (API GoogleMap)
		new SearchGoogleMap();
		Double[] location = SearchGoogleMap.getLocation(ville);

		// récupérer la liste des jours d'intérêt
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<String> semaine = new ArrayList<String>();
		calendar.add(Calendar.DATE, -0);
		Date date0 = calendar.getTime();
		semaine.add(dateFormat.format(date0));

		// passé
		int nbTweetsPas = 0;

		// total
		int nbTweets = 0;

		// stockage
		int[] tweetsTotal = new int[7];

		for (int k = 0; k < 7; k++) {
			Date date = calendar.getTime();

			// tweets de l'instant k à maintenant
			int nbTweetsActuels = new SearchTwitter(recherche + " since:" + dateFormat.format(date), location,
					30.00).nbTweets;

			// tweets du jour k
			nbTweets = nbTweetsActuels - nbTweetsPas;

			// stockage
			tweetsTotal[k] = nbTweets;

			// on change la date
			calendar.add(Calendar.DATE, -1);

			// on change la valeur du passé
			nbTweetsPas += nbTweets;
		}

		System.out.println("Results on Twitter");
		for (int k = 0; k < tweetsTotal.length; k++) {
			System.out.print(tweetsTotal[k] + "\t");
		}
		// Beta series
		Request r2 = new Request();
		r2.setValeurWhere(recherche);
		double[] res = BetaSeries.series(r2);
		System.out.println();
		System.out.println("Results on BetaSeries");
		for (int k = 0; k < res.length; k++) {
			System.out.print(res[k] + "\t");
		}
	}

	// Retourne le résultat de la recherche sur Twitter et BetaSeries partout
	// dans le monde
	public static void rechercheTwitterBetaSeries(String recherche) throws Exception {
		// récupérer la liste des jours d'intérêt
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<String> semaine = new ArrayList<String>();
		calendar.add(Calendar.DATE, -0);
		Date date0 = calendar.getTime();
		semaine.add(dateFormat.format(date0));

		// passé
		int nbTweetsPas = 0;

		// total
		int nbTweets = 0;

		// stockage
		int[] tweetsTotal = new int[7];

		for (int k = 0; k < 7; k++) {
			Date date = calendar.getTime();

			// tweets de l'instant k à maintenant
			int nbTweetsActuels = new SearchTwitter(recherche + " since:" + dateFormat.format(date)).nbTweets;

			// tweets du jour k
			nbTweets = nbTweetsActuels - nbTweetsPas;

			// stockage
			tweetsTotal[k] = nbTweets;

			// on change la date
			calendar.add(Calendar.DATE, -1);

			// on change la valeur du passé
			nbTweetsPas += nbTweets;
		}

		System.out.println("Results on Twitter");
		for (int k = 0; k < tweetsTotal.length; k++) {
			System.out.print(tweetsTotal[k] + "\t");
		}
		System.out.println();

		// Beta series
		Request r2 = new Request();
		r2.setValeurWhere(recherche);
		double[] res = BetaSeries.series(r2);
		System.out.println("Results on BetaSeries");
		for (int k = 0; k < res.length; k++) {
			System.out.print(res[k] + "\t");
		}
	}

	// Retourne le résultat de la recherche sur Twitter et iTunes dans une ville
	public static void rechercheTwitterItunes(String recherche, String ville) throws Exception {
		// Obtenir le latitude et longitude d'une ville (API GoogleMap)
		new SearchGoogleMap();
		Double[] location = SearchGoogleMap.getLocation(ville);

		// récupérer la liste des jours d'intérêt
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<String> semaine = new ArrayList<String>();
		calendar.add(Calendar.DATE, -0);
		Date date0 = calendar.getTime();
		semaine.add(dateFormat.format(date0));

		// passé
		int nbTweetsPas = 0;

		// total
		int nbTweets = 0;

		// stockage
		int[] tweetsTotal = new int[7];

		for (int k = 0; k < 7; k++) {
			Date date = calendar.getTime();

			// tweets de l'instant k à maintenant
			int nbTweetsActuels = new SearchTwitter(recherche + " since:" + dateFormat.format(date), location,
					30.00).nbTweets;

			// tweets du jour k
			nbTweets = nbTweetsActuels - nbTweetsPas;

			// stockage
			tweetsTotal[k] = nbTweets;

			// on change la date
			calendar.add(Calendar.DATE, -1);

			// on change la valeur du passé
			nbTweetsPas += nbTweets;
		}

		System.out.println("Results on Twitter");
		for (int k = 0; k < tweetsTotal.length; k++) {
			System.out.print(tweetsTotal[k] + "\t");
		}
		System.out.println();
		// utilisation de l'API iTunes
		Request r = new Request();
		r.setValeurWhere(recherche);
		
		double[] resultat = itunes.ApiRequest.artistAction(r);
		System.out.println("Results on iTunes");
		for (int k = 0; k < resultat.length; k++) {
			System.out.print(resultat[k] + "\t");
		}
	}

	// Retourne le résultat de la recherche sur Twitter et iTunes partout dans
	// le monde
	public static void rechercheTwitterItunes(String recherche) throws Exception {
		// récupérer la liste des jours d'intérêt
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<String> semaine = new ArrayList<String>();
		calendar.add(Calendar.DATE, -0);
		Date date0 = calendar.getTime();
		semaine.add(dateFormat.format(date0));

		// passé
		int nbTweetsPas = 0;

		// total
		int nbTweets = 0;

		// stockage
		int[] tweetsTotal = new int[7];

		for (int k = 0; k < 7; k++) {
			Date date = calendar.getTime();

			// tweets de l'instant k à maintenant
			int nbTweetsActuels = new SearchTwitter(recherche + " since:" + dateFormat.format(date)).nbTweets;

			// tweets du jour k
			nbTweets = nbTweetsActuels - nbTweetsPas;

			// stockage
			tweetsTotal[k] = nbTweets;

			// on change la date
			calendar.add(Calendar.DATE, -1);

			// on change la valeur du passé
			nbTweetsPas += nbTweets;
		}

		System.out.println("Results on Twitter");
		for (int k = 0; k < tweetsTotal.length; k++) {
			System.out.print(tweetsTotal[k] + "\t");
		}
		System.out.println();
		// utilisation de l'API iTunes
		main.Request r = new main.Request();
		r.setValeurWhere("recherche");
		double[] resultat = itunes.ApiRequest.artistAction(r);
		System.out.println("Results on iTunes");
		for (int k = 0; k < resultat.length; k++) {
			System.out.print(resultat[k] + "\t");
		}
	}
}
