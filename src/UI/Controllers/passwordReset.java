package UI.Controllers;

import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * @author Jonathan Luna
 * @version It 4
 * password reset verification page, sends an email and opens passwordKeyPopup
 */
public class passwordReset {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private String ResetKey;

    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField ID;
    @FXML
    private JFXRadioButton m;
    @FXML
    private JFXRadioButton a;
    @FXML
    private Label emailMessage;
    @FXML
    private JFXButton go;


    public passwordReset(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    /**
     * Sends a reset email if an account is associated with the given email
     */
    @FXML
    public void sendResetEmail() throws SQLException, java.io.IOException {

        String getData = "SELECT COUNT(*) AS rowCount FROM " + (a.isSelected() ? "AGENTS" : "REPRESENTATIVES") + " WHERE EMAIL = ? AND " + (a.isSelected() ? "TTBID" : "REPID") + " = ?";
        PreparedStatement ps = cacheM.getDbM().getConnection().prepareStatement(getData, ResultSet.TYPE_SCROLL_INSENSITIVE);
        ps.setString(1, email.getText());
        ps.setString(2, ID.getText());
        ResultSet rs = ps.executeQuery();
        rs.next();
        if (rs.getInt("rowCount") == 1) {
            System.out.println("Trying to email");
            this.send("ttb.database@gmail.com", "OnyxOgopogo", email.getText(), "TTB Password Reset", generateEmailBody());
        }

        FXMLLoader popLoader = new FXMLLoader(getClass().getResource("/UI/Views/passwordKeyPopup.fxml"));
        sceneM.popWindowLoader(popLoader, new passwordKeyPopup(sceneM, cacheM, ResetKey, email.getText(), a.isSelected(), new Stage()), "Password Key Input");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, cacheM));
    }

    /**
     * Generates the body of the email
     *
     * @return the email body
     */
    private String generateEmailBody() {
        String Body = "Hello TTB Database User," + "\n";
        Body += "A password reset was requested on your account, if this was not you, please ignore this email." + "\n";
        Body += "Your password reset key is: ";
        Body += randomKeyGenerator();

        return Body;
    }

    /**
     * Generates a random key and saves it to the page
     *
     * @return a random alpha numeric 5 character string
     */
    private String randomKeyGenerator() {
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
     *
     * @param from
     * @param password
     * @param to
     * @param sub
     * @param msg
     */
    private static void send(String from, String password, String to, String sub, String msg) {
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

    @FXML
    public void validateButton() {
        if (m.isSelected() || a.isSelected()) {
            go.setDisable(false);
            if (ID.getText().isEmpty() || email.getText().isEmpty()) {
                go.setDisable(true);
            } else {
                go.setDisable(false);
            }
        } else {
            go.setDisable(true);
        }
    }

    @FXML
    public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, cacheM));
    }
}
