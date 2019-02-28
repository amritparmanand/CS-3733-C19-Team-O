package UI.Controllers;

import Datatypes.*;
import Managers.*;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * @author Amrit Parmanand, Elizabeth Del Monaco, & Gabriel Entov
 * @version It 2
 * Controller for mApplicationFormPg3 of UI
 */
public class aApplicationFormViewPg3 extends Controller {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private LabelImage image = new LabelImage();
    private Form form;
    @FXML private JFXTextField onlyState;
    @FXML private JFXTextField ttbID;
    @FXML private JFXTextField bottleCapacity; //will be int, for future reference
    @FXML private JFXCheckBox certificateOfApproval;
    @FXML private JFXCheckBox certificateOfExemption;
    @FXML private JFXCheckBox DistinctiveLiquor;
    @FXML private JFXCheckBox resubmission;
    @FXML private ImageView imagePreview;
    @FXML private Label errorLabel;
    @FXML private ImageView alcyView;
    @FXML private Text alcyLabel;
    private settingPage settingPage;

    public aApplicationFormViewPg3(SceneManager sceneM, CacheManager cacheM, Form form) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
    }

    @SuppressWarnings("Duplicates")
    @FXML public void initialize() {
        Alcy alcy = cacheM.getAlcy();
        alcy.summonAlcy(alcyView, alcyLabel);
        alcy.sayAForm();

        certificateOfApproval.setSelected(form.getCertificateOfApproval());
        certificateOfExemption.setSelected(form.getCertificateOfExemption());
        DistinctiveLiquor.setSelected(form.getDistinctiveLiquor());
        resubmission.setSelected(form.getResubmission());
        certificateOfApproval.setDisable(true);
        certificateOfExemption.setDisable(true);
        DistinctiveLiquor.setDisable(true);
        resubmission.setDisable(true);
        validateStateField();
        validateBottleCapacity();
        validateTTBID();
        onlyState.setText(form.getOnlyState());
        if(form.getTtbID() != 0)
            ttbID.setText(String.valueOf(form.getTtbID()));
        bottleCapacity.setText(form.getBottleCapacity());
        if(form.getLabel().getLabelImage() != null)
            imagePreview.setImage(form.getLabel().getLabelImage());
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

    @FXML public void goToHomePage() throws IOException {
//        multiThreadWaitFor.onShutDown();
        cacheM.setForm(new Form());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
        sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
    }

    @FXML public void nextPage() throws IOException {
//        multiThreadWaitFor.onShutDown();
        System.out.println("on to page 4");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormViewPg4.fxml"));
        sceneM.changeScene(loader, new aApplicationFormViewPg4(sceneM, cacheM,form));
    }
    @FXML public void previousPage() throws IOException {
//        multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormViewPg2.fxml"));
        sceneM.changeScene(loader, new aApplicationFormViewPg2(sceneM, cacheM,form));
    }

    @FXML
    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));
    }

    @FXML public void savePDF() throws IOException {
        PDF pdf = new PDF();
        pdf.savePDF(cacheM.getForm());
    }
    @FXML public void settings() throws IOException {
        settingPage = new settingPage(sceneM, cacheM);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/settingPage.fxml"));
        sceneM.popWindowLoader(loader, settingPage, "Setting");
    }
}