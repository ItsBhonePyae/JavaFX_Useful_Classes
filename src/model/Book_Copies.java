package model;

public class Book_Copies {

	
	private int Book_Copies_ID;
	private int Book_ID;
	private int Copies;
	public Book_Copies( int book_ID, int copies) {
		super();
		Book_ID = book_ID;
		Copies = copies;
	}

	public int getBook_ID() {
		return Book_ID;
	}
	public void setBook_ID(int book_ID) {
		Book_ID = book_ID;
	}
	public int getCopies() {
		return Copies;
	}
	public void setCopies(int copies) {
		Copies = copies;
	}
	@Override
	public String toString() {
		return "Book_Copies [Book_Copies=" + Book_Copies_ID + ", Book_ID=" + Book_ID + ", Copies=" + Copies + "]";
	}
	
	
}
