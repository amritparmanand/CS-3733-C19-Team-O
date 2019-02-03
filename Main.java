import Managers.*;

import UI.LoginPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Main extends Application {
    private UIManager uiManager;
    private CacheManager cacheManager = new CacheManager();
    private DatabaseManager databaseManager = new DatabaseManager();


    @Override
    public void start(Stage primaryStage) throws Exception {
        uiManager = new UIManager(primaryStage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UI/Views/LoginPage.fxml"));

        uiManager.changeScene(loader, new LoginPage(uiManager, cacheManager, databaseManager), "UI");
    }

    public static void main(String[] args) {
        launch(args);
    }
}