package TBEStructure.view;

import TBEStructure.DatabaseAccess.CartDA;
import TBEStructure.DatabaseAccess.CartDA.CartTable;
import TBEStructure.model.OrderDetail;
import TBEStructure.model.Book;
import TBEStructure.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;


public class CartController implements Initializable {

    @FXML private Button btn_BackToShop;
    @FXML private Button btn_CheckOut;
    @FXML private Button btn_ClearCart;
    @FXML private Button btn_RemoveItem;
    @FXML private TableView<CartTable> tableView;
    @FXML private TableColumn<CartTable, Integer> col_BooKId;
    @FXML private TableColumn<CartTable, String> col_ModuleCode;
    @FXML private TableColumn<CartTable, Integer> col_Price;
    @FXML private TableColumn<CartTable, String> col_Title;

    @FXML private Label lbl_price;
    @FXML private Label errorMessage;
    @FXML private Label sessionLabel;

    private User user;
	private Stage dialogStage;

    ObservableList<CartTable> data;

    public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
    public void setUser(User user) {
        this.user = user;
    }

    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		this.errorMessage.setTextFill(Color.web("#ff1a1a"));
      
	}

    public void setCartTable(User user) {
	
        col_BooKId.setCellValueFactory(new PropertyValueFactory<CartTable, Integer>("Id"));
        col_Title.setCellValueFactory(new PropertyValueFactory<CartTable, String>("Title"));
        col_ModuleCode.setCellValueFactory(new PropertyValueFactory<CartTable, String>("ModuleCode"));
        col_Price.setCellValueFactory(new PropertyValueFactory<CartTable, Integer>("Price"));
		data=new CartDA().getAllCartItems(user);
		tableView.setItems(data);
		this.user=user;
	}

	

    public void removeItem(ActionEvent event){
		if(data.isEmpty()){
			this.errorMessage.setText("Sorry, Cart is empty!");
		}
		else{
			if(tableView.getSelectionModel().getSelectedIndex()!=-1){
			Book book=new Book();
			OrderDetail orderDetail = new OrderDetail();
			book.setBookId(Integer.parseInt(data.get(tableView.getSelectionModel().getSelectedIndex()).getId()));
			orderDetail.setOrderID(user.getUserId());
			orderDetail.setBook(book);
			CartDA cartDA=new CartDA();
			if(cartDA.removeFromCart(orderDetail)){
				this.errorMessage.setText(data.get(tableView.getSelectionModel().getSelectedIndex()).getTitle()+" removed");
				data.remove(data.get(tableView.getSelectionModel().getSelectedIndex()));
			}
			else{
				this.errorMessage.setText("Sorry the product couldn't be removed");
			}
		}
			else{
				this.errorMessage.setText("Please select an item to be removed");
			}
		}
		 
	}


	public void backToShop(ActionEvent event){
		try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TBEStructure/view/Shop.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
        dialogStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        ShopController controller = loader.<ShopController>getController();
        controller.setUser(user);
        dialogStage.setScene(scene);
        dialogStage.setTitle("Cart");
        dialogStage.show();
            

        } catch (IOException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
    

}