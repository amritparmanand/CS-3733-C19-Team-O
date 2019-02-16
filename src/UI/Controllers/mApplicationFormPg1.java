package UI.Controllers;

import Datatypes.Form;
import Datatypes.Manufacturer;
import Datatypes.ProgressBar;
import Managers.*;
import UI.MultiThreadWaitFor;
import UI.callableFunction;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.lang.System;

import javafx.scene.paint.Color;

import java.io.IOException;

/**
 * @author Amrit Parmanand & Elizabeth Del Monaco
 * @version It 2
 * Controller for mApplicationFormPg1 of UI
 */
public class mApplicationFormPg1 {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private Label saveDraftMessage;
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
    @FXML private JFXButton saveDraft;

    public mApplicationFormPg1(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @SuppressWarnings("Duplicates") @FXML public void initialize(){

        Form form = cacheM.getForm();
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
        }
        else if (form.getBeerWineSpirit() == "SPIRITS") {
            isWine = false;
            isSpirit = true;
            isMalt = false;
        }
        else if (form.getBeerWineSpirit() == "BEER") {
            isWine = false;
            isSpirit = false;
            isMalt = true;
        }
        if(form.getRepID() != 0)
            repID.setText(Integer.toString(form.getRepID()));
        else
                repID.setText(Integer.toString(manAcc.getRepID()));
        brewerNO.setText(form.getBrewerNumber());
        domestic.setSelected(isDomestic);
        imported.setSelected(isImported);
        serialNumber.setText(form.getSerialNumber());
        wine.setSelected(isWine);
        distilled.setSelected(isSpirit);
        malt.setSelected(isMalt);
        if(!form.getBrandName().equals(""))
            brandName.setText(form.getBrandName());
        else
            brandName.setText(manAcc.getCompanyName());
        fancifulName.setText(form.getFancifulName());
        wine2.setSelected(isWine);
        spirits2.setSelected(isSpirit);
        beer2.setSelected(isMalt);
        alcoholPercentage.setText(form.getAlcoholPercent());
        phLevel.setText(form.getpHLevel());
        vintageYear.setText(form.getVintageYear());
    }


    @FXML public void saveDraft(){
        Form form = cacheM.getForm();

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

        cacheM.setForm(form);

        System.out.println("Pg1 saved!");
    }

//    /**
//     * The multi-thread function
//     * Saves draft every 5 seconds
//     */
//    callableFunction cf = new callableFunction() {
//        @Override
//        @SuppressWarnings("Duplicates")
//        public void call() {
//            if(repID != null && brewerNO != null && serialNumber != null && brandName != null && fancifulName != null &&
//                    alcoholPercentage != null && wine2 != null && spirits2 != null && beer2 != null && wine != null &&
//                    distilled != null && malt != null && domestic != null && imported != null){
//                saveDraft();
//            }
//            else{
//                System.out.println("null detected!");
//            }
//        }
//    };
//
//    MultiThreadWaitFor multiThreadWaitFor = new MultiThreadWaitFor(5, cf);


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
        sceneM.changeScene(loader, new mApplicationFormPg2(sceneM, cacheM));
    }

    @FXML public void goToHomePage() throws IOException {
//        multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
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


}
