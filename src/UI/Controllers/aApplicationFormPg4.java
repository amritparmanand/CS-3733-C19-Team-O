package UI.Controllers;


import Datatypes.Form;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * @author Clay Oshiro-Leavitt & Elizabeth Del Monaco
 * @version It 2
 * Controller for aApplicationFormPg4 of UI
 */
public class aApplicationFormPg4 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;

    public aApplicationFormPg4(SceneManager sceneM, CacheManager cacheM, Form form) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
    }

    @FXML private JFXButton acceptForm;
    @FXML private JFXButton denyForm;
    @FXML private JFXButton search;
    @FXML private JFXButton homePage;
    @FXML private JFXButton previous;
    @FXML private JFXButton submit;
    @FXML private JFXTextField dateOfApplication;
    @FXML private JFXTextField dateIssued;
    @FXML private JFXTextField signature;
    @FXML private JFXTextField printName;
    @FXML private JFXTextArea Q16Comment;
    @FXML private JFXTextArea Q17Comment;
    @FXML private JFXTextArea Q18Comment;
    @FXML private JFXTextArea Q19Comment;

    @FXML public void initialize () {
        Form form = this.form;
        dateOfApplication.setText(form.getDateOfApplication());
        dateOfApplication.setEditable(false);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
        sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
    }

    @FXML
    public void acceptForm() throws IOException {
        cacheM.approveForm(cacheM.getDbM().getConnection());
    }


    @FXML
    public void denyForm() throws IOException {
        cacheM.denyForm(cacheM.getDbM().getConnection());
    }

}
