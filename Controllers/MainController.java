package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class MainController {

    /**
     * Change scene
     */
    @FXML
    void changeScene(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if(event.getSource()==register){
            //get reference to the button's stage
            stage=(Stage) register.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("Views/Manufacturer-Homepage-Mockup.fxml"));
        }
        else{
            stage=(Stage) back.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Views/registerUIMockup.fxml"));
        }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // For LoginTTBUIM
    @FXML
    Button registerAsManufacturer;
    // End for LoginTTBUIM

    // For registerUIMockup.fxml
    @FXML
    Button register;
    // End for registerUIMockup.fxml

    // For Manufacturer-Homepage-Mockup.fxml
    @FXML
    Button back;
    // End for Manufacturer-Homepage-Mockup.fxml
}
