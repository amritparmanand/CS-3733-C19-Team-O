import edu.wpi.cs3733c19.teamO.UI.Controllers.LoginPage;
import edu.wpi.cs3733c19.teamO.Managers.CacheManager;
import edu.wpi.cs3733c19.teamO.Managers.DatabaseManager;
import edu.wpi.cs3733c19.teamO.Managers.SceneManager;
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
        dbM.generateTablesForms();
        dbM.generateTablesApplication();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("src/edu/wpi/cs3733c19/teamO/UI/Views/LoginPage.fxml"));
        sceneM.changeScene(loader, new LoginPage(sceneM, cacheM), "UI");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
