package Managers;

import Datatypes.StageContainingScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Percy Jiang
 * @version It 1
 * Manager to deal with changing scenes
 */
public class SceneManager {

    private Stage main;

    private Scene lastScene;

    public SceneManager(Stage main) {
        this.main = main;
//        main.setMaximized(true);
    }

    //WARNING: Object isn't a good thing to settle on. We will create a super class later to call here.
    public void changeScene(FXMLLoader loader, Object sceneClass) throws IOException {
        loader.setControllerFactory(c -> sceneClass);

        Parent root = loader.load();
        main.setScene(new Scene(root));
//        main.setMaximized(true);
        main.show();
    }

    //title changing overload
    public void changeScene(FXMLLoader loader, Object sceneClass, String title) throws IOException {
        lastScene = main.getScene();

        loader.setControllerFactory(c -> sceneClass);

        Parent root = loader.load();

        main.setScene(new Scene(root));
        main.setTitle(title);
        main.show();
    }

    public void backScene() {
        main.setScene(lastScene);
        main.show();
    }

    @SuppressWarnings("Duplicates")
    public void popWindowLoader(FXMLLoader loader, StageContainingScene sceneClass, String title) throws IOException {
        loader.setControllerFactory(c -> sceneClass);

        Parent root = loader.load();

        Stage stage = sceneClass.getStage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    /**
     * Pop-up windows
     */
    @SuppressWarnings("Duplicates")
    public void popWindow(Parent root, String title) {
        Stage stage;
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}