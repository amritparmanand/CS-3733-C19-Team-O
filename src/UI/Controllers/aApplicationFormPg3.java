package UI.Controllers;


import Datatypes.Agent;
import Datatypes.Comments;
import Datatypes.Form;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Clay Oshiro-Leavitt & Elizabeth Del Monaco
 * @version It 2
 * Controller for aApplicationFormPg3 of UI
 */
public class aApplicationFormPg3 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;
    private Comments comments;

    public aApplicationFormPg3(SceneManager sceneM, CacheManager cacheM, Form form, Comments comments) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
        this.comments = comments;
    }


    @FXML private JFXButton acceptForm;
    @FXML private JFXButton denyForm;
    @FXML private JFXButton saveDraft;
    @FXML private JFXButton homePage;
    @FXML private JFXButton previous;
    @FXML private JFXButton next;
    @FXML private JFXButton search;
    @FXML private JFXButton logout;
    @FXML private JFXTextField typeOfApp;
    @FXML private JFXButton uploadImage;
    @FXML private JFXTextArea Q14Comment;
    @FXML private JFXTextArea Q15Comment;
    @FXML private JFXTextField onlyState;
    @FXML private JFXTextField ttbID;
    @FXML private JFXTextField bottleCapacity; //will be int, for future reference
    @FXML private JFXCheckBox certificateOfApproval;
    @FXML private JFXCheckBox certificateOfExemption;
    @FXML private JFXCheckBox DistinctiveLiquor;
    @FXML private JFXCheckBox resubmission;
    @FXML private ImageView imagePreview;
    @FXML private Label errorLabel;
    @FXML private JFXTextField receiver;


    @SuppressWarnings("Duplicates")
    public void initialize(){
        Q14Comment.setText(comments.getComment14());
        Q15Comment.setText(comments.getComment15());
        Form form = cacheM.getForm();

        certificateOfApproval.setSelected(form.getCertificateOfApproval());
        certificateOfExemption.setSelected(form.getCertificateOfExemption());
        DistinctiveLiquor.setSelected(form.getDistinctiveLiquor());
        resubmission.setSelected(form.getResubmission());
        certificateOfApproval.setDisable(true);
        certificateOfExemption.setDisable(true);
        DistinctiveLiquor.setDisable(true);

        onlyState.setText(form.parseGarbage(form.getOnlyState()));
        onlyState.setStyle(form.parseStyle(form.getOnlyState()));

        bottleCapacity.setText(form.parseGarbage(form.getBottleCapacity()));
        bottleCapacity.setStyle(form.parseStyle(form.getBottleCapacity()));
        System.out.println(form.parseStyle(form.getBottleCapacity()));

        resubmission.setDisable(true);

        if(form.getResubmission())
            ttbID.setText(String.valueOf(form.getTtbID()));

        // Deal with image
        if(form.getLabel().getLabelImage() != null){
            imagePreview.setImage(form.getLabel().getLabelImage());

            File image = form.getLabel().getLabelFile();
            if(form.getLabel().getLabelFile() == null){
                System.out.println("label file is null");
            }else{
                System.out.println("label file isn't null");
                System.out.println(image.getPath());
            }

            File imageFile = new File(image.getPath());
            ITesseract instance = new Tesseract();
            instance.setDatapath("src\\OCR\\Tess4J\\tessdata");
            instance.setLanguage("eng");

            try {
                String result = instance.doOCR(imageFile);
                System.out.println(result);
            } catch (TesseractException e) {
                System.err.println(e.getMessage());
            }
        }

    }
    @FXML
    public void search() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void back() throws IOException {
        comments.setComment14(Q14Comment.getText());
        comments.setComment15(Q15Comment.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormPg2.fxml"));
        sceneM.changeScene(loader, new aApplicationFormPg2(sceneM, cacheM, form,comments));
    }
    @FXML
    public void nextPage() throws IOException {
        comments.setComment14(Q14Comment.getText());
        comments.setComment15(Q15Comment.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormPg4.fxml"));
        sceneM.changeScene(loader, new aApplicationFormPg4(sceneM, cacheM, form,comments));
    }
    @FXML
    public void goToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
        sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
    }
    @FXML
    public void acceptForm() throws IOException {
        cacheM.approveForm(cacheM.getDbM().getConnection());
        Agent A = (Agent) cacheM.getAcct();
        A.approveOrDeny(form);
        goToHomePage();
    }


    @FXML
    public void denyForm() throws Exception {
        comments.setComment14(Q14Comment.getText());
        comments.setComment15(Q15Comment.getText());
        cacheM.getForm().setComments(comments);
        System.out.println(comments.generateComments());
        cacheM.denyForm(cacheM.getDbM().getConnection());
        Agent A = (Agent) cacheM.getAcct();
        A.approveOrDeny(form);
        goToHomePage();
    }

    @FXML public void passForm() throws IOException{
        cacheM.passForm(cacheM.getDbM().getConnection(),cacheM.getForm().getFormID(), receiver.getText());
        Agent A = (Agent) cacheM.getAcct();
        A.pass(form);
        goToHomePage();
    }

    @FXML
    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));

    }

}
