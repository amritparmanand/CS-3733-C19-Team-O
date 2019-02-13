package Datatypes;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.sql.*;
import java.util.Date;
/**
 * @author Harry James and Gabriel Entov
 * @version It 2
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
    private Boolean certificateOfApproval;
    private Boolean certificateOfExemption;
    private String onlyState;
    private Boolean distinctiveLiquor;
    private Boolean resubmission;
    private int ttbID;
    private LabelImage label;
    private String dateOfApplication;
    private String printName;
    private String beerWineSpirit;
    private String alcoholPercent;
    private String vintageYear;
    private String pHLevel;
    private String bottleCapacity;
    private int formID;


    // Constructor
    public Form() {
        this.repID = 0;
        this.formID = 0;
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
        this.certificateOfApproval = null;
        this.certificateOfExemption = null;
        this.onlyState = null;
        this.distinctiveLiquor = null;
        this.resubmission = null;
        this.ttbID = 0;
        this.bottleCapacity = null;
    }

    // Getters and setters
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
    public Boolean getCertificateOfApproval() {
        return certificateOfApproval;
    }
    public void setCertificateOfApproval(Boolean certificateOfApproval) {
        this.certificateOfApproval = certificateOfApproval;
    }
    public Boolean getCertificateOfExemption() {
        return certificateOfExemption;
    }
    public void setCertificateOfExemption(Boolean certificateOfExemption) {
        this.certificateOfExemption = certificateOfExemption;
    }
    public String getOnlyState() {
        return onlyState;
    }
    public void setOnlyState(String onlyState) {
        this.onlyState = onlyState;
    }
    public String getBottleCapacity() {
        return bottleCapacity;
    }
    public void setBottleCapacity(String bottleCapacity) {
        this.bottleCapacity = bottleCapacity;
    }
    public Boolean getDistinctiveLiquor() {
        return distinctiveLiquor;
    }
    public void setDistinctiveLiquor(Boolean distinctiveLiquor) {
        this.distinctiveLiquor = distinctiveLiquor;
    }
    public Boolean getResubmission() {
        return resubmission;
    }
    public void setResubmission(Boolean resubmission) {
        this.resubmission = resubmission;
    }
    public int getTtbID() {
        return ttbID;
    }
    public void setTtbID(int ttbID) {
        this.ttbID = ttbID;
    }
    public void setFormID(int formID) {
        this.formID = formID;
    }
    public int getFormID() {
        return formID;
    }
    public LabelImage getLabel() {
        return label;
    }
    public void setLabel(LabelImage label) {
        this.label = label;
    }


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

    public void receive(Connection connection, int passer, int receiver){
        String SQL = "UPDATE APPLICATIONS SET TTBID = " + receiver + ", WHERE FORMID =" + this.formID;
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }

    /**
     * Automatically generates and inserts an Application into database when Form is inserted
     * @param connection
     * @throws SQLException
     */
    public void addApp(Connection connection) throws SQLException{
        String Apps1 = "INSERT INTO Applications(APPID, FORMID, REPID, TTBID, DATESUBMITTED, DATEAPPROVED, DATEREJECTED,STATUS) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement prepStmt = connection.prepareStatement(Apps1);
        ResultSet seqVal = null;
        try {
            seqVal = connection.prepareStatement("values (next value for appIDSequence)").executeQuery();
            seqVal.next();
            prepStmt.setInt(1, seqVal.getInt(1));
            prepStmt.setInt(2, this.getFormID());
            prepStmt.setInt(3, this.getRepID());
            prepStmt.setNull(4, Types.INTEGER);
            prepStmt.setString(5, this.getDateOfApplication());
            prepStmt.setNull(6, Types.VARCHAR);
            prepStmt.setNull(7, Types.VARCHAR);
            prepStmt.setString(8, "PENDING");
            prepStmt.executeUpdate();
            prepStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert a form from Manufacturer side into database
     * @param connection
     * @throws SQLException
     */
    public void insert(Connection connection) throws SQLException {

        String Forms1 = "INSERT INTO Forms(FORMID, REPID, BREWERNUMBER, PRODUCTSOURCE, SERIALNUMBER, " +
                "PRODUCTTYPE, BRANDNAME, FANCIFULNAME, APPLICANTNAME, MAILINGADDRESS, FORMULA, GRAPEVARIETAL, " +
                "APPELLATION, PHONENUMBER, EMAILADDRESS, /* insert pg 3 things,*/ DATEOFAPPLICATION, PRINTNAME, BEERWINESPIRIT, ALCOHOLPERCENT, " +
                "VINTAGEYEAR, PHLEVEL) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement prepStmt = connection.prepareStatement(Forms1);
        ResultSet seqVal = null;
        try {
            seqVal = connection.prepareStatement("values (next value for FormIDSequence)").executeQuery();
            seqVal.next();
            prepStmt.setInt(1,seqVal.getInt(1));
            prepStmt.setInt(2, this.getRepID());
            prepStmt.setString(3, this.getBrewerNumber());
            prepStmt.setString(4, this.getProductSource());
            prepStmt.setString(5, this.getSerialNumber());
            prepStmt.setString(6, this.getProductType());
            prepStmt.setString(7, this.getBrandName());
            prepStmt.setString(8, this.getFancifulName());
            prepStmt.setString(9, this.getApplicantName());
            prepStmt.setString(10, this.getMailingAddress());
            prepStmt.setString(11, this.getFormula());
            prepStmt.setString(12, this.getGrapeVarietal());
            prepStmt.setString(13, this.getAppellation());
            prepStmt.setString(14, this.getPhoneNumber());
            prepStmt.setString(15, this.getEmailAddress());
            //page3  args goes here
            prepStmt.setString(16, this.getDateOfApplication());
            prepStmt.setString(17, this.getPrintName());
            prepStmt.setString(18, this.getBeerWineSpirit());
            prepStmt.setString(19, this.getAlcoholPercent());
            prepStmt.setString(20, this.getVintageYear());
            prepStmt.setString(21, this.getpHLevel());
            addApp(connection);
            prepStmt.executeUpdate();
            prepStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param conn
     * @return the result set of approved applications
     * @throws SQLException
     */
    public ResultSet getApprovedApplications(Connection conn) throws SQLException{
        String retrieve = "SELECT * FROM APPLICATIONS JOIN FORMS " +
                "ON FORMS.FORMID = APPLICATIONS.FORMID " +
                "WHERE APPLICATIONS.STATUS='APPROVED'";
        ResultSet rset = conn.createStatement().executeQuery(retrieve);
        return rset;
    }
}
