package UI.Controllers;

import Datatypes.Agent;
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
import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class Scores {
    private SceneManager sceneM;
    private CacheManager cacheM;

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
    @FXML private Label first;
    @FXML private Label second;
    @FXML private Label third;
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

    @FXML public void initialize() {
        Connection connection = cacheM.getDbM().getConnection();
        Agent A = (Agent) cacheM.getAcct();
        A.rankAgents(connection);
        A.calculateScore(connection);
        score.setText(Integer.toString(A.getScore()));
        approved.setText(Integer.toString(A.getNumberApproved()));
        numDenied.setText(Integer.toString(A.getNumberDenied()));
        passed.setText(Integer.toString(A.getNumberPassed()));
        first.setText(A.getAgentPlaces().get(0).getAgentName());
        second.setText(A.getAgentPlaces().get(1).getAgentName());
        third.setText(A.getAgentPlaces().get(2).getAgentName());

        ArrayList<agentScore> agents = ((Agent) cacheM.getAcct()).getAgentPlaces();

        for (agentScore agent : agents) {
            Pane leaderBoard;
            System.out.println(agent.getAgentName());
            try {
                leaderBoard = FXMLLoader.load(getClass().getResource("/UI/Views/leaderboard.fxml"));
                Node hbox = leaderBoard.getChildren().get(0);
                if (hbox instanceof HBox) {

                    Node uName = ((HBox) hbox).getChildren().get(0);
                    Node score = ((HBox) hbox).getChildren().get(1);

                    ((Label) uName).setText(agent.getAgentName());
                    ((Label) score).setText(Integer.toString(agent.getAgentScore()));

                }
                loadScores.getChildren().add(leaderBoard);

            } catch (IOException e) {
                e.printStackTrace();

            }
        }
System.out.println(A);
        int numberOfForms = A.getNumberProcessed();
        if (numberOfForms>0){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/1.png");
                 rookie.setImage(image);
        }
        if (numberOfForms>99){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/2.png");
                 gettingStarted.setImage(image);
        }
        if (numberOfForms>9999){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/3.png");
                 seasonedPro.setImage(image);
        }
        if (A.getNumberApproved() > 0){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/10.png");
                 pleasantMood.setImage(image);
        }
        System.out.println(A.getNumberApproved());

        if (A.getNumberApproved() > 99){
            System.out.println(A.getNumberApproved() + " got here");
            Image image = new Image("UI/Icons/SoftEngAchieveColor/11.png");
                 yesMan.setImage(image);
        }
        if (A.getNumberApproved() > 9999){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/12.png");
                 goodVibrations.setImage(image);
        }
        if (A.getNumberDenied() > 0){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/14.png");
                 denied.setImage(image);
        }
        if (A.getNumberDenied() > 99){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/13.png");
                 highStandards.setImage(image);
        }
        if (A.getNumberDenied() > 9999){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/15.png");
                 itsAllWrong.setImage(image);
        }
        if (A.getNumberPassed() > 0){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/7.png");
                 passingTheBuck.setImage(image);
        }
        if (A.getNumberPassed() > 99){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/8.png");
                 expertProcrastination.setImage(image);
        }
        if (A.getNumberPassed() > 9999){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/9.png");
                 expertDelegation.setImage(image);
        }
        if (A.isGotOldForms()==true){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/5.png");
                 backFromTheDead.setImage(image);
        }
        if (A.getRowAD() > 2){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/6.png");
                 threePointer.setImage(image);
        }
        if (A.getRowP() > 2){
            Image image = new Image("UI/Icons/SoftEngAchieveColor/4.png");
                 strikeThree.setImage(image);
        }


    }
    @FXML
    public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
        sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
    }
}
