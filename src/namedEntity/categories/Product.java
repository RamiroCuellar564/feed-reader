package namedEntity.categories;

import namedEntity.NamedEntity;

public class Product extends NamedEntity {
	private boolean isComercial;
	private String producer;
	
	public Product(String name, String category, int frequency) {
		super(name, category, frequency);
	}

	public boolean isComercial() {
		return isComercial;
	}

	public void setComercial(boolean isComercial) {
		this.isComercial = isComercial;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

}
