package UI.Controllers;

import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

/**
 * @author Jonathan Luna
 * @version It 4
 * password reset verification page, sends an email and has a field to put in your verification code
 */
public class passwordReset {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private String ResetKey;

    @FXML private TextField email;
    @FXML private TextField keyInput;
    @FXML private RadioButton m;
    @FXML private RadioButton a;
    @FXML private Label keyMessage;
    @FXML private Label emailMessage;


    public passwordReset(SceneManager sceneM, CacheManager cacheM){
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    /**
     * Sends a reset email if an account is associated with the given email
     */
    @FXML
    public void sendResetEmail(){
        if (m.isSelected()) {
            System.out.println("Checking for email");
            if (cacheM.getDbM().mFindEmail(email.getText())) {
                this.send("ttb.database@gmail.com","OnyxOgopogo",email.getText(),"TTB Password Reset",generateEmailBody());
                emailMessage.setText("Password reset email sent");
                System.out.println("Password reset email sent");
            }
        } else if (a.isSelected()) {
            System.out.println("checking for email");
            if (cacheM.getDbM().aFindEmail(email.getText())) {
                this.send("ttb.database@gmail.com","OnyxOgopogo",email.getText(),"TTB Password Reset",generateEmailBody());
                emailMessage.setText("Password reset email sent");
                System.out.println("Password reset email sent");
            }
        }
    }

    /**
     * changes page only if the key matches the most recent one sent
     * @throws IOException
     */
    @FXML
    public void checkKey() throws IOException{
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

    @FXML
    public void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/startPage.fxml"));
        sceneM.changeScene(loader, new startPage(sceneM, cacheM));
    }

    /**
     * Generates the body of the email
     * @return the email body
     */
    private String generateEmailBody(){
        String Body = "Hello TTB Database User," + "\n";
        Body += "A password reset was requested on your account, if this was not you, please ignore this email." + "\n";
        Body += "Your password reset key is: ";
        Body += randomKeyGenerator();

        return Body;
    }

    /**
     * Generates a random key and saves it to the page
     * @return a random alpha numeric 5 character string
     */
    private String randomKeyGenerator(){
        String key = "";
        Random r = new Random();

        String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 5; i++) {
            key += (alphabet.charAt(r.nextInt(alphabet.length())));
        }
        this.ResetKey = key;
        return key;
    }

    /**
     * Sends the email, takes in email information
     * @param from
     * @param password
     * @param to
     * @param sub
     * @param msg
     */
    private static void send(String from,String password,String to,String sub,String msg) {
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });
        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
