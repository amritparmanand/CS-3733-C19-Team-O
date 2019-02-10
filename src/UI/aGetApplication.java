package UI;

import Managers.CacheManager;
import Managers.DatabaseManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class aGetApplication {
    private SceneManager sceneM;
    private CacheManager cacheM;

    public aGetApplication(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML private JFXButton back;
    @FXML private JFXButton search;
    @FXML private JFXButton getFormsButton;
    //@FXML private VBox getApp;
    @FXML private FlowPane loadFormPane;

    @FXML
    public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
        sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
    }

    @FXML
    public void search() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void loadForms(ActionEvent event) {
        Pane formResult = null;
        loadFormPane.getChildren().clear();
        try {
            formResult = FXMLLoader.load(getClass().getResource("/UI/Views/alcBox.fxml"));
            loadFormPane.getChildren().add(formResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}