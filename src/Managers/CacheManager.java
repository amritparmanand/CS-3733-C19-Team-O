package Managers;

import Datatypes.Account;
import Datatypes.Form;
import Datatypes.SearchResult;
import Fuzzy.FuzzyContext;

import java.io.FileNotFoundException;
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
    private String fuzzy;
    private char format;

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
    public SearchResult getSelectedResult() {
        return selectedResult;
    }
    public void setSelectedResult(SearchResult selectedResult) {
        this.selectedResult = selectedResult;
    }
    public int getFormLimit() {
        return formLimit;
    }
    public void setFormLimit(int formLimit) {
        this.formLimit = formLimit;
    }
    public String getFuzzy() {
        return fuzzy;
    }
    public void setFuzzy(String fuzzy) {
        this.fuzzy = fuzzy;
    }
    public char getFormat() {
        return format;
    }
    public void setFormat(char format) {
        this.format = format;
    }

    // Facade stuff
    // Form
    public void approveForm(Connection conn){
        System.out.println("cManager approve Form");
        System.out.println(form.getFormID());
        System.out.println(form.getSignature());
        System.out.println(form.getDateIssued());
        form.approve(conn);

    }
    public void denyForm(Connection conn) throws Exception{
        form.deny(conn);
    }
    public void insertForm(Connection connection) throws Exception {
        form.insertForm(connection);
    }
    public ResultSet getApprovedApplications(Connection conn, String condition, String type) throws SQLException{
        return form.getApprovedApplications(conn, condition, type);
    }
    public void passForm(Connection connection, long formID,  String ttbUsername) {
        form.passForm(connection, formID, ttbUsername);
    }

}
