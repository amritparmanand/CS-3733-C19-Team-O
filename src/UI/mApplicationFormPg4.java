package UI;

import Datatypes.Form;
import Managers.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.format.DateTimeFormatter;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class mApplicationFormPg4 {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private Button previous;
    @FXML private Button search;
    @FXML private Button back;
    @FXML private Button submit;
    @FXML private DatePicker dateOfApplication;
    @FXML private TextField applicantSig;
    @FXML private TextField applicantNamePrint;

    public mApplicationFormPg4(SceneManager sceneM, CacheManager cacheM) {

        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML
    public void previousPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg3.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg2(sceneM, cacheM));
    }


    @FXML
    public void submit() throws SQLException {
        Form form = cacheM.getForm();
        form.setDateOfApplication(java.sql.Date.valueOf(dateOfApplication.getValue()));
       // form.setSignatureOfApplicant(applicantSig.getText());
        form.setPrintName(applicantNamePrint.getText());
//        form.setDateIssued("");

        cacheM.getDbM().insertForm(form);

    }

    @FXML
    public void searchPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void goToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }
}