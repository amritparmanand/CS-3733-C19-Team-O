package UI;


import Datatypes.Form;
import Managers.CacheManager;
import Managers.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class aApplicationFormPg4 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;

    public aApplicationFormPg4(SceneManager sceneM, CacheManager cacheM, Form form) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
    }

    @FXML
    private Button back;
    @FXML private Button approve;
    @FXML private Button deny;
    @FXML private Button search;
    @FXML private TextField date;
    @FXML private TextField printName;

    public void initialize () {
        Form form = cacheM.getForm();
        date.setText(form.getDateOfApplication());
        date.setEditable(false);
        printName.setText(form.getPrintName());
        printName.setEditable(false);
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
