package TBEStructure.DatabaseAccess;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.scene.control.SelectionModel;
import TBEStructure.helper.DBconnection;
import TBEStructure.model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class BookDA {

    private Connection con;

    public class BookTable {
        public SimpleIntegerProperty BookId = new SimpleIntegerProperty();
        public SimpleIntegerProperty Price = new SimpleIntegerProperty();
        public SimpleIntegerProperty SellerId = new SimpleIntegerProperty();
        public SimpleStringProperty ISBN = new SimpleStringProperty();
        public SimpleStringProperty Title = new SimpleStringProperty();
        public SimpleStringProperty Author = new SimpleStringProperty();
        public SimpleIntegerProperty Edition = new SimpleIntegerProperty();
        public SimpleStringProperty ModuleCode = new SimpleStringProperty();
        public SimpleStringProperty Category = new SimpleStringProperty();
        public SimpleIntegerProperty SellerPrice = new SimpleIntegerProperty();
        public SimpleIntegerProperty SalePrice = new SimpleIntegerProperty();

        public int getBookId() {
            return BookId.get();
        }
        public int geSellerId() {
            return BookId.get();
        }
        public String getISBN() {
            return ISBN.get();
        }
        public String getTitle() {
            return Title.get();
        }
        public String getAuthor() {
            return Author.get();
        }
        public int getEdition() {
            return Edition.get();
        }
        public String getModuleCode() {
            return ModuleCode.get();
        }
        public String getCategory() {
            return Category.get();
        }
        public int getPrice() {
            return Price.get();
        }
        public int getSellerprice() {
            return SellerPrice.get();
        }
        public int getSalePrice() {
            return SalePrice.get();
        }
        public static SelectionModel<BookTable> getSelectionModel() {
            return null;
        }

    }

    private ObservableList<BookTable> AllBookList;

    public void deleteProduct(Book book) {

        try {
            con = new DBconnection().getNewConnection();
            String query = "delete from Book where BookId=?;";

            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, book.getBookId());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.close();

            con.close();
            con = null;

        } catch (Exception e) {
            System.out.println("Error closing con: " + e);
        }

    }

    public ObservableList<BookTable> getAllBooks(String query) {
        AllBookList = FXCollections.observableArrayList();
        try {

            con = new DBconnection().getNewConnection();
            ResultSet rs = con.createStatement().executeQuery(query);

            while (rs.next()) {
                BookTable BookTable = new BookTable();
                BookTable.BookId.set(rs.getInt("BookId"));
                BookTable.ISBN.set(rs.getString("ISBN"));
                BookTable.Title.set(rs.getString("Title"));
                BookTable.Author.set(rs.getString("Author"));
                BookTable.Edition.set(rs.getInt("Edition"));
                BookTable.ModuleCode.set(rs.getString("ModuleCode"));
                BookTable.Category.set(rs.getString("Category"));
                BookTable.Price.set(rs.getInt("SalePrice"));
                BookTable.SellerPrice.set(rs.getInt("SellerPrice"));

                AllBookList.add(BookTable);
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data :" + e.getMessage());
        }
        return AllBookList;
    }
}
