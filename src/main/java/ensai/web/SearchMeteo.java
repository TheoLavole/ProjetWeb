package ensai.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SearchMeteo {
	static void getConditions(String city) throws Exception, IOException {
		String url = "http://www.prevision-meteo.ch/services/json/"+city;
		HttpURLConnection conn = (HttpURLConnection) new URL(
                url).openConnection();
        conn.connect();
        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        byte[] bytes = new byte[1024];
        int tmp ;
        while( (tmp = bis.read(bytes) ) != -1 ) {
            String chaine = new String(bytes,0,tmp);
            System.out.print(chaine);
        }
        conn.disconnect();
	}

}