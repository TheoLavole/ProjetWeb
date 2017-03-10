package ensai.web;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		dataVille("Rennes");
	}

	public static void dataVille(String ville) throws Exception {
		// Obtenir le latitude et longitude d'une ville (API GoogleMap)
		Double[] location = new SearchGoogleMap().getLocation(ville);

		// Obtenir la météo
		SearchMeteo meteo = new SearchMeteo();
		meteo.getConditions(ville);

		// utiliser la loc trouvée avant pour les tweets
		// near:\"Paris\" within:15mi
		// :) pour les tweets positifs, :( pour les tweets négatifs
		new SearchTwitter("#FCBPSG :)", location, 10.00);
	}
}
