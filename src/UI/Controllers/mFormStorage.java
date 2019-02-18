package UI.Controllers;

import Datatypes.Agent;
import Datatypes.Form;
import Datatypes.Manufacturer;
import Datatypes.NumberAssigned;
import Managers.CacheManager;
import Managers.DatabaseManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
/**
 * @author ???
 * @version It 1
 * Controller for mFormStorage of UI
 */
public class mFormStorage {

    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private Button backToMHome;
    @FXML private JFXButton search;
    @FXML private JFXButton newForm;
    @FXML private JFXCheckBox approved;
    @FXML private JFXCheckBox pending;
    @FXML private JFXCheckBox denied;
    @FXML private FlowPane loadForms;

    public mFormStorage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }



    @FXML
    public void newForm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg1.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg1(sceneM, cacheM));
    }

    @SuppressWarnings("Duplicates")
    @FXML public void getForms() throws IOException {
        Manufacturer manAcc = (Manufacturer) cacheM.getAcct();
        manAcc.retrieveAssignedForms(cacheM.getDbM().getConnection());

        ArrayList<Form> populatedForms = (manAcc.getAssignedForms());

        for (Form form : populatedForms) {
            Pane formResult;
            try {
                formResult = FXMLLoader.load(getClass().getResource("/UI/Views/alcBox.fxml"));
                Node vbox = formResult.getChildren().get(0);
                if (vbox instanceof VBox) {
                    Node fName = ((VBox) vbox).getChildren().get(1);
                    Node bName = ((VBox) vbox).getChildren().get(2);
                    Node aType = ((VBox) vbox).getChildren().get(3);

                    ((Label) fName).setText(form.getFancifulName());
                    ((Label) bName).setText(form.getBrandName());
                    switch(form.getProductType()){
                        case "WINE":
                            ((Label) aType).setText("Wine");
                            break;
                        case "DISTILLED":
                            ((Label) aType).setText("Distilled Beverage");
                            break;
                        case "MALT":
                            ((Label) aType).setText("Malt Beverage");
                            break;
                    }


                }
                loadForms.getChildren().add(formResult);
                formResult.setId("Alcoholbox");

                formResult.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            mApplicationFormControl(form);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }}

    @FXML
    public void mApplicationFormControl(Form form) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormViewPg1.fxml"));
        sceneM.changeScene(loader, new mApplicationFormViewPg1(sceneM, cacheM, form));
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