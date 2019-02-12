package Managers;

import Datatypes.Account;
import Datatypes.Form;
import Fuzzy.FuzzyContext;

import java.sql.Connection;
/**
 * @author Sam Silver
 * @version It 1
 * manages the cache, provides access to cached info
 */
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

    // Register function
    // Calls the register method in account
    public void register(Account a) {
        a.register(dbM.getConnection());
    }
}
