package ensai.web;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		// near:\"Paris\" within:15mi
		// :) pour les tweets positifs, :( pour les tweets négatifs
		new SearchTwitter("#FCBPSG :)");
		// SearchMeteo meteo = new SearchMeteo();
		// meteo.getConditions("Rennes");
	}
}