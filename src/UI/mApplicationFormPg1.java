package UI;

import Datatypes.Form;
import Managers.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class mApplicationFormPg1 {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private Button next;
    @FXML private Button search;
    @FXML private Button back;
    @FXML private TextField repID;
    @FXML private TextField brewerNO;
    @FXML private RadioButton productSource;
    @FXML private RadioButton wine;
    @FXML private RadioButton distilled;
    @FXML private RadioButton malt;
    @FXML private TextField serialNumber;
    @FXML private TextField brandName;
    @FXML private TextField fancifulName;
    private int isDomestic = 0; // 1 if domestic, 0 if imported
    private int type = 0; // 0 if wine, 1 if distilled beverage, 2 if malt beverage


    public mApplicationFormPg1(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML
    public void nextPage() throws IOException {
        Form form = cacheM.getForm();

        // checks if domestic or imported
        if(productSource.isSelected() == true) {
            isDomestic = 1;
        }else{
            isDomestic = 0;
        }

        // checks if wine, distilled, or malt beverage
        if(wine.isSelected() == true){
            type = 0;
        } else if(distilled.isSelected() == true){
            type = 1;
        } else if(malt.isSelected() == true){
            type = 2;
        }
        form.setRepID(Integer.parseInt(repID.getText()));
        form.setBrewerNumber(Integer.parseInt(brewerNO.getText()));
        form.setProductSource(isDomestic);
        form.setSerialNumber(Integer.parseInt(serialNumber.getText()));
        form.setProductType(type);
        form.setBrandName(brandName.getText());
        form.setFancifulName(fancifulName.getText());

        cacheM.setForm(form);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg2.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg2(sceneM, cacheM));
    }

    @FXML
    public void searchPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void goToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }

}