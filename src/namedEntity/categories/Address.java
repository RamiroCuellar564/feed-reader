package namedEntity.categories;

public final class Address extends Place {
	protected String city;

	public Address(String name, String category, int frequency) {
		super(name, category, frequency);
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

}
