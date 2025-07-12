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

// Esta clase implementa el parser de feed de tipo rss (xml)


public class RssParser extends GeneralParser {
	private List<String> xmlList;
	private List<Element> roots;
	private List<Feed> feedList;
	
	public RssParser(List<String> xmlList) {
		super(null,null);
		this.xmlList = new ArrayList<String>(xmlList);
	}
	
	public List<Feed> getFeedList() {
		return feedList;
	}

	public void setFeedList(List<Feed> feedList) {
		this.feedList = feedList;
	}

	public List<String> getXmlList() {
		return xmlList;
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
	    List<Feed> feedList = new ArrayList<>();

	    for (Element root : roots) {
	        List<Article> articleList = new ArrayList<>();
	        NodeList nList = root.getChildNodes();
	        Feed feed = new Feed(null);

	        for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);

	            if (nNode.getNodeType() == Node.ELEMENT_NODE && nNode.getNodeName().equals("channel")) {
	                Element currentChannel = (Element) nNode;
	                feed.setSiteName(currentChannel.getElementsByTagName("title").item(0).getTextContent());
	            }

	            if (nNode.getNodeType() == Node.ELEMENT_NODE && nNode.getNodeName().equals("item")) {
	                Element currentItem = (Element) nNode;

	                String pubDateStr = currentItem.getElementsByTagName("pubDate").item(0).getTextContent();
	                SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
	                Date date = null;
	                try {
	                    date = formatter.parse(pubDateStr);
	                } catch (ParseException e) {
	                    e.printStackTrace();
	                }

	                Article article = new Article(
	                    currentItem.getElementsByTagName("title").item(0).getTextContent(),
	                    currentItem.getElementsByTagName("description").item(0).getTextContent(),
	                    date,
	                    currentItem.getElementsByTagName("link").item(0).getTextContent()
	                );
	                articleList.add(article);
	            }
	        }

	        feed.setArticleList(articleList);
	        feedList.add(feed);
	    }
	    
	    setFeedList(feedList);
	}

	
	private void buildDocument() {	
		try {
			DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			Document doc = null;
			
			List<Element> roots = new ArrayList<>();
			
			for (String xml : getXmlList()) {
	            doc = docBuilder.parse(new InputSource(new StringReader(xml)));
	            doc.getDocumentElement().normalize();
	            roots.add(doc.getDocumentElement());
			}
			
			setRoots(roots);
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Feed> buildFeed(){
		buildDocument();
		parseFeed(getRoots());
		
		
		return getFeedList();
	}

}
