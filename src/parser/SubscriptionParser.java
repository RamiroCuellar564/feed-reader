package parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;
import subscription.Subscription;
import subscription.SingleSubscription;

/*
 * Esta clase implementa el parser del  archivo de suscripcion (json)
 * Leer https://www.w3docs.com/snippets/java/how-to-parse-json-in-java.html
 * */

public class SubscriptionParser extends GeneralParser{
	private JSONArray subscriptionList;
	private List<String> urlList;
	private List<List<String>> urlParamsList;
	private List<String> urlTypeList;
	
	public SubscriptionParser(String filePath) {
		super(filePath);
	}
	
	public JSONArray getSubscriptionList() {
		return subscriptionList;
	}
	
	public void setSubscriptionList(JSONArray subscriptionList) {
		this.subscriptionList = subscriptionList;
	}
	
	public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}

	public List<List<String>> getUrlParamsList() {
		return urlParamsList;
	}

	public void setUrlParamsList(List<List<String>> urlParamsList) {
		this.urlParamsList = urlParamsList;
	}

	public List<String> getUrlTypeList() {
		return urlTypeList;
	}

	public void setUrlTypeList(List<String> urlTypeList) {
		this.urlTypeList = urlTypeList;
	}
	
	@Override
	public void parse() throws Exception {
		// TODO Auto-generated method stub
		FileReader reader = null;
		try {
			reader = new FileReader(this.filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray subList = new JSONArray(new JSONTokener(reader));
		List<String> urlList = new ArrayList<String>();
		List<List<String>> urlParamsList = new ArrayList<List<String>>();
		List<String> urlTypeList = new ArrayList<String>();

		for (int i = 0; i < subList.length(); i++) {
		    JSONObject obj = subList.getJSONObject(i);
		    
		    urlList.add(obj.getString("url"));
		    
		    JSONArray paramsArray = obj.getJSONArray("urlParams");
		    List<String> paramsList = new ArrayList<>();
		    for (int j = 0; j < paramsArray.length(); j++) {
		        paramsList.add(paramsArray.getString(j));
		    }
		    urlParamsList.add(paramsList);
		    
		    urlTypeList.add(obj.getString("urlType"));
		}
		
		this.subscriptionList = subList;
		this.urlList = urlList;
		this.urlParamsList = urlParamsList;
		this.urlTypeList = urlTypeList;
		
	}
	
	public void printList() {
		System.out.print(this.subscriptionList);
	}
	
	public void printUrlList() {
		System.out.println(this.urlList);
	}
	public void printParamsList() {
		System.out.println(this.urlParamsList);
	}
	public void printUrlTypeList() {
		System.out.println(this.urlTypeList);
	}
	
	public static void main(String[] args) {
		SubscriptionParser parser = new SubscriptionParser("config/subscriptions.json");
		try {
			parser.parse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parser.printUrlList();
		parser.printParamsList();
		parser.printUrlTypeList();
		
		
		
		//SingleSubscription s0 = new SingleSubscription(null, null, null);
	}


	

}
