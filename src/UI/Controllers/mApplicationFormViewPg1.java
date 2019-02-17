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
public class mApplicationFormViewPg1 {
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

    public mApplicationFormViewPg1(SceneManager sceneM, CacheManager cacheM, Form form) {
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
            case "IMPORTED":
                imported.setSelected(true);

        }
        switch(form.getProductType()){
            case "WINE":
                wine.setSelected(true);
            case "DISTILLED":
                distilled.setSelected(true);
            case "MALT":
                malt.setSelected(true);
        }
        switch(form.getBeerWineSpirit()){
            case "WINE":
                wine2.setSelected(true);
            case "SPIRITS":
                spirits2.setSelected(true);
            case "BEER":
                beer2.setSelected(true);
        }
        if(form.getRepID() != 0)
            repID.setText(Integer.toString(form.getRepID()));
        brewerNO.setText(form.getBrewerNumber());
        domestic.setDisable(true);
        imported.setDisable(true);
        serialNumber.setText(form.getSerialNumber());
        wine.setDisable(true);
        distilled.setDisable(true);
        malt.setDisable(true);
        brandName.setText(form.getBrandName());
        fancifulName.setText(form.getFancifulName());
        alcoholPercentage.setText(form.getAlcoholPercent());
        phLevel.setText(form.getpHLevel());
        vintageYear.setText(form.getVintageYear());
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }

    @FXML
    public void nextPage() throws IOException {
//        multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormViewPg2.fxml"));
        sceneM.changeScene(loader, new mApplicationFormViewPg2(sceneM, cacheM, form));
    }
    //PROGRESSBAR 1
    //GET THIS DATA FROM CACHE

    @FXML
    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));
    }


}

