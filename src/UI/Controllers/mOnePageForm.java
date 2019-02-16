package UI.Controllers;

import Datatypes.PDF;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class mOnePageForm {
    @FXML private JFXTextField textfield;
    @FXML private JFXButton button;
    private SceneManager sceneM;
    private CacheManager cacheM;


    public mOnePageForm(SceneManager sceneM, CacheManager cacheM){
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }


    @FXML
    public void savePDF() throws IOException {

        System.out.println("saving pdf");
        PDF pdf = new PDF();
        pdf.appendText(textfield.getText(), 24,780, 10);
        System.out.println("saved!");
    }

}
