package UI;

import UI.Managers.CacheManager;
import UI.Managers.DatabaseManager;
import UI.Managers.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class aRegister {

    private SceneManager sceneM;
    private CacheManager cacheM;
    private DatabaseManager dbM;

    public aRegister(SceneManager sceneM, CacheManager cacheM, DatabaseManager dbM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.dbM = dbM;
    }

    @FXML private Button aRegister;
    @FXML private Button search;
    @FXML private Button back;

    /**
     * Change scene
     * @param event
     * @throws IOException
     */
    @FXML
    public void changeScene(MouseEvent event) throws IOException {
        if(event.getSource() == aRegister){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/aHomepage.fxml"));
            sm.changeScene(root, aRegister);
        }
        else if(event.getSource() == search){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/SearchPage.fxml"));
            sm.changeScene(root, search);
        }
        else if(event.getSource() == back){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/LoginPage.fxml"));
            sm.changeScene(root, back);
        }
    }
}
