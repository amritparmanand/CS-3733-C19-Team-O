package UI;

import Managers.*;

import Managers.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginPage {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private RadioButton m;
    @FXML private RadioButton a;
    @FXML private Button register;
    @FXML private Button login;
    @FXML private TextField id;
    @FXML private TextField username;
    @FXML private TextField password;

    public LoginPage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML
    public void login() throws IOException {
        // Login
        if(m.isSelected()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
            sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
        }
        else if(a.isSelected()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
            sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
        }
    }

    @FXML
    public void register() throws IOException {
        if(m.isSelected()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mRegister.fxml"));
            sceneM.changeScene(loader, new mRegister(sceneM, cacheM));
        }
        else if (a.isSelected()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aRegister.fxml"));
            sceneM.changeScene(loader, new aRegister(sceneM, cacheM));
        }
    }

    @FXML
    public void search() throws IOException {
        // Search
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    /**
     * Enables the login btn and the register btn if radio buttons select and text fields are filled.
     * Disable otherwise
     */
    @FXML
    public void validateButton(){
        if(m.isSelected() || a.isSelected()){
            register.setDisable(false);
            if (username.getText().isEmpty() || password.getText().isEmpty() || id.getText().isEmpty()) {
                login.setDisable(true);
            } else {
                login.setDisable(false);
            }
        }
        else{
            register.setDisable(true);
            login.setDisable(true);
        }
    }
}