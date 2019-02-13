package UI.Controllers;

import Managers.CacheManager;
import Managers.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SearchResultPopup {
    @FXML Label fancifulLabel;
    @FXML Label typeLabel;
    @FXML Label companyLabel;
    @FXML Label alcoholPercentLabel;
    @FXML Label pHLabel;
    @FXML Label yearLabel;

    public Label getFancifulLabel() {
        return fancifulLabel;
    }

    public Label getTypeLabel() {
        return typeLabel;
    }

    public Label getCompanyLabel() {
        return companyLabel;
    }

    public Label getAlcoholPercentLabel() {
        return alcoholPercentLabel;
    }

    public Label getpHLabel() {
        return pHLabel;
    }

    public Label getYearLabel() {
        return yearLabel;
    }

    @FXML public void setSearchPopupLabels(SceneManager sceneM, CacheManager cacheM){

        fancifulLabel.setText(cacheM.getSelectedResult().getFancifulName());
    }


}
