package UI.Controllers;


import Datatypes.Agent;
import Datatypes.Form;
import Managers.CacheManager;
import Managers.SceneManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
public class aFormStorage {
    private SceneManager sceneM;
    private CacheManager cacheM;

    public aFormStorage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML private FlowPane loadForms;
    @FXML private Label achievement;
    @FXML private ImageView alcyView;
    @FXML private Text alcyLabel;
    private ArrayList<Form> repeated = new ArrayList<>();
    private Agent A;

    @SuppressWarnings("Duplicates") @FXML public void initialize() throws Exception {
        A = (Agent) cacheM.getAcct();
        cacheM.getAlcy().summonAlcy(alcyView, alcyLabel);
        A.getAssignedForms(cacheM.getDbM().getConnection());
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

                    if(form.getLabel().getLabelImage() != null)
                        ((ImageView) imgView).setImage(form.getLabel().getLabelImage());
                    ((Label) fName).setText(form.parseGarbage(form.getFancifulName()));
                    ((Label) bName).setText(form.parseGarbage(form.getBrandName()));
                    switch(form.parseGarbage(form.getProductType())){
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
        int numberOfForms = A.getNumberProcessed();

        // processed 1+ forms
        if (numberOfForms == 1){
            achievement.setText("Achievement: The Rookie Unlocked!");
        }
        // processed 100+ forms
        if (numberOfForms == 100){
            achievement.setText("Achievement: Getting Started Unlocked!");
        }
        // processed 10000+ forms
        if (numberOfForms == 10000){
            achievement.setText("Achievement: Seasoned Pro Unlocked!");
        }
        // approved 1+ forms
        if (A.getNumberApproved() == 1){
            achievement.setText("Achievement: Pleasant Mood Unlocked!");
        }
        // approved 100+ forms
        if (A.getNumberApproved() == 100){
            achievement.setText("Achievement: Yes Man Unlocked!");
        }
        // approved 10000+ forms
        if (A.getNumberApproved() == 10000){
            achievement.setText("Achievement: Good Vibrations Unlocked!");
        }
        // denied 1+ forms
        if (A.getNumberDenied() == 1){
            achievement.setText("Achievement: Denied Unlocked!");
        }
        // denied 100+ forms
        if (A.getNumberDenied() == 100){
            achievement.setText("Achievement: High Standards Unlocked!");
        }
        // denied 10000+ forms
        if (A.getNumberDenied() == 10000){
            achievement.setText("Achievement: It's All Wrong Unlocked!");
        }
        // passed 1+ forms to another agent
        if (A.getNumberPassed() == 1){
            achievement.setText("Achievement: Passing The Buck Unlocked!");
        }
        // passed 100+ forms to another agent
        if (A.getNumberPassed() == 100){
            achievement.setText("Achievement: Expert Procrastination Unlocked!");
        }
        // passed 10000+ forms to another agent
        if (A.getNumberPassed() == 10000){
            achievement.setText("Achievement: Expert Delegation Unlocked!");
        }
        // processed 3 forms in a row
        if (A.getRowAD() == 3){
            achievement.setText("Achievement: Three Pointer Unlocked!");
        }

        // passed three forms in a row
        if (A.getRowP()  == 3){
            achievement.setText("Achievement: Strike Three Unlocked!");
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

//            if (!repeated.contains(form)) {
//                System.out.println("adding repeated failed");
//
//            }
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

//                repeated.add(form);
//                A.getWorkingForms().add(form);
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


}
