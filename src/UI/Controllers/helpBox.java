package UI.Controllers;

import Managers.CacheManager;
import Managers.SceneManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;

public class helpBox {
    @FXML
    ImageView helpGif;
    private String prevLocation;
    private SceneManager sceneM;
    private CacheManager cacheM;

    public helpBox(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }

    public void imageSeter(String prevLocation){

        Image image;
        switch (prevLocation) {
            case "login":
                image = new Image(getClass().getResourceAsStream("/HelpGifs/login.png"));
                helpGif.setFitHeight(726); //726
                helpGif.setFitWidth(500); //500
                helpGif.setImage(image);
                break;
            case "aAppMulti":
                image = new Image(getClass().getResourceAsStream("/HelpGifs/aApp.png"));
                helpGif.setFitHeight(726); //726
                helpGif.setFitWidth(500); //500
                helpGif.setImage(image);
                break;
            case "aAppSingle":
                image = new Image(getClass().getResourceAsStream("/HelpGifs/aApp.png"));
                helpGif.setFitHeight(726); //726
                helpGif.setFitWidth(500); //500
                helpGif.setImage(image);
                break;
            case "mAppSingle":
                image = new Image(getClass().getResourceAsStream("/HelpGifs/mAppSingle.png"));
                helpGif.setFitHeight(726); //726
                helpGif.setFitWidth(500); //500
                helpGif.setImage(image);
                break;
            case "mAppMulti":
                image = new Image(getClass().getResourceAsStream("/HelpGifs/mApp.png"));
                helpGif.setFitHeight(726); //726
                helpGif.setFitWidth(500); //500
                helpGif.setImage(image);
                break;

        }
    }
}
