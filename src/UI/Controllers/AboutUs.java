package UI.Controllers;

import Datatypes.StageContainingScene;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class AboutUs extends StageContainingScene {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private ImageView alcyView;
    @FXML private Text alcyLabel;

    public AboutUs(SceneManager sceneM, CacheManager cacheM) {
        super();
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML
    public void close() {
        super.getStage().close();
    }
}