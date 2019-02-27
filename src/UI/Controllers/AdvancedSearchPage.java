package UI.Controllers;

import Datatypes.SearchResult;
import Datatypes.StageContainingScene;
import Managers.CacheManager;
import Managers.SceneManager;
import Managers.SearchManager;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AdvancedSearchPage extends StageContainingScene {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private SearchManager searchM;

    @FXML
    JFXDatePicker fromDate;
    @FXML
    JFXDatePicker toDate;
    @FXML
    JFXRadioButton brandName;
    @FXML
    JFXRadioButton fancyName;
    @FXML
    JFXRadioButton either;
    @FXML
    JFXCheckBox receivedCode000;
    @FXML
    JFXCheckBox receivedCode001;
    @FXML
    JFXCheckBox receivedCode002;
    @FXML
    JFXCheckBox receivedCode003;
    @FXML
    JFXTextField fromTTB;
    @FXML
    JFXTextField toTTB;
    @FXML
    JFXTextField fromSerial;
    @FXML
    JFXTextField toSerial;
    @FXML
    JFXTextField brewerNumber;


    public AdvancedSearchPage(SceneManager sceneM, CacheManager cacheM, SearchManager searchM) {
        super();
        this.searchM = searchM;
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML
    public void initialize() {
        //populate Lmao
        if(searchM.exists(searchM.getBrandFancyEither())) {
            switch (searchM.getBrandFancyEither()) {
                case 1:
                    this.brandName.setSelected(true);
                    break;
                case 2:
                    this.fancyName.setSelected(true);
                    break;
                case 3:
                    this.either.setSelected(true);
                    break;
                default:
                    break;
            }
        }



        if(searchM.exists(searchM.getFromDate()))
            this.fromDate.setValue(searchM.getFromDate());
        if(searchM.exists(searchM.getToDate()))
            this.toDate.setValue(searchM.getToDate());

        if(searchM.exists(searchM.getFromTTB()))
            this.fromTTB.setText(searchM.getFromTTB());
        if(searchM.exists(searchM.getToTTB()))
            this.toTTB.setText(searchM.getToTTB());

        if(searchM.exists(searchM.getFromSerial()))
            this.fromSerial.setText(searchM.getFromSerial());
        if(searchM.exists(searchM.getToSerial()))
            this.toSerial.setText(searchM.getToSerial());

        if(searchM.exists(searchM.getBrewerNumber()))
            this.brewerNumber.setText(searchM.getBrewerNumber());
    }

    @FXML
    public void applySearchSettings() {
        Integer tempCode = 0;
        if (receivedCode000.isSelected())
            tempCode += 1;
        if (receivedCode001.isSelected())
            tempCode += 10;
        if (receivedCode002.isSelected())
            tempCode += 100;
        if (receivedCode003.isSelected())
            tempCode += 1000;

        Integer tempBrandFancyEither = null;
        if(brandName.isSelected()) tempBrandFancyEither = 1;
        if(fancyName.isSelected()) tempBrandFancyEither = 2;
        if(either.isSelected()) tempBrandFancyEither = 3;

        this.searchM.setFromDate(fromDate.getValue());
        this.searchM.setToDate(toDate.getValue());
        this.searchM.setBrandFancyEither(tempBrandFancyEither);
        this.searchM.setOriginState(null);
        this.searchM.setReceivedCode(tempCode);
        this.searchM.setFromTTB(getNullableText(fromTTB));
        this.searchM.setToTTB(getNullableText(toTTB));
        this.searchM.setFromSerial(getNullableText(fromSerial));
        this.searchM.setToSerial(getNullableText(toSerial));
        this.searchM.setBrewerNumber(getNullableText(brewerNumber));
        this.searchM.isActive = true;

        super.getStage().close();
    }

    public String getNullableText(JFXTextField tf) {
        if (tf.getText().equals(""))
            return null;
        else
            return tf.getText();
    }

    public SearchManager transferSearchManager() {
        return this.searchM;
    }

    @FXML
    public void clearSettings() {
        System.out.println("ADSD");
        searchM.setFromDate(null);
        searchM.setToDate(null);
        searchM.setBrandFancyEither(null);
        searchM.setOriginState(null);
        searchM.setReceivedCode(null);
        searchM.setFromTTB(null);
        searchM.setToTTB(null);
        searchM.setFromSerial(null);
        searchM.setToSerial(null);
        searchM.setBrewerNumber(null);
        searchM.isActive = true;

        super.getStage().close();
    }
}
