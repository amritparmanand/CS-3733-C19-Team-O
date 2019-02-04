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
        } catch (SQLException e) {
            System.out.println("Connection Failed. Check stacktrace.");
            e.printStackTrace();
        }
        this.connection = connection;
        this.stmt = stmt;
        String createRepresentative = "create table Representatives" +
                "(repID int constraint Representatives_pk	primary key, " +
                "username varchar(20),	" +
                "password varchar(15), 	" +
                "fullName varchar(50),	" +
                "email varchar(20),	" +
                "phone bigint)";
        String createAgents = "create table Agents" +
                "(ttbID int	constraint Agents_pk primary key, " +
                "username varchar(20), " +
                "password varchar(15), " +
                "fullName varchar(50),	" +
                "email varchar(20),	" +
                "phone bigint)";
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
                "phoneNumber bigint, " +
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
            stmt.execute(createRepresentative);
            stmt.execute(createAgents);
            stmt.execute(createForms);
            stmt.execute(createUniqueReps);
            stmt.execute(createUniqueAgents);
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStmt() {
        return stmt;
    }
}
