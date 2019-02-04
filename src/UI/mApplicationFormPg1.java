package UI;

import Datatypes.Form;
import Managers.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class mApplicationFormPg1 {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private Button next;
    @FXML private Button search;
    @FXML private Button back;

    public mApplicationFormPg1(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML
    public void nextPage() throws IOException {
        Form form = cacheM.getForm();

        form.setBrewerNumber(1);
        form.setProductSource(1);
        form.setSerialNumber(1);
        form.setProductType(1);
        form.setBrandName("Yeet");
        form.setFancifulName("yoit");

        cacheM.setForm(form);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg2.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg2(sceneM, cacheM));
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