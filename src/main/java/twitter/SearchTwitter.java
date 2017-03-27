package twitter;

import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class SearchTwitter {

	public int nbTweets = 0;

	public SearchTwitter(String recherche) throws TwitterException {
		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey("ePCYTxOV0J28ol4iE2mSxUYej")
					.setOAuthConsumerSecret("sFsaQzueveR8boFFbbngjQ8DUrQRvNAvGnKGcnzOBo407nz0h8")
					.setOAuthAccessToken("837234720660406272-g2cbn4CbndBlHS8mXKl5529w0J2sHZC")
					.setOAuthAccessTokenSecret("Oc1Wl3RLINHYFzEFOdi1yxaMzzIhJvoE2tcPdrzugKzsY");
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();

			// Ce qu'on recherche
			Query q = new Query(recherche);
			// dire combien on veut de retours
			q.setCount(100);

			List<Status> tweets = twitter.search(q).getTweets();
			for (Status currentTweet : tweets) {
				// GeoLocation loc = currentTweet.getGeoLocation();
				// if (loc != null) {
				// System.out.println(loc);
				// }
				// // Date
				// System.out.println("Creat : " + currentTweet.getCreatedAt());
				// // Nom
				// System.out.println("By : " +
				// currentTweet.getUser().getName());
				// // Localisation
				// System.out.println("Loc : " +
				// currentTweet.getUser().getLocation());
				// // Message
				// System.out.println("Message : " + currentTweet.getText());
				// // Source
				// System.out.println("Source : " + currentTweet.getSource());
				// System.out.println("---------------------------------------------------------");
				nbTweets++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SearchTwitter(String recherche, Double[] location, double rayon) throws TwitterException {
		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey("ePCYTxOV0J28ol4iE2mSxUYej")
					.setOAuthConsumerSecret("sFsaQzueveR8boFFbbngjQ8DUrQRvNAvGnKGcnzOBo407nz0h8")
					.setOAuthAccessToken("837234720660406272-g2cbn4CbndBlHS8mXKl5529w0J2sHZC")
					.setOAuthAccessTokenSecret("Oc1Wl3RLINHYFzEFOdi1yxaMzzIhJvoE2tcPdrzugKzsY");
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();

//			// Ce qu'on recherche
//			Query q = new Query(recherche);
//			// Ajouter un emplacement
//			q.geoCode(new GeoLocation(location[0], location[1]), rayon, "km");
//			// dire combien on veut de retours
//			q.setCount(100);
//
//			List<Status> tweets = twitter.search(q).getTweets();
//			for (Status currentTweet : tweets) {
//				// GeoLocation loc = currentTweet.getGeoLocation();
//				// if (loc != null) {
//				// System.out.println(loc);
//				// }
//				// // Date
//				// System.out.println("Creat : " + currentTweet.getCreatedAt());
//				// // Nom
//				// System.out.println("By : " +
//				// currentTweet.getUser().getName());
//				// // Localisation
//				// System.out.println("Loc : " +
//				// currentTweet.getUser().getLocation());
//				// // Message
//				// System.out.println("Message : " + currentTweet.getText());
//				// // Source
//				// System.out.println("Source : " + currentTweet.getSource());
//				// System.out.println("---------------------------------------------------------");
//				nbTweets++;
//			}

			// Ce qu'on recherche
			Query query = new Query(recherche);
			// Ajouter un emplacement
			query.geoCode(new GeoLocation(location[0], location[1]), rayon, "km");
			// dire combien on veut de retours
			query.setCount(100);

			QueryResult result = twitter.search(query);
			List<Status> tweets = result.getTweets();
			for (Status currentTweet : tweets) {
				nbTweets++;
			}
			if (result.hasNext())// there is more pages to load
			{
				query = result.nextQuery();
				result = twitter.search(query);
				tweets = result.getTweets();
				for (Status currentTweet : tweets) {
					nbTweets++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
