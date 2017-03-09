package ensai.web;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		// Obtenir la météo
		 SearchMeteo meteo = new SearchMeteo();
		 meteo.getConditions("Rennes");
		
		 // Obtenir le latitude et longitude d'une ville (API GoogleMap)
		new SearchGoogleMap().getLocation("Rennes");
		
		// utiliser la loc trouvée avant pour les tweets
		// near:\"Paris\" within:15mi
		// :) pour les tweets positifs, :( pour les tweets négatifs
		new SearchTwitter("#FCBPSG :)");
	}
}