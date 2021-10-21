package TBEStructure.view;

import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import TBEStructure.DatabaseAccess.BookDA.BookTable;
import TBEStructure.DatabaseAccess.BookDA;
import TBEStructure.DatabaseAccess.CartDA;

import TBEStructure.model.*;
import TBEStructure.model.User;


public class ShopController implements Initializable {

    ObservableList<BookTable> data;
  
    private User user;
    private Stage dialogStage;

    @FXML
    private TableView<BookTable> TableBooks;

    @FXML
    private TableColumn<BookTable, Integer> col_edition;
    @FXML
    private TableColumn<BookTable, String> col_isbn;
    @FXML
    private TableColumn<BookTable, String> col_module;
    @FXML
    private TableColumn<BookTable, String> col_author;
    @FXML
    private TableColumn<BookTable, String> col_title;
    @FXML
    private TableColumn<BookTable, Integer> col_price;

    @FXML
    private Button btn_AddToCart;
    @FXML
    private Button btn_Logout;
    @FXML
    private Button btn_ViewCart;
    @FXML
    private TextField txt_search;
    @FXML
    private Label sessionLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        txt_search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                UpdateTable("SELECT * from book WHERE ISBN LIKE '%" + newValue + "%'" + " OR Title LIKE '%" + newValue
                        + "%'" + " OR ModuleCode LIKE '%" + newValue + "%';");
            }
        });

        UpdateTable("Select * from book;");

    }

    public void UpdateTable(String text) {

        col_edition.setCellValueFactory(new PropertyValueFactory<BookTable, Integer>("Edition"));
        col_isbn.setCellValueFactory(new PropertyValueFactory<BookTable, String>("ISBN"));
        col_module.setCellValueFactory(new PropertyValueFactory<BookTable, String>("ModuleCode"));
        col_price.setCellValueFactory(new PropertyValueFactory<BookTable, Integer>("Price"));
        col_author.setCellValueFactory(new PropertyValueFactory<BookTable, String>("Author"));
        col_title.setCellValueFactory(new PropertyValueFactory<BookTable, String>("Title"));
        data = new BookDA().getAllBooks(text);
        TableBooks.setItems(data);

    }

    public void AddToCart() {
        if (TableBooks.getSelectionModel().getSelectedIndex() != -1) {

            Book book = new Book();
            book.setBookId((data.get(TableBooks.getSelectionModel().getSelectedIndex()).getBookId()));
            book.setTitle((data.get(TableBooks.getSelectionModel().getSelectedIndex()).getTitle()));
            book.setIsbn((data.get(TableBooks.getSelectionModel().getSelectedIndex()).getISBN()));
            book.setAuthor((data.get(TableBooks.getSelectionModel().getSelectedIndex()).getAuthor()));
            book.setCategory((data.get(TableBooks.getSelectionModel().getSelectedIndex()).getCategory()));
            book.setModule((data.get(TableBooks.getSelectionModel().getSelectedIndex()).getModuleCode()));
            book.setSalePrice((data.get(TableBooks.getSelectionModel().getSelectedIndex()).getSalePrice()));
            book.setEdition((data.get(TableBooks.getSelectionModel().getSelectedIndex()).getEdition()));
            book.setSellerPrice((data.get(TableBooks.getSelectionModel().getSelectedIndex()).getSellerprice()));

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderID(user.getUserId());
            orderDetail.setBook(book);
            System.out.println("oRder Id :" + orderDetail.getOrderID());

            CartDA cartda = new CartDA();
            cartda.addCartItem(orderDetail);

        }

    }

    public void viewCart(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TBEStructure/view/cart.fxml"));

            AnchorPane root = (AnchorPane) loader.load();
            dialogStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            CartController controller = loader.<CartController>getController();
            controller.setCartTable(user);
            dialogStage.setScene(scene);
            dialogStage.setTitle("Cart");
            dialogStage.show();

        } catch (IOException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LogOut(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TBEStructure/view/Login.fxml"));

            AnchorPane root = (AnchorPane) loader.load();
            dialogStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);         
            dialogStage.setScene(scene);
            dialogStage.setTitle("Login");
            dialogStage.show();

        } catch (IOException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

    }

    public void setUser(User user) {
        this.user = user;

    }

}