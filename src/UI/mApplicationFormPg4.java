package UI;

import Managers.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class mApplicationFormPg4 {
    private UIManager uiManager;
    private CacheManager cacheManager;
    private DatabaseManager databaseManager;

    @FXML private Button next;
    @FXML private Button previous;
    @FXML private Button search;
    @FXML private Button back;
    @FXML private Button submit;

    public mApplicationFormPg4(UIManager uiManager, CacheManager cacheManager, DatabaseManager databaseManager) {
        this.uiManager = uiManager;
        this.cacheManager = cacheManager;
        this.databaseManager = databaseManager;
    }

    @SuppressWarnings("Duplicates")
    @FXML
    public void changeScene(MouseEvent event) throws IOException {
//        if(event.getSource() == previous){
//            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/mApplicationFormPg3.fxml"));
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
