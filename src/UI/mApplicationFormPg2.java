package UI;

import Datatypes.Form;
import Managers.*;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sun.plugin.dom.core.Text;

import java.awt.event.TextEvent;
import java.io.IOException;
import java.math.BigInteger;
/**
 * @author Amrit Parmanand & Elizabeth Del Monaco
 * @version It 2
 * Controller for mApplicationFormPg2 of UI
 */
public class mApplicationFormPg2 {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private AnchorPane mainPane;

    @FXML private VBox hideBox;
    @FXML private VBox varietalVBox;
    @FXML private VBox appellationVBox;

    @FXML private Button next;
    @FXML private Button previous;
    @FXML private Button search;
    @FXML private Button back;

    @FXML private JFXTextField printName;
    @FXML private JFXTextField mailAddress;
    @FXML private JFXTextField formula;
    @FXML private JFXTextField grapes;
    @FXML private JFXTextField appellation;
    @FXML private JFXTextField phoneNumber;
    @FXML private JFXTextField email;

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

        if(cacheM.getForm().getBeerWineSpirit() != 0) {
            form.setGrapeVarietal("");
            form.setAppellation("");
        }

        cacheM.setForm(form);

        System.out.println("worked 2");
    }

    @FXML public void wineFieldCheck(){
        if(cacheM.getForm().getBeerWineSpirit() != 0) {
            grapes.setEditable(false);
            grapes.setPromptText("n/a");
            appellation.setEditable(false);
            appellation.setPromptText("n/a");
        }
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
    /**
     * @author Clay Oshiro-Leavitt
     * @version It 2
     * @param phoneNumber phone number to be checked
     * checks the manufacturer phone number for Manufacturer Registration form
     * will accept US number with the following conditions
     * 1 prefix optional
     * area code is required
     * delimiters between number groups are optional
     * if delimiters are used, can use spaces, dashes, back slashes as dividers between number groups
     * alphanumeric format is allowed after area code
     * @return true if is valid number, false if not
     */
    @FXML
    public boolean validManuPhone(String phoneNumber){
        if(phoneNumber.matches("(^([0-9]( |-|.|/)?)?(\\(?[0-9]{3}\\)?|[0-9]{3})( |-|.|/)?([0-9]{3}( |-|.|/)?[0-9]{4}|[a-zA-Z0-9]{7})$)")){
            //System.out.println("valid number");
            return true;
        }else
            // System.out.println("invalid number");
            return false;

    }

}