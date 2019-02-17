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
    private String passer;
    private String receiver;
    private String fuzzy;
    private String format;

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
        System.out.println("cManager approve Form");
        System.out.println(form.getFormID());
        System.out.println(form.getSignature());
        System.out.println(form.getDateIssued());
        form.approve(conn);

    }
    public void denyForm(Connection conn){
        form.deny(conn);
    }
    public void insertForm(Connection connection) throws SQLException, FileNotFoundException {
        form.insertForm(connection);
    }
    public ResultSet getApprovedApplications(Connection conn) throws SQLException{
        return form.getApprovedApplications(conn);
    }
    public void passForm(Connection connection, long f, int r) {
        form.passForm(connection, f, r);
    }

    public void setFuzzy(String fuzzy) {
        this.fuzzy = fuzzy;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
