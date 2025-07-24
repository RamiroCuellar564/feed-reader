package namedEntity;

import subscription.Counter;

public class NamedEntityFactory {

	public static NamedEntity createNamedEntity(String name, String category, int frequency) {
		Counter personCounter = new Counter();
		switch(category) {
			case "Person":
				personCounter.increment();
				return new Person(name, category, frequency,personCounter.getValue());
			case "Name":
				return new Name(name, category, frequency);
			case "Surname":
				return new Surname(name, category, frequency);
			case "Company":
				return new Company(name, category, frequency);
			case "null":
				break;
		}
		return null;
	}	
}
