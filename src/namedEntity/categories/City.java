package namedEntity.categories;

public final class City extends Place {
	protected String country;
	protected String capital;
	protected String population;
	
	public City(String name, String category, int frequency) {
		super(name, category, frequency);
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

}
