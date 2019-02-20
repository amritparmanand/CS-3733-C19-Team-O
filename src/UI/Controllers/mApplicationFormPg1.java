package UI.Controllers;

import Datatypes.Form;
import Datatypes.Manufacturer;
import Datatypes.PDF;
import Datatypes.ProgressBar;
import Managers.*;
import UI.MultiThreadWaitFor;
import UI.callableFunction;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.lang.System;

import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Year;
import java.util.Calendar;

/**
 * @author Amrit Parmanand & Elizabeth Del Monaco
 * @version It 2
 * Controller for mApplicationFormPg1 of UI
 */
public class mApplicationFormPg1 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;
    @FXML
    private Label saveDraftMessage;
    @FXML
    private JFXTextField repID;
    @FXML
    private JFXTextField brewerNO;
    @FXML
    private RadioButton domestic;
    @FXML
    private RadioButton imported;
    @FXML
    private JFXTextField serialNumber;
    @FXML
    private RadioButton wine;
    @FXML
    private RadioButton distilled;
    @FXML
    private RadioButton malt;
    @FXML
    private JFXTextField brandName;
    @FXML
    private JFXTextField fancifulName;
    @FXML
    private JFXTextField alcoholPercentage;
    @FXML
    private JFXTextField phLevel;
    @FXML
    private JFXTextField vintageYear;
    @FXML
    private RadioButton wine2;
    @FXML
    private RadioButton spirits2;
    @FXML
    private RadioButton beer2;
    @FXML
    private JFXButton saveDraft;
    @FXML
    private Label serialMessage;

    String style = "-fx-background-color: #94BDFF;";

    public mApplicationFormPg1(SceneManager sceneM, CacheManager cacheM, Form form) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
    }

    public mApplicationFormPg1(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        form = cacheM.getForm();
    }

    @SuppressWarnings("Duplicates")
    @FXML
    public void initialize() {

        Manufacturer manAcc = (Manufacturer) cacheM.getAcct();

        boolean isDomestic = false;
        boolean isImported = false;
        boolean isWine = false;
        boolean isSpirit = false;
        boolean isMalt = false;
        phVBox.setVisible(false);
        vintageVBox.setVisible(false);

        if (form.getProductSource() == "DOMESTIC") {
            isDomestic = true;
            isImported = false;
        }
        if (form.getPrintName() == "IMPORTED") {
            isDomestic = false;
            isImported = true;
        }
        if (form.getBeerWineSpirit() == "WINE") {
            isWine = true;
            isSpirit = false;
            isMalt = false;
            phLevel.setDisable(false);
            vintageYear.setDisable(false);
        } else if (form.getBeerWineSpirit() == "SPIRITS") {
            isWine = false;
            isSpirit = true;
            isMalt = false;
        } else if (form.getBeerWineSpirit() == "BEER") {
            isWine = false;
            isSpirit = false;
            isMalt = true;
        }
        if (form.getRepID() != 0)
            repID.setText(Integer.toString(form.getRepID()));
        else
            repID.setText(Integer.toString(manAcc.getRepID()));

        brewerNO.setText(form.parseGarbage(form.getBrewerNumber()));
        domestic.setSelected(isDomestic);
        imported.setSelected(isImported);
        serialNumber.setText(form.parseGarbage(form.getSerialNumber()));
        wine.setSelected(isWine);
        distilled.setSelected(isSpirit);
        malt.setSelected(isMalt);
        if (!form.getBrandName().equals(""))
            brandName.setText(form.parseGarbage(form.getBrandName()));
        else
            brandName.setText(manAcc.getCompanyName());
        fancifulName.setText(form.parseGarbage(form.getFancifulName()));
        wine2.setSelected(isWine);
        spirits2.setSelected(isSpirit);
        beer2.setSelected(isMalt);
        alcoholPercentage.setText(form.parseGarbage(form.getAlcoholPercent()));
        phLevel.setText(form.parseGarbage(form.getpHLevel()));
        vintageYear.setText(form.parseGarbage(form.getVintageYear()));
    }

    @FXML
    public void saveDraft() {

        if (form.getTtbID() != 0) {
            checkDiff();
        }

        if (domestic.isSelected() || imported.isSelected()) {
            if (domestic.isSelected()) {
                form.setProductSource("DOMESTIC");
            } else if (imported.isSelected()) {
                form.setProductSource("IMPORTED");
            }
        }

        if (wine.isSelected() || distilled.isSelected() || malt.isSelected()) {
            if (wine.isSelected()) {
                form.setProductType("WINE");
            } else if (distilled.isSelected()) {
                form.setProductType("DISTILLED");
            } else if (malt.isSelected()) {
                form.setProductType("MALT");
            }
        }

        String type2 = "WINE";
        if (wine2.isSelected() || spirits2.isSelected() || beer2.isSelected()) {
            if (wine2.isSelected())
                type2 = "WINE";
            else if (spirits2.isSelected())
                type2 = "SPIRITS";
            else if (beer2.isSelected())
                type2 = "BEER";
            form.setBeerWineSpirit(type2);
            if (type2 == "WINE") {
                if (!phLevel.getText().isEmpty() && !form.getpHLevel().contains(style)) {
                    form.setpHLevel(phLevel.getText());
                }
                if (!vintageYear.getText().isEmpty() && !form.getVintageYear().contains(style)) {
                    form.setVintageYear(vintageYear.getText());
                }
            }
            else {
                form.setpHLevel(null);
                form.setVintageYear(null);
            }
        }

        if (!repID.getText().isEmpty()) {
            form.setRepID(Integer.parseInt(repID.getText()));
        }
        if (!brewerNO.getText().isEmpty() && !form.getBrewerNumber().contains(style)) {
            form.setBrewerNumber(brewerNO.getText());
            System.out.println("overwritten");
        }
        if (!serialNumber.getText().isEmpty() && !form.getSerialNumber().contains(style)) {
            form.setSerialNumber(serialNumber.getText());
            System.out.println("overwritten");
        }
        if (!brandName.getText().isEmpty() && !form.getBrandName().contains(style)) {
            form.setBrandName(brandName.getText());
            System.out.println("overwritten");
        }
        if (!fancifulName.getText().isEmpty() && !form.getFancifulName().contains(style)) {
            form.setFancifulName(fancifulName.getText());
            System.out.println("overwritten");
        }
        if (!alcoholPercentage.getText().isEmpty() && !form.getAlcoholPercent().contains(style)) {
            form.setAlcoholPercent(alcoholPercentage.getText());
        }



        //cacheM.setForm(form);

        System.out.println("Pg1 saved!");
    }

    @FXML
    private VBox phVBox;
    @FXML
    private VBox vintageVBox;

    @FXML
    public void hideWineFields() {
        phVBox.setVisible(false);
        vintageVBox.setVisible(false);
    }

    @FXML
    public void showWineFields() {
        phVBox.setVisible(true);
        vintageVBox.setVisible(true);
    }

    @FXML
    public void nextPage() throws IOException {
        saveDraft();
//        multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg2.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg2(sceneM, cacheM, form));
    }

    @FXML
    public void goToHomePage() throws IOException {
//        multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }

    //starting with textboxes, not sure how to handle radio buttons and checkboxes
    public void checkDiff() {

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
    }

    //PROGRESSBAR 1
    //GET THIS DATA FROM CACHE
    @FXML
    public void updateProgressBar(int questions1) {
        ProgressBar progressBar1 = new ProgressBar();
        progressBar1.updateProgressBar1(questions1);
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

//    @FXML public void onePageSwitch() throws IOException {
//        if (switchButton.isDisable()){
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mOnePageForm.fxml"));
//            sceneM.changeScene(loader, new mOnePageForm(sceneM, new CacheManager(this.cacheM.getDbM())));
//            switchButton.isFocused();
//        }else{
//
//        }
//    }

    /**
     * @return true if valid serial number, false if not
     * @author Clay Oshiro-Leavitt
     * Checks that the serial number is valid
     * Criteria:
     * must be an integer
     * must be 6 digits in length
     * 1st two digits must be the last two digits of the current year (IE Year = 2019 -> 1st two digits = 19)
     */
    @FXML
    public Boolean validSerial() {
        String year = Integer.toString(Year.now().getValue());
        String serial = serialNumber.getText();
        if (serial.matches("^[0-9]{6}")) {
            if (serial.substring(0, 1).equals(year.substring(2, 3))) {
                serialMessage.setText("");
                return true;
            } else System.out.println("First two digits must be equal to last two digits of current year");
            serialMessage.setTextFill(Color.RED);
            serialMessage.setText("First two digits must be equal to last two digits of current year");
            return false;
        } else
            serialMessage.setTextFill(Color.RED);
        serialMessage.setText("Serial Number must be an integer of 6 digits");
        return false;
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
//                        pdf.savePDF(pdf, vbox);
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