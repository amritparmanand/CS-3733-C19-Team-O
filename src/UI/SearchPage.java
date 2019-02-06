package UI;

import Managers.CacheManager;
import Managers.DatabaseManager;
import Managers.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;

public class SearchPage {
    private SceneManager sceneM;
    private CacheManager cacheM;

    public SearchPage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML private Button back;
    @FXML private TextField searchBox;
    @FXML private CheckBox beerCheck;
    @FXML private CheckBox liquorCheck;
    @FXML private CheckBox wineCheck;
    @FXML private TextField phLow;
    @FXML private TextField phHigh;
    @FXML private TextField alcoholLow;
    @FXML private TextField alcoholHigh;
    @FXML private TextField yearLow;
    @FXML private TextField yearHigh;
    @FXML private FlowPane searchResults;
    @FXML private Button searchButton;
    @FXML
    public void back() throws IOException {
        //sceneM.backScene();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, cacheM));
    }

    @FXML
    public void loadAlcohol(ActionEvent event) {
        Pane alcResult = null;
        try {
            alcResult = FXMLLoader.load(getClass().getResource("/UI/Views/alcBox.fxml"));
            searchResults.getChildren().add(alcResult);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void search(ActionEvent event) throws SQLException {
//        cacheM.getDbM().
    }
}
