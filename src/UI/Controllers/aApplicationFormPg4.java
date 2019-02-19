package UI.Controllers;


import Datatypes.Form;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    @FXML private JFXButton logout;
    @FXML private JFXDatePicker dateOfApplication;
    @FXML private JFXTextField printName;
    @FXML private JFXTextArea Q16Comment;
    @FXML private JFXTextArea Q17Comment;
    @FXML private JFXTextArea Q18Comment;
    @FXML private JFXTextArea Q19Comment;
    @FXML private JFXTextField receiver;
    @FXML private JFXDatePicker dateIssued;
    @FXML private JFXTextField signature;




    @FXML public void initialize () {
        Form form = this.form;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        if(!form.getDateOfApplication().isEmpty()){
            dateOfApplication.setValue(LocalDate.parse(form.getDateOfApplication(), formatter));
        }
        dateOfApplication.setEditable(false);
        printName.setText(form.getPrintName());
        printName.setEditable(false);
        signature.setText(form.getSignature());
        if(!form.getDateIssued().isEmpty()){
            dateIssued.setValue(LocalDate.parse(form.getDateIssued(), formatter));
        }
    }

    @FXML
    public void search() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormPg3.fxml"));
        sceneM.changeScene(loader, new aApplicationFormPg3(sceneM, cacheM, form));
    }
    @FXML
    public void goToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
        sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
    }

    @FXML
    public void acceptForm() throws IOException {
        form.setSignature(signature.getText());
        System.out.println(form.getSignature());
        form.setDateIssued(dateIssued.getValue().toString());
        System.out.println(form.getDateIssued());
        cacheM.approveForm(cacheM.getDbM().getConnection());
        System.out.println("acceptForm Called");
    }


    @FXML
    public void denyForm() throws IOException {
        form.setSignature(signature.getText());
        form.setDateIssued(dateIssued.getValue().toString());
        cacheM.denyForm(cacheM.getDbM().getConnection());
    }

    @FXML
    public void saveDraft() throws IOException{
    }


    @FXML public void passForm() throws IOException{
        cacheM.passForm(cacheM.getDbM().getConnection(),cacheM.getForm().getFormID(), Integer.parseInt(receiver.getText()));
        back();
    }

    @FXML
    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));
    }
}
