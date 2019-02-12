package UI;

import Managers.*;

import Managers.SceneManager;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.EventListener;

import javafx.scene.paint.Color;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LoginPage implements SerialPortDataListener {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private BCryptPasswordEncoder passwordDecoder = new BCryptPasswordEncoder();
    SerialPort ports[] = SerialPort.getCommPorts();
    SerialPort serialPort = ports[ports.length - 1];
    @FXML private RadioButton m;
    @FXML private RadioButton a;
    @FXML private Button register;
    @FXML private Button login;
    @FXML private TextField id;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Label loginMessage;


    public LoginPage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        serialPort.openPort();
        serialPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {
                if (serialPortEvent.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                    return;
                byte[] newData = new byte[serialPort.bytesAvailable()];
                serialPort.readBytes(newData, newData.length);
                String buf = new String(newData);
                buf = buf.trim();
                System.out.println(buf);
                if((buf != null && !(buf.length() < 2))) {
                    buf = buf.trim();
                    int loginID = Integer.parseInt(buf);
                    String uname = cacheM.getDbM().aFindUsername(loginID);
                    String hashedPassword = cacheM.getDbM().aFindPassword(loginID);
                    id.setText(String.valueOf(loginID));
                    username.setText(uname);
                    password.setText(hashedPassword);
                    if((uname != null) && (uname != "")) {
                    cacheM.setAcct(cacheM.getDbM().aCreate(loginID));
                    System.out.println("Login Successful!");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
                    Platform.runLater(() -> {
                        try {
                            sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        serialPort.closePort();
                        return;

                    });
                }
                }
            }
        });
    }

    @Override
    public int getListeningEvents() { return 0;}
    @Override
    public void serialEvent(SerialPortEvent event)
    {
    }

    @FXML
    @SuppressWarnings("Duplicates")
    public void login() throws IOException {
        int theID = Integer.parseInt(id.getText());

        if(m.isSelected()){
            String uname = cacheM.getDbM().mFindUsername(theID);
            String hashedPassword = cacheM.getDbM().mFindPassword(theID);

            if(uname.equals(username.getText()) && passwordDecoder.matches(password.getText(),hashedPassword)) {
                cacheM.setAcct(cacheM.getDbM().mCreate(theID));
                System.out.println("Login Successful!");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
                sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
            }
            else{
                loginMessage.setTextFill(Color.RED);
                loginMessage.setText("Incorrect username or password. Please try again.");
                System.out.println("The username and password you entered did not match our records. Please double-check and try again.");
            }
        }

        else if(a.isSelected()){
            String uname = cacheM.getDbM().aFindUsername(theID);
            String hashedPassword = cacheM.getDbM().aFindPassword(theID);
            if(uname.equals(username.getText()) && passwordDecoder.matches(password.getText(),hashedPassword)) {
                cacheM.setAcct(cacheM.getDbM().aCreate(theID));
                System.out.println("Login Successful!");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
                sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
            }
            else{
                loginMessage.setTextFill(Color.RED);
                loginMessage.setText("Incorrect username or password. Please try again.");
                System.out.println("The username and password you entered did not match our records. Please double-check and try again.");
            }
        }
    }

    @FXML public void register() throws IOException {
        if(m.isSelected()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mRegister.fxml"));
            sceneM.changeScene(loader, new mRegister(sceneM, cacheM));
        }
        else if (a.isSelected()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aRegister.fxml"));
            sceneM.changeScene(loader, new aRegister(sceneM, cacheM));
        }
    }
    @FXML public void search() throws IOException {
        // Search
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }
    @FXML public void validateButton(){
        if(m.isSelected() || a.isSelected()){
            register.setDisable(false);
            if (username.getText().isEmpty() || password.getText().isEmpty() || id.getText().isEmpty()) {
                login.setDisable(true);
            } else {
                login.setDisable(false);
            }
        }
        else{
            register.setDisable(true);
            login.setDisable(true);
        }
    }
}