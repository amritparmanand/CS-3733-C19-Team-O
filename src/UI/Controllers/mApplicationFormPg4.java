package UI.Controllers;

import Datatypes.Alcy;
import Datatypes.Form;
import Datatypes.Manufacturer;
import Datatypes.PDF;
import Managers.*;
import UI.MultiThreadWaitFor;
import UI.callableFunction;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Amrit Parmanand & Elizabeth Del Monaco
 * @version It 2
 * Controller for mApplicationFormPg4 of UI
 */
public class mApplicationFormPg4 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;

    @FXML private Button previous;
    @FXML private Button search;
    @FXML private Button back;
    @FXML private Button submit;
    @FXML private JFXDatePicker dateOfApplication;
    @FXML private JFXTextField applicantSig;
    @FXML private JFXTextField applicantNamePrint;
    @FXML private Label errorLabel2;
    @FXML private JFXButton pdfButton;
    @FXML private VBox commentVBox;
    @FXML private JFXTextArea aComment;
    @FXML private ImageView alcyView;
    @FXML private Text alcyLabel;


    public mApplicationFormPg4(SceneManager sceneM, CacheManager cacheM, Form form) {

        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
    }
    public mApplicationFormPg4(SceneManager sceneM, CacheManager cacheM) {

        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = cacheM.getForm();
    }

    @FXML
    public void initialize() {
        Manufacturer manAcc = (Manufacturer) cacheM.getAcct();
        Alcy alcy = cacheM.getAlcy();
        alcy.summonAlcy(alcyView, alcyLabel);
        alcy.sayMForm();

        dateOfApplication.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                { cacheM.getAlcy().sayMHelpDate();}
                else
                {
                    saveDraft();
                    //cacheM.getAlcy().sayGreeting();
                }
            }
        });

        if(form.getCommentString() == ""){
            commentVBox.setVisible(false);
        }
        aComment.setText(form.getCommentString());

        if(!form.getPrintName().equals(""))
            applicantNamePrint.setText(form.getPrintName());
        else
            applicantNamePrint.setText(manAcc.getFullName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        System.out.println("starting");
        if(!form.getDateOfApplication().isEmpty()){
            dateOfApplication.setValue(LocalDate.parse(form.getDateOfApplication(), formatter));
        }

    }

    @SuppressWarnings("Duplicates") public void saveDraft(){
        if (form.getTtbID() != 0) {
            checkDiff();
        }

        if(dateOfApplication.getValue() != null)
            form.setDateOfApplication(dateOfApplication.getValue().toString());
        if (!applicantNamePrint.getText().isEmpty()) {
            if(!form.getPrintName().contains(cacheM.getStyle())){
                form.setPrintName(applicantNamePrint.getText());
            }
        }

        cacheM.setForm(form);
        System.out.println("Pg4 Saved!");

    }

    @FXML public void submit() throws Exception{
        //multiThreadWaitFor.onShutDown();
        saveDraft();
        //Form form = cacheM.getForm();
        if(form.getCommentString() == ""){
            commentVBox.setVisible(false);
        }

        try{
            System.out.println(form.getResubmission());
            if(form.getResubmission()){
                form.resubmitForm(cacheM.getDbM().getConnection());
            } else{
                form.insertForm(cacheM.getDbM().getConnection());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        Manufacturer M = (Manufacturer) cacheM.getAcct();
        M.submitForm(cacheM.getDbM().getConnection());

        Form cleanForm = new Form();
        cacheM.setForm(cleanForm);
        goToHomePage();

    }

    public void checkDiff() {
        if (!applicantNamePrint.getText().equals(form.getPrintName()) && !applicantNamePrint.getText().contains(cacheM.getStyle())) {
            form.setPrintName(applicantNamePrint.getText() + cacheM.getStyle());
            System.out.println("check diff called, it looks like: " + form.getPrintName());
        }
    }

    @FXML public void previousPage() throws IOException {
        //multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg3.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg3(sceneM, cacheM, form));
    }

    @FXML public void goToHomePage() throws IOException {
        //multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }

    @FXML
    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));
    }

    @FXML
    public void onePage() throws IOException {
        saveDraft();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mOnePageForm.fxml"));
        sceneM.changeScene(loader, new mOnePageForm(sceneM, cacheM,form));
    }


    @FXML public void savePDF() throws IOException {
        saveDraft();
        PDF pdf = new PDF();
        pdf.savePDF(form);
    }

}