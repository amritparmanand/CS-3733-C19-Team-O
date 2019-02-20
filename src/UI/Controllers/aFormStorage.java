package UI.Controllers;


import Datatypes.Agent;
import Datatypes.Form;
import Datatypes.Setting;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Sam Silver & Percy Jiang
 * @version It 2
 * Controller for aFormStorage of UI
 * @since It 1
 */
public class aFormStorage {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private FlowPane loadForms;
    ArrayList<Form> fucker = new ArrayList<>();

    public aFormStorage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @SuppressWarnings("Duplicates") @FXML public void initialize(){
        Agent A = (Agent) cacheM.getAcct();
        if(!A.isGotCurrentForms()){
            ((Agent) cacheM.getAcct()).getAssignedForms(cacheM.getDbM().getConnection());
        }
        ArrayList<Form> populatedForms = ((Agent) cacheM.getAcct()).getWorkingForms();

        for (Form form : populatedForms) {
            Pane formResult;
            try {
                formResult = FXMLLoader.load(getClass().getResource("/UI/Views/alcBox.fxml"));
                Node vbox = formResult.getChildren().get(0);
                if (vbox instanceof VBox) {
                    Node fName = ((VBox) vbox).getChildren().get(1);
                    Node bName = ((VBox) vbox).getChildren().get(2);
                    Node aType = ((VBox) vbox).getChildren().get(3);

                    ((Label) fName).setText(form.parseGarbage(form.getFancifulName()));
                    ((Label) bName).setText(form.parseGarbage(form.getBrandName()));
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
                            aApplicationFormControl(form);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
        sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
    }

    @FXML
    public void aApplicationFormControl(Form form) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aApplicationFormPg1.fxml"));
        sceneM.changeScene(loader, new aApplicationFormPg1(sceneM, cacheM, form));
    }

    @SuppressWarnings("Duplicates")
    @FXML
    public void assignNewForms() throws IOException {

        int limit = cacheM.getFormLimit();

        Agent A = (Agent) cacheM.getAcct();
        if(!A.isHasFetchedForms()){
            ((Agent) cacheM.getAcct()).assignNewForms(cacheM.getDbM().getConnection(), limit);
        }

        ArrayList<Form> populatedForms = ((Agent) cacheM.getAcct()).getNewForms();

        for (Form form : populatedForms) {

            if(!fucker.contains(form)){
                System.out.println("adding fucker failed");
                Pane formResult;
                try {
                    formResult = FXMLLoader.load(getClass().getResource("/UI/Views/alcBox.fxml"));
                    Node vbox = formResult.getChildren().get(0);
                    if (vbox instanceof VBox) {
                        Node fName = ((VBox) vbox).getChildren().get(1);
                        Node bName = ((VBox) vbox).getChildren().get(2);
                        Node aType = ((VBox) vbox).getChildren().get(3);

                        ((Label) fName).setText(form.parseGarbage(form.getFancifulName()));
                        ((Label) bName).setText(form.parseGarbage(form.getBrandName()));
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
                                aApplicationFormControl(form);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

                fucker.add(form);
            }
        }
    }

    @FXML
    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));
    }


}
