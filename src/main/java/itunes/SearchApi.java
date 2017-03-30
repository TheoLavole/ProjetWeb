package itunes;

import static itunes.ParametersStringBuilder.buildLookUpStringParams;
import static itunes.ParametersStringBuilder.buildSearchStringParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import itunes.parameters.LookUpParameters;
import itunes.parameters.SearchParameters;
import itunes.parameters.parameter.Limit;
import itunes.parameters.parameter.Media;
import itunes.result.SearchResults;
import junit.framework.Assert;

/**
 * @author szagriichuk
 */
public class SearchApi {
    //    TODO: query string as configurable
    private static final String searchUrl = "https://itunes.apple.com/search?";
    private static final String lookupUrl = "https://itunes.apple.com/lookup?";
    private static final Logger logger = Logger.getLogger(SearchApi.class.getName());

    public static SearchResults search(SearchParameters params) {
        URL url;
        url = createUrl(searchUrl, buildSearchStringParams(params));
        URL url2 = createUrl(url.toString(), "&sort=recent");
//        System.out.println(url2);
        HttpURLConnection connection = openConnection(url2);
        return parseResponseData(readResponse(connection));
    }

    public static SearchResults lookup(LookUpParameters params) {
        URL url = createUrl(lookupUrl, buildLookUpStringParams(params));
        HttpURLConnection connection = openConnection(url);
        return parseResponseData(readResponse(connection));
    }

    private static HttpURLConnection openConnection(URL url) {
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            logger.log(Level.ALL, e.getMessage(), e);
            throw new iTunesSearchApiException(e.getMessage());
        }
        return connection;
    }

    private static SearchResults parseResponseData(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, SearchResults.class);
    }

    private static String readResponse(HttpURLConnection connection) {
        return readDataFromResponseStream(createResponseReader(connection));
    }

    private static String readDataFromResponseStream(BufferedReader responseReader) {
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = responseReader.readLine()) != null) {
                builder.append(line);
            }
            responseReader.close();
        } catch (IOException e) {
            logger.log(Level.ALL, e.getMessage(), e);
            throw new iTunesSearchApiException(e.getMessage());
        }
        return builder.toString();
    }

    private static BufferedReader createResponseReader(HttpURLConnection connection) {
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
        } catch (IOException e) {
            logger.log(Level.ALL, e.getMessage(), e);
            throw new iTunesSearchApiException(e.getMessage());
        }
        return in;
    }

    private static URL createUrl(String mainUrl, String stringParams) {
        URL url;
        try {
            url = new URL(mainUrl + stringParams);
        } catch (MalformedURLException e) {
            logger.log(Level.ALL, e.getMessage(), e);
            throw new iTunesSearchApiException(e.getMessage());
        }
        return url;
    }
    
 
        
    
}
