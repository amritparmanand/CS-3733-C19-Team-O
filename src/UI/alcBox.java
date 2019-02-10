package UI;

import Datatypes.Form;
import Managers.CacheManager;
import Managers.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;
/**
 * @author Amrit Parmanand & Robert Rinearson
 * @version It 1
 * Search box on agent's page that takes to forms
 */
public class alcBox {
    private SceneManager sceneM;
    private CacheManager cacheM;

    public alcBox(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    Form form = cacheM.getForm();

    @FXML private Button alcBox;

    @FXML
    public void aApplicationFormControl() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormPg1.fxml"));
        sceneM.changeScene(loader, new aApplicationFormPg1(sceneM, cacheM, form));
    }

}
