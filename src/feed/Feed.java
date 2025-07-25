package feed;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import namedEntity.NamedEntity;

/*Esta clase modela la lista de articulos de un determinado feed*/
public class Feed {
	String siteName;
	List<Article> articleList;
	List<List<NamedEntity>> namedEntityTable;
	HashMap<String, Integer> entityMap;
	
	public Feed(String siteName) {
		super();
		this.siteName = siteName;
		this.articleList = new ArrayList<Article>();
		this.namedEntityTable = new ArrayList<List<NamedEntity>>();
		this.entityMap = new HashMap<String, Integer>();
	}

	public String getSiteName(){
		return siteName;
	}
	
	public void setSiteName(String siteName){
		this.siteName = siteName;
	}
	
	public List<Article> getArticleList(){
		return articleList;
	}
	
	public void setArticleList(List<Article> articleList){
		this.articleList = articleList;
	}
	
	public void addArticle(Article a){
		this.getArticleList().add(a);
	}
	
	public Article getArticle(int i){
		return this.getArticleList().get(i);
	}
	
	public int getNumberOfArticles(){
		return this.getArticleList().size();
	}
	
	@Override
	public String toString(){
		return "Feed [siteName=" + getSiteName() + ", articleList=" + getArticleList() + "]";
	}
	

	public void prettyPrint(){
		for (Article a: this.getArticleList()){
			a.prettyPrint();
		}
		
	}
	
	public void setNamedEntityTable() {
		for(Article a: this.getArticleList()) {
			this.namedEntityTable.add(a.getNamedEntityList());
		}
	}
	
	public void buildEntityMap() {
		this.setNamedEntityTable();
		for(List<NamedEntity> row: this.namedEntityTable) {
			for(NamedEntity ne: row) {
				String name = ne.getName();
				if(this.entityMap.containsKey(name)) {
					int oldFreq = this.entityMap.get(name);
					int newFreq = oldFreq + ne.getFrequency();
					this.entityMap.replace(name, newFreq);
				}else {
					this.entityMap.put(ne.getName(), ne.getFrequency());
				}
			}
		}
	}
	
	public void printEntityTable() {
	    System.out.println("FEED: " + this.siteName);
	    System.out.println("=====================================");
	    System.out.printf("%-25s %s%n", "Entidad", "Frecuencia");
	    System.out.println("=====================================");

	    for (String key : this.entityMap.keySet()) {
	        String name = key;
	        String frequency = this.entityMap.get(key).toString();
	        System.out.printf("%-30s %s%n", name, frequency);
	    }

	    System.out.println("=====================================");
	}
	
	
	
	public static void main(String[] args) {
		  Article a1 = new Article("This Historically Black University Created Its Own Tech Intern Pipeline",
			  "A new program at Bowie State connects computing students directly with companies, bypassing an often harsh Silicon Valley vetting process",
			  new Date(),
			  "https://www.nytimes.com/2023/04/05/technology/bowie-hbcu-tech-intern-pipeline.html"
			  );
		 
		  Article a2 = new Article("This Historically Black University Created Its Own Tech Intern Pipeline",
				  "A new program at Bowie State connects computing students directly with companies, bypassing an often harsh Silicon Valley vetting process",
				  new Date(),
				  "https://www.nytimes.com/2023/04/05/technology/bowie-hbcu-tech-intern-pipeline.html"
				  );
		  
		  Article a3 = new Article("This Historically Black University Created Its Own Tech Intern Pipeline",
				  "A new program at Bowie State connects computing students directly with companies, bypassing an often harsh Silicon Valley vetting process",
				  new Date(),
				  "https://www.nytimes.com/2023/04/05/technology/bowie-hbcu-tech-intern-pipeline.html"
				  );
		  
		  Feed f = new Feed("nytimes");
		  f.addArticle(a1);
		  f.addArticle(a2);
		  f.addArticle(a3);

		  f.prettyPrint();
		  
	}
	
}
