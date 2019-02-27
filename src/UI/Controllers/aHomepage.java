package UI.Controllers;

import Datatypes.Agent;
import Datatypes.Alcy;
import Datatypes.Controller;
import Managers.*;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Percy Jiang
 * @version It 1
 * Controller for aHomepage of UI
 */
public class aHomepage extends Controller {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private ImageView alcyView;
    @FXML private Text alcyLabel;
    private Agent A;
    private String message;
    private Image image;
    @FXML private FlowPane achievement;

    public aHomepage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML public void initialize(){
        A = (Agent) cacheM.getAcct();
        Alcy alcy = cacheM.getAlcy();
        alcy.summonAlcy(alcyView, alcyLabel);
        alcy.sayAHomePage();
        int numberOfForms = A.getNumberProcessed();

//        System.out.println(numberOfForms);

        // processed 1+ forms
        int achievementAwarded = 0;
        if (numberOfForms == 1) {
            image = new Image("UI/Icons/SoftEngAchieveColor/1.png");
            message = "The Rookie";
            achievementAwarded = 1;
        }
        // processed 100+ forms
        if (numberOfForms == 100) {
            image = new Image("UI/Icons/SoftEngAchieveColor/2.png");
            message = "Getting Started";
            achievementAwarded = 1;
        }
        // processed 10000+ forms
        if (numberOfForms == 10000) {
            image = new Image("UI/Icons/SoftEngAchieveColor/3.png");
            message = "Seasoned Pro";
            achievementAwarded = 1;
        }
        // approved 1+ forms
        if (A.getNumberApproved() == 1) {
            image = new Image("UI/Icons/SoftEngAchieveColor/10.png");
            message = "Pleasant Mood";
            achievementAwarded = 1;
        }
        // approved 100+ forms
        if (A.getNumberApproved() == 100) {
            image = new Image("UI/Icons/SoftEngAchieveColor/11.png");
            message = "Yes Man";
            achievementAwarded = 1;
        }
        // approved 10000+ forms
        if (A.getNumberApproved() == 10000) {
            image = new Image("UI/Icons/SoftEngAchieveColor/12.png");
            message = "Good Vibrations";
            achievementAwarded = 1;
        }
        // denied 1+ forms
        if (A.getNumberDenied() == 1) {
            image = new Image("UI/Icons/SoftEngAchieveColor/14.png");
            message = "Denied";
            achievementAwarded = 1;
        }
        // denied 100+ forms
        if (A.getNumberDenied() == 100) {
            image = new Image("UI/Icons/SoftEngAchieveColor/13.png");
            message = "High Standards";
            achievementAwarded = 1;
        }
        // denied 10000+ forms
        if (A.getNumberDenied() == 10000) {
            image = new Image("UI/Icons/SoftEngAchieveColor/15.png");
            message = "It's All Wrong";
            achievementAwarded = 1;
        }
        // passed 1+ forms to another agent
        if (A.getNumberPassed() == 1) {
            image = new Image("UI/Icons/SoftEngAchieveColor/7.png");
            message = "Passing The Buck";
            achievementAwarded = 1;
        }
        // passed 100+ forms to another agent
        if (A.getNumberPassed() == 100) {
            image = new Image("UI/Icons/SoftEngAchieveColor/8.png");
            message = "Expert Procrastination";
            achievementAwarded = 1;
        }
        // passed 10000+ forms to another agent
        if (A.getNumberPassed() == 10000) {
            image = new Image("UI/Icons/SoftEngAchieveColor/9.png");
            message = "Expert Delegation";
            achievementAwarded = 1;
        }
        // processed 3 forms in a row
        if (A.getRowAD() == 3) {
            image = new Image("UI/Icons/SoftEngAchieveColor/4.png");
            message = "Three Pointer";
            achievementAwarded = 1;
        }

        // passed three forms in a row
        if (A.getRowP() == 3) {
            image = new Image("UI/Icons/SoftEngAchieveColor/6.png");
            message = "Strike Three";
            achievementAwarded = 1;
            }

        Pane achievementDisplay;


        if (achievementAwarded == 1) {
            System.out.println("in");
            try {
                achievementDisplay = FXMLLoader.load(getClass().getResource("/UI/Views/achievementPage.fxml"));
                Node vbox = achievementDisplay.getChildren().get(0);
                if (vbox instanceof VBox) {

                    Node achImg = ((VBox) vbox).getChildren().get(1);
                    Node tag = ((VBox) vbox).getChildren().get(2);

                    ((ImageView) achImg).setImage(image);

                    //    System.out.println(agent.getAgentScore());
                    ((Label) tag).setText(message);
                    System.out.println(message);

                }

                //     System.out.println(agent.getAgentName() + " not in leaderboard. Adding.");
                achievement.getChildren().add(achievementDisplay);


            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    @FXML
    public void search() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void logout() throws IOException {
        Agent A = (Agent) cacheM.getAcct();
        A.deleteLabels();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, new CacheManager(this.cacheM.getDbM())));

    }

    @FXML
    public void getApp() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aFormStorage.fxml"));
        sceneM.changeScene(loader, new aFormStorage(sceneM, cacheM));
    }

    @FXML
    public void viewReviewed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aGetApplication.fxml"));
        sceneM.changeScene(loader, new aGetApplication(sceneM, cacheM));
    }

    @FXML
    public void scores() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/Scores.fxml"));
        sceneM.changeScene(loader, new Scores(sceneM, cacheM));
    }
    @FXML public void settings() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/settingPage.fxml"));
        sceneM.changeScene(loader, new settingPage(sceneM, cacheM,this));
    }
}
