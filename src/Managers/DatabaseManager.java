package Managers;

import Datatypes.Form;
import java.sql.*;

public class DatabaseManager {
    private Connection connection;
    private Statement stmt;

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
                "dateSubmitted date," +
                "dateApproved date," +
                "dateRejected date)";
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
                "dateOfApplication date, " +
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
            this.stmt.execute(createApplications);
            this.stmt.execute(createRepresentatives);
            this.stmt.execute(createAgents);
            this.stmt.execute(createForms);
            this.stmt.execute(createUniqueReps);
            this.stmt.execute(createUniqueAgents);
        }
        catch (SQLException e){
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }

    public void insertForm(Form form) throws SQLException
    {
        form.setForms_pk(1);
        form.setApplicantName("DankMEME");
        form.setBeerWineSpirit(11);
        form.setpHLevel(1.1);
        form.setVintageYear(1111);
        form.setAlcoholPercent(1000);

        String Forms1 = "INSERT INTO Forms(FORMID, BREWERNUMBER, PRODUCTSOURCE, SERIALNUMBER, PRODUCTTYPE, BRANDNAME, FANCIFULNAME, APPLICANTNAME, MAILINGADDRESS, FORMULA, GRAPEVARIETAL, APPELLATION, PHONENUMBER, EMAILADDRESS, DATEOFAPPLICATION, PRINTNAME, BEERWINESPIRIT, ALCOHOLPERCENT, VINTAGEYEAR, PHLEVEL) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement prepStmt = connection.prepareStatement(Forms1);

        try {

            prepStmt.setInt(1,form.getForms_pk());
            prepStmt.setInt(2, form.getBrewerNumber());
            prepStmt.setInt(3, form.getProductSource());
            prepStmt.setInt(4, form.getSerialNumber());
            prepStmt.setInt(5, form.getProductType());
            prepStmt.setString(6, form.getBrandName());
            prepStmt.setString(7, form.getFancifulName());
            prepStmt.setString(8, form.getApplicantName());
            prepStmt.setString(9, form.getMailingAddress());
            prepStmt.setString(10, form.getFormula());
            prepStmt.setString(11, form.getGrapeVarietal());
            prepStmt.setString(12, form.getAppellation());
            prepStmt.setString(13, form.getPhoneNumber().toString());
            prepStmt.setString(14, form.getEmailAddress());
            prepStmt.setString(15, "1999-05-05");
            prepStmt.setString(16, form.getPrintName());
            prepStmt.setInt(17, form.getBeerWineSpirit());
            prepStmt.setDouble(18, form.getAlcoholPercent());
            prepStmt.setInt(19, form.getVintageYear());
            prepStmt.setDouble(20, form.getpHLevel());
            prepStmt.executeUpdate();
            prepStmt.close();

        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }
    public Connection getConnection()
    {
        return connection;
    }

    public void executeStatement(PreparedStatement ps) {
        try {
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }
}
