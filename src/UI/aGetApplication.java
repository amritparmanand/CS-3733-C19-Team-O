package UI;

import Managers.CacheManager;
import Managers.DatabaseManager;
import Managers.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class aGetApplication {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private DatabaseManager dbM;

    public aGetApplication(SceneManager sceneM, CacheManager cacheM, DatabaseManager dbM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.dbM = dbM;
    }

    @FXML private Button backToAHome;
    @FXML private Button search;
    //@FXML private VBox getApp;

    @FXML
    public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
        sceneM.changeScene(loader, new aHomepage(sceneM, cacheM, dbM));
    }

    @FXML
    public void search() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM, dbM));
    }
}
