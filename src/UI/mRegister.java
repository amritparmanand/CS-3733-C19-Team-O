package UI;

import Datatypes.Manufacturer;
import Managers.CacheManager;
import Managers.DatabaseManager;
import Managers.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class mRegister {

    private SceneManager sceneM;
    private CacheManager cacheM;

    public mRegister(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField fullName;
    @FXML private TextField email;
    @FXML private TextField phone;
    @FXML private TextField repID;
    @FXML private TextField companyName;


    @FXML
    public void register() throws IOException {
        Manufacturer m = new Manufacturer(username.getText(), password.getText(), fullName.getText(), email.getText(),
                phone.getText(), Integer.parseInt(repID.getText()),companyName.getText());

        String createManufacturer = "INSERT INTO Representatives(repid, username, password, fullname, companyname, email, phone) " +
                "VALUES(?,?,?,?,?,?,?)";
        System.out.println(createManufacturer);

        try {
            PreparedStatement prepStmt = cacheM.getDbM().getConnection().prepareStatement(createManufacturer);
            prepStmt.setInt(1,1);
            prepStmt.setString(2,m.getUsername());
            prepStmt.setString(3,m.getPassword());
            prepStmt.setString(4,m.getFullName());
            prepStmt.setString(5,m.getCompanyName());
            prepStmt.setString(6,m.getEmail());
            prepStmt.setString(7,m.getPhone());
            prepStmt.executeUpdate();
            prepStmt.close();

        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }

    @FXML
    public void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, cacheM));
    }

    @FXML
    public void search() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }
}
