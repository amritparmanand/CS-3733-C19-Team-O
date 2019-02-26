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
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.lang.System;

import javafx.scene.paint.Color;

import java.io.IOException;
import java.time.Year;
import java.util.Calendar;

/**
 * @author Amrit Parmanand & Elizabeth Del Monaco
 * @version It 2
 * Controller for mApplicationFormPg1 of UI
 */
public class aApplicationFormViewPg1 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;

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
    @FXML private Label serialMessage;
    @FXML private JFXButton savePDF;

    public aApplicationFormViewPg1(SceneManager sceneM, CacheManager cacheM, Form form) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
    }

    @SuppressWarnings("Duplicates")
    @FXML public void initialize(){
        cacheM.setForm(form);
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
        repID.setEditable(false);
        brewerNO.setText(form.getBrewerNumber());
        brewerNO.setEditable(false);
        domestic.setDisable(true);
        imported.setDisable(true);
        serialNumber.setText(form.getSerialNumber());
        serialNumber.setEditable(false);
        wine.setDisable(true);
        distilled.setDisable(true);
        malt.setDisable(true);
        brandName.setText(form.getBrandName());
        brandName.setEditable(false);
        fancifulName.setText(form.getFancifulName());
        fancifulName.setEditable(false);
        alcoholPercentage.setText(form.getAlcoholPercent());
        alcoholPercentage.setEditable(false);
        phLevel.setText(form.getpHLevel());
        phLevel.setEditable(false);
        vintageYear.setText(form.getVintageYear());
        vintageYear.setEditable(false);
        wine2.setDisable(true);
        spirits2.setDisable(true);
        beer2.setDisable(true);
        System.out.println("starting");
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

    @FXML public void goToHomePage() throws IOException {
//        multiThreadWaitFor.onShutDown();
        cacheM.setForm(new Form());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
        sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
    }

    @FXML
    public void nextPage() throws IOException {
//        multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormViewPg2.fxml"));
        sceneM.changeScene(loader, new aApplicationFormViewPg2(sceneM, cacheM, form));
    }
    //PROGRESSBAR 1
    //GET THIS DATA FROM CACHE

    @FXML
    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));
    }

    @FXML public void savePDF() throws IOException {
        PDF pdf = new PDF();
        pdf.savePDF(cacheM.getForm());
    }

    @FXML public void settings() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/settingPage.fxml"));
        sceneM.changeScene(loader, new settingPage(sceneM, cacheM));
    }
}

