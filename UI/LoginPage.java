package UI;

import Managers.*;

import Managers.UIManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javax.naming.directory.SearchControls;
import java.io.IOException;

public class LoginPage {
    private UIManager uiManager;
    private CacheManager cacheManager;
    private DatabaseManager databaseManager;

    @FXML private Button pop;
    @FXML private RadioButton m;
    @FXML private RadioButton a;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Button register;
    @FXML private Button login;
    @FXML private Button search;

    public LoginPage(UIManager uiManager, CacheManager cacheManager, DatabaseManager databaseManager) {
        this.uiManager = uiManager;
        this.cacheManager = cacheManager;
        this.databaseManager = databaseManager;
    }

    /**
     * Default constructor
     */

    @FXML
    public void login() throws IOException {
        // Login
        if(m.isSelected()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
            uiManager.changeScene(loader, new mHomepage(uiManager, cacheManager, databaseManager));
        }
        else if(a.isSelected()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
            uiManager.changeScene(loader, new aHomepage(uiManager, cacheManager, databaseManager));
        }
    }

    @FXML
    public void register() throws IOException {
        if(m.isSelected()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mRegister.fxml"));
            uiManager.changeScene(loader, new mRegister(uiManager, cacheManager, databaseManager));
        }
        else if (a.isSelected()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aRegister.fxml"));
            uiManager.changeScene(loader, new aRegister(uiManager, cacheManager, databaseManager));
        }
    }

    @FXML
    public void search() throws IOException {
        // Search
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        uiManager.changeScene(loader, new SearchPage(uiManager, cacheManager, databaseManager));
    }

    /**
     * Pop out a window
     * @param event
     * @throws IOException
     */
    @FXML
    public void popWindow(MouseEvent event) throws IOException {
        if(event.getSource() == pop){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/mortgage.fxml"));
            uiManager.popWindow(root);
        }
    }

    /**
     * Enables the login btn and the register btn if radio buttons select and text fields are filled.
     * Disable otherwise
     */
    @FXML
    public void validateButton(){
        if(m.isSelected() || a.isSelected()){
            register.setDisable(false);
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
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
