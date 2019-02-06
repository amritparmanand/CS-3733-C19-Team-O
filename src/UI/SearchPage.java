package UI;

import Datatypes.SearchResult;
import Managers.CacheManager;
import Managers.DatabaseManager;
import Managers.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchPage {
    private SceneManager sceneM;
    private CacheManager cacheM;

    public SearchPage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    ArrayList<SearchResult> searchList = new ArrayList<SearchResult>();

    @FXML private Button back;
    @FXML private TextField searchBox;
    @FXML private CheckBox beerCheck;
    @FXML private CheckBox liquorCheck;
    @FXML private CheckBox wineCheck;
    @FXML private TextField phLow;
    @FXML private TextField phHigh;
    @FXML private TextField alcoholLow;
    @FXML private TextField alcoholHigh;
    @FXML private TextField yearLow;
    @FXML private TextField yearHigh;
    @FXML private FlowPane searchResults;
    @FXML private Button searchButton;
    @FXML
    public void back() throws IOException {
        //sceneM.backScene();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, cacheM));
    }

    @FXML
    public void loadAlcohol(ActionEvent event) {
        Pane alcResult = null;
        try {
            alcResult = FXMLLoader.load(getClass().getResource("/UI/Views/alcBox.fxml"));
            searchResults.getChildren().add(alcResult);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void search() throws SQLException {
        ResultSet rs = cacheM.getDbM().getApplications();
        while (rs.next()) {
            SearchResult result = new SearchResult();
//            result.setFancifulName(rs.getString(""));
            result.setFancifulName(rs.getString("FANCIFULNAME"));
            result.setCompanyName(rs.getString("BRANDNAME"));
            result.setPhLevel(rs.getDouble("PHLEVEL"));
            result.setAlcohol(rs.getDouble("ALCOHOLPERCENT"));
            result.setYear(rs.getInt("VINTAGEYEAR"));
            if(rs.getInt("BEERWINESPIRIT") == 0){
                result.setWine(true);
                result.setBeer(false);
                result.setLiquor(false);

            }
        }

    }
}
