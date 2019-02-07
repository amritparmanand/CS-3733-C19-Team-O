package UI;

import Datatypes.Agent;
import Datatypes.Manufacturer;
import Managers.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class aRegister {

    private SceneManager sceneM;
    private CacheManager cacheM;

    public aRegister(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML private Button aRegister;

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField confirmP;
    @FXML private TextField fullName;
    @FXML private TextField email;
    @FXML private TextField phone;
    @FXML private TextField ttbID;


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
            cacheM.register(a);
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
                ttbID.getText().isEmpty()){
            aRegister.setDisable(true);
        }
        else{
            aRegister.setDisable(false);
        }
    }
}
