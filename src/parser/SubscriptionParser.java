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

	public SubscriptionParser(String filePath) {
		super(filePath);
	}
	
	
	public Subscription buildSubscription() throws Exception {
		FileReader reader = null;
		try {
			reader = new FileReader(this.filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		JSONArray subList = new JSONArray(new JSONTokener(reader));
        Subscription subscription = new Subscription(null);

        for (int i = 0; i < subList.length(); i++) {
            JSONObject obj = subList.getJSONObject(i);


            String url = obj.getString("url");
            String urlType = obj.getString("urlType");

            SingleSubscription singleSub = new SingleSubscription(url, null, urlType);

            JSONArray paramsArray = obj.getJSONArray("urlParams");
            for (int j = 0; j < paramsArray.length(); j++) {
                singleSub.setUrlParams(paramsArray.getString(j));
            }

            subscription.addSingleSubscription(singleSub);
        }

        return subscription;
		
	}
	
	public static void main(String[] args) {
		try {
            SubscriptionParser parser = new SubscriptionParser("config/subscriptions.json");
            Subscription subscription = parser.buildSubscription();
            subscription.prettyPrint();
        } catch (Exception e) {
            System.err.println("Error al leer el archivo de suscripciones: " + e.getMessage());
        }
	}
}
