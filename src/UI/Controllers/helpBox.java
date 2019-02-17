package UI.Controllers;

import Managers.CacheManager;
import Managers.SceneManager;

public class helpBox {
    @FXML ImageView helpGif;
    String prevLocation="login";
//    private SceneManager sceneM;
//    private CacheManager cacheM;
//
//    public helpBox(SceneManager sceneM, CacheManager cacheM) {
//        this.sceneM = sceneM;
//        this.cacheM = cacheM;
//    }
    switch(prevLocation){
        case "login":
            Image image = new Image(getClass().getResourceAsStream("/HelpGifs/login.png"));
            helpGif.setFitHeight(726); //726
            helpGif.setFitWidth(500); //500
            helpGif.setImage(image);
            break;
        case "aAppMulti":
            Image image = new Image(getClass().getResourceAsStream("/HelpGifs/aApp.png"));
            helpGif.setFitHeight(726); //726
            helpGif.setFitWidth(500); //500
            helpGif.setImage(image);
            break;
        case "aAppSingle":
            Image image = new Image(getClass().getResourceAsStream("/HelpGifs/aApp.png"));
            helpGif.setFitHeight(726); //726
            helpGif.setFitWidth(500); //500
            helpGif.setImage(image);
            break;
        case "mAppSingle":
            Image image = new Image(getClass().getResourceAsStream("/HelpGifs/mAppSingle.png"));
            helpGif.setFitHeight(726); //726
            helpGif.setFitWidth(500); //500
            helpGif.setImage(image);
            break;
        case "mAppMulti":
            Image image = new Image(getClass().getResourceAsStream("/HelpGifs/mApp.png"));
            helpGif.setFitHeight(726); //726
            helpGif.setFitWidth(500); //500
            helpGif.setImage(image);
            break;

    }

//    @FXML
//    private Text instruction;



}
