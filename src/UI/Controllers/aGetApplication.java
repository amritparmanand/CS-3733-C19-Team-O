package UI.Controllers;

import Datatypes.Agent;
import Datatypes.Form;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Sam Silver & Jonathan Luna & Percy & Gabe
 * @version It 3
 * Controller for aGetApplication of UI
 */
public class aGetApplication {
    private SceneManager sceneM;
    private CacheManager cacheM;

    public aGetApplication(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML private FlowPane loadFormPane;
    @FXML private JFXCheckBox approved;
    @FXML private JFXCheckBox denied;

    private String filterA = "";
    private String filterD = "";
    private boolean noFilter = false;

    @SuppressWarnings("Duplicates")
    @FXML public void initialize() throws IOException {
        loadFormPane.getChildren().clear();

        if(approved.isSelected()){
            filterA = "APPROVED";
        }
        else{
            filterA = "no";
        }

        if(denied.isSelected()){
            filterD = "DENIED";
        }
        else{
            filterD = "no";
        }

        if(!approved.isSelected() && !denied.isSelected()){
            noFilter = true;
        }
        else{
            noFilter = false;
        }

        Agent A = (Agent) cacheM.getAcct();

        if(!A.isGotOldForms()){
            ((Agent) cacheM.getAcct()).getReviewedForms(cacheM.getDbM().getConnection());
        }

        ArrayList<Form> populatedForms = ((Agent) cacheM.getAcct()).getReviewedForms();

        for (Form form : populatedForms) {
            if (form.getFormStatus(cacheM.getDbM().getConnection()).equals(filterA) ||
                    form.getFormStatus(cacheM.getDbM().getConnection()).equals(filterD) ||
                    noFilter) {
                Pane formResult;
                try {
                    formResult = FXMLLoader.load(getClass().getResource("/UI/Views/alcBox.fxml"));
                    Node vbox = formResult.getChildren().get(0);
                    if (vbox instanceof VBox) {
                        Node imgView = ((VBox) vbox).getChildren().get(0);
                        Node fName = ((VBox) vbox).getChildren().get(1);
                        Node bName = ((VBox) vbox).getChildren().get(2);
                        Node aType = ((VBox) vbox).getChildren().get(3);
                        if(form.getLabel().getLabelImage() != null)
                            ((ImageView) imgView).setImage(form.getLabel().getLabelImage());
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

                        String style = "";
                        switch(form.getFormStatus(cacheM.getDbM().getConnection())){
                            case "APPROVED":
                                style = "-fx-background-color: #e4f7ef;\n";
                                break;
                            case "DENIED":
                                style = "-fx-background-color: #fcedec;\n";
                                break;
                        }
                        vbox.setStyle(style);

                    }
                    loadFormPane.getChildren().add(formResult);
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

        cacheM.setAcct(A);
    }

    @FXML
    public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
        sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
    }

    @FXML
    public void aApplicationFormControl(Form form) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormViewPg1.fxml"));
        sceneM.changeScene(loader, new mApplicationFormViewPg1(sceneM, cacheM, form));
    }

    @FXML
    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));

    }
}