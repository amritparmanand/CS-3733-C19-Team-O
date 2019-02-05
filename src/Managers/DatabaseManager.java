package Managers;

import Datatypes.Form;
import java.sql.*;

public class DatabaseManager {
    private Connection connection;
    private Statement stmt;

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

    public void generateTables(){
        String createApplications = "create table Applications(" +
                "appID int constraint Applications_pk primary key," +
                "formID int constraint APPLICATIONS_FORMS_FORMID_FK	references FORMS," +
                "repID int constraint APPLICATIONS_REPRESENTATIVES_REPID_FK	references REPRESENTATIVES," +
                "ttbID int constraint APPLICATIONS_AGENTS_TTBID_FK references AGENTS," +
                "dateSubmitted VARCHAR(20) ," +
                "dateApproved VARCHAR(20)," +
                "dateRejected VARCHAR(20))";
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
                "email varchar(20),	" +
                "phone varchar(15))";
        String createForms = "create table Forms(" +
                "formID int	constraint Forms_pk	primary key, " +
                "repID int, " +
                "brewerNumber int,	" +
                "productSource int,	" +
                "serialNumber int,	" +
                "productType int,	" +
                "brandName varchar(20),	" +
                "fancifulName varchar(20),	" +
                "applicantName varchar(40),	" +
                "mailingAddress varchar(80), " +
                "formula varchar(20), " +
                "grapeVarietal varchar(20),	" +
                "appellation varchar(20), " +
                "phoneNumber varchar(15), " +
                "emailAddress varchar(30),	" +
                "dateOfApplication VARCHAR(20) , " +
                "printName varchar(40),	" +
                "beerWineSpirit int, " +
                "alcoholPercent double,	" +
                "vintageYear int, " +
                "phLevel double)";
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
        String repSequence = "create sequence repIDSequence as int start with 1";
        String formSequence = "create sequence formIDSequence as int start with 1";
        String appSequence = "create sequence agentIDSequence as int start with 1";

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

    public void insertForm(Form form) throws SQLException {
        form.setApplicantName("DankMEME");
        form.setBeerWineSpirit(11);
        form.setpHLevel(1.1);
        form.setVintageYear(1111);
        form.setAlcoholPercent(1000);



        String Forms1 = "INSERT INTO Forms(FORMID, REPID, BREWERNUMBER, PRODUCTSOURCE, SERIALNUMBER, " +
                "PRODUCTTYPE, BRANDNAME, FANCIFULNAME, APPLICANTNAME, MAILINGADDRESS, FORMULA, GRAPEVARIETAL, " +
                "APPELLATION, PHONENUMBER, EMAILADDRESS, DATEOFAPPLICATION, PRINTNAME, BEERWINESPIRIT, ALCOHOLPERCENT, " +
                "VINTAGEYEAR, PHLEVEL) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement prepStmt = connection.prepareStatement(Forms1);

        try {
            ResultSet seqVal = connection.prepareStatement("values (next value for FormIDSequence)").executeQuery();
            seqVal.next();
            prepStmt.setInt(1,seqVal.getInt(1));
            prepStmt.setInt(2, form.getRepID());
            prepStmt.setInt(3, form.getBrewerNumber());
            prepStmt.setInt(4, form.getProductSource());
            prepStmt.setInt(5, form.getSerialNumber());
            prepStmt.setInt(6, form.getProductType());
            prepStmt.setString(7, form.getBrandName());
            prepStmt.setString(8, form.getFancifulName());
            prepStmt.setString(9, form.getApplicantName());
            prepStmt.setString(10, form.getMailingAddress());
            prepStmt.setString(11, form.getFormula());
            prepStmt.setString(12, form.getGrapeVarietal());
            prepStmt.setString(13, form.getAppellation());
            prepStmt.setString(14, form.getPhoneNumber());
            prepStmt.setString(15, form.getEmailAddress());
            prepStmt.setString(16, form.getDateOfApplication());
            prepStmt.setString(17, form.getPrintName());
            prepStmt.setInt(18, form.getBeerWineSpirit());
            prepStmt.setDouble(19, form.getAlcoholPercent());
            prepStmt.setInt(20, form.getVintageYear());
            prepStmt.setDouble(21, form.getpHLevel());
            prepStmt.executeUpdate();
            prepStmt.close();

        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    public void executeStatement(PreparedStatement ps) {
        try {
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
}
