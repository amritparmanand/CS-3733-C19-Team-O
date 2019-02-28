package UI.Controllers;

import Datatypes.StageContainingScene;
import Managers.CacheManager;
import Managers.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class achievementPage extends StageContainingScene {

    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML ImageView achievement;
    @FXML Label tag;

    public achievementPage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

}
