package Managers;

import Datatypes.Form;

public class CacheManager {
    DatabaseManager databaseManager;
    UIManager uiManager;

    public CacheManager(DatabaseManager databaseManager, UIManager uiManager) {
        this.databaseManager = databaseManager;
        this.uiManager = uiManager;
    }


    Form activeForm;

    private void sendFormToDatabase() {
        databaseManager.uploadForm(activeForm);

    }


}
