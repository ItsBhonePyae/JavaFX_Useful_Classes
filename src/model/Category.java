package model;

public class Category {

	private int Category_ID;
	private String Name;
	
	
	public Category(int category_ID, String name) {
		super();
		Category_ID = category_ID;
		Name = name;
	}


	public int getCategory_ID() {
		return Category_ID;
	}


	public void setCategory_ID(int category_ID) {
		Category_ID = category_ID;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	@Override
	public String toString() {
		return "Category [Category_ID=" + Category_ID + ", Name=" + Name + "]";
	}
	
	
}
