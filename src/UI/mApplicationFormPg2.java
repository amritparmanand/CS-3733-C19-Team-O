package UI;

import Managers.CacheManager;
import Managers.DatabaseManager;
import Managers.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class mApplicationFormPg2 {
    private SceneManager sceneManager;
    private CacheManager cacheManager;
    private DatabaseManager databaseManager;

    @FXML private Button next;
    @FXML private Button previous;
    @FXML private Button search;
    @FXML private Button back;

    public mApplicationFormPg2(SceneManager sceneManager, CacheManager cacheManager, DatabaseManager databaseManager) {
        this.sceneManager = sceneManager;
        this.cacheManager = cacheManager;
        this.databaseManager = databaseManager;
    }

    @FXML
    public void nextPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg3.fxml"));
        sceneManager.changeScene(loader, new mApplicationFormPg3(sceneManager, cacheManager, databaseManager));
    }

    @FXML
    public void previousPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg1.fxml"));
        sceneManager.changeScene(loader, new mApplicationFormPg1(sceneManager, cacheManager, databaseManager));
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