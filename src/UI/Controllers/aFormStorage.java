package UI.Controllers;


import Datatypes.Agent;
import Datatypes.Controller;
import Datatypes.Form;
import Managers.CacheManager;
import Managers.SceneManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Sam Silver & Percy Jiang
 * @version It 2
 * Controller for aFormStorage of UI
 * @since It 1
 */
public class aFormStorage extends Controller {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private settingPage settingPage;


    public aFormStorage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML private FlowPane loadForms;
    @FXML private ImageView alcyView;
    @FXML private Text alcyLabel;
    private ArrayList<Form> repeated = new ArrayList<>();
    private Agent A;
    private String message;
    private Image image;
  //  private achievementPage achievement;
    @FXML private FlowPane achievement;

    @SuppressWarnings("Duplicates") @FXML public void initialize() throws Exception {
        //   achievement.setText("");
        A = (Agent) cacheM.getAcct();
        cacheM.getAlcy().summonAlcy(alcyView, alcyLabel);
        try {
            A.getAssignedForms(cacheM.getDbM().getConnection());
        }catch (Exception e)
        {

        }
        ArrayList<Form> populatedForms = A.getWorkingForms();

        for (Form form : populatedForms) {
            Pane formResult;
            try {
                formResult = FXMLLoader.load(getClass().getResource("/UI/Views/alcBox.fxml"));
                Node vbox = formResult.getChildren().get(0);
                if (vbox instanceof VBox) {
                    Node imgView = ((VBox) vbox).getChildren().get(0);
                    Node fName = ((VBox) vbox).getChildren().get(1);
                    Node bName = ((VBox) vbox).getChildren().get(2);
                    Node aType = ((VBox) vbox).getChildren().get(3);

                    if (form.getLabel().getLabelImage() != null)
                        ((ImageView) imgView).setImage(form.getLabel().getLabelImage());
                    ((Label) fName).setText(form.parseGarbage(form.getFancifulName()));
                    ((Label) bName).setText(form.parseGarbage(form.getBrandName()));
                    switch (form.parseGarbage(form.getProductType())) {
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
    public void assignNewForms() throws IOException, Exception {

        int limit = cacheM.getFormLimit();

        A.assignNewForms(cacheM.getDbM().getConnection(), limit);
        ArrayList<Form> populatedForms = A.getNewForms();

        for (Form form : populatedForms) {
            Pane formResult;
            try {
                formResult = FXMLLoader.load(getClass().getResource("/UI/Views/alcBox.fxml"));
                Node vbox = formResult.getChildren().get(0);
                if (vbox instanceof VBox) {
                    Node imgView = ((VBox) vbox).getChildren().get(0);
                    Node fName = ((VBox) vbox).getChildren().get(1);
                    Node bName = ((VBox) vbox).getChildren().get(2);
                    Node aType = ((VBox) vbox).getChildren().get(3);

                    if (form.getLabel().getLabelImage() != null) {
                        ((ImageView) imgView).setImage(form.getLabel().getLabelImage());
                        ((Label) fName).setText(form.parseGarbage(form.getFancifulName()));
                        ((Label) bName).setText(form.parseGarbage(form.getBrandName()));
                        switch (form.parseGarbage(form.getProductType())) {
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

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML public void logout() throws IOException {
        A.deleteLabels();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));
    }
    @FXML public void settings() throws IOException {
        settingPage = new settingPage(sceneM, cacheM);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/settingPage.fxml"));
        sceneM.popWindowLoader(loader, settingPage, "Setting");
    }

}
