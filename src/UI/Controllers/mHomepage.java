package UI.Controllers;

import Managers.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import com.jfoenix.controls.JFXRadioButton;
import javafx.scene.layout.VBox;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;

/**
 * @author Percy Jiang
 * @version It 1
 * Controller for mHomePage of UI
 */
public class mHomepage {

    private SceneManager sceneM;
    private CacheManager cacheM;

    public mHomepage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML private Button back;
    @FXML private Button search;
    @FXML private VBox newForm;
    @FXML private VBox storage;
    @FXML private JFXButton help;


    @FXML
    public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, cacheM));
    }

    @FXML
    public void newForm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg1.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg1(sceneM, cacheM));
    }

    @FXML
    public void storage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mFormStorage.fxml"));
        sceneM.changeScene(loader, new mFormStorage(sceneM, cacheM));
    }

    @FXML
    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));
    }
    public void help() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/helpViews/helpmAppMulti.fxml"));
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
