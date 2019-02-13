package UI.Controllers;

import Datatypes.Form;
import Managers.*;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Amrit Parmanand & Elizabeth Del Monaco
 * @version It 2
 * Controller for mApplicationFormPg4 of UI
 */
public class mApplicationFormPg4 {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private Button previous;
    @FXML private Button search;
    @FXML private Button back;
    @FXML private Button submit;
    @FXML private JFXTextField dateOfApplication;
    @FXML private JFXTextField applicantSig;
    @FXML private JFXTextField applicantNamePrint;

    public mApplicationFormPg4(SceneManager sceneM, CacheManager cacheM) {

        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML public void initialize(){
        Form form = cacheM.getForm();

        System.out.println("starting");
        dateOfApplication.setText(form.getDateOfApplication());
        //applicantSig.setText(form.getApplicantSignature);
        applicantNamePrint.setText(form.getApplicantName());

    }

    @FXML
    public void submit() throws SQLException, IOException {
        Form form = cacheM.getForm();

        form.setDateOfApplication(dateOfApplication.getText());
       // form.setSignatureOfApplicant(applicantSig.getText());
        form.setPrintName(applicantNamePrint.getText());
//        form.setDateIssued("");

        try{
            cacheM.getDbM().insertForm(form);
        }catch(SQLException e){
            e.printStackTrace();
        }

        Form cleanForm = new Form();
        cacheM.setForm(cleanForm);
        goToHomePage();

    }

    @FXML public void previousPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg3.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg3(sceneM, cacheM));
    }
    @FXML public void searchPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }
    @FXML public void goToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }
}