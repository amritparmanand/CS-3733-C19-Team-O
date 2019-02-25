package UI.Controllers;

import Managers.CacheManager;
import Managers.SceneManager;

public class AdvancedSearchPage {
    private SceneManager sceneM;
    private CacheManager cacheM;

    public AdvancedSearchPage(SceneManager sceneM, CacheManager cacheM) {
        this.sceneM = sceneM;
        this.cacheM = cacheM;
    }
}
