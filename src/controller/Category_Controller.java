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
import model.Category;

public class Category_Controller implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<Category,Integer> colCategory_ID;

    @FXML
    private TableColumn<Category,String> colName;

    @FXML
    private Label lblName;

    @FXML
    private Label lblTitle;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<Category> tblCategory;

    @FXML
    private TextField tfName;
    
    @FXML
    private TextField tfSearch;
    
    @FXML
    private Label lblSearch;
    
    Datebase_Connection db = new Datebase_Connection();
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    int result;
    ObservableList<Category> cList=FXCollections.observableArrayList();
    Category c;

    @FXML
    void clickAdd(ActionEvent event) throws ClassNotFoundException, SQLException {
    	cList.clear();
    	con=db.getDB();
    	
    	if(btnAdd.getText().equals("Add"))
    	{
    		String name=tfName.getText();
    		
    		if(name.isEmpty())
    		{
    			JOptionPane.showMessageDialog(null, "Fill The Require NIGGA!!","Input Warning!!",JOptionPane.WARNING_MESSAGE);return;
    		}
    		
    		pstmt=con.prepareStatement("INSERT INTO `category`(`Name`) VALUES (?)");
    		pstmt.setString(1, name);
    		result=pstmt.executeUpdate();
        	if(result>0)
    		{
    			JOptionPane.showMessageDialog(null, "Insert User Record Success!");
    		}
        	getCategory();
        	tblCategory.setItems(cList);
        	
    	}else if(btnAdd.getText().equals("Edit"))
    	{
    		String name1=tfName.getText();
    		
    		pstmt=con.prepareStatement("UPDATE `category` SET `Name` = ? WHERE `Category_ID` = ?");
    		pstmt.setString(1, name1);
    		pstmt.setInt(2, c.getCategory_ID());
    		result=pstmt.executeUpdate();
    		if (result > 0) {
    		    JOptionPane.showMessageDialog(null, "Update Category Successfully!");
    		}
    	cList.clear();
    	cList = getCategory();
    	tblCategory.setItems(cList);
    	}

    }

    @FXML
    void clickClear(ActionEvent event) {

    	btnAdd.setText("Add");
    	tfName.clear();
    }

    @FXML
    void clickDelete(ActionEvent event) throws ClassNotFoundException, SQLException {

    	con=db.getDB();
    	Category data=tblCategory.getSelectionModel().getSelectedItem();
    	
    	if(data!=null)
    	{
    		int value=JOptionPane.showConfirmDialog(null, "Are You Sure To Delete "+data.getName()+"?");
    		if(value==0)
    		{
    			pstmt=con.prepareStatement("DELETE FROM category WHERE Category_ID=?");
				pstmt.setInt(1, data.getCategory_ID());
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
    	cList.clear();
    	cList.remove(data);
    	cList=getCategory();
    	tblCategory.setItems(cList);
    	
    }

    @FXML
    void clickUpdate(ActionEvent event) throws ClassNotFoundException, SQLException {

    	con=db.getDB();
    	c=tblCategory.getSelectionModel().getSelectedItem();
    	btnAdd.setText("Edit");
    	if(c!=null)
    	{
    		tfName.setText(c.getName());
    		
    	}else
    	{
    		JOptionPane.showMessageDialog(null, "Click Category NIGGA!","Null",JOptionPane.WARNING_MESSAGE);
    	}
    }
    @FXML
    void clickEnter(ActionEvent event) throws ClassNotFoundException, SQLException {

    	String search=tfSearch.getText().trim();
    	cList.clear();
    	cList=getCategory_Search(search);
    	tblCategory.setItems(cList);
    }
    public ObservableList<Category> getCategory_Search(String sn) throws ClassNotFoundException, SQLException
    {
    	cList.clear();
    	con=db.getDB();
    	pstmt=con.prepareStatement("SELECT * FROM Category WHERE Name LIKE ?");
    	pstmt.setString(1,"%"+ sn+"%");
    	rs=pstmt.executeQuery();
    	while(rs.next())
    	{
    		int id=rs.getInt(1);
    		String name=rs.getString(2);
    		
    		c=new Category(id,name);
    		cList.add(c);
    	}
    	tblCategory.setItems(cList);
    	
    	return cList;
    }
    
    public ObservableList<Category> getCategory() throws SQLException, ClassNotFoundException
    {
    	cList.clear();
    	con=db.getDB();
    	pstmt=con.prepareStatement("SELECT * FROM Category");
    	rs=pstmt.executeQuery();
    	
    	while(rs.next())
    	{
    		int id=rs.getInt(1);
    		String n=rs.getString(2);
    		
    		c=new Category(id,n);
    		cList.add(c);
    	}
		return cList;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		colCategory_ID.setCellValueFactory(new PropertyValueFactory<>("Category_ID"));
		colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
		cList.clear();
		try {
			getCategory();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tblCategory.setItems(cList);
	}

}
