package UI.Controllers;


import Datatypes.Agent;
import Datatypes.Comments;
import Datatypes.Form;
import Managers.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Clay Oshiro-Leavitt
 * @version It 2
 * Controller for aApplicationFormPg1 of UI
 */
public class aApplicationFormPg1 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;
    private Comments comments = new Comments();

    @FXML private JFXButton acceptForm;
    @FXML private JFXButton denyForm;
    @FXML private JFXButton saveDraft;
    @FXML private JFXButton logout;
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
        Q1Comment.setText(comments.getComment1());
        Q2Comment.setText(comments.getComment2());
        Q3Comment.setText(comments.getComment3());
        Q4Comment.setText(comments.getComment4());
        Q5Comment.setText(comments.getComment5());
        Q6Comment.setText(comments.getComment6());
        Q7Comment.setText(comments.getComment7());
        cacheM.setForm(form);

        System.out.println(form.getProductSource());
        switch(form.parseGarbage(form.getProductSource())){
            case "DOMESTIC":
                domestic.setSelected(true);
                domestic.setOpacity(1);
                domestic.setStyle(form.parseStyle(form.getProductSource()));
                System.out.println(form.parseStyle(form.getProductSource()));
                break;
            case "IMPORTED":
                imported.setSelected(true);
                imported.setOpacity(1);
                imported.setStyle(form.parseStyle(form.getProductSource()));
                System.out.println(form.parseStyle(form.getProductSource()));
                break;

        }

        System.out.println(form.getProductType());
        switch(form.parseGarbage(form.getProductType())){
            case "WINE":
                wine.setSelected(true);
                wine.setOpacity(1);
                wine.setStyle(form.parseStyle(form.getProductType()));
                break;
            case "DISTILLED":
                spirits.setSelected(true);
                spirits.setOpacity(1);
                spirits.setStyle(form.parseStyle(form.getProductType()));
                break;
            case "MALT":
                malt.setSelected(true);
                malt.setOpacity(1);
                malt.setStyle(form.parseStyle(form.getProductType()));
                break;
        }

        System.out.println(form.getBeerWineSpirit());
        switch(form.parseGarbage(form.getBeerWineSpirit())){
            case "WINE":
                wine2.setSelected(true);
                wine2.setOpacity(1);
                wine2.setStyle(form.parseStyle(form.getBeerWineSpirit()));
                break;
            case "SPIRITS":
                spirits2.setSelected(true);
                spirits2.setOpacity(1);
                spirits2.setStyle(form.parseStyle(form.getBeerWineSpirit()));
                break;
            case "BEER":
                beer2.setSelected(true);
                beer2.setOpacity(1);
                beer2.setStyle(form.parseStyle(form.getBeerWineSpirit()));
                break;
        }
      
        if(form.getRepID() != 0)
            repID.setText(Integer.toString(form.getRepID()));

        brewerNO.setText(form.parseGarbage(form.getBrewerNumber()));
        brewerNO.setStyle(form.parseStyle(form.getBrewerNumber()));

        domestic.setDisable(true);
        imported.setDisable(true);

        serialNO.setText(form.parseGarbage(form.getSerialNumber()));
        serialNO.setStyle(form.parseStyle(form.getSerialNumber()));

        wine.setDisable(true);
        spirits.setDisable(true);
        malt.setDisable(true);

        brand.setText(form.parseGarbage(form.getBrandName()));
        brand.setStyle(form.parseStyle(form.getBrandName()));

        fanciful.setText(form.parseGarbage(form.getFancifulName()));
        fanciful.setStyle(form.parseStyle(form.getFancifulName()));

        alcoholPercentage.setText(form.parseGarbage(form.getAlcoholPercent()));
        alcoholPercentage.setStyle(form.parseStyle(form.getAlcoholPercent()));

        if (wine.isSelected()) {
            phLevel.setText(form.parseGarbage(form.getpHLevel()));
            phLevel.setStyle(form.parseStyle(form.getpHLevel()));

            vintageYear.setText(form.parseGarbage(form.getVintageYear()));
            vintageYear.setStyle(form.parseStyle(form.getVintageYear()));
        }

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

    public aApplicationFormPg1(SceneManager sceneM, CacheManager cacheM, Form form, Comments comments) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
        this.comments = comments;
    }

    @FXML
    public void nextPage() throws IOException {

        comments.setComment1(Q1Comment.getText());
        comments.setComment2(Q2Comment.getText());
        comments.setComment3(Q3Comment.getText());
        comments.setComment4(Q4Comment.getText());
        comments.setComment5(Q5Comment.getText());
        comments.setComment6(Q6Comment.getText());
        comments.setComment7(Q7Comment.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormPg2.fxml"));
        sceneM.changeScene(loader, new aApplicationFormPg2(sceneM, cacheM, form,comments));

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
    public void denyForm() throws Exception {
        comments.setComment1(Q1Comment.getText());
        comments.setComment2(Q2Comment.getText());
        comments.setComment3(Q3Comment.getText());
        comments.setComment4(Q4Comment.getText());
        comments.setComment5(Q5Comment.getText());
        comments.setComment6(Q6Comment.getText());
        comments.setComment7(Q7Comment.getText());
        cacheM.getForm().setComments(comments);
        System.out.println(comments.generateComments());
        cacheM.denyForm(cacheM.getDbM().getConnection());
        goToHomePage();
    }

    @FXML public void passForm() throws IOException{
        cacheM.passForm(cacheM.getDbM().getConnection(),cacheM.getForm().getFormID(), receiver.getText());
        goToHomePage();
    }

    @FXML
    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));

    }

}

