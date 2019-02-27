package Datatypes;

import javafx.stage.Stage;

public abstract class StageContainingScene {
    public StageContainingScene() {
        this.stage = new Stage();
    }

    public Stage getStage() {
        return stage;
    }

    private Stage stage;
}
