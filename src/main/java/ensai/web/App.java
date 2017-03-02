package ensai.web;

import java.util.List;

import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IllegalStateException, TwitterException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("ePCYTxOV0J28ol4iE2mSxUYej")
				.setOAuthConsumerSecret("sFsaQzueveR8boFFbbngjQ8DUrQRvNAvGnKGcnzOBo407nz0h8")
				.setOAuthAccessToken("837234720660406272-g2cbn4CbndBlHS8mXKl5529w0J2sHZC")
				.setOAuthAccessTokenSecret("Oc1Wl3RLINHYFzEFOdi1yxaMzzIhJvoE2tcPdrzugKzsY");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();

		// Ce qu'on recherche
		Query q = new Query("#LCK");
		q.setCount(100);
		List<Status> statuses = twitter.search().search(q).getTweets();
		for (Status status : statuses) {
			// Date
			System.out.println(status.getCreatedAt());
			// Nom + Texte
			System.out.println(status.getUser().getName() + " : " + status.getText());
			System.out.println("Loc : "+status.getUser().getLocation());
		}
	}
}
