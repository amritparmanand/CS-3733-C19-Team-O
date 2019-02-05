package UI;

import Datatypes.Form;
import Managers.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;
import java.math.BigInteger;

public class mApplicationFormPg2 {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private Button next;
    @FXML private Button previous;
    @FXML private Button search;
    @FXML private Button back;

    public mApplicationFormPg2(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML
    public void nextPage() throws IOException {

        Form form = cacheM.getForm();

        form.setPrintName("");
        form.setMailingAddress("");
        form.setFormula("");
        form.setGrapeVarietal("");
        form.setProductType(1);
        form.setAppellation("");
        form.setFancifulName("");
        form.setPhoneNumber("");
        form.setEmailAddress("");

        System.out.println(form.getBrandName());
        cacheM.setForm(form);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg3.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg3(sceneM, cacheM));
    }

    @FXML
    public void previousPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg1.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg1(sceneM, cacheM));
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