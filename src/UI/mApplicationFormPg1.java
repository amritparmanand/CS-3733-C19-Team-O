package UI;

import Datatypes.Form;
import Datatypes.ProgressBar;
import Managers.*;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
/**
 * @author Amrit Parmanand & Elizabeth Del Monaco
 * @version It 2
 * Controller for mApplicationFormPg1 of UI
 */
public class mApplicationFormPg1{
    private SceneManager sceneM;
    private CacheManager cacheM;

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

    public mApplicationFormPg1(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML public void saveDraft(){
        Form form = cacheM.getForm();

        // checks if domestic or imported
        if (domestic.isSelected() || imported.isSelected()) {
            if(domestic.isSelected()) {
                form.setProductSource("domestic");
            }
            else if(imported.isSelected()){
                form.setProductSource("imported");
            }
        }

        // checks if wine, distilled, or malt beverage
        if (wine.isSelected() || distilled.isSelected() || malt.isSelected()) {
            if(wine.isSelected()){
                form.setProductType("wine");
            }
            else if(distilled.isSelected()){
                form.setProductType("distilled");
            }
            else if(malt.isSelected()) {
                form.setProductType("malt");
            }
        }

        int type2 = 0;
        if (wine2.isSelected() || spirits2.isSelected() || beer2.isSelected()) {
            if(wine2.isSelected())
                type2 = 0;
            else if(spirits2.isSelected())
                type2 = 1;
            else if(beer2.isSelected())
                type2 = 2;
            form.setBeerWineSpirit(type2);
            if(type2 == 0) {
                form.setpHLevel(Double.parseDouble(phLevel.getText()));
                form.setVintageYear(Integer.parseInt(vintageYear.getText()));
            }else{
                form.setpHLevel(0);
                form.setVintageYear(0);
            }
        }

        if (!repID.getText().isEmpty()){
            form.setRepID(Integer.parseInt(repID.getText()));
        }
        if (!brewerNO.getText().isEmpty()) {
            form.setBrewerNumber(Integer.parseInt(brewerNO.getText()));
        }
        if (!serialNumber.getText().isEmpty()) {
            form.setSerialNumber(Integer.parseInt(serialNumber.getText()));
        }
        if (!brandName.getText().isEmpty()) {
            form.setBrandName(brandName.getText());
        }
        if (!fancifulName.getText().isEmpty()) {
            form.setFancifulName(fancifulName.getText());
        }
        if (!alcoholPercentage.getText().isEmpty()) {
            form.setAlcoholPercent(Double.parseDouble(alcoholPercentage.getText()));
        }

        cacheM.setForm(form);

        System.out.println("Pg1 saved!");
    }

    callableFunction cf = new callableFunction() {
        @Override
        @FXML
        public void call() {
            Form form = cacheM.getForm();
            System.out.println(form.getRepID());
            cacheM.setForm(form);
            System.out.println("hi");
        }
    };

    @FXML
    MultiThreadWaitFor multiThreadWaitFor = new MultiThreadWaitFor(3, cf);

    @FXML private VBox phVBox;
    @FXML private VBox vintageVBox;
    @FXML public void hideWineFields(){
        phVBox.setVisible(false);
        vintageVBox.setVisible(false);
    }
    @FXML public void showWineFields(){
        phVBox.setVisible(true);
        vintageVBox.setVisible(true);
    }

    @FXML public void nextPage() throws IOException {
        saveDraft();
        multiThreadWaitFor.onShutDown();
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

    //PROGRESSBAR 1
    //GET THIS DATA FROM CACHE
    @FXML public void updateProgressBar(int questions1){
        ProgressBar progressBar1 = new ProgressBar();
        progressBar1.updateProgressBar1(questions1);
    }
}
