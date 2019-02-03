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

public class mApplicationFormPg1 {
    private UIManager uiManager;
    private CacheManager cacheManager;
    private DatabaseManager databaseManager;

    @FXML private Button next;
    @FXML private Button previous;
    @FXML private Button search;
    @FXML private Button back;

    public mApplicationFormPg1(UIManager uiManager, CacheManager cacheManager, DatabaseManager databaseManager) {
        this.uiManager = uiManager;
        this.cacheManager = cacheManager;
        this.databaseManager = databaseManager;
    }

    @FXML
    public void changeScene(MouseEvent event) throws IOException {
//        if(event.getSource() == next){
//            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/mApplicationFormPg2.fxml"));
//            sm.changeScene(root, next);
//        }
//        else if(event.getSource() == search){
//            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/SearchPage.fxml"));
//            sm.changeScene(root, search);
//        }
//        else if(event.getSource() == back){
//            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/mHomepage.fxml"));
//            sm.changeScene(root, back);
//        }
    }
}
