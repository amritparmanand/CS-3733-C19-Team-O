package Managers;

import Datatypes.Account;
import Datatypes.Form;

import java.sql.Connection;

public class CacheManager {
    private DatabaseManager dbM;
    private Form form;
    private Account acct;

    public CacheManager(DatabaseManager dbM) {
        this.dbM = dbM;
        this.form = new Form();
    }

    public DatabaseManager getDbM() {
        return dbM;
    }
    public Form getForm() {
        return form;
    }
    public void setForm(Form form) {
         this.form = form;
    }
    public Account getAcct() {
        return acct;
    }
    public void setAcct(Account acct) {
        this.acct = acct;
    }

    public void register(Account a) {
        this.acct = a;
        a.register(dbM.getConnection());
    }
}
