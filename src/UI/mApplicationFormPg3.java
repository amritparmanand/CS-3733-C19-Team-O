package UI;

import UI.Managers.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class mApplicationFormPg3 {

    @FXML private Button next;
    @FXML private Button previous;
    @FXML private Button search;
    @FXML private Button back;

    /**
     * Model
     */
    private SceneManager sm;

    /**
     * Default constructor
     */
    public mApplicationFormPg3() {
        this.sm = new SceneManager();
    }

    @SuppressWarnings("Duplicates")
    @FXML
    public void changeScene(MouseEvent event) throws IOException {
        if(event.getSource() == next){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/mApplicationFormPg4.fxml"));
            sm.changeScene(root, next);
        }
        else if(event.getSource() == previous){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/mApplicationFormPg2.fxml"));
            sm.changeScene(root, previous);
        }
        else if(event.getSource() == search){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/SearchPage.fxml"));
            sm.changeScene(root, search);
        }
        else if(event.getSource() == back){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/mHomepage.fxml"));
            sm.changeScene(root, back);
        }
    }
}
