package TBEStructure.start;

import TBEStructure.view.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class launch extends Application {

    @Override
    public void start(Stage primarystage) {
        try {
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TBEStructure/view/Login.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            primarystage.setTitle("Login Registration");
            Scene scene = new Scene(root);

            primarystage.setScene(scene);

            if (loader.getController() == null) { System.out.println("fxml loader");}
            LoginController controller = loader.getController();
            controller.setDialogueStage(primarystage);
            primarystage.show();
        }
        catch (Exception e) {
            System.out.println("error while creating view : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
