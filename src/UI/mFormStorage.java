package UI;

import UI.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class mFormStorage {

    @FXML private Button backToMHome;
    @FXML private Button search;
    //@FXML private VBox getApp;

    /**
     * Model
     */
    private SceneManager sm;

    /**
     * Default constructor
     */
    public mFormStorage() {this.sm = new SceneManager(); }

    @FXML
    public void changeScene(MouseEvent event) throws IOException {
        if(event.getSource() == backToMHome){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/mHomepage.fxml"));
            sm.changeScene(root, backToMHome);
        }
        else if(event.getSource() == search){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/SearchPage.fxml"));
            sm.changeScene(root, search);
        }
    }
}