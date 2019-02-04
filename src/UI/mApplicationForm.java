package UI;

import Managers.*;

public class mApplicationForm {
    private UIManager uiManager;
    private CacheManager cacheManager;
    private DatabaseManager databaseManager;

    public mApplicationForm(UIManager uiManager, CacheManager cacheManager, DatabaseManager databaseManager) {
        this.uiManager = uiManager;
        this.cacheManager = cacheManager;
        this.databaseManager = databaseManager;
    }
}
