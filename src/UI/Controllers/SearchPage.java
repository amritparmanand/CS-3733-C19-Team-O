package UI.Controllers;

import Datatypes.SearchResult;
import Fuzzy.*;
import Managers.CacheManager;
import Managers.SceneManager;
import com.jfoenix.controls.JFXCheckBox;
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

    public SearchPage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    ArrayList<SearchResult> searchList = new ArrayList<SearchResult>();

    @FXML private ScrollPane scroll;
    @FXML private Button back;
    @FXML private TextField searchBox;
    @FXML private JFXCheckBox beerCheck;
    @FXML private JFXCheckBox liquorCheck;
    @FXML private JFXCheckBox wineCheck;
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
    @FXML private MenuItem sqlSearch;
    @FXML private MenuItem lSearch;
    @FXML private MenuItem dlSearch;
    @FXML private MenuItem hiddenScore;
    @FXML private MenuButton algChoose;


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

    @FXML public void back() throws IOException {
        //sceneM.backScene();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, cacheM));
    }

    @FXML public void loadAlcohol(ArrayList<SearchResult> searchList) {
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
                    ((Label) aType).setText(result.getBeerWineSpirit());
                }
                searchResults.getChildren().add(alcResult);
                //POPUP WINDOW
                vbox.setOnMousePressed(new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent me) {
                        Parent root = null;
                        try {
//                            SearchResultPopup searchResultPopup = new SearchResultPopup();
                            root = FXMLLoader.load(getClass().getResource("/UI/Views/SearchResultPopup.fxml"));

                            //searchResultPopup.fancifulLabel.setText(result.getFancifulName());
                            Node vbox2 = root.getChildrenUnmodifiable().get(0);

                            if (vbox2 instanceof VBox) {
                                Node fancifulBox = ((VBox) vbox2).getChildren().get(0);
                                if(fancifulBox instanceof HBox) {
                                    Node fancifulName = ((HBox) fancifulBox).getChildren().get(0);
                                    ((Label) fancifulName).setText(result.getFancifulName());
                                }
                                Node hbox = ((VBox) vbox2).getChildren().get(1);
                                if (hbox instanceof HBox) {
                                    Node labelImage = ((HBox) hbox).getChildren().get(1); //image
                                    if (labelImage instanceof ImageView){

                                    }
                                    Node vbox3 = ((HBox) hbox).getChildren().get(0);
                                    if (vbox3 instanceof VBox) {
                                        Node typeBox = ((VBox) vbox3).getChildren().get(0);
                                        Node companyBox = ((VBox) vbox3).getChildren().get(1);
                                        Node alcoholPercentBox = ((VBox) vbox3).getChildren().get(2);
                                        Node pHBox = ((VBox) vbox3).getChildren().get(3);
                                        Node yearBox = ((VBox) vbox3).getChildren().get(4);

                                        if (typeBox instanceof HBox){
                                            Node typeName = ((HBox) typeBox).getChildren().get(1);
                                            ((Label) typeName).setText(result.getBeerWineSpirit());
                                        }

                                        if (companyBox instanceof HBox){
                                            Node companyName = ((HBox) companyBox).getChildren().get(1);
                                            ((Label) companyName).setText(result.getCompanyName());
                                        }

                                        if (alcoholPercentBox instanceof HBox){
                                            Node alcoholPercentName = ((HBox) alcoholPercentBox).getChildren().get(1);
                                            ((Label) alcoholPercentName).setText((result.getAlcohol()));
                                        }

                                        if (pHBox instanceof HBox){
                                            Node pHName = ((HBox) pHBox).getChildren().get(1);
                                            ((Label) pHName).setText((result.getPhLevel()));
                                        }

                                        if (yearBox instanceof HBox){
                                            Node yearName = ((HBox) yearBox).getChildren().get(1);
                                            ((Label) yearName).setText((result.getYear()));
                                        }

                                    }

                                }

                            }



                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (root !=null)
                            cacheM.getSelectedResult();
                            popWindow(root);







                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void popWindow(Parent root) {
        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Search Result");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }


//    @FXML public void initialize() throws SQLException{
//        search();
//    }

    @FXML public void searchSuggest() throws SQLException {
        searchBox.setText(searchSuggest.getText());
        search();
        didYouMean.setText("");
        searchSuggest.setText("");
    }

    @FXML public void search() throws SQLException {

        // Perform fuzzy search based on user's choice
        String suggestion = "";
        FuzzyContext fc = new FuzzyContext();
        if(SQL){
            fc.setF(new SQL());
        }
        else if(Levi){
            fc.setF(new Levenshtein());
        }
        else if(DLevi){
            fc.setF(new Damerau_Levenshtein());
        }
        else if(hiddenS){
            fc.setF(new hiddenScore());
        }
        suggestion = fc.fuzzy(searchBox.getText(), cacheM.getDbM().getConnection());

        ResultSet rs = getApprovedApplications();
        searchResults.getChildren().clear();
        searchList.clear();

        // For each of the approved applications
        while (rs.next()) {
            SearchResult result = new SearchResult();
            result.setFancifulName(rs.getString("FANCIFULNAME"));
            result.setCompanyName(rs.getString("BRANDNAME"));
            result.setPhLevel(rs.getString("PHLEVEL"));
            result.setAlcohol(rs.getString("ALCOHOLPERCENT"));
            result.setYear(rs.getString("VINTAGEYEAR"));
            result.setBeerWineSpirit(rs.getString("BEERWINESPIRIT"));
            //IMAGE REEEEEEE
            // result set label image
//            if(rs.getString("PRODUCTTYPE") == "WINE"){
//                result.setWine(true);
//                result.setBeer(false);
//                result.setLiquor(false);
//                result.setAlcoholType("Wine");
//            }
//            else if(rs.getString("PRODUCTTYPE") == "BEER"){
//                result.setWine(false);
//                result.setBeer(true);
//                result.setLiquor(false);
//                result.setAlcoholType("Beer");
//            }
//            else if(rs.getString("PRODUCTTYPE") == "LIQUOR"){
//                result.setWine(false);
//                result.setBeer(false);
//                result.setLiquor(true);
//                result.setAlcoholType("Liquor");
//            }

            if(searchBox.getText().isEmpty()) {
                System.out.println("foo");
                if (!beerCheck.isSelected() && !wineCheck.isSelected() && !liquorCheck.isSelected()) {
                    searchList.add(result);
                }
                else {
                    System.out.println(beerCheck.isSelected());
                    System.out.println(result.getBeerWineSpirit().toLowerCase());
                    if (beerCheck.isSelected() && result.getBeerWineSpirit().toLowerCase().equals("beer")) {
                        searchList.add(result);
                    }
                    if (wineCheck.isSelected() && result.getBeerWineSpirit().toLowerCase().equals("wine") ){
                        searchList.add(result);
                    }
                    if (liquorCheck.isSelected() && result.getBeerWineSpirit().toLowerCase().equals("spirit")) {
                        searchList.add(result);
                    }
                }
            }

            else if(!searchBox.getText().isEmpty() &&
                    ((result.getFancifulName().toLowerCase().contains(searchBox.getText().toLowerCase()))
                    || (result.getCompanyName().toLowerCase().contains(searchBox.getText().toLowerCase())))){
                //if ^^ this is not empty, we do a fuzzy search, and check if it's empty again
                //see if fuzzy returns anything
                //if it does: set did you mean to "Did you Mean"
                //set the searchSuggest to return value
                System.out.println("bar");
                if (!beerCheck.isSelected() && !wineCheck.isSelected()) {
                    searchList.add(result);
                }
                else {
                    if (beerCheck.isSelected() && result.getBeerWineSpirit().toLowerCase()=="beer") {
                        searchList.add(result);
                    }
                    if (wineCheck.isSelected() && result.getBeerWineSpirit().toLowerCase()=="wine") {
                        searchList.add(result);
                    }
                    if (liquorCheck.isSelected() && result.getBeerWineSpirit().toLowerCase()=="spirit") {
                        searchList.add(result);
                    }
                }
                continue;
            }
            //I added a conditional else so that the else doesn't always print stuff... I know it's ugly
            else if(!searchBox.getText().isEmpty() &&
                    !((result.getFancifulName().toLowerCase().contains(searchBox.getText().toLowerCase()))
                            || (result.getCompanyName().toLowerCase().contains(searchBox.getText().toLowerCase())))){ //a bunch of approved forms go into this else because they don't pass the first two conditions
                didYouMean.setText("Did you mean: ");
                searchSuggest.setText(suggestion);
                continue;
            }
        }
        loadAlcohol(searchList);
    }

    public ResultSet getApprovedApplications() throws SQLException{
        return cacheM.getApprovedApplications(cacheM.getDbM().getConnection());
    }

    /**
     * @author Jonathan Luna and Liz Del Monaco
     * downloads all searches from a query into a csv file
     */
    public void download(){
        System.out.println(";)");

        String path = "";

        JFileChooser chooser = new JFileChooser();
        String choosertitle = "Select a destination";

        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        chooser.setAcceptAllFileFilterUsed(false);

        int r = chooser.showSaveDialog(null);

        if(r == JFileChooser.APPROVE_OPTION){
            path = chooser.getSelectedFile().getAbsolutePath();

            try {
                System.out.println(path);
                PrintWriter writer = new PrintWriter(path + "/" + "search-results.csv", "UTF-8");

                writer.println("sep=;");
                writer.println("FANCIFUL NAME;COMPANY NAME;ALCOHOL TYPE;ALCOHOL TYPE2;PH LEVEL;ALCOHOL PERCENT;YEAR");

                for(SearchResult s : searchList){
                    //holder variable to hold the type of alcohol for printing
                    String alcoholType = s.getBeerWineSpirit().toLowerCase();

                    writer.println(s.getFancifulName() + ";" + s.getCompanyName()+ ";" + s.getAlcoholType() + ";" + alcoholType + ";" + s.getPhLevel() + ";" + s.getAlcohol() + ";" + s.getYear());
                }
                writer.close();
            }

            catch(FileNotFoundException e){
                System.out.println("File not found.");
                e.printStackTrace();
            }

            catch(UnsupportedEncodingException e){
                System.out.println("Unsupported encoding exception.");
                e.printStackTrace();
            }
        }

        else{
            System.out.println("User cancelled the operation");
        }


    }
}
