package UI.Controllers;

import Datatypes.Agent;
import Datatypes.Alcy;
import Datatypes.agentScore;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;


/**
 * @author Clay Oshiro-Leavitt
 * @version It 4
 */
public class Scores {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private ArrayList<agentScore> agents;

    public Scores(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;

    }
    @FXML private FlowPane loadScores;

    @FXML private Label score;
    @FXML private Label approved;
    @FXML private Label numDenied;
    @FXML private Label passed;
    @FXML private JFXButton back;
    @FXML private ImageView rookie;
    @FXML private ImageView gettingStarted;
    @FXML private ImageView seasonedPro;
    @FXML private ImageView threePointer;
    @FXML private ImageView backFromTheDead;
    @FXML private ImageView strikeThree;
    @FXML private ImageView passingTheBuck;
    @FXML private ImageView expertProcrastination;
    @FXML private ImageView expertDelegation;
    @FXML private ImageView pleasantMood;
    @FXML private ImageView yesMan;
    @FXML private ImageView goodVibrations;
    @FXML private ImageView highStandards;
    @FXML private ImageView denied;
    @FXML private ImageView itsAllWrong;
    @FXML private ImageView aNewFriend;
    @FXML private ImageView inquisitive;
    @FXML private ImageView alcyView;
    @FXML private Text alcyLabel;

    /**
     * @author Clay Oshiro-Leavitt & Trevor Dowd
     * initializes the scores page, populating the page with:
     * user statistics (score, forms approved, passed, denied)
     * leaderboard showing current top ranking of agents (based off of score)
     * user achievements
     */
    @FXML public void initialize() {
        Connection connection = cacheM.getDbM().getConnection();
        Agent A = (Agent) cacheM.getAcct();
        A.calculateScore(connection);
        A.rankAgents(connection);
        score.setText(Integer.toString(A.getScore()));
        approved.setText(Integer.toString(A.getNumberApproved()));
        numDenied.setText(Integer.toString(A.getNumberDenied()));
        passed.setText(Integer.toString(A.getNumberPassed()));

        agents = ((Agent) cacheM.getAcct()).getAgentPlaces();

        Alcy alcy = cacheM.getAlcy();
        alcy.summonAlcy(alcyView, alcyLabel);
        alcy.sayAScores();

        for (agentScore agent : agents) {
            Pane leaderBoard;

            try {
                leaderBoard = FXMLLoader.load(getClass().getResource("/UI/Views/leaderboard.fxml"));
                Node hbox = leaderBoard.getChildren().get(0);
                if (hbox instanceof HBox) {

                    Node uName = ((HBox) hbox).getChildren().get(0);
                    Node score = ((HBox) hbox).getChildren().get(1);

                    ((Label) uName).setText(agent.getAgentName());
                    System.out.println(agent.getAgentScore());
                    ((Label) score).setText(Integer.toString(agent.getAgentScore()));

                }

                    System.out.println(agent.getAgentName() + " not in leaderboard. Adding.");
                    loadScores.getChildren().add(leaderBoard);


            } catch (IOException e) {
                e.printStackTrace();

            }
        }

        // checking for achievements being unlocked
        int numberOfForms = A.getNumberProcessed();

        // processed 1+ forms
        if (numberOfForms>0){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/1.png");
                 rookie.setImage(image);
        }
        // processed 100+ forms
        if (numberOfForms>99){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/2.png");
                 gettingStarted.setImage(image);
        }
        // processed 10000+ forms
        if (numberOfForms>9999){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/3.png");
                 seasonedPro.setImage(image);
        }
        // approved 1+ forms
        if (A.getNumberApproved() > 0){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/10.png");
                 pleasantMood.setImage(image);
        }
        // approved 100+ forms
        if (A.getNumberApproved() > 99){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/11.png");
                 yesMan.setImage(image);
        }
        // approved 10000+ forms
        if (A.getNumberApproved() > 9999){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/12.png");
                 goodVibrations.setImage(image);
        }
        // denied 1+ forms
        if (A.getNumberDenied() > 0){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/14.png");
                 denied.setImage(image);
        }
        // denied 100+ forms
        if (A.getNumberDenied() > 99){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/13.png");
                 highStandards.setImage(image);
        }
        // denied 10000+ forms
        if (A.getNumberDenied() > 9999){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/15.png");
                 itsAllWrong.setImage(image);
        }
        // passed 1+ forms to another agent
        if (A.getNumberPassed() > 0){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/7.png");
                 passingTheBuck.setImage(image);
        }
        // passed 100+ forms to another agent
        if (A.getNumberPassed() > 99){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/8.png");
                 expertProcrastination.setImage(image);
        }
        // passed 10000+ forms to another agent
        if (A.getNumberPassed() > 9999){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/9.png");
                 expertDelegation.setImage(image);
        }

        // reviewed a form that was previously denied
        if (A.isGotOldForms()==false){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/5.png");
                 backFromTheDead.setImage(image);
        }
        // processed 3 forms in a row
        if (A.getRowAD() > 2){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/4.png");
                 threePointer.setImage(image);
        }

        // passed three forms in a row
        if (A.getRowP() > 2){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/6.png");
                 strikeThree.setImage(image);
        }

            Image image2 = new Image("UI/Icons/SoftEngAchieveColor/17.png");
            inquisitive.setImage(image2);

            Image image3 = new Image("UI/Icons/SoftEngAchieveColor/18.png");
            aNewFriend.setImage(image3);

    }
    @FXML
    public void back() throws IOException {

        agents.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
        sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));

    }
}
