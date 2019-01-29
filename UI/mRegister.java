package UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class mRegister {

    @FXML private Button mRegister;
    @FXML private Button search;
    @FXML private Button back;

    /**
     * Model
     */
    private SceneManager sm;

    /**
     * Default constructor
     */
    public mRegister() {
        this.sm = new SceneManager();
    }

    /**
     * Change scene
     * @param event
     * @throws IOException
     */
    @FXML
    public void changeScene(MouseEvent event) throws IOException {
        if(event.getSource() == mRegister){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/mHomepage.fxml"));
            sm.changeScene(root, mRegister);
        }
        else if(event.getSource() == search){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/SearchPage.fxml"));
            sm.changeScene(root, search);
        }
        else if(event.getSource() == back){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/LoginPage.fxml"));
            sm.changeScene(root, back);
        }
    }
}
