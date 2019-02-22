package UI.Controllers;

import Datatypes.Manufacturer;
import Managers.*;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
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

    @FXML private JFXButton accepted;
    @FXML private JFXButton pending;
    @FXML private JFXButton denied;

    @FXML public void initialize(){
        Manufacturer M = (Manufacturer) cacheM.getAcct();

        accepted.setText("Accepted: " + M.countStatus(cacheM.getDbM().getConnection(), "APPROVED"));
        pending.setText("Pending: " + M.countStatus(cacheM.getDbM().getConnection(), "PENDING"));
        denied.setText("Denied: " + M.countStatus(cacheM.getDbM().getConnection(), "DENIED"));
    }


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
}
