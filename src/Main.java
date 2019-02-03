import UI.LoginPage;
import Managers.CacheManager;
import Managers.DatabaseManager;
import Managers.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Main extends Application {
    private SceneManager sceneM;
    private CacheManager cacheM = new CacheManager();
    private DatabaseManager dbM = new DatabaseManager();

    @Override
    public void start(Stage primaryStage) throws Exception {
        sceneM = new SceneManager(primaryStage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UI/Views/LoginPage.fxml"));

        sceneM.changeScene(loader, new LoginPage(sceneM, cacheM, dbM), "UI");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
