package UI;


import Datatypes.Form;
import Managers.CacheManager;
import Managers.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.xml.soap.Text;
import java.io.IOException;

public class aApplicationFormPg3 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;

    public aApplicationFormPg3(SceneManager sceneM, CacheManager cacheM, Form form) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
    }

    @FXML
    private Button back;
    @FXML private Button next;
    @FXML private Button search;
    @FXML private TextField typeOfApp;

    public void initialize(){
        Form form = this.form;


    }
    @FXML
    public void search() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, cacheM));
    }
    @FXML
    public void nextPage() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormPg4.fxml"));
        sceneM.changeScene(loader, new aApplicationFormPg4(sceneM, cacheM, form));
    }
    @FXML
    public void goToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }
    @FXML
    public void acceptForm() throws IOException {
        form.approve(cacheM.getDbM().getConnection());
    }


    @FXML
    public void denyForm() throws IOException {
        form.deny(cacheM.getDbM().getConnection());
    }


}
