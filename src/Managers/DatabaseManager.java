package Managers;

import Datatypes.Account;
import Datatypes.Agent;
import Datatypes.Form;
import Datatypes.Manufacturer;
import Fuzzy.Damerau_Levenshtein;
import Fuzzy.FuzzyContext;
import Fuzzy.Levenshtein;
import Fuzzy.hiddenScore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
/**
 * @author Amrit Parmanand & Percy
 * @version It 2
 * @since It 1
 * Manages the database, handles accessing and inserting data
 */
public class DatabaseManager {
    private Connection connection;
    private Statement stmt;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Connection getConnection()
    {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public Statement getStmt() {
        return stmt;
    }
    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public DatabaseManager() {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:derby:ttbDB;create=true");
            stmt = connection.createStatement();
        }
        catch(SQLException e){
            if (!e.getSQLState().equals("XBM0J")) {
                System.out.println("Connection Failed. Check stacktrace.");
                e.printStackTrace();
            }
        }
        this.connection = connection;
        this.stmt = stmt;

    }

    // Got connected, codes start here

    // Generate the tables in database and create the sequences for ids
    public void generateTables(){
        String createApplications = "create table Applications(" +
                "appID int constraint Applications_pk primary key," +
                "formID int /*constraint APPLICATIONS_FORMS_FORMID_FK	references FORMS*/," +
                "repID int /*constraint APPLICATIONS_REPRESENTATIVES_REPID_FK	references REPRESENTATIVES*/," +
                "ttbID int /*constraint APPLICATIONS_AGENTS_TTBID_FK references AGENTS*/," +
                "agentName VARCHAR(40)," +
                "dateSubmitted VARCHAR(20) ," +
                "dateApproved VARCHAR(20)," +
                "dateRejected VARCHAR(20)," +
                "dateExpired VARCHAR(20)," +
                "status VARCHAR(15))";
        String createRepresentatives = "create table Representatives" +
                "(repID int constraint Representatives_pk	primary key, " +
                "username varchar(20),	" +
                "password varchar(65), 	" +
                "fullName varchar(50),	" +
                "companyName varchar(50),	" +
                "email varchar(20),	" +
                "phone varchar(15))";
        String createAgents = "create table Agents" +
                "(ttbID int	constraint Agents_pk primary key, " +
                "username varchar(20), " +
                "password varchar(65), " +
                "fullName varchar(50),	" +
                "email varchar(60),	" +
                "phone varchar(15))";
        String createForms = "create table Forms(" +
                "formID bigint	constraint Forms_pk	primary key, " +
                "repID int, " +
                "brewerNumber varchar(60),	" +
                "productSource varchar(60),	" +
                "serialNumber varchar(60),	" +
                "productType varchar(60),	" +
                "brandName varchar(60),	" +
                "fancifulName varchar(60),	" +
                "applicantName varchar(200),	" +
                "mailingAddress varchar(80), " +
                "formula varchar(80), " +
                "grapeVarietal varchar(80),	" +
                "appellation varchar(60), " +
                "phoneNumber varchar(20), " +
                "emailAddress varchar(50),	" +
                "certificateOfApproval boolean," +   //begin new
                "certificateOfExemption boolean," +
                "onlyState varchar(2)," +
                "distinctiveLiquor boolean," +
                "bottleCapacity VARCHAR(5)," +
                "resubmission boolean," +
                "ttbID int ," + //end new
                "dateOfApplication VARCHAR(30) , " +
                "printName varchar(40),	" +
                "beerWineSpirit varchar(60), " +
                "alcoholPercent varchar(60),	" +
                "vintageYear varchar(60), " +
                "phLevel varchar(60), " +
                "labelImage BLOB)";
        String createUniqueReps = "create unique index Representatives_username_uindex " +
                "on Representatives (username)";
        String createUniqueAgents = "create unique index Agents_username_uindex " +
                "on Agents (username)";
        try {
            this.stmt.execute(createRepresentatives);
            this.stmt.execute(createAgents);
            this.stmt.execute(createForms);
            this.stmt.execute(createApplications);
            this.stmt.execute(createUniqueReps);
            this.stmt.execute(createUniqueAgents);
        }
        catch (SQLException e){
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }
    public void createSequences(){
        String repSequence = "create sequence repIDSequence as int start with 800 increment by 1";
        String formSequence = "create sequence formIDSequence as int start with 800 increment by 1";
        String appSequence = "create sequence appIDSequence as int start with 800 increment by 1";

        try {
            this.stmt.execute(repSequence);
            this.stmt.execute(formSequence);
            this.stmt.execute(appSequence);
        }
        catch (SQLException e){
            if (!e.getSQLState().equals("X0Y68"))
                e.printStackTrace();
        }
    }

    // Find username and password for an account by its id
    public String mFindUsername(int id){
        String uname = "";
        try {
            String getData = "select * from REPRESENTATIVES where REPID = " + id;
            ResultSet result = this.getStmt().executeQuery(getData);
            while(result.next()){
                uname = result.getString("username");
            }
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
        return uname;
    }
    public String mFindPassword(int id){
        String hashedPassword = "";
        try {
            String getData = "select * from REPRESENTATIVES where REPID = " + id;
            ResultSet result = this.getStmt().executeQuery(getData);
            while(result.next()){
                hashedPassword = result.getString("password");
            }
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
        return hashedPassword;
    }
    public String aFindUsername(int id){
        String uname = "";
        try {
            String getData = "select * from AGENTS where TTBID = " + id;
            ResultSet result = this.getStmt().executeQuery(getData);
            while(result.next()){
                uname = result.getString("username");
            }
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
        return uname;
    }
    public String aFindPassword(int id){
        String hashedPassword = "";
        try {
            String getData = "select * from AGENTS where TTBID = " + id;
            ResultSet result = this.getStmt().executeQuery(getData);
            while(result.next()){
                hashedPassword = result.getString("password");
            }
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
        return hashedPassword;
    }

    // Create an instance of an account once logged in
    @SuppressWarnings("Duplicates") public Manufacturer mCreate(int id){
        String uname = "";
        String pword = "";
        String fname = "";
        String email = "";
        String phone = "";
        String cname = "";
        try {
            String getData = "select * from REPRESENTATIVES where REPID = " + id;
            ResultSet result = this.getStmt().executeQuery(getData);
            while(result.next()){
                uname = result.getString("username");
                pword = result.getString("password");
                fname = result.getString("fullName");
                email = result.getString("email");
                phone = result.getString("phone");
                cname = result.getString("companyName");
            }
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
        Manufacturer m = new Manufacturer(uname,pword,fname,email,phone,id,cname);
        return m;
    }
    @SuppressWarnings("Duplicates") public Agent aCreate(int id){
        String uname = "";
        String pword = "";
        String fname = "";
        String email = "";
        String phone = "";
        try {
            String getData = "select * from AGENTS where TTBID = " + id;
            ResultSet result = this.getStmt().executeQuery(getData);
            while(result.next()){
                uname = result.getString("username");
                pword = result.getString("password");
                fname = result.getString("fullName");
                email = result.getString("email");
                phone = result.getString("phone");
            }
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
        Agent a = new Agent(uname,pword,fname,email,phone,id);
        return a;
    }

    // Insert a form from Manufacturer side into database
//    public void insertForm(Form form) throws SQLException, FileNotFoundException {
//
//        String Forms1 = "INSERT INTO Forms(FORMID, REPID, BREWERNUMBER, PRODUCTSOURCE, SERIALNUMBER, " +
//                "PRODUCTTYPE, BRANDNAME, FANCIFULNAME, APPLICANTNAME, MAILINGADDRESS, FORMULA, GRAPEVARIETAL, " +
//                "APPELLATION, PHONENUMBER, EMAILADDRESS, CERTIFICATEOFAPPROVAL, CERTIFICATEOFEXEMPTION, ONLYSTATE, " +
//                "DISTINCTIVELIQUOR, BOTTLECAPACITY, RESUBMISSION, TTBID, DATEOFAPPLICATION, PRINTNAME, BEERWINESPIRIT, " +
//                "ALCOHOLPERCENT, VINTAGEYEAR, PHLEVEL, LABELIMAGE) " +
//                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//        PreparedStatement prepStmt = connection.prepareStatement(Forms1);
//        ResultSet seqVal = null;
//        try {
//            seqVal = connection.prepareStatement("values (next value for FormIDSequence)").executeQuery();
//            seqVal.next();
//            prepStmt.setInt(1,seqVal.getInt(1));
//            prepStmt.setInt(2, form.getRepID());
//            prepStmt.setString(3, form.getBrewerNumber());
//            prepStmt.setString(4, form.getProductSource());
//            prepStmt.setString(5, form.getSerialNumber());
//            prepStmt.setString(6, form.getProductType());
//            prepStmt.setString(7, form.getBrandName());
//            prepStmt.setString(8, form.getFancifulName());
//            prepStmt.setString(9, form.getApplicantName());
//            prepStmt.setString(10, form.getMailingAddress());
//            prepStmt.setString(11, form.getFormula());
//            prepStmt.setString(12, form.getGrapeVarietal());
//            prepStmt.setString(13, form.getAppellation());
//            prepStmt.setString(14, form.getPhoneNumber());
//            prepStmt.setString(15, form.getEmailAddress());
//            prepStmt.setBoolean(16, form.getCertificateOfApproval());
//            prepStmt.setBoolean(17, form.getCertificateOfExemption());
//            prepStmt.setString(18, form.getOnlyState());
//            prepStmt.setBoolean(19, form.getDistinctiveLiquor());
//            prepStmt.setString(20, form.getBottleCapacity());
//            prepStmt.setBoolean(21, form.getResubmission());
//            prepStmt.setInt(22, form.getTtbID());
//            prepStmt.setString(23, form.getDateOfApplication());
//            prepStmt.setString(24, form.getPrintName());
//            prepStmt.setString(25, form.getBeerWineSpirit());
//            prepStmt.setString(26, form.getAlcoholPercent());
//            prepStmt.setString(27, form.getVintageYear());
//            prepStmt.setString(28, form.getpHLevel());
//            File slimebert = form.getLabel().getLabelFile();
//            FileInputStream blobert = new FileInputStream(slimebert);
//            prepStmt.setBinaryStream(29, blobert, (int) slimebert.length());
//            addApp(seqVal.getInt(1),form.getRepID(),form.getDateOfApplication());
//            prepStmt.executeUpdate();
//            prepStmt.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    // Automatically generates and inserts an Application into database when Form is inserted
//    public void addApp(int formID, int repID, String dateSubmitted) throws SQLException{
//        String Apps1 = "INSERT INTO Applications(APPID, FORMID, REPID, TTBID, DATESUBMITTED, DATEAPPROVED, DATEREJECTED,STATUS) " +
//                "VALUES(?,?,?,?,?,?,?,?)";
//        PreparedStatement prepStmt = connection.prepareStatement(Apps1);
//        ResultSet seqVal = null;
//        try {
//            seqVal = connection.prepareStatement("values (next value for appIDSequence)").executeQuery();
//            seqVal.next();
//            prepStmt.setInt(1, seqVal.getInt(1));
//            prepStmt.setInt(2,formID);
//            prepStmt.setInt(3,repID);
//            prepStmt.setNull(4, Types.INTEGER);
//            prepStmt.setString(5, dateSubmitted);
//            prepStmt.setNull(6, Types.VARCHAR);
//            prepStmt.setNull(7, Types.VARCHAR);
//            prepStmt.setString(8, "PENDING");
//            prepStmt.executeUpdate();
//            prepStmt.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void insertDefault() throws SQLException{
        String mPassword = this.passwordEncoder.encode("manu");
        String aPassword = this.passwordEncoder.encode("ttb");

        String mDefault = "insert into REPRESENTATIVES values (0, 'manu', '" + mPassword + "', 'manu', 'manu', 'manu', 'manu')";
        String aDefault = "insert into AGENTS values (0, 'ttb', '" + aPassword + "', 'ttb', 'ttb', 'ttb')";
        try {
            this.stmt.execute(mDefault);
            this.stmt.execute(aDefault);
        }catch(SQLException e){
            if (!e.getSQLState().equals("23505"))
                e.printStackTrace();
        }
    }
}
