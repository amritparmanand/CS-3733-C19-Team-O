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
import com.jfoenix.controls.JFXButton;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;

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
    @FXML private JFXButton help;

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
        if(!DistinctiveLiquor.isSelected()) {
            bottleCapacity.setText("");
            bottleCapacity.setDisable(true);
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
        if (onlyState!= null && ttbID!= null && bottleCapacity!= null && certificateOfApproval!= null
                &&certificateOfExemption!= null && DistinctiveLiquor!= null
                && resubmission!= null && imagePreview!= null) {
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
        else{
            return false;
        }
    }

//    /**
//     * The multi-thread function
//     * Saves draft every 5 seconds
//     */
//    callableFunction cf = new callableFunction() {
//        @Override
//        @SuppressWarnings("Duplicates")
//        public void call() {
//            if(onlyState!= null && ttbID!= null && bottleCapacity!= null && certificateOfApproval!= null &&
//                    certificateOfExemption!= null && DistinctiveLiquor!= null && resubmission!= null &&
//                    imagePreview!= null && imagePreview!= null){
//                saveDraft();
//                System.out.println("Pg3 saved!");
//            }
//
//        }
//    };
//    MultiThreadWaitFor multiThreadWaitFor = new MultiThreadWaitFor(5, cf);

    /*
    @FXML public void nextPage() throws IOException {
        multiThreadWaitFor.onShutDown();
        if(saveDraft()) {
            System.out.println("on to page 4");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg4.fxml"));
            sceneM.changeScene(loader, new mApplicationFormPg4(sceneM, cacheM));
        }
    }
    */
    @FXML public void nextPage() throws IOException {
        saveDraft();
//        multiThreadWaitFor.onShutDown();
        System.out.println("on to page 4");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg4.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg4(sceneM, cacheM));
    }
    @FXML public void previousPage() throws IOException {
//        multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg2.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg2(sceneM, cacheM));
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

    public void help() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/helpViews/helpmAppMulti.fxml"));
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