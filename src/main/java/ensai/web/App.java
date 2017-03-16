package ensai.web;

import java.io.IOException;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		dataVille("Game of thrones", "Paris");
	}

	public static void dataVille(String recherche, String ville) throws Exception {
		// Obtenir le latitude et longitude d'une ville (API GoogleMap)
		Double[] location = new SearchGoogleMap().getLocation(ville);

		// Obtenir la météo
		SearchMeteo meteo = new SearchMeteo();
		String condition = meteo.getConditions(ville);

		Date today = new Date();
		System.out.println(today.getYear()+"-"+today.getMonth()+"-"+today.getDate());
		
		// utiliser la loc trouvée avant pour les tweets
		int nbTweetsPos = 0;
		int nbTweetsNeg = 0;
		// :) pour les tweets positifs, :( pour les tweets négatifs
		nbTweetsPos = new SearchTwitter(recherche + " :)", location, 10.00).nbTweets;
		nbTweetsNeg = new SearchTwitter(recherche + " since:2017-03-09 :(", location, 10.00).nbTweets;

		System.out.println("Recherche -> "+recherche);
		System.out.println("Lieu      -> "+ville);
		System.out.println("Météo     -> "+condition);
		System.out.println(":)        -> " + nbTweetsPos);
		System.out.println(":(        -> " + nbTweetsNeg);
	}
}
