package parser;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import feed.Article;
import feed.Feed;
import httpRequest.httpRequester;
import subscription.Subscription;

// Esta clase implementa el parser de feed de tipo rss (xml)


public class RssParser extends GeneralParser {
	private List<String> xmlList;
	private List<Element> roots;
	private List<Feed> feedList;
	
	public RssParser(List<String> xmlList) {
		super(null,null);
		this.xmlList = new ArrayList<String>(xmlList);
	    this.feedList = new ArrayList<>();
	    this.roots = new ArrayList<>();
	}
	
	public List<Feed> getFeedList() {
		return feedList;
	}

	public void setFeedList(List<Feed> feedList) {
		this.feedList = feedList;
	}
	
	public int getFeedCount() {
		return feedList.size();
	}

	public List<String> getXmlList() {
		return this.xmlList;
	}
	
	public List<Element> getRoots() {
		return roots;
	}
	
	public int getRootCount() {
		return roots.size();
	}

	public void setRoots(List<Element> roots) {
		this.roots = roots;
	}

	
	private void parseFeed(List<Element> roots) {
	    this.feedList = new ArrayList<>();

	    for (Element root : roots) {
	        List<Article> articleList = new ArrayList<>();
	        Feed feed = new Feed(null);

	        Element channelElement = (Element) root.getElementsByTagName("channel").item(0);

	        if (channelElement != null) {
	            feed.setSiteName(channelElement.getElementsByTagName("title").item(0).getTextContent());

	            NodeList itemNodes = channelElement.getElementsByTagName("item");

	            for (int i = 0; i < itemNodes.getLength(); i++) {
	                Element item = (Element) itemNodes.item(i);

	                String pubDateStr = getTextContent(item, "pubDate");
	                SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
	                Date date = null;

	                try {
	                    date = formatter.parse(pubDateStr);
	                } catch (ParseException e) {
	                    e.printStackTrace();
	                }

	                Article article = new Article(
	                    getTextContent(item, "title"),
	                    getTextContent(item, "description"),
	                    date,
	                    getTextContent(item, "link")
	                );

	                articleList.add(article);
	            }
	        }

	        feed.setArticleList(articleList);
	        this.feedList.add(feed);
	    }
	}

	private String getTextContent(Element parent, String tagName) {
	    Node node = parent.getElementsByTagName(tagName).item(0);
	    return (node != null) ? node.getTextContent() : "";
	}

	
	private void buildDocument() {	
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			Document doc = null;
			
			
			for (String xml : getXmlList()) {
	            doc = docBuilder.parse(new InputSource(new StringReader(xml)));
	            doc.getDocumentElement().normalize();
	            this.roots.add(doc.getDocumentElement());
			}
			
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Feed> buildFeed(){
		buildDocument();
		parseFeed(getRoots());
		
		
		return getFeedList();
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
        List<String> xmlList = feed.getXmlList();
        for (String xml : xmlList) {
            System.out.println("Resumen del XML: " + xml.substring(0, Math.min(1000, xml.length())));
        }
        RssParser rssParser = new RssParser(feed.getXmlList());
        List<Feed> feeds = rssParser.buildFeed();
        
        for (Feed f : feeds) {
            System.out.println("Feed: " + f.getSiteName());
            for (Article a : f.getArticleList()) {
                System.out.println(" - " + a.getTitle());
            }
        }
	}
	

}
