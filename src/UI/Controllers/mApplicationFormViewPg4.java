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
public class mApplicationFormViewPg4 {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private Form form;

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

    public mApplicationFormViewPg4(SceneManager sceneM, CacheManager cacheM, Form form) {

        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.form = form;
    }

    @FXML
    public void initialize() {
        Form form = cacheM.getForm();
        Manufacturer manAcc = (Manufacturer) cacheM.getAcct();

        System.out.println("starting");
            applicantNamePrint.setText(form.getPrintName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        if(!form.getDateOfApplication().isEmpty()){
            dateOfApplication.setValue(LocalDate.parse(form.getDateOfApplication(), formatter));
        }

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


    @FXML public void previousPage() throws IOException {
        //multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormViewPg3.fxml"));
        sceneM.changeScene(loader, new mApplicationFormViewPg3(sceneM, cacheM,form));
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