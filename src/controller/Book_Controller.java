package controller;

import java.net.URL;
import javafx.application.Platform;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Book;

public class Book_Controller implements Initializable{

    @FXML
    private AnchorPane Book_Pane;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpload;

    @FXML
    private ComboBox<String> cbAuthor;

    @FXML
    private ComboBox<String> cbCategory;

    @FXML
    private ComboBox<String> cbPublisher;

    @FXML
    private ImageView imageView;

    @FXML
    private Button btnRefresh;
    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblCategory;

    @FXML
    private Label lblCopies;

    @FXML
    private Label lblDescription;
    @FXML
    private Label lblArrivalCopies;
    
    @FXML
    private TextField tfArrivalCopies;

    @FXML
    private Label lblISBN;

    @FXML
    private Label lblPublisher;

    @FXML
    private Label lblPublishion_Year;

    @FXML
    private Label lblTitle;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextArea taDescription;

    @FXML
    private TableView<Book> tblBook;

    @FXML
    private TextField tfCopies;

    @FXML
    private TextField tfISBN;

    @FXML
    private TextField tfPublishion_Year;

    @FXML
    private TextField tfTitle;
    @FXML
    private TableColumn<Book,Integer> colCopies;

    @FXML
    private TableColumn<Book,String> colDescription;

    @FXML
    private TableColumn<Book,Integer> colID;

    @FXML
    private TableColumn<Book,String> colISBN;

    @FXML
    private TableColumn<Book,String> colImage;

    @FXML
    private TableColumn<Book,Integer> colPublish;

    @FXML
    private TableColumn<Book,String> colTitle;

    @FXML
    private ComboBox<String> cbSelectAuthor;

    @FXML
    private ComboBox<String> cbSelectCategory;

    @FXML
    private ComboBox<String> cbSelectPublisher;

    @FXML
    private Label lblByAuthor;

    @FXML
    private Label lblByCategory;

    @FXML
    private Label lblByPublisher;
    
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    
    Datebase_Connection db = new Datebase_Connection();
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    int result;
    ObservableList<String> aList=FXCollections.observableArrayList();
    ObservableList<String> cList=FXCollections.observableArrayList();
    ObservableList<String> pList=FXCollections.observableArrayList();
    ObservableList<Book> bList=FXCollections.observableArrayList();
    Book b;

    @FXML
    void clickClear(ActionEvent event) {

    	tfTitle.clear();
    	tfISBN.clear();
    	cbAuthor.setValue(null);
    	tfPublishion_Year.clear();
    	cbCategory.setValue(null);
    	taDescription.clear();
    	tfCopies.clear();
    	cbPublisher.setValue(null);
    	
    }

    @FXML
    void clickSave(ActionEvent event) throws ClassNotFoundException, SQLException {

    	bList.clear();
    	Button btn=(Button)event.getSource();
    	con=db.getDB();
    	if(btn.getText().equals("Save"))
    	{
    	
    	String t=tfTitle.getText();
    	String isbn=tfISBN.getText();
    	String a=cbAuthor.getSelectionModel().getSelectedItem();
    	int y=Integer.parseInt(tfPublishion_Year.getText());
    	String c=cbCategory.getSelectionModel().getSelectedItem();
    	String d=taDescription.getText();
    	int copies=Integer.parseInt(tfCopies.getText());
    	String p=cbPublisher.getSelectionModel().getSelectedItem();
    	String img="eg.png";
    	int a_id = 0,c_id=0,p_id=0;
    	
    	con=db.getDB();
    	pstmt=con.prepareStatement("SELECT Author_ID FROM author WHERE Name=?");
    	pstmt.setString(1, a);
    	rs=pstmt.executeQuery();
    	if(rs.next())
    	{
    		a_id=rs.getInt(1);
    	}
    	
    	pstmt=con.prepareStatement("SELECT Category_ID FROM category WHERE Name=?");
    	pstmt.setString(1, c);
    	rs=pstmt.executeQuery();
    	if(rs.next())
    	{
    		c_id=rs.getInt(1);
    	}
    	
    	pstmt=con.prepareStatement("SELECT Publisher_ID FROM publisher WHERE Name=?");
    	pstmt.setString(1, p);
    	rs=pstmt.executeQuery();
    	if(rs.next())
    	{
    		p_id=rs.getInt(1);
    	}
    	
    	pstmt=con.prepareStatement("INSERT INTO Book(Title,Author_ID,Published_Year,Category_ID,Description,Copies,Publisher_ID,Image,ISBN) VALUES (?,?,?,?,?,?,?,?,?)");
    	
    	pstmt.setString(1, t);
    	pstmt.setInt(2, a_id);
    	pstmt.setInt(3, y);
    	pstmt.setInt(4, c_id);
    	pstmt.setString(5, d);
    	pstmt.setInt(6,copies);
    	pstmt.setInt(7, p_id);
    	pstmt.setString(8, img);
    	pstmt.setString(9, isbn);
    	
    	
    	result=pstmt.executeUpdate();
    	if(result>0)
    	{
    		JOptionPane.showMessageDialog(null, "Insert Success!");
    	}
    	pstmt=con.prepareStatement("SELECT MAX(Book_ID) FROM Book");
    	rs=pstmt.executeQuery();
    	int id=0;
    	if(rs.next())
    	{
    		id=rs.getInt(1);
    	}    	
    	pstmt=con.prepareStatement("INSERT INTO Book_Copies(Book_ID,Copies) VALUES (?,?)");
    	pstmt.setInt(1,id);
    	pstmt.setInt(2, copies);
    	pstmt.executeUpdate();
    	
    	bList=getBook(null,null,null);
    	tblBook.setItems(bList);
    	}else if(btn.getText().equals("Edit"))
    	{
    		int a_copies=Integer.parseInt(tfArrivalCopies.getText());
    		int c=Integer.parseInt(tfCopies.getText());
    		
    		pstmt=con.prepareStatement("UPDATE book SET Copies=? WHERE Book_ID =?");
    		pstmt.setInt(1, (a_copies+c));
    		pstmt.setInt(2,b.getBook_ID() );
    		pstmt.executeUpdate();
    		
    		pstmt=con.prepareStatement("UPDATE Book_Copies SET Copies=? WHERE Book_ID =?");
    		pstmt.setInt(1, (a_copies+c));
    		pstmt.setInt(2,b.getBook_ID() );
    		pstmt.executeUpdate();
    		bList.clear();
    		bList=getBook(null,null,null);
        	tblBook.setItems(bList);
    	}
    }
    @FXML
    void clickUploadPhoto(ActionEvent event) {

    }
    @FXML
    void clickSelect(ActionEvent event) throws ClassNotFoundException, SQLException
    {
        bList = getBook(
            cbSelectAuthor.getValue(),
            cbSelectCategory.getValue(),
            cbSelectPublisher.getValue()
        );

        tblBook.setItems(bList);
    }

    public ObservableList<Book> getBook(String author, String category, String publisher)throws SQLException, ClassNotFoundException
    {
        ObservableList<Book> list = FXCollections.observableArrayList();
        con = db.getDB();

        pstmt = con.prepareStatement(
            "SELECT Book_ID, Title, ISBN, Copies, Description, Image, Published_Year,Author_ID,Category_ID,Publisher_ID FROM book "
            + " WHERE (? IS NULL OR Author_ID = (SELECT Author_ID FROM author WHERE Name = ?)) "
            + " AND (? IS NULL OR Category_ID = (SELECT Category_ID FROM category WHERE Name = ?)) "
            + " AND (? IS NULL OR Publisher_ID = (SELECT Publisher_ID FROM publisher WHERE Name = ?))");

        pstmt.setString(1, author);
        pstmt.setString(2, author);

        pstmt.setString(3, category);
        pstmt.setString(4, category);

        pstmt.setString(5, publisher);
        pstmt.setString(6, publisher);

        rs = pstmt.executeQuery();

        while (rs.next())
        {
            int bid = rs.getInt(1);
            String title = rs.getString(2);
            String isbn = rs.getString(3);
            int copies = rs.getInt(4);
            String description = rs.getString(5);
            String image = rs.getString(6);
            int year = rs.getInt(7);
            int a_id=rs.getInt(8);
            int c_id=rs.getInt(9);
            int p_id=rs.getInt(10);

            Book b = new Book(bid, title, isbn,a_id, year,c_id, description, copies,p_id, image);
            list.add(b);
        }

        rs.close();
        pstmt.close();
        con.close();

        return list;
    }

   
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		colID.setCellValueFactory(new PropertyValueFactory<>("Book_ID"));
	    colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
	    colISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
	    colCopies.setCellValueFactory(new PropertyValueFactory<>("Copies"));
	    colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
	    colImage.setCellValueFactory(new PropertyValueFactory<>("Image"));
	    colPublish.setCellValueFactory(new PropertyValueFactory<>("Published_Year"));
	    
	    
		try {
			con=db.getDB();
			pstmt=con.prepareStatement("SELECT Name FROM Author");
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				String name=rs.getString(1);
				aList.add(name);
			}
			cbAuthor.setItems(aList);
			cbSelectAuthor.setItems(aList);
			
			pstmt=con.prepareStatement("SELECT Name FROM Category");
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				String name=rs.getString(1);
				cList.add(name);
			}
			cbCategory.setItems(cList);
			cbSelectCategory.setItems(cList);
			
			pstmt=con.prepareStatement("SELECT Name FROM Publisher");
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				String name=rs.getString(1);
				pList.add(name);
			}
			cbPublisher.setItems(pList);
			cbSelectPublisher.setItems(pList);
//			con.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con=db.getDB();
			bList=getBook(null,null,null);
			tblBook.setItems(bList);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lblArrivalCopies.setDisable(true);
		tfArrivalCopies.setDisable(true);
			}

	public void clickRefresh() throws ClassNotFoundException, SQLException
	{
		bList=getBook(null,null,null);
    	tblBook.setItems(bList);
    	cbSelectPublisher.setValue(null);
    	cbSelectCategory.setValue(null);
    	cbSelectAuthor.setValue(null);
    	btnSave.setText("Save");
    	tfTitle.clear();
    	tfISBN.clear();
    	cbAuthor.setValue(null);
    	tfPublishion_Year.clear();
    	cbCategory.setValue(null);
    	taDescription.clear();
    	tfCopies.clear();
    	cbPublisher.setValue(null);
    	
	}
	public void clickUpdate(ActionEvent event) throws SQLException, ClassNotFoundException
	{
		con=db.getDB();
		String name="";
		lblArrivalCopies.setDisable(false);
		tfArrivalCopies.setDisable(false);
		
		btnSave.setText("Edit");		
		
		b=tblBook.getSelectionModel().getSelectedItem();
		if(b!=null)
		{
			tfTitle.setText(b.getTitle());
			tfISBN.setText(b.getISBN());
			tfCopies.setText(String.valueOf(b.getCopies()));
			tfPublishion_Year.setText(String.valueOf(b.getPublished_Year()));
			taDescription.setText(b.getDescription());
			
			
			/*pstmt=con.prepareStatement("SELECT Name FROM Author WHERE Author_ID=? ");
			pstmt.setInt(1, b.getAuthor_ID());*/
			pstmt=con.prepareStatement("SELECT Name FROM Author JOIN book ON Author.Author_ID = book.Author_ID");
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				name=rs.getString(1);
			}
			cbAuthor.setValue(name);
			
			/*pstmt=con.prepareStatement("SELECT Name FROM Category WHERE Category_ID=?");
			pstmt.setInt(1, b.getCategory_ID());*/
			pstmt=con.prepareStatement("SELECT Name FROM Category JOIN book ON Category.Category_ID=book.Category_ID");
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				name=rs.getString(1);
			}
			cbCategory.setValue(name);
			
			/*pstmt=con.prepareStatement("SELECT Name FROM Publisher WHERE Publisher_ID=?");
			pstmt.setInt(1, b.getPublisher_ID());*/
			
			pstmt=con.prepareStatement("SELECT Name FROM Publisher JOIN book ON Publisher.Publisher_ID=book.Publisher_ID");
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				name=rs.getString(1);
			}
			cbPublisher.setValue(name);
		}else
		{
			JOptionPane.showMessageDialog(null,"Please Select A Book!!","Input Warning!",JOptionPane.WARNING_MESSAGE);
		}
	}
	public void clickDelete(ActionEvent event) throws ClassNotFoundException, SQLException
	{
		con=db.getDB();
		Book data=tblBook.getSelectionModel().getSelectedItem();
		
		if(data!=null)
		{
			
			int value=JOptionPane.showConfirmDialog(null, "Are You Sure To Delete "+data.getTitle()+"?");
			if(value==0)
			{
				pstmt=con.prepareStatement("DELETE FROM book_copies WHERE Book_ID=?");
				pstmt.setInt(1, data.getBook_ID());
				result=pstmt.executeUpdate();
				
				pstmt=con.prepareStatement("DELETE FROM book WHERE Book_ID=?");
				pstmt.setInt(1, data.getBook_ID());
				result=pstmt.executeUpdate();
				if(result>0)
				{
					JOptionPane.showMessageDialog(null,data.getBook_ID()+ "'s Delete Data Succesful!");
				}
			}
		}else 
		{
			JOptionPane.showMessageDialog(null, "The Data is Null Nigga!!","Input Warning",JOptionPane.WARNING_MESSAGE);
		}
		bList.remove(data);
		bList.clear();
		getBook(null,null,null);
		tblBook.setItems(bList);
	}
}
