package httpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import parser.SubscriptionParser;
import subscription.SingleSubscription;
import subscription.Subscription;

// Esta clase se encarga de realizar efectivamente el pedido de feed al servidor de noticias

public class httpRequester {
	private Subscription subscription;
	
	public httpRequester(Subscription sub) {
		super();
		this.subscription = sub;
	}
	
	public static String getFeedRss(String urlFeed){
		String feedRssXml = null;
		
		
		try {
			URI uri = URI.create(urlFeed);
            URL url = uri.toURL();
            
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            
            int status = con.getResponseCode();
            if(status != 200) {
            	throw new RuntimeException("Ha ocurrido un error: " + status);
            } else {
            	BufferedReader in = new BufferedReader(
            			  new InputStreamReader(con.getInputStream()));
            	String inputLine;
            	StringBuffer content = new StringBuffer();
            	while ((inputLine = in.readLine()) != null) {
            		content.append(inputLine);
            	}
            	in.close();
                con.disconnect();
                feedRssXml = content.toString();
            }      
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return feedRssXml;
	}

	public String getFeedReedit(String urlFeed) {
		String feedReeditJson = null;	
		return feedReeditJson;
	}
	
	public String request() {
		String req = "";
		String url;
		String param;
		for(int i=0;i<subscription.getSubscriptionsList().size()-1;i++) {
			SingleSubscription sub = subscription.getSingleSubscription(i);
			url = sub.getUrl();
			for(int j=0;j<sub.getUrlParamsSize()-1;j++) {
				param = sub.getUrlParams(j);
				url = url.replace("%s",param);
				req = req + getFeedRss(url);
			}
		}
		
		return req;
	}
	
	public static void main(String[] args) {
		
		try {
            SubscriptionParser parser = new SubscriptionParser("config/subscriptions.json");
            Subscription subscription = parser.buildSubscription();
            httpRequester feed = new httpRequester(subscription);
            
            System.out.println(feed.request());
            
        } catch (Exception e) {
            System.err.println("Error al leer el archivo de suscripciones: " + e.getMessage());
        }
	}

}
