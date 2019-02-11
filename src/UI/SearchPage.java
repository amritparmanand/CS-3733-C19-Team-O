package UI;

import Datatypes.SearchResult;
import Managers.CacheManager;
import Managers.DatabaseManager;
import Managers.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * @author Amrit Parmanand & Robert Rinearson
 * @version It 1
 * Controller for SearchPage of UI
 */
public class SearchPage {
    private SceneManager sceneM;
    private CacheManager cacheM;

    public SearchPage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    ArrayList<SearchResult> searchList = new ArrayList<SearchResult>();

    @FXML private ScrollPane scroll;
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
    public void loadAlcohol(ArrayList<SearchResult> searchList) {
        Pane alcResult = null;
        for(SearchResult result: searchList) {
            try {
                alcResult = FXMLLoader.load(getClass().getResource("/UI/Views/alcBox.fxml"));
                Node vbox = alcResult.getChildren().get(0);
                if (vbox instanceof VBox) {
                    Node fName = ((VBox) vbox).getChildren().get(1);
                    Node bName = ((VBox) vbox).getChildren().get(2);
                    Node aType = ((VBox) vbox).getChildren().get(3);

                    ((Label) fName).setText(result.getFancifulName());
                    ((Label) bName).setText(result.getCompanyName());
                    ((Label) aType).setText(result.getAlcoholType());
                }
                searchResults.getChildren().add(alcResult);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML public void initialize() throws SQLException{
        search();
    }

    public void search() throws SQLException {
        ResultSet rs = cacheM.getDbM().getApprovedApplications();
        searchResults.getChildren().clear();
        searchList.clear();
        while (rs.next()) {
            SearchResult result = new SearchResult();
//            result.setFancifulName(rs.getString(""));
            result.setFancifulName(rs.getString("FANCIFULNAME"));
            result.setCompanyName(rs.getString("BRANDNAME"));
            result.setPhLevel(rs.getDouble("PHLEVEL"));
            result.setAlcohol(rs.getDouble("ALCOHOLPERCENT"));
            result.setYear(rs.getInt("VINTAGEYEAR"));
            if(rs.getInt("PRODUCTTYPE") == 0){
                result.setWine(true);
                result.setBeer(false);
                result.setLiquor(false);
                result.setAlcoholType("Wine");
            }else if(rs.getInt("PRODUCTTYPE") == 1){
                result.setWine(false);
                result.setBeer(true);
                result.setLiquor(false);
                result.setAlcoholType("Beer");
            }
            if(searchBox.getText().isEmpty()) {
                if (!beerCheck.isSelected() && !wineCheck.isSelected()) {
                    searchList.add(result);
                } else {
                    if (beerCheck.isSelected() && result.isBeer()) {
                        searchList.add(result);
                    } else if (beerCheck.isSelected() && !result.isBeer()) {
                    }
                    if (wineCheck.isSelected() && result.isWine()) {
                        searchList.add(result);
                    } else if (wineCheck.isSelected() && !result.isWine()) {
                    }
                }
            } else if(!searchBox.getText().isEmpty() &&
                    ((result.getFancifulName().toLowerCase().contains(searchBox.getText().toLowerCase()))
                    || (result.getCompanyName().toLowerCase().contains(searchBox.getText().toLowerCase())))){
                if (!beerCheck.isSelected() && !wineCheck.isSelected()) {
                    searchList.add(result);
                } else {
                    if (beerCheck.isSelected() && result.isBeer()) {
                        searchList.add(result);
                    } else if (beerCheck.isSelected() && !result.isBeer()) {
                    }
                    if (wineCheck.isSelected() && result.isWine()) {
                        searchList.add(result);
                    } else if (wineCheck.isSelected() && !result.isWine()) {
                    }
                }
            }
        }
        loadAlcohol(searchList);
    }
}
