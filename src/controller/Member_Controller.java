package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Member;

public class Member_Controller implements Initializable{

    @FXML
    private Button btnClear;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<Member, String> colAddress;

    @FXML
    private TableColumn<Member, Integer> colBook_Count;

    @FXML
    private TableColumn<Member,LocalDate> colDob;

    @FXML
    private TableColumn<Member, String> colEmail;

    @FXML
    private TableColumn<Member, LocalDate> colExpried_Date;

    @FXML
    private TableColumn<Member, String> colGender;

    @FXML
    private TableColumn<Member, Integer> colMember_ID;

    @FXML
    private TableColumn<Member, Integer> colNRC_No;

    @FXML
    private TableColumn<Member, String> colName;

    @FXML
    private TableColumn<Member, String> colPhone_No;

    @FXML
    private TableColumn<Member, LocalDate> colRegister_Date;

    @FXML
    private DatePicker dpDob;

    @FXML
    private DatePicker dpExpried;

    @FXML
    private DatePicker dpRegister;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblDob;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblExpried;

    @FXML
    private Label lblGender;

    @FXML
    private Label lblName;

    @FXML
    private Label lblNrc;

    @FXML
    private Label lblPhone;

    @FXML
    private Label lblRegister;

    @FXML
    private Label lblSearch;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private RadioButton rbMale;

    @FXML
    private RadioButton rbOther;

    @FXML
    private TextArea taAddress;

    @FXML
    private TableView<Member> tblMember;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfNrc;

    @FXML
    private TextField tfPhone;

    @FXML
    private TextField tfSearch;
    
    Datebase_Connection db = new Datebase_Connection();
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    int result;
    ObservableList<Member> mList=FXCollections.observableArrayList();
    Member m;

    @FXML
    void clickClear(ActionEvent event) {

    	tfName.clear();
    	tfNrc.clear();
    	dpDob.setValue(null);
    	tfEmail.clear();
    	tfPhone.clear();
    	taAddress.clear();
    	dpRegister.setValue(null);
    	dpExpried.setValue(null);
    	rbMale.setSelected(false);
    	rbFemale.setSelected(false);
    	rbOther.setSelected(false);
    }

    @FXML
    void clickRegister(ActionEvent event) throws ClassNotFoundException, SQLException {

    	mList.clear();
    	con=db.getDB();
    	
    	String name=tfName.getText();
    	String nrc=tfNrc.getText();
    	LocalDate dob=dpDob.getValue();
    	String email=tfEmail.getText();
    	String ph=tfPhone.getText();
    	String address=taAddress.getText();
    	LocalDate reg=dpRegister.getValue();
    	LocalDate exp=dpExpried.getValue();
    	
    	String gender="";
    	if(rbMale.isSelected())
    	{
    		gender="Male";
    	}else if(rbFemale.isSelected())
    	{
    		gender="Female";
    	}else if(rbOther.isSelected())
    	{
    		gender="Gay";
    	}
    	
    	pstmt=con.prepareStatement("INSERT INTO `member`( `Name`, `NRC_No`, `DateOfBirth`, `Email`, `Phone_No`, `Address`, `Register_Date`, `Expried_Date`, `Gender`, `Book_Count`) VALUES (?,?,?,?,?,?,?,?,?,?)");
    	pstmt.setString(1, name);
    	pstmt.setString(2, nrc);
    	pstmt.setDate(3, Date.valueOf(dob));
    	pstmt.setString(4, email);
    	pstmt.setString(5, ph);
    	pstmt.setString(6, address);
    	pstmt.setDate(7, Date.valueOf(reg));
    	pstmt.setDate(8, Date.valueOf(exp));
    	pstmt.setString(9, gender);
    	pstmt.setInt(10, 0);
    	result=pstmt.executeUpdate();
    	if(result>0)
		{
			JOptionPane.showMessageDialog(null, "Insert Member Record Success!");
		}
    	getMember();
    	tblMember.setItems(mList);
    }
    public ObservableList<Member> getMember() throws ClassNotFoundException, SQLException
    {
    	mList.clear();
    	con=db.getDB();
    	pstmt=con.prepareStatement("SELECT * FROM Member");
    	rs=pstmt.executeQuery();
    	
    	while(rs.next())
    	{
    		int id=rs.getInt(1);
    		String n=rs.getString(2);
    		String nrc=rs.getString(3);
    		LocalDate birth=rs.getDate(4).toLocalDate();
    		String e=rs.getString(5);
    		String p=rs.getString(6);
    		String a=rs.getString(7);
    		LocalDate r=rs.getDate(8).toLocalDate();
    		LocalDate ex=rs.getDate(9).toLocalDate();
    		int bc=rs.getInt(10);
    		String g=rs.getString(11);
    		
    		m=new Member(id,n,nrc,birth,e,p,a,r,ex,bc,g);
    		mList.add(m);
    	}
    	
		return mList;
    }
    
    
    public void clickSearch(ActionEvent event) throws ClassNotFoundException, SQLException
    {
    	con=db.getDB();
    	mList.clear();
    	String name=tfSearch.getText().trim();
    	
    	pstmt=con.prepareStatement("SELECT * FROM Member WHERE Name LIKE ?");
    	pstmt.setString(1,"%"+ name+"%");
    	rs=pstmt.executeQuery();
    	while(rs.next())
    	{
    		int id=rs.getInt(1);
    		String n=rs.getString(2);
    		String nrc=rs.getString(3);
    		LocalDate birth=rs.getDate(4).toLocalDate();
    		String e=rs.getString(5);
    		String p=rs.getString(6);
    		String a=rs.getString(7);
    		LocalDate r=rs.getDate(8).toLocalDate();
    		LocalDate ex=rs.getDate(9).toLocalDate();
    		int bc=rs.getInt(10);
    		String g=rs.getString(11);
    		
    		Member m1=new Member(id,n,nrc,birth,e,p,a,r,ex,bc,g);
    		mList.add(m1);
    	}
    	tblMember.setItems(mList);
    }

    public ObservableList<Member> getMember(String sn) throws ClassNotFoundException, SQLException
    {
    	mList.clear();
    	con=db.getDB();
    	pstmt=con.prepareStatement("SELECT * FROM Member WHERE Name LIKE ?");
    	pstmt.setString(1,"%"+ sn+"%");
    	rs=pstmt.executeQuery();
    	while(rs.next())
    	{
    		int id=rs.getInt(1);
    		String n=rs.getString(2);
    		String nrc=rs.getString(3);
    		LocalDate birth=rs.getDate(4).toLocalDate();
    		String e=rs.getString(5);
    		String p=rs.getString(6);
    		String a=rs.getString(7);
    		LocalDate r=rs.getDate(8).toLocalDate();
    		LocalDate ex=rs.getDate(9).toLocalDate();
    		int bc=rs.getInt(10);
    		String g=rs.getString(11);
    		
    		Member m1=new Member(id,n,nrc,birth,e,p,a,r,ex,bc,g);
    		mList.add(m1);
    	}
    	tblMember.setItems(mList);
    	
		return mList;
    }
    
    public void clickEnter(ActionEvent event) throws ClassNotFoundException, SQLException
    {
    	String search=tfSearch.getText().trim();
    	mList.clear();
    	mList=getMember(search);
    	tblMember.setItems(mList);
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		dpRegister.setValue(LocalDate.now());
		dpExpried.setValue(LocalDate.now().plusYears(1));
		
		colMember_ID.setCellValueFactory(new PropertyValueFactory<>("Member_ID"));
		colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
		colNRC_No.setCellValueFactory(new PropertyValueFactory<>("NRC_No"));
		colDob.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
		colPhone_No.setCellValueFactory(new PropertyValueFactory<>("Phone_No"));
		colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
		colRegister_Date.setCellValueFactory(new PropertyValueFactory<>("Register_Date"));
		colExpried_Date.setCellValueFactory(new PropertyValueFactory<>("Expried_Date"));
		colBook_Count.setCellValueFactory(new PropertyValueFactory<>("Book_Count"));
		colGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
		
		try {
			mList.clear();
			getMember();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	tblMember.setItems(mList);
	}

}
