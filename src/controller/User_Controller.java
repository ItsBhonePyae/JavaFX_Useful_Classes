package controller;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Security.PasswordUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Member;
import model.Users;

public class User_Controller implements Initializable{

    @FXML
    private ToggleGroup Gender;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpload;
    
    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private ComboBox<String> cbRole;

    @FXML
    private TableColumn<Users,String> colAddress;

    @FXML
    private TableColumn<Users, LocalDate> colDob;

    @FXML
    private TableColumn<Users,String> colEmail;

    @FXML
    private TableColumn<Users,String> colGender;

    @FXML
    private TableColumn<Users,String> colImage;

    @FXML
    private TableColumn<Users,LocalDate> colJoin_Date;

    @FXML
    private TableColumn<Users,String> colName;

    @FXML
    private TableColumn<Users,String> colPassword;

    @FXML
    private TableColumn<Users,String> colPhone;

    @FXML
    private TableColumn<Users,String> colRole;

    @FXML
    private TableColumn<Users,Integer> colUser_ID;

    @FXML
    private DatePicker dpDob;

    @FXML
    private DatePicker dpJoin;

    @FXML
    private ImageView image;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblDob;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblGender;

    @FXML
    private Label lblJoin;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPhone;

    @FXML
    private Label lblRole;

    @FXML
    private Label lblSearch;

    @FXML
    private AnchorPane pane;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private RadioButton rbMale;

    @FXML
    private RadioButton rbOther;

    @FXML
    private TextArea taAddress;

    @FXML
    private TableView<Users> tblUser;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfName;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfPhone;

    @FXML
    private TextField tfSearch;
    @FXML
    private Label lblTitle;
    @FXML 
    private Label lblConfirmPw;
    
    @FXML
    private PasswordField pfConfirmPw;

    Datebase_Connection db = new Datebase_Connection();
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    int result;
    ObservableList<Users> uList=FXCollections.observableArrayList();
    Users u;
    
    @FXML
    void clickClear(ActionEvent event) {

    	
    	btnRegister.setText("Register");
    	tfName.clear();
    	pfPassword.clear();
    	taAddress.clear();
    	tfEmail.clear();
    	dpDob.setValue(null);
    	rbMale.setSelected(false);
    	rbFemale.setSelected(false);
    	rbOther.setSelected(false);
    	tfPhone.clear();
    	cbRole.setValue(null);
    	dpJoin.setValue(null);
    	image.setImage(null);
    	
    }

    public ObservableList<Users> getUser(String sn) throws ClassNotFoundException, SQLException
    {
    	uList.clear();
    	con=db.getDB();
    	pstmt=con.prepareStatement("SELECT * FROM Users WHERE Name LIKE ?");
    	pstmt.setString(1,"%"+ sn+"%");
    	rs=pstmt.executeQuery();
    	while(rs.next())
    	{
    		int id=rs.getInt(1);
    		String n=rs.getString(2);
    		String pwd=rs.getString(3);
    		String e=rs.getString(4);
    		LocalDate birth=rs.getDate(5).toLocalDate();
    		String g=rs.getString(6);
    		String p=rs.getString(7);
    		String r=rs.getString(8);
    		String a=rs.getString(9);
    		LocalDate join=rs.getDate(10).toLocalDate();
    		String img=rs.getString(11);
    		
    		u=new Users(id,n,pwd,e,birth,g,p,r,a,join,img);
    		uList.add(u);
    	}tblUser.setItems(uList);
    	
		return uList;
    }
    @FXML
    void clickEnter(ActionEvent event) throws ClassNotFoundException, SQLException {

    	String search=tfSearch.getText().trim();
    	uList.clear();
    	uList=getUser(search);
    	tblUser.setItems(uList);
    }

    @FXML
    void clickRegister(ActionEvent event) throws ClassNotFoundException, SQLException {
    	
    	uList.clear();
    	con=db.getDB();
    	
    	if(btnRegister.getText().equals("Register"))
    	{
    		String name=tfName.getText();
        	String pwd=pfPassword.getText();
        	LocalDate dob=dpDob.getValue();
        	String email=tfEmail.getText();
        	String ph=tfPhone.getText();
        	String address=taAddress.getText();
        	LocalDate join=dpJoin.getValue();
        	String role=cbRole.getSelectionModel().getSelectedItem();
        	String Img=image.getImage().getUrl();
        	
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
        	
        	String hash_pw=PasswordUtil.hashPassword(pwd);
        	
        	if(name.isEmpty()||pwd.isEmpty()||dob.equals(null)||email.isEmpty()||ph.isEmpty()||address.isEmpty()||join.equals(null)||role.isEmpty()||Img.isEmpty()||gender.isEmpty())
        	{
        		JOptionPane.showMessageDialog(null, "Fill All The Require NIGGA!!","Input Warning!!",JOptionPane.WARNING_MESSAGE);return;
        	}
        	if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                JOptionPane.showMessageDialog(null,"Email Invalid Idiots!","Input Warning",JOptionPane.WARNING_MESSAGE);return;
            }
        	if(!ph.matches("^\\d+$"))
        	{
        		JOptionPane.showMessageDialog(null, "Only Input Number Idiots!!","Input Warning!",JOptionPane.WARNING_MESSAGE);return;
        	}

        	
        	if(pfConfirmPw.getText().equals(pwd))
        	{
        	pstmt=con.prepareStatement("INSERT INTO `users`(`Name`, `Password`, `Email`, `DateOfBirth`, `Gender`, `Phone_No`, `Role`, `Address`, `Join_Date`, `Image`) VALUES (?,?,?,?,?,?,?,?,?,?)");
        	pstmt.setString(1, name);
        	pstmt.setString(2, hash_pw);
        	pstmt.setString(3, email);
        	pstmt.setDate(4, Date.valueOf(dob));
        	pstmt.setString(5, gender);
        	pstmt.setString(6, ph);
        	pstmt.setString(7, role);
        	pstmt.setString(8, address);
        	pstmt.setDate(9, Date.valueOf(join));
        	pstmt.setString(10, Img);
        	
        	result=pstmt.executeUpdate();
        	if(result>0)
    		{
    			JOptionPane.showMessageDialog(null, "Insert User Record Success!");
    		}
        	getUser();
        	tblUser.setItems(uList);
        	}else
        	{
        		JOptionPane.showMessageDialog(null, "Password And Confirm Password Should be Same NIGGA!","Password Valation!",JOptionPane.WARNING_MESSAGE);
        	}
    	}else if(btnRegister.getText().equals("Edit"))
    	{
    		String name=tfName.getText();
        	String pwd=pfPassword.getText();
        	LocalDate dob=dpDob.getValue();
        	String email=tfEmail.getText();
        	String ph=tfPhone.getText();
        	String address=taAddress.getText();
        	LocalDate join=dpJoin.getValue();
        	String role=cbRole.getSelectionModel().getSelectedItem();
        	String Img=image.getImage().getUrl();
        	
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
        	
        	String hash_pw=PasswordUtil.hashPassword(pwd);
        	
        	pstmt=con.prepareStatement("UPDATE `users` SET `Name`=?,`Password`=?,`Email`=?,`DateOfBirth`=?,`Gender`=?,`Phone_No`=?,`Role`=?,`Address`=?,`Join_Date`=?,`Image`=? WHERE `User_ID` = ?");
        	
        	pstmt.setString(1, name);
        	pstmt.setString(2, hash_pw);
        	pstmt.setString(3, email);
        	pstmt.setDate(4, Date.valueOf(dob));
        	pstmt.setString(5, gender);
        	pstmt.setString(6, ph);
        	pstmt.setString(7, role);
        	pstmt.setString(8, address);
        	pstmt.setDate(9, Date.valueOf(join));
        	pstmt.setString(10, Img);
        	pstmt.setInt(11, u.getUser_ID());
        	
        	result = pstmt.executeUpdate();

    		if (result > 0) {
    		    JOptionPane.showMessageDialog(null, "Update Member Successfully!");
    		}
    		
    	
    	uList.clear();
    	uList = getUser();
    	tblUser.setItems(uList);
    	}
    }
    public ObservableList<Users> getUser() throws ClassNotFoundException, SQLException
    {
    	uList.clear();
    	con=db.getDB();
    	pstmt=con.prepareStatement("SELECT * FROM Users");
    	rs=pstmt.executeQuery();
    	
    	while(rs.next())
    	{
    		int id=rs.getInt(1);
    		String n=rs.getString(2);
    		String pwd=rs.getString(3);
    		String e=rs.getString(4);
    		LocalDate birth=rs.getDate(5).toLocalDate();
    		String g=rs.getString(6);
    		String p=rs.getString(7);
    		String r=rs.getString(8);
    		String a=rs.getString(9);
    		LocalDate join=rs.getDate(10).toLocalDate();
    		String img=rs.getString(11);
    		
    		
    		u=new Users(id,n,pwd,e,birth,g,p,r,a,join,img);
    		uList.add(u);
    	}
    	
		return uList;
    }
    

    @FXML
    void clickSearch(ActionEvent event) throws SQLException, ClassNotFoundException {

    	con=db.getDB();
    	uList.clear();
    	String name=tfSearch.getText().trim();
    	
    	pstmt=con.prepareStatement("SELECT * FROM Users WHERE Name LIKE ?");
    	pstmt.setString(1,"%"+ name+"%");
    	rs=pstmt.executeQuery();
    	while(rs.next())
    	{
    		int id=rs.getInt(1);
    		String n=rs.getString(2);
    		String pwd=rs.getString(3);
    		String e=rs.getString(4);
    		LocalDate birth=rs.getDate(5).toLocalDate();
    		String g=rs.getString(6);
    		String p=rs.getString(7);
    		String r=rs.getString(8);
    		String a=rs.getString(9);
    		LocalDate join=rs.getDate(10).toLocalDate();
    		String img=rs.getString(11);
    		
    		u=new Users(id,n,pwd,e,birth,g,p,r,a,join,img);
    		uList.add(u);
    	}tblUser.setItems(uList);
    }
    
    public void clickUpload(ActionEvent event)
	{
		FileChooser c=new FileChooser();
		c.setTitle("Choose Image");
		c.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg","*.jfif"));
		Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
		
		File file = c.showOpenDialog(stage);
		if (file != null) 
		{
			Image img;
			try {
				img = new Image(file.toURI().toString());
				image.setImage(img);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}

    public void clickUpdate(ActionEvent event) throws ClassNotFoundException, SQLException
    {
    	con=db.getDB();
    	image.setImage(null);
    	btnRegister.setText("Edit");
    	u=tblUser.getSelectionModel().getSelectedItem();
    	
    	if(u!=null)
    	{
    		tfName.setText(u.getName());
    		//pfPassword.clear();
    		//pfPassword.setPromptText("Password is currently Hashing!");
    		pfPassword.setText(u.getPassword());
    		dpDob.setValue(u.getDateOfBirth());
    		tfEmail.setText(u.getEmail());
    		tfPhone.setText(u.getPhone_No());
    		taAddress.setText(u.getAddress());
    		cbRole.setValue(u.getRole());
    		dpJoin.setValue(u.getJoined_Date());
    		
    		if (u.getGender().equals("Male")) 
    			rbMale.setSelected(true);
    		
    		else if (u.getGender().equals("Female")) 
    			rbFemale.setSelected(true);
    		else if(u.getGender().equals("Gay"))
    		{
    			rbOther.setSelected(true);
    		}
    		image.setImage(new Image(u.getImage()));
    	}else
    	{
    		JOptionPane.showMessageDialog(null, "Click User NIGGA!","Null",JOptionPane.WARNING_MESSAGE);
    	}
    }
    public void clickDelete(ActionEvent event) throws ClassNotFoundException, SQLException
    {
    	con=db.getDB();
    	Users data=tblUser.getSelectionModel().getSelectedItem();
    	
    	if(data!=null)
    	{
    		int value=JOptionPane.showConfirmDialog(null, "Are You Sure To Delete "+data.getName()+"?");
    		if(value==0)
    		{
    			pstmt=con.prepareStatement("DELETE FROM users WHERE User_ID=?");
				pstmt.setInt(1, data.getUser_ID());
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
    	
    	uList.clear();
    	uList.remove(data);
    	uList = getUser();
    	tblUser.setItems(uList);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		dpJoin.setValue(LocalDate.now());

    	colUser_ID.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
		colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
		colPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
		colDob.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
		colPhone.setCellValueFactory(new PropertyValueFactory<>("Phone_No"));
		colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
		colRole.setCellValueFactory(new PropertyValueFactory<>("Role"));
		colJoin_Date.setCellValueFactory(new PropertyValueFactory<>("Joined_Date"));
		colImage.setCellValueFactory(new PropertyValueFactory<>("Image"));
		colGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
		
		
		 ObservableList roleList=FXCollections.observableArrayList();
			roleList.add("Admin");
			roleList.add("Librarian");
			cbRole.setItems(roleList);
		uList.clear();
		try {
			getUser();
			tblUser.setItems(uList);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
