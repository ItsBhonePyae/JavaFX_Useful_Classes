package model;

import java.time.LocalDate;

public class Users {

	private int User_ID;
	private String Name;
	private String Password;
	private String Email;
	private LocalDate DateOfBirth;
	private String Gender;
	private String Phone_No;
	private String Role;
	private String Address;
	private LocalDate Joined_Date;
	private String Image;
	
	
	
	public Users(int user_ID, String name, String password, String email, LocalDate dateOfBirth, String gender,
			String phone_No, String role, String address, LocalDate joined_Date, String image) {
		super();
		User_ID = user_ID;
		Name = name;
		Password = password;
		Email = email;
		DateOfBirth = dateOfBirth;
		Gender = gender;
		Phone_No = phone_No;
		Role = role;
		Address = address;
		Joined_Date = joined_Date;
		Image = image;
	}
	
	public int getUser_ID() {
		return User_ID;
	}
	public void setUser_ID(int user_ID) {
		User_ID = user_ID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public LocalDate getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getPhone_No() {
		return Phone_No;
	}
	public void setPhone_No(String phone_No) {
		Phone_No = phone_No;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public LocalDate getJoined_Date() {
		return Joined_Date;
	}
	public void setJoined_Date(LocalDate joined_Date) {
		Joined_Date = joined_Date;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	@Override
	public String toString() {
		return "Users [User_ID=" + User_ID + ", Name=" + Name + ", Password=" + Password + ", Email=" + Email
				+ ", DateOfBirth=" + DateOfBirth + ", Gender=" + Gender + ", Phone_No=" + Phone_No + ", Role=" + Role
				+ ", Address=" + Address + ", Joined_Date=" + Joined_Date + ", Image=" + Image + "]";
	}

	
	
	
}
