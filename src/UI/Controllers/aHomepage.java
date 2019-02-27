package UI.Controllers;

import Datatypes.Agent;
import Datatypes.Alcy;
import Managers.*;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Percy Jiang
 * @version It 1
 * Controller for aHomepage of UI
 */
public class aHomepage {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private ImageView alcyView;
    @FXML private Text alcyLabel;

    public aHomepage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML public void initialize(){
        Alcy alcy = cacheM.getAlcy();
        alcy.summonAlcy(alcyView, alcyLabel);
        alcy.sayAHomePage();
    }

    @FXML
    public void search() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void logout() throws IOException {
        Agent A = (Agent) cacheM.getAcct();
        A.deleteLabels();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));

    }

    @FXML
    public void getApp() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aFormStorage.fxml"));
        sceneM.changeScene(loader, new aFormStorage(sceneM, cacheM));
    }

    @FXML
    public void viewReviewed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aGetApplication.fxml"));
        sceneM.changeScene(loader, new aGetApplication(sceneM, cacheM));
    }

    @FXML
    public void scores() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/Scores.fxml"));
        sceneM.changeScene(loader, new Scores(sceneM, cacheM));
    }
    @FXML public void settings() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/settingPage.fxml"));
        sceneM.changeScene(loader, new settingPage(sceneM, cacheM));
    }
}
