package UI.Controllers;

import Managers.CacheManager;
import Managers.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class passwordKeyPopup {
    @FXML private TextField keyInput;
    @FXML private Label keyMessage;

    private SceneManager sceneM;
    private CacheManager cacheM;
    private String ResetKey;

    /**
     * changes page only if the key matches the most recent one sent
     * @throws IOException
     */
    @FXML
    public void checkKey() throws IOException {
        if (keyInput.getText().equals(ResetKey)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/newPasswordInput.fxml"));
            sceneM.changeScene(loader, new newPasswordInput(sceneM, cacheM));
        }
        else{
            keyMessage.setTextFill(Color.RED);
            keyMessage.setText("Key not valid. Please try again.");
            System.out.println("The key you entered was not valid. Please double-check your email and try again.");
        }
    }
}
