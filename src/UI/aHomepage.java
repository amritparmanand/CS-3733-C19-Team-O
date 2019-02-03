package UI;

import UI.Managers.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class aHomepage {

    @FXML private Button back;
    @FXML private Button search;
    @FXML private VBox getApp;

    /**
     * Model
     */
    private SceneManager sm;

    /**
     * Default constructor
     */
    public aHomepage() {
        this.sm = new SceneManager();
    }

    @FXML
    public void changeScene(MouseEvent event) throws IOException {
        if(event.getSource() == back){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/LoginPage.fxml"));
            sm.changeScene(root, back);
        }
        else if(event.getSource() == search){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/SearchPage.fxml"));
            sm.changeScene(root, search);
        }
        else if(event.getSource() == getApp){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/aGetApplication.fxml"));
            sm.changeSceneWithV(root, getApp);
        }
    }
}
