package Managers;

import Datatypes.Account;
import Datatypes.Form;
import Fuzzy.FuzzyContext;

import java.sql.Connection;
/**
 * @author Sam Silver & Percy
 * @version It 2
 * manages the cache, provides access to cached info
 */
public class CacheManager {
//    private Connection dbConn;
    private DatabaseManager dbM;
    private Form form;
    private Account acct;

    public CacheManager(DatabaseManager dbM) {
        this.dbM = dbM;
        this.form = new Form();
    }

//    public CacheManager(Connection dbConn) {
//        this.dbConn = dbConn;
//        this.form = new Form();
//    }
//
//    public Connection getDbConn() {
//        return dbConn;
//    }
//    public void setDbConn(Connection dbConn) {
//        this.dbConn = dbConn;
//    }

    // Getters and setters
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

    // Facade stuff

}
