package parser;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import feed.Feed;

// Esta clase implementa el parser de feed de tipo rss (xml)


public class RssParser extends GeneralParser {
	private List<String> xmlList;
	private List<String> titleList;
	private List<String> descriptionList;
	private List<String> pubDateList;
	private List<String> linkList;
	private List<Element> roots;
	private List<String> feedNameList;
	
	public RssParser(List<String> xmlList) {
		super(null,null);
		this.xmlList = new ArrayList<String>(xmlList);
	}
	
	public List<String> getFeedName() {
		return feedNameList;
	}

	public void setFeedName(List<String> feedNameList) {
		this.feedNameList = feedNameList;
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

	public List<String> getTitleList() {
		return titleList;
	}

	public void setTitleList(List<String> titleList) {
		this.titleList = titleList;
	}

	public List<String> getDescriptionList() {
		return descriptionList;
	}

	public void setDescriptionList(List<String> descriptionList) {
		this.descriptionList = descriptionList;
	}
	
	public List<String> getPubDateList() {
		return pubDateList;
	}

	public void setPubDateList(List<String> pubDateList) {
		this.pubDateList = pubDateList;
	}
	
	public List<String> getLinkList() {
		return linkList;
	}

	public void setLinkList(List<String> linkList) {
		this.linkList = linkList;
	}
	
	private void setLists(List<Element> roots) {
		List<String> titleList = new ArrayList<String>();
		List<String> descriptionList = new ArrayList<String>();
		List<String> pubDateList = new ArrayList<String>();
		List<String> linkList = new ArrayList<String>();
		List<String> feedNameList = new ArrayList<String>();
		
		for(Element root: roots) {
			NodeList nList = root.getChildNodes();
			
			for (int temp = 0; temp < nList.getLength(); temp++) {      
	            Node nNode = nList.item(temp);
	            
	            if(nNode.getNodeType() == Node.ELEMENT_NODE && nNode.getNodeName().equals("channel")) {
	            	Element eElement = (Element) nNode;
	            	feedNameList.add(eElement.getElementsByTagName("title").item(0).getTextContent());
	            }
		        if (nNode.getNodeType() == Node.ELEMENT_NODE && nNode.getNodeName().equals("item")) {     
	               Element eElement = (Element) nNode;
	               titleList.add(eElement.getElementsByTagName("title").item(0).getTextContent());
	               descriptionList.add(eElement.getElementsByTagName("title").item(0).getTextContent());
	               pubDateList.add(eElement.getElementsByTagName("pubDate").item(0).getTextContent());
	               linkList.add(eElement.getElementsByTagName("pubDate").item(0).getTextContent());
		        }
	         }
		}	
		setTitleList(titleList);
		setDescriptionList(descriptionList);
		setPubDateList(pubDateList);
		setLinkList(linkList);
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
	
	public Feed BuildFeed() {
		Feed feed = null;
		
		return feed;
	}

}
