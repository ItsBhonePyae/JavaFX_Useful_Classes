package model;

public class Publisher {

	private int Publisher_ID;
	private String Name;
	
	
	
	public Publisher(int publisher_ID, String name) {
		super();
		Publisher_ID = publisher_ID;
		Name = name;
	}



	public int getPublisher_ID() {
		return Publisher_ID;
	}



	public void setPublisher_ID(int publisher_ID) {
		Publisher_ID = publisher_ID;
	}



	public String getName() {
		return Name;
	}



	public void setName(String name) {
		Name = name;
	}



	@Override
	public String toString() {
		return "Publisher [Publisher_ID=" + Publisher_ID + ", Name=" + Name + "]";
	}
	
	
}
