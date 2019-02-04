package UI;

import Datatypes.Agent;
import Datatypes.Manufacturer;
import Managers.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class aRegister {

    private SceneManager sceneM;
    private CacheManager cacheM;

    public aRegister(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML private Button aRegister;
    @FXML private Button search;
    @FXML private Button back;

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField fullName;
    @FXML private TextField email;
    @FXML private TextField phone;
    @FXML private TextField ttbID;


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
        // public Agent(String username, String password, String fullName, String email, String phone, int ttbID)
        Agent a = new Agent(username.getText(), password.getText(), fullName.getText(), email.getText(),
                phone.getText(), Integer.parseInt(ttbID.getText()));

        String createManufacturer = "INSERT INTO Agents (ttbid, username, password, fullname, email, phone) " +
                "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prepStmt = cacheM.getDbM().getConnection().prepareStatement(createManufacturer);
            prepStmt.setInt(1,a.getTtbID());
            prepStmt.setString(2,a.getUsername());
            prepStmt.setString(3,a.getEncryptor().encode(a.getPassword()));
            prepStmt.setString(4,a.getFullName());
            prepStmt.setString(5,a.getEmail());
            prepStmt.setString(6,a.getPhone());
            prepStmt.executeUpdate();
            prepStmt.close();

        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }
}
