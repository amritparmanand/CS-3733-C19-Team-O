package UI.Controllers;

import Datatypes.Form;
import Datatypes.LabelImage;
import Managers.*;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;

/**
 * @author Amrit Parmanand, Elizabeth Del Monaco, & Gabriel Entov
 * @version It 2
 * Controller for mApplicationFormPg3 of UI
 */
public class mApplicationFormPg3 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private LabelImage image = new LabelImage();
    @FXML private JFXTextField onlyState;
    @FXML private JFXTextField ttbID;
    @FXML private JFXTextField bottleCapacity; //will be int, for future reference
    @FXML private JFXCheckBox certificateOfApproval;
    @FXML private JFXCheckBox certificateOfExemption;
    @FXML private JFXCheckBox DistinctiveLiquor;
    @FXML private JFXCheckBox resubmission;
    @FXML private ImageView imagePreview;
    @FXML private Label errorLabel;

    public mApplicationFormPg3(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML public void initialize() {
        Form form = cacheM.getForm();
        if(form.getCertificateOfApproval() == null)
            certificateOfApproval.setSelected(false);
        else if(form.getCertificateOfApproval())
            certificateOfApproval.setSelected(true);
        if(form.getCertificateOfExemption() == null)
            certificateOfExemption.setSelected(false);
        else if(form.getCertificateOfExemption())
            certificateOfExemption.setSelected(true);
        if(form.getDistinctiveLiquor() == null)
            DistinctiveLiquor.setSelected(false);
        else if(form.getDistinctiveLiquor())
            DistinctiveLiquor.setSelected(true);
        if(form.getResubmission() == null)
            resubmission.setSelected(false);
        else if(form.getResubmission())
            resubmission.setSelected(true);
        validateStateField();
        validateBottleCapacity();
        validateTTBID();
//        if(!(form.getLabel().getLabelImage() == null))
//            imagePreview.setImage(form.getLabel().getLabelImage());
    }


    @FXML public void validateStateField() {
        if(!certificateOfExemption.isSelected()) {
            onlyState.setText("");
            onlyState.setDisable(true);
        }else {
            onlyState.setDisable(false);
        }
    }
    @FXML public void validateBottleCapacity() {
        if(!certificateOfExemption.isSelected()) {
            onlyState.setText("");
            onlyState.setDisable(true);
        }else {
            onlyState.setDisable(false);
        }
    }
    @FXML public void validateTTBID() {
        if(!resubmission.isSelected()) {
            ttbID.setText("");
            ttbID.setDisable(true);
        }else {
            ttbID.setDisable(false);
        }
    }

    @FXML public boolean saveDraft(){
        if(!certificateOfExemption.isSelected() && !certificateOfApproval.isSelected() &&
                !DistinctiveLiquor.isSelected() && !resubmission.isSelected()) {
            errorLabel.setText("Please select a type of application.");
            return false;
        }
        Form form = cacheM.getForm();

        form.setCertificateOfApproval(certificateOfApproval.isSelected());
        form.setCertificateOfExemption(certificateOfExemption.isSelected());
        if(certificateOfExemption.isSelected()) {
            form.setOnlyState(onlyState.getText());
        }else {
            form.setOnlyState(null);
        }
        form.setDistinctiveLiquor(DistinctiveLiquor.isSelected());
        form.setResubmission(resubmission.isSelected());
        if(!resubmission.isSelected())
            form.setTtbID(0);
        else
            form.setTtbID(Integer.parseInt(ttbID.getText()));
        form.setBottleCapacity(bottleCapacity.getText());
        form.setLabel(image);
        errorLabel.setText(" ");
        cacheM.setForm(form);
        return true;
    }

    @FXML public void nextPage() throws IOException {
        if(saveDraft()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg4.fxml"));
            sceneM.changeScene(loader, new mApplicationFormPg4(sceneM, cacheM));
        }
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
    public void uploadImage(){
        image.getFile();
        imagePreview.setImage(image.getLabelImage());
    }

}