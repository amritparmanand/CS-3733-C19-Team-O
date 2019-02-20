package UI.Controllers;

import Datatypes.*;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.*;
import com.sun.tools.hat.internal.model.Root;
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

    public mOnePageForm(SceneManager sceneM, CacheManager cacheM, Form form){
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
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
    @FXML private RadioButton wine2;
    @FXML private RadioButton spirits2;
    @FXML private RadioButton beer2;
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

    @SuppressWarnings("Duplicates")
    @FXML public void initialize(){

        switchButton.setSelected(true);

        Form form = cacheM.getForm();
        Manufacturer manAcc = (Manufacturer) cacheM.getAcct();
        image = form.getLabel();


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
                wine2.setSelected(true);
                break;
            case "SPIRITS":
                spirits2.setSelected(true);
                break;
            case "BEER":
                beer2.setSelected(true);
                break;
        }
        if(form.getRepID() != 0)
            repID.setText(Integer.toString(form.getRepID()));
        else
            repID.setText(Integer.toString(manAcc.getRepID()));
        brewerNO.setText(form.getBrewerNumber());
        serialNumber.setText(form.getSerialNumber());
        if(!form.getBrandName().equals(""))
            brandName.setText(form.getBrandName());
        else
            brandName.setText(manAcc.getCompanyName());
        fancifulName.setText(form.getFancifulName());
        alcoholPercentage.setText(form.getAlcoholPercent());
        phLevel.setText(form.getpHLevel());
        vintageYear.setText(form.getVintageYear());
        printName.setText(form.getApplicantName());
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
        wineFieldCheck();

        certificateOfApproval.setSelected(form.getCertificateOfApproval());
        certificateOfExemption.setSelected(form.getCertificateOfExemption());
        DistinctiveLiquor.setSelected(form.getDistinctiveLiquor());
        resubmission.setSelected(form.getResubmission());
        onlyState.setText(form.getOnlyState());
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
            applicantNamePrint.setText(form.getPrintName());
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
            onlyState.setText("");
            onlyState.setDisable(true);
        }else {
            onlyState.setDisable(false);
        }
    }
    @FXML public void validateBottleCapacity() {
        if(!DistinctiveLiquor.isSelected()) {
            bottleCapacity.setText("");
            bottleCapacity.setDisable(true);
        }else{
            bottleCapacity.setDisable(false);
        }
    }
    @FXML public void validateTTBID() {
        if(!resubmission.isSelected()) {
            ttbID.setText("");
            ttbID.setDisable(true);
        }else {
            ttbID.setDisable(false);
        }
    }

    @FXML public boolean validFormPhone(String phoneNumber){
        if(phoneNumber.matches("^[0]{8,20}$")){
            return false;
        } else if(phoneNumber.matches("(^([0-9]( |-|.|/)?)?(\\(?[0-9]{3}\\)?|[0-9]{3})( |-|.|/)?([0-9]{3}( |-|.|/)?[0-9]{4}|[a-zA-Z0-9]{7})$)")){
            return true;
        }else
            return false;

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
        pdf.savePDF(cacheM.getForm());
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
        //Form form = cacheM.getForm();

        // Page 1
        if (domestic.isSelected() || imported.isSelected()) {
            if(domestic.isSelected()) {
                form.setProductSource("DOMESTIC");
            }
            else if(imported.isSelected()){
                form.setProductSource("IMPORTED");
            }
        }
        if (wine.isSelected() || distilled.isSelected() || malt.isSelected()) {
            if(wine.isSelected()){
                form.setProductType("WINE");
            }
            else if(distilled.isSelected()){
                form.setProductType("DISTILLED");
            }
            else if(malt.isSelected()) {
                form.setProductType("MALT");
            }
        }
        String type2 = "WINE";
        if (wine2.isSelected() || spirits2.isSelected() || beer2.isSelected()) {
            if(wine2.isSelected())
                type2 = "WINE";
            else if(spirits2.isSelected())
                type2 = "SPIRITS";
            else if(beer2.isSelected())
                type2 = "BEER";
            form.setBeerWineSpirit(type2);
            if(type2 == "WINE") {
                form.setpHLevel(phLevel.getText());
                form.setVintageYear(vintageYear.getText());
            }else{
                form.setpHLevel(null);
                form.setVintageYear(null);
            }
        }
        if (!repID.getText().isEmpty()){
            form.setRepID(Integer.parseInt(repID.getText()));
        }
        if (!brewerNO.getText().isEmpty()){
            form.setBrewerNumber(brewerNO.getText());
        }
        if (!serialNumber.getText().isEmpty()){
            form.setSerialNumber(serialNumber.getText());
        }
        if (!brandName.getText().isEmpty()) {
            form.setBrandName(brandName.getText());
        }
        if (!fancifulName.getText().isEmpty()) {
            form.setFancifulName(fancifulName.getText());
        }
        if (!alcoholPercentage.getText().isEmpty()) {
            form.setAlcoholPercent(alcoholPercentage.getText());
        }

        // Page 2
        if (!printName.getText().isEmpty()) {
            form.setPrintName(printName.getText());
        }
        if (!mailAddress.getText().isEmpty()) {
            form.setMailingAddress(mailAddress.getText());
        }
        if (!formula.getText().isEmpty()) {
            form.setFormula(formula.getText());
        }
        if (!grapes.getText().isEmpty()) {
            form.setGrapeVarietal(grapes.getText());
        }
        if (!appellation.getText().isEmpty()) {
            form.setAppellation(appellation.getText());
        }
        if (!phoneNumber.getText().isEmpty()) {
            if (validFormPhone(phoneNumber.getText().trim())) {
                form.setPhoneNumber(phoneNumber.getText().trim());
                System.out.println("valid phone number");
            }
            else {
                System.out.println("invalid phone number");
            }
        }
        if (!email.getText().isEmpty()) {
            if (validFormEmail(email.getText().trim())) {
                form.setEmailAddress(email.getText());
                System.out.println("valid email");
            }
            else {
                System.out.println("invalid email");
            }
        }
        if (cacheM.getForm().getBeerWineSpirit() != "WINE") {
            form.setGrapeVarietal("");
            form.setAppellation("");
        }

        // Page 3
        form.setCertificateOfApproval(certificateOfApproval.isSelected());
        form.setCertificateOfExemption(certificateOfExemption.isSelected());
        if(certificateOfExemption.isSelected()) {
            form.setOnlyState(onlyState.getText());
        }
        else {
            form.setOnlyState(null);
        }
        form.setDistinctiveLiquor(DistinctiveLiquor.isSelected());
        form.setResubmission(resubmission.isSelected());
        if(!resubmission.isSelected())
            form.setTtbID(0);
        else
            form.setTtbID(Integer.parseInt(ttbID.getText()));
        if (!bottleCapacity.getText().isEmpty()) {
            form.setBottleCapacity(bottleCapacity.getText());
        }
        form.setLabel(image);

        // Page 4
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        if(dateOfApplication.getValue() != null)
            form.setDateOfApplication(dateOfApplication.getValue().toString());
        form.setApplicantName(applicantNamePrint.getText());

        cacheM.setForm(form);

        System.out.println("Form Saved!");
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


    @SuppressWarnings("Duplicates") @FXML public void submit()throws IOException{
        saveDraft();

        if (!validFormEmail(email.getText().trim()) || !validFormPhone(phoneNumber.getText().trim())) {
            System.out.println("Unable to submit. Invalid fields entered");
        }
        else {
            System.out.println("save Draft executed");

            try{
                cacheM.insertForm(cacheM.getDbM().getConnection());
            }catch(SQLException e){
                e.printStackTrace();
            }

            Form cleanForm = new Form();
            cacheM.setForm(cleanForm);
            goToHomePage();
        }

    }
    //starting with textboxes, not sure how to handle radio buttons and checkboxes
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
        if (wine2.isSelected()) {
            if (!phLevel.getText().equals(form.getpHLevel()) && !phLevel.getText().contains(style)) {
                form.setpHLevel(phLevel.getText() + style);
            }
            if (!vintageYear.getText().equals(form.getVintageYear()) && !vintageYear.getText().contains(style)) {
                form.setVintageYear(vintageYear.getText() + style);
            }
        }

        if (!brewerNO.getText().equals(form.getBrewerNumber()) && !brewerNO.getText().contains(style)) {
            form.setBrewerNumber(brewerNO.getText() + style);
        }
        if (!serialNumber.getText().equals(form.getSerialNumber()) && !serialNumber.getText().contains(style)) {
            form.setSerialNumber((serialNumber.getText() + style));
        }
        if (!brandName.getText().equals(form.getBrandName()) && !brandName.getText().contains(style)) {
            form.setBrandName(brandName.getText() + style);
        }
        if (!fancifulName.getText().equals(form.getFancifulName()) && !fancifulName.getText().contains(style)) {
            form.setFancifulName(fancifulName.getText() + style);
        }
        if (!alcoholPercentage.getText().equals(form.getAlcoholPercent()) && !alcoholPercentage.getText().contains(style)) {
            form.setAlcoholPercent(alcoholPercentage.getText() + style);
        }

        if(domestic.isSelected() && !form.getProductSource().equals("DOMESTIC")){
            form.setProductSource("DOMESTIC" + style);
        }
        if(imported.isSelected() && !form.getProductSource().equals("IMPORTED")){
            form.setProductSource("IMPORTED" + style);
        }

        if(wine.isSelected() && !form.getProductType().equals("WINE")){
            form.setProductType("WINE" + style);
        }
        if(distilled.isSelected() && !form.getProductType().equals("DISTILLED")){
            form.setProductType("DISTILLED" + style);
        }
        if(malt.isSelected() && !form.getProductType().equals("MALT")){
            form.setProductType("MALT" + style);
        }

        if(wine2.isSelected() && !form.getBeerWineSpirit().equals("WINE")){
            form.setBeerWineSpirit("WINE" + style);
        }
        if(spirits2.isSelected() && !form.getBeerWineSpirit().equals("SPIRITS")){
            form.setBeerWineSpirit("SPIRITS" + style);
        }
        if(beer2.isSelected() && !form.getBeerWineSpirit().equals("BEER")){
            form.setBeerWineSpirit("BEER" + style);
        }
        if (!onlyState.getText().equals(form.getOnlyState()) && !onlyState.getText().contains(style)) {
            form.setOnlyState(onlyState.getText() + style);
            System.out.println("added garbage");
        }
        if (!bottleCapacity.getText().equals(form.getBottleCapacity()) && !bottleCapacity.getText().contains(style)) {
            form.setBottleCapacity(bottleCapacity.getText() + style);
        }
        if (!applicantSig.getText().equals(form.getSignature()) && applicantSig.getText().contains(style)) {
            form.setSignature(applicantSig.getText() + style);
        }
        if (!applicantNamePrint.getText().equals(form.getPrintName()) && applicantNamePrint.getText().contains(style)) {
            form.setPrintName(applicantNamePrint.getText() + style);
        }
    }



}
