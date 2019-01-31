import UI.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    SceneManager sm;

    @Override
    public void start(Stage primaryStage) throws Exception{
        init();

        Parent root = FXMLLoader.load(getClass().getResource("UI/Views/LoginPage.fxml"));
        primaryStage.setTitle("UI");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void init(){
        sm = new SceneManager();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
