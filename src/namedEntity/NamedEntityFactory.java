package namedEntity;

import namedEntity.categories.*;
import namedEntity.topics.*;
import subscription.Counter;

public class NamedEntityFactory {
	
	public static NamedEntity topicFactory(String name, String category, int frequency) {
		switch(category.toLowerCase()) {
		case "sport":
			return new Sport(name, category, frequency);
		case "football":
			return new Football(name, category, frequency);
		case "tennis":
			return new Tennis(name, category, frequency);
		case "formula1":
			return new Formula1(name, category, frequency);
		case "culture":
			return new Culture(name, category, frequency);
		case "music":
			return new Music(name, category, frequency);
		case "politics":
			return new Politics(name, category, frequency);
		}
		return null;
	}

	public static NamedEntity createNamedEntity(String name, String category, int frequency) {
		Counter personCounter = new Counter();
		switch(category.toLowerCase()) {
			case "person":
				personCounter.increment();
				return new Person(name, category, frequency,personCounter.getValue());
			case "name":
				return new Name(name, category, frequency);
			case "surname":
				return new Surname(name, category, frequency);
			case "company":
				return new Company(name, category, frequency);
			case "place":
				return new Place(name, category, frequency);
			case "country":
				return new Country(name, category, frequency);
			case "city":
				return new City(name, category, frequency);
			case "address":
				return new Address(name, category, frequency);
			case "product":
				return new Product(name, category, frequency);
			case "event":
				return new Event(name, category, frequency);
			case "date":
				return new Date(name, category, frequency);
			case "topic":
				return topicFactory(name, category, frequency);
			case "null":
				break;
			default:
				break;
		}
		return null;
	}	
}
