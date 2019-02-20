package UI.Controllers;

import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class startPage {
    private SceneManager sceneM;
    private CacheManager cacheM;

    public startPage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML JFXTextField search;
    
    @FXML public void login() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, cacheM));
    }

    @FXML public void search() throws IOException {
        cacheM.setSearch(search.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML public void about() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/AboutUs.fxml"));
        sceneM.changeScene(loader, new AboutUs(sceneM, cacheM));
    }

    @FXML public void settings() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/settingPage.fxml"));
        sceneM.changeScene(loader, new settingPage(sceneM, cacheM));
    }

}
