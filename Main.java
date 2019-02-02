import UI.LoginPage;
import UI.StorageManager;
import UI.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    StorageManager storageManager;
    SceneManager sm;

    @Override
    public void start(Stage primaryStage) throws Exception {
        init();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UI/Views/LoginPage.fxml"));
        loader.setControllerFactory(c ->
           new LoginPage(storageManager, sm)
        );

        Parent root = loader.load();
        primaryStage.setTitle("UI");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void init(){
        sm = new SceneManager();
    }


    public static void main(String[] args) {
        launch(args);

        return;
    }
}
