package UI;

import Managers.CacheManager;
import Managers.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class aFormStorage {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML
    private FlowPane loadForms;
    @FXML
    private Button getFormsButton;

    public aFormStorage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML private Button back;
    @FXML private Button search;
    @FXML private VBox getApp;

    @FXML
    public void search() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, cacheM));
    }

    @FXML
    public void loadForms(ActionEvent event) {
        Pane formResult = null;
        try {
            System.out.println("hi");
            formResult = FXMLLoader.load(getClass().getResource("/UI/Views/alcBox.fxml"));
            loadForms.getChildren().add(formResult);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
