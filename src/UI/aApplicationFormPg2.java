package UI;


import Datatypes.Form;
import Managers.CacheManager;
import Managers.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javax.xml.soap.Text;
import java.io.IOException;

public class aApplicationFormPg2 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;

    public aApplicationFormPg2(SceneManager sceneM, CacheManager cacheM, Form form) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
    }

    @FXML
    private Button back;
    @FXML
    private Button next;
    @FXML
    private Button search;
    @FXML
    private TextField applicantName;
    @FXML
    private TextField mailAddress;
    @FXML
    private TextField formula;
    @FXML
    private TextField grapes;
    @FXML
    private TextField appellation;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField email;


    public void initialize() {
        Form form = cacheM.getForm();

        applicantName.setText(form.getPrintName());
        applicantName.setEditable(false);
        mailAddress.setText(form.getMailingAddress());
        mailAddress.setEditable(false);
        formula.setText(form.getFormula());
        formula.setEditable(false);
        grapes.setText(form.getGrapeVarietal());
        grapes.setEditable(false);
        appellation.setText(form.getAppellation());
        appellation.setEditable(false);
        phoneNumber.setText(form.getPhoneNumber());
        phoneNumber.setEditable(false);
        email.setText(form.getEmailAddress());
        email.setEditable(false);
        System.out.println("filled in info page 2");

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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormPg3.fxml"));
        sceneM.changeScene(loader, new aApplicationFormPg3(sceneM, cacheM, form));
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
