package UI.Controllers;

import Datatypes.Agent;
import Datatypes.Manufacturer;
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
import java.sql.Statement;
import java.util.EventListener;
import java.util.Properties;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class newPasswordInput {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @FXML private JFXPasswordField password;
    @FXML private JFXPasswordField confirmP;
    @FXML private Button reset;

    public newPasswordInput(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

}
