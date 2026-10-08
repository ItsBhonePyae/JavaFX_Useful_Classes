package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Security.PasswordUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Login_Controller implements Initializable{

    @FXML
    private Button btnClear;

    @FXML
    private Button btnLogin;

    @FXML
    private ComboBox<?> cbRole;

    @FXML
    private ImageView img;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblRole;

    @FXML
    private Label lblTitle;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField tfName;

    @FXML
    private PasswordField tfPassword;

    Datebase_Connection db = new Datebase_Connection();
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    int result;
    @FXML
    void clickClear(ActionEvent event) {

    	tfName.clear();
    	tfPassword.clear();
    	cbRole.setValue(null);
    }

    @FXML
    void clickLogin(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {

    	con=db.getDB();
    	String name=tfName.getText();
    	String pw=tfPassword.getText();
    	
    	String role=cbRole.getSelectionModel().getSelectedItem().toString();
		if(role.equals("Admin"))
		{
			role="Admin";
		}
		if(role.equals("Librarian"))
		{
			role="Librarian";
		}
		
		pstmt=con.prepareStatement("SELECT Name,Password,Role FROM users WHERE Name=? AND Role=?");
		pstmt.setString(1, name);
		pstmt.setString(2, role);
		
		rs=pstmt.executeQuery();
		
		if(rs.next())
		{
			
			String hash_pw=rs.getString("Password");
			boolean isPasswordCorrect = PasswordUtil.checkPassword(pw, hash_pw);
			
			if(isPasswordCorrect)
			{
			if(role.equals("Admin"))
			{
				Parent root=FXMLLoader.load(getClass().getResource("/view/User.fxml"));
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
			if(role.equals("Librarian"))
			{
				Parent root=FXMLLoader.load(getClass().getResource("/view/Librarian.fxml"));
				Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
				Scene scene=new Scene(root,900,700);
				stage.setTitle("Librarian Page");
				scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
				stage.setTitle("Library Management System");
				Image icon=new Image("file:///C:/16G_Project/Library_Management_System_16G/src/Images/photo_2026-09-29_22-27-38-removebg-preview.png");
				stage.getIcons().add(icon);
				stage.setScene(scene);
				stage.show();
			}
		}else 
		{
			JOptionPane.showMessageDialog(null, "Make it Right Nigga!");
		}
		}else
		{
			JOptionPane.showMessageDialog(null, "User not found or incorrect role!");
        }
		}
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		 ObservableList roleList=FXCollections.observableArrayList();
			roleList.add("Admin");
			roleList.add("Librarian");
			cbRole.setItems(roleList);
	}

}
