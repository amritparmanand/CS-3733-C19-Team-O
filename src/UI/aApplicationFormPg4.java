package UI;


import Datatypes.Form;
import Managers.CacheManager;
import Managers.SceneManager;
import com.sun.java.accessibility.util.java.awt.TextComponentTranslator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class aApplicationFormPg4 {
    private SceneManager sceneM;
    private CacheManager cacheM;

    public aApplicationFormPg4(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        initializePg4();
    }

    @FXML
    private Button back;
    @FXML private Button next;
    @FXML private Button search;
    @FXML private TextField date;
    @FXML private TextField printName;

    public void initializePg4 () {
        Form form = cacheM.getForm();
        //   date.setText(form.getDateOfApplication());
        //   date.setEditable(false);
        printName.setText(form.getPrintName());
        printName.setEditable(false);
    }

    @FXML
    public void search() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, cacheM));
    }
    @FXML
    public void goToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }


/*    @FXML
    public void nextPage() throws IOException {
        Form form = cacheM.getForm();

        form.setBrewerNumber(1);
        form.setProductSource(1);
        form.setSerialNumber(1);
        form.setProductType(1);
        form.setBrandName("Yeet");
        form.setFancifulName("");

        cacheM.setForm(form);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormPg4.fxml"));
        sceneM.changeScene(loader, new aApplicationFormPg4(sceneM, cacheM));
    } */
}
