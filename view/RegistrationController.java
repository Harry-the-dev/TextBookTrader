package TBEStructure.view;

import java.io.IOException;
import java.util.regex.*;

import TBEStructure.DatabaseAccess.loginDA;
import TBEStructure.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.stage.Stage;

public class RegistrationController {

    private Stage dialogStage;

    @FXML
    private Label err_email;
    @FXML
    private Label err_name;
    @FXML
    private Label err_password;
    @FXML
    private Label err_phonenumber;
    @FXML
    private Label err_reEnterPassword;
    @FXML
    private Label err_surname;
    @FXML
    private Button btn_register;
    @FXML
    private AnchorPane pane_Register;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_name;
    @FXML
    private PasswordField txt_password;
    @FXML
    private TextField txt_phoneNumber;
    @FXML
    private PasswordField txt_reEnterPassword;
    @FXML
    private TextField txt_surname;

    public void Registration(ActionEvent event) throws IOException {
        boolean valid = true;

        if (!isValidPhoneNumber(txt_phoneNumber.getText())) {
            err_phonenumber.setText("Enter Valid Phone Number");
            txt_phoneNumber.setText("");
            valid = false;
        }
        if ((!isValidemailAddress(txt_email.getText()) || (txt_email.getText() == ""))) {
            err_email.setText("Enter Valid Email Address");
            txt_email.setText("");
            valid = false;
        }
        if (txt_name.getText() == "" || txt_name.getText() == null) {
            err_name.setText("Name can't be empty");
            txt_name.setText("");
            valid = false;
        }
        if (txt_surname.getText() == "" || txt_surname.getText() == null) {
            err_surname.setText("Name can't be empty");
            txt_surname.setText("");
            valid = false;
        }
        if (!txt_password.getText().equals(txt_reEnterPassword.getText())) {
            err_password.setText("Passwords do not match");
            txt_password.setText("");
            txt_reEnterPassword.setText("");
            valid = false;
        }
        if (valid) {
            System.out.println("no error");
            err_email.setText("");
            err_phonenumber.setText("");
            err_name.setText("");
            err_password.setText("");
            err_surname.setText("");

            User user = new User();
            user.setName(txt_name.getText());
            user.setSurname(txt_surname.getText());
            user.setEmail(txt_email.getText());
            user.setPhoneNumber(txt_phoneNumber.getText());
            user.setPassword(txt_password.getText());

            loginDA registerDA = new loginDA();
            registerDA.AddCustomer(user.getName(), user.getSurname(), user.getPhoneNumber(), user.getPassword(),
                    user.getEmail());
            SwitchToLogin(event);
        }

    }

    public void SwitchToLogin(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/TBEStructure/view/Login.fxml"));
        dialogStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.setTitle("Login");
        dialogStage.show();

    }

    public boolean isValidPhoneNumber(String number) {
        String regex = "^((?:\\+27|27)|0)(\\d{2})-?(\\d{3})-?(\\d{4})$";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(number);
        return match.matches();
    }

    public boolean isValidemailAddress(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(email);
        return match.matches();
    }

}
