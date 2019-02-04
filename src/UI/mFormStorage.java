package UI;

import Managers.CacheManager;
import Managers.DatabaseManager;
import Managers.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class mFormStorage {

    private SceneManager sceneManager;
    private CacheManager cacheManager;
    private DatabaseManager databaseManager;

    @FXML private Button backToMHome;
    @FXML private Button search;
    @FXML private Button newForm;
    //@FXML private VBox getApp;

    /**
     * Model
     */
    private SceneManager sm;

    /**
     * Default constructor
     */

    public mFormStorage(SceneManager sm, CacheManager cm, DatabaseManager dm) {
        this.sceneManager = sm;
        this.cacheManager = cm;
        this.databaseManager = dm;
    }

    @FXML
    public void newForm() throws IOException {
        //
        //
    }

    @FXML
    public void searchPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneManager.changeScene(loader, new SearchPage(sceneManager, cacheManager, databaseManager));
    }

    @FXML
    public void goToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneManager.changeScene(loader, new mHomepage(sceneManager, cacheManager, databaseManager));
    }
}