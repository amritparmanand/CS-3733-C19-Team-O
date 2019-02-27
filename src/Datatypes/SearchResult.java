package Datatypes;

import Observer.IObservable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Robert Rinearson & Percy Jiang
 * @version It 1
 * Container for search results
 */
public class SearchResult {
    private String fancifulName;
    private String companyName;
    private String alcoholType;
    private String phLevel;
    private String alcohol;
    private String year;
    private String productType;

    //sam's filtering stuff
    private String approvedDate;
    private String TTBID;
    private String SerialNum;
    private String BrewerNum;
    private String origin;
    private String formID;


    public SearchResult(String fancifulName, String companyName, String alcoholType, String phLevel,
                        String alcohol, String year, String productType) {
        this.fancifulName = fancifulName;
        this.companyName = companyName;
        this.alcoholType = alcoholType;
        this.phLevel = phLevel;
        this.alcohol = alcohol;
        this.year = year;
        this.productType = productType;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public String getTTBID() {
        return TTBID;
    }

    public String getSerialNum() {
        return SerialNum;
    }

    public String getBrewerNum() {
        return BrewerNum;
    }

    public String getOrigin() {
        return origin;
    }

    public SearchResult(String fancifulName, String companyName, String alcoholType, String phLevel, String alcohol, String year, String productType, String approvedDate, String TTBID, String serialNum, String brewerNum, String origin, Long formID) {
        this.fancifulName = fancifulName;
        this.companyName = companyName;
        this.alcoholType = alcoholType;
        this.phLevel = phLevel;
        this.alcohol = alcohol;
        this.year = year;
        this.productType = productType;


        this.approvedDate = approvedDate;
        this.TTBID = TTBID;
        this.SerialNum = serialNum;
        this.BrewerNum = brewerNum;
        this.origin = origin;
        this.formID = formID.toString();

    }

    @Deprecated
    public SearchResult() {
        this.fancifulName = null;
        this.companyName = null;
        this.alcoholType = null;
        this.phLevel = null;
        this.alcohol = null;
        this.year = null;
        this.productType = null;

    }

    public String getFormID() {
        return formID;
    }

    public void setFormID(String formID) {
        this.formID = formID;
    }

    public String getFancifulName() {
        return fancifulName;
    }

    public void setFancifulName(String fancifulName) {
        this.fancifulName = fancifulName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAlcoholType() {
        return alcoholType;
    }

    public void setAlcoholType(String alcoholType) {
        this.alcoholType = alcoholType;
    }

    public String getPhLevel() {
        return phLevel;
    }

    public void setPhLevel(String phLevel) {
        this.phLevel = phLevel;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
