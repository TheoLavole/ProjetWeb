package main;

import java.util.Date;

import google.map.SearchGoogleMap;
import itunes.Request;
import meteo.SearchMeteo;
import twitter.SearchTwitter;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		dataVille("Game of thrones", "Paris");
	}

	public static void dataVille(String recherche, String ville) throws Exception {
		new SearchGoogleMap();
		// Obtenir le latitude et longitude d'une ville (API GoogleMap)
		Double[] location = SearchGoogleMap.getLocation(ville);

		// Obtenir la météo
		String condition = SearchMeteo.getConditions(ville);

		Date today = new Date();
		String date_str = (today.getYear() + 1900) + "-" + (today.getMonth() + 1) + "-" + today.getDate();

		// utiliser la loc trouvée avant pour les tweets
		int nbTweetsPos;
		int nbTweetsNeg;
		int nbTweets;

		// :) pour les tweets positifs, :( pour les tweets négatifs
		nbTweets = new SearchTwitter(recherche + " since:" + date_str, location, 10.00).nbTweets;
		nbTweetsPos = new SearchTwitter(recherche + " since:" + date_str + " :)", location, 10.00).nbTweets;
		nbTweetsNeg = new SearchTwitter(recherche + " since:" + date_str + " :(", location, 10.00).nbTweets;

		System.out.println("Recherche -> " + recherche);
		System.out.println("Lieu      -> " + ville);
		System.out.println("Météo     -> " + condition);
		System.out.println(":)        -> " + nbTweetsPos);
		System.out.println(":(        -> " + nbTweetsNeg);
		System.out.println("Total     -> " + nbTweets);
		System.out.println("-------------------------------------------");

		// utilisation de l'API iTunes
		itunes.Request r = new itunes.Request();
		r.setValeurWhere("Berg");
		double[] resultat = itunes.ApiRequest.artistAction(r);
		for (int k = 0; k < resultat.length; k++) {
			System.out.println(resultat[k] + "\n");
		}
	}

}
