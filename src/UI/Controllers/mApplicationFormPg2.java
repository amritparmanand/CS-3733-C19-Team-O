package UI.Controllers;

import Datatypes.Form;
import Managers.*;
import UI.MultiThreadWaitFor;
import UI.callableFunction;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * @author Amrit Parmanand & Elizabeth Del Monaco
 * @version It 2
 * Controller for mApplicationFormPg2 of UI
 */
public class mApplicationFormPg2 {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private AnchorPane mainPane;

    @FXML private VBox hideBox;
    @FXML private VBox varietalVBox;
    @FXML private VBox appellationVBox;

    @FXML private JFXTextField printName;
    @FXML private JFXTextField mailAddress;
    @FXML private JFXTextField formula;
    @FXML private JFXTextField grapes;
    @FXML private JFXTextField appellation;
    @FXML private JFXTextField phoneNumber;
    @FXML private JFXTextField email;

    public mApplicationFormPg2(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML public void initialize(){
        Form form = cacheM.getForm();
        printName.setText(form.getPrintName());
        mailAddress.setText(form.getMailingAddress());
        formula.setText(form.getFormula());
        grapes.setText(form.getGrapeVarietal());
        appellation.setText(form.getAppellation());
        phoneNumber.setText(form.getPhoneNumber());
        email.setText(form.getEmailAddress());

    }

    @FXML public void saveDraft(){
        if (printName!=null && mailAddress!=null && formula!=null && grapes!=null && appellation!=null && phoneNumber!=null && email!=null) {
            Form form = cacheM.getForm();

            form.setPrintName(printName.getText());
            form.setMailingAddress(mailAddress.getText());
            form.setFormula(formula.getText());
            form.setGrapeVarietal(grapes.getText());
            form.setAppellation(appellation.getText());
            form.setPhoneNumber(phoneNumber.getText());
            form.setEmailAddress(email.getText());

            if(cacheM.getForm().getBeerWineSpirit() != "WINE") {
                form.setGrapeVarietal("");
                form.setAppellation("");
            }

            cacheM.setForm(form);

            System.out.println("Pg2 saved!");
        }
    }

    @FXML public void wineFieldCheck(){
        if(cacheM.getForm().getBeerWineSpirit() != "WINE") {
            grapes.setEditable(false);
            grapes.setPromptText("n/a");
            appellation.setEditable(false);
            appellation.setPromptText("n/a");
        }
    }

    /**
     * The multi-thread function
     * Saves draft every 5 seconds
     */
    callableFunction cf = new callableFunction() {
        @Override
        @FXML
        public void call() {
            saveDraft();
        }
    };
    MultiThreadWaitFor multiThreadWaitFor = new MultiThreadWaitFor(5, cf);

    @FXML public void nextPage() throws IOException {
        saveDraft();
        multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg3.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg3(sceneM, cacheM));
    }
    @FXML public void previousPage() throws IOException {
        saveDraft();
        multiThreadWaitFor.onShutDown();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg1.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg1(sceneM, cacheM));
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

}