package UI.Controllers;

import Datatypes.Form;
import Managers.*;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * @author Amrit Parmanand & Elizabeth Del Monaco
 * @version It 2
 * Controller for mApplicationFormPg2 of UI
 */
public class mApplicationFormPg2 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private String phoneNumberString;

    @FXML private AnchorPane mainPane;

    @FXML private VBox hideBox;
    @FXML private VBox varietalVBox;
    @FXML private VBox appellationVBox;

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

    @FXML public void initialize(){
        Form form = cacheM.getForm();
        printName.setText(form.getPrintName());
        mailAddress.setText(form.getMailingAddress());
        formula.setText(form.getFormula());
        grapes.setText(form.getGrapeVarietal());
        appellation.setText(form.getAppellation());
        phoneNumber.setText(form.getPhoneNumber());
        email.setText(form.getEmailAddress());

    }

    @FXML public void saveDraft(){
        Form form = cacheM.getForm();

        phoneNumberString = phoneNumber.getText().trim();

        form.setPrintName(printName.getText());
        form.setMailingAddress(mailAddress.getText());
        form.setFormula(formula.getText());
        form.setGrapeVarietal(grapes.getText());
        form.setAppellation(appellation.getText());
        if(validFormPhone(phoneNumberString)) {
            form.setPhoneNumber(phoneNumberString);
            System.out.println("vaild phone number");
        }else{
            System.out.println("invalid phone number");
        }
        form.setEmailAddress(email.getText());

        if(cacheM.getForm().getBeerWineSpirit() != "WINE") {
            form.setGrapeVarietal("");
            form.setAppellation("");
        }

        cacheM.setForm(form);

        System.out.println("save Draft executed");
    }

    @FXML public void wineFieldCheck(){
        if(cacheM.getForm().getBeerWineSpirit() != "WINE") {
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
     * checks the manufacturer phone number for Manufacturer Application form
     * will accept US number with the following conditions
     * 1 prefix optional
     * area code is required
     * delimiters between number groups are optional
     * if delimiters are used, can use spaces, dashes, back slashes as dividers between number groups
     * alphanumeric format is allowed after area code
     * @return true if is valid number, false if not
     */
    @FXML
    public boolean validFormPhone(String phoneNumber){
        if(phoneNumber.matches("^[0]{8,20}$")){
            return false;
        } else if(phoneNumber.matches("(^([0-9]( |-|.|/)?)?(\\(?[0-9]{3}\\)?|[0-9]{3})( |-|.|/)?([0-9]{3}( |-|.|/)?[0-9]{4}|[a-zA-Z0-9]{7})$)")){
            return true;
        }else
            return false;

    }

}