package model;

public class Author {

	private int Author_ID;
	private String Name;
	
	
	public Author(int author_ID, String name) {
		super();
		Author_ID = author_ID;
		Name = name;
	}


	public int getAuthor_ID() {
		return Author_ID;
	}


	public void setAuthor_ID(int author_ID) {
		Author_ID = author_ID;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	@Override
	public String toString() {
		return "Author [Author_ID=" + Author_ID + ", Name=" + Name + "]";
	}
	
	
}
