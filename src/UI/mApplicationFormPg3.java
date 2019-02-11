package UI;

import Datatypes.Form;
import Datatypes.LabelImage;
import Managers.*;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
/**
 * @author Amrit Parmanand, Elizabeth Del Monaco, & Gabriel Entov
 * @version It 2
 * Controller for mApplicationFormPg3 of UI
 */
public class mApplicationFormPg3 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private LabelImage image = new LabelImage();
    @FXML private Button next;
    @FXML private Button previous;
    @FXML private Button search;
    @FXML private Button back;
    @FXML private Button uploadImageButton;
    @FXML private JFXTextField stateAbb;
    @FXML private JFXTextField ttbID;
    @FXML private JFXTextField bottleCapacity; //will be int, for future reference
    @FXML private CheckBox certLabelApp;
    @FXML private CheckBox certExempLabApp;
    @FXML private CheckBox distinctLiqBottApp;
    @FXML private CheckBox resubmitAfterRej;
    @FXML private ImageView imagePreview;

    public mApplicationFormPg3(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML public void initialize() {
        Form form = cacheM.getForm();

    }




    @FXML public void saveDraft(){
        Form form = cacheM.getForm();

        form.setCertificateOfApproval(certLabelApp.isSelected());
        form.setCertificateOfExemption(certExempLabApp.isSelected());
        form.setOnlyState(stateAbb.getText());
        form.setDistinctiveLiquor(distinctLiqBottApp.isSelected());
        form.setResubmission(resubmitAfterRej.isSelected());
        form.setTtbID(Integer.parseInt(ttbID.getText()));
        form.setBottleCapacity(bottleCapacity.getText());
        form.setLabelImage(image.getFile());

        cacheM.setForm(form);
    }

    @FXML public void nextPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg4.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg4(sceneM, cacheM));
    }
    @FXML public void previousPage() throws IOException {
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

    @FXML
    public void uploadImage(ActionEvent event){
        image.getFile();
        imagePreview.setImage(image.getLabelImage());
    }

}