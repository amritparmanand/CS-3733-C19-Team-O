package UI;

import Datatypes.Agent;
import Datatypes.Manufacturer;
import Managers.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Percy Jiang & Gabe Entov
 * @version It 2
 * Controller for aRegister of UI
 */
public class aRegister {

    private SceneManager sceneM;
    private CacheManager cacheM;

    public aRegister(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML private JFXButton aRegister;
    @FXML private JFXButton back;
    @FXML private JFXTextField username;
    @FXML private JFXPasswordField password;
    @FXML private JFXPasswordField confirmP;
    @FXML private JFXTextField fullName;
    @FXML private JFXTextField email;
    @FXML private JFXTextField phone;
    @FXML private JFXTextField ttbID;


    @FXML
    public void search() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, cacheM));
    }

    @FXML
    @SuppressWarnings("Duplicates")
    public void register() throws IOException {
        if(password.getText().equals(confirmP.getText())){
            Agent a = new Agent(username.getText(), password.getText(), fullName.getText(), email.getText(), phone.getText(), Integer.parseInt(ttbID.getText()));
            cacheM.setAcct(a);
            cacheM.getAcct().register(cacheM.getDbM().getConnection());
            System.out.println("Registration Successful! Welcome!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
            sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));

        }
        else{
            System.out.println("Please confirm password!");
        }

    }

    @FXML
    public void validateButton(){
        if(username.getText().isEmpty() ||
                password.getText().isEmpty() ||
                confirmP.getText().isEmpty() ||
                fullName.getText().isEmpty() ||
                email.getText().isEmpty() ||
                phone.getText().isEmpty() ||
                ttbID.getText().isEmpty() ){
            aRegister.setDisable(true);
        }
        else{
            aRegister.setDisable(false);
        }
    }

    /**
     * @Author Clay Oshiro-Leavitt
     * checks the agent phone number for Agent Registration form
     * will only accept US phone numbers
     * @return true if is valid number, false if not
     */
    @FXML
    public boolean validAgentPhone(){
        if(phone.getText().matches("^(\\+0?1\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$\n")){
            return true;
        }else return false;
    }
}
