package UI;

import Datatypes.Form;
import Managers.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class mApplicationFormPg4 {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private Button previous;
    @FXML private Button search;
    @FXML private Button back;
    @FXML private Button submit;

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
    public void submit() throws IOException {
        Form form = cacheM.getForm();

        form.setDateOfApplication(new Date(1,1,1));
//        form.setSignatureOfApplicant("");
        form.setPrintName("");
//        form.setDateIssued("");

        String createForm = "INSERT INTO Forms VALUES(" + form.getForms_pk()+
                ", " + form.getBrewerNumber()+
                ",	" + form.getProductSource()+
                ",	" + form.getSerialNumber()+
                ",	" + form.getProductType()+
                ",	" + form.getBrandName()+
                ", " + form.getFancifulName()+
                ",	" + form.getApplicantName()+
                ",	" + form.getMailingAddress()+
                ", " + form.getFormula()+
                ", " + form.getGrapeVarietal()+
                ",	" + form.getAppellation()+
                ", " + form.getPhoneNumber()+
                ", " + form.getEmailAddress()+
                ",	" + form.getDateOfAppilcation()+
                ", " + form.getPrintName()+
                ",	" + form.getBeerWineSpirit()+
                ", " + form.getAlcoholPercent()+
                ",	" + form.getVintageYear()+
                ", " + + form.getpHLevel();

        try {

            cacheM.getDbM().getStmt().execute(createForm);

        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
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