package UI.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Jonathan & Trevor & Sri
 * @version It 3
 * @since It 3
 * Manages the popup helpbox
 */

public class helpBox {
    @FXML
    private Image image = new Image(getClass().getResourceAsStream("/HelpGifs/login.gif"));
    private ImageView helpGif = new ImageView(image);

//    private String prevLocation;


    public helpBox() throws FileNotFoundException {
//        this.prevLocation = prevLocation;
//        imageSeter(prevLocation);
    }

    public void loginPage(){
        Stage stage = new Stage();
        helpGif.setFitHeight(480);
        helpGif.setFitWidth(270);
        helpGif.setImage(image);
        Group root = new Group(helpGif);
        Scene scene = new Scene(root, 500, 270);
        stage.setTitle("Gif");
        stage.setScene(scene);
        stage.show();
    }

    public void mAppMulti(){
        image = new Image(getClass().getResourceAsStream("/HelpGifs/mApp.gif"));
        Stage stage = new Stage();
        helpGif.setFitHeight(480);
        helpGif.setFitWidth(270);
        helpGif.setImage(image);
        Group root = new Group(helpGif);
        Scene scene = new Scene(root, 500, 270);
        stage.setTitle("Gif");
        stage.setScene(scene);
        stage.show();

    }
    /**
     * @author Trevor and Jonathan
     * @param prevLocation
     * sets the image to be displayed on the help page
     */
    private void imageSeter(String prevLocation){

        helpGif.setFitHeight(726); //726
        helpGif.setFitWidth(500); //500


        switch (prevLocation) {
            case "login":
                image = new Image(getClass().getResourceAsStream("/HelpGifs/login.gif"));
                helpGif.setImage(image);
                break;
            case "aAppMulti":
                image = new Image(getClass().getResourceAsStream("/HelpGifs/aApp.gif"));
                 helpGif.setImage(image);
                break;
            case "aAppSingle":
                image = new Image(getClass().getResourceAsStream("/HelpGifs/aApp.gif"));
                 helpGif.setImage(image);
                break;
            case "mAppSingle":
                image = new Image(getClass().getResourceAsStream("/HelpGifs/mAppSingle.gif"));
                helpGif.setImage(image);
                break;
            case "mAppMulti":
                image = new Image(getClass().getResourceAsStream("/HelpGifs/mApp.gif"));
                 helpGif.setImage(image);
                break;
            case "search":
                image = new Image(getClass().getResourceAsStream("/HelpGifs/search.gif"));
                helpGif.setImage(image);
                break;
            case "aGetApp":
                image = new Image(getClass().getResourceAsStream("/HelpGifs/aGetApp.gif"));
                 helpGif.setImage(image);
                break;
            case "mCreateForm":
                image = new Image(getClass().getResourceAsStream("/HelpGifs/mCreateForm.gif"));
                 helpGif.setImage(image);
                break;
            case "aCreateAccount":
                image = new Image(getClass().getResourceAsStream("/HelpGifs/aCreateAccount.gif"));
                helpGif.setImage(image);
                break;
            case "mCreateAccount":
                image = new Image(getClass().getResourceAsStream("/HelpGifs/mCreateAccount.gif"));
                 helpGif.setImage(image);
                break;

        }

        Group root = new Group(helpGif);
        Scene scene = new Scene(root, 400, 300);
    }
}
