package UI.Controllers;

import Datatypes.StageContainingScene;
import Managers.*;
import Managers.SceneManager;
import com.jfoenix.controls.JFXPasswordField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.sql.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class newPasswordInput extends StageContainingScene {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private String email;
    private Boolean Agent;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @FXML private JFXPasswordField password;
    @FXML private JFXPasswordField confirmP;
    @FXML private Button reset;
    @FXML private Label passwordMessage;

    public newPasswordInput(SceneManager sceneM, CacheManager cacheM, String email, Boolean Agent, Stage stage) {
        super(stage);
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.email = email;
        this.Agent = Agent;
    }
    //fix connection
    @FXML
    public void reset(Connection connection) throws SQLException{
        if (password.equals(confirmP)){
            String setData = "UPDATE ? SET PASSWORD = ? WHERE EMAIL = ?";
            PreparedStatement ps = cacheM.getDbM().getConnection().prepareStatement(setData);
            ps.setString(1, Agent ? "AGENTS":"REPRESENTATIVES");
            ps.setString(2, confirmP.getText());
            ps.setString(3, email);
            ps.executeUpdate();
            System.out.println("Password reset! Please login to continue");
            super.getStage().close();
        }
        else{
            passwordMessage.setTextFill(Color.RED);
            passwordMessage.setText("Passwords do not match.");
        }
    }

}
