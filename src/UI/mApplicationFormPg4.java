package UI;

import Managers.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.SQLException;

public class mApplicationFormPg4 {
    private SceneManager sceneM;
    private CacheManager cacheM;

    @FXML private Button previous;
    @FXML private Button search;
    @FXML private Button back;
    @FXML private Button submit;

    public mApplicationFormPg4(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    @FXML
    public void previousPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mApplicationFormPg3.fxml"));
        sceneM.changeScene(loader, new mApplicationFormPg2(sceneM, cacheM));
    }


    @FXML
    public void submit() throws IOException {
        String createForm = "INSERT INTO Forms(" +
                "formID int	constraint Forms_pk	primary key, " +
                "brewerNumber int,	" +
                "productSource int,	" +
                "serialNumber int,	" +
                "productType int,	" +
                "brandName varchar(20),	" +
                "fancifulName varchar(20),	" +
                "applicantName varchar(40),	" +
                "mailingAddress varchar(80), " +
                "formula varchar(20), " +
                "grapeVarietal varchar(20),	" +
                "appelation varchar(20), " +
                "phoneNumber bigint, " +
                "emailAddress varchar(30),	" +
                "dateOfApplication date, " +
                "printName varchar(40),	" +
                "beerWineSpirit int, " +
                "alcoholPercent double,	" +
                "vintageYear int, " +
                "phLevel double)";

        try {

            cacheM.getDbM().getStmt().execute(createForm);

        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }

    @FXML
    public void searchPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/SearchPage.fxml"));
        sceneM.changeScene(loader, new SearchPage(sceneM, cacheM));
    }

    @FXML
    public void goToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/mHomepage.fxml"));
        sceneM.changeScene(loader, new mHomepage(sceneM, cacheM));
    }
}