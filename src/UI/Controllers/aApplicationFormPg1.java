package UI.Controllers;


import Datatypes.Agent;
import Datatypes.Form;
import Managers.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.sql.SQLException;

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
    @FXML private JFXButton logout;
    @FXML private JFXButton homePage;
    @FXML private JFXButton help;
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
    @FXML private JFXRadioButton wine2;
    @FXML private JFXRadioButton spirits2;
    @FXML private JFXRadioButton beer2;
    @FXML private JFXTextField alcoholPercentage;
    @FXML private JFXTextField phLevel;
    @FXML private JFXTextField vintageYear;
    @FXML private JFXTextArea Q1Comment;
    @FXML private JFXTextArea Q2Comment;
    @FXML private JFXTextArea Q3Comment;
    @FXML private JFXTextArea Q4Comment;
    @FXML private JFXTextArea Q5Comment;
    @FXML private JFXTextArea Q6Comment;
    @FXML private JFXTextArea Q7Comment;
    @FXML private JFXTextField receiver;
    @FXML private JFXTextField TTBID;

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
                spirits.setSelected(true);
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
        serialNO.setText(form.getSerialNumber());
        wine.setDisable(true);
        spirits.setDisable(true);
        malt.setDisable(true);
        brand.setText(form.getBrandName());
        fanciful.setText(form.getFancifulName());
        alcoholPercentage.setText(form.getAlcoholPercent());
        phLevel.setText(form.getpHLevel());
        vintageYear.setText(form.getVintageYear());
        wine2.setDisable(true);
        spirits2.setDisable(true);
        beer2.setDisable(true);
        System.out.println("starting");
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
        cacheM.approveForm(cacheM.getDbM().getConnection());
    }

    @FXML
    public void denyForm() throws IOException {
        cacheM.denyForm(cacheM.getDbM().getConnection());
    }

    @FXML public void passForm() throws IOException{
        cacheM.passForm(cacheM.getDbM().getConnection(),cacheM.getForm().getFormID(), Integer.parseInt(receiver.getText()));
        goToHomePage();
    }

    @FXML
    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));

    }
    public void help() throws IOException{
        helpBox.imageSeter("login");
        Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/helpBox.fxml"));
        helpPopWindow(root);
    }

    public void helpPopWindow(Parent root){
        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Help Window");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}

