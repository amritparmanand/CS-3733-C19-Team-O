package UI.Controllers;

import Datatypes.Form;
import Datatypes.LabelImage;
import Datatypes.PDF;
import Managers.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Amrit Parmanand, Elizabeth Del Monaco, & Gabriel Entov
 * @version It 2
 * Controller for mApplicationFormPg3 of UI
 */
public class mApplicationFormPg3 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private LabelImage image = new LabelImage();
    private Form form;
    String style = "-fx-background-color: #94BDFF;";

    @FXML private JFXTextField onlyState;
    @FXML private JFXTextField ttbID;
    @FXML private JFXTextField bottleCapacity; //will be int, for future reference
    @FXML private JFXCheckBox certificateOfApproval;
    @FXML private JFXCheckBox certificateOfExemption;
    @FXML private JFXCheckBox DistinctiveLiquor;
    @FXML private JFXCheckBox resubmission;
    @FXML private ImageView imagePreview;
    @FXML private Label errorLabel;
    @FXML private JFXButton pdfButton;

    public mApplicationFormPg3(SceneManager sceneM, CacheManager cacheM,Form form) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
    }
    public mApplicationFormPg3(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = cacheM.getForm();
    }

    @FXML public void initialize() {
        image = form.getLabel();
        certificateOfApproval.setSelected(form.getCertificateOfApproval());
        certificateOfExemption.setSelected(form.getCertificateOfExemption());
        DistinctiveLiquor.setSelected(form.getDistinctiveLiquor());
        resubmission.setSelected(form.getResubmission());
        onlyState.setText(form.getOnlyState());
        if(form.getTtbID() != 0)
            ttbID.setText(String.valueOf(form.getTtbID()));
        bottleCapacity.setText(form.getBottleCapacity());
        if(form.getLabel().getLabelImage() != null)
            imagePreview.setImage(form.getLabel().getLabelImage());
        validateStateField();
        validateBottleCapacity();
        validateTTBID();
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
            ttbID.setDisable(false);
        }
    }

    @FXML public boolean saveDraft(){
        if (form.getTtbID() != 0) {
            checkDiff();
        }

        if (onlyState!= null && ttbID!= null && bottleCapacity!= null && certificateOfApproval!= null
                &&certificateOfExemption!= null && DistinctiveLiquor!= null
                && resubmission!= null && imagePreview!= null) {
            if(!certificateOfExemption.isSelected() && !certificateOfApproval.isSelected() &&
                    !DistinctiveLiquor.isSelected() && !resubmission.isSelected()) {
                errorLabel.setText("Please select a type of application.");
            }
           // Form form = cacheM.getForm();

            form.setCertificateOfApproval(certificateOfApproval.isSelected());
            form.setCertificateOfExemption(certificateOfExemption.isSelected());
            if(certificateOfExemption.isSelected()) {
                if (!onlyState.getText().isEmpty() && (!form.getOnlyState().contains(style))){
                    System.out.println("this is weird this shouldn't pass");
                        form.setOnlyState(onlyState.getText());
                    }
            }else {
                form.setOnlyState(null);
            }
            form.setDistinctiveLiquor(DistinctiveLiquor.isSelected());
            if(DistinctiveLiquor.isSelected()) {
                if (!bottleCapacity.getText().isEmpty()) {
                    if(!form.getBottleCapacity().contains(style)){
                        form.setBottleCapacity(bottleCapacity.getText());
                    }
                }
            }else {
                form.setOnlyState(null);
            }
            form.setResubmission(resubmission.isSelected());
            if(!resubmission.isSelected())
                form.setTtbID(0);
            else
                form.setTtbID(Integer.parseInt(ttbID.getText()));
            //form.setBottleCapacity(bottleCapacity.getText());
            form.setLabel(image);
            errorLabel.setText(" ");
            form.setLabel(image);
            cacheM.setForm(form);
            return true;
        }
        else{
            return false;
        }

    }

    public void checkDiff() {

        //@FXML private JFXTextField onlyState;
        String style = "-fx-background-color: #94BDFF;";
        if (!onlyState.getText().equals(form.getOnlyState()) && !onlyState.getText().contains(style)) {
            form.setOnlyState(onlyState.getText() + style);
            System.out.println("added garbage");
        }
        if (!bottleCapacity.getText().equals(form.getBottleCapacity()) && !bottleCapacity.getText().contains(style)) {
            form.setBottleCapacity(bottleCapacity.getText() + style);
        }

    }

    @FXML public void nextPage() throws IOException {
        saveDraft();
//        multiThreadWaitFor.onShutDown();
        System.out.println("on to page 4");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg4.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg4(sceneM, cacheM, form));
    }
    @FXML public void previousPage() throws IOException {
//        multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg2.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg2(sceneM, cacheM, form));
    }
    @FXML public void goToHomePage() throws IOException {
//        multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }

    @FXML
    public void uploadImage(){
        image.getFile();
        imagePreview.setImage(image.getLabelImage());
    }

    @FXML
    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));
    }

    @FXML
    public void onePage() throws IOException {
        saveDraft();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mOnePageForm.fxml"));
        sceneM.changeScene(loader, new mOnePageForm(sceneM, cacheM));
    }

    @FXML public void savePDF() throws IOException {
        saveDraft();
        PDF pdf = new PDF();
        pdf.savePDF(cacheM.getForm());
    }

////PDF BY ROB oops i mean
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
//        if(form.getSerialNumber() != "") {
//            pdf.appendText(Character.toString(form.getSerialNumber().charAt(0)), 24, 811, 10);
//            pdf.appendText(Character.toString(form.getSerialNumber().charAt(1)), 42, 811, 10);
//            pdf.appendText(Character.toString(form.getSerialNumber().charAt(2)), 70, 811, 10);
//            pdf.appendText(Character.toString(form.getSerialNumber().charAt(3)), 88, 811, 10);
//            pdf.appendText(Character.toString(form.getSerialNumber().charAt(4)), 106, 811, 10);
//            pdf.appendText(Character.toString(form.getSerialNumber().charAt(5)), 122, 811, 10);
//        }
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