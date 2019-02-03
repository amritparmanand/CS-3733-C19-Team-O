package UI;

import UI.Managers.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class aGetApplication {

    @FXML private Button backToAHome;
    @FXML private Button search;
    //@FXML private VBox getApp;

    /**
     * Model
     */
    private SceneManager sm;

    /**
     * Default constructor
     */
    public aGetApplication() {this.sm = new SceneManager(); }

    @FXML
    public void changeScene(MouseEvent event) throws IOException {
        if(event.getSource() == backToAHome){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/aHomepage.fxml"));
            sm.changeScene(root, backToAHome);
        }
        else if(event.getSource() == search){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/SearchPage.fxml"));
            sm.changeScene(root, search);
        }
    }
}
