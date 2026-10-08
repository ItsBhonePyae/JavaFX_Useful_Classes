package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Librarian_Controller {

    @FXML
    private AnchorPane Librarian_Pane;

    @FXML
    private Button btnAuthor;

    @FXML
    private Button btnBook;

    @FXML
    private Button btnCategory;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnMember;

    @FXML
    private Button btnPublisher;

    @FXML
    private Label lblWelcome;

    @FXML
    private AnchorPane pane;

    @FXML
    void clickAuthor(ActionEvent event) throws IOException {

    	Librarian_Pane.getChildren().clear();
    	pane=FXMLLoader.load(getClass().getResource("/view/Author.fxml"));
    	Librarian_Pane.getChildren().add(pane);
    }

    @FXML
    void clickBook(ActionEvent event) throws IOException {

    	Librarian_Pane.getChildren().clear();
    	pane=FXMLLoader.load(getClass().getResource("/view/Book.fxml"));
    	Librarian_Pane.getChildren().add(pane);
    }

    @FXML
    void clickCategory(ActionEvent event) throws IOException {

    	Librarian_Pane.getChildren().clear();
    	pane=FXMLLoader.load(getClass().getResource("/view/Category.fxml"));
    	Librarian_Pane.getChildren().add(pane);
    }

    @FXML
    void clickLogout(ActionEvent event) throws IOException {

    	Parent root=FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
    	Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene=new Scene(root,900,700);
		stage.setTitle("Admin Page");
		scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
		stage.setTitle("Library Management System");
		Image icon=new Image("file:///C:/16G_Project/Library_Management_System_16G/src/Images/photo_2026-09-29_22-27-38-removebg-preview.png");
		stage.getIcons().add(icon);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void clickMember(ActionEvent event) throws IOException {

    	Librarian_Pane.getChildren().clear();
    	pane=FXMLLoader.load(getClass().getResource("/view/Member.fxml"));
    	Librarian_Pane.getChildren().add(pane);
    }

    @FXML
    void clickPublisher(ActionEvent event) throws IOException {

    	Librarian_Pane.getChildren().clear();
    	pane=FXMLLoader.load(getClass().getResource("/view/Publisher.fxml"));
    	Librarian_Pane.getChildren().add(pane);
    }

}
