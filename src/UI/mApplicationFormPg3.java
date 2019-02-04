package UI;

import Managers.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class mApplicationFormPg3 {
    private UIManager uiManager;
    private CacheManager cacheManager;
    private DatabaseManager databaseManager;

    @FXML private Button next;
    @FXML private Button previous;
    @FXML private Button search;
    @FXML private Button back;

    public mApplicationFormPg3(UIManager uiManager, CacheManager cacheManager, DatabaseManager databaseManager) {
        this.uiManager = uiManager;
        this.cacheManager = cacheManager;
        this.databaseManager = databaseManager;
    }

    @SuppressWarnings("Duplicates")
    @FXML
    public void changeScene(MouseEvent event) throws IOException {
//        if(event.getSource() == next){
//            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/mApplicationFormPg4.fxml"));
//            sm.changeScene(root, next);
//        }
//        else if(event.getSource() == previous){
//            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/mApplicationFormPg2.fxml"));
//            sm.changeScene(root, previous);
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
