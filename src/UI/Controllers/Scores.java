package UI.Controllers;

import Datatypes.Agent;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.Connection;

public class Scores {
    private SceneManager sceneM;
    private CacheManager cacheM;

    public Scores(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;

    }

    @FXML private Label score;
    @FXML private Label approved;
    @FXML private Label denied;
    @FXML private Label passed;
    @FXML private JFXButton back;
    @FXML private Label first;
    @FXML private Label second;
    @FXML private Label third;

    @FXML public void initialize() {
        Connection connection = cacheM.getDbM().getConnection();
        Agent A = (Agent) cacheM.getAcct();
        A.rankAgents(connection);
        A.calculateScore(connection);
        score.setText(Integer.toString(A.getScore()));
        approved.setText(Integer.toString(A.getNumberApproved()));
        denied.setText(Integer.toString(A.getNumberDenied()));
        passed.setText(Integer.toString(A.getNumberPassed()));
        first.setText(A.getAgentPlaces().get(0).getAgentName());
        second.setText(A.getAgentPlaces().get(1).getAgentName());
        third.setText(A.getAgentPlaces().get(2).getAgentName());


    }
    @FXML
    public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
        sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
    }
}
