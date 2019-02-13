package Managers;

import Datatypes.Account;
import Datatypes.Form;
import Datatypes.SearchResult;
import Fuzzy.FuzzyContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sam Silver & Percy
 * @version It 2
 * manages the cache, provides access to cached info
 */
public class CacheManager {
    private DatabaseManager dbM;
    private Form form;
    private Account acct;
    private SearchResult selectedResult;
    private int formLimit;
    private String passer;
    private String receiver;

    public CacheManager(DatabaseManager dbM) {
        this.dbM = dbM;
        this.form = new Form();
    }

    // Getters and Setters
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
    public int getFormLimit() {
        return formLimit;
    }
    public void setFormLimit(int formLimit) {
        this.formLimit = formLimit;
    }
    public String getPasser() {
        return passer;
    }
    public void setPasser(String passer) {
        this.passer = passer;
    }
    public String getReceiver() {
        return receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public SearchResult getSelectedResult() {
        return selectedResult;
    }

    public void setSelectedResult(SearchResult selectedResult) {
        this.selectedResult = selectedResult;
    }

    // Facade stuff

    // Form
    public void approveForm(Connection conn){
        form.approve(conn);
    }
    public void denyForm(Connection conn){
        form.deny(conn);
    }
    public void insertApp(Connection connection) throws SQLException {
        form.addApp(connection);
    }
    public void insertForm(Connection connection) throws SQLException{
        form.insert(connection);
    }
    public ResultSet getApprovedApplications(Connection conn) throws SQLException{
        return form.getApprovedApplications(conn);
    }

}
