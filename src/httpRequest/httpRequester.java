package httpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import parser.SubscriptionParser;
import subscription.SingleSubscription;
import subscription.Subscription;

// Esta clase se encarga de realizar efectivamente el pedido de feed al servidor de noticias

public class httpRequester {
	private Subscription subscription;
	private List<String> xmlList;
	
	public httpRequester(Subscription sub) {
		super();
		this.subscription = sub;
	}
	
	public void setXmlList(List<String> list) {
		this.xmlList = list;
	}
	
	public List<String> getXmlList(){
		return xmlList;
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
	
	public void buildXmlList() {
		String url;
		String param;
		List<String> xmlList = new ArrayList<String> ();
		for(int i=0;i<subscription.getSubscriptionsList().size();i++) {
			SingleSubscription sub = subscription.getSingleSubscription(i);
			if(sub.getUrlType().equals("rss")) {
				url = sub.getUrl();
				for(int j=0;j<sub.getUrlParamsSize();j++) {
					param = sub.getUrlParams(j);
					url = url.replace("%s",param);
					xmlList.add(getFeedRss(url));
				}
			}
		}
		setXmlList(xmlList);
	}
	
	public void printXmlList() {
		for(int i=0;i<this.xmlList.size(); i++) {
			System.out.println(this.xmlList.get(i));
		}
	}
	
	public static void main(String[] args) {
		SubscriptionParser parser = new SubscriptionParser("config/subscriptions.json");
        Subscription subscription = null;
		try {
			subscription = parser.buildSubscription();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        httpRequester feed = new httpRequester(subscription);
        feed.buildXmlList();
        feed.printXmlList();
        
	}

}
