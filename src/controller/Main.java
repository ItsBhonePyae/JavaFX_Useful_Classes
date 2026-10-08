package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[]args)
	{
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {
		
		//Parent root=FXMLLoader.load(getClass().getResource("/view/Book.fxml"));
		//Parent root=FXMLLoader.load(getClass().getResource("/view/Member.fxml"));
		//Parent root=FXMLLoader.load(getClass().getResource("/view/User.fxml"));
		//Parent root=FXMLLoader.load(getClass().getResource("/view/Librarian.fxml"));
		Parent root=FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		//Parent root=FXMLLoader.load(getClass().getResource("/view/Category.fxml"));
		//Parent root=FXMLLoader.load(getClass().getResource("/view/Publisher.fxml"));
		//Parent root=FXMLLoader.load(getClass().getResource("/view/Author.fxml"));
		Scene scene=new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
		stage.setTitle("Library Management System");
		Image icon=new Image("file:///C:/16G_Project/Library_Management_System_16G/src/Images/photo_2026-09-29_22-27-38-removebg-preview.png");
		stage.getIcons().add(icon);
		
		stage.setScene(scene);
		stage.show();
	}

}
