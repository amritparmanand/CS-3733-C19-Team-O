package UI.Controllers;

import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;


public class AboutUs{
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private ImageView alcyView;
    @FXML private Text alcyLabel;

    public AboutUs(SceneManager sceneM, CacheManager cacheM){
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML public void initialize(){
        cacheM.getAlcy().summonAlcy(alcyView, alcyLabel);
        cacheM.getAlcy().sayAboutUs();
    }

    @FXML
    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/startPage.fxml"));
        sceneM.changeScene(loader, new startPage(sceneM, cacheM));
    }
    @FXML public void settings() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/settingPage.fxml"));
        sceneM.changeScene(loader, new settingPage(sceneM, cacheM));
    }
}