package UI.Controllers;

import Datatypes.Agent;
import Datatypes.Manufacturer;
import Managers.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import org.springframework.security.access.method.P;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Percy Jiang & Gabe Entov
 * @version It 2
 * Controller for aRegister of UI
 */
public class aRegister {

    private SceneManager sceneM;
    private CacheManager cacheM;
    private String phoneNumber;
    private String agentEmail;
    private String ID;
    private String agentUsername;
    int ttbIDFromChip = -1;


    public aRegister(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    public aRegister(SceneManager sceneM, CacheManager cacheM, int chipID) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.ttbIDFromChip = chipID;

    }

    @FXML private JFXButton aRegister;
    @FXML private JFXButton back;
    @FXML private JFXTextField username;
    @FXML private JFXPasswordField password;
    @FXML private JFXPasswordField confirmP;
    @FXML private JFXTextField fullName;
    @FXML private JFXTextField email;
    @FXML private JFXTextField phone;
    @FXML private JFXTextField ttbID;
    @FXML private Label usernameMessage;
    @FXML private Label phoneMessage;
    @FXML private Label emailMessage;
    @FXML private Label IDMessage;
    @FXML private Label passwordMessage;
    @FXML private JFXButton help;


    @FXML
    public void initialize()
    {
        if(ttbIDFromChip >= 0)
            ttbID.setText(String.valueOf(ttbIDFromChip));
    }
    @FXML
    public void search() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, cacheM));
    }

    @FXML
    @SuppressWarnings("Duplicates")
    public void register() throws IOException {
        if(confirmPass(password.getText(), confirmP.getText())){
            Agent a = new Agent(username.getText(), password.getText(), fullName.getText(), email.getText(), phone.getText(), Integer.parseInt(ttbID.getText()));
            cacheM.setAcct(a);
            cacheM.getAcct().register(cacheM.getDbM().getConnection());
            System.out.println("Registration Successful! Welcome!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/aHomepage.fxml"));
            sceneM.changeScene(loader, new aHomepage(sceneM, cacheM));

        }
        else{
            System.out.println("Please confirm password!");
        }

    }

    @FXML
    public void validateButton(){
        phoneNumber = phone.getText().trim();
        agentEmail = email.getText().trim();
        agentUsername = username.getText().trim();
        ID = ttbID.getText().trim();
        if(username.getText().isEmpty() ||
                password.getText().isEmpty() ||
                confirmP.getText().isEmpty() ||
                fullName.getText().isEmpty() ||
                email.getText().isEmpty() ||
                phone.getText().isEmpty() ||
                ttbID.getText().isEmpty() ||
                !validAgentPhone(phoneNumber) ||
                !validAgentEmail(agentEmail) ||
                invalidID(ID) ||
                !confirmPass(password.getText(), confirmP.getText()) ||
                !validUsername(agentUsername)
        ){
            if(!validAgentPhone(phoneNumber) && !phoneNumber.isEmpty()){
                phoneMessage.setTextFill(Color.RED);
                phoneMessage.setText("Invalid Phone Number");
            }
            if(!validAgentEmail(agentEmail) && !agentEmail.isEmpty()){
                emailMessage.setTextFill(Color.RED);
                emailMessage.setText("Invalid Email. Must be a .gov Address");
            }
            if(invalidID(ID) && !ID.isEmpty()){
                IDMessage.setTextFill(Color.RED);
                IDMessage.setText("Invalid ID");
            } else{
                IDMessage.setText("");
            }
            if(validAgentPhone(phoneNumber)){
                phoneMessage.setText("");
            }
            if(validAgentEmail(agentEmail)){
                emailMessage.setText("");
            }
            if(!confirmPass(password.getText(), confirmP.getText())){
                passwordMessage.setTextFill(Color.RED);
                passwordMessage.setText("Passwords do not match");

            }
            if(confirmPass(password.getText(), confirmP.getText())){
                passwordMessage.setText("");
            }
            if(!validUsername(agentUsername)){
                usernameMessage.setTextFill(Color.RED);
                usernameMessage.setText("Username cannot have spaces, special characters");
            }
            if(validUsername(agentUsername) && !agentUsername.isEmpty()){
                usernameMessage.setText("");
            }

            aRegister.setDisable(true);
        }
        else{
            aRegister.setDisable(false);
        }
    }

    /**
     * @author Clay Oshiro-Leavitt
     * @version It 2
     * @since It 2
     * @param phoneNumber phone number to be checked
     * checks the agent phone number for Agent Registration form
     * will accept US number with the following conditions
     * 1 prefix optional
     * area code is required
     * delimiters between number groups are optional
     * if delimiters are used, can use spaces, dashes as dividers between number groups
     * alphanumeric format is allowed after area code
     * @return true if is valid number, false if not
     */
    @FXML
    public boolean validAgentPhone(String phoneNumber){
        if(phoneNumber.matches("^[0]{8,20}$")){
            return false;
        } else if(phoneNumber.matches("(^([0-9]( |-|.|/)?)?(\\(?[0-9]{3}\\)?|[0-9]{3})( |-|.|/)?([0-9]{3}( |-|.|/)?[0-9]{4}|[a-zA-Z0-9]{7})$)")){
            //System.out.println("valid number");
            return true;
        }else
        return false;
    }

    /**
     * @author Clay Oshiro-Leavitt
     * @version It 2
     * @param email email to be checked
     * checks the agent email
     * it must be a .gov account
     * can have lower case, upper case, numbers
     * @return true if valid email, false if invalid
     */
    @FXML
    public boolean validAgentEmail(String email){
        if(email.matches("^([a-zA-Z0-9_\\-\\.]+)@+([a-zA-Z]+).gov$")){
            return true;
        }else return false;
    }

    /**
     * @author Clay Oshiro-Leavitt
     * @version It 2
     * @param ID TTBID to be checked
     * checks to make sure that:
     * ID is not negative
     * ID does not contain any letters
     * @return true if is Invalid, false if valid
     */
    @FXML
    public boolean invalidID(String ID){
        if(ID.isEmpty() || ID.matches("^[a-zA-Z]+") || Integer.parseInt(ID) < 0) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * @author Clay Oshiro-Leavitt
     * @version It 2
     * @param password password from password field
     * @param confirm password for confirmation field
     * @return true if passwords are the same, false if not
     */
    @FXML
    public boolean confirmPass(String password, String confirm){
        if(password.equals(confirm)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * @author Clay Oshiro-Leavitt
     * @version It 2
     * @param username Username to be checked
     * username cannot have any spaces, special characters
     * @return true if the username is valid, false if not
     */
    @FXML
    public boolean validUsername(String username){
        if(username.matches("^[a-zA-Z0-9]+")){
            return true;
        }else return false;
    }

    public void help() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/UI/Views/helpaRegister.fxml"));
        helpPopWindow(root);
    }

    public void helpPopWindow(Parent root){
        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Help Window");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}
