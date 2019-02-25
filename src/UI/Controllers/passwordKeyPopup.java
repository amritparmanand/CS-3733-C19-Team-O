package UI.Controllers;

import Datatypes.StageContainingScene;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Jonathan Luna ft. Same Silver
 * @version It 4
 * pops up and prompts user for the key they received as an email. Gives an error if a bad key is inputted
 *
 */
public class passwordKeyPopup extends StageContainingScene {
    @FXML private JFXTextField keyInput;
    @FXML private JFXButton checkKey;
    @FXML private Label keyMessage;

    private SceneManager sceneM;
    private CacheManager cacheM;
    private String ResetKey;
    private String email;
    private Boolean Agent;



    public passwordKeyPopup(SceneManager sceneM, CacheManager cacheM, String ResetKey, String email, Boolean Agent, Stage stage){
        super(stage);
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.ResetKey = ResetKey;
        this.email = email;
        this.Agent = Agent;
    }

    /**
     * changes page only if the key matches the most recent one sent
     * @throws IOException
     */
    @FXML
    public void checkKey() throws IOException {
        if (keyInput.getText().equals(ResetKey)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/newPasswordInput.fxml"));
            sceneM.changeScene(loader, new newPasswordInput(sceneM, cacheM, email, Agent, new Stage()));
        }
        else{
            keyMessage.setTextFill(Color.RED);
            keyMessage.setText("Key not valid. Please try again.");
            System.out.println("The key you entered was not valid. Please double-check your email and try again.");
        }
    }
}
