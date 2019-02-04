package UI;

import Managers.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class aRegister {
    private UIManager uiManager;
    private CacheManager cacheManager;
    private DatabaseManager databaseManager;

    @FXML private Button aRegister;
    @FXML private Button search;
    @FXML private Button back;

    public aRegister(UIManager uiManager, CacheManager cacheManager, DatabaseManager databaseManager) {
        this.uiManager = uiManager;
        this.cacheManager = cacheManager;
        this.databaseManager = databaseManager;
    }

    /**
     * Change scene
     * @param event
     * @throws IOException
     */
    @FXML
    public void changeScene(MouseEvent event) throws IOException {
//        if(event.getSource() == aRegister){
//            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/aHomepage.fxml"));
//            sm.changeScene(root, aRegister);
//        }
//        else if(event.getSource() == search){
//            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/SearchPage.fxml"));
//            sm.changeScene(root, search);
//        }
//        else if(event.getSource() == back){
//            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/LoginPage.fxml"));
//            sm.changeScene(root, back);
//        }
    }
}
