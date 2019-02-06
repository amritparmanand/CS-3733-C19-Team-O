package UI;

import Datatypes.Agent;
import Managers.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.Console;
import java.io.IOException;

public class aHomepage {
    private SceneManager sceneM;
    private CacheManager cacheM;

    public aHomepage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML private Button back;
    @FXML private Button search;
    @FXML private VBox getApp;

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
    public void getAssignedForms() {
        ((Agent) cacheM.getAcct()).getAssignedForms(cacheM.getDbM().getConnection());
    }

    @FXML
    public void assignNewForms() {
        Agent a = ((Agent) cacheM.getAcct());
        System.out.println(a.getTtbID());

        //a.assignNewForms(cacheM.getDbM().getConnection());
    }
}
