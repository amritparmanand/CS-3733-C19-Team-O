package UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.springframework.security.crypto.bcrypt.*;

import java.io.IOException;

public class mRegister {

    public static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
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
    @SuppressWarnings("Duplicates")
    @FXML
    public void changeScene(MouseEvent event) throws IOException {
        if(event.getSource() == mRegister){
            //manufacturer data populated here
            //String hashedPassword = passwordEncoder.encode(//text from password field cast to CharSequence);
            //populate the password data with that string ^^
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/mHomepage.fxml"));
            sm.changeScene(root, mRegister);
        }
        else if(event.getSource() == search){
            //manufacturer data populated here
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/SearchPage.fxml"));
            sm.changeScene(root, search);
        }
        else if(event.getSource() == back){
            Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/LoginPage.fxml"));
            sm.changeScene(root, back);
        }
    }
}
