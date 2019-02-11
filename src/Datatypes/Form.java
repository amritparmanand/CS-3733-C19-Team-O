package Datatypes;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
/**
 * @author Harry James
 * @version It 1
 * object to hold information in a form
 */
public class Form {

    private int repID;
    private String brewerNumber;
    private String productSource;
    private String serialNumber;
    private String productType;
    private String brandName;
    private String fancifulName;
    private String applicantName;
    private String mailingAddress;
    private String formula;
    private String grapeVarietal;
    private String appellation;
    private String phoneNumber;
    private String emailAddress;
    private String dateOfApplication;
    private String printName;
    private String beerWineSpirit;
    private String alcoholPercent;
    private String vintageYear;
    private String pHLevel;
    private int formID;

    public Form() {
        this.repID = 0;
        this.brewerNumber = null;
        this.productSource = null;
        this.serialNumber = null;
        this.productType = null;
        this.brandName = null;
        this.fancifulName = null;
        this.applicantName = null;
        this.mailingAddress = null;
        this.formula = null;
        this.grapeVarietal = null;
        this.appellation = null;
        this.phoneNumber = null;
        this.emailAddress = null;
        this.dateOfApplication = null;
        this.printName = null;
        this.beerWineSpirit = null;
        this.alcoholPercent = null;
        this.vintageYear = null;
        this.pHLevel = null;
    }

    public Form(int formID, int repID, String brewerNumber, String productSource, String serialNumber, String productType, String brandName, String fancifulName, String applicantName, String mailingAddress, String formula, String grapeVarietal, String appellation, String phoneNumber, String emailAddress, String dateOfApplication, String printName, String beerWineSpirit, String alcoholPercent, String vintageYear, String pHLevel) {
        this.formID = formID;
        this.repID = repID;
        this.brewerNumber = brewerNumber;
        this.productSource = productSource;
        this.serialNumber = serialNumber;
        this.productType = productType;
        this.brandName = brandName;
        this.fancifulName = fancifulName;
        this.applicantName = applicantName;
        this.mailingAddress = mailingAddress;
        this.formula = formula;
        this.grapeVarietal = grapeVarietal;
        this.appellation = appellation;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.dateOfApplication = dateOfApplication;
        this.printName = printName;
        this.beerWineSpirit = beerWineSpirit;
        this.alcoholPercent = alcoholPercent;
        this.vintageYear = vintageYear;
        this.pHLevel = pHLevel;
    }

    public int getRepID(){ return repID; }
    public void setRepID(int repID){ this.repID = repID; }
    public String getBrewerNumber() {
        return brewerNumber;
    }
    public void setBrewerNumber(String brewerNumber) {
        this.brewerNumber = brewerNumber;
    }
    public String getProductSource() {
        return productSource;
    }
    public void setProductSource(String productSource) {
        this.productSource = productSource;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public String getFancifulName() {
        return fancifulName;
    }
    public void setFancifulName(String fancifulName) {
        this.fancifulName = fancifulName;
    }
    public String getApplicantName() {
        return applicantName;
    }
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }
    public String getMailingAddress() {
        return mailingAddress;
    }
    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
    public String getFormula() {
        return formula;
    }
    public void setFormula(String formula) {
        this.formula = formula;
    }
    public String getGrapeVarietal() {
        return grapeVarietal;
    }
    public void setGrapeVarietal(String grapeVarietal) {
        this.grapeVarietal = grapeVarietal;
    }
    public String getAppellation() {
        return appellation;
    }
    public void setAppellation(String appelation) {
        this.appellation = appelation;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getDateOfApplication() {
        return dateOfApplication;
    }
    public void setDateOfApplication(String dateOfApplication) {
        this.dateOfApplication = dateOfApplication;
    }
    public String getPrintName() {
        return printName;
    }
    public void setPrintName(String printName) {
        this.printName = printName;
    }
    public String getBeerWineSpirit() {
        return beerWineSpirit;
    }
    public void setBeerWineSpirit(String beerWineSpirit) {
        this.beerWineSpirit = beerWineSpirit;
    }
    public String getAlcoholPercent() {
        return alcoholPercent;
    }
    public void setAlcoholPercent(String alcoholPercent) {
        this.alcoholPercent = alcoholPercent;
    }
    public String getVintageYear() {
        return vintageYear;
    }
    public void setVintageYear(String vintageYear) {
        this.vintageYear = vintageYear;
    }
    public String getpHLevel() {
        return pHLevel;
    }
    public void setpHLevel(String pHLevel) {
        this.pHLevel = pHLevel;
    }

    public int getFormID() {
        return formID;
    }


    // Agent also has the same method?


    // Query the database to select applications where form ID matches this form
    // Update the status to be approved
    @SuppressWarnings("Duplicates")
    public void approve(Connection conn) {
        String SQL = "UPDATE APPLICATIONS SET DATEAPPROVED = CURRENT_DATE, STATUS = 'APPROVED' WHERE FORMID ="
                + this.formID;

        try {
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }

    // Query the database to select applications where form ID matches this form
    // Update the status to be denied
    @SuppressWarnings("Duplicates")
    public void deny(Connection conn) {
        String SQL = "UPDATE APPLICATIONS SET DATEREJECTED = CURRENT_DATE, STATUS = 'DENIED' WHERE FORMID ="
                + this.formID;
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }
}
