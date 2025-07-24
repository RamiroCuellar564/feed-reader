package namedEntity;

import subscription.Counter;

public class NamedEntityFactory {

	public static NamedEntity createNamedEntity(String name, String category, int frequency) {
		Counter personCounter = new Counter();
		switch(category) {
			case "Person":
				personCounter.increment();
				return new Person(name, category, frequency,personCounter.getValue());
			case "null":
				break;
		}
		return null;
	}	
}
