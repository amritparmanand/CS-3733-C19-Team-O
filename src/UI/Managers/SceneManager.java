package UI.Managers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    private Stage main;

    public SceneManager(Stage main) {
        this.main = main;
    }

    //WARNING: Object isn't a good thing to settle on. We will create a super class later to call here.
    public void changeScene(FXMLLoader loader, Object sceneClass) throws IOException {
        loader.setControllerFactory(c -> sceneClass);

        Parent root = loader.load();
        main.setScene(new Scene(root));
        main.show();
    }

    //title changing overload
    public void changeScene(FXMLLoader loader, Object sceneClass, String title) throws IOException {
        loader.setControllerFactory(c -> sceneClass);

        Parent root = loader.load();
        main.setScene(new Scene(root));
        main.setTitle(title);
        main.show();
    }


    /**
     * Pop-up windows
     */
    public void popWindow(Parent root) {
        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("idk what to pop so here's a fake mortgage calculator");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**
     * Change Scene by pressing on V box
     */
    @SuppressWarnings("Duplicates")
    public void changeSceneWithV(Parent root, VBox v) {
        Stage stage;
        stage = (Stage) v.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}