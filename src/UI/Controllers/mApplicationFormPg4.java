package UI.Controllers;

import Datatypes.Form;
import Managers.*;
import UI.MultiThreadWaitFor;
import UI.callableFunction;
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

    @FXML
    private Button previous;
    @FXML
    private Button search;
    @FXML
    private Button back;
    @FXML
    private Button submit;
    @FXML
    private JFXTextField dateOfApplication;
    @FXML
    private JFXTextField applicantSig;
    @FXML
    private JFXTextField applicantNamePrint;

    public mApplicationFormPg4(SceneManager sceneM, CacheManager cacheM) {

        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML
    public void initialize() {
        Form form = cacheM.getForm();

        System.out.println("starting");
        dateOfApplication.setText(form.getDateOfApplication());
        applicantNamePrint.setText(form.getApplicantName());

    }

    public void saveDraft(){
            Form form = cacheM.getForm();

            form.setDateOfApplication(dateOfApplication.getText());
            form.setPrintName(applicantNamePrint.getText());

            cacheM.setForm(form);
            System.out.println("Pg4 Saved!");

    }

    /**
     * The multi-thread function
     * Saves draft every 5 seconds
     */
    callableFunction cf = new callableFunction() {
        @Override
        public void call() {
            if (dateOfApplication!=null && applicantNamePrint!=null){
                saveDraft();
            }
        }
    };
    MultiThreadWaitFor multiThreadWaitFor = new MultiThreadWaitFor(5, cf);

    @FXML
    public void submit() throws SQLException, IOException {
        multiThreadWaitFor.onShutDown();
        Form form = cacheM.getForm();

        form.setDateOfApplication(dateOfApplication.getText());
        // form.setSignatureOfApplicant(applicantSig.getText());
        form.setPrintName(applicantNamePrint.getText());
//        form.setDateIssued("");

        try{
            cacheM.insertForm(cacheM.getDbM().getConnection());
        }catch(SQLException e){
            e.printStackTrace();
        }

        Form cleanForm = new Form();
        cacheM.setForm(cleanForm);
        goToHomePage();

    }


    @FXML public void previousPage() throws IOException {
        multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg3.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg3(sceneM, cacheM));
    }
    @FXML public void searchPage() throws IOException {
        multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }
    @FXML public void goToHomePage() throws IOException {
        multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }

    @FXML
    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));
    }
}