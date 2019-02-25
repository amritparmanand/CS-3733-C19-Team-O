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
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Clay Oshiro-Leavitt & Percy & Gabe
 * @version It 2
 * Controller for aApplicationFormPg3 of UI
 */
public class aApplicationFormPg3 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;
    private Comments comments;
    boolean parsedImage = false;

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
    public void initialize() throws IOException {
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
        }

    }

    @FXML public void OCR() throws IOException {
        // Check label file
        if(form.getLabel().getLabelFile() == null){
            System.out.println("label file NO");
        }else{
            System.out.println("label file detected");
            System.out.println(form.getLabel().getLabelFile().getPath());
        }

        if(form.getLabel().getLabelImage() != null){
            // Convert to black and white
            BufferedImage bw = ImageIO.read(form.getLabel().getLabelFile());
            BufferedImage bw_result = new BufferedImage(bw.getWidth(), bw.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D graphic = bw_result.createGraphics();
            graphic.drawImage(bw, 0, 0, Color.WHITE, null);
            graphic.dispose();
            File output = new File("src/Tess4J/images/Black&White.jpg");
            ImageIO.write(bw_result, "jpg", output);

            // The Tesseract
            ITesseract instance = new Tesseract();
            instance.setDatapath("src/Tess4J.tessdata");
            instance.setLanguage("eng");
            while(!parsedImage) {
                try {
                    String result = instance.doOCR(output);
                    System.out.println(result);
                    parsedImage = true;
                    // Check for Bottle Capacity
                    if (!form.getBottleCapacity().isEmpty()) {
                        if (result.contains("ml")) {
                            System.out.println("bottle capacity DETECTED");
                            int position_ml = result.indexOf("ml");

                            // Initialize capacity
                            String capacity;
                            if (result.substring(position_ml - 1, position_ml).equals(" ")) {
                                capacity = result.substring(position_ml - 4, position_ml - 1);
                            } else {
                                capacity = result.substring(position_ml - 3, position_ml);
                            }

                            System.out.println(capacity);
                            System.out.println(form.parseGarbage(form.getBottleCapacity()));
                            if (form.parseGarbage(form.getBottleCapacity()).equals(capacity)) {
                                System.out.println("bottle capacity MATCHED");
                            } else {
                                System.out.println("bottle capacity NO MATCH");
                            }
                        } else if (result.contains("mL")) {
                            System.out.println("bottle capacity DETECTED");
                            int position_mL = result.indexOf("mL");

                            // Initialize capacity
                            String capacity;
                            if (result.substring(position_mL - 1, position_mL).equals(" ")) {
                                capacity = result.substring(position_mL - 4, position_mL - 1);
                            } else {
                                capacity = result.substring(position_mL - 3, position_mL);
                            }

                            System.out.println(capacity);
                            System.out.println(form.parseGarbage(form.getBottleCapacity()));
                            if (form.parseGarbage(form.getBottleCapacity()).equals(capacity)) {
                                System.out.println("bottle capacity MATCHED");
                            } else {
                                System.out.println("bottle capacity NO MATCH");
                            }
                        } else {
                            System.out.println("bottle capacity NOT DETECTED");
                        }
                        System.out.println("\n");
                    }

                    // Check for Alcohol Percentage
                    if (!form.getAlcoholPercent().isEmpty()) {
                        if (result.contains("%")) {
                            System.out.println("alcohol percentage DETECTED");
                            int position = result.indexOf("%");

                            String percentage1 = result.substring(position - 2, position);
                            System.out.println(percentage1);
                            String percentage2 = result.substring(position - 4, position);
                            System.out.println(percentage2);

                            System.out.println(form.parseGarbage(form.getAlcoholPercent()));
                            if (form.parseGarbage(form.getAlcoholPercent()).equals(percentage1)) {
                                System.out.println("alcohol percentage MATCHED");
                            } else if (form.parseGarbage(form.getAlcoholPercent()).equals(percentage2)) {
                                System.out.println("alcohol percentage MATCHED");
                            } else {
                                System.out.println("alcohol percentage NO MATCH");
                            }
                        } else {
                            System.out.println("alcohol percentage NOT DETECTED");
                        }
                        System.out.println("\n");
                    }

                    // Check for Appellation
                    if (!form.getAppellation().isEmpty()) {
                        int aLength = form.getAppellation().length();

                        if (result.contains("VALLEY")) {
                            System.out.println("appellation DETECTED");
                            int position = result.indexOf("VALLEY");

                            String appellation = result.substring(position - aLength - 1, position - 1);
                            System.out.println(appellation);
                            System.out.println(form.parseGarbage(form.getAppellation()));

                            if (form.parseGarbage(form.getAppellation()).equals(appellation)) {
                                System.out.println("appellation MATCHED");
                            } else {
                                System.out.println("appellation NO MATCH");
                            }
                        } else {
                            System.out.println("appellation NOT DETECTED");
                        }
                        System.out.println("\n");
                    }

                } catch (TesseractException e) {
                    System.err.println(e.getMessage());
                }
            }

            // Delete the stored file
            if(form.getLabel().getLabelFile().delete()){
                System.out.println("File deleted successfully");
            }else{
                System.out.println("Failed to delete the file");
            }

        }else{
            System.out.println("there is no image to parse");
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
        if(form.getLabel().getLabelFile().delete()){
            System.out.println("File deleted successfully");
        }else{
            System.out.println("Failed to delete the file");
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
        sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
    }
    @FXML
    public void acceptForm() throws IOException {
        if(form.getLabel().getLabelFile().delete()){
            System.out.println("File deleted successfully");
        }else{
            System.out.println("Failed to delete the file");
        }
        cacheM.approveForm(cacheM.getDbM().getConnection());
        Agent A = (Agent) cacheM.getAcct();
        A.approveOrDeny(form);
        goToHomePage();
    }


    @FXML
    public void denyForm() throws Exception {
        if(form.getLabel().getLabelFile().delete()){
            System.out.println("File deleted successfully");
        }else{
            System.out.println("Failed to delete the file");
        }
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
        if(form.getLabel().getLabelFile().delete()){
            System.out.println("File deleted successfully");
        }else{
            System.out.println("Failed to delete the file");
        }
        cacheM.passForm(cacheM.getDbM().getConnection(),cacheM.getForm().getFormID(), receiver.getText());
        Agent A = (Agent) cacheM.getAcct();
        A.pass(form);
        goToHomePage();
    }

    @FXML
    public void logout() throws IOException {
        if(form.getLabel().getLabelFile().delete()){
            System.out.println("File deleted successfully");
        }else{
            System.out.println("Failed to delete the file");
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));

    }

}
