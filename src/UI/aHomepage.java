package UI;

import Managers.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class aHomepage {
    private UIManager uiManager;
    private CacheManager cacheManager;
    private DatabaseManager databaseManager;

    @FXML private Button back;
    @FXML private Button search;

    public aHomepage(UIManager uiManager, CacheManager cacheManager, DatabaseManager databaseManager) {
        this.uiManager = uiManager;
        this.cacheManager = cacheManager;
        this.databaseManager = databaseManager;
    }

    @FXML
    public void changeScene(MouseEvent event) throws IOException {
//        if(event.getSource() == back){
//            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/LoginPage.fxml"));
//            sm.changeScene(root, back);
//        }
//        else if(event.getSource() == search){
//            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/SearchPage.fxml"));
//            sm.changeScene(root, search);
//        }
    }
}
