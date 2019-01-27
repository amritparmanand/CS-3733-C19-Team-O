package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    /**
     * Change scene
     */
    @FXML
    public void changeScene(MouseEvent event) throws IOException {
        Stage stage;
        Parent root;
        if(event.getSource()==register && m.isSelected()){
            //get reference to the button's stage
            stage=(Stage) register.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("../Views/mRegister.fxml"));
        }
        else if(event.getSource()==register && a.isSelected()){
            stage=(Stage) register.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../Views/aRegister.fxml"));
        }
        else if(event.getSource()==login && a.isSelected()){
            stage=(Stage) login.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../Views/aHomepage.fxml"));
        }
        else if(event.getSource()==login && m.isSelected()){
            stage=(Stage) login.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../Views/mHomepage.fxml"));
        }

        else if(event.getSource()==mRegister){
            stage=(Stage) mRegister.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../Views/mHomepage.fxml"));
        }

        else if(event.getSource()==aRegister){
            stage=(Stage) aRegister.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../Views/aHomepage.fxml"));
        }

        else{
            stage=(Stage) mHomepageBack.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("../Views/LoginPage.fxml"));
        }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Enables the login btn if username and password fields are filled
     * Disable otherwise
     */
    @FXML
    public void textFieldCheck() {
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            login.setDisable(true);
        } else {
            login.setDisable(false);
        }
    }

    /**
     * Enables the login btn and the register btn if radio buttons selected
     * and text fields are filled.
     * Disable otherwise
     */
    @FXML
    public void rdoBtnCheck(){
        if(m.isSelected() || a.isSelected()){
            register.setDisable(false);
        }
        else{
            register.setDisable(true);
            login.setDisable(true);
        }
    }

    @FXML
    RadioButton m;
    @FXML
    RadioButton a;

    @FXML
    TextField username;
    @FXML
    TextField password;

    @FXML
    Button register;
    @FXML
    Button mRegister;
    @FXML
    Button aRegister;
    @FXML
    Button login;

    @FXML
    Button mHomepageBack;
}
