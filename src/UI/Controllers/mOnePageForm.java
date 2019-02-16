package UI.Controllers;

import Datatypes.PDF;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * @author Robert & Percy
 * @version It3
 * application form in one page
 */
public class mOnePageForm {
    private SceneManager sceneM;
    private CacheManager cacheM;


    public mOnePageForm(SceneManager sceneM, CacheManager cacheM){
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML private JFXTextField brandName;
    @FXML private JFXTextField fancifulName;
    @FXML private JFXTextField formula;



    @FXML
    public void savePDF() throws IOException {

        System.out.println("saving pdf");
        PDF pdf = new PDF();
        pdf.appendText(brandName.getText(), 24,780, 10);
        pdf.appendText(fancifulName.getText(), 24,740, 10);
//        pdf.appendText(formula.getText(), 24,700, 10);
        System.out.println("saved!");
    }

}
