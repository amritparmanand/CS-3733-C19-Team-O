package UI.Controllers;

import Datatypes.*;
import Managers.*;
import UI.MultiThreadWaitFor;
import UI.callableFunction;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Amrit Parmanand & Elizabeth Del Monaco
 * @version It 2
 * Controller for mApplicationFormPg2 of UI
 */
public class mApplicationFormPg2 extends Controller {
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
    @FXML private VBox commentVBox;
    @FXML private JFXTextArea aComment;
    @FXML private ImageView alcyView;
    @FXML private Text alcyLabel;
    private settingPage settingPage;

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

        Alcy alcy = cacheM.getAlcy();
        alcy.summonAlcy(alcyView, alcyLabel);
        alcy.sayMForm();

        printName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { cacheM.getAlcy().sayMHelpCompanyName();}
                else
                {
                    saveDraft();
                    //cacheM.getAlcy().sayGreeting();
                }
            }
        });

        mailAddress.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { cacheM.getAlcy().sayMHelpMailingAddress();}
                else
                {
                    saveDraft();
                    cacheM.getAlcy().sayMailingAddress();
                }
            }
        });

        formula.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { cacheM.getAlcy().sayMHelpFormula();}
                else
                {
                    saveDraft();
                    //cacheM.getAlcy().sayGreeting();
                }
            }
        });

        grapes.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { cacheM.getAlcy().sayMHelpGrapeVariental();}
                else
                {
                    saveDraft();
                    //cacheM.getAlcy().sayGreeting();
                }
            }
        });

        appellation.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { cacheM.getAlcy().sayMHelpWineAppellation();}
                else
                {
                    saveDraft();
                    //cacheM.getAlcy().sayGreeting();
                }
            }
        });

        phoneNumber.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {cacheM.getAlcy().sayMHelpPhoneNumber(); }
                else
                {
                    saveDraft();
                    cacheM.getAlcy().sayPhoneNumber();
                }
            }
        });

        email.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { cacheM.getAlcy().sayMHelpEmail();}
                else
                {
                    saveDraft();
                    //cacheM.getAlcy().sayGreeting();
                }
            }
        });

        Manufacturer manAcc = (Manufacturer) cacheM.getAcct();

        aComment.setText(form.getCommentString());

        if(form.getCommentString() == ""){
            commentVBox.setVisible(false);
        }
        printName.setText(form.parseGarbage(form.getApplicantName()));
        mailAddress.setText(form.parseGarbage(form.getMailingAddress()));
        formula.setText(form.parseGarbage(form.getFormula()));
        grapes.setText(form.parseGarbage(form.getGrapeVarietal()));
        appellation.setText(form.parseGarbage(form.getAppellation()));
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

    @SuppressWarnings("Duplicates") @FXML public void saveDraft() {

        if (form.getTtbID() != 0) {
            checkDiff();
        }

        phoneNumberString = phoneNumber.getText().trim();
        formEmail = email.getText().trim();

        if (!printName.getText().isEmpty() && !form.getApplicantName().contains(cacheM.getStyle())) {
            form.setApplicantName(printName.getText());
        }
        if (!mailAddress.getText().isEmpty() && !form.getMailingAddress().contains(cacheM.getStyle())) {
            form.setMailingAddress(mailAddress.getText());
        }
        if (!formula.getText().isEmpty() && !form.getFormula().contains(cacheM.getStyle())) {
            form.setFormula(formula.getText());
        }
        if (!grapes.getText().isEmpty() && !form.getGrapeVarietal().contains(cacheM.getStyle())) {
            form.setGrapeVarietal(grapes.getText());
        }
        if (!appellation.getText().isEmpty() && !form.getAppellation().contains(cacheM.getStyle())) {
            form.setAppellation(appellation.getText());
        }

        if (validFormPhone(phoneNumberString)) {
            if (!phoneNumber.getText().isEmpty() && !form.getPhoneNumber().contains(cacheM.getStyle())) {
                form.setPhoneNumber(phoneNumberString);
            }
            System.out.println("valid phone number");
        } else {
            System.out.println("invalid phone number");
        }

        if (validFormEmail(formEmail)) {
            if (!email.getText().isEmpty() && !form.getEmailAddress().contains(cacheM.getStyle())) {
                form.setEmailAddress(email.getText());
            }
            System.out.println("valid email");
        } else {
            System.out.println("invalid email");
        }

//        if (!cacheM.getForm().getBeerWineSpirit().equals("WINE")) {
//            form.setGrapeVarietal("");
//            form.setAppellation("");
//        }


        //I think this call is extraneous
//        if (!validFormEmail(formEmail) || !validFormPhone(phoneNumberString)) {
//            System.out.println("Unable to save. Invalid fields entered");
//            saveDraftMessage.setTextFill(Color.RED);
//            saveDraftMessage.setText("Unable to save. Invalid phone and/or email");
//        }
 //       else {
//            saveDraftMessage.setText("");
            if (!phoneNumber.getText().isEmpty() && !form.getPhoneNumber().contains(cacheM.getStyle())) {
                form.setPhoneNumber(phoneNumberString);
            }
            if (!email.getText().isEmpty() && !form.getEmailAddress().contains(cacheM.getStyle())) {
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
        if (!printName.getText().equals(form.getApplicantName()) && !printName.getText().contains(cacheM.getStyle())) {
            form.setApplicantName(printName.getText() + cacheM.getStyle());
        }
        if (!mailAddress.getText().equals(form.getMailingAddress()) && !mailAddress.getText().contains(cacheM.getStyle())) {
            form.setMailingAddress(mailAddress.getText() + cacheM.getStyle());
        }
        if (!formula.getText().equals(form.getFormula()) && !formula.getText().contains(cacheM.getStyle())) {
            form.setFormula(formula.getText() + cacheM.getStyle());
        }
        if (!grapes.getText().equals(form.getGrapeVarietal()) && !grapes.getText().contains(cacheM.getStyle())) {
            form.setGrapeVarietal(grapes.getText() + cacheM.getStyle());
        }
        if(!appellation.getText().equals(form.getAppellation()) && !appellation.getText().contains(cacheM.getStyle())) {
            form.setAppellation(appellation.getText() + cacheM.getStyle());
        }
        if(!phoneNumber.getText().equals(form.getPhoneNumber()) && !phoneNumber.getText().contains(cacheM.getStyle())) {
            form.setPhoneNumber(phoneNumber.getText() + cacheM.getStyle());
        }
        if(!email.getText().equals(form.getEmailAddress()) && !email.getText().contains(cacheM.getStyle())) {
            form.setEmailAddress(email.getText() + cacheM.getStyle());
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
        sceneM.changeScene(loader, new mOnePageForm(sceneM, cacheM, form));
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
        pdf.savePDF(form);
    }
    @FXML public void settings() throws IOException {
        settingPage = new settingPage(sceneM, cacheM);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/settingPage.fxml"));
        sceneM.popWindowLoader(loader, settingPage, "Setting");
    }
}