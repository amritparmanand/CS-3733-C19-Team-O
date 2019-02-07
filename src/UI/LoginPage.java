package UI;

import Managers.*;

import Managers.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import java.io.IOException;

import javafx.scene.paint.Color;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LoginPage {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private BCryptPasswordEncoder passwordDecoder = new BCryptPasswordEncoder();

    @FXML private RadioButton m;
    @FXML private RadioButton a;
    @FXML private Button register;
    @FXML private Button login;
    @FXML private TextField id;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Label loginMessage;

    public LoginPage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML
    @SuppressWarnings("Duplicates")
    public void login() throws IOException {
        int theID = Integer.parseInt(id.getText());

        if(m.isSelected()){
            String uname = cacheM.getDbM().mFindUsername(theID);
            String hashedPassword = cacheM.getDbM().mFindPassword(theID);

            if(uname.equals(username.getText()) && passwordDecoder.matches(password.getText(),hashedPassword)) {
                cacheM.setAcct(cacheM.getDbM().mCreate(theID));
                System.out.println("Login Successful!");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
                sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
            }
            else{
                loginMessage.setTextFill(Color.RED);
                loginMessage.setText("Incorrect username or password. Please try again.");
                System.out.println("The username and password you entered did not match our records. Please double-check and try again.");
            }
        }

        else if(a.isSelected()){
            String uname = cacheM.getDbM().aFindUsername(theID);
            String hashedPassword = cacheM.getDbM().aFindPassword(theID);
            if(uname.equals(username.getText()) && passwordDecoder.matches(password.getText(),hashedPassword)) {
                cacheM.setAcct(cacheM.getDbM().aCreate(theID));
                System.out.println("Login Successful!");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
                sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
            }
            else{
                loginMessage.setTextFill(Color.RED);
                loginMessage.setText("Incorrect username or password. Please try again.");
                System.out.println("The username and password you entered did not match our records. Please double-check and try again.");
            }
        }
    }

    @FXML public void register() throws IOException {
        if(m.isSelected()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mRegister.fxml"));
            sceneM.changeScene(loader, new mRegister(sceneM, cacheM));
        }
        else if (a.isSelected()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aRegister.fxml"));
            sceneM.changeScene(loader, new aRegister(sceneM, cacheM));
        }
    }
    @FXML public void search() throws IOException {
        // Search
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }
    @FXML public void validateButton(){
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