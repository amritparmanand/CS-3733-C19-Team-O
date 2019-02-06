package UI;


import Datatypes.Agent;
import Datatypes.Form;
import Managers.CacheManager;
import Managers.SceneManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class aFormStorage {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML
    private FlowPane loadForms;
    @FXML
    private Button getFormsButton;

    public aFormStorage(SceneManager sceneM, CacheManager cacheM) {
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
        sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
    }

    @FXML
    public void loadForms(ActionEvent event) {
        Pane formResult = null;
        try {
            formResult = FXMLLoader.load(getClass().getResource("/UI/Views/alcBox.fxml"));
            loadForms.getChildren().add(formResult);
            formResult.setId("Alcoholbox");
            formResult.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try{
                        aApplicationFormPg1();

                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void aApplicationFormPg1() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormPg1.fxml"));
        sceneM.changeScene(loader, new aApplicationFormPg1(sceneM, cacheM));
    }

    @FXML
    public void getAssignedForms() {
        ((Agent) cacheM.getAcct()).getAssignedForms(cacheM.getDbM().getConnection());
    }

    @FXML
    public void assignNewForms() {
        ((Agent) cacheM.getAcct()).assignNewForms(cacheM.getDbM().getConnection());
    }

}
