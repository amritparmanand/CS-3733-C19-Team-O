package UI;


import Datatypes.Form;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.xml.soap.Text;
import java.io.IOException;
/**
 * @Author Clay Oshiro-Leavitt
 * Controller for aApplicationFormPg3 of UI
 */
public class aApplicationFormPg3 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;

    public aApplicationFormPg3(SceneManager sceneM, CacheManager cacheM, Form form) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
    }


    @FXML private JFXButton acceptForm;
    @FXML private JFXButton denyForm;
    @FXML private JFXButton saveDraft;
    @FXML private JFXButton homePage;
    @FXML private JFXButton previous;
    @FXML private JFXButton next;
    @FXML private JFXButton search;
    @FXML private JFXTextField typeOfApp;
    @FXML private JFXButton uploadImage;
    @FXML private JFXTextArea Q14Comment;
    @FXML private JFXTextArea Q15Comment;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormPg2.fxml"));
        sceneM.changeScene(loader, new aApplicationFormPg2(sceneM, cacheM, form));
    }
    @FXML
    public void nextPage() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormPg4.fxml"));
        sceneM.changeScene(loader, new aApplicationFormPg4(sceneM, cacheM, form));
    }
    @FXML
    public void goToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
        sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
    }
    @FXML
    public void acceptForm() throws IOException {
        form.approve(cacheM.getDbM().getConnection());
    }


    @FXML
    public void denyForm() throws IOException {
        form.deny(cacheM.getDbM().getConnection());
    }

    @FXML
    public void saveDraft() throws IOException{

    }
    @FXML
    public void uploadImage() throws IOException{

    }

}
