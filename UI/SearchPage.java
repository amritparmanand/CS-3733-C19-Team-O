package UI;

import Managers.CacheManager;
import Managers.DatabaseManager;
import Managers.UIManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SearchPage {
    private UIManager uiManager;
    private CacheManager cacheManager;
    private DatabaseManager databaseManager;

    @FXML private Button back;

    public SearchPage(UIManager uiManager, CacheManager cacheManager, DatabaseManager databaseManager) {
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
    }
}
