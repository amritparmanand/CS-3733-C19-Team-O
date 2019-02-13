package Managers;

import Datatypes.Account;
import Datatypes.Form;
import Datatypes.SearchResult;
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
    private SearchResult selectedResult;

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

    public SearchResult getSelectedResult() {
        return selectedResult;
    }

    public void setSelectedResult(SearchResult selectedResult) {
        this.selectedResult = selectedResult;
    }
}
