package UI;

import Datatypes.Form;
import Datatypes.LabelImage;
import Managers.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.math.BigInteger;

public class mApplicationFormPg3 {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private Button next;
    @FXML private Button previous;
    @FXML private Button search;
    @FXML private Button back;
    @FXML private Button uploadImageButton;
    @FXML private TextField applicationType;
    @FXML private TextField stateAbb;
    @FXML private TextField ttbID;
    @FXML private TextField bottleCap; //will be int, for future reference
    @FXML private CheckBox certLabelApp;
    @FXML private CheckBox certExempLabApp;
    @FXML private CheckBox distinctLiqBottApp;
    @FXML private CheckBox resubmitAfterRej;
    @FXML private ImageView imagePreview;

    public mApplicationFormPg3(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

//    @FXML public void saveDraft{
//        Form form = cacheM.getForm();
//
//        form.setTypeOfApplication(applicationType.getText());
//        form.setCertificateOfApproval(certLabelApp.isSelected());
//        form.setCertificateOfExemptionFromLabelApproval(certExempLabApp.isSelected());
//        form.setForSaleOnlyIn(stateAbb.getText());
//        form.distinctiveLiquorBottleApproval(distinctLiqBottApp.isSelected());
//        form.setResubmissionAfterRejection(resubmitAfterRej.isSelected());
//        form.setTTBID(Integer.parseInt(ttbID.getText()));
//        form.setImage(uploadedImage);
//
//        cacheM.setForm(form);
//    }

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
        LabelImage image = new LabelImage();
        imagePreview.setImage(image.getImageFile());
    }

}