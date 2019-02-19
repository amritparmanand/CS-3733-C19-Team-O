package Managers;

import Datatypes.Account;
import Datatypes.Agent;
import Datatypes.Form;
import Datatypes.Manufacturer;
import Fuzzy.Damerau_Levenshtein;
import Fuzzy.FuzzyContext;
import Fuzzy.Levenshtein;
import Fuzzy.hiddenScore;
import com.opencsv.CSVReader;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.*;
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
            CallableStatement cs = connection.prepareCall
                    ("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.language.sequence.preallocator', '1')");
            cs.execute();
            cs.close();
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

    /**
     * Find username and password for an account by its id
     * @param id
     * @return the username or password
     */
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

    /**
     * Generate the tables in database and create the sequences for ids
     */
    public void generateTables(){
        String createApplications = "create table Applications(" +
                "appID int constraint Applications_pk primary key," +
                "formID bigint /*constraint APPLICATIONS_FORMS_FORMID_FK	references FORMS*/," +
                "repID int /*constraint APPLICATIONS_REPRESENTATIVES_REPID_FK	references REPRESENTATIVES*/," +
                "ttbID int /*constraint APPLICATIONS_AGENTS_TTBID_FK references AGENTS*/," +
                "agentName VARCHAR(40)," +
                "dateSubmitted VARCHAR(20) ," +
                "dateApproved VARCHAR(20)," +
                "dateRejected VARCHAR(20)," +
                "dateExpired VARCHAR(20)," +
                "status VARCHAR(15)," +
                "dateIssued VARCHAR(20)," +
                "signature VARCHAR(40))";
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
                "email varchar(100),	" +
                "phone varchar(40))";
//        String createForms = "create table Forms(" +
//                "formID int	constraint Forms_pk	primary key, " +
//                "repID int, " +
//                "brewerNumber varchar(100),	" +
//                "productSource varchar(100),	" +
//                "serialNumber varchar(100),	" +
//                "productType varchar(100),	" +
//                "brandName varchar(100),	" +
//                "fancifulName varchar(100),	" +
//                "applicantName varchar(200),	" +
//                "mailingAddress varchar(80), " +
//                "formula varchar(80), " +
//                "grapeVarietal varchar(80),	" +
//                "appellation varchar(100), " +
//                "phoneNumber varchar(20), " +
//                "emailAddress varchar(50),	" +
//                "certificateOfApproval boolean," +   //begin new
//                "certificateOfExemption boolean," +
//                "onlyState varchar(2)," +
//                "distinctiveLiquor boolean," +
//                "bottleCapacity VARCHAR(5)," +
//                "resubmission boolean," +
//                "ttbID int ," + //end new
//                "dateOfApplication VARCHAR(30) , " +
//                "printName varchar(40),	" +
//                "beerWineSpirit varchar(100), " +
//                "alcoholPercent varchar(100),	" +
//                "vintageYear varchar(100), " +
//                "phLevel varchar(100))";


        String createForms = "create table Forms(" +
                "formID bigint   constraint Forms_pk primary key, " +
                "repID varchar (20), " +
                "brewerNumber varchar(100), " +
                "productSource varchar(100),    " +
                "serialNumber varchar(100), " +
                "productType varchar(100),  " +
                "brandName varchar(100),    " +
                "fancifulName varchar(100), " +
                "applicantName varchar(200),   " +
                "mailingAddress varchar(120), " +
                "formula varchar(120), " +
                "grapeVarietal varchar(200),    " +
                "appellation varchar(200), " +
                "phoneNumber varchar(20), " +
                "emailAddress varchar(50), " +
                "certificateOfApproval BOOLEAN," +   //begin new
                "certificateOfExemption BOOLEAN," +
                "onlyState varchar(100)," +
                "distinctiveLiquor BOOLEAN," +
                "bottleCapacity VARCHAR(300)," +
                "resubmission BOOLEAN," +
                "ttbID int," + //end new
                "dateOfApplication VARCHAR(100) , " +
                "printName varchar(100),    " +
                "beerWineSpirit varchar(100), " +
                "alcoholPercent varchar(100),   " +
                "vintageYear varchar(100), " +
                "phLevel varchar(100), "+
                "labelImage blob(32M)) ";
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

    /**
     * Insert the default manufacturer and agent
     * @throws SQLException
     */
    public void insertDefault() throws SQLException{
        String mPassword = this.passwordEncoder.encode("manu");
        String aPassword = this.passwordEncoder.encode("ttb");

        String mDefault = "insert into REPRESENTATIVES values (1, 'manu', '" + mPassword + "', 'manu', 'manu', 'manu', 'manu')";
        String aDefault = "insert into AGENTS values (1, 'ttb', '" + aPassword + "', 'ttb', 'ttb', 'ttb')";
        try {
            this.stmt.execute(mDefault);
            this.stmt.execute(aDefault);
        }catch(SQLException e){
            if (!e.getSQLState().equals("23505"))
                e.printStackTrace();
        }
    }
    public boolean isFormsEmpty() throws SQLException{
        ResultSet rs = stmt.executeQuery("SELECT * from FORMS");

        // checking if ResultSet is empty
        if (!rs.next()) {
            return true;
        }
        else
            return false;
    }
    public boolean isAppsEmpty() throws SQLException{
        ResultSet rs = stmt.executeQuery("SELECT * from APPLICATIONS");

        // checking if ResultSet is empty
        if (!rs.next()) {
            return true;
        }
        else
            return false;
    }

    public void generateTablesForms() {
        try {

            // Create an object of filereader
            // class with CSV file as a parameter.
            ClassLoader classLoader = getClass().getClassLoader();
            FileReader filereader = new FileReader(new File(classLoader.getResource("Resources/forPresentation.csv").getFile()));

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            String bigString = "INSERT INTO FORMS VALUES";
            int numOfOutput = 1;
            int numOfSqlExecute = 0;
            nextRecord = csvReader.readNext();
            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {

                String output = "(";
                int counter = 0;
                for (int i = 0; i < nextRecord.length; i++) {

                    String[] splitRecord = nextRecord[i].split("!");

                    for (int j = 0; j < splitRecord.length; j++) {


                        if (counter == 0) {
                            output += splitRecord[j] + ",'";
                        } else if (counter == 27) {
                            output += splitRecord[j] + "', NULL)";
                            break;

                        } else if (counter == 14 || counter == 17 || counter == 19) {
                            output += splitRecord[j] + "',";
                        } else if (counter == 15 || counter == 20) {
                            output += splitRecord[j] + ",";

                        } else if (counter == 16 || counter == 18) {
                            output += splitRecord[j] + ",'";

                        }
                        else if(counter == 21)
                        {
                            output += " 999,'";

                        }
                            else {
                            output += splitRecord[j] + "','";
                            if (output.charAt(1) != '1' && output.charAt(1) != '2' && output.charAt(1) != '3') {
                                break;
                            }
                        }

                        counter++;
                        if (counter > 27) {
                            counter = 0;
                            output = "";
                            break;
                        }

                    }

                }

                if (counter == 27) {

//                    || output.charAt(2) == 2 || output.charAt(2) == 3
                    if (numOfOutput < 999) {

                        bigString += output + ",\n";
                    } else {

                        bigString += output;

                    }
                    numOfOutput++;


                }
                if (numOfOutput == 1000) {
                    System.out.println(numOfSqlExecute);
                    if (numOfSqlExecute == 0) {
                        System.out.println(bigString);
                    }
                    stmt.executeUpdate(bigString);
                    bigString = "INSERT INTO FORMS VALUES";
                    numOfOutput = 0;
                    numOfSqlExecute++;

                    System.out.println("Printed");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")
    //Duplicate
    public void generateTablesApplication()
    {
        try {


            // Create an object of filereader
            // class with CSV file as a parameter.
            ClassLoader classLoader = getClass().getClassLoader();
            FileReader filereader = new FileReader(new File(classLoader.getResource("Resources/ApplicationsXLSX.csv").getFile()));
            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            String bigString = "INSERT INTO APPLICATIONS VALUES";
            int numOfOutput = 1;
            int numOfSqlExecute = 0;
            nextRecord = csvReader.readNext();
            int appidCounter = 1000;
            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null && appidCounter < 240000) {
                String output = "(" + appidCounter + ",";
                int counter = 0;
                for (int i = 0; i < nextRecord.length; i++) {
                    String[] splitRecord = nextRecord[i].split("!");

                    for(int j = 0; j < splitRecord.length; j++)
                    {


                        if(counter == 0 || counter == 1 || counter ==2)
                        {
                            output += splitRecord[j] + ",";

                        }
                        else if(counter == 3)
                        {
                            output+="'" + splitRecord[j]+"','";
                        }
                        else if(counter == 10)
                    {
                        output += splitRecord[j] + "')";
                        break;

                    }
                       else {
                        output += splitRecord[j] + "','";
                    }

                        counter++;
                        if(counter>10)
                        {
                            counter = 0;
                            output = "";
                            break;
                        }
                    }

                }

                if(counter == 10) {

//                    || output.charAt(2) == 2 || output.charAt(2) == 3
                    if (numOfOutput < 999) {

                        bigString += output + ",\n";

                    } else {

                        bigString += output;

                    }
                    numOfOutput++;
                    appidCounter++;


                }
                if(numOfOutput == 1000)
                {
                    System.out.println(numOfSqlExecute);

//                        System.out.println(bigString);


                        stmt.executeUpdate(bigString);

                    bigString = "INSERT INTO APPLICATIONS VALUES";
                    numOfOutput = 0;
                    numOfSqlExecute++;


                    System.out.println("Printed");
                }

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
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
}
