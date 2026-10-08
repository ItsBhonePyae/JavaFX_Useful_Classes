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
import model.Publisher;

public class Publisher_Controller implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<Publisher, Integer> colPublisher_ID;

    @FXML
    private TableColumn<Publisher, String> colName;

    @FXML
    private Label lblName;

    @FXML
    private Label lblSearch;

    @FXML
    private Label lblTitle;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<Publisher> tblPublisher;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfSearch;
    
    Datebase_Connection db = new Datebase_Connection();
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    int result;
    ObservableList<Publisher> pList=FXCollections.observableArrayList();
    Publisher p;

    @FXML
    void clickAdd(ActionEvent event) throws SQLException, ClassNotFoundException {

    	pList.clear();
    	con=db.getDB();
    	
    	if(btnAdd.getText().equals("Add"))
    	{
    		String name=tfName.getText();
    		
    		if(name.isEmpty())
    		{
    			JOptionPane.showMessageDialog(null, "Fill The Require NIGGA!!","Input Warning!!",JOptionPane.WARNING_MESSAGE);return;
    		}
    		
    		pstmt=con.prepareStatement("INSERT INTO `publisher`(`Name`) VALUES (?)");
    		pstmt.setString(1, name);
    		result=pstmt.executeUpdate();
        	if(result>0)
    		{
    			JOptionPane.showMessageDialog(null, "Insert Publisher Record Success!");
    		}
        	getPublisher();
        	tblPublisher.setItems(pList);
        	
    	}else if(btnAdd.getText().equals("Edit"))
    	{
    		String name1=tfName.getText();
    		
    		pstmt=con.prepareStatement("UPDATE `publisher` SET `Name` = ? WHERE `Publisher_ID` = ?");
    		pstmt.setString(1, name1);
    		pstmt.setInt(2, p.getPublisher_ID());
    		result=pstmt.executeUpdate();
    		if (result > 0) {
    		    JOptionPane.showMessageDialog(null, "Update Publisher Successfully!");
    		}
    	pList.clear();
    	pList = getPublisher();
    	tblPublisher.setItems(pList);
    	}
    }
    public ObservableList<Publisher>getPublisher() throws ClassNotFoundException, SQLException
    {
    	pList.clear();
    	con=db.getDB();
    	pstmt=con.prepareStatement("SELECT * FROM Publisher");
    	rs=pstmt.executeQuery();
    	
    	while(rs.next())
    	{
    		int id=rs.getInt(1);
    		String n=rs.getString(2);
    		
    		p=new Publisher(id,n);
    		pList.add(p);
    	}
		return pList;
    	
    }

    @FXML
    void clickClear(ActionEvent event) {

    	btnAdd.setText("Add");
    	tfName.clear();
    }

    @FXML
    void clickDelete(ActionEvent event) throws ClassNotFoundException, SQLException {

    	con=db.getDB();
    	Publisher data=tblPublisher.getSelectionModel().getSelectedItem();
    	
    	if(data!=null)
    	{
    		int value=JOptionPane.showConfirmDialog(null, "Are You Sure To Delete "+data.getName()+"?");
    		if(value==0)
    		{
    			pstmt=con.prepareStatement("DELETE FROM publisher WHERE Publisher_ID=?");
				pstmt.setInt(1, data.getPublisher_ID());
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
    	pList.clear();
    	pList.remove(data);
    	pList = getPublisher();
    	tblPublisher.setItems(pList);
    }

    public ObservableList<Publisher> getPublisher_Search(String sn) throws ClassNotFoundException, SQLException
    {
    	pList.clear();
    	con=db.getDB();
    	pstmt=con.prepareStatement("SELECT * FROM Publisher WHERE Name LIKE ?");
    	pstmt.setString(1,"%"+ sn+"%");
    	rs=pstmt.executeQuery();
    	while(rs.next())
    	{
    		int id=rs.getInt(1);
    		String name=rs.getString(2);
    		
    		p=new Publisher(id,name);
    		pList.add(p);
    	}
    	tblPublisher.setItems(pList);
    	
    	return pList;
    }
    @FXML
    void clickEnter(ActionEvent event) throws ClassNotFoundException, SQLException {

    	String search=tfSearch.getText().trim();
    	pList.clear();
    	pList=getPublisher_Search(search);
    	tblPublisher.setItems(pList);
    }

    @FXML
    void clickUpdate(ActionEvent event) throws ClassNotFoundException, SQLException {

    	con=db.getDB();
    	p=tblPublisher.getSelectionModel().getSelectedItem();
    	btnAdd.setText("Edit");
    	if(p!=null)
    	{
    		tfName.setText(p.getName());
    		
    	}else
    	{
    		JOptionPane.showMessageDialog(null, "Click Category NIGGA!","Null",JOptionPane.WARNING_MESSAGE);
    	}
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		colPublisher_ID.setCellValueFactory(new PropertyValueFactory<>("Publisher_ID"));
		colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
		pList.clear();
		try {
			getPublisher();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tblPublisher.setItems(pList);
	}
}
