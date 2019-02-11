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
    @FXML private Label searchSuggest;
    @FXML private Label didYouMean;

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

//    @FXML public void initialize() throws SQLException{
//        search();
//    }

    public void search() throws SQLException {

//]        String search = searchBox.getText().toUpperCase();
        String suggestion = cacheM.getDbM().fuzzy2(searchBox.getText());
        didYouMean.setText("Did you mean: ");
        searchSuggest.setText(suggestion);

//        ResultSet rs = getApprovedApplications();
//        searchResults.getChildren().clear();
//        searchList.clear();
//        while (rs.next()) {
//            SearchResult result = new SearchResult();
////            result.setFancifulName(rs.getString(""));
//            result.setFancifulName(rs.getString("FANCIFULNAME"));
//            result.setCompanyName(rs.getString("BRANDNAME"));
//            result.setPhLevel(rs.getDouble("PHLEVEL"));
//            result.setAlcohol(rs.getDouble("ALCOHOLPERCENT"));
//            result.setYear(rs.getInt("VINTAGEYEAR"));
//            if(rs.getString("PRODUCTTYPE") == "WINE"){
//                result.setWine(true);
//                result.setBeer(false);
//                result.setLiquor(false);
//                result.setAlcoholType("Wine");
//            }else if(rs.getString("PRODUCTTYPE") == "BEER"){
//                result.setWine(false);
//                result.setBeer(true);
//                result.setLiquor(false);
//                result.setAlcoholType("Beer");
//            }
//
//            if(searchBox.getText().isEmpty()) {
//                System.out.println("foo");
//                if (!beerCheck.isSelected() && !wineCheck.isSelected()) {
//                    searchList.add(result);
//                } else {
//                    if (beerCheck.isSelected() && result.isBeer()) {
//                        searchList.add(result);
//                    } else if (beerCheck.isSelected() && !result.isBeer()) {
//                    }
//                    if (wineCheck.isSelected() && result.isWine()) {
//                        searchList.add(result);
//                    } else if (wineCheck.isSelected() && !result.isWine()) {
//                    }
//                }
//            }
//            else if(!searchBox.getText().isEmpty() &&
//                    ((result.getFancifulName().toLowerCase().contains(searchBox.getText().toLowerCase()))
//                    || (result.getCompanyName().toLowerCase().contains(searchBox.getText().toLowerCase())))){
//                //if ^^ this is not empty, we do a fuzzy search, and check if it's empty again
//                //see if fuzzy returns anything
//                //if it does: set did you mean to "Did you Mean"
//                //set the searchSuggest to return value
//                System.out.println("bar");
//                if (!beerCheck.isSelected() && !wineCheck.isSelected()) {
//                    searchList.add(result);
//                }
//                else {
//                    if (beerCheck.isSelected() && result.isBeer()) {
//                        searchList.add(result);
//                    } else if (beerCheck.isSelected() && !result.isBeer()) {
//                    }
//                    if (wineCheck.isSelected() && result.isWine()) {
//                        searchList.add(result);
//                    } else if (wineCheck.isSelected() && !result.isWine()) {
//                    }
//                }
//            }
//            else{
//                //System.out.println("nanana");
//                didYouMean.setText("Did you mean:");
//                searchSuggest.setText(suggestion);
//                System.out.println(suggestion);
//            }
//        }
//        loadAlcohol(searchList);
    }

    public ResultSet getApprovedApplications() throws SQLException{
        String retrieve = "SELECT * FROM APPLICATIONS JOIN FORMS " +
                "ON FORMS.FORMID = APPLICATIONS.FORMID " +
                "WHERE APPLICATIONS.STATUS='APPROVED'";
        ResultSet rset = cacheM.getDbM().getStmt().executeQuery(retrieve);
        return rset;
    }
}
