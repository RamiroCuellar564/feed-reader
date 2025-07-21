import java.util.List;

import feed.Article;
import feed.Feed;
import httpRequest.httpRequester;
import namedEntity.NamedEntity;
import namedEntity.heuristic.QuickHeuristic;
import parser.RssParser;
import parser.SubscriptionParser;
import subscription.Subscription;

public class FeedReaderMain {

	private static void printHelp(){
		System.out.println("Please, call this program in correct way: FeedReader [-ne]");
	}
	
	public static void main(String[] args) {
		System.out.println("************* FeedReader version 1.0 *************");
		if (args.length == 0) {
			SubscriptionParser subParser = new SubscriptionParser("config/subscriptions.json");
			Subscription subscription = null;
			try {
				subscription = subParser.buildSubscription();
			} catch (Exception e) {
				e.printStackTrace();
			}
			httpRequester request = new httpRequester(subscription);
			request.buildXmlList();
			
			List<String> contentList = request.getXmlList();
			
			RssParser rssParser = new RssParser(contentList);
			List<Feed> feedList = rssParser.buildFeed();
			
			for(Feed feed: feedList) {
				feed.prettyPrint();
			}
			
		} else if (args.length == 1){
			SubscriptionParser subParser = new SubscriptionParser("config/subscriptions.json");
			Subscription subscription = null;
			QuickHeuristic heuristic = new QuickHeuristic();
			try {
				subscription = subParser.buildSubscription();
			} catch (Exception e) {
				e.printStackTrace();
			}
			httpRequester request = new httpRequester(subscription);
			request.buildXmlList();
			
			List<String> contentList = request.getXmlList();
			
			RssParser rssParser = new RssParser(contentList);
			List<Feed> feedList = rssParser.buildFeed();
			
			for(Feed feed: feedList) {
				feed.prettyPrint();
				for(Article article: feed.getArticleList()) {
					article.computeNamedEntities(heuristic);
					for(NamedEntity namedEntity: article.getNamedEntityList()) {
						namedEntity.prettyPrint();
					}
				}
			}
			/*
			Llamar a la heuristica para que compute las entidades nombradas de cada articulos del feed
			LLamar al prettyPrint de la tabla de entidades nombradas del feed.
			*/
			
		}else {
			printHelp();
		}
	}

}
