package UI.Controllers;

import Datatypes.Alcy;
import Datatypes.Controller;
import Datatypes.Setting;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * @author Percy
 * @version It3
 * Setting page that con be only accessed once
 */
public class settingPage {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Controller controller;

    public settingPage(SceneManager sceneM, CacheManager cacheM, Controller controller) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.controller = controller;
    }

    @FXML private JFXRadioButton SQL;
    @FXML private JFXRadioButton Levenshtein;
    @FXML private JFXRadioButton Damerau;
    @FXML private JFXTextField formLimit;
    @FXML private JFXTextField delimiter;
    @FXML private JFXToggleButton enableAlcy;
    @FXML private ImageView alcyView;
    @FXML private Text alcyLabel;

    @FXML public void initialize(){
        Alcy alcy = cacheM.getAlcy();
        if(alcy.isDemonicSeance()){
            enableAlcy.setSelected(true);
        }
        alcy.summonAlcy(alcyView, alcyLabel);
        alcy.saySettings();
    }

    @FXML public void banishAlcy(){
        if(!enableAlcy.isSelected()){
            cacheM.getAlcy().saySettingsBye();
            cacheM.getAlcy().sad();
            cacheM.getAlcy().setDemonicSeance(false);
        }else {
            cacheM.getAlcy().saySettingsRelief();
            cacheM.getAlcy().happy();

        }
    }

    @FXML public void alcySad(){
        //on mouse exit he get sad
        if(!enableAlcy.isSelected()) {
            cacheM.getAlcy().saySettingsBye();
        }else {
            cacheM.getAlcy().saySettingsRelief();
            cacheM.getAlcy().happy();
        }
    }

    @FXML public void confirm() throws IOException {
        // Initialize the instance of the Singleton class
        Setting object = Setting.getInstance();
        cacheM.getAlcy().summonAlcy(alcyView, alcyLabel);

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
        if(delimiter.getText().isEmpty()){
            object.setFormat(',');
        }
        else{
            object.setFormat(delimiter.getText().charAt(0));
        }

        if(enableAlcy.isSelected()){
            object.setDemonicseance(true);
        }
        else {
            object.setDemonicseance(false);
        }

        System.out.println("Settings: ");
        cacheM.setFormLimit(object.getFormLimit());
        System.out.println("form limit: " + object.getFormLimit());
        cacheM.setFuzzy(object.getFuzzy());
        System.out.println("fuzzy algorithm: " + object.getFuzzy());
        cacheM.setFormat(object.getFormat());
        System.out.println("download format: " + object.getFormat());
        cacheM.getAlcy().setDemonicSeance(object.isDemonicseance());
        System.out.println("Demonic Seance: " + object.isDemonicseance());

        System.out.println("Settings completed!");
        System.out.println(sceneM.getLastScene().toString());
        try {
            controller.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }


        sceneM.backScene();
    }

    @FXML
    public void search() throws IOException {
        // Search
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void alcyHover(){
        cacheM.getAlcy().saySettingsHover();
    }
}
