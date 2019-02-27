package UI.Controllers;

import Datatypes.SearchResult;
import Fuzzy.*;
import Managers.CacheManager;
import Managers.SceneManager;
import Managers.SearchManager;
import Observer.IObservable;
import Observer.ObservablePane;
import Observer.SearchTether;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.opencsv.CSVWriter;
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
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import javax.swing.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Amrit Parmanand & Robert Rinearson & Percy
 * @version It 3
 * Controller for SearchPage of UI
 */
public class SearchPage {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private SearchManager searchM;

    private AdvancedSearchPage advancedSearchPage;

    String oldSearch = "";
    LinkedList<SearchResult> srArr;
    String searchType;

    public SearchPage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
        this.searchM = new SearchManager();
    }

    @FXML
    public void initialize() throws SQLException {
        advancedSearchPage = new AdvancedSearchPage(sceneM, cacheM, searchM);
        TextFields.bindAutoCompletion(searchBox, cacheM.getForm().autoSearch(cacheM.getDbM().getConnection()));
        cacheM.getAlcy().summonAlcy(alcyView, alcyLabel);
        cacheM.getAlcy().saySearchResult();
        ObservablePane op = null;

        for (int i = 0; i < 24; i++) {
            try {
                Pane pane = FXMLLoader.load(getClass().getResource("/UI/Views/alcBox.fxml"));
                op = new ObservablePane(pane, searchResults, sceneM);
            } catch (IOException e) {
                System.out.println(e.fillInStackTrace());
            }
            searchTether.subscribe(op);
        }

        searchBox.setText(cacheM.getSearch());
        if (!searchBox.getText().isEmpty()) {
            search();
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
    private Label searchSuggest;
    @FXML
    private Label didYouMean;
    @FXML
    private Label pageNum;
    @FXML private ImageView alcyView;
    @FXML private Text alcyLabel;

    @FXML
    public void popupAdvanced() throws IOException {
        advancedSearchPage = new AdvancedSearchPage(sceneM, cacheM, searchM);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/AdvancedSearchPage.fxml"));
        //advancedSearchPage = new AdvancedSearchPage(sceneM, cacheM, new Stage());
        sceneM.popWindowLoader(loader, advancedSearchPage, "Advanced Search");
    }


    @FXML
    public void back() throws Exception{
        //sceneM.backScene();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/startPage.fxml"));
        sceneM.changeScene(loader, new startPage(sceneM, cacheM));
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

        if ((searchBox.getText().equals(oldSearch) && !searchM.isActive) || searchBox.getText().isEmpty())
            return;

        oldSearch = searchBox.getText();

        // Perform fuzzy search based on user's choice
        String suggestion;
        FuzzyContext fc = new FuzzyContext();
        this.searchType = cacheM.getFuzzy();
        if (this.searchType.equals("SQL")) {
            fc.setF(new SQL());
        } else if (this.searchType.equals("Levenshtein")) {
            fc.setF(new Levenshtein());
        } else if (this.searchType.equals("Damerau")) {
            fc.setF(new Damerau_Levenshtein());
        } else if (this.searchType.equals("hiddenScore")) {
            fc.setF(new hiddenScore());
        }
        suggestion = fc.fuzzy(searchBox.getText(), cacheM.getDbM().getConnection());

        if (!oldSearch.equals(suggestion)) {
            didYouMean.setText("Did you mean: ");
            searchSuggest.setText(suggestion);
        } else {
            didYouMean.setText("");
            searchSuggest.setText("");
        }

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

        ResultSet approvedResults = getApprovedApplications(oldSearch, type);
        this.searchM = advancedSearchPage.transferSearchManager();
        System.out.println("SEARCH ACTIVE? " + searchM.isActive + " " + oldSearch);

        srArr = linkedListify(approvedResults);
        if (searchM.isActive) {
            srArr = searchM.filter(srArr, oldSearch);
        }

        searchTether.setSrArray(srArr);

        for (int i = 0; i < srArr.size(); i++) {
            System.out.println(srArr.get(i).getCompanyName());
        }
        searchTether.setBools(beerCheck.isSelected(), wineCheck.isSelected(), liquorCheck.isSelected());
        searchTether.notifyObservers(0, next);

    }

    private LinkedList<SearchResult> linkedListify(ResultSet rs) throws SQLException {
        LinkedList<SearchResult> srArr = new LinkedList();
        while (rs.next()) {
            //String fancifulName, String companyName, String alcoholType, String phLevel,
            //                        String alcohol, String year, String productType
            srArr.add(new SearchResult(rs.getString("FANCIFULNAME"),
                    rs.getString("BRANDNAME"),
                    rs.getString("PRODUCTTYPE"),
                    rs.getString("PHLEVEL"),
                    rs.getString("ALCOHOLPERCENT"),
                    rs.getString("VINTAGEYEAR"),
                    rs.getString("PRODUCTTYPE"),
                    rs.getString("DATEAPPROVED"),
                    rs.getString("TTBID"),
                    rs.getString("SERIALNUMBER"),
                    rs.getString("BREWERNUMBER"),
                    rs.getString("ONLYSTATE"),
                    rs.getLong("FORMID")));
        }
        return srArr;
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
        Character delimiter;
        System.out.println(cacheM.getFormat());
        delimiter = cacheM.getFormat();
//        if(!delimiterChooser.getText().isEmpty()){
//            delimiter = delimiterChooser.getText().charAt(0);
//        }else{
//            delimiter = ‘,’;
//        }


        DirectoryChooser directoryChooser = new DirectoryChooser();
        //Select the extentions we allow for the files
        //Actually get the folder
        directoryChooser.setInitialDirectory(null);

        File folder = directoryChooser.showDialog(null);
        String filePath = folder + "/Save-Results.csv";
        File file = new File(filePath);

        try {
            FileWriter searchResults = new FileWriter(file);

            CSVWriter writer = new CSVWriter(searchResults,
                    delimiter,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            List<String[]> data = new ArrayList<String[]>();
            data.add(new String[]{"FANCIFUL NAME","COMPANY NAME","ALCOHOL TYPE","ALCOHOL TYPE2","PH LEVEL","ALCOHOL PERCENT","YEAR"});

            for(SearchResult s : srArr) {
                String alcoholType = s.getProductType();
                data.add(new String[]{s.getFancifulName(), s.getCompanyName(), s.getAlcoholType(), alcoholType, s.getPhLevel(), s.getAlcohol(), s.getYear()});
            }
            writer.writeAll(data);

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
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