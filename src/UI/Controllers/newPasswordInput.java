package UI.Controllers;

import Datatypes.Agent;
import Datatypes.Manufacturer;
import Datatypes.StageContainingScene;
import Managers.*;

import Managers.SceneManager;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.jfoenix.controls.JFXPasswordField;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.EventListener;
import java.util.Properties;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    @FXML
    public void reset(Connection connection) throws SQLException, java.io.IOException {
        if (password.equals(confirmP)){
            String setData = "UPDATE ? SET PASSWORD = ? WHERE EMAIL = ?";
            PreparedStatement ps = connection.prepareStatement(setData);
            ps.setString(1, Agent ? "AGENTS":"REPRESENTATIVES");
            ps.setString(2, confirmP.getText());
            ps.setString(3, email);
            ResultSet rs = ps.executeQuery();
            System.out.println("Password reset! Please login to continue");
            super.getStage().close();
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
            //sceneM.changeScene(loader, new LoginPage(sceneM, cacheM));
        }
        else{
            passwordMessage.setTextFill(Color.RED);
            passwordMessage.setText("Passwords do not match. Please try again.");
        }
    }

}
