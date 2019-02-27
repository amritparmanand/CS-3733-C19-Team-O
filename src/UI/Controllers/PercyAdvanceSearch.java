package UI.Controllers;

import Datatypes.SearchResult;
import Datatypes.StageContainingScene;
import Managers.CacheManager;
import Managers.SceneManager;
import Managers.SearchManager;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.time.LocalDate;

public class PercyAdvanceSearch extends StageContainingScene {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML JFXTextField fromTTB;
    @FXML JFXTextField toTTB;
    @FXML JFXTextField stateCode;


    public PercyAdvanceSearch(SceneManager sceneM, CacheManager cacheM) {
        super();
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML public void initialize() {
        TextFields.bindAutoCompletion(stateCode, cacheM.getForm().stateSelect());
    }

    @FXML public void applySearchSettings() {
        if(!fromTTB.getText().isEmpty() && !toTTB.getText().isEmpty()){
            System.out.println("filter detected!");
            cacheM.setFromTTB(Integer.parseInt(fromTTB.getText()));
            cacheM.setToTTB(Integer.parseInt(toTTB.getText()));
            cacheM.setFilter(true);
        }else if(!stateCode.getText().isEmpty()){
            System.out.println("state detected!");
            cacheM.setFilter2(true);
            cacheM.setSearchState(stateCode.getText());
        } else{
            System.out.println("no filter!!!");
        }
        super.getStage().close();
    }

}
