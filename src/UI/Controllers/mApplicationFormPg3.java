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
    private Form form;
    String style = "-fx-background-color: #94BDFF;";

    @FXML private JFXTextField onlyState;
    @FXML private JFXTextField ttbID;
    @FXML private JFXTextField bottleCapacity; //will be int, for future reference
    @FXML private JFXCheckBox certificateOfApproval;
    @FXML private JFXCheckBox certificateOfExemption;
    @FXML private JFXCheckBox DistinctiveLiquor;
    @FXML private JFXCheckBox resubmission;
    @FXML private ImageView imagePreview;
    @FXML private Label errorLabel;

    public mApplicationFormPg3(SceneManager sceneM, CacheManager cacheM,Form form) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
    }
    public mApplicationFormPg3(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = cacheM.getForm();
    }

    @FXML public void initialize() {
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
    }


    @FXML public void validateStateField() {
        if(!certificateOfExemption.isSelected()) {
            onlyState.setText("");
            onlyState.setDisable(true);
            onlyState.setText(form.parseGarbage(form.getOnlyState()));
        }else {
            onlyState.setDisable(false);
        }
    }
    @FXML public void validateBottleCapacity() {
        if(!DistinctiveLiquor.isSelected()) {
            bottleCapacity.setText("");
            bottleCapacity.setDisable(true);
            bottleCapacity.setText(form.parseGarbage(form.getBottleCapacity()));
        }else{
            bottleCapacity.setDisable(false);
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
        if (form.getTtbID() != 0) {
            checkDiff();
        }

        if (onlyState!= null && ttbID!= null && bottleCapacity!= null && certificateOfApproval!= null
                &&certificateOfExemption!= null && DistinctiveLiquor!= null
                && resubmission!= null && imagePreview!= null) {
            if(!certificateOfExemption.isSelected() && !certificateOfApproval.isSelected() &&
                    !DistinctiveLiquor.isSelected() && !resubmission.isSelected()) {
                errorLabel.setText("Please select a type of application.");
                return false;
            }
           // Form form = cacheM.getForm();

            form.setCertificateOfApproval(certificateOfApproval.isSelected());
            form.setCertificateOfExemption(certificateOfExemption.isSelected());
            if(certificateOfExemption.isSelected()) {
                if (!onlyState.getText().isEmpty()) {
                    if(!form.getOnlyState().contains(style)){
                        form.setOnlyState(onlyState.getText());
                    }
                }
            }else {
                form.setOnlyState(null);
            }
            form.setDistinctiveLiquor(DistinctiveLiquor.isSelected());
            if(DistinctiveLiquor.isSelected()) {
                if (!bottleCapacity.getText().isEmpty()) {
                    if(!form.getBottleCapacity().contains(style)){
                        form.setBottleCapacity(bottleCapacity.getText());
                    }
                }
            }else {
                form.setOnlyState(null);
            }
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
        else{
            return false;
        }

    }

    public void checkDiff() {

        //@FXML private JFXTextField onlyState;
        String style = "-fx-background-color: #94BDFF;";
        if (!onlyState.getText().equals(form.getOnlyState()) && !onlyState.getText().contains(style)) {
            form.setPrintName(onlyState.getText() + style);
        }
        if (!bottleCapacity.getText().equals(form.getBottleCapacity()) && !bottleCapacity.getText().contains(style)) {
            form.setPrintName(bottleCapacity.getText() + style);
        }

    }

    @FXML public void nextPage() throws IOException {
        saveDraft();
//        multiThreadWaitFor.onShutDown();
        System.out.println("on to page 4");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg4.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg4(sceneM, cacheM, form));
    }
    @FXML public void previousPage() throws IOException {
//        multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg2.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg2(sceneM, cacheM, form));
    }
    @FXML public void goToHomePage() throws IOException {
//        multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }

    @FXML
    public void uploadImage(){
        image.getFile();
        imagePreview.setImage(image.getLabelImage());
    }

    @FXML
    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));
    }

}