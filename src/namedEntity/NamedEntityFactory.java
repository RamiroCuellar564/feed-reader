package namedEntity;

import namedEntity.categories.*;
import namedEntity.topics.*;
import subscription.Counter;

public class NamedEntityFactory {
	
	public static NamedEntity topicFactory(String name, String category, int frequency) {
		switch(category) {
		case "Sport":
			return new Sport(name, category, frequency);
		case "Football":
			return new Football(name, category, frequency);
		case "Tennis":
			return new Tennis(name, category, frequency);
		case "Formula1":
			return new Formula1(name, category, frequency);
		case "Culture":
			return new Culture(name, category, frequency);
		case "Music":
			return new Music(name, category, frequency);
		case "Politics":
			return new Politics(name, category, frequency);
		}
		return null;
	}

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
			case "Place":
				return new Place(name, category, frequency);
			case "Country":
				return new Country(name, category, frequency);
			case "City":
				return new City(name, category, frequency);
			case "Address":
				return new Address(name, category, frequency);
			case "Product":
				return new Product(name, category, frequency);
			case "Event":
				return new Event(name, category, frequency);
			case "Date":
				return new Date(name, category, frequency);
			case "Topic":
				return topicFactory(name, category, frequency);
			case "null":
				break;
		}
		return null;
	}	
}
