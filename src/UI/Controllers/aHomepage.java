package UI.Controllers;

import Managers.*;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import com.jfoenix.controls.JFXButton;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;

/**
 * @author Percy Jiang
 * @version It 1
 * Controller for aHomepage of UI
 */
public class aHomepage {
    private SceneManager sceneM;
    private CacheManager cacheM;

    public aHomepage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML private JFXButton logout;
    @FXML private JFXButton search;
    @FXML private VBox getApp;
    @FXML private JFXButton help;

    @FXML
    public void search() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));

    }

    @FXML
    public void getApp() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aFormStorage.fxml"));
        sceneM.changeScene(loader, new aFormStorage(sceneM, cacheM));
    }

    public void help() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/helpViews/helpaHome.fxml"));
        helpPopWindow(root);
    }

    public void helpPopWindow(Parent root){
        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Help Window");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}
