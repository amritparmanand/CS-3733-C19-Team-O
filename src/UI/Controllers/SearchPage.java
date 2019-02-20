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
    ResultSet approvedResults;

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
    private JFXButton previous;
    @FXML
    private JFXButton next;
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
    private JFXTextField delim;


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
        pageNum.setText("0");
        previous.setDisable(true);

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

        approvedResults = getApprovedApplications(oldSearch, type);

        searchTether.setRs(approvedResults);
        searchTether.setBools(beerCheck.isSelected(), wineCheck.isSelected(), liquorCheck.isSelected());
        searchTether.notifyObservers(0, next);

    }

    public ResultSet getApprovedApplications(String condition, String type) throws SQLException {
        return cacheM.getApprovedApplications(cacheM.getDbM().getConnection(), condition, type);
    }

    /**
     * @author Samuel Silver And Jonathan Luna
     * downloads
     * all searches from a query into a csv file
     */
    @FXML
    public void download() {
        String path = "";

        JFileChooser chooser = new JFileChooser();
        String choosertitle = "Select a destination";

        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        chooser.setAcceptAllFileFilterUsed(false);

        int r = chooser.showSaveDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().getAbsolutePath();

            try {
                System.out.println(path);
                PrintWriter writer = new PrintWriter(path + "/" + "search-results.csv", "UTF-8");

                String delimi = delim.getText();
                if (delimi == "" || delimi == null) {
                    delimi = ",";
                }

                writer.println("sep=" + delimi);
                writer.println("FANCIFUL NAME" + delimi +
                        "COMPANY NAME" + delimi +
                        "ALCOHOL TYPE" + delimi +
                        "ALCOHOL TYPE2" + delimi +
                        "PH LEVEL" + delimi +
                        "ALCOHOL PERCENT" + delimi +
                        "YEAR");

                int row = approvedResults.getRow();
                if (approvedResults.first()) {

                    do {
                        //holder variable to hold the type of alcohol for printing
                        String alcoholType = approvedResults.getString("PRODUCTTYPE");

                        writer.println(approvedResults.getString("FANCIFULNAME") + delimi +
                                approvedResults.getString("BRANDNAME") + delimi +
                                alcoholType + delimi + alcoholType + delimi +
                                approvedResults.getString("PHLEVEL") + delimi +
                                approvedResults.getString("ALCOHOLPERCENT") + delimi +
                                approvedResults.getString("VINTAGEYEAR"));
                    }
                    while (approvedResults.next());
                }

                writer.close();

                approvedResults.absolute(row);

            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                System.out.println("Unsupported encoding exception.");
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("User cancelled the operation");
        }
    }

    @FXML
    public void next() {
        int page = Integer.parseInt(this.pageNum.getText());
        page++;
        notifyOthers(page);
    }

    @FXML
    public void prev() {
        int page = Integer.parseInt(this.pageNum.getText());
        page--;
        notifyOthers(page);
    }

    public void notifyOthers(int page) {
        this.pageNum.setText((page) + "");
        try {
            searchTether.notifyObservers(page, next);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (page <= 0)
            previous.setDisable(true);
        else
            previous.setDisable(false);
    }
}