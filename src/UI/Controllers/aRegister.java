package UI.Controllers;

import Datatypes.Agent;
import Managers.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

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

    public aRegister(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
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
    @FXML private Label phoneMessage;
    @FXML private Label emailMessage;
    @FXML private Label IDMessage;


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
        if(password.getText().equals(confirmP.getText())){
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
        if(username.getText().isEmpty() ||
                password.getText().isEmpty() ||
                confirmP.getText().isEmpty() ||
                fullName.getText().isEmpty() ||
                email.getText().isEmpty() ||
                phone.getText().isEmpty() ||
                ttbID.getText().isEmpty() ||
                !validAgentPhone(phoneNumber) ||
                !validAgentEmail(agentEmail)
        ){
            if(!validAgentPhone(phoneNumber)){
                phoneMessage.setTextFill(Color.RED);
                phoneMessage.setText("Invalid Phone Number. Please Try again.");
            }
            if(!validAgentEmail(agentEmail)){
                emailMessage.setTextFill(Color.RED);
                emailMessage.setText("Invalid Email. Must Be A .gov Address");
            }
            if(validAgentPhone(phoneNumber)){
                phoneMessage.setText("");
            }
            if(validAgentEmail(agentEmail)){
                emailMessage.setText("");
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
}
