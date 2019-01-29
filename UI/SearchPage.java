package UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SearchPage {

    @FXML private Button back;

    /**
     * Model
     */
    private SceneManager sm;

    /**
     * Default constructor
     */
    public SearchPage() {
        this.sm = new SceneManager();
    }

    @FXML
    public void changeScene(MouseEvent event) throws IOException {
        if(event.getSource() == back){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/LoginPage.fxml"));
            sm.changeScene(root, back);
        }
    }
}
