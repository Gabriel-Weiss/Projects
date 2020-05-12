package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class MainController implements Initializable{

    @FXML
    private TextField tfID;

    @FXML
    private TextField tfTitle;

    @FXML
    private TextField tfAuthor;

    @FXML
    private TextField tfYear;

    @FXML
    private TextField tfPages;

    @FXML
    private TableView<Books> tvBooks;

    @FXML
    private TableColumn<Books, Integer> colid;

    @FXML
    private TableColumn<Books, String> coltitle;

    @FXML
    private TableColumn<Books, String> colauthor;

    @FXML
    private TableColumn<Books, Integer> colyear;

    @FXML
    private TableColumn<Books, Integer> colpages;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private void handleButtonAction(ActionEvent event) {
    	if(event.getSource()==btnInsert) insertRecord();
    	else if(event.getSource()==btnUpdate) updateRecord();
    	else if (event.getSource()==btnDelete) deleteRecords();
    }
    
    @FXML
    void handleMouseAction(MouseEvent event) {
    	Books book = tvBooks.getSelectionModel().getSelectedItem();
    	tfID.setText(book.getId()+"");
    	tfTitle.setText(book.getTitle());
    	tfAuthor.setText(book.getAuthor());
    	tfYear.setText(book.getYear()+"");
    	tfPages.setText(book.getPages()+"");
    }

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		showBooks();
	}
    
    public Connection getConnection() {
    	
    	String database = "jdbc:sqlite:database.db";
    	try {
    		Connection conn=DriverManager.getConnection(database);
			return conn;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
    }
    
    public ObservableList<Books> getBooksList(){
    	ObservableList<Books> bookList = FXCollections.observableArrayList();
    	Connection conn = getConnection();
    	String query = "select * from books";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st= conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				Books books = new Books(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("year"), rs.getInt("pages"));
				bookList.add(books);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
    	
		return bookList;
    	
    }
    
    public void showBooks() {
    	ObservableList<Books> list = getBooksList();
    	
    	colid.setCellValueFactory(new PropertyValueFactory<Books, Integer>("id"));
    	coltitle.setCellValueFactory(new PropertyValueFactory<Books, String>("title"));
    	colauthor.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
    	colyear.setCellValueFactory(new PropertyValueFactory<Books, Integer>("year"));
    	colpages.setCellValueFactory(new PropertyValueFactory<Books, Integer>("pages"));
    	
    	tvBooks.setItems(list);
    	
    }
    
    public void insertRecord() {
    	String query = "insert into books values("+tfID.getText()+",'"
                                                  +tfTitle.getText()+"','"
    			                                  +tfAuthor.getText()+"',"
                                                  +tfYear.getText()+","
    			                                  +tfPages.getText()+")";
    	executeQuery(query);
    	showBooks();
    	
    }
    
    public void updateRecord() {
    	String query = "update books set title=' "+tfTitle.getText()+
    			                    "', author=' "+tfAuthor.getText()+
    			                       "', year= "+tfYear.getText()+
    			                       ", pages= "+tfPages.getText()+
    			                     " where id= "+tfID.getText()+"";
    	executeQuery(query);
    	showBooks();
    }
    
    public void deleteRecords() {
    	String query = "delete from books where id= "+tfID.getText()+"";
    	executeQuery(query);
    	showBooks();
    }

	private void executeQuery(String query) {
		Connection conn = getConnection();
		Statement st;
		try {
			st=conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
