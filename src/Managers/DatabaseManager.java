package Managers;

import java.sql.*;

public class DatabaseManager {
    private Connection connection;
    private Statement stmt;

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
        String createRepresentative = "create table Representatives" +
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
            this.stmt.execute(createRepresentative);
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

    public Connection getConnection() {
        return this.connection;
    }

    public Statement getStmt() {
        return this.stmt;
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
