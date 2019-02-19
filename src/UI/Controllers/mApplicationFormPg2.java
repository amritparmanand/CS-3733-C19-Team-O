package UI.Controllers;

import Datatypes.Form;
import Datatypes.Manufacturer;
import Managers.*;
import UI.MultiThreadWaitFor;
import UI.callableFunction;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;

/**
 * @author Amrit Parmanand & Elizabeth Del Monaco
 * @version It 2
 * Controller for mApplicationFormPg2 of UI
 */
public class mApplicationFormPg2 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private String phoneNumberString;
    private String formEmail;

    @FXML private AnchorPane mainPane;

    @FXML private VBox hideBox;
    @FXML private VBox varietalVBox;
    @FXML private VBox appellationVBox;
    @FXML private Label saveDraftMessage;

    @FXML private JFXTextField printName;
    @FXML private JFXTextField mailAddress;
    @FXML private JFXTextField formula;
    @FXML private JFXTextField grapes;
    @FXML private JFXTextField appellation;
    @FXML private JFXTextField phoneNumber;
    @FXML private JFXTextField email;
    @FXML private JFXButton help;

    public mApplicationFormPg2(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML public void initialize(){

        Manufacturer manAcc = (Manufacturer) cacheM.getAcct();
        Form form = cacheM.getForm();
        printName.setText(form.getPrintName());
        mailAddress.setText(form.getMailingAddress());
        formula.setText(form.getFormula());
        grapes.setText(form.getGrapeVarietal());
        appellation.setText(form.getAppellation());
        if(!form.getPhoneNumber().equals(""))
            phoneNumber.setText(form.getPhoneNumber());
        else
            phoneNumber.setText(manAcc.getPhone());
        if(!form.getEmailAddress().equals(""))
            email.setText(form.getEmailAddress());
        else
            email.setText(manAcc.getEmail());
    }

    @FXML public void saveDraft() {
        Form form = cacheM.getForm();

        phoneNumberString = phoneNumber.getText().trim();
        formEmail = email.getText().trim();

        form.setPrintName(printName.getText());
        form.setMailingAddress(mailAddress.getText());
        form.setFormula(formula.getText());
        form.setGrapeVarietal(grapes.getText());
        form.setAppellation(appellation.getText());
        if (validFormPhone(phoneNumberString)) {
            form.setPhoneNumber(phoneNumberString);
            System.out.println("valid phone number");
        } else {
            System.out.println("invalid phone number");
        }
        if (validFormEmail(formEmail)) {
            form.setEmailAddress(email.getText());
            System.out.println("valid email");
        } else {
            System.out.println("invalid email");
        }
        if (cacheM.getForm().getBeerWineSpirit() != "WINE") {
            form.setGrapeVarietal("");
            form.setAppellation("");
        }


        if (!validFormEmail(formEmail) || !validFormPhone(phoneNumberString)) {
            System.out.println("Unable to save. Invalid fields entered");
            saveDraftMessage.setTextFill(Color.RED);
            saveDraftMessage.setText("Unable to save. Invalid phone and/or email");
        }
        else {
            saveDraftMessage.setText("");
            form.setPhoneNumber(phoneNumberString);
            form.setEmailAddress(formEmail);
            cacheM.setForm(form);

            System.out.println("save Draft executed");
        }
    }

    @FXML public void wineFieldCheck(){
        if(cacheM.getForm().getBeerWineSpirit() != "WINE") {
            grapes.setEditable(false);
            grapes.setPromptText("n/a");
            appellation.setEditable(false);
            appellation.setPromptText("n/a");
        }
    }

//    /**
//     * The multi-thread function
//     * Saves draft every 5 seconds
//     */
//    callableFunction cf = new callableFunction() {
//        @Override
//        public void call() {
//            if(printName != null && mailAddress != null && formula != null && grapes != null && appellation != null
//                    && phoneNumber != null && email != null){
//                saveDraft();
//            }
//        }
//    };
//    MultiThreadWaitFor multiThreadWaitFor = new MultiThreadWaitFor(5, cf);


    @FXML public void nextPage() throws IOException {
        saveDraft();
//        multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg3.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg3(sceneM, cacheM));
    }
    @FXML public void previousPage() throws IOException {
        saveDraft();
//        multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg1.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg1(sceneM, cacheM));
    }
    @FXML public void searchPage() throws IOException {
//        multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }
    @FXML public void goToHomePage() throws IOException {
//        multiThreadWaitFor.onShutDown();
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
    @FXML public boolean validFormPhone(String phoneNumber){
        if(phoneNumber.matches("^[0]{8,20}$")){
            return false;
        } else if(phoneNumber.matches("(^([0-9]( |-|.|/)?)?(\\(?[0-9]{3}\\)?|[0-9]{3})( |-|.|/)?([0-9]{3}( |-|.|/)?[0-9]{4}|[a-zA-Z0-9]{7})$)")){
            return true;
        }else
            return false;

    }

    /**
     * @author Clay Oshiro-Leavitt
     * @version It 2
     * @param email email to be checked
     * checks the manufacturer email
     * it must be a .gov account
     * can have lower case, upper case, numbers
     * @return true if valid email, false if invalid
     */
    @FXML
    public boolean validFormEmail(String email){
        if(email.matches("^([a-zA-Z0-9_\\-\\.]+)@+([a-zA-Z]+).+([a-zA-Z]{2,3})$")){
            return true;
        }else return false;
    }

    @FXML
    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));
    }
    public void help() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/helpViews/helpmAppMulti.fxml"));
        helpPopWindow(root);
    }

    public void helpPopWindow(Parent root){
        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Help Window");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}