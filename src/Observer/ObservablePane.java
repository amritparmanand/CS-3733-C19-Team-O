package Observer;

;
import Datatypes.SearchResult;
import Managers.SceneManager;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ObservablePane implements IObservable {
    private Pane self;
    String fancy;
    String brand;
    String type;
    String alcPercent;
    String phLevel;
    String year;

    SceneManager sceneManager;

    public ObservablePane(Pane self, FlowPane searchResults, SceneManager sceneManager) {
        this.self = self;
//        System.out.println(self);
        searchResults.getChildren().add(this.self);
        this.sceneManager = sceneManager;
    }

    public Node getSelf() {
        return this.self;
    }

    public void setBlank() {
        Node vbox = this.self.getChildren().get(0);

        if (vbox instanceof VBox) {
            Node fName = ((VBox) vbox).getChildren().get(1);
            Node bName = ((VBox) vbox).getChildren().get(2);
            Node aType = ((VBox) vbox).getChildren().get(3);

            ((Label) fName).setText("");
            ((Label) bName).setText("");
            ((Label) aType).setText("");
        }

        vbox.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {}});
    }

    public void update(SearchResult sr) throws SQLException {

        Node vbox = this.self.getChildren().get(0);

        if (vbox instanceof VBox) {
            Node imgView = ((VBox) vbox).getChildren().get(0);
            Node fName = ((VBox) vbox).getChildren().get(1);
            Node bName = ((VBox) vbox).getChildren().get(2);
            Node aType = ((VBox) vbox).getChildren().get(3);
            ((ImageView) imgView).setImage(null);
            fancy = sr.getFancifulName();
            brand = sr.getCompanyName();
            type = sr.getAlcoholType();
            alcPercent = sr.getAlcohol();
            phLevel = sr.getPhLevel();
            year = sr.getYear();
            if(sr.getLabelImage().getLabelImage() != null) {
                ((ImageView) imgView).setImage(sr.getLabelImage().getLabelImage());
            }else
                ((ImageView) imgView).setImage(null);
            ((Label) fName).setText(fancy);
            ((Label) bName).setText(brand);
            ((Label) aType).setText(type);
        }

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
                        if (fancifulBox instanceof HBox) {
                            Node fancifulName = ((HBox) fancifulBox).getChildren().get(0);
                            ((Label) fancifulName).setText(fancy);
                        }
                        Node hbox = ((VBox) vbox2).getChildren().get(1);
                        if (hbox instanceof HBox) {
                            Node labelImage = ((HBox) hbox).getChildren().get(1); //image
                            if (labelImage instanceof ImageView) {
                                if(sr.getLabelImage().getLabelImage() != null)
                                    ((ImageView) labelImage).setImage(sr.getLabelImage().getLabelImage());
                            }
                            Node vbox3 = ((HBox) hbox).getChildren().get(0);
                            if (vbox3 instanceof VBox) {
                                Node typeBox = ((VBox) vbox3).getChildren().get(0);
                                Node companyBox = ((VBox) vbox3).getChildren().get(1);
                                Node alcoholPercentBox = ((VBox) vbox3).getChildren().get(2);
                                Node pHBox = ((VBox) vbox3).getChildren().get(3);
                                Node yearBox = ((VBox) vbox3).getChildren().get(4);

                                if (typeBox instanceof HBox) {
                                    Node typeName = ((HBox) typeBox).getChildren().get(1);
                                    ((Label) typeName).setText(type);
                                }

                                if (companyBox instanceof HBox) {
                                    Node companyName = ((HBox) companyBox).getChildren().get(1);
                                    ((Label) companyName).setText(brand);
                                }

                                if (alcoholPercentBox instanceof HBox) {
                                    Node alcoholPercentName = ((HBox) alcoholPercentBox).getChildren().get(1);
                                    ((Label) alcoholPercentName).setText(alcPercent);
                                }

                                if (pHBox instanceof HBox) {
                                    Node pHName = ((HBox) pHBox).getChildren().get(1);
                                    ((Label) pHName).setText(phLevel);
                                }

                                if (yearBox instanceof HBox) {
                                    Node yearName = ((HBox) yearBox).getChildren().get(1);
                                    ((Label) yearName).setText(year);
                                }

                            }

                        }

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

                sceneManager.popWindow(root, fancy);
            }
        });
    }
}
