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
//    //PDF BY ROB oops i mean
//    /**@author Rob**/
//
//    @FXML public void savePDF() throws IOException {
//
//        saveDraft();
//
//        Form form = cacheM.getForm();
//
//        System.out.println("saving pdf");
//        PDF pdf = new PDF();
//
//        pdf.open();
//
//        pdf.appendText(Integer.toString(form.getRepID()), 24, 912, 10);
//        pdf.appendText(form.getBrewerNumber(), 24, 865, 10);
//
//        if(form.getProductSource() == "DOMESTIC")
//            pdf.appendText("X", 143,870, 10);
//        else
//            pdf.appendText("X", 202,870, 10);
//
//        pdf.appendText(Character.toString(form.getSerialNumber().charAt(0)), 24, 811, 10);
//        pdf.appendText(Character.toString(form.getSerialNumber().charAt(1)), 42, 811, 10);
//        pdf.appendText(Character.toString(form.getSerialNumber().charAt(2)), 70, 811, 10);
//        pdf.appendText(Character.toString(form.getSerialNumber().charAt(3)), 88, 811, 10);
//        pdf.appendText(Character.toString(form.getSerialNumber().charAt(4)), 106, 811, 10);
//        pdf.appendText(Character.toString(form.getSerialNumber().charAt(5)), 122, 811, 10);
//
//        //type of product
//        if (form.getProductType() =="WINE")
//            pdf.appendText("X", 146,833, 10);
//        else if(form.getProductType()=="DISTILLED")
//            pdf.appendText("X", 146,816, 10);
//        else
//            pdf.appendText("X", 146, 804, 10);
//
//
//        pdf.appendText(form.getBrandName(), 24,780, 10);
//        pdf.appendText(form.getFancifulName(), 24,755, 10);
//        pdf.appendText(form.getPrintName(), 268, 846, 10);
//        pdf.appendText(form.getMailingAddress(), 268,780, 10);
//        pdf.appendText(form.getFormula(), 24,722, 10);
//        pdf.appendText(form.getGrapeVarietal(), 153, 722, 10);
//        pdf.appendText(form.getAppellation(), 24,688, 10);
//        pdf.appendText(form.getEmailAddress(), 153, 656, 10);
//        pdf.appendText(form.getPhoneNumber(), 24, 656, 10);
//
//
//        //type of application
//        if(form.getCertificateOfApproval()){
//            pdf.appendText("X", 398, 736, 10);
//        }
//        if(form.getCertificateOfExemption()){
//            pdf.appendText("X", 398,720, 10);
//            pdf.appendText(form.getOnlyState(), 451, 710, 10);
//        }
//        if(form.getDistinctiveLiquor()){
//            pdf.appendText("X", 398, 700, 10);
//            pdf.appendText(form.getBottleCapacity(), 541, 690, 10);
//        }
//        if(form.getResubmission()){
//            pdf.appendText("X", 398,668, 10);
//            pdf.appendText(Integer.toString(form.getTtbID()), 437, 658, 10);
//        }
//
//        //Label fix later
//        //pdf.appendImage(image.getLabelFile().getPath(), 200, 66, 200, 200);
//
//        pdf.appendText(form.getDateOfApplication(), 24, 500, 10);
//        //pdf.appendText(applicantSig.getText(), 138, 500, 10);
//        pdf.appendText(form.getApplicantName(), 366, 500, 10);
//
//        pdf.appendText("Additional Fields:", 24, 620, 10 );
//        pdf.appendText("Alcohol Percentage: "+ form.getAlcoholPercent(), 24, 610, 10);
//        pdf.appendText("pH Level: "+ form.getpHLevel(), 24, 600, 10);
//        pdf.appendText("Vintage Year: "+ form.getVintageYear(), 24, 590, 10);
//
//        pdf.closeStream();
//
//        pdfPopupWindow(pdf);
//        pdf.close();
//
//        System.out.println("saved!");
//    }
//
//
//    //PoPup
//
//
//    //POPUP WINDOW
//
//    public void pdfPopupWindow(PDF pdf) throws IOException {
//        Form form = cacheM.getForm();
//        Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/PDFpopup.fxml"));
//
//        //time for the
//        Node vbox = root.getChildrenUnmodifiable().get(0);
//        ImageView pdfImage = new ImageView();
//        pdfImage.setImage(pdf.renderPDF());
//
//        if (vbox instanceof VBox) {
//            System.out.println("vboxinstance");
//            Node navBox = ((VBox) vbox).getChildren().get(0);
//            Node scrollPane = ((VBox) vbox).getChildren().get(1);
//
//            if (navBox instanceof HBox){
//                Node fancifulLabel = ((HBox) navBox).getChildren().get(0);
//                Node saveButton = ((HBox) navBox).getChildren().get(1);
//
//                ((Label) fancifulLabel).setText(form.getFancifulName());
//                ((JFXButton) saveButton).setOnAction((event -> {
//                    try {
//                        pdf.savePDFtoDirectory(pdf, vbox);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                }));
//            }
//
//            if (scrollPane instanceof ScrollPane){
//                System.out.println("scrollpane instance");
//                ((ScrollPane) scrollPane).setContent(pdfImage);
//            }
//        }
//        popWindow(root);
//    }
//
//    @FXML public void popWindow(Parent root) throws IOException {
//        Stage stage;
//        stage = new Stage();
//        stage.setScene(new Scene(root));
//        stage.setTitle("TTB PDF");
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.showAndWait();
//    }

}