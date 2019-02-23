import Managers.*;

import UI.Controllers.LoginPage;
import UI.Controllers.startPage;
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
        dbM.createSequences();
        dbM.insertDefault();
        if(dbM.isFormsEmpty())
            dbM.generateTablesForms();
        else if(!dbM.isFormsEmpty())
            System.out.println("Forms are populated!");

        if(dbM.isAppsEmpty())
            dbM.generateTablesApplication();
        else if(!dbM.isAppsEmpty())
            System.out.println("Apps are populated");



        FXMLLoader loader = new FXMLLoader(getClass().getResource("UI/Views/startPage.fxml"));
        sceneM.changeScene(loader, new startPage(sceneM, cacheM), "UI");
    }


    public static void main(String[] args) { launch(args); }
}
