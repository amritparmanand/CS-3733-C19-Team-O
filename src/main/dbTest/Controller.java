package main.dbTest;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.*;
import java.util.Random;

public class Controller {
    @FXML
    private Button sendData;
    @FXML
    private Button getButton;
    @FXML
    private Label field1;

    public void sendRandomData(){
        Random r = new Random();

        //Creates a connection object, use for connection to a database
        Connection connection = null;
        //Creates a statement object, used for interfacing with SQL
        Statement stmt = null;


        try {
            //Instantiates the connection to the database located in the ttbDB directory, if it doesn't exist it creates the database
            connection = DriverManager.getConnection("jdbc:derby:ttbDB;create=true");
            //Instantiates the statement object based on the connection object
            stmt = connection.createStatement();
        }
        catch(SQLException e){
            System.out.println("Connection Failed. Check stacktrace.");
            e.printStackTrace();
        }
        //Generates SQL code as a string
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String generatedData = "INSERT INTO MANUFACTURERS VALUES("+ r.nextInt(50) + ", " +
                    " '" + alphabet.charAt(r.nextInt(alphabet.length())) +
                    alphabet.charAt(r.nextInt(alphabet.length())) +
                    alphabet.charAt(r.nextInt(alphabet.length()))+ "'," +
                    "'example@sample.com'," +
                    r.nextInt(9999999)+ ")";

        //Prints the generatedData string for testing
        System.out.println(generatedData);

        try{
            //Attempts to run the generated SQL code written above
            stmt.execute(generatedData);
        }
        catch(SQLException e){
            if (!e.getSQLState().equals("23505"))
                e.printStackTrace();
            else
                System.out.println("Used a duplicate ID");
        }
    }
    public void clearData(){
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
        //Needs 1=1 to run for some reason
        String clear = "DELETE FROM MANUFACTURERS WHERE 1=1";
        try{
            stmt.execute(clear);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void buildData(){
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

        Statement st = null;
        //A result set is a table you receive from SELECT in SQL
        ResultSet rs = null;
        //Meta data is how many rows
        ResultSetMetaData rsmd;
        int columnsNumber = 0;
        try {
            //statement is created with given connection
            st = connection.createStatement();
            //ResultSet holds the output from the SELECT that statement executes
            //Execute is used for inserts, deletes, table creation
            //ExecuteQuery is used for SELECTS
            rs = st.executeQuery("SELECT * FROM MANUFACTURERS");
            //Stores the metaData of the Result Set in the Result Set Meta Data
            rsmd = rs.getMetaData();
            //Establishes the column numbers
            columnsNumber = rsmd.getColumnCount();
        } catch(SQLException e){
            e.printStackTrace();
        }
        String out = "";
        try {
            //while the result set has another row continue
            while (rs.next()) {
                //proceeds through all columns
                for (int i = 1; i <= columnsNumber; i++) {

                    out += (rs.getString(i) + " "); //Print one element of a row

                }

                out += "\n";//Move to the next line to print the next row.

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //sets the field text to out
        field1.setText(out);
    }
}
