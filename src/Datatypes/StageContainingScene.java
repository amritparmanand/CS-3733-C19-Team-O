package Datatypes;

import javafx.stage.Stage;

public abstract class StageContainingScene {
    public StageContainingScene(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    private Stage stage;
}