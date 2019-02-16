package edu.wpi.cs3733c19.teamO.UI.Controllers;

import edu.wpi.cs3733c19.teamO.Managers.CacheManager;
import edu.wpi.cs3733c19.teamO.Managers.SceneManager;
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

    @FXML private JFXTextField search;
    @FXML private JFXButton about;
    
    @FXML public void login() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/wpi/cs3733c19/teamO/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, cacheM));
    }
    @FXML public void search() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/wpi/cs3733c19/teamO/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }
     @FXML public void about() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/wpi/cs3733c19/teamO/UI/Views/AboutUs.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

}
