package UI.Controllers;

import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;


public class AboutUs{
    private SceneManager sceneM;
    private CacheManager cacheM;

    public AboutUs(SceneManager sceneM, CacheManager cacheM){
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML private JFXButton back;

    @FXML
    public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, cacheM));
    }
}