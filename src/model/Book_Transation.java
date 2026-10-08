package model;

import java.time.LocalDate;

public class Book_Transation {

	private int Book_Transition_ID;
	private int Book_ID;
	private int Member_ID;
	private LocalDate Issued_Date;
	private LocalDate Returned_Date;
	private double Fine;
	public Book_Transation( int book_ID, int member_ID, LocalDate issued_Date,
			LocalDate returned_Date, double fine) {
		super();
		Book_ID = book_ID;
		Member_ID = member_ID;
		Issued_Date = issued_Date;
		Returned_Date = returned_Date;
		Fine = fine;
	}

	public int getBook_ID() {
		return Book_ID;
	}
	public void setBook_ID(int book_ID) {
		Book_ID = book_ID;
	}
	public int getMember_ID() {
		return Member_ID;
	}
	public void setMember_ID(int member_ID) {
		Member_ID = member_ID;
	}
	public LocalDate getIssued_Date() {
		return Issued_Date;
	}
	public void setIssued_Date(LocalDate issued_Date) {
		Issued_Date = issued_Date;
	}
	public LocalDate getReturned_Date() {
		return Returned_Date;
	}
	public void setReturned_Date(LocalDate returned_Date) {
		Returned_Date = returned_Date;
	}
	public double getFine() {
		return Fine;
	}
	public void setFine(double fine) {
		Fine = fine;
	}
	@Override
	public String toString() {
		return "Book_Transation [Book_Transition_ID=" + Book_Transition_ID + ", Book_ID=" + Book_ID + ", Member_ID="
				+ Member_ID + ", Issued_Date=" + Issued_Date + ", Returned_Date=" + Returned_Date + ", Fine=" + Fine
				+ "]";
	}
	
}
