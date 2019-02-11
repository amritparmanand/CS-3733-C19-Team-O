package Managers;

import Datatypes.Account;
import Datatypes.Agent;
import Datatypes.Form;
import Datatypes.Manufacturer;

import java.sql.*;
/**
 * @author Amrit Parmanand
 * @version It 1
 * Manages the database, handles accessing and inserting data
 */
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
                "formID int	constraint Forms_pk	primary key, " +
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
                "dateOfApplication VARCHAR(30) , " +
                "printName varchar(40),	" +
                "beerWineSpirit varchar(60), " +
                "alcoholPercent varchar(60),	" +
                "vintageYear varchar(60), " +
                "phLevel varchar(60))";
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
    public void insertForm(Form form) throws SQLException {

        String Forms1 = "INSERT INTO Forms(FORMID, REPID, BREWERNUMBER, PRODUCTSOURCE, SERIALNUMBER, " +
                "PRODUCTTYPE, BRANDNAME, FANCIFULNAME, APPLICANTNAME, MAILINGADDRESS, FORMULA, GRAPEVARIETAL, " +
                "APPELLATION, PHONENUMBER, EMAILADDRESS, DATEOFAPPLICATION, PRINTNAME, BEERWINESPIRIT, ALCOHOLPERCENT, " +
                "VINTAGEYEAR, PHLEVEL) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement prepStmt = connection.prepareStatement(Forms1);
        ResultSet seqVal = null;
        try {
            seqVal = connection.prepareStatement("values (next value for FormIDSequence)").executeQuery();
            seqVal.next();
            prepStmt.setInt(1,seqVal.getInt(1));
            prepStmt.setInt(2, form.getRepID());
            prepStmt.setString(3, form.getBrewerNumber());
            prepStmt.setString(4, form.getProductSource());
            prepStmt.setString(5, form.getSerialNumber());
            prepStmt.setString(6, form.getProductType());
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
            prepStmt.setString(18, form.getBeerWineSpirit());
            prepStmt.setString(19, form.getAlcoholPercent());
            prepStmt.setString(20, form.getVintageYear());
            prepStmt.setString(21, form.getpHLevel());
            addApp(seqVal.getInt(1),form.getRepID(),form.getDateOfApplication());
            prepStmt.executeUpdate();
            prepStmt.close();

        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    // Automatically generates and inserts an Application into database when Form is inserted
    public void addApp(int formID, int repID, String dateSubmitted) throws SQLException{
        String Apps1 = "INSERT INTO Applications(APPID, FORMID, REPID, TTBID, DATESUBMITTED, DATEAPPROVED, DATEREJECTED,STATUS) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement prepStmt = connection.prepareStatement(Apps1);
        ResultSet seqVal = null;
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
            prepStmt.executeUpdate();
            prepStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Unused?
    // Agent calls this method to approve an Application
//    public void approveApplication(int formID) throws SQLException{
//        String Apps1 = "UPDATE CUSTOMERS\n" +
//                "SET STATUS = 'Approved'\n" +
//                "WHERE ID =" + formID + ";";
//        PreparedStatement prepStmt = connection.prepareStatement(Apps1);
//        ResultSet seqVal = null;
//        try {
//
//            prepStmt.executeUpdate();
//            prepStmt.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    // ???
    public void executeStatement(PreparedStatement ps) {
        try {
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }


//     Insert the sample data for Iteration 1
//    public void insertSamples() throws SQLException{
//        String formSamples = "INSERT INTO FORMS VALUES (1,81,81,1,120009,0,'GOUGER CELLARS','MOONSHINE','GOUGER CELLARS, GOUGER CELLARS, LLC','1812 WASHINGTON ST','MOONSHINE','MOONSHINE','YAKIMA VALLEY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',7,13.95,2010,13.95), (2,81,81,1,120009,0,'GOUGER CELLARS','MOONSHINE','GOUGER CELLARS, GOUGER CELLARS, LLC','1813 WASHINGTON ST','MOONSHINE','MOONSHINE','YAKIMA VALLEY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',7,11,2010,11), (3,88,88,1,120009,0,'GOUGER CELLARS','MOONSHINE','GOUGER CELLARS, GOUGER CELLARS, LLC','1814 WASHINGTON ST','MOONSHINE','MOONSHINE','YAKIMA VALLEY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',7,14.9,2010,14.9), (4,88,88,1,120009,0,'GOUGER CELLARS','MOONSHINE','GOUGER CELLARS, GOUGER CELLARS, LLC','1815 WASHINGTON ST','MOONSHINE','MOONSHINE','YAKIMA VALLEY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',1,14.9,2010,14.9), (5,88,88,1,120009,0,'GOUGER CELLARS','MOONSHINE','GOUGER CELLARS, GOUGER CELLARS, LLC','1816 WASHINGTON ST','MOONSHINE','MOONSHINE','YAKIMA VALLEY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',7,14.7,2010,14.7), (6,88,88,1,120009,0,'GOUGER CELLARS','MOONSHINE','GOUGER CELLARS, GOUGER CELLARS, LLC','1817 WASHINGTON ST','MOONSHINE','MOONSHINE','YAKIMA VALLEY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',7,14.7,2010,14.7), (7,88,88,1,120009,0,'GOUGER CELLARS','MOONSHINE','GOUGER CELLARS, GOUGER CELLARS, LLC','1818 WASHINGTON ST','MOONSHINE','MOONSHINE','YAKIMA VALLEY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',7,14.7,2010,14.7), (8,641,641,1,120009,0,'\"REUNION\"','MOONSHINE','TIGER JUICE LLC','1819 WASHINGTON ST','MOONSHINE','MOONSHINE','YAKIMA VALLEY','\"5856457291\"','hjames@wpi.edu','2012-02-18 00:00:00','Harrison James',41,40,2010,40), (9,88,88,1,120009,0,'ORFILA VINEYARDS AND WINERY','ESTATE AMBASSADOR''S RESERVE','ORFILA VINEYARDS, ORFILA VINEYARDS, INC.','1820 WASHINGTON ST','ESTATE AMBASSADOR''S RESERVE','ESTATE AMBASSADOR''S RESERVE','YAKIMA VALLEY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',1,14.9,2010,14.9), (10,88,88,1,120010,0,'ORFILA VINEYARDS AND WINERY','ESTATE AMBASSADOR''S RESERVE','ORFILA VINEYARDS, ORFILA VINEYARDS, INC.','1821 WASHINGTON ST','ESTATE AMBASSADOR''S RESERVE','ESTATE AMBASSADOR''S RESERVE','YAKIMA VALLEY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',1,14.9,2010,14.9), (11,88,88,1,120011,0,'ORFILA VINEYARDS AND WINERY','ESTATE AMBASSADOR''S RESERVE','ORFILA VINEYARDS, ORFILA VINEYARDS, INC.','1822 WASHINGTON ST','ESTATE AMBASSADOR''S RESERVE','ESTATE AMBASSADOR''S RESERVE','YAKIMA VALLEY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',1,14.7,2010,14.7), (12,88,88,1,120012,0,'ORFILA VINEYARDS AND WINERY','ESTATE AMBASSADOR''S RESERVE','ORFILA VINEYARDS, ORFILA VINEYARDS, INC.','1823 WASHINGTON ST','ESTATE AMBASSADOR''S RESERVE','ESTATE AMBASSADOR''S RESERVE','YAKIMA VALLEY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',1,14.6,2010,14.6), (13,88,88,1,120013,0,'ORFILA VINEYARDS AND WINERY','ESTATE AMBASSADOR''S RESERVE','ORFILA VINEYARDS, ORFILA VINEYARDS, INC.','1824 WASHINGTON ST','ESTATE AMBASSADOR''S RESERVE','ESTATE AMBASSADOR''S RESERVE','YAKIMA VALLEY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',1,15.3,2010,15.3), (14,88,88,1,120014,0,'ORFILA VINEYARDS AND WINERY','ESTATE AMBASSADOR''S RESERVE','ORFILA VINEYARDS, ORFILA VINEYARDS, INC.','1825 WASHINGTON ST','ESTATE AMBASSADOR''S RESERVE','ESTATE AMBASSADOR''S RESERVE','YAKIMA VALLEY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',1,15,2010,15), (15,88,88,1,120015,1,'ORFILA VINEYARDS AND WINERY','ESTATE AMBASSADOR''S RESERVE','ORFILA VINEYARDS, ORFILA VINEYARDS, INC.','1826 WASHINGTON ST','ESTATE AMBASSADOR''S RESERVE','ESTATE AMBASSADOR''S RESERVE','YAKIMA VALLEY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',1,15.9,2010,15.9), (16,88,88,1,120016,1,'ORFILA VINEYARDS AND WINERY','ESTATE AMBASSADOR''S RESERVE','ORFILA VINEYARDS, ORFILA VINEYARDS, INC.','13455 SAN PASQUAL RD','ESTATE AMBASSADOR''S RESERVE','ESTATE AMBASSADOR''S RESERVE','YAKIMA VALLEY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',1,15.2,2010,15.2), (17,81,81,1,120017,1,'ORFILA VINEYARDS AND WINERY','MOONSHINE','ORFILA VINEYARDS, ORFILA VINEYARDS, INC.','13456 SAN PASQUAL RD','MOONSHINE','MOONSHINE','MENDOCINO COUNTY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',1,11.9,2010,11.9), (18,88,88,1,120009,1,'MOSHIN VINEYARDS','MOONSHINE','MOSHIN VINEYARDS, MOSHIN VINEYARDS, INC.','13457 SAN PASQUAL RD','MOONSHINE','MOONSHINE','MENDOCINO COUNTY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',1,14.8,2009,14.8), (19,81,81,1,120001,1,'ACKERMANN','MOONSHINE','CHATEAU BARNABY, CHATEAU BARNABY, LLC','13458 SAN PASQUAL RD','MOONSHINE','MOONSHINE','MENDOCINO COUNTY','\"5856457291\"','hjames@wpi.edu','2012-01-17 00:00:00','Harrison James',53,8.5,2009,8.5), (20,88,88,1,120009,1,'GOUGER CELLARS','MOONSHINE','GOUGER CELLARS, GOUGER CELLARS, LLC','13459 SAN PASQUAL RD','MOONSHINE','MOONSHINE','MENDOCINO COUNTY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',7,14.5,2009,14.5), (21,80,80,0,120009,1,'DOMAINE CHEVROT','MOONSHINE','JOLI VIN IMPORTS, GARY ROSHKE','13460 SAN PASQUAL RD','MOONSHINE','MOONSHINE','MENDOCINO COUNTY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',51,12.5,2009,12.5), (22,80,80,0,122865,1,'DOMAINE CHEVROT','MOONSHINE','GOLDEN STATE WINE CO., G.S.W.C., INC.','13461 SAN PASQUAL RD','MOONSHINE','MOONSHINE','MENDOCINO COUNTY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',51,13,2009,13), (23,80,80,0,120001,1,'DOMAINE CHEVROT','MOONSHINE','FLEISCHER INTERNATIONAL TRADING, INC.','13462 SAN PASQUAL RD','MOONSHINE','MOONSHINE','MENDOCINO COUNTY','\"5856457291\"','hjames@wpi.edu','2012-01-17 00:00:00','Harrison James',52,13.3,2009,13.3), (24,85,85,0,120001,1,'DOMAINE CHEVROT','MOONSHINE','ROUND 8, LLC','13463 SAN PASQUAL RD','MOONSHINE','MOONSHINE','MENDOCINO COUNTY','\"5856457291\"','hjames@wpi.edu','2012-01-19 00:00:00','Harrison James',52,1,2009,13), (25,301,301,0,120001,1,'DOMAINE CHEVROT','MOONSHINE','SMUGGLERS'' NOTCH DISTILLERY, LLC','13464 SAN PASQUAL RD','MOONSHINE','MOONSHINE','MENDOCINO COUNTY','\"5856457291\"','hjames@wpi.edu','2012-01-19 00:00:00','Harrison James',46,40,2009,40), (26,902,902,0,120001,1,'DOMAINE CHEVROT','MOONSHINE','FOUR CORNERS GRILLE AND FLYING GOOSE BREW PUB, TJM ENTERPRISES, INC.','13465 SAN PASQUAL RD','MOONSHINE','MOONSHINE','MENDOCINO COUNTY','\"5856457291\"','hjames@wpi.edu','2012-01-05 00:00:00','Harrison James',33,1,2009,13), (27,80,80,0,120009,1,'DOMAINE CHEVROT','MOONSHINE','TASTY WINE COMPANY, A.B. COMPANY OF WISCONSIN INC.','13466 SAN PASQUAL RD','MOONSHINE','MOONSHINE','MENDOCINO COUNTY','\"5856457291\"','hjames@wpi.edu','2012-01-13 00:00:00','Harrison James',1,12.5,2009,12.5), (28,902,902,0,120009,1,'DOMAINE CHEVROT','MOONSHINE','ROHRBACH BREWING COMPANY, RAILROAD STREET BREWING COMPANY INC.','13467 SAN PASQUAL RD','MOONSHINE','MOONSHINE','MENDOCINO COUNTY','\"5856457291\"','hjames@wpi.edu','2012-01-18 00:00:00','Harrison James',2,1,2009,13), (29,906,906,0,120009,1,'DOMAINE CHEVROT','MOONSHINE','CITY STEAM BREWERY, THOMAS HOOKER BREWING COMPANY, LLC','13468 SAN PASQUAL RD','MOONSHINE','MOONSHINE','MENDOCINO COUNTY','\"5856457291\"','hjames@wpi.edu','2012-01-06 00:00:00','Harrison James',14,5.9,2009,5.9)";
//        String appSamples = "INSERT INTO APPLICATIONS VALUES (1,1,81,1,'HARRISON','1999-01-01 00:00:00','.','.','.','APPROVED'), (2,2,81,3,'AMRIT','1999-01-02 00:00:00','.','.','.','DENIED'), (3,3,88,5,'PERCY','1999-01-03 00:00:00','.','.','.','APPROVED'), (4,4,88,7,'LIZ','1999-01-04 00:00:00','.','.','.','DENIED'), (5,5,88,9,'TREVOR','1999-01-05 00:00:00','.','.','.','APPROVED'), (6,6,88,11,'ROBERT','1999-01-06 00:00:00','.','.','.','DENIED'), (7,7,88,13,'JOHN','1999-01-07 00:00:00','.','.','.','APPROVED'), (8,8,641,15,'GABE','1999-01-08 00:00:00','.','.','.','DENIED'), (9,9,88,17,'SRI','1999-01-09 00:00:00','.','.','.','APPROVED'), (10,10,88,19,'SAM','1999-01-10 00:00:00','.','.','.','DENIED'), (11,11,88,21,'CLAY','1999-01-11 00:00:00','.','.','.','APPROVED'), (12,12,88,23,'HARRISON','1999-01-12 00:00:00','.','.','.','DENIED'), (13,13,88,25,'AMRIT','1999-01-13 00:00:00','.','.','.','APPROVED'), (14,14,88,27,'PERCY','1999-01-14 00:00:00','.','.','.','DENIED'), (15,15,88,29,'LIZ','1999-01-15 00:00:00','.','.','.','APPROVED'), (16,16,88,31,'TREVOR','1999-01-16 00:00:00','.','.','.','DENIED'), (17,17,81,33,'ROBERT','1999-01-17 00:00:00','.','.','.','APPROVED'), (18,18,88,35,'JOHN','1999-01-18 00:00:00','.','.','.','DENIED'), (19,19,81,37,'GABE','1999-01-19 00:00:00','.','.','.','APPROVED'), (20,20,88,39,'SRI','1999-01-20 00:00:00','.','.','.','DENIED'), (21,21,80,41,'SAM','1999-01-21 00:00:00','.','.','.','APPROVED'), (22,22,80,43,'CLAY','1999-01-22 00:00:00','.','.','.','DENIED'), (23,23,80,45,'HARRISON','1999-01-23 00:00:00','.','.','.','APPROVED'), (24,24,85,47,'AMRIT','1999-01-24 00:00:00','.','.','.','DENIED'), (25,25,301,49,'PERCY','1999-01-25 00:00:00','.','.','.','APPROVED'), (26,26,902,51,'LIZ','1999-01-26 00:00:00','.','.','.','DENIED'), (27,27,80,53,'TREVOR','1999-01-27 00:00:00','.','.','.','APPROVED'), (28,28,902,55,'ROBERT','1999-01-28 00:00:00','.','.','.','DENIED'), (29,29,906,57,'JOHN','1999-01-29 00:00:00','.','.','.','APPROVED')";
//        try {
//            this.stmt.execute(formSamples);
//            this.stmt.execute(appSamples);
//        }catch(SQLException e){
//            if (!e.getSQLState().equals("23505"))
//                e.printStackTrace();
//        }
//    }
}
