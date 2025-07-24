package namedEntity.categories;

public final class Surname extends Person {
	protected String origin;
	
	public Surname(String name, String category, int frequency) {
		super(name, category, frequency, 0);
	}
	
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	
	public String getOrigin() {
		return origin;
	}

}
