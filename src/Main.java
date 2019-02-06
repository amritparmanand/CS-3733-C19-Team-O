import Managers.*;

import UI.LoginPage;
import UI.aHomepage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Main extends Application {
    private SceneManager sceneM;
    private CacheManager cacheM;
    private DatabaseManager dbM;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //backend
        dbM = new DatabaseManager();
        //frontend
        sceneM = new SceneManager(primaryStage);
        //middleman
        cacheM = new CacheManager(dbM);

        dbM.generateTables();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UI/Views/aHomepage.fxml"));
        sceneM.changeScene(loader, new aHomepage(sceneM, cacheM), "UI");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
