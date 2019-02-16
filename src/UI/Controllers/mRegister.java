package UI.Controllers;

import Datatypes.Manufacturer;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.IOException;

/**
 * @author Percy Jiang & Gabe Entov & Elizabeth Del Monaco
 * @version It 2
 * Controller for mRegister of UI
 */
public class mRegister {

    private SceneManager sceneM;
    private CacheManager cacheM;
    private String phoneNumber;
    private String manuEmail;
    private String manuUsername;
    private String ID;


    public mRegister(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML private Button register;
    @FXML private Label IDMessage;
    @FXML private Label emailMessage;
    @FXML private Label phoneMessage;
    @FXML private Label passwordMessage;
    @FXML private Label usernameMessage;
    @FXML private JFXTextField username;
    @FXML private JFXPasswordField password;
    @FXML private JFXPasswordField confirmP;
    @FXML private JFXTextField fullName;
    @FXML private JFXTextField email;
    @FXML private JFXTextField phone;
    @FXML private JFXTextField repID;
    @FXML private JFXTextField companyName;

    @FXML
    @SuppressWarnings("Duplicates")
    public void register() throws IOException {
        if(password.getText().equals(confirmP.getText())){
            Manufacturer m = new Manufacturer(username.getText(), password.getText(), fullName.getText(),
                    email.getText(), phone.getText(), Integer.parseInt(repID.getText()),companyName.getText());
            cacheM.setAcct(m);
            cacheM.getAcct().register(cacheM.getDbM().getConnection());
            System.out.println("Registration Successful! Welcome!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
            sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
        }
        else{
            System.out.println("Please confirm password!");
        }
    }

    @FXML public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, cacheM));
    }

    @FXML public void search() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML public void validateButton(){
        phoneNumber = phone.getText().trim();
        manuEmail = email.getText().trim();
        ID = repID.getText().trim();
        manuUsername = username.getText().trim();

        if(username.getText().isEmpty() ||
                password.getText().isEmpty() ||
                confirmP.getText().isEmpty() ||
                fullName.getText().isEmpty() ||
                email.getText().isEmpty() ||
                phone.getText().isEmpty() ||
                companyName.getText().isEmpty() ||
                repID.getText().isEmpty() ||
                !validManuPhone(phoneNumber) ||
                !validManuEmail(manuEmail) ||
                invalidID(ID) ||
                !confirmPass(password.getText(), confirmP.getText())
        ){
            register.setDisable(true);
            if(!validManuPhone(phoneNumber) && !phoneNumber.isEmpty()){
                phoneMessage.setTextFill(Color.RED);
                phoneMessage.setText("Invalid Phone Number. Please Try Again");
            }
            if(!validManuEmail(manuEmail) && !manuEmail.isEmpty()){
                emailMessage.setTextFill(Color.RED);
                emailMessage.setText("Invalid Email Address. Please Try Again");
            }
            if(invalidID(ID) && !ID.isEmpty()){
                IDMessage.setTextFill(Color.RED);
                IDMessage.setText("Invalid ID");
            } else{
                IDMessage.setText("");
            }
            if(validManuPhone(phoneNumber)){
                phoneMessage.setText("");
            }
            if(validManuEmail(manuEmail)){
                emailMessage.setText("");
            }
            if(!confirmPass(password.getText(), confirmP.getText())){
                passwordMessage.setTextFill(Color.RED);
                passwordMessage.setText("Passwords do not match");

            }
            if(confirmPass(password.getText(), confirmP.getText())){
                passwordMessage.setText("");
            }

            if(!validUsername(manuUsername) && !manuUsername.isEmpty()){
                usernameMessage.setTextFill(Color.RED);
                usernameMessage.setText("Username cannot have spaces, special characters");
            }
            if(validUsername(manuUsername)){
                usernameMessage.setText("");
            }

        }
        else{
            register.setDisable(false);
        }
    }

    /**
     * @author Clay Oshiro-Leavitt
     * @version It 2
     * @param phoneNumber phone number to be checked
     * checks the manufacturer phone number for Manufacturer Registration form
     * will accept US number with the following conditions
     * 1 prefix optional
     * area code is required
     * delimiters between number groups are optional
     * if delimiters are used, can use spaces, dashes, back slashes as dividers between number groups
     * alphanumeric format is allowed after area code
     * @return true if is valid number, false if not
     */

    @FXML
    public boolean validManuPhone(String phoneNumber){
        if(phoneNumber.matches("^[0]{8,20}$")){
            return false;
        } else if(phoneNumber.matches("(^([0-9]( |-|.|/)?)?(\\(?[0-9]{3}\\)?|[0-9]{3})( |-|.|/)?([0-9]{3}( |-|.|/)?[0-9]{4}|[a-zA-Z0-9]{7})$)")){
            return true;
        }else
            return false;

    }

    /**
     * @author Clay Oshiro-Leavitt
     * @version It 2
     * @param email email to be checked
     * checks the manufacturer email
     * it must be a .gov account
     * can have lower case, upper case, numbers
     * @return true if valid email, false if invalid
     */
    @FXML
    public boolean validManuEmail(String email){
        if(email.matches("^([a-zA-Z0-9_\\-\\.]+)@+([a-zA-Z]+).+([a-zA-Z]{2,3})$")){
            return true;
        }else return false;
    }

    /**
     * @author Clay Oshiro-Leavitt
     * @version It 2
     * @param ID repID to be checked
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
}
