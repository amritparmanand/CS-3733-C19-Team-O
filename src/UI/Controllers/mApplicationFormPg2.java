package UI.Controllers;

import Datatypes.Form;
import Datatypes.Manufacturer;
import Datatypes.PDF;
import Managers.*;
import UI.MultiThreadWaitFor;
import UI.callableFunction;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private String formEmail;
    private Form form;
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
    @FXML private JFXButton pdfButton;

    String style = "-fx-background-color: #94BDFF;";

    public mApplicationFormPg2(SceneManager sceneM, CacheManager cacheM, Form form) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
    }

    public mApplicationFormPg2(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        form = cacheM.getForm();
    }
    @FXML public void initialize(){

        Manufacturer manAcc = (Manufacturer) cacheM.getAcct();
        printName.setText(form.parseGarbage(form.getPrintName()));
        mailAddress.setText(form.parseGarbage(form.getMailingAddress()));
        formula.setText(form.parseGarbage(form.getFormula()));
        grapes.setText(form.parseGarbage(form.getGrapeVarietal()));
        appellation.setText(form.getAppellation());
        if(!form.getPhoneNumber().equals(""))
            phoneNumber.setText(form.parseGarbage(form.getPhoneNumber()));
        else
            phoneNumber.setText(manAcc.getPhone());
        if(!form.getEmailAddress().equals(""))
            email.setText(form.parseGarbage(form.getEmailAddress()));
        else
            email.setText(manAcc.getEmail());
        wineFieldCheck();
    }

    @FXML public void saveDraft() {

        if (form.getTtbID() != 0) {
            checkDiff();
        }

        phoneNumberString = phoneNumber.getText().trim();
        formEmail = email.getText().trim();

        if (!printName.getText().isEmpty() && !form.getPrintName().contains(style)) {
            form.setPrintName(printName.getText());
        }
        if (!mailAddress.getText().isEmpty() && !form.getMailingAddress().contains(style)) {
            form.setMailingAddress(mailAddress.getText());
        }
        if (!formula.getText().isEmpty() && !form.getFormula().contains(style)) {
            form.setFormula(formula.getText());
        }
        if (!grapes.getText().isEmpty() && !form.getGrapeVarietal().contains(style)) {
            form.setGrapeVarietal(grapes.getText());
        }
        if (!appellation.getText().isEmpty() && !form.getAppellation().contains(style)) {
            form.setAppellation(appellation.getText());
        }

        if (validFormPhone(phoneNumberString)) {
            if (!phoneNumber.getText().isEmpty() && !form.getPhoneNumber().contains(style)) {
                form.setPhoneNumber(phoneNumberString);
            }
            System.out.println("valid phone number");
        } else {
            System.out.println("invalid phone number");
        }

        if (validFormEmail(formEmail)) {
            if (!email.getText().isEmpty() && !form.getEmailAddress().contains(style)) {
                form.setEmailAddress(email.getText());
            }
            System.out.println("valid email");
        } else {
            System.out.println("invalid email");
        }

        if (cacheM.getForm().getBeerWineSpirit() != "WINE") {
            form.setGrapeVarietal("");
            form.setAppellation("");
        }


        //I think this call is extraneous
//        if (!validFormEmail(formEmail) || !validFormPhone(phoneNumberString)) {
//            System.out.println("Unable to save. Invalid fields entered");
//            saveDraftMessage.setTextFill(Color.RED);
//            saveDraftMessage.setText("Unable to save. Invalid phone and/or email");
//        }
 //       else {
            saveDraftMessage.setText("");
            if (!phoneNumber.getText().isEmpty() && !form.getPhoneNumber().contains(style)) {
                form.setPhoneNumber(phoneNumberString);
            }
            if (!email.getText().isEmpty() && !form.getEmailAddress().contains(style)) {
                form.setEmailAddress(formEmail);
            }
            cacheM.setForm(form);

            System.out.println("save Draft executed");
     //   }
    }

    @SuppressWarnings("Duplicates")
    @FXML public void wineFieldCheck(){
        if(cacheM.getForm().getBeerWineSpirit() != "WINE") {
            grapes.setEditable(false);
            grapes.setPromptText("n/a");
            appellation.setEditable(false);
            appellation.setPromptText("n/a");
        }
    }

    public void checkDiff() {
        if (!printName.getText().equals(form.getPrintName()) && !printName.getText().contains(style)) {
            form.setPrintName(printName.getText() + style);
        }
        if (!mailAddress.getText().equals(form.getMailingAddress()) && !mailAddress.getText().contains(style)) {
            form.setMailingAddress(mailAddress.getText() + style);
        }
        if (!formula.getText().equals(form.getFormula()) && !formula.getText().contains(style)) {
            form.setFormula(formula.getText() + style);
        }
        if (!grapes.getText().equals(form.getGrapeVarietal()) && !grapes.getText().contains(style)) {
            form.setGrapeVarietal(grapes.getText() + style);
        }
        if(!appellation.getText().equals(form.getAppellation()) && !appellation.getText().contains(style)) {
            form.setAlcoholPercent(appellation.getText() + style);
        }
        if(!phoneNumber.getText().equals(form.getPhoneNumber()) && !phoneNumber.getText().contains(style)) {
            form.setPhoneNumber(phoneNumber.getText() + style);
        }
        if(!email.getText().equals(form.getEmailAddress()) && !email.getText().contains(style)) {
            form.setEmailAddress(email.getText() + style);
        }

    }

    @FXML public void nextPage() throws IOException {
        saveDraft();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg3.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg3(sceneM, cacheM, form));
    }
    @FXML public void previousPage() throws IOException {
        saveDraft();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg1.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg1(sceneM, cacheM, form));
    }
    @FXML public void goToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }


    @FXML
    public void onePage() throws IOException {
        saveDraft();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mOnePageForm.fxml"));
        sceneM.changeScene(loader, new mOnePageForm(sceneM, cacheM));
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



    @FXML public void savePDF() throws IOException {
        saveDraft();
        PDF pdf = new PDF();
        pdf.savePDF(cacheM.getForm());
    }

}