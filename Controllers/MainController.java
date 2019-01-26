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
        if(event.getSource()==registerAsManufacturer){
            //get reference to the button's stage
            stage=(Stage) registerAsManufacturer.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("../Views/mRegister.fxml"));
        }
        else if(event.getSource()==login){
            stage=(Stage) login.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../Views/mHomepage.fxml"));
        }
        else if(event.getSource()==register){
            stage=(Stage) register.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../Views/mHomepage.fxml"));
        }
        else{
            stage=(Stage) back.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../Views/LoginPage.fxml"));
        }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    Button registerAsManufacturer;
    @FXML
    Button register;
    @FXML
    Button login;
    @FXML
    Button back;
}
