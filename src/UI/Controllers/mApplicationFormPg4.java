package UI.Controllers;

import Datatypes.Form;
import Datatypes.Manufacturer;
import Datatypes.PDF;
import Managers.*;
import UI.MultiThreadWaitFor;
import UI.callableFunction;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Amrit Parmanand & Elizabeth Del Monaco
 * @version It 2
 * Controller for mApplicationFormPg4 of UI
 */
public class mApplicationFormPg4 {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private Button previous;
    @FXML private Button search;
    @FXML private Button back;
    @FXML private Button submit;
    @FXML private JFXDatePicker dateOfApplication;
    @FXML private JFXTextField applicantSig;
    @FXML private JFXTextField applicantNamePrint;
    @FXML private Label errorLabel2;
    @FXML private JFXButton pdfButton;


    public mApplicationFormPg4(SceneManager sceneM, CacheManager cacheM) {

        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML
    public void initialize() {
        Form form = cacheM.getForm();
        Manufacturer manAcc = (Manufacturer) cacheM.getAcct();

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

    public void saveDraft(){
        Form form = cacheM.getForm();

        if(dateOfApplication.getValue() != null)
            form.setDateOfApplication(dateOfApplication.getValue().toString());
        if(!applicantNamePrint.getText().isEmpty())
            form.setPrintName(applicantNamePrint.getText());

        cacheM.setForm(form);
        System.out.println("Pg4 Saved!");

    }

    /**
     * The multi-thread function
     * Saves draft every 5 seconds
     */
//    callableFunction cf = new callableFunction() {
//        @Override
//        public void call() {
//            if (dateOfApplication!=null && applicantNamePrint!=null){
//                saveDraft();
//            }
//        }
//    };
//    MultiThreadWaitFor multiThreadWaitFor = new MultiThreadWaitFor(5, cf);

    @FXML
    public void submit() throws SQLException, IOException {
        //multiThreadWaitFor.onShutDown();
        saveDraft();
        Form form = cacheM.getForm();
        System.out.println(form.isValid());
        if(!form.isValid()) {
            errorLabel2.setText("Missing required fields.");
            return;
        }else {
            try {
                cacheM.insertForm(cacheM.getDbM().getConnection());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Form cleanForm = new Form();
        cacheM.setForm(cleanForm);
        goToHomePage();

    }


    @FXML public void previousPage() throws IOException {
        //multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg3.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg3(sceneM, cacheM));
    }
    @FXML public void searchPage() throws IOException {
        //multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }
    @FXML public void goToHomePage() throws IOException {
        //multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
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
//
}