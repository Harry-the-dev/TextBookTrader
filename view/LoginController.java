package TBEStructure.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.regex.*;

import TBEStructure.model.User;
import TBEStructure.DatabaseAccess.loginDA;

public class LoginController {

    private Stage dialogStage;

    @FXML
    private TextField txt_Email;
    @FXML
    private PasswordField txt_Password;
    @FXML
    private Button btn_Login;
    @FXML
    private Button btn_Regsiter;
    @FXML
    private Label err_login;

   // public void ToolTip ()
   // {
   //     Tooltip usertip = new Tooltip("Enter your Password")
   // }

    public void SwitchToRegister(ActionEvent event) throws IOException {

        AnchorPane root = FXMLLoader.load(getClass().getResource("/TBEStructure/view/Registration.fxml"));
        dialogStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.setTitle("Regsiter");
        dialogStage.show();

    }

    public void SwitchToShop(ActionEvent event, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(
								getClass().getResource("/TBEStructure/view/Shop.fxml"));

        AnchorPane root = (AnchorPane) loader.load();
        dialogStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        ShopController controller = 
        loader.<ShopController>getController();
        controller.setUser(user);
        dialogStage.setScene(scene);
        dialogStage.setTitle("Shop - Welcome : "+ user.getName());
        dialogStage.show();

    }

    public void Login(ActionEvent event) throws IOException {
        boolean valid = true;
        if (txt_Email.getText() == null || txt_Password.getText() == null) {
            err_login.setText("Fields can't be empty");
            valid = false;
        }
        if (txt_Password.getLength() < 4) {
            err_login.setText("password should be more than 4 characters");
            valid = false;
        }
        if (!isValidemailAddress(txt_Email.getText())) {
            err_login.setText("PLease enter valid email");
            valid = false;
        }

        if (valid) {
            err_login.setText("");
            String email = this.txt_Email.getText();
            String password = this.txt_Password.getText();
            loginDA loginDA = new loginDA();
            User user = loginDA.AuthenticateUser(email, password);

            if(user==null)
            {
                err_login.setText("Invalid Login Details");
                txt_Email.setText("");
                txt_Password.setText("");
            }
            else{
                this.err_login.setText("");
                SwitchToShop(event,user);
            }



           
        
        }

    }

  
    public boolean isValidemailAddress(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(email);
        return match.matches();
    }

   

    public void setDialogueStage(Stage primarystage) {
        this.dialogStage = primarystage;
    }

}
