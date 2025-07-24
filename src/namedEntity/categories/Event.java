package namedEntity.categories;

import java.util.Date;

import namedEntity.NamedEntity;

public class Event extends NamedEntity {
	private Date date;
	private boolean isRecurring;
	
	public Event(String name, String category, int frequency) {
		super(name, category, frequency);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isRecurring() {
		return isRecurring;
	}

	public void setRecurring(boolean isRecurring) {
		this.isRecurring = isRecurring;
	}

}
