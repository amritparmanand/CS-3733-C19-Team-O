package UI;

import Datatypes.Manufacturer;
import Managers.CacheManager;
import Managers.DatabaseManager;
import Managers.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.security.crypto.bcrypt.*;
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

    @FXML private Button register;

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField confirmP;
    @FXML private TextField fullName;
    @FXML private TextField email;
    @FXML private TextField phone;
    @FXML private TextField repID;
    @FXML private TextField companyName;


    @FXML
    @SuppressWarnings("Duplicates")
    public void register() throws IOException {
        if(password.getText().equals(confirmP.getText())){
            Manufacturer m = new Manufacturer(username.getText(), password.getText(), fullName.getText(), email.getText(),
                    phone.getText(), Integer.parseInt(repID.getText()),companyName.getText());

            String createManufacturer = "INSERT INTO Representatives(repid, username, password, fullname, companyname, email, phone) " +
                    "VALUES(?,?,?,?,?,?,?)";

            try {
                PreparedStatement prepStmt = cacheM.getDbM().getConnection().prepareStatement(createManufacturer);
                prepStmt.setInt(1,m.getRepID());
                prepStmt.setString(2,m.getUsername());
                prepStmt.setString(3,m.getEncryptor().encode(m.getPassword()));
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
        else{
            System.out.println("Please confirm password!");
        }
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

    @FXML
    public void validateButton(){
        if(username.getText().isEmpty() ||
                password.getText().isEmpty() ||
                confirmP.getText().isEmpty() ||
                fullName.getText().isEmpty() ||
                email.getText().isEmpty() ||
                phone.getText().isEmpty() ||
                companyName.getText().isEmpty() ||
                repID.getText().isEmpty()){
            register.setDisable(true);
        }
        else{
            register.setDisable(false);
        }
    }
}
