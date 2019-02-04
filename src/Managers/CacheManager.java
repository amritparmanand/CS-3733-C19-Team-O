package Managers;

import Datatypes.Form;

public class CacheManager {
    private DatabaseManager dbM;
    private Form form;

    public CacheManager(DatabaseManager dbM) {
        this.dbM = dbM;
        this.form = null;
    }

    public DatabaseManager getDbM() {
        return dbM;
    }

    public Form getForm() {
        return form;
    }
}
