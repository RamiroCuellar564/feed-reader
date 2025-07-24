package namedEntity;

public class Person extends NamedEntity {
    protected int id;

    public Person(String name, String category, int frequency,int id) {
        super(name, category, frequency);
        this.id = id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public int getId() {
    	return id;
    }
}