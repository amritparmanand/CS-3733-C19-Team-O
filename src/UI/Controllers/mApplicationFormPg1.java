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

        //Form form = cacheM.getForm();
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
        brewerNO.setText(form.getBrewerNumber());
        domestic.setSelected(isDomestic);
        imported.setSelected(isImported);
        serialNumber.setText(form.getSerialNumber());
        wine.setSelected(isWine);
        distilled.setSelected(isSpirit);
        malt.setSelected(isMalt);
        if (!form.getBrandName().equals(""))
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


    @FXML
    public void saveDraft() {
//        Form form = cacheM.getForm();

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
                form.setpHLevel(phLevel.getText());
                form.setVintageYear(vintageYear.getText());
            } else {
                form.setpHLevel(null);
                form.setVintageYear(null);
            }
        }

        if (!repID.getText().isEmpty()) {
            form.setRepID(Integer.parseInt(repID.getText()));
        }
        if (!brewerNO.getText().isEmpty()) {
            form.setBrewerNumber(brewerNO.getText());
        }
        if (!serialNumber.getText().isEmpty()) {
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

        if (form.getTtbID() != 0) {
            checkDiff();
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
        String style = "-fx-background-color: #94BDFF;";
        if (brewerNO.getText() != form.getBrewerNumber()) {
            form.setBrewerNumber(brewerNO.getText() + style);
        }
        if (phLevel.getText() != form.getpHLevel()) {
            form.setpHLevel(phLevel.getText() + style);
        }
        if (vintageYear.getText() != form.getVintageYear()) {
            form.setVintageYear(vintageYear.getText() + style);
        }
        if (brandName.getText() != form.getBrandName()) {
            form.setBrandName(brandName.getText() + style);
        }
        if(alcoholPercentage.getText() != form.getAlcoholPercent())
        {
            form.setAlcoholPercent(alcoholPercentage.getText() + style);
        }
        //How do you handle this one
//        if (Integer.parseInt(repID.getText()) != form.getRepID()) {
//            form.setBrandName(brandName.getText() + style);
//        }

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


}
