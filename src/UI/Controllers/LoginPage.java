package UI.Controllers;

import Managers.CacheManager;
import Managers.SceneManager;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;

public class LoginPage implements SerialPortDataListener {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private BCryptPasswordEncoder passwordDecoder = new BCryptPasswordEncoder();
    SerialPort ports[] = SerialPort.getCommPorts();
    SerialPort serialPort = null;
    int chipID = -1;

    @FXML
    private RadioButton m;
    @FXML
    private RadioButton a;
    @FXML
    private Button register;
    @FXML
    private Button login;
    @FXML
    private TextField id;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label loginMessage;
    @FXML
    private Button programChip;
    @FXML
    private Button about;
    @FXML
    private JFXButton help;



    public LoginPage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML
    public void initialize() {
        programChip.setVisible(false);
        if (ports.length > 0) {
            this.serialPort = ports[ports.length - 1];
            this.serialPort.openPort();

            serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING,0,0);

            serialPort.addDataListener(new SerialPortDataListener() {
                @Override
                public int getListeningEvents() {
                    return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                }

                @Override
                public synchronized void serialEvent(SerialPortEvent serialPortEvent) {
//                    byte[] newData = new byte[8];
//                    serialPort.readBytes(newData, 8);
                    byte[] newData = new byte[serialPort.bytesAvailable()];
                    serialPort.readBytes(newData, newData.length);
                    String buf = new String(newData);
                    buf = buf.trim();
//                    InputStream in = serialPort.getInputStream();
//                    String buf = "";
//                    for(int i = 0; i< 16; i++)
//                    {
//                        try {
//                            buf += (char) in.read();
//                        }
//                        catch(Exception e)
//                        {
//                            break;
//                        }
//                    }
//                    //System.out.println("output" + buf.split("\n")[0]);
//                    buf = buf.split("\n")[0];
//                    buf = buf.replaceAll("\"","");
//                    buf = buf.trim();
                    System.out.println(buf);
                    //MAGIC
                    if ((buf != null && !(buf.length() < 4))) {
                        chipID = Integer.parseInt(buf);
                        String uname = cacheM.getDbM().aFindUsername(chipID);
                        String hashedPassword = cacheM.getDbM().aFindPassword(chipID);
                        if ((uname != null) && (uname != "")) {
                            System.out.println("Login Successful!");
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
                            Platform.runLater(() -> {
                                id.setText(String.valueOf(chipID));
                                username.setText(uname);
                                password.setText(hashedPassword);
                                cacheM.setAcct(cacheM.getDbM().aCreate(chipID));
                                try {
                                    sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                serialPort.closePort();
                                return;

                            });
                        }
                        else{
                            Platform.runLater(() -> {
                                loginMessage.setTextFill(Color.RED);
                                loginMessage.setText("Badge has not yet been programmed");
                                programChip.setVisible(true);
                                programChip.setDisable(false);
                                return;

                            });

                        }
                    }
                    else if (buf.equals(""))
                    {
                        Platform.runLater(() -> {
                            loginMessage.setTextFill(Color.RED);
                            loginMessage.setText("Badge cannot be authenticated. Access Denied!");
                            programChip.setVisible(false);
                            return;

                        });
                    }
                }
            });
        }
    }

    @Override
    public int getListeningEvents() {
        return 0;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
    }

    @FXML
    @SuppressWarnings("Duplicates")
    public void login() throws IOException {
        int theID = Integer.parseInt(id.getText());

        if (m.isSelected()) {
            String uname = cacheM.getDbM().mFindUsername(theID);
            String hashedPassword = cacheM.getDbM().mFindPassword(theID);

            if (uname.equals(username.getText()) && passwordDecoder.matches(password.getText(), hashedPassword)) {
                cacheM.setAcct(cacheM.getDbM().mCreate(theID));
                System.out.println("Login Successful!");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
                sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
            } else {
                loginMessage.setTextFill(Color.RED);
                loginMessage.setText("Incorrect username or password. Please try again.");
                System.out.println("The username and password you entered did not match our records. Please double-check and try again.");
            }
        } else if (a.isSelected()) {
            String uname = cacheM.getDbM().aFindUsername(theID);
            String hashedPassword = cacheM.getDbM().aFindPassword(theID);
            if (uname.equals(username.getText()) && passwordDecoder.matches(password.getText(), hashedPassword)) {
                cacheM.setAcct(cacheM.getDbM().aCreate(theID));
                System.out.println("Login Successful!");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
                sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));
            } else {
                loginMessage.setTextFill(Color.RED);
                loginMessage.setText("Incorrect username or password. Please try again.");
                System.out.println("The username and password you entered did not match our records. Please double-check and try again.");
            }
        }
    }

    @FXML
    public void register() throws IOException {
        if (m.isSelected()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mRegister.fxml"));
            sceneM.changeScene(loader, new mRegister(sceneM, cacheM));
        } else if (a.isSelected()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aRegister.fxml"));
            sceneM.changeScene(loader, new aRegister(sceneM, cacheM));
        }
    }

    @FXML
    public void programChip() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aRegister.fxml"));
        sceneM.changeScene(loader, new aRegister(sceneM, cacheM, chipID));
    }

    @FXML
    public void search() throws IOException {
        // Search
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void aboutUs() throws IOException {
        // Search
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/AboutUs.fxml"));
        sceneM.changeScene(loader, new AboutUs(sceneM, cacheM));
    }

    @FXML
    public void validateButton() {
        if (m.isSelected() || a.isSelected()) {
            register.setDisable(false);
            if (username.getText().isEmpty() || password.getText().isEmpty() || id.getText().isEmpty()) {
                login.setDisable(true);
            } else {
                login.setDisable(false);
            }
        } else {
            register.setDisable(true);
            login.setDisable(true);
        }
    }
    @FXML 
    public void about() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/AboutUs.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void help() throws IOException{
        helpBox helpBox = new helpBox();
        helpBox.loginPage();
        Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/helpBox.fxml"));
        helpPopWindow(root);
    }

    public void helpPopWindow(Parent root){
//        Image image = new Image(getClass().getResourceAsStream("/HelpGifs/login.gif"));
//        ImageView helpGif = new ImageView(image);
        Stage stage;
        stage = new Stage();
//        helpGif.setFitHeight(480);
//        helpGif.setFitWidth(270);
//        helpGif.setImage(image);
        stage.setScene(new Scene(root));
        stage.setTitle("Help Window");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();


    }
}
