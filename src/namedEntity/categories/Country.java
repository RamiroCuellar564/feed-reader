package namedEntity.categories;

public final class Country extends Place {
	protected int population;
	protected String language;
	
	
	public Country(String name, String category, int frequency) {
		super(name, category, frequency);
	}


	public int getPopulation() {
		return population;
	}


	public void setPopulation(int population) {
		this.population = population;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}
	
	

}
