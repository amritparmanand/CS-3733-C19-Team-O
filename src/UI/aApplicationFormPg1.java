package UI;


import Datatypes.Form;
import Managers.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class aApplicationFormPg1 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;

    @FXML private Button next;
    @FXML private Button search;
    @FXML private Button back;
    @FXML private TextField repID;
    @FXML private TextField brewerNO;
    @FXML private RadioButton domestic;
    @FXML private RadioButton imported;
    @FXML private RadioButton wine;
    @FXML private RadioButton spirits;
    @FXML private RadioButton malt;
    @FXML private TextField serialNO;
    @FXML private TextField brand;
    @FXML private TextField fanciful;


    public void initialize(Form form){

        boolean isDomestic = false;
        boolean isImported = false;
        boolean isWine = false;
        boolean isSpirit = false;
        boolean isMalt = false;
        if(form.getProductSource() == 1){
            isDomestic = true;
            isImported = false;
        }else{
            isDomestic = false;
            isImported = true;
        }
        if(form.getBeerWineSpirit() == 0){
            isWine = true;
            isSpirit = false;
            isMalt = false;
        }else if(form.getBeerWineSpirit() == 1){
            isWine = false;
            isSpirit = true;
            isMalt = false;
        }else if(form.getBeerWineSpirit() == 2){
            isWine = false;
            isSpirit = false;
            isMalt = true;
        }
        System.out.println("starting");
        brewerNO.setText(Integer.toString(form.getBrewerNumber()));
        brewerNO.setEditable(false);
        domestic.setSelected(isDomestic);
        imported.setSelected(isImported);
        serialNO.setText(Integer.toString(form.getSerialNumber()));
        serialNO.setEditable(false);
        wine.setSelected(isWine);
        spirits.setSelected(isSpirit);
        malt.setSelected(isMalt);
        brand.setText(form.getBrandName());
        brand.setEditable(false);
        fanciful.setText(form.getFancifulName());
        fanciful.setEditable(false);
        System.out.println("filled in info page 1");
    }


    public aApplicationFormPg1(SceneManager sceneM, CacheManager cacheM, Form form) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
    }

    @FXML
    public void nextPage() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormPg2.fxml"));
        sceneM.changeScene(loader, new aApplicationFormPg2(sceneM, cacheM, form));

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

    @FXML
    public void acceptForm() throws IOException {
        form.approve(cacheM.getDbM().getConnection());
    }


    @FXML
    public void denyForm() throws IOException {
        form.deny(cacheM.getDbM().getConnection());
    }

}

