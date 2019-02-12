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
 * Controller for aApplicationFormPg2 of UI
 */
public class aApplicationFormPg2 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;

    public aApplicationFormPg2(SceneManager sceneM, CacheManager cacheM, Form form) {
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
    @FXML private JFXTextField applicantName;
    @FXML private JFXTextField mailAddress;
    @FXML private JFXTextField formula;
    @FXML private JFXTextField grapes;
    @FXML private JFXTextField appellation;
    @FXML private JFXTextField phoneNumber;
    @FXML private JFXTextField email;
    @FXML private JFXTextArea Q8Comment;
    @FXML private JFXTextArea Q8aComment;
    @FXML private JFXTextArea Q9Comment;
    @FXML private JFXTextArea Q10Comment;
    @FXML private JFXTextArea Q11Comment;
    @FXML private JFXTextArea Q12Comment;
    @FXML private JFXTextArea Q13Comment;



    @FXML public void initialize() {
        Form form = this.form;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormPg3.fxml"));
        sceneM.changeScene(loader, new aApplicationFormPg1(sceneM, cacheM, form));
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

    @FXML
    public void saveDraft() throws IOException{

    }
}
