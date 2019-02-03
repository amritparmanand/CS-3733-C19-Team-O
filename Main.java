import Managers.*;

import UI.LoginPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private UIManager uiManager;
    private CacheManager cacheManager = new CacheManager();
    private DatabaseManager databaseManager = new DatabaseManager();


    @Override
    public void start(Stage primaryStage) throws Exception {
        uiManager = new UIManager(primaryStage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UI/Views/LoginPage.fxml"));

        uiManager.switchScreen2(loader, new LoginPage(uiManager, cacheManager, databaseManager));
//
//        loader.setControllerFactory(c ->
//                new LoginPage(uiManager, cacheManager, databaseManager)
//        );
//
//        Parent root = loader.load();
//        primaryStage.setTitle("UI");
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}