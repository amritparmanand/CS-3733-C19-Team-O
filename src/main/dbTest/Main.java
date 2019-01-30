package dbTest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Database Example");
        primaryStage.setScene(new Scene(root, 350, 500));
        primaryStage.show();
        DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:derby:ttbDB;create=true");
            stmt = connection.createStatement();
        }
        catch(SQLException e){
            System.out.println("Connection Failed. Check stacktrace.");
            e.printStackTrace();
        }
        String createManTable = "create table MANUFACTURERS(MANUFACTURER_ID INTEGER not null constraint MANUFACTURERS_PK primary key," +
                " NAME VARCHAR(30),	" +
                "EMAIL VARCHAR(20)," +
                "PHONENUMBER BIGINT)";
        String createRepTable= "create table REPS( REP_ID INTEGER not null	constraint REPS_PK primary key,	FIRSTNAME VARCHAR(15), " +
                "LASTNAME VARCHAR(15), " +
                "EMAIL VARCHAR(30), " +
                "PHONENUMBER BIGINT, " +
                "COMPANYNAME VARCHAR(50), " +
                "USERNAME VARCHAR(15), " +
                "PASSWORD VARCHAR(15), " +
                "MANUFACTURER_ID INTEGER " +
                "constraint REPS_MANUFACTURERS_MANUFACTURER_ID_FK " +
                "references MANUFACTURERS)";
        String createAppTable = "create table APPLICATION(APPLICATION_ID INTEGER not null constraint APPLICATION_PK primary key, " +
                "REP_ID INTEGER constraint APPLICATION_REPS_REP_ID_FK references REPS, " +
                "TTB_ID INTEGER,DATE_SUBMITTED DATE, " +
                "DATE_APPROVED DATE, " +
                "DATE_REJECTED DATE )";
        try {
            stmt.execute(createManTable);
            stmt.execute(createRepTable);
            stmt.execute(createAppTable);
        }
        catch (SQLException e){
            if (!e.getSQLState().equals("X0Y32"))
                throw e;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}