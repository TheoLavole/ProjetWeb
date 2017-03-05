package ensai.web;

import java.io.IOException;
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
	public static void main(String[] args) throws Exception {
//		new SearchTwitter("#TrumpFact");
		SearchMeteo meteo = new SearchMeteo();
		meteo.getConditions("Rennes");
	}
}


