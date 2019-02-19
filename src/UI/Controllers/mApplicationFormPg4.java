package UI.Controllers;

import Datatypes.Form;
import Datatypes.Manufacturer;
import Managers.*;
import UI.MultiThreadWaitFor;
import UI.callableFunction;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

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
    String style = "-fx-background-color: #94BDFF;";

    @FXML
    private Button previous;
    @FXML
    private Button search;
    @FXML
    private Button back;
    @FXML
    private Button submit;
    @FXML
    private JFXDatePicker dateOfApplication;
    @FXML
    private JFXTextField applicantSig;
    @FXML
    private JFXTextField applicantNamePrint;

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

        System.out.println("starting");
        if(!form.getApplicantName().equals(""))
            applicantNamePrint.setText(form.getPrintName());
        else
            applicantNamePrint.setText(manAcc.getFullName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        System.out.println("starting");
        if(!form.getDateOfApplication().isEmpty()){
            dateOfApplication.setValue(LocalDate.parse(form.getDateOfApplication(), formatter));
        }

    }

    public void saveDraft(){
            //Form form = cacheM.getForm();

            form.setDateOfApplication(dateOfApplication.getValue().toString());
        if (!applicantNamePrint.getText().isEmpty()) {
            if(!form.getPrintName().contains(style)){
                form.setPrintName(applicantNamePrint.getText());
            }
        }

        if (form.getTtbID() != 0) {
            checkDiff();
        }

            cacheM.setForm(form);
            System.out.println("Pg4 Saved!");

    }

    /**
     * The multi-thread function
     * Saves draft every 5 seconds
     */
//    callableFunction cf = new callableFunction() {
//        @Override
//        public void call() {
//            if (dateOfApplication!=null && applicantNamePrint!=null){
//                saveDraft();
//            }
//        }
//    };
//    MultiThreadWaitFor multiThreadWaitFor = new MultiThreadWaitFor(5, cf);

    @FXML
    public void submit() throws SQLException, IOException {
        //multiThreadWaitFor.onShutDown();
        Form form = cacheM.getForm();

        form.setDateOfApplication(dateOfApplication.getValue().toString());
        // form.setSignatureOfApplicant(applicantSig.getText());
        form.setPrintName(applicantNamePrint.getText());
//        form.setDateIssued("");

        try{
            if(cacheM.getForm().getResubmission()){
                cacheM.getForm().resubmitForm(cacheM.getDbM().getConnection());
            }
            else{
                cacheM.insertForm(cacheM.getDbM().getConnection());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        Form cleanForm = new Form();
        cacheM.setForm(cleanForm);
        goToHomePage();

    }

    public void checkDiff() {

        if (!applicantSig.getText().equals(form.getSignature()) && applicantSig.getText().contains(style)) {
            form.setSignature(applicantSig.getText() + style);
        }
        if (!applicantNamePrint.getText().equals(form.getPrintName()) && applicantNamePrint.getText().contains(style)) {
            form.setPrintName(applicantNamePrint.getText() + style);
        }
    }

    @FXML public void previousPage() throws IOException {
        //multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg3.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg3(sceneM, cacheM, form));
    }
    @FXML public void searchPage() throws IOException {
        //multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
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
}