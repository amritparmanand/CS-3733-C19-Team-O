package UI;

import Managers.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class mRegister {
    private UIManager uiManager;
    private CacheManager cacheManager;
    private DatabaseManager databaseManager;

    @FXML private Button mRegister;
    @FXML private Button search;
    @FXML private Button back;

    public mRegister(UIManager uiManager, CacheManager cacheManager, DatabaseManager databaseManager) {
        this.uiManager = uiManager;
        this.cacheManager = cacheManager;
        this.databaseManager = databaseManager;
    }

    /**
     * Change scene
     * @param event
     * @throws IOException
     */
    @SuppressWarnings("Duplicates")
    @FXML
    public void changeScene(MouseEvent event) throws IOException {
//        if(event.getSource() == mRegister){
//            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/mHomepage.fxml"));
//            sm.changeScene(root, mRegister);
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
