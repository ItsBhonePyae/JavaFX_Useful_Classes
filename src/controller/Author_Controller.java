package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Author;
import model.Publisher;

public class Author_Controller implements Initializable{

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<Author, Integer> colAuthor_ID;

    @FXML
    private TableColumn<Author, String> colName;

    @FXML
    private Label lblName;

    @FXML
    private Label lblSearch;

    @FXML
    private Label lblTitle;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<Author> tblAuthor;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfSearch;
    
    Datebase_Connection db = new Datebase_Connection();
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    int result;
    ObservableList<Author> aList=FXCollections.observableArrayList();
    Author a;

    @FXML
    void clickAdd(ActionEvent event) throws SQLException, ClassNotFoundException {

    	aList.clear();
    	con=db.getDB();
    	
    	if(btnAdd.getText().equals("Add"))
    	{
    		String name=tfName.getText();
    		
    		if(name.isEmpty())
    		{
    			JOptionPane.showMessageDialog(null, "Fill The Require NIGGA!!","Input Warning!!",JOptionPane.WARNING_MESSAGE);return;
    		}
    		
    		pstmt=con.prepareStatement("INSERT INTO `author`(`Name`) VALUES (?)");
    		pstmt.setString(1, name);
    		result=pstmt.executeUpdate();
        	if(result>0)
    		{
    			JOptionPane.showMessageDialog(null, "Insert Author Record Success!");
    		}
        	getAuthor();
        	tblAuthor.setItems(aList);
        	
    	}else if(btnAdd.getText().equals("Edit"))
    	{
    		String name1=tfName.getText();
    		
    		pstmt=con.prepareStatement("UPDATE `Author` SET `Name` = ? WHERE `Author_ID` = ?");
    		pstmt.setString(1, name1);
    		pstmt.setInt(2, a.getAuthor_ID());
    		result=pstmt.executeUpdate();
    		if (result > 0) {
    		    JOptionPane.showMessageDialog(null, "Update Author Successfully!");
    		}
    	aList.clear();
    	aList = getAuthor();
    	tblAuthor.setItems(aList);
    	}
    }
    public ObservableList<Author>getAuthor() throws ClassNotFoundException, SQLException
    {
    	aList.clear();
    	con=db.getDB();
    	pstmt=con.prepareStatement("SELECT * FROM Author");
    	rs=pstmt.executeQuery();
    	
    	while(rs.next())
    	{
    		int id=rs.getInt(1);
    		String n=rs.getString(2);
    		
    		a=new Author(id,n);
    		aList.add(a);
    	}
		return aList;
    	
    }

    @FXML
    void clickClear(ActionEvent event) {

    	btnAdd.setText("Add");
    	tfName.clear();
    }

    @FXML
    void clickDelete(ActionEvent event) throws ClassNotFoundException, SQLException {

    	con=db.getDB();
    	Author data=tblAuthor.getSelectionModel().getSelectedItem();
    	
    	if(data!=null)
    	{
    		int value=JOptionPane.showConfirmDialog(null, "Are You Sure To Delete "+data.getName()+"?");
    		if(value==0)
    		{
    			pstmt=con.prepareStatement("DELETE FROM Author WHERE Author_ID=?");
				pstmt.setInt(1, data.getAuthor_ID());
				result=pstmt.executeUpdate();
				if(result>0)
				{
					JOptionPane.showMessageDialog(null,data.getName()+ "'s Delete Data Succesful!");
				}
    		}
    	}else 
		{
			JOptionPane.showMessageDialog(null, "The Data is Null Nigga!!","Input Warning",JOptionPane.WARNING_MESSAGE);
		}
    	aList.clear();
    	aList.remove(data);
    	aList = getAuthor();
    	tblAuthor.setItems(aList);
    }

    public ObservableList<Author> getAuthor_Search(String sn) throws ClassNotFoundException, SQLException
    {
    	aList.clear();
    	con=db.getDB();
    	pstmt=con.prepareStatement("SELECT * FROM Author WHERE Name LIKE ?");
    	pstmt.setString(1,"%"+ sn+"%");
    	rs=pstmt.executeQuery();
    	while(rs.next())
    	{
    		int id=rs.getInt(1);
    		String name=rs.getString(2);
    		
    		a=new Author(id,name);
    		aList.add(a);
    	}
    	tblAuthor.setItems(aList);
    	
    	return aList;
    }
    @FXML
    void clickEnter(ActionEvent event) throws ClassNotFoundException, SQLException {

    	String search=tfSearch.getText().trim();
    	aList.clear();
    	aList=getAuthor_Search(search);
    	tblAuthor.setItems(aList);
    }

    @FXML
    void clickUpdate(ActionEvent event) throws ClassNotFoundException, SQLException {

    	con=db.getDB();
    	a=tblAuthor.getSelectionModel().getSelectedItem();
    	btnAdd.setText("Edit");
    	if(a!=null)
    	{
    		tfName.setText(a.getName());
    		
    	}else
    	{
    		JOptionPane.showMessageDialog(null, "Click Author NIGGA!","Null",JOptionPane.WARNING_MESSAGE);
    	}
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		colAuthor_ID.setCellValueFactory(new PropertyValueFactory<>("Author_ID"));
		colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
		aList.clear();
		try {
			getAuthor();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tblAuthor.setItems(aList);
	}

}
