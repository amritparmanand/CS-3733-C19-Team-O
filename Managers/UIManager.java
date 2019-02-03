package Managers;

import UI.LoginPage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UIManager {

    private Stage main;

    public UIManager(Stage main) {
        this.main = main;
    }

    public void switchScreen2(FXMLLoader loader, Object sceneClass) throws Exception {
        loader.setControllerFactory(c -> sceneClass);
        
        Parent root = loader.load();
        main.setScene(new Scene(root));
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
     * Change scene
     */
    @SuppressWarnings("Duplicates")
    public void changeScene(Parent root, Button btn) {
        Stage stage;
        stage = (Stage) btn.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
