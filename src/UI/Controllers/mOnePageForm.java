package UI.Controllers;

import Datatypes.*;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author Robert & Percy
 * @version It3
 * application form in one page
 */
public class mOnePageForm {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;
    private ProgressBar progressBar;

    public mOnePageForm(SceneManager sceneM, CacheManager cacheM, Form form){
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
        progressBar = new ProgressBar(this.sceneM,this.cacheM, this.form);
    }

    public mOnePageForm(SceneManager sceneM, CacheManager cacheM){
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML private JFXTextField repID;
    @FXML private JFXTextField brewerNO;
    @FXML private RadioButton domestic;
    @FXML private RadioButton imported;
    @FXML private JFXTextField serialNumber;
    @FXML private RadioButton wine;
    @FXML private RadioButton distilled;
    @FXML private RadioButton malt;
    @FXML private JFXTextField brandName;
    @FXML private JFXTextField fancifulName;
    @FXML private JFXTextField alcoholPercentage;
    @FXML private JFXTextField phLevel;
    @FXML private JFXTextField vintageYear;
//    @FXML private RadioButton wine;
//    @FXML private RadioButton distilled;
//    @FXML private RadioButton beer2;
    @FXML private JFXTextField printName;
    @FXML private JFXTextField mailAddress;
    @FXML private JFXTextField formula;
    @FXML private JFXTextField grapes;
    @FXML private JFXTextField appellation;
    @FXML private JFXTextField phoneNumber;
    @FXML private JFXTextField email;
    @FXML private JFXTextField onlyState;
    @FXML private JFXTextField ttbID;
    @FXML private JFXTextField bottleCapacity;
    @FXML private JFXCheckBox certificateOfApproval;
    @FXML private JFXCheckBox certificateOfExemption;
    @FXML private JFXCheckBox DistinctiveLiquor;
    @FXML private JFXCheckBox resubmission;
    @FXML private ImageView imagePreview;
    @FXML private JFXDatePicker dateOfApplication;
    @FXML private JFXTextField applicantSig;
    @FXML private JFXTextField applicantNamePrint;
    @FXML private LabelImage image = new LabelImage();
    @FXML private Button button;
    @FXML private JFXToggleButton switchButton;
    @FXML private JFXProgressBar progressBarElement;
    @FXML private ImageView alcyView;
    @FXML private Text alcyLabel;

    @SuppressWarnings("Duplicates") @FXML public void initialize(){
        cacheM.getAlcy().setAlcyLabel(alcyLabel);

        repID.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { }
                else
                {
                    saveDraft();
                    cacheM.getAlcy().sayGreeting();
                }
            }
        });

        alcoholPercentage.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { }
                else
                {
                    saveDraft();
                    cacheM.getAlcy().sayWierdBeerPercentage();
                }
            }
        });

        fancifulName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { }
                else
                {
                    saveDraft();
                    cacheM.getAlcy().sayFancifulName();
                }
            }
        });

        brandName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { }
                else
                {
                    saveDraft();
                    cacheM.getAlcy().sayBrandName();
                }
            }
        });

        mailAddress.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { }
                else
                {
                    saveDraft();
                    cacheM.getAlcy().sayMailingAddress();
                }
            }
        });

        phLevel.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { }
                else
                {
                    saveDraft();
                    cacheM.getAlcy().saypHLevel();
                }
            }
        });

        phoneNumber.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { }
                else
                {
                    saveDraft();
                    cacheM.getAlcy().sayPhoneNumber();
                }
            }
        });


        switchButton.setSelected(true);

        Form form = cacheM.getForm();
        Manufacturer manAcc = (Manufacturer) cacheM.getAcct();
        image = form.getLabel();
        Alcy alcy = cacheM.getAlcy();
        alcy.setImageView(alcyView);
        alcy.start();


        switch(form.getProductSource()){
            case "DOMESTIC":
                domestic.setSelected(true);
                break;
            case "IMPORTED":
                imported.setSelected(true);
                break;

        }
        switch(form.getProductType()){
            case "WINE":
                wine.setSelected(true);
                break;
            case "DISTILLED":
                distilled.setSelected(true);
                break;
            case "MALT":
                malt.setSelected(true);
                break;
        }
        switch(form.getBeerWineSpirit()){
            case "WINE":
                wine.setSelected(true);
                break;
            case "SPIRITS":
                distilled.setSelected(true);
                break;
            case "BEER":
                malt.setSelected(true);
                break;
        }
        if(form.getRepID() != 0)
            repID.setText(Integer.toString(form.getRepID()));
        else
            repID.setText(Integer.toString(manAcc.getRepID()));
        brewerNO.setText(form.parseGarbage(form.getBrewerNumber()));
        serialNumber.setText(form.parseGarbage(form.getSerialNumber()));
        if(!form.getBrandName().equals(""))
            brandName.setText(form.parseGarbage(form.getBrandName()));
        else
            brandName.setText(manAcc.getCompanyName());
        fancifulName.setText(form.parseGarbage(form.getFancifulName()));
        alcoholPercentage.setText(form.parseGarbage(form.getAlcoholPercent()));
        phLevel.setText(form.parseGarbage(form.getpHLevel()));
        vintageYear.setText(form.parseGarbage(form.getVintageYear()));
        printName.setText(form.parseGarbage(form.getApplicantName()));
        applicantNamePrint.setText(form.parseGarbage(form.getPrintName()));
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

        certificateOfApproval.setSelected(form.getCertificateOfApproval());
        certificateOfExemption.setSelected(form.getCertificateOfExemption());
        DistinctiveLiquor.setSelected(form.getDistinctiveLiquor());
        resubmission.setSelected(form.getResubmission());
        onlyState.setText(form.parseGarbage(form.getOnlyState()));
        if(form.getTtbID() != 0)
            ttbID.setText(String.valueOf(form.getTtbID()));
        bottleCapacity.setText(form.getBottleCapacity());
        try {
            imagePreview.setImage(form.getLabel().getLabelImage());
        }catch (Exception e){
            e.printStackTrace();
        }
        validateStateField();
        validateBottleCapacity();
        validateTTBID();
        if(!form.getPrintName().equals(""))
            applicantNamePrint.setText(form.parseGarbage(form.getPrintName())); //getPrintName
        else
            applicantNamePrint.setText(manAcc.getFullName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        System.out.println("starting");
        if(!form.getDateOfApplication().isEmpty()){
            dateOfApplication.setValue(LocalDate.parse(form.getDateOfApplication(), formatter));
        }
    }

    @FXML
    public void onePage() throws IOException {
        saveDraft();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg1.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg1(sceneM, cacheM));
    }

    @FXML public void validateStateField() {
        if(!certificateOfExemption.isSelected()) {
            onlyState.setDisable(true);
        }else {
            onlyState.setText(form.parseGarbage(form.getOnlyState()));
            System.out.println(form.getOnlyState());
            onlyState.setDisable(false);
        }
    }

    @FXML public void validateBottleCapacity() {
        if(!DistinctiveLiquor.isSelected()) {
            bottleCapacity.setDisable(true);
        }else{
            bottleCapacity.setText(form.parseGarbage(form.getBottleCapacity()));
            bottleCapacity.setDisable(false);
        }
    }

    @FXML public void validateTTBID() {
        if(!resubmission.isSelected()) {
            ttbID.setText("");
            ttbID.setDisable(true);
        }else {
            ttbID.setText(Integer.toString(form.getTtbID()));
            ttbID.setEditable(false);
        }
    }

    @FXML public boolean validFormPhone(String phoneNumber){
        if(phoneNumber.matches("^[0]{8,20}$")){
            return false;
        } else if(phoneNumber.matches("(^([0-9]( |-|.|/)?)?(\\(?[0-9]{3}\\)?|[0-9]{3})( |-|.|/)?([0-9]{3}( |-|.|/)?[0-9]{4}|[a-zA-Z0-9]{7})$)")){
            return true;
        }else {
            cacheM.getAlcy().sad();
            alcyLabel.setText("im straight...");
            return false;

        }
    }

    @FXML public void goCrazy(){
        cacheM.getAlcy().drunk();
    }

    @FXML public boolean validFormEmail(String email){
        if(email.matches("^([a-zA-Z0-9_\\-\\.]+)@+([a-zA-Z]+).+([a-zA-Z]{2,3})$")){
            return true;
        }else return false;
    }
    @FXML public void wineFieldCheck(){
        if(cacheM.getForm().getBeerWineSpirit() != "WINE") {
            grapes.setDisable(true);
            grapes.setPromptText("n/a");
            appellation.setDisable(true);
            appellation.setPromptText("n/a");
        }
    }
    @FXML public void validateWineFields(){
        if(wine.isSelected()){
            grapes.setDisable(false);
            grapes.setPromptText("n/a");
            appellation.setDisable(false);
            appellation.setPromptText("n/a");
        }else{
            grapes.setDisable(true);
            grapes.setPromptText("n/a");
            appellation.setDisable(true);
            appellation.setPromptText("n/a");
        }
    }


    @FXML public void savePDF() throws IOException {
        saveDraft();
        PDF pdf = new PDF();
        pdf.savePDF(form);
    }

    @FXML
    public void hideWineFields() {
        phLevel.setDisable(true);
        vintageYear.setDisable(true);
    }

    @FXML
    public void showWineFields() {
        phLevel.setDisable(false);
        vintageYear.setDisable(false);
    }

    @FXML public void saveDraft(){
        if (form.getTtbID() != 0) {
            checkDiff();
        }

        String phoneNumberString = phoneNumber.getText().trim();
        String formEmail = email.getText().trim();

        if (!form.getProductSource().contains(cacheM.getStyle())) {
            if (domestic.isSelected() || imported.isSelected()) {
                if (domestic.isSelected()) {
                    form.setProductSource("DOMESTIC");
                } else if (imported.isSelected()) {
                    form.setProductSource("IMPORTED");
                }
            }
        }

        if (!form.getProductType().contains(cacheM.getStyle())) {
            if (wine.isSelected() || distilled.isSelected() || malt.isSelected()) {
                if (wine.isSelected()) {
                    form.setProductType("WINE");
                } else if (distilled.isSelected()) {
                    form.setProductType("DISTILLED");
                } else if (malt.isSelected()) {
                    form.setProductType("MALT");
                }
            }
        }

        if (!form.getBeerWineSpirit().contains(cacheM.getStyle())) {
            if (wine.isSelected() || distilled.isSelected() || malt.isSelected()) {
                if (wine.isSelected())
                    form.setBeerWineSpirit("WINE");
                else if (distilled.isSelected())
                    form.setBeerWineSpirit("SPIRITS");
                else if (malt.isSelected())
                    form.setBeerWineSpirit("BEER");
                if (wine.isSelected()) {
                    if (!phLevel.getText().isEmpty() && !form.getpHLevel().contains(cacheM.getStyle())) {
                        form.setpHLevel(phLevel.getText());
                    }
                    if (!vintageYear.getText().isEmpty() && !form.getVintageYear().contains(cacheM.getStyle())) {
                        form.setVintageYear(vintageYear.getText());
                    }
                }
                else {
                    form.setpHLevel(null);
                    form.setVintageYear(null);
                }
            }
        }

        if (!repID.getText().isEmpty()) {
            form.setRepID(Integer.parseInt(repID.getText()));
        }
        if (!brewerNO.getText().isEmpty() && !form.getBrewerNumber().contains(cacheM.getStyle())) {
            form.setBrewerNumber(brewerNO.getText());
            System.out.println("overwritten");
        }
        if (!serialNumber.getText().isEmpty() && !form.getSerialNumber().contains(cacheM.getStyle())) {
            form.setSerialNumber(serialNumber.getText());
            System.out.println("overwritten");
        }
        if (!brandName.getText().isEmpty() && !form.getBrandName().contains(cacheM.getStyle())) {
            form.setBrandName(brandName.getText());
            System.out.println("overwritten");
        }
        if (!fancifulName.getText().isEmpty() && !form.getFancifulName().contains(cacheM.getStyle())) {
            form.setFancifulName(fancifulName.getText());
            System.out.println("overwritten");
        }
        if (!alcoholPercentage.getText().isEmpty() && !form.getAlcoholPercent().contains(cacheM.getStyle())) {
            form.setAlcoholPercent(alcoholPercentage.getText());
        }



        //cacheM.setForm(form);

        System.out.println("Pg1 saved!");
        phoneNumberString = phoneNumber.getText().trim();
        formEmail = email.getText().trim();

        if (!printName.getText().isEmpty() && !form.getApplicantName().contains(cacheM.getStyle())) {
            form.setApplicantName(printName.getText());
        }
        if (!applicantNamePrint.getText().isEmpty() && !form.getPrintName().contains(cacheM.getStyle())) {
            form.setPrintName(applicantNamePrint.getText());
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

        if (cacheM.getForm().getBeerWineSpirit() != "WINE") {
            form.setGrapeVarietal("");
            form.setAppellation("");
        }

        if (!phoneNumber.getText().isEmpty() && !form.getPhoneNumber().contains(cacheM.getStyle())) {
            form.setPhoneNumber(phoneNumberString);
        }
        if (!email.getText().isEmpty() && !form.getEmailAddress().contains(cacheM.getStyle())) {
            form.setEmailAddress(formEmail);
        }

        //cacheM.setForm(form);
        ////////////////////////////////////////////
        if (onlyState!= null && ttbID!= null && bottleCapacity!= null && certificateOfApproval!= null
                &&certificateOfExemption!= null && DistinctiveLiquor!= null
                && resubmission!= null && imagePreview!= null) {
//            if(!certificateOfExemption.isSelected() && !certificateOfApproval.isSelected() &&
//                    !DistinctiveLiquor.isSelected() && !resubmission.isSelected()) {
//                errorLabel.setText("Please select a type of application.");
//            }
            // Form form = cacheM.getForm();

            form.setCertificateOfApproval(certificateOfApproval.isSelected());
            form.setCertificateOfExemption(certificateOfExemption.isSelected());
            if(certificateOfExemption.isSelected()) {
                if (!onlyState.getText().isEmpty() && (!form.getOnlyState().contains(cacheM.getStyle()))){
                    System.out.println("this is weird this shouldn't pass");
                    form.setOnlyState(onlyState.getText());
                }
            }
            else {
                form.setOnlyState("");
            }
            form.setDistinctiveLiquor(DistinctiveLiquor.isSelected());
            if(DistinctiveLiquor.isSelected()) {
                if (!bottleCapacity.getText().isEmpty()) {
                    if(!form.getBottleCapacity().contains(cacheM.getStyle())){
                        form.setBottleCapacity(bottleCapacity.getText());
                    }
                }
            }else {
                form.setOnlyState("");
            }
            form.setResubmission(resubmission.isSelected());
            if(!resubmission.isSelected())
                form.setTtbID(0);
            else
                form.setTtbID(Integer.parseInt(ttbID.getText()));
            //form.setBottleCapacity(bottleCapacity.getText());
            form.setLabel(image);
//            errorLabel.setText(" ");
            form.setLabel(image);
           // cacheM.setForm(form);
        }
        if(dateOfApplication.getValue() != null)
            form.setDateOfApplication(dateOfApplication.getValue().toString());
        if (!applicantNamePrint.getText().isEmpty()) {
            if(!form.getPrintName().contains(cacheM.getStyle())){
                form.setPrintName(applicantNamePrint.getText());
            }
        }

        if (form.getTtbID() != 0) {
            checkDiff();
        }

        cacheM.setForm(form);
        System.out.println("Pg4 Saved!");
        System.out.println("save Draft executed");
        //call the progress bar
        System.out.println(progressBar.updateProgressBar());
        progressBarElement.setProgress(progressBar.updateProgressBar());

    }

    @FXML public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));
    }

    @FXML public void goToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }

    @FXML
    public void uploadImage(){
        image.getFile();
        imagePreview.setImage(image.getLabelImage());
    }
    @FXML
    public void updateProgress()
    {
        saveDraft();
        progressBarElement.setProgress(progressBar.updateProgressBar());
    }

    @SuppressWarnings("Duplicates") @FXML public void submit()throws Exception{
        saveDraft();

        if (!validFormEmail(email.getText().trim()) || !validFormPhone(phoneNumber.getText().trim())) {
            System.out.println("Unable to submit. Invalid fields entered");
        }
        else {
            System.out.println("save Draft executed");

            try{
                System.out.println(form.getResubmission());
                if(form.getResubmission()){
                    form.resubmitForm(cacheM.getDbM().getConnection());
                } else{
                    form.insertForm(cacheM.getDbM().getConnection());
                }
            }catch(SQLException e){
                e.printStackTrace();
            }

            Manufacturer M = (Manufacturer) cacheM.getAcct();
            M.submitForm(cacheM.getDbM().getConnection());

            Form cleanForm = new Form();
            cacheM.setForm(cleanForm);
            goToHomePage();
        }

    }
    //starting with textboxes, not sure how to handle radio buttons and checkboxes
    @SuppressWarnings("Duplicates")
    public void checkDiff() {
        if (!printName.getText().equals(form.getApplicantName()) && !printName.getText().contains(cacheM.getStyle())) {
            form.setApplicantName(printName.getText() + cacheM.getStyle());
        }
        if (!mailAddress.getText().equals(form.getMailingAddress()) && !mailAddress.getText().contains(cacheM.getStyle())) {
            form.setMailingAddress(mailAddress.getText() + cacheM.getStyle());
        }
        if (!formula.getText().equals(form.getFormula()) && !formula.getText().contains(cacheM.getStyle())) {
            form.setFormula(formula.getText() + cacheM.getStyle());
            System.out.println(form.getFormula());
        }
        if (!grapes.getText().equals(form.getGrapeVarietal()) && !grapes.getText().contains(cacheM.getStyle())) {
            form.setGrapeVarietal(grapes.getText() + cacheM.getStyle());
            System.out.println(form.getGrapeVarietal());
        }
        if(!appellation.getText().equals(form.getAppellation()) && !appellation.getText().contains(cacheM.getStyle())) {
            form.setAlcoholPercent(appellation.getText() + cacheM.getStyle());
        }
        if(!phoneNumber.getText().equals(form.getPhoneNumber()) && !phoneNumber.getText().contains(cacheM.getStyle())) {
            form.setPhoneNumber(phoneNumber.getText() + cacheM.getStyle());
        }
        if(!email.getText().equals(form.getEmailAddress()) && !email.getText().contains(cacheM.getStyle())) {
            form.setEmailAddress(email.getText() + cacheM.getStyle());
        }
        if (wine.isSelected()) {
            if (!phLevel.getText().equals(form.getpHLevel()) && !phLevel.getText().contains(cacheM.getStyle())) {
                form.setpHLevel(phLevel.getText() + cacheM.getStyle());
            }
            if (!vintageYear.getText().equals(form.getVintageYear()) && !vintageYear.getText().contains(cacheM.getStyle())) {
                form.setVintageYear(vintageYear.getText() + cacheM.getStyle());
            }
        }

        if (!brewerNO.getText().equals(form.getBrewerNumber()) && !brewerNO.getText().contains(cacheM.getStyle())) {
            form.setBrewerNumber(brewerNO.getText() + cacheM.getStyle());
        }
        if (!serialNumber.getText().equals(form.getSerialNumber()) && !serialNumber.getText().contains(cacheM.getStyle())) {
            form.setSerialNumber((serialNumber.getText() + cacheM.getStyle()));
        }
        if (!brandName.getText().equals(form.getBrandName()) && !brandName.getText().contains(cacheM.getStyle())) {
            form.setBrandName(brandName.getText() + cacheM.getStyle());
        }
        if (!fancifulName.getText().equals(form.getFancifulName()) && !fancifulName.getText().contains(cacheM.getStyle())) {
            form.setFancifulName(fancifulName.getText() + cacheM.getStyle());
        }
        if (!alcoholPercentage.getText().equals(form.getAlcoholPercent()) && !alcoholPercentage.getText().contains(cacheM.getStyle())) {
            form.setAlcoholPercent(alcoholPercentage.getText() + cacheM.getStyle());
        }

        if(domestic.isSelected() && !form.getProductSource().equals("DOMESTIC")){
            form.setProductSource("DOMESTIC" + cacheM.getStyle());
        }
        if(imported.isSelected() && !form.getProductSource().equals("IMPORTED")){
            form.setProductSource("IMPORTED" + cacheM.getStyle());
        }

        if(wine.isSelected() && !form.getProductType().equals("WINE")){
            form.setProductType("WINE" + cacheM.getStyle());
        }
        if(distilled.isSelected() && !form.getProductType().equals("DISTILLED")){
            form.setProductType("DISTILLED" + cacheM.getStyle());
        }
        if(malt.isSelected() && !form.getProductType().equals("MALT")){
            form.setProductType("MALT" + cacheM.getStyle());
        }

        if(wine.isSelected() && !form.getBeerWineSpirit().equals("WINE")){
            form.setBeerWineSpirit("WINE" + cacheM.getStyle());
        }
        if(distilled.isSelected() && !form.getBeerWineSpirit().equals("SPIRITS")){
            form.setBeerWineSpirit("SPIRITS" + cacheM.getStyle());
        }
        if(malt.isSelected() && !form.getBeerWineSpirit().equals("BEER")){
            form.setBeerWineSpirit("BEER" + cacheM.getStyle());
        }
        if (!onlyState.getText().equals(form.getOnlyState()) && !onlyState.getText().contains(cacheM.getStyle())) {
            form.setOnlyState(onlyState.getText() + cacheM.getStyle());
            System.out.println("added garbage");
        }
        if (!bottleCapacity.getText().equals(form.getBottleCapacity()) && !bottleCapacity.getText().contains(cacheM.getStyle())) {
            form.setBottleCapacity(bottleCapacity.getText() + cacheM.getStyle());
        }
//        if (!applicantSig.getText().equals(form.getSignature()) && applicantSig.getText().contains(style)) {
//            form.setSignature(applicantSig.getText() + style);
//        }
        if (!applicantNamePrint.getText().equals(form.getPrintName()) && applicantNamePrint.getText().contains(cacheM.getStyle())) {
            form.setPrintName(applicantNamePrint.getText() + cacheM.getStyle());
        }


    }



}
