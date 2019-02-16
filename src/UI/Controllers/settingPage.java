package UI.Controllers;

import Datatypes.Setting;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * @author Percy
 * @version It3
 * Setting page that con be only accessed once
 */
public class settingPage {
    private SceneManager sceneM;
    private CacheManager cacheM;

    public settingPage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML private JFXRadioButton SQL;
    @FXML private JFXRadioButton Levenshtein;
    @FXML private JFXRadioButton Damerau;
    @FXML private JFXRadioButton hiddenScore;
    @FXML private JFXTextField formLimit;
    @FXML private JFXRadioButton CSV;

    @FXML public void confirm() throws IOException {
        // Initialize the instance of the Singleton class
        Setting object = Setting.getInstance();

        // Set fuzzy
        if(SQL.isSelected()){
            object.setFuzzy("SQL");
        }
        else if(Levenshtein.isSelected()){
            object.setFuzzy("Levenshtein");
        }
        else if(Damerau.isSelected()){
            object.setFuzzy("Damerau");
        }
        else{
            object.setFuzzy("hiddenScore");
        }

        // Set form limit
        if(formLimit.getText().isEmpty()){
            object.setFormLimit(5);
        }
        else{
            object.setFormLimit(Integer.parseInt(formLimit.getText()));
        }

        // Set download format
        if(CSV.isSelected()){
            object.setFormat("CSV");
        }

        System.out.println("Settings: ");
        cacheM.setFormLimit(object.getFormLimit());
        System.out.println("form limit: " + object.getFormLimit());
        cacheM.setFuzzy(object.getFuzzy());
        System.out.println("fuzzy algorithm: " + object.getFuzzy());
        cacheM.setFormat(object.getFormat());
        System.out.println("download format: " + object.getFormat());

        System.out.println("Settings completed!");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/startPage.fxml"));
        sceneM.changeScene(loader, new startPage(sceneM, cacheM));
    }
}
