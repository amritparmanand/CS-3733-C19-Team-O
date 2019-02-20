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
import com.jfoenix.controls.JFXRadioButton;
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
 * @author Percy & Gabe
 * @version It 3
 * Controller for mFormStorage of UI
 */
public class mFormStorage {

    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private JFXRadioButton approved;
    @FXML private JFXRadioButton pending;
    @FXML private JFXRadioButton denied;
    @FXML private FlowPane loadForms;

    private String filter = "";

    public mFormStorage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @SuppressWarnings("Duplicates") @FXML public void initialize(){
        if(approved.isSelected()){
            filter = "APPROVED";
        }
        else if(pending.isSelected()){
            filter = "PENDING";
        }
        else if(denied.isSelected()){
            filter = "DENIED";
        }
        else{
            filter = "all";
        }

        Manufacturer manAcc = (Manufacturer) cacheM.getAcct();
        if(!manAcc.getHasFetchedForms())
            manAcc.setAssignedForms(cacheM.getDbM().getConnection());

        ArrayList<Form> populatedForms = (manAcc.getAssignedForms());

        for (Form form : populatedForms) {
            if (form.getFormStatus(cacheM.getDbM().getConnection()).equals(filter) || filter.equals("all")) {
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

                        String style = "";
                        switch(form.getFormStatus(cacheM.getDbM().getConnection())){
                            case "APPROVED":
                                style = "-fx-background-color: #e4f7ef;\n";
                                break;
                            case "DENIED":
                                style = "-fx-background-color: #fcedec;\n";
                                break;
                            case "PENDING":
                                style = "-fx-background-color: #fbf8e1;\n";
                                break;
                        }
                        vbox.setStyle(style);


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
            }
        }
    }

    @FXML public void reInitialize() {
        initialize();
    }

    @FXML
    public void mApplicationFormControl(Form form) throws IOException {
        if (!form.getFormStatus(cacheM.getDbM().getConnection()).equals("DENIED")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormViewPg1.fxml"));
            sceneM.changeScene(loader, new mApplicationFormViewPg1(sceneM, cacheM, form));
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg1.fxml"));
            sceneM.changeScene(loader, new mApplicationFormPg1(sceneM, cacheM, form));
        }
    }

    @FXML
    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));
    }

    @FXML
    public void goToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }


}