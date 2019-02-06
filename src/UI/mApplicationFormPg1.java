package UI;

import Datatypes.Form;
import Managers.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class mApplicationFormPg1 {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private TextField repID;
    @FXML private TextField brewerNO;
    @FXML private RadioButton domestic;
    @FXML private RadioButton imported;
    @FXML private RadioButton wine;
    @FXML private RadioButton distilled;
    @FXML private RadioButton malt;
    @FXML private TextField serialNumber;
    @FXML private TextField brandName;
    @FXML private TextField fancifulName;
    @FXML private TextField alcoholPercentage;
    @FXML private TextField phLevel;
    @FXML private TextField vintageYear;
    @FXML private RadioButton wine2;
    @FXML private RadioButton spirits2;
    @FXML private RadioButton beer2;
    @FXML private VBox phVBox;
    @FXML private VBox vintageVBox;


    private int isDomestic = 0; // 1 if domestic, 0 if imported
    private int type = 0; // 0 if wine, 1 if distilled beverage, 2 if malt beverage
    private int type2 = 0;


    public mApplicationFormPg1(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML
    public void saveDraft(){
        Form form = cacheM.getForm();

        // checks if domestic or imported
        if(domestic.isSelected()) {
            isDomestic = 1;
        }else if(imported.isSelected()){
            isDomestic = 0;
        }
        // checks if wine, distilled, or malt beverage
        if(wine.isSelected())
            type = 0;
        else if(distilled.isSelected())
            type = 1;
        else if(malt.isSelected())
            type = 2;


        if(wine2.isSelected())
            type2 = 0;
        else if(spirits2.isSelected())
            type2 = 1;
        else if(beer2.isSelected())
            type2 = 2;


        form.setRepID(Integer.parseInt(repID.getText()));
        form.setBrewerNumber(Integer.parseInt(brewerNO.getText()));
        form.setProductSource(isDomestic);
        form.setSerialNumber(Integer.parseInt(serialNumber.getText()));
        form.setProductType(type);
        form.setBrandName(brandName.getText());
        form.setFancifulName(fancifulName.getText());
        form.setBeerWineSpirit(type2);
        form.setAlcoholPercent(Double.parseDouble(alcoholPercentage.getText()));
        if(type2 == 0) {
            form.setpHLevel(Double.parseDouble(phLevel.getText()));
            form.setVintageYear(Integer.parseInt(vintageYear.getText()));
        }else{
            form.setpHLevel(0);
            form.setVintageYear(0);
        }

        cacheM.setForm(form);

        System.out.println("worked 1");
    }
    @FXML public void hideWineFields(){
        phLevel.setEditable(false);
        phLevel.setPromptText("n/a");
        vintageYear.setEditable(false);
        vintageYear.setPromptText("n/a");
    }
    @FXML public void showWineFields(){
        phVBox.setVisible(true);
        vintageVBox.setVisible(true);
    }

    @FXML public void nextPage() throws IOException {
        saveDraft();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg2.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg2(sceneM, cacheM));
    }
    @FXML public void searchPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }
    @FXML public void goToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }

}
