package model;

import java.time.LocalDate;

public class Member {
	private int Member_ID;
	private String Name;
	private String NRC_No;
	private LocalDate DateOfBirth;
	private String Email;
	private String Phone_No;
	private String Address;
	private LocalDate Register_Date;
	private LocalDate Expried_Date;
	private int Book_Count;
	private String Gender;
	public Member( int member_ID,String name, String nRC_No, LocalDate dateOfBirth, String email, String phone_No,
			String address, LocalDate register_Date, LocalDate expried_Date, int book_Count, String gender) {
		super();
		Member_ID=member_ID;
		Name = name;
		NRC_No = nRC_No;
		DateOfBirth = dateOfBirth;
		Email = email;
		Phone_No = phone_No;
		Address = address;
		Register_Date = register_Date;
		Expried_Date = expried_Date;
		Book_Count = book_Count;
		Gender = gender;
	}
	
	public int getMember_ID() {
		return Member_ID;
	}

	public void setMember_ID(int member_ID) {
		Member_ID = member_ID;
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getNRC_No() {
		return NRC_No;
	}
	public void setNRC_No(String nRC_No) {
		NRC_No = nRC_No;
	}
	public LocalDate getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhone_No() {
		return Phone_No;
	}
	public void setPhone_No(String phone_No) {
		Phone_No = phone_No;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public LocalDate getRegister_Date() {
		return Register_Date;
	}
	public void setRegister_Date(LocalDate register_Date) {
		Register_Date = register_Date;
	}
	public LocalDate getExpried_Date() {
		return Expried_Date;
	}
	public void setExpried_Date(LocalDate expried_Date) {
		Expried_Date = expried_Date;
	}
	public int getBook_Count() {
		return Book_Count;
	}
	public void setBook_Count(int book_Count) {
		Book_Count = book_Count;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	@Override
	public String toString() {
		return "Member [Member_ID=" + Member_ID + ", Name=" + Name + ", NRC_No=" + NRC_No + ", DateOfBirth="
				+ DateOfBirth + ", Email=" + Email + ", Phone_No=" + Phone_No + ", Address=" + Address
				+ ", Register_Date=" + Register_Date + ", Expried_Date=" + Expried_Date + ", Book_Count=" + Book_Count
				+ ", Gender=" + Gender + "]";
	}
	
	

}
