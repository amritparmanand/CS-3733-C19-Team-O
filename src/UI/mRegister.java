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

public class mRegister {

    private SceneManager sceneM;
    private CacheManager cacheM;
    private DatabaseManager dbM;

    public mRegister(SceneManager sceneM, CacheManager cacheM, DatabaseManager dbM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.dbM = dbM;
    }

    @FXML private Button mRegister;
    @FXML private Button search;
    @FXML private Button back;

    /**
     * Change scene
     * @param event
     * @throws IOException
     */
    @SuppressWarnings("Duplicates")
    @FXML
    public void changeScene(MouseEvent event) throws IOException {
        if(event.getSource() == mRegister){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/mHomepage.fxml"));
            sm.changeScene(root, mRegister);
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
