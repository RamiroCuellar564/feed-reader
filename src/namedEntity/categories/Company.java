package namedEntity.categories;

import namedEntity.NamedEntity;

public final class Company extends NamedEntity {
	protected int members;
	protected String type;

	public Company(String name, String category, int frequency) {
		super(name, category, frequency);
	}
	
	public void setMembers(int numberOfMembers) {
		this.members = numberOfMembers;
	}
	
	public int getMembers() {
		return members;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

}
