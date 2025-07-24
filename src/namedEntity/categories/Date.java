package namedEntity.categories;

import namedEntity.NamedEntity;

public class Date extends NamedEntity {
	private Date day;
	
	public Date(String name, String category, int frequency) {
		super(name, category, frequency);
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}

}
