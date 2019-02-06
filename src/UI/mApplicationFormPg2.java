package UI;

import Datatypes.Form;
import Managers.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sun.plugin.dom.core.Text;

import java.awt.event.TextEvent;
import java.io.IOException;
import java.math.BigInteger;

public class mApplicationFormPg2 {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private Button next;
    @FXML private Button previous;
    @FXML private Button search;
    @FXML private Button back;

    @FXML private TextField printName;
    @FXML private TextField mailAddress;
    @FXML private TextField formula;
    @FXML private TextField grapes;
    @FXML private TextField appellation;
    @FXML private TextField phoneNumber;
    @FXML private TextField email;

    public mApplicationFormPg2(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML public void saveDraft(){
        Form form = cacheM.getForm();
        form.setPrintName(printName.getText());
        form.setMailingAddress(mailAddress.getText());
        form.setFormula(formula.getText());
        form.setGrapeVarietal(grapes.getText());
        form.setAppellation(appellation.getText());
        form.setPhoneNumber(phoneNumber.getText());
        form.setEmailAddress(email.getText());

        cacheM.setForm(form);

        System.out.println("worked 2");
    }

    @FXML public void nextPage() throws IOException {
        saveDraft();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg3.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg3(sceneM, cacheM));
    }
    @FXML public void previousPage() throws IOException {
        saveDraft();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg1.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg1(sceneM, cacheM));
    }
    @FXML public void searchPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }
    @FXML public void goToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }

}