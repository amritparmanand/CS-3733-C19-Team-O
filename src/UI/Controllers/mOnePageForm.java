package UI.Controllers;

import Datatypes.Form;
import Datatypes.LabelImage;
import Datatypes.PDF;
import Datatypes.SearchResult;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
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
import java.util.ArrayList;

/**
 * @author Robert & Percy
 * @version It3
 * application form in one page
 */
public class mOnePageForm {
    private SceneManager sceneM;
    private CacheManager cacheM;


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
    @FXML private JFXTextField dateOfApplication;
    @FXML private JFXTextField applicantSig;
    @FXML private JFXTextField applicantNamePrint;
    @FXML private LabelImage image = new LabelImage();
    @FXML private Button button;

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
            grapes.setEditable(false);
            grapes.setPromptText("n/a");
            appellation.setEditable(false);
            appellation.setPromptText("n/a");
        }
    }

    @FXML public void savePDF() throws IOException {

        System.out.println("saving pdf");
        PDF pdf = new PDF();

        pdf.open();

        pdf.appendText(repID.getText(), 24, 912, 10);
        pdf.appendText(brewerNO.getText(), 24, 865, 10);

        if(domestic.isSelected())
            pdf.appendText("X", 143,870, 10);
        else
            pdf.appendText("X", 190,870, 10);

        pdf.appendText(Character.toString(serialNumber.getText().charAt(0)), 24, 811, 10);
        pdf.appendText(Character.toString(serialNumber.getText().charAt(1)), 42, 811, 10);
        pdf.appendText(Character.toString(serialNumber.getText().charAt(2)), 70, 811, 10);
        pdf.appendText(Character.toString(serialNumber.getText().charAt(3)), 88, 811, 10);
        pdf.appendText(Character.toString(serialNumber.getText().charAt(4)), 106, 811, 10);
        pdf.appendText(Character.toString(serialNumber.getText().charAt(5)), 122, 811, 10);

        //type of product
        if (wine.isSelected())
            pdf.appendText("X", 146,833, 10);
        else if(distilled.isSelected())
            pdf.appendText("X", 146,828, 10);
        else
            pdf.appendText("X", 146, 823, 10);


        pdf.appendText(brandName.getText(), 24,780, 10);
        pdf.appendText(fancifulName.getText(), 24,755, 10);
        pdf.appendText(printName.getText(), 268, 846, 10);
        pdf.appendText(mailAddress.getText(), 268,780, 10);
        pdf.appendText(formula.getText(), 24,722, 10);
        pdf.appendText(grapes.getText(), 153, 722, 10);
        pdf.appendText(appellation.getText(), 24,688, 10);
        pdf.appendText(email.getText(), 153, 652, 10);
        pdf.appendText(phoneNumber.getText(), 24, 652, 10);


        //type of application
        if(certificateOfApproval.isSelected()){
            pdf.appendText("X", 398, 736, 10);
        }
        if(certificateOfExemption.isSelected()){
            pdf.appendText("X", 398,720, 10);
            pdf.appendText(onlyState.getText(), 451, 710, 10);
        }
        if(DistinctiveLiquor.isSelected()){
            pdf.appendText("X", 398, 700, 10);
            pdf.appendText(bottleCapacity.getText(), 541, 690, 10);
        }
        if(resubmission.isSelected()){
            pdf.appendText("X", 398,668, 10);
            pdf.appendText(ttbID.getText(), 437, 658, 10);
        }

        //Label
        pdf.appendImage(image.getLabelFile().getPath(), 200, 66, 200, 200);

        pdf.appendText(dateOfApplication.getText(), 24, 500, 10);
        pdf.appendText(applicantSig.getText(), 138, 500, 10);
        pdf.appendText(applicantNamePrint.getText(), 366, 500, 10);

        pdf.closeStream();

        pdfPopupWindow(pdf);
        pdf.close();

        System.out.println("saved!");
    }


    //PoPup


                //POPUP WINDOW

    public void pdfPopupWindow(PDF pdf) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/PDFpopup.fxml"));

        //time for the
        Node vbox = root.getChildrenUnmodifiable().get(0);
        ImageView pdfImage = new ImageView();
        pdfImage.setImage(pdf.renderPDF());

        if (vbox instanceof VBox) {
            System.out.println("vboxinstance");
            Node navBox = ((VBox) vbox).getChildren().get(0);
            Node scrollPane = ((VBox) vbox).getChildren().get(1);

            if (navBox instanceof HBox){
                Node fancifulLabel = ((HBox) navBox).getChildren().get(0);
                Node saveButton = ((HBox) navBox).getChildren().get(1);

                ((Label) fancifulLabel).setText(fancifulName.getText());
                ((JFXButton) saveButton).setOnAction((event -> {
                    try {
                        pdf.savePDF(pdf, vbox);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }));
            }

            if (scrollPane instanceof ScrollPane){
                System.out.println("scrollpane instance");
                ((ScrollPane) scrollPane).setContent(pdfImage);
//                if(pdfVBox instanceof VBox){
//                    Node pdfImage = ((VBox) vbox).getChildren().get(0);
//                    if (pdfImage instanceof ImageView){
//                        System.out.println("imageview sout");
//                        ((ImageView) pdfImage).setImage(pdf.renderPDF());
//                    }
//                }


            }
        }
        popWindow(root);
    }

    @FXML public void printPDF(){

    }


    @FXML public void popWindow(Parent root) throws IOException {
        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("TTB PDF");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML public void saveDraft(){
        Form form = cacheM.getForm();

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
        if (!dateOfApplication.getText().isEmpty()) {
            dateOfApplication.setText(form.getDateOfApplication());
        }
        if (!applicantNamePrint.getText().isEmpty()) {
            applicantNamePrint.setText(form.getApplicantName());
        }

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



}
