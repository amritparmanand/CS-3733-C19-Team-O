package Datatypes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.security.auth.Subject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.sql.*;
import java.util.Date;
/**
 * @author Harry James and Gabriel Entov & Percy
 * @version It 3
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
    private long formID;
    private String signature;
    private String dateIssued;
    private Comments comments;
    private String commentString;


    // Constructor
    public Form() {
        this.repID = 0;
        this.formID = 20;
        this.brewerNumber = "";
        this.productSource = "";
        this.serialNumber = "";
        this.productType = "";
        this.brandName = "";
        this.fancifulName = "";
        this.applicantName = "";
        this.mailingAddress = "";
        this.formula = "";
        this.grapeVarietal = "";
        this.appellation = "";
        this.phoneNumber = "";
        this.emailAddress = "";
        this.dateOfApplication = "";
        this.printName = "";
        this.beerWineSpirit = "";
        this.alcoholPercent = "";
        this.vintageYear = "";
        this.pHLevel = "";
        this.certificateOfApproval = false;
        this.certificateOfExemption = false;
        this.onlyState = "";
        this.distinctiveLiquor = false;
        this.resubmission = false;
        this.ttbID = 0;
        this.bottleCapacity = "";
        this.signature = "";
        this.dateIssued = "";
        this.label = new LabelImage();
        this.comments = new Comments();
        this.commentString = "";
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
    public void setFormID(long formID) {
        this.formID = formID;
    }
    public long getFormID() {
        return formID;
    }
    public LabelImage getLabel() {
        return label;
    }
    public void setLabel(LabelImage label) {
        this.label = label;
    }
    public String getSignature() {
        return signature;
    }
    public void setSignature(String signature) {
        this.signature = signature;
    }
    public String getDateIssued() {
        return dateIssued;
    }
    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }
    public Comments getComments() {
        return comments;
    }
    public void setComments(Comments comments) {
        this.comments = comments;
    }
    public String getCommentString() {
        return commentString;
    }
    public void setCommentString(String commentString) {
        this.commentString = commentString;
    }

    @SuppressWarnings("Duplicates")
    public void approve(Connection conn) {
        System.out.println("in Form Approve");
        String SQL = "UPDATE APPLICATIONS SET DATEAPPROVED = CURRENT_DATE, DATEREJECTED = null, STATUS = 'APPROVED' , DATEISSUED ='" + this.dateIssued + "', SIGNATURE ='" + this.signature + "' WHERE FORMID ="
                + this.formID;

        System.out.println(SQL);
                try {
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
        //parseGarbage
        String SQLClearGarbage = "UPDATE FORMS SET BREWERNUMBER = '" + this.parseGarbage(this.getBrewerNumber()) + "'" +
                ", PHLEVEL = '" + this.parseGarbage(this.getpHLevel()) + "'" +
                ", PRODUCTSOURCE = '" + this.parseGarbage(this.getProductSource()) + "'" +
                ", SERIALNUMBER = '" + this.parseGarbage(this.getSerialNumber()) + "'" +
                ", PRODUCTTYPE = '" + this.parseGarbage(this.getProductType()) + "'" +
                ", BRANDNAME = '" + this.parseGarbage(this.getBrandName()) + "'" +
                ", FANCIFULNAME = '" + this.parseGarbage(this.getFancifulName()) + "'" +
                ", APPLICANTNAME = '" + this.parseGarbage(this.getApplicantName()) + "'" +
                ", MAILINGADDRESS = '" + this.parseGarbage(this.getMailingAddress()) + "'" +
                ", FORMULA = '" + this.parseGarbage(this.getFormula()) + "'" +
                ", GRAPEVARIETAL = '" + this.parseGarbage(this.getGrapeVarietal()) + "'" +
                ", APPELLATION = '" + this.parseGarbage(this.getAppellation()) + "'" +
                ", PHONENUMBER = '" + this.parseGarbage(this.getPhoneNumber()) + "'" +
                ", EMAILADDRESS = '" + this.parseGarbage(this.getEmailAddress()) + "'" +
                ", CERTIFICATEOFAPPROVAL = '" + this.getCertificateOfApproval() + "'" +
                ", CERTIFICATEOFEXEMPTION = '" + this.getCertificateOfExemption() + "'" +
                ", ONLYSTATE = '" + this.parseGarbage(this.getOnlyState()) + "'" +
                ", DISTINCTIVELIQUOR = '" + this.getDistinctiveLiquor() + "'" +
                ", BOTTLECAPACITY = '" + this.parseGarbage(this.getBottleCapacity()) + "'" +
                ", RESUBMISSION = '" + this.getResubmission() + "'" +
                ", DATEOFAPPLICATION = '" + this.parseGarbage(this.getDateOfApplication()) + "'" +
                ", PRINTNAME = '" + this.parseGarbage(this.getPrintName()) + "'" +
                ", BEERWINESPIRIT = '" + this.parseGarbage(this.getBeerWineSpirit()) + "'" +
                ", ALCOHOLPERCENT = '" + this.parseGarbage(this.getAlcoholPercent()) + "'" +
                ", VINTAGEYEAR = '" + this.parseGarbage(this.getVintageYear()) + "'" +

                " WHERE FORMID ="
                + this.formID;
        try {
            PreparedStatement ps = conn.prepareStatement(SQLClearGarbage);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }
    @SuppressWarnings("Duplicates")
    public void deny(Connection conn) throws Exception{
        String SQL = "UPDATE APPLICATIONS SET DATEREJECTED = CURRENT_DATE,STATUS = 'DENIED', COMMENTS = '"+ comments.generateComments() + "' WHERE FORMID ="+ this.formID;
               try {
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.executeUpdate();

            ps.close();
               } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
        //parseGarbage
        String SQLClearGarbage = "UPDATE FORMS SET BREWERNUMBER = '" + this.parseGarbage(this.getBrewerNumber()) + "'" +
                ", PHLEVEL = '" + this.parseGarbage(this.getpHLevel()) + "'" +
                ", PRODUCTSOURCE = '" + this.parseGarbage(this.getProductSource()) + "'" +
                ", SERIALNUMBER = '" + this.parseGarbage(this.getSerialNumber()) + "'" +
                ", PRODUCTTYPE = '" + this.parseGarbage(this.getProductType()) + "'" +
                ", BRANDNAME = '" + this.parseGarbage(this.getBrandName()) + "'" +
                ", FANCIFULNAME = '" + this.parseGarbage(this.getFancifulName()) + "'" +
                ", APPLICANTNAME = '" + this.parseGarbage(this.getApplicantName()) + "'" +
                ", MAILINGADDRESS = '" + this.parseGarbage(this.getMailingAddress()) + "'" +
                ", FORMULA = '" + this.parseGarbage(this.getFormula()) + "'" +
                ", GRAPEVARIETAL = '" + this.parseGarbage(this.getGrapeVarietal()) + "'" +
                ", APPELLATION = '" + this.parseGarbage(this.getAppellation()) + "'" +
                ", PHONENUMBER = '" + this.parseGarbage(this.getPhoneNumber()) + "'" +
                ", EMAILADDRESS = '" + this.parseGarbage(this.getEmailAddress()) + "'" +
                ", CERTIFICATEOFAPPROVAL = '" + this.getCertificateOfApproval() + "'" +
                ", CERTIFICATEOFEXEMPTION = '" + this.getCertificateOfExemption() + "'" +
                ", ONLYSTATE = '" + this.parseGarbage(this.getOnlyState()) + "'" +
                ", DISTINCTIVELIQUOR = '" + this.getDistinctiveLiquor() + "'" +
                ", BOTTLECAPACITY = '" + this.parseGarbage(this.getBottleCapacity()) + "'" +
                ", RESUBMISSION = '" + this.getResubmission() + "'" +
                ", DATEOFAPPLICATION = '" + this.parseGarbage(this.getDateOfApplication()) + "'" +
                ", PRINTNAME = '" + this.parseGarbage(this.getPrintName()) + "'" +
                ", BEERWINESPIRIT = '" + this.parseGarbage(this.getBeerWineSpirit()) + "'" +
                ", ALCOHOLPERCENT = '" + this.parseGarbage(this.getAlcoholPercent()) + "'" +
                ", VINTAGEYEAR = '" + this.parseGarbage(this.getVintageYear()) + "'" +

                " WHERE FORMID ="
                + this.formID;
        try {
            PreparedStatement ps = conn.prepareStatement(SQLClearGarbage);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")
    public void resubmitApp(Connection conn) {
        //set the status to pending 
        //if(formID != getFormID())
        String SQL = "UPDATE APPLICATIONS SET DATESUBMITTED = CURRENT_DATE, STATUS = 'PENDING' WHERE FORMID ="
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

    @SuppressWarnings("Duplicates")
    public void passForm(Connection connection, long formID, String username){
        //take in a username of an Agent, query the agent table for the ID, the rest is the same
        int id = 0;
        String getID = "SELECT TTBID FROM AGENTS WHERE USERNAME = '" + username + "'";
        try {
            ResultSet rset = connection.createStatement().executeQuery(getID);
            while(rset.next())
                id = rset.getInt("ttbID");
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }

        String s = "UPDATE APPLICATIONS SET TTBID = " + id + " WHERE FORMID = " + formID;
        try {
            PreparedStatement ps = connection.prepareStatement(s);
            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }

    public boolean isValid(){
        if(brewerNumber == "" || productSource == "" || serialNumber == "" || productType == "" || brandName == "" ||
        applicantName == "" || alcoholPercent == "" || phoneNumber == "" || emailAddress == ""  ||
        dateOfApplication == "" || printName == "") {

            return false;
        }
        else
            return true;
    }

    /**
     * Automatically generates and inserts an Application into database when Form is inserted
     * @param connection
     * @param formID
     * @param repID
     * @param dateSubmitted
     * @throws SQLException
     */
    public void addApp(Connection connection, int formID, int repID, String dateSubmitted, String dateIssued, String signature) throws SQLException, Exception{
        String Apps1 = "INSERT INTO Applications(APPID, FORMID, REPID, TTBID, DATESUBMITTED, DATEAPPROVED, DATEREJECTED,STATUS, DATEISSUED, SIGNATURE, COMMENTS) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement prepStmt = connection.prepareStatement(Apps1);
        ResultSet seqVal;
        try {
            seqVal = connection.prepareStatement("values (next value for appIDSequence)").executeQuery();
            seqVal.next();
            prepStmt.setInt(1, seqVal.getInt(1));
            prepStmt.setInt(2,formID);
            prepStmt.setInt(3,repID);
            prepStmt.setNull(4, Types.INTEGER);
            prepStmt.setString(5, dateSubmitted);
            prepStmt.setNull(6, Types.VARCHAR);
            prepStmt.setNull(7, Types.VARCHAR);
            prepStmt.setString(8, "PENDING");
            prepStmt.setString(9, dateIssued);
            prepStmt.setString(10,signature);
            prepStmt.setNull(11, Types.VARCHAR);
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
    public void insertForm(Connection connection) throws  Exception {

        String Forms1 = "INSERT INTO Forms(FORMID, REPID, BREWERNUMBER, PRODUCTSOURCE, SERIALNUMBER, " +
                "PRODUCTTYPE, BRANDNAME, FANCIFULNAME, APPLICANTNAME, MAILINGADDRESS, FORMULA, GRAPEVARIETAL, " +
                "APPELLATION, PHONENUMBER, EMAILADDRESS, CERTIFICATEOFAPPROVAL, CERTIFICATEOFEXEMPTION, ONLYSTATE, " +
                "DISTINCTIVELIQUOR, BOTTLECAPACITY, RESUBMISSION, TTBID, DATEOFAPPLICATION, PRINTNAME, BEERWINESPIRIT, " +
                "ALCOHOLPERCENT, VINTAGEYEAR, PHLEVEL, LABELIMAGE) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement prepStmt = connection.prepareStatement(Forms1);
        ResultSet seqVal;
        try {
            seqVal = connection.prepareStatement("values (next value for FormIDSequence)").executeQuery();
            seqVal.next();
            this.setFormID(seqVal.getInt(1));
            prepStmt.setInt(1, seqVal.getInt(1));
            prepStmt.setInt(2, getRepID());
            prepStmt.setString(3, getBrewerNumber());
            prepStmt.setString(4, getProductSource());
            prepStmt.setString(5, getSerialNumber());
            prepStmt.setString(6, getProductType());
            prepStmt.setString(7, getBrandName());
            prepStmt.setString(8, getFancifulName());
            prepStmt.setString(9, getApplicantName());
            prepStmt.setString(10, getMailingAddress());
            prepStmt.setString(11, getFormula());
            prepStmt.setString(12, getGrapeVarietal());
            prepStmt.setString(13, getAppellation());
            prepStmt.setString(14, getPhoneNumber());
            prepStmt.setString(15, getEmailAddress());
            prepStmt.setBoolean(16, getCertificateOfApproval());
            prepStmt.setBoolean(17, getCertificateOfExemption());
            prepStmt.setString(18, getOnlyState());
            prepStmt.setBoolean(19, getDistinctiveLiquor());
            prepStmt.setString(20, getBottleCapacity());
            prepStmt.setBoolean(21, getResubmission());
            if (getTtbID() != 0)
                prepStmt.setInt(22, getTtbID());
            else
                prepStmt.setNull(22, java.sql.Types.INTEGER);
            prepStmt.setString(23, getDateOfApplication());
            prepStmt.setString(24, getPrintName());
            prepStmt.setString(25, getBeerWineSpirit());
            prepStmt.setString(26, getAlcoholPercent());
            prepStmt.setString(27, getVintageYear());
            prepStmt.setString(28, getpHLevel());
            File slimebert = getLabel().getLabelFile();
            FileInputStream blobert = new FileInputStream(slimebert);
            prepStmt.setBinaryStream(29, blobert, (int) slimebert.length());

            addApp(connection, seqVal.getInt(1), getRepID(), getDateOfApplication(), dateIssued, signature);
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
    @SuppressWarnings("Duplicates")
    public void resubmitForm(Connection connection) throws SQLException, FileNotFoundException {

        String SQL = "UPDATE FORMS SET BREWERNUMBER = '" + this.getBrewerNumber() + "'" +
                ", PHLEVEL = '" + this.getpHLevel() + "'" +
                ", PRODUCTSOURCE = '" + this.getProductSource() + "'" +
                ", SERIALNUMBER = '" + this.getSerialNumber() + "'" +
                ", PRODUCTTYPE = '" + this.getProductType() + "'" +
                ", BRANDNAME = '" + this.getBrandName() + "'" +
                ", FANCIFULNAME = '" + this.getFancifulName() + "'" +
                ", APPLICANTNAME = '" + this.getApplicantName() + "'" +
                ", MAILINGADDRESS = '" + this.getMailingAddress() + "'" +
                ", FORMULA = '" + this.getFormula() + "'" +
                ", GRAPEVARIETAL = '" + this.getGrapeVarietal() + "'" +
                ", APPELLATION = '" + this.getAppellation() + "'" +
                ", PHONENUMBER = '" + this.getPhoneNumber() + "'" +
                ", EMAILADDRESS = '" + this.getEmailAddress() + "'" +
                ", CERTIFICATEOFAPPROVAL = '" + this.getCertificateOfApproval() + "'" +
                ", CERTIFICATEOFEXEMPTION = '" + this.getCertificateOfExemption() + "'" +
                ", ONLYSTATE = '" + this.getOnlyState() + "'" +
                ", DISTINCTIVELIQUOR = '" + this.getDistinctiveLiquor() + "'" +
                ", BOTTLECAPACITY = '" + this.getBottleCapacity() + "'" +
                ", RESUBMISSION = '" + this.getResubmission() + "'" +
                ", DATEOFAPPLICATION = '" + this.getDateOfApplication() + "'" +
                ", PRINTNAME = '" + this.getPrintName() + "'" +
                ", BEERWINESPIRIT = '" + this.getBeerWineSpirit() + "'" +
                ", ALCOHOLPERCENT = '" + this.getAlcoholPercent() + "'" +
                ", VINTAGEYEAR = '" + this.getVintageYear() + "'" +

                " WHERE FORMID ="
                + this.formID;
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);

            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }

        resubmitApp(connection);

    }

    /**
     *
     * @param conn
     * @return the result set of approved applications
     * @throws SQLException
     */
    public ResultSet getApprovedApplications(Connection conn, String condition, String type) throws SQLException{
        String retrieve = "SELECT FANCIFULNAME, BRANDNAME, PRODUCTTYPE, PHLEVEL, ALCOHOLPERCENT," +
                "VINTAGEYEAR FROM APPLICATIONS JOIN FORMS " +
                "ON FORMS.FORMID = APPLICATIONS.FORMID " +
                "WHERE APPLICATIONS.STATUS='APPROVED' AND ((UPPER(FANCIFULNAME) LIKE UPPER(?)) OR (UPPER(BRANDNAME) LIKE UPPER(?))) AND " + type;

        System.out.println(retrieve);
        PreparedStatement ps = conn.prepareStatement(retrieve, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, "%"+condition+"%");
        ps.setString(2, "%"+condition+"%");

        ResultSet rset = ps.executeQuery();
        return rset;
    }

    public String getFormStatus(Connection connection){

        String status = "";

        String getID = "SELECT STATUS FROM APPLICATIONS join FORMS on forms.FORMID = APPLICATIONS.FORMID WHERE FORMS.FORMID = " + this.formID;
        try {
            ResultSet rset = connection.createStatement().executeQuery(getID);
            while(rset.next())
                status = rset.getString("status");
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }

        return status;
    }

    public String parseGarbage(String s) {
        String result;
        if (s != null) {
            if (s.contains("#")) {
                result = s.substring(0, s.indexOf('-'));
            } else {
                result = s;
            }

            return result;
        }
        return "";
    }


    public String parseStyle(String s) {
        String result;
        if (s != null) {
            if (s.contains("#")) {
                result = s.substring(s.indexOf('-'));
            } else {
                result = "";
            }

            return result;
        }
        return "";
    }

    @SuppressWarnings("Duplicates") public ObservableList<String> autoSearch(Connection connection){
        ObservableList<String> result = FXCollections.observableArrayList();

        String getID = "SELECT distinct BRANDNAME, FANCIFULNAME FROM FORMS";
        try {
            ResultSet rset = connection.createStatement().executeQuery(getID);
            while(rset.next())
                result.add(rset.getString("brandName"));
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }

        return result;
    }

}
