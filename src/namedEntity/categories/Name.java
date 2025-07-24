package namedEntity.categories;

import java.util.ArrayList;
import java.util.List;

public final class Name extends Person {
	protected String name;
	protected String origin;
	protected List<String> alternateForms = new ArrayList<String>();
	
	
	public Name(String name, String category, int frequency) {
		super(name, category, frequency, 0);
	}
	
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	
	public String getOrigin() {
		return origin;
	}
	
	
	public void addAlternateForm(String alternateName) {
		this.alternateForms.add(alternateName);
	}

	public List<String> getAlternateForms(){
		return this.alternateForms;
	}
}
