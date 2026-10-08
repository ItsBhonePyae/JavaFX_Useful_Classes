package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Datebase_Connection {

	public Connection getDB() throws SQLException, ClassNotFoundException
	{
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		String url="jdbc:mysql://localhost:3306/library_management_system_school";
//		Connection con=DriverManager.getConnection(url,"root","");
//		return con;
		//1. Define the driver
		Class.forName("com.mysql.cj.jdbc.Driver");//ClassNotFoundException
		
		//2.Define URL
		String url="jdbc:mysql://localhost:3306/library_management_system_school";
		
		//3.Connection
		Connection con=DriverManager.getConnection(url,"root","");//Where database have/SQLException
		return con;
	}
}
