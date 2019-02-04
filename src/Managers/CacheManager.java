package Managers;

import Datatypes.Form;

public class CacheManager {
    DatabaseManager dbM;
    SceneManager sceneM;

    public CacheManager(DatabaseManager dbM, SceneManager sceneM) {
        this.dbM = dbM;
        this.sceneM = sceneM;
    }

}
