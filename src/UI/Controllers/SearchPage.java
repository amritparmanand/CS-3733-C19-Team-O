package UI.Controllers;

import Datatypes.SearchResult;
import Fuzzy.*;
import Managers.CacheManager;
import Managers.SceneManager;
import Observer.IObservable;
import Observer.ObservablePane;
import Observer.SearchTether;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
    boolean SQL = false;
    boolean Levi = false;
    boolean DLevi = false;
    boolean hiddenS = true;

    String oldSearch = "";
    private int searchPageNumber = 1;
    private int lowerBound = (searchPageNumber - 1) * 15;

    public SearchPage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        //subscribe number of searches
    }

    @FXML
    public void initialize() {
        ObservablePane op = null;

        for (int i = 0; i < 15; i++) {
            try {
                Pane pane = FXMLLoader.load(getClass().getResource("/UI/Views/alcBox.fxml"));
                op = new ObservablePane(pane, searchResults, sceneM);
            } catch (IOException e) {
                System.out.println(e.fillInStackTrace());
            }
            searchTether.subscribe(op);
        }
    }

    SearchTether searchTether = new SearchTether();

    @FXML
    private ScrollPane scroll;
    @FXML
    private Button back;
    @FXML
    private TextField searchBox;
    @FXML
    private JFXCheckBox beerCheck;
    @FXML
    private JFXCheckBox liquorCheck;
    @FXML
    private JFXCheckBox wineCheck;
    @FXML
    private TextField phLow;
    @FXML
    private TextField phHigh;
    @FXML
    private TextField alcoholLow;
    @FXML
    private TextField alcoholHigh;
    @FXML
    private TextField yearLow;
    @FXML
    private TextField yearHigh;
    @FXML
    private FlowPane searchResults;
    @FXML
    private Button searchButton;
    @FXML
    private Label searchSuggest;
    @FXML
    private Label didYouMean;
    @FXML
    private MenuItem sqlSearch;
    @FXML
    private MenuItem lSearch;
    @FXML
    private MenuItem dlSearch;
    @FXML
    private MenuItem hiddenScore;
    @FXML
    private MenuButton algChoose;
    @FXML
    private Label pageNum;
    @FXML
    private JFXButton pageButton;


    @FXML
    public void searchSQL() throws IOException {
        SQL = true;
        Levi = false;
        DLevi = false;
        hiddenS = false;
        algChoose.setText("SQL");
    }

    @FXML
    public void searchLevi() throws IOException {
        SQL = false;
        Levi = true;
        DLevi = false;
        hiddenS = false;
        algChoose.setText("Levenshtein");
    }

    @FXML
    public void searchDLevi() throws IOException {
        SQL = false;
        Levi = false;
        DLevi = true;
        hiddenS = false;
        algChoose.setText("Damerau-Levenshtein");
    }

    @FXML
    public void searchHiddenS() throws IOException {
        SQL = false;
        Levi = false;
        DLevi = false;
        hiddenS = true;
        algChoose.setText("Hidden Score");
    }

    @FXML
    public void back() throws IOException {
        //sceneM.backScene();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, cacheM));
    }


    @FXML
    public void searchSuggest() throws SQLException {
        searchBox.setText(searchSuggest.getText());
        search();
        didYouMean.setText("");
        searchSuggest.setText("");
    }

    @SuppressWarnings("Duplicates")
    @FXML
    public void search() throws SQLException {
        pageNum.setText("1");

        if (searchBox.getText() == oldSearch)
            return;
        oldSearch = searchBox.getText();

        // Perform fuzzy search based on user's choice
        String suggestion = "";
        FuzzyContext fc = new FuzzyContext();
        if (SQL) {
            fc.setF(new SQL());
        } else if (Levi) {
            fc.setF(new Levenshtein());
        } else if (DLevi) {
            fc.setF(new Damerau_Levenshtein());
        } else if (hiddenS) {
            fc.setF(new hiddenScore());
        }
        suggestion = fc.fuzzy(searchBox.getText(), cacheM.getDbM().getConnection());
        didYouMean.setText("Did you mean: ");
        searchSuggest.setText(suggestion);

        String type = "(PRODUCTTYPE = ";

        if (beerCheck.isSelected())
            type += "'MALT' OR PRODUCTTYPE = ";
        if (wineCheck.isSelected())
            type += "'WINE' OR PRODUCTTYPE = ";
        if (liquorCheck.isSelected())
            type += "'DISTILLED' OR PRODUCTTYPE = ";
        if (!beerCheck.isSelected() && !wineCheck.isSelected() && !liquorCheck.isSelected())
            type = "TRUE";

        if (!type.equals("TRUE"))
            type = type.substring(0, type.length() - 18) + ")";

        ResultSet rs = getApprovedApplications(oldSearch, type);
        searchTether.setRs(rs);
        searchTether.setBools(beerCheck.isSelected(), wineCheck.isSelected(), liquorCheck.isSelected());
        searchTether.notifyObservers();

        // For each of the approved applications  NOW we have to limit this punk
        //make the array

//        while (rs.next()) {
//            SearchResult result = new SearchResult();
//            result.setFancifulName(rs.getString("FANCIFULNAME"));
//            result.setCompanyName(rs.getString("BRANDNAME"));
//            result.setPhLevel(rs.getString("PHLEVEL"));
//            result.setAlcohol(rs.getString("ALCOHOLPERCENT"));
//            result.setYear(rs.getString("VINTAGEYEAR"));
//            result.setProductType(rs.getString("PRODUCTTYPE"));
//            //IMAGE REEEEEEE
//            // result set label image
////
//
//            if (searchBox.getText().isEmpty()) {
//                System.out.println("foo");
//                if (!beerCheck.isSelected() && !wineCheck.isSelected() && !liquorCheck.isSelected()) {
//                    searchTether.addSearchList(result);
//                } else {
//                    if (beerCheck.isSelected() && result.getProductType().toLowerCase().equals("malt")) {
//                        searchTether.addSearchList(result);
//                    }
//                    if (wineCheck.isSelected() && result.getProductType().toLowerCase().equals("wine")) {
//                        searchTether.addSearchList(result);
//                    }
//                    if (liquorCheck.isSelected() && result.getProductType().toLowerCase().equals("distilled")) {
//                        searchList.add(result);
//                    }
//                }
//            } else if (!searchBox.getText().isEmpty() &&
//                    ((result.getFancifulName().toLowerCase().contains(searchBox.getText().toLowerCase()))
//                            || (result.getCompanyName().toLowerCase().contains(searchBox.getText().toLowerCase())))) {
//                //if ^^ this is not empty, we do a fuzzy search, and check if it's empty again
//                //see if fuzzy returns anything
//                //if it does: set did you mean to "Did you Mean"
//                //set the searchSuggest to return value
//                System.out.println("bar");
//                if (!beerCheck.isSelected() && !wineCheck.isSelected()) {
//                    searchList.add(result);
//                } else {
//                    if (beerCheck.isSelected() && result.getProductType().toLowerCase().equals("malt")) {
//                        searchList.add(result);
//                    }
//                    if (wineCheck.isSelected() && result.getProductType().toLowerCase().equals("wine")) {
//                        searchList.add(result);
//                    }
//                    if (liquorCheck.isSelected() && result.getProductType().toLowerCase().equals("distilled")) {
//                        searchList.add(result);
//                    }
//                }
//            }
//            //I added a conditional else so that the else doesn't always print stuff... I know it's ugly
//            else if (!searchBox.getText().isEmpty() &&
//                    !((result.getFancifulName().toLowerCase().contains(searchBox.getText().toLowerCase()))
//                            || (result.getCompanyName().toLowerCase().contains(searchBox.getText().toLowerCase())))) { //a bunch of approved forms go into this else because they don't pass the first two conditions
//                didYouMean.setText("Did you mean: ");
//                searchSuggest.setText(suggestion);
//            }
//        }


//        loadAlcohol(searchTether.getChildren());

        //populate the hos in the for
    }

    //put them together
//    public void searchBuild(){
//        searchResults.getChildren().clear();
//        for(int i = lowerBound; i <lowerBound+15; i++){
//            loadAlcohol(searchList);
//        }
//    }

    public ResultSet getApprovedApplications(String condition, String type) throws SQLException {
        return cacheM.getApprovedApplications(cacheM.getDbM().getConnection(), condition, type); //we have to limit so it doesnt overload our program with 1M icons
    }

    /**
     * @author Jonathan Luna and Liz Del Monaco
     * downloads all searches from a query into a csv file
     */
    public void download() {
//        System.out.println(";)");
//
//        String path = "";
//
//        JFileChooser chooser = new JFileChooser();
//        String choosertitle = "Select a destination";
//
//        chooser.setCurrentDirectory(new java.io.File("."));
//        chooser.setDialogTitle(choosertitle);
//        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//
//        chooser.setAcceptAllFileFilterUsed(false);
//
//        int r = chooser.showSaveDialog(null);
//
//        if (r == JFileChooser.APPROVE_OPTION) {
//            path = chooser.getSelectedFile().getAbsolutePath();
//
//            try {
//                System.out.println(path);
//                PrintWriter writer = new PrintWriter(path + "/" + "search-results.csv", "UTF-8");
//
//                writer.println("sep=;");
//                writer.println("FANCIFUL NAME;COMPANY NAME;ALCOHOL TYPE;ALCOHOL TYPE2;PH LEVEL;ALCOHOL PERCENT;YEAR");
//
//                for (SearchResult s : searchList) {
//                    //holder variable to hold the type of alcohol for printing
//                    String alcoholType = s.getProductType();
//
//                    writer.println(s.getFancifulName() + ";" + s.getCompanyName() + ";" + s.getAlcoholType() + ";" + alcoholType + ";" + s.getPhLevel() + ";" + s.getAlcohol() + ";" + s.getYear());
//                }
//                writer.close();
//            } catch (FileNotFoundException e) {
//                System.out.println("File not found.");
//                e.printStackTrace();
//            } catch (UnsupportedEncodingException e) {
//                System.out.println("Unsupported encoding exception.");
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("User cancelled the operation");
//        }


    }

    @FXML
    public void next() {
        try {
            int page = Integer.parseInt(this.pageNum.getText());
            this.pageNum.setText((page + 1) + "");
            searchTether.notifyObservers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}