package model;

public class Book {
	private int Book_ID;
	private String Title;
	private String ISBN;
	private int Author_ID;
	private int Published_Year;
	private int Category_ID;
	private String Description;
	private int Copies;
	private int Publisher_ID;
	private String Image;
	
	public Book(int book_ID, String title, String iSBN, int author_ID, int published_Year, int category_ID,
			String description, int copies, int publisher_ID, String image) {
		super();
		Book_ID=book_ID;
		Title = title;
		ISBN = iSBN;
		Author_ID = author_ID;
		Published_Year = published_Year;
		Category_ID = category_ID;
		Description = description;
		Copies = copies;
		Publisher_ID = publisher_ID;
		Image = image;
	}

	

	public Book(int book_ID, String title, String iSBN, int published_Year, String description, int copies,
			String image) {
		super();
		Book_ID = book_ID;
		Title = title;
		ISBN = iSBN;
		Published_Year = published_Year;
		Description = description;
		Copies = copies;
		Image = image;
	}



	public int getBook_ID() {
		return Book_ID;
	}


	public void setBook_ID(int book_ID) {
		Book_ID = book_ID;
	}


	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public int getAuthor_ID() {
		return Author_ID;
	}
	public void setAuthor_ID(int author_ID) {
		Author_ID = author_ID;
	}
	public int getPublished_Year() {
		return Published_Year;
	}
	public void setPublished_Year(int published_Year) {
		Published_Year = published_Year;
	}
	public int getCategory_ID() {
		return Category_ID;
	}
	public void setCategory_ID(int category_ID) {
		Category_ID = category_ID;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getCopies() {
		return Copies;
	}
	public void setCopies(int copies) {
		Copies = copies;
	}
	public int getPublisher_ID() {
		return Publisher_ID;
	}
	public void setPublisher_ID(int publisher_ID) {
		Publisher_ID = publisher_ID;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	@Override
	public String toString() {
		return "Book [Book_ID=" + Book_ID + ", Title=" + Title + ", ISBN=" + ISBN + ", Author_ID=" + Author_ID
				+ ", Published_Year=" + Published_Year + ", Category_ID=" + Category_ID + ", Description=" + Description
				+ ", Copies=" + Copies + ", Publisher_ID=" + Publisher_ID + ", Image=" + Image + "]";
	}
	
	

}
