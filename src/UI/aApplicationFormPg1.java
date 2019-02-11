package UI;


import Datatypes.Form;
import Managers.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * @author Clay Oshiro-Leavitt & Elizabeth Del Monaco
 * @version It 2
 * Controller for aApplicationFormPg1 of UI
 */
public class aApplicationFormPg1 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;

    @FXML private JFXButton acceptForm;
    @FXML private JFXButton denyForm;
    @FXML private JFXButton saveDraft;
    @FXML private JFXButton homePage;
    @FXML private JFXTextField repID;
    @FXML private JFXTextField brewerNO;
    @FXML private JFXRadioButton domestic;
    @FXML private JFXRadioButton imported;
    @FXML private JFXRadioButton wine;
    @FXML private JFXRadioButton spirits;
    @FXML private JFXRadioButton malt;
    @FXML private JFXTextField serialNO;
    @FXML private JFXTextField brand;
    @FXML private JFXTextField fanciful;
    @FXML private JFXTextArea Q1Comment;
    @FXML private JFXTextArea Q2Comment;
    @FXML private JFXTextArea Q3Comment;
    @FXML private JFXTextArea Q4Comment;
    @FXML private JFXTextArea Q5Comment;
    @FXML private JFXTextArea Q6Comment;
    @FXML private JFXTextArea Q7Comment;




    @FXML public void initialize(){
        Form form = this.form;

        boolean isDomestic = false;
        boolean isImported = false;
        boolean isWine = false;
        boolean isSpirit = false;
        boolean isMalt = false;
        if(form.getProductSource() == "DOMESTIC"){
            isDomestic = true;
            isImported = false;
        }else{
            isDomestic = false;
            isImported = true;
        }
        if(form.getBeerWineSpirit() == "WINE"){
            isWine = true;
            isSpirit = false;
            isMalt = false;
        }else if(form.getBeerWineSpirit() == "SPIRITS"){
            isWine = false;
            isSpirit = true;
            isMalt = false;
        }else if(form.getBeerWineSpirit() == "BEER"){
            isWine = false;
            isSpirit = false;
            isMalt = true;
        }
        System.out.println("starting");
        brewerNO.setText(form.getBrewerNumber());
        brewerNO.setEditable(false);
        domestic.setSelected(isDomestic);
        imported.setSelected(isImported);
        serialNO.setText(form.getSerialNumber());
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
        sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
    }

    @FXML
    public void acceptForm() throws IOException {
        form.approve(cacheM.getDbM().getConnection());
    }


    @FXML
    public void denyForm() throws IOException {
        form.deny(cacheM.getDbM().getConnection());
    }

    @FXML
    public void saveDraft() throws IOException{

    }

}

